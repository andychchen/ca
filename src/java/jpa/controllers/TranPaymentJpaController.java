/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.controllers;

import java.util.List;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.RollbackFailureException;
import jpa.entities.PaymentType;
import jpa.entities.TranHead;
import jpa.entities.TranPayment;

/**
 *
 * @author achen
 */
public class TranPaymentJpaController {

    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;
    private boolean isFromOutside = false;

    public void setUtx(UserTransaction utxIn) {
        isFromOutside = true;
        utx = utxIn;
    }

    public EntityManager getEntityManager() {
        if (emf != null) {
            return emf.createEntityManager();
        }
        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            return (EntityManager) ctx.lookup("persistence/LogicalName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create(TranPayment tranPayment) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PaymentType paymentType = tranPayment.getPaymentType();
            if (paymentType != null) {
                paymentType = em.getReference(paymentType.getClass(), paymentType.getId());
                tranPayment.setPaymentType(paymentType);
            }
            TranHead tranHeadId = tranPayment.getTranHeadId();
            if (tranHeadId != null) {
                tranHeadId = em.getReference(tranHeadId.getClass(), tranHeadId.getTranHeadId());
                tranPayment.setTranHeadId(tranHeadId);
            }
            em.persist(tranPayment);
            /*if (paymentType != null) {
                paymentType.getTranPaymentCollection().add(tranPayment);
                paymentType = em.merge(paymentType);
            }*/
            if (tranHeadId != null) {
                tranHeadId.getTranPaymentCollection().add(tranPayment);
                tranHeadId = em.merge(tranHeadId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public void edit(TranPayment tranPayment) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TranPayment persistentTranPayment = em.find(TranPayment.class, tranPayment.getId());
            PaymentType paymentTypeOld = persistentTranPayment.getPaymentType();
            PaymentType paymentTypeNew = tranPayment.getPaymentType();
            TranHead tranHeadIdOld = persistentTranPayment.getTranHeadId();
            TranHead tranHeadIdNew = tranPayment.getTranHeadId();
            if (paymentTypeNew != null) {
                paymentTypeNew = em.getReference(paymentTypeNew.getClass(), paymentTypeNew.getId());
                tranPayment.setPaymentType(paymentTypeNew);
            }
            if (tranHeadIdNew != null) {
                tranHeadIdNew = em.getReference(tranHeadIdNew.getClass(), tranHeadIdNew.getTranHeadId());
                tranPayment.setTranHeadId(tranHeadIdNew);
            }
            tranPayment = em.merge(tranPayment);
            if (paymentTypeOld != null && !paymentTypeOld.equals(paymentTypeNew)) {
                paymentTypeOld.getTranPaymentCollection().remove(tranPayment);
                paymentTypeOld = em.merge(paymentTypeOld);
            }
            if (paymentTypeNew != null && !paymentTypeNew.equals(paymentTypeOld)) {
                paymentTypeNew.getTranPaymentCollection().add(tranPayment);
                paymentTypeNew = em.merge(paymentTypeNew);
            }
            if (tranHeadIdOld != null && !tranHeadIdOld.equals(tranHeadIdNew)) {
                tranHeadIdOld.getTranPaymentCollection().remove(tranPayment);
                tranHeadIdOld = em.merge(tranHeadIdOld);
            }
            if (tranHeadIdNew != null && !tranHeadIdNew.equals(tranHeadIdOld)) {
                tranHeadIdNew.getTranPaymentCollection().add(tranPayment);
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
                Integer id = tranPayment.getId();
                if (findTranPayment(id) == null) {
                    throw new NonexistentEntityException("The tranPayment with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TranPayment tranPayment;
            try {
                tranPayment = em.getReference(TranPayment.class, id);
                tranPayment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tranPayment with id " + id + " no longer exists.", enfe);
            }
            PaymentType paymentType = tranPayment.getPaymentType();
            if (paymentType != null) {
                paymentType.getTranPaymentCollection().remove(tranPayment);
                paymentType = em.merge(paymentType);
            }
            TranHead tranHeadId = tranPayment.getTranHeadId();
            if (tranHeadId != null) {
                tranHeadId.getTranPaymentCollection().remove(tranPayment);
                tranHeadId = em.merge(tranHeadId);
            }
            em.remove(tranPayment);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public List<TranPayment> findTranPaymentEntities() {
        return findTranPaymentEntities(true, -1, -1);
    }

    public List<TranPayment> findTranPaymentEntities(int maxResults, int firstResult) {
        return findTranPaymentEntities(false, maxResults, firstResult);
    }

    private List<TranPayment> findTranPaymentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TranPayment as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }

    public TranPayment findTranPayment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TranPayment.class, id);
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }

    public int getTranPaymentCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TranPayment as o").getSingleResult()).intValue();
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }
}
