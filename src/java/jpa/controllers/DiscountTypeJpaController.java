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
import jpa.controllers.exceptions.IllegalOrphanException;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.PreexistingEntityException;
import jpa.controllers.exceptions.RollbackFailureException;
import jpa.entities.Discount;
import java.util.ArrayList;
import java.util.Collection;
import jpa.entities.DiscountType;

/**
 *
 * @author achen
 */
public class DiscountTypeJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DiscountType discountType) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (discountType.getDiscountCollection() == null) {
            discountType.setDiscountCollection(new ArrayList<Discount>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Discount> attachedDiscountCollection = new ArrayList<Discount>();
            for (Discount discountCollectionDiscountToAttach : discountType.getDiscountCollection()) {
                discountCollectionDiscountToAttach = em.getReference(discountCollectionDiscountToAttach.getClass(), discountCollectionDiscountToAttach.getDiscountId());
                attachedDiscountCollection.add(discountCollectionDiscountToAttach);
            }
            discountType.setDiscountCollection(attachedDiscountCollection);
            em.persist(discountType);
            /*for (Discount discountCollectionDiscount : discountType.getDiscountCollection()) {
                DiscountType oldTypeOfDiscountCollectionDiscount = discountCollectionDiscount.getType();
                discountCollectionDiscount.setType(discountType);
                discountCollectionDiscount = em.merge(discountCollectionDiscount);
                if (oldTypeOfDiscountCollectionDiscount != null) {
                    oldTypeOfDiscountCollectionDiscount.getDiscountCollection().remove(discountCollectionDiscount);
                    oldTypeOfDiscountCollectionDiscount = em.merge(oldTypeOfDiscountCollectionDiscount);
                }
            }*/
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findDiscountType(discountType.getType()) != null) {
                throw new PreexistingEntityException("DiscountType " + discountType + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DiscountType discountType) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            DiscountType persistentDiscountType = em.find(DiscountType.class, discountType.getType());
            Collection<Discount> discountCollectionOld = persistentDiscountType.getDiscountCollection();
            Collection<Discount> discountCollectionNew = discountType.getDiscountCollection();
            List<String> illegalOrphanMessages = null;
            for (Discount discountCollectionOldDiscount : discountCollectionOld) {
                if (!discountCollectionNew.contains(discountCollectionOldDiscount)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Discount " + discountCollectionOldDiscount + " since its type field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Discount> attachedDiscountCollectionNew = new ArrayList<Discount>();
            for (Discount discountCollectionNewDiscountToAttach : discountCollectionNew) {
                discountCollectionNewDiscountToAttach = em.getReference(discountCollectionNewDiscountToAttach.getClass(), discountCollectionNewDiscountToAttach.getDiscountId());
                attachedDiscountCollectionNew.add(discountCollectionNewDiscountToAttach);
            }
            discountCollectionNew = attachedDiscountCollectionNew;
            discountType.setDiscountCollection(discountCollectionNew);
            discountType = em.merge(discountType);
            /*for (Discount discountCollectionNewDiscount : discountCollectionNew) {
                if (!discountCollectionOld.contains(discountCollectionNewDiscount)) {
                    DiscountType oldTypeOfDiscountCollectionNewDiscount = discountCollectionNewDiscount.getType();
                    discountCollectionNewDiscount.setType(discountType);
                    discountCollectionNewDiscount = em.merge(discountCollectionNewDiscount);
                    if (oldTypeOfDiscountCollectionNewDiscount != null && !oldTypeOfDiscountCollectionNewDiscount.equals(discountType)) {
                        oldTypeOfDiscountCollectionNewDiscount.getDiscountCollection().remove(discountCollectionNewDiscount);
                        oldTypeOfDiscountCollectionNewDiscount = em.merge(oldTypeOfDiscountCollectionNewDiscount);
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
                String id = discountType.getType();
                if (findDiscountType(id) == null) {
                    throw new NonexistentEntityException("The discountType with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            DiscountType discountType;
            try {
                discountType = em.getReference(DiscountType.class, id);
                discountType.getType();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The discountType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Discount> discountCollectionOrphanCheck = discountType.getDiscountCollection();
            for (Discount discountCollectionOrphanCheckDiscount : discountCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DiscountType (" + discountType + ") cannot be destroyed since the Discount " + discountCollectionOrphanCheckDiscount + " in its discountCollection field has a non-nullable type field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(discountType);
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

    public List<DiscountType> findDiscountTypeEntities() {
        return findDiscountTypeEntities(true, -1, -1);
    }

    public List<DiscountType> findDiscountTypeEntities(int maxResults, int firstResult) {
        return findDiscountTypeEntities(false, maxResults, firstResult);
    }

    private List<DiscountType> findDiscountTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from DiscountType as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DiscountType findDiscountType(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DiscountType.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiscountTypeCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from DiscountType as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
