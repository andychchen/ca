/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.controllers;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.PreexistingEntityException;
import jpa.controllers.exceptions.RollbackFailureException;
import jpa.entities.Promotion;
import java.util.ArrayList;
import java.util.Collection;
import jpa.entities.WeekDays;

/**
 *
 * @author achen
 */
public class WeekDaysJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(WeekDays weekDays) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (weekDays.getPromotionCollection() == null) {
            weekDays.setPromotionCollection(new ArrayList<Promotion>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Promotion> attachedPromotionCollection = new ArrayList<Promotion>();
            for (Promotion promotionCollectionPromotionToAttach : weekDays.getPromotionCollection()) {
                promotionCollectionPromotionToAttach = em.getReference(promotionCollectionPromotionToAttach.getClass(), promotionCollectionPromotionToAttach.getPromotionId());
                attachedPromotionCollection.add(promotionCollectionPromotionToAttach);
            }
            weekDays.setPromotionCollection(attachedPromotionCollection);
            em.persist(weekDays);
            /*for (Promotion promotionCollectionPromotion : weekDays.getPromotionCollection()) {
                WeekDays oldWeeklyDayOfPromotionCollectionPromotion = promotionCollectionPromotion.getWeeklyDay();
                promotionCollectionPromotion.setWeeklyDay(weekDays);
                promotionCollectionPromotion = em.merge(promotionCollectionPromotion);
                if (oldWeeklyDayOfPromotionCollectionPromotion != null) {
                    oldWeeklyDayOfPromotionCollectionPromotion.getPromotionCollection().remove(promotionCollectionPromotion);
                    oldWeeklyDayOfPromotionCollectionPromotion = em.merge(oldWeeklyDayOfPromotionCollectionPromotion);
                }
            }*/
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findWeekDays(weekDays.getDay()) != null) {
                throw new PreexistingEntityException("WeekDays " + weekDays + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(WeekDays weekDays) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            WeekDays persistentWeekDays = em.find(WeekDays.class, weekDays.getDay());
            Collection<Promotion> promotionCollectionOld = persistentWeekDays.getPromotionCollection();
            Collection<Promotion> promotionCollectionNew = weekDays.getPromotionCollection();
            Collection<Promotion> attachedPromotionCollectionNew = new ArrayList<Promotion>();
            for (Promotion promotionCollectionNewPromotionToAttach : promotionCollectionNew) {
                promotionCollectionNewPromotionToAttach = em.getReference(promotionCollectionNewPromotionToAttach.getClass(), promotionCollectionNewPromotionToAttach.getPromotionId());
                attachedPromotionCollectionNew.add(promotionCollectionNewPromotionToAttach);
            }
            promotionCollectionNew = attachedPromotionCollectionNew;
            weekDays.setPromotionCollection(promotionCollectionNew);
            weekDays = em.merge(weekDays);
            /*for (Promotion promotionCollectionOldPromotion : promotionCollectionOld) {
                if (!promotionCollectionNew.contains(promotionCollectionOldPromotion)) {
                    promotionCollectionOldPromotion.setWeeklyDay(null);
                    promotionCollectionOldPromotion = em.merge(promotionCollectionOldPromotion);
                }
            }
            for (Promotion promotionCollectionNewPromotion : promotionCollectionNew) {
                if (!promotionCollectionOld.contains(promotionCollectionNewPromotion)) {
                    WeekDays oldWeeklyDayOfPromotionCollectionNewPromotion = promotionCollectionNewPromotion.getWeeklyDay();
                    promotionCollectionNewPromotion.setWeeklyDay(weekDays);
                    promotionCollectionNewPromotion = em.merge(promotionCollectionNewPromotion);
                    if (oldWeeklyDayOfPromotionCollectionNewPromotion != null && !oldWeeklyDayOfPromotionCollectionNewPromotion.equals(weekDays)) {
                        oldWeeklyDayOfPromotionCollectionNewPromotion.getPromotionCollection().remove(promotionCollectionNewPromotion);
                        oldWeeklyDayOfPromotionCollectionNewPromotion = em.merge(oldWeeklyDayOfPromotionCollectionNewPromotion);
                    }
                }
            }*/
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = weekDays.getDay();
                if (findWeekDays(id) == null) {
                    throw new NonexistentEntityException("The weekDays with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            WeekDays weekDays;
            try {
                weekDays = em.getReference(WeekDays.class, id);
                weekDays.getDay();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The weekDays with id " + id + " no longer exists.", enfe);
            }
            Collection<Promotion> promotionCollection = weekDays.getPromotionCollection();
            for (Promotion promotionCollectionPromotion : promotionCollection) {
                promotionCollectionPromotion.setWeeklyDay(null);
                promotionCollectionPromotion = em.merge(promotionCollectionPromotion);
            }
            em.remove(weekDays);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<WeekDays> findWeekDaysEntities() {
        return findWeekDaysEntities(true, -1, -1);
    }

    public List<WeekDays> findWeekDaysEntities(int maxResults, int firstResult) {
        return findWeekDaysEntities(false, maxResults, firstResult);
    }

    private List<WeekDays> findWeekDaysEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from WeekDays as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public WeekDays findWeekDays(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(WeekDays.class, id);
        } finally {
            em.close();
        }
    }

    public int getWeekDaysCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from WeekDays as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
