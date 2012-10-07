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
import jpa.entities.Discount;
import jpa.entities.ReturnTranHead;
import jpa.entities.TranHead;
import jpa.entities.YesNo;
import jpa.entities.ReturnTranDetail;
import java.util.ArrayList;
import java.util.Collection;
import jpa.entities.ReturnTranPayment;

/**
 *
 * @author achen
 */
public class ReturnTranHeadJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReturnTranHead returnTranHead) throws RollbackFailureException, Exception {
        if (returnTranHead.getReturnTranDetailCollection() == null) {
            returnTranHead.setReturnTranDetailCollection(new ArrayList<ReturnTranDetail>());
        }
        if (returnTranHead.getReturnTranPaymentCollection() == null) {
            returnTranHead.setReturnTranPaymentCollection(new ArrayList<ReturnTranPayment>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Discount headManagerDiscount = returnTranHead.getHeadManagerDiscount();
            if (headManagerDiscount != null) {
                headManagerDiscount = em.getReference(headManagerDiscount.getClass(), headManagerDiscount.getDiscountId());
                returnTranHead.setHeadManagerDiscount(headManagerDiscount);
            }
            TranHead originalTranHeadId = returnTranHead.getOriginalTranHeadId();
            if (originalTranHeadId != null) {
                originalTranHeadId = em.getReference(originalTranHeadId.getClass(), originalTranHeadId.getTranHeadId());
                returnTranHead.setOriginalTranHeadId(originalTranHeadId);
            }
            YesNo isTrainingMode = returnTranHead.getIsTrainingMode();
            if (isTrainingMode != null) {
                isTrainingMode = em.getReference(isTrainingMode.getClass(), isTrainingMode.getName());
                returnTranHead.setIsTrainingMode(isTrainingMode);
            }
            Collection<ReturnTranDetail> attachedReturnTranDetailCollection = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetailToAttach : returnTranHead.getReturnTranDetailCollection()) {
                returnTranDetailCollectionReturnTranDetailToAttach = em.getReference(returnTranDetailCollectionReturnTranDetailToAttach.getClass(), returnTranDetailCollectionReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollection.add(returnTranDetailCollectionReturnTranDetailToAttach);
            }
            returnTranHead.setReturnTranDetailCollection(attachedReturnTranDetailCollection);
            Collection<ReturnTranPayment> attachedReturnTranPaymentCollection = new ArrayList<ReturnTranPayment>();
            for (ReturnTranPayment returnTranPaymentCollectionReturnTranPaymentToAttach : returnTranHead.getReturnTranPaymentCollection()) {
                returnTranPaymentCollectionReturnTranPaymentToAttach = em.getReference(returnTranPaymentCollectionReturnTranPaymentToAttach.getClass(), returnTranPaymentCollectionReturnTranPaymentToAttach.getId());
                attachedReturnTranPaymentCollection.add(returnTranPaymentCollectionReturnTranPaymentToAttach);
            }
            returnTranHead.setReturnTranPaymentCollection(attachedReturnTranPaymentCollection);
            em.persist(returnTranHead);
            /*if (headManagerDiscount != null) {
                headManagerDiscount.getReturnTranHeadCollection().add(returnTranHead);
                headManagerDiscount = em.merge(headManagerDiscount);
            }
            if (originalTranHeadId != null) {
                originalTranHeadId.getReturnTranHeadCollection().add(returnTranHead);
                originalTranHeadId = em.merge(originalTranHeadId);
            }
            if (isTrainingMode != null) {
                isTrainingMode.getReturnTranHeadCollection().add(returnTranHead);
                isTrainingMode = em.merge(isTrainingMode);
            }
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetail : returnTranHead.getReturnTranDetailCollection()) {
                ReturnTranHead oldTranHeadIdOfReturnTranDetailCollectionReturnTranDetail = returnTranDetailCollectionReturnTranDetail.getTranHeadId();
                returnTranDetailCollectionReturnTranDetail.setTranHeadId(returnTranHead);
                returnTranDetailCollectionReturnTranDetail = em.merge(returnTranDetailCollectionReturnTranDetail);
                if (oldTranHeadIdOfReturnTranDetailCollectionReturnTranDetail != null) {
                    oldTranHeadIdOfReturnTranDetailCollectionReturnTranDetail.getReturnTranDetailCollection().remove(returnTranDetailCollectionReturnTranDetail);
                    oldTranHeadIdOfReturnTranDetailCollectionReturnTranDetail = em.merge(oldTranHeadIdOfReturnTranDetailCollectionReturnTranDetail);
                }
            }
            for (ReturnTranPayment returnTranPaymentCollectionReturnTranPayment : returnTranHead.getReturnTranPaymentCollection()) {
                ReturnTranHead oldTranHeadIdOfReturnTranPaymentCollectionReturnTranPayment = returnTranPaymentCollectionReturnTranPayment.getTranHeadId();
                returnTranPaymentCollectionReturnTranPayment.setTranHeadId(returnTranHead);
                returnTranPaymentCollectionReturnTranPayment = em.merge(returnTranPaymentCollectionReturnTranPayment);
                if (oldTranHeadIdOfReturnTranPaymentCollectionReturnTranPayment != null) {
                    oldTranHeadIdOfReturnTranPaymentCollectionReturnTranPayment.getReturnTranPaymentCollection().remove(returnTranPaymentCollectionReturnTranPayment);
                    oldTranHeadIdOfReturnTranPaymentCollectionReturnTranPayment = em.merge(oldTranHeadIdOfReturnTranPaymentCollectionReturnTranPayment);
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

    public void edit(ReturnTranHead returnTranHead) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ReturnTranHead persistentReturnTranHead = em.find(ReturnTranHead.class, returnTranHead.getTranHeadId());
            Discount headManagerDiscountOld = persistentReturnTranHead.getHeadManagerDiscount();
            Discount headManagerDiscountNew = returnTranHead.getHeadManagerDiscount();
            TranHead originalTranHeadIdOld = persistentReturnTranHead.getOriginalTranHeadId();
            TranHead originalTranHeadIdNew = returnTranHead.getOriginalTranHeadId();
            YesNo isTrainingModeOld = persistentReturnTranHead.getIsTrainingMode();
            YesNo isTrainingModeNew = returnTranHead.getIsTrainingMode();
            Collection<ReturnTranDetail> returnTranDetailCollectionOld = persistentReturnTranHead.getReturnTranDetailCollection();
            Collection<ReturnTranDetail> returnTranDetailCollectionNew = returnTranHead.getReturnTranDetailCollection();
            Collection<ReturnTranPayment> returnTranPaymentCollectionOld = persistentReturnTranHead.getReturnTranPaymentCollection();
            Collection<ReturnTranPayment> returnTranPaymentCollectionNew = returnTranHead.getReturnTranPaymentCollection();
            List<String> illegalOrphanMessages = null;
            for (ReturnTranDetail returnTranDetailCollectionOldReturnTranDetail : returnTranDetailCollectionOld) {
                if (!returnTranDetailCollectionNew.contains(returnTranDetailCollectionOldReturnTranDetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReturnTranDetail " + returnTranDetailCollectionOldReturnTranDetail + " since its tranHeadId field is not nullable.");
                }
            }
            for (ReturnTranPayment returnTranPaymentCollectionOldReturnTranPayment : returnTranPaymentCollectionOld) {
                if (!returnTranPaymentCollectionNew.contains(returnTranPaymentCollectionOldReturnTranPayment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReturnTranPayment " + returnTranPaymentCollectionOldReturnTranPayment + " since its tranHeadId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (headManagerDiscountNew != null) {
                headManagerDiscountNew = em.getReference(headManagerDiscountNew.getClass(), headManagerDiscountNew.getDiscountId());
                returnTranHead.setHeadManagerDiscount(headManagerDiscountNew);
            }
            if (originalTranHeadIdNew != null) {
                originalTranHeadIdNew = em.getReference(originalTranHeadIdNew.getClass(), originalTranHeadIdNew.getTranHeadId());
                returnTranHead.setOriginalTranHeadId(originalTranHeadIdNew);
            }
            if (isTrainingModeNew != null) {
                isTrainingModeNew = em.getReference(isTrainingModeNew.getClass(), isTrainingModeNew.getName());
                returnTranHead.setIsTrainingMode(isTrainingModeNew);
            }
            Collection<ReturnTranDetail> attachedReturnTranDetailCollectionNew = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollectionNewReturnTranDetailToAttach : returnTranDetailCollectionNew) {
                returnTranDetailCollectionNewReturnTranDetailToAttach = em.getReference(returnTranDetailCollectionNewReturnTranDetailToAttach.getClass(), returnTranDetailCollectionNewReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollectionNew.add(returnTranDetailCollectionNewReturnTranDetailToAttach);
            }
            returnTranDetailCollectionNew = attachedReturnTranDetailCollectionNew;
            returnTranHead.setReturnTranDetailCollection(returnTranDetailCollectionNew);
            Collection<ReturnTranPayment> attachedReturnTranPaymentCollectionNew = new ArrayList<ReturnTranPayment>();
            for (ReturnTranPayment returnTranPaymentCollectionNewReturnTranPaymentToAttach : returnTranPaymentCollectionNew) {
                returnTranPaymentCollectionNewReturnTranPaymentToAttach = em.getReference(returnTranPaymentCollectionNewReturnTranPaymentToAttach.getClass(), returnTranPaymentCollectionNewReturnTranPaymentToAttach.getId());
                attachedReturnTranPaymentCollectionNew.add(returnTranPaymentCollectionNewReturnTranPaymentToAttach);
            }
            returnTranPaymentCollectionNew = attachedReturnTranPaymentCollectionNew;
            returnTranHead.setReturnTranPaymentCollection(returnTranPaymentCollectionNew);
            returnTranHead = em.merge(returnTranHead);
            if (headManagerDiscountOld != null && !headManagerDiscountOld.equals(headManagerDiscountNew)) {
                headManagerDiscountOld.getReturnTranHeadCollection().remove(returnTranHead);
                headManagerDiscountOld = em.merge(headManagerDiscountOld);
            }
            if (headManagerDiscountNew != null && !headManagerDiscountNew.equals(headManagerDiscountOld)) {
                headManagerDiscountNew.getReturnTranHeadCollection().add(returnTranHead);
                headManagerDiscountNew = em.merge(headManagerDiscountNew);
            }
            if (originalTranHeadIdOld != null && !originalTranHeadIdOld.equals(originalTranHeadIdNew)) {
                originalTranHeadIdOld.getReturnTranHeadCollection().remove(returnTranHead);
                originalTranHeadIdOld = em.merge(originalTranHeadIdOld);
            }
            if (originalTranHeadIdNew != null && !originalTranHeadIdNew.equals(originalTranHeadIdOld)) {
                originalTranHeadIdNew.getReturnTranHeadCollection().add(returnTranHead);
                originalTranHeadIdNew = em.merge(originalTranHeadIdNew);
            }
            if (isTrainingModeOld != null && !isTrainingModeOld.equals(isTrainingModeNew)) {
                isTrainingModeOld.getReturnTranHeadCollection().remove(returnTranHead);
                isTrainingModeOld = em.merge(isTrainingModeOld);
            }
            if (isTrainingModeNew != null && !isTrainingModeNew.equals(isTrainingModeOld)) {
                isTrainingModeNew.getReturnTranHeadCollection().add(returnTranHead);
                isTrainingModeNew = em.merge(isTrainingModeNew);
            }
            for (ReturnTranDetail returnTranDetailCollectionNewReturnTranDetail : returnTranDetailCollectionNew) {
                if (!returnTranDetailCollectionOld.contains(returnTranDetailCollectionNewReturnTranDetail)) {
                    ReturnTranHead oldTranHeadIdOfReturnTranDetailCollectionNewReturnTranDetail = returnTranDetailCollectionNewReturnTranDetail.getTranHeadId();
                    returnTranDetailCollectionNewReturnTranDetail.setTranHeadId(returnTranHead);
                    returnTranDetailCollectionNewReturnTranDetail = em.merge(returnTranDetailCollectionNewReturnTranDetail);
                    if (oldTranHeadIdOfReturnTranDetailCollectionNewReturnTranDetail != null && !oldTranHeadIdOfReturnTranDetailCollectionNewReturnTranDetail.equals(returnTranHead)) {
                        oldTranHeadIdOfReturnTranDetailCollectionNewReturnTranDetail.getReturnTranDetailCollection().remove(returnTranDetailCollectionNewReturnTranDetail);
                        oldTranHeadIdOfReturnTranDetailCollectionNewReturnTranDetail = em.merge(oldTranHeadIdOfReturnTranDetailCollectionNewReturnTranDetail);
                    }
                }
            }
            for (ReturnTranPayment returnTranPaymentCollectionNewReturnTranPayment : returnTranPaymentCollectionNew) {
                if (!returnTranPaymentCollectionOld.contains(returnTranPaymentCollectionNewReturnTranPayment)) {
                    ReturnTranHead oldTranHeadIdOfReturnTranPaymentCollectionNewReturnTranPayment = returnTranPaymentCollectionNewReturnTranPayment.getTranHeadId();
                    returnTranPaymentCollectionNewReturnTranPayment.setTranHeadId(returnTranHead);
                    returnTranPaymentCollectionNewReturnTranPayment = em.merge(returnTranPaymentCollectionNewReturnTranPayment);
                    if (oldTranHeadIdOfReturnTranPaymentCollectionNewReturnTranPayment != null && !oldTranHeadIdOfReturnTranPaymentCollectionNewReturnTranPayment.equals(returnTranHead)) {
                        oldTranHeadIdOfReturnTranPaymentCollectionNewReturnTranPayment.getReturnTranPaymentCollection().remove(returnTranPaymentCollectionNewReturnTranPayment);
                        oldTranHeadIdOfReturnTranPaymentCollectionNewReturnTranPayment = em.merge(oldTranHeadIdOfReturnTranPaymentCollectionNewReturnTranPayment);
                    }
                }
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
                Integer id = returnTranHead.getTranHeadId();
                if (findReturnTranHead(id) == null) {
                    throw new NonexistentEntityException("The returnTranHead with id " + id + " no longer exists.");
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
            ReturnTranHead returnTranHead;
            try {
                returnTranHead = em.getReference(ReturnTranHead.class, id);
                returnTranHead.getTranHeadId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The returnTranHead with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ReturnTranDetail> returnTranDetailCollectionOrphanCheck = returnTranHead.getReturnTranDetailCollection();
            for (ReturnTranDetail returnTranDetailCollectionOrphanCheckReturnTranDetail : returnTranDetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ReturnTranHead (" + returnTranHead + ") cannot be destroyed since the ReturnTranDetail " + returnTranDetailCollectionOrphanCheckReturnTranDetail + " in its returnTranDetailCollection field has a non-nullable tranHeadId field.");
            }
            Collection<ReturnTranPayment> returnTranPaymentCollectionOrphanCheck = returnTranHead.getReturnTranPaymentCollection();
            for (ReturnTranPayment returnTranPaymentCollectionOrphanCheckReturnTranPayment : returnTranPaymentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ReturnTranHead (" + returnTranHead + ") cannot be destroyed since the ReturnTranPayment " + returnTranPaymentCollectionOrphanCheckReturnTranPayment + " in its returnTranPaymentCollection field has a non-nullable tranHeadId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Discount headManagerDiscount = returnTranHead.getHeadManagerDiscount();
            if (headManagerDiscount != null) {
                headManagerDiscount.getReturnTranHeadCollection().remove(returnTranHead);
                headManagerDiscount = em.merge(headManagerDiscount);
            }
            TranHead originalTranHeadId = returnTranHead.getOriginalTranHeadId();
            if (originalTranHeadId != null) {
                originalTranHeadId.getReturnTranHeadCollection().remove(returnTranHead);
                originalTranHeadId = em.merge(originalTranHeadId);
            }
            YesNo isTrainingMode = returnTranHead.getIsTrainingMode();
            if (isTrainingMode != null) {
                isTrainingMode.getReturnTranHeadCollection().remove(returnTranHead);
                isTrainingMode = em.merge(isTrainingMode);
            }
            em.remove(returnTranHead);
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

    public List<ReturnTranHead> findReturnTranHeadEntities() {
        return findReturnTranHeadEntities(true, -1, -1);
    }

    public List<ReturnTranHead> findReturnTranHeadEntities(int maxResults, int firstResult) {
        return findReturnTranHeadEntities(false, maxResults, firstResult);
    }

    private List<ReturnTranHead> findReturnTranHeadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ReturnTranHead as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ReturnTranHead findReturnTranHead(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReturnTranHead.class, id);
        } finally {
            em.close();
        }
    }

    public int getReturnTranHeadCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from ReturnTranHead as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
