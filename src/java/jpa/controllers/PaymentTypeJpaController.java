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
import jpa.controllers.exceptions.RollbackFailureException;
import jpa.entities.PaymentType;
import jpa.entities.TranPayment;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author achen
 */
public class PaymentTypeJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaymentType paymentType) throws RollbackFailureException, Exception {
        if (paymentType.getTranPaymentCollection() == null) {
            paymentType.setTranPaymentCollection(new ArrayList<TranPayment>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<TranPayment> attachedTranPaymentCollection = new ArrayList<TranPayment>();
            for (TranPayment tranPaymentCollectionTranPaymentToAttach : paymentType.getTranPaymentCollection()) {
                tranPaymentCollectionTranPaymentToAttach = em.getReference(tranPaymentCollectionTranPaymentToAttach.getClass(), tranPaymentCollectionTranPaymentToAttach.getId());
                attachedTranPaymentCollection.add(tranPaymentCollectionTranPaymentToAttach);
            }
            paymentType.setTranPaymentCollection(attachedTranPaymentCollection);
            em.persist(paymentType);
            /*for (TranPayment tranPaymentCollectionTranPayment : paymentType.getTranPaymentCollection()) {
                PaymentType oldPaymentTypeOfTranPaymentCollectionTranPayment = tranPaymentCollectionTranPayment.getPaymentType();
                tranPaymentCollectionTranPayment.setPaymentType(paymentType);
                tranPaymentCollectionTranPayment = em.merge(tranPaymentCollectionTranPayment);
                if (oldPaymentTypeOfTranPaymentCollectionTranPayment != null) {
                    oldPaymentTypeOfTranPaymentCollectionTranPayment.getTranPaymentCollection().remove(tranPaymentCollectionTranPayment);
                    oldPaymentTypeOfTranPaymentCollectionTranPayment = em.merge(oldPaymentTypeOfTranPaymentCollectionTranPayment);
                }
            }*/
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

    public void edit(PaymentType paymentType) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PaymentType persistentPaymentType = em.find(PaymentType.class, paymentType.getId());
            Collection<TranPayment> tranPaymentCollectionOld = persistentPaymentType.getTranPaymentCollection();
            Collection<TranPayment> tranPaymentCollectionNew = paymentType.getTranPaymentCollection();
            List<String> illegalOrphanMessages = null;
            for (TranPayment tranPaymentCollectionOldTranPayment : tranPaymentCollectionOld) {
                if (!tranPaymentCollectionNew.contains(tranPaymentCollectionOldTranPayment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TranPayment " + tranPaymentCollectionOldTranPayment + " since its paymentType field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TranPayment> attachedTranPaymentCollectionNew = new ArrayList<TranPayment>();
            for (TranPayment tranPaymentCollectionNewTranPaymentToAttach : tranPaymentCollectionNew) {
                tranPaymentCollectionNewTranPaymentToAttach = em.getReference(tranPaymentCollectionNewTranPaymentToAttach.getClass(), tranPaymentCollectionNewTranPaymentToAttach.getId());
                attachedTranPaymentCollectionNew.add(tranPaymentCollectionNewTranPaymentToAttach);
            }
            tranPaymentCollectionNew = attachedTranPaymentCollectionNew;
            paymentType.setTranPaymentCollection(tranPaymentCollectionNew);
            paymentType = em.merge(paymentType);
            /*for (TranPayment tranPaymentCollectionNewTranPayment : tranPaymentCollectionNew) {
                if (!tranPaymentCollectionOld.contains(tranPaymentCollectionNewTranPayment)) {
                    PaymentType oldPaymentTypeOfTranPaymentCollectionNewTranPayment = tranPaymentCollectionNewTranPayment.getPaymentType();
                    tranPaymentCollectionNewTranPayment.setPaymentType(paymentType);
                    tranPaymentCollectionNewTranPayment = em.merge(tranPaymentCollectionNewTranPayment);
                    if (oldPaymentTypeOfTranPaymentCollectionNewTranPayment != null && !oldPaymentTypeOfTranPaymentCollectionNewTranPayment.equals(paymentType)) {
                        oldPaymentTypeOfTranPaymentCollectionNewTranPayment.getTranPaymentCollection().remove(tranPaymentCollectionNewTranPayment);
                        oldPaymentTypeOfTranPaymentCollectionNewTranPayment = em.merge(oldPaymentTypeOfTranPaymentCollectionNewTranPayment);
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
                Integer id = paymentType.getId();
                if (findPaymentType(id) == null) {
                    throw new NonexistentEntityException("The paymentType with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PaymentType paymentType;
            try {
                paymentType = em.getReference(PaymentType.class, id);
                paymentType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paymentType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TranPayment> tranPaymentCollectionOrphanCheck = paymentType.getTranPaymentCollection();
            for (TranPayment tranPaymentCollectionOrphanCheckTranPayment : tranPaymentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PaymentType (" + paymentType + ") cannot be destroyed since the TranPayment " + tranPaymentCollectionOrphanCheckTranPayment + " in its tranPaymentCollection field has a non-nullable paymentType field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(paymentType);
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

    public List<PaymentType> findPaymentTypeEntities() {
        return findPaymentTypeEntities(true, -1, -1);
    }

    public List<PaymentType> findPaymentTypeEntities(int maxResults, int firstResult) {
        return findPaymentTypeEntities(false, maxResults, firstResult);
    }

    private List<PaymentType> findPaymentTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PaymentType as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PaymentType findPaymentType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaymentType.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaymentTypeCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from PaymentType as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
