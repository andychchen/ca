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
import jpa.controllers.exceptions.RollbackFailureException;
import jpa.entities.ReturnTranHead;
import jpa.entities.ReturnTranPayment;

/**
 *
 * @author achen
 */
public class ReturnTranPaymentJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReturnTranPayment returnTranPayment) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ReturnTranHead tranHeadId = returnTranPayment.getTranHeadId();
            if (tranHeadId != null) {
                tranHeadId = em.getReference(tranHeadId.getClass(), tranHeadId.getTranHeadId());
                returnTranPayment.setTranHeadId(tranHeadId);
            }
            em.persist(returnTranPayment);
            /*if (tranHeadId != null) {
                tranHeadId.getReturnTranPaymentCollection().add(returnTranPayment);
                tranHeadId = em.merge(tranHeadId);
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

    public void edit(ReturnTranPayment returnTranPayment) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ReturnTranPayment persistentReturnTranPayment = em.find(ReturnTranPayment.class, returnTranPayment.getId());
            ReturnTranHead tranHeadIdOld = persistentReturnTranPayment.getTranHeadId();
            ReturnTranHead tranHeadIdNew = returnTranPayment.getTranHeadId();
            if (tranHeadIdNew != null) {
                tranHeadIdNew = em.getReference(tranHeadIdNew.getClass(), tranHeadIdNew.getTranHeadId());
                returnTranPayment.setTranHeadId(tranHeadIdNew);
            }
            returnTranPayment = em.merge(returnTranPayment);
            if (tranHeadIdOld != null && !tranHeadIdOld.equals(tranHeadIdNew)) {
                tranHeadIdOld.getReturnTranPaymentCollection().remove(returnTranPayment);
                tranHeadIdOld = em.merge(tranHeadIdOld);
            }
            if (tranHeadIdNew != null && !tranHeadIdNew.equals(tranHeadIdOld)) {
                tranHeadIdNew.getReturnTranPaymentCollection().add(returnTranPayment);
                tranHeadIdNew = em.merge(tranHeadIdNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = returnTranPayment.getId();
                if (findReturnTranPayment(id) == null) {
                    throw new NonexistentEntityException("The returnTranPayment with id " + id + " no longer exists.");
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
            ReturnTranPayment returnTranPayment;
            try {
                returnTranPayment = em.getReference(ReturnTranPayment.class, id);
                returnTranPayment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The returnTranPayment with id " + id + " no longer exists.", enfe);
            }
            ReturnTranHead tranHeadId = returnTranPayment.getTranHeadId();
            if (tranHeadId != null) {
                tranHeadId.getReturnTranPaymentCollection().remove(returnTranPayment);
                tranHeadId = em.merge(tranHeadId);
            }
            em.remove(returnTranPayment);
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

    public List<ReturnTranPayment> findReturnTranPaymentEntities() {
        return findReturnTranPaymentEntities(true, -1, -1);
    }

    public List<ReturnTranPayment> findReturnTranPaymentEntities(int maxResults, int firstResult) {
        return findReturnTranPaymentEntities(false, maxResults, firstResult);
    }

    private List<ReturnTranPayment> findReturnTranPaymentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ReturnTranPayment as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ReturnTranPayment findReturnTranPayment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReturnTranPayment.class, id);
        } finally {
            em.close();
        }
    }

    public int getReturnTranPaymentCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from ReturnTranPayment as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
