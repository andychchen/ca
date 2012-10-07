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
import jpa.entities.EmpUsers;
import jpa.entities.TranHead;
import jpa.entities.YesNo;
import jpa.entities.TranDetail;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.Context;
import javax.naming.InitialContext;
import jpa.entities.TranPayment;
import jpa.entities.ReturnTranHead;

/**
 *
 * @author achen
 */
public class TranHeadJpaController {

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

    public void create(TranHead tranHead) throws RollbackFailureException, Exception {
        if (tranHead.getTranDetailCollection() == null) {
            tranHead.setTranDetailCollection(new ArrayList<TranDetail>());
        }
        if (tranHead.getTranPaymentCollection() == null) {
            tranHead.setTranPaymentCollection(new ArrayList<TranPayment>());
        }
        if (tranHead.getTranHeadCollection() == null) {
            tranHead.setTranHeadCollection(new ArrayList<TranHead>());
        }
        if (tranHead.getReturnTranHeadCollection() == null) {
            tranHead.setReturnTranHeadCollection(new ArrayList<ReturnTranHead>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Discount headManagerDiscount = tranHead.getHeadManagerDiscount();
            if (headManagerDiscount != null) {
                headManagerDiscount = em.getReference(headManagerDiscount.getClass(), headManagerDiscount.getDiscountId());
                tranHead.setHeadManagerDiscount(headManagerDiscount);
            }
            EmpUsers userId = tranHead.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getId());
                tranHead.setUserId(userId);
            }
            TranHead originalHeadId = tranHead.getOriginalHeadId();
            if (originalHeadId != null) {
                originalHeadId = em.getReference(originalHeadId.getClass(), originalHeadId.getTranHeadId());
                tranHead.setOriginalHeadId(originalHeadId);
            }
            YesNo isForReturned = tranHead.getIsForReturned();
            if (isForReturned != null) {
                isForReturned = em.getReference(isForReturned.getClass(), isForReturned.getName());
                tranHead.setIsForReturned(isForReturned);
            }
            YesNo isTrainingMode = tranHead.getIsTrainingMode();
            if (isTrainingMode != null) {
                isTrainingMode = em.getReference(isTrainingMode.getClass(), isTrainingMode.getName());
                tranHead.setIsTrainingMode(isTrainingMode);
            }
            Collection<TranDetail> attachedTranDetailCollection = new ArrayList<TranDetail>();
            for (TranDetail tranDetailCollectionTranDetailToAttach : tranHead.getTranDetailCollection()) {
                tranDetailCollectionTranDetailToAttach = em.getReference(tranDetailCollectionTranDetailToAttach.getClass(), tranDetailCollectionTranDetailToAttach.getTranDetailId());
                attachedTranDetailCollection.add(tranDetailCollectionTranDetailToAttach);
            }
            tranHead.setTranDetailCollection(attachedTranDetailCollection);
            Collection<TranPayment> attachedTranPaymentCollection = new ArrayList<TranPayment>();
            for (TranPayment tranPaymentCollectionTranPaymentToAttach : tranHead.getTranPaymentCollection()) {
                tranPaymentCollectionTranPaymentToAttach = em.getReference(tranPaymentCollectionTranPaymentToAttach.getClass(), tranPaymentCollectionTranPaymentToAttach.getId());
                attachedTranPaymentCollection.add(tranPaymentCollectionTranPaymentToAttach);
            }
            tranHead.setTranPaymentCollection(attachedTranPaymentCollection);
            Collection<TranHead> attachedTranHeadCollection = new ArrayList<TranHead>();
            for (TranHead tranHeadCollectionTranHeadToAttach : tranHead.getTranHeadCollection()) {
                tranHeadCollectionTranHeadToAttach = em.getReference(tranHeadCollectionTranHeadToAttach.getClass(), tranHeadCollectionTranHeadToAttach.getTranHeadId());
                attachedTranHeadCollection.add(tranHeadCollectionTranHeadToAttach);
            }
            tranHead.setTranHeadCollection(attachedTranHeadCollection);
            Collection<ReturnTranHead> attachedReturnTranHeadCollection = new ArrayList<ReturnTranHead>();
            for (ReturnTranHead returnTranHeadCollectionReturnTranHeadToAttach : tranHead.getReturnTranHeadCollection()) {
                returnTranHeadCollectionReturnTranHeadToAttach = em.getReference(returnTranHeadCollectionReturnTranHeadToAttach.getClass(), returnTranHeadCollectionReturnTranHeadToAttach.getTranHeadId());
                attachedReturnTranHeadCollection.add(returnTranHeadCollectionReturnTranHeadToAttach);
            }
            tranHead.setReturnTranHeadCollection(attachedReturnTranHeadCollection);
            em.persist(tranHead);
            /*if (headManagerDiscount != null) {
                headManagerDiscount.getTranHeadCollection().add(tranHead);
                headManagerDiscount = em.merge(headManagerDiscount);
            }
            if (userId != null) {
                userId.getTranHeadCollection().add(tranHead);
                userId = em.merge(userId);
            }*/
            if (originalHeadId != null) {
                originalHeadId.getTranHeadCollection().add(tranHead);
                originalHeadId = em.merge(originalHeadId);
            }
            /*if (isForReturned != null) {
                isForReturned.getTranHeadCollection().add(tranHead);
                isForReturned = em.merge(isForReturned);
            }
            if (isTrainingMode != null) {
                isTrainingMode.getTranHeadCollection().add(tranHead);
                isTrainingMode = em.merge(isTrainingMode);
            }
            for (TranDetail tranDetailCollectionTranDetail : tranHead.getTranDetailCollection()) {
                TranHead oldTranHeadIdOfTranDetailCollectionTranDetail = tranDetailCollectionTranDetail.getTranHeadId();
                tranDetailCollectionTranDetail.setTranHeadId(tranHead);
                tranDetailCollectionTranDetail = em.merge(tranDetailCollectionTranDetail);
                if (oldTranHeadIdOfTranDetailCollectionTranDetail != null) {
                    oldTranHeadIdOfTranDetailCollectionTranDetail.getTranDetailCollection().remove(tranDetailCollectionTranDetail);
                    oldTranHeadIdOfTranDetailCollectionTranDetail = em.merge(oldTranHeadIdOfTranDetailCollectionTranDetail);
                }
            }
            for (TranPayment tranPaymentCollectionTranPayment : tranHead.getTranPaymentCollection()) {
                TranHead oldTranHeadIdOfTranPaymentCollectionTranPayment = tranPaymentCollectionTranPayment.getTranHeadId();
                tranPaymentCollectionTranPayment.setTranHeadId(tranHead);
                tranPaymentCollectionTranPayment = em.merge(tranPaymentCollectionTranPayment);
                if (oldTranHeadIdOfTranPaymentCollectionTranPayment != null) {
                    oldTranHeadIdOfTranPaymentCollectionTranPayment.getTranPaymentCollection().remove(tranPaymentCollectionTranPayment);
                    oldTranHeadIdOfTranPaymentCollectionTranPayment = em.merge(oldTranHeadIdOfTranPaymentCollectionTranPayment);
                }
            }*/
            /*for (TranHead tranHeadCollectionTranHead : tranHead.getTranHeadCollection()) {
                TranHead oldOriginalHeadIdOfTranHeadCollectionTranHead = tranHeadCollectionTranHead.getOriginalHeadId();
                tranHeadCollectionTranHead.setOriginalHeadId(tranHead);
                tranHeadCollectionTranHead = em.merge(tranHeadCollectionTranHead);
                if (oldOriginalHeadIdOfTranHeadCollectionTranHead != null) {
                    oldOriginalHeadIdOfTranHeadCollectionTranHead.getTranHeadCollection().remove(tranHeadCollectionTranHead);
                    oldOriginalHeadIdOfTranHeadCollectionTranHead = em.merge(oldOriginalHeadIdOfTranHeadCollectionTranHead);
                }
            }*/
            /*for (ReturnTranHead returnTranHeadCollectionReturnTranHead : tranHead.getReturnTranHeadCollection()) {
                TranHead oldOriginalTranHeadIdOfReturnTranHeadCollectionReturnTranHead = returnTranHeadCollectionReturnTranHead.getOriginalTranHeadId();
                returnTranHeadCollectionReturnTranHead.setOriginalTranHeadId(tranHead);
                returnTranHeadCollectionReturnTranHead = em.merge(returnTranHeadCollectionReturnTranHead);
                if (oldOriginalTranHeadIdOfReturnTranHeadCollectionReturnTranHead != null) {
                    oldOriginalTranHeadIdOfReturnTranHeadCollectionReturnTranHead.getReturnTranHeadCollection().remove(returnTranHeadCollectionReturnTranHead);
                    oldOriginalTranHeadIdOfReturnTranHeadCollectionReturnTranHead = em.merge(oldOriginalTranHeadIdOfReturnTranHeadCollectionReturnTranHead);
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
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public void edit(TranHead tranHead) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TranHead persistentTranHead = em.find(TranHead.class, tranHead.getTranHeadId());
            Discount headManagerDiscountOld = persistentTranHead.getHeadManagerDiscount();
            Discount headManagerDiscountNew = tranHead.getHeadManagerDiscount();
            EmpUsers userIdOld = persistentTranHead.getUserId();
            EmpUsers userIdNew = tranHead.getUserId();
            TranHead originalHeadIdOld = persistentTranHead.getOriginalHeadId();
            TranHead originalHeadIdNew = tranHead.getOriginalHeadId();
            YesNo isForReturnedOld = persistentTranHead.getIsForReturned();
            YesNo isForReturnedNew = tranHead.getIsForReturned();
            YesNo isTrainingModeOld = persistentTranHead.getIsTrainingMode();
            YesNo isTrainingModeNew = tranHead.getIsTrainingMode();
            Collection<TranDetail> tranDetailCollectionOld = persistentTranHead.getTranDetailCollection();
            Collection<TranDetail> tranDetailCollectionNew = tranHead.getTranDetailCollection();
            Collection<TranPayment> tranPaymentCollectionOld = persistentTranHead.getTranPaymentCollection();
            Collection<TranPayment> tranPaymentCollectionNew = tranHead.getTranPaymentCollection();
            Collection<TranHead> tranHeadCollectionOld = persistentTranHead.getTranHeadCollection();
            Collection<TranHead> tranHeadCollectionNew = tranHead.getTranHeadCollection();
            Collection<ReturnTranHead> returnTranHeadCollectionOld = persistentTranHead.getReturnTranHeadCollection();
            Collection<ReturnTranHead> returnTranHeadCollectionNew = tranHead.getReturnTranHeadCollection();
            List<String> illegalOrphanMessages = null;
            for (TranDetail tranDetailCollectionOldTranDetail : tranDetailCollectionOld) {
                if (!tranDetailCollectionNew.contains(tranDetailCollectionOldTranDetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TranDetail " + tranDetailCollectionOldTranDetail + " since its tranHeadId field is not nullable.");
                }
            }
            for (TranPayment tranPaymentCollectionOldTranPayment : tranPaymentCollectionOld) {
                if (!tranPaymentCollectionNew.contains(tranPaymentCollectionOldTranPayment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TranPayment " + tranPaymentCollectionOldTranPayment + " since its tranHeadId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (headManagerDiscountNew != null) {
                headManagerDiscountNew = em.getReference(headManagerDiscountNew.getClass(), headManagerDiscountNew.getDiscountId());
                tranHead.setHeadManagerDiscount(headManagerDiscountNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                tranHead.setUserId(userIdNew);
            }
            if (originalHeadIdNew != null) {
                originalHeadIdNew = em.getReference(originalHeadIdNew.getClass(), originalHeadIdNew.getTranHeadId());
                tranHead.setOriginalHeadId(originalHeadIdNew);
            }
            if (isForReturnedNew != null) {
                isForReturnedNew = em.getReference(isForReturnedNew.getClass(), isForReturnedNew.getName());
                tranHead.setIsForReturned(isForReturnedNew);
            }
            if (isTrainingModeNew != null) {
                isTrainingModeNew = em.getReference(isTrainingModeNew.getClass(), isTrainingModeNew.getName());
                tranHead.setIsTrainingMode(isTrainingModeNew);
            }
            Collection<TranDetail> attachedTranDetailCollectionNew = new ArrayList<TranDetail>();
            for (TranDetail tranDetailCollectionNewTranDetailToAttach : tranDetailCollectionNew) {
                tranDetailCollectionNewTranDetailToAttach = em.getReference(tranDetailCollectionNewTranDetailToAttach.getClass(), tranDetailCollectionNewTranDetailToAttach.getTranDetailId());
                attachedTranDetailCollectionNew.add(tranDetailCollectionNewTranDetailToAttach);
            }
            tranDetailCollectionNew = attachedTranDetailCollectionNew;
            tranHead.setTranDetailCollection(tranDetailCollectionNew);
            Collection<TranPayment> attachedTranPaymentCollectionNew = new ArrayList<TranPayment>();
            for (TranPayment tranPaymentCollectionNewTranPaymentToAttach : tranPaymentCollectionNew) {
                tranPaymentCollectionNewTranPaymentToAttach = em.getReference(tranPaymentCollectionNewTranPaymentToAttach.getClass(), tranPaymentCollectionNewTranPaymentToAttach.getId());
                attachedTranPaymentCollectionNew.add(tranPaymentCollectionNewTranPaymentToAttach);
            }
            tranPaymentCollectionNew = attachedTranPaymentCollectionNew;
            tranHead.setTranPaymentCollection(tranPaymentCollectionNew);
            Collection<TranHead> attachedTranHeadCollectionNew = new ArrayList<TranHead>();
            for (TranHead tranHeadCollectionNewTranHeadToAttach : tranHeadCollectionNew) {
                tranHeadCollectionNewTranHeadToAttach = em.getReference(tranHeadCollectionNewTranHeadToAttach.getClass(), tranHeadCollectionNewTranHeadToAttach.getTranHeadId());
                attachedTranHeadCollectionNew.add(tranHeadCollectionNewTranHeadToAttach);
            }
            tranHeadCollectionNew = attachedTranHeadCollectionNew;
            tranHead.setTranHeadCollection(tranHeadCollectionNew);
            Collection<ReturnTranHead> attachedReturnTranHeadCollectionNew = new ArrayList<ReturnTranHead>();
            for (ReturnTranHead returnTranHeadCollectionNewReturnTranHeadToAttach : returnTranHeadCollectionNew) {
                returnTranHeadCollectionNewReturnTranHeadToAttach = em.getReference(returnTranHeadCollectionNewReturnTranHeadToAttach.getClass(), returnTranHeadCollectionNewReturnTranHeadToAttach.getTranHeadId());
                attachedReturnTranHeadCollectionNew.add(returnTranHeadCollectionNewReturnTranHeadToAttach);
            }
            returnTranHeadCollectionNew = attachedReturnTranHeadCollectionNew;
            tranHead.setReturnTranHeadCollection(returnTranHeadCollectionNew);
            tranHead = em.merge(tranHead);
            if (headManagerDiscountOld != null && !headManagerDiscountOld.equals(headManagerDiscountNew)) {
                headManagerDiscountOld.getTranHeadCollection().remove(tranHead);
                headManagerDiscountOld = em.merge(headManagerDiscountOld);
            }
            if (headManagerDiscountNew != null && !headManagerDiscountNew.equals(headManagerDiscountOld)) {
                headManagerDiscountNew.getTranHeadCollection().add(tranHead);
                headManagerDiscountNew = em.merge(headManagerDiscountNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getTranHeadCollection().remove(tranHead);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getTranHeadCollection().add(tranHead);
                userIdNew = em.merge(userIdNew);
            }
            if (originalHeadIdOld != null && !originalHeadIdOld.equals(originalHeadIdNew)) {
                originalHeadIdOld.getTranHeadCollection().remove(tranHead);
                originalHeadIdOld = em.merge(originalHeadIdOld);
            }
            if (originalHeadIdNew != null && !originalHeadIdNew.equals(originalHeadIdOld)) {
                originalHeadIdNew.getTranHeadCollection().add(tranHead);
                originalHeadIdNew = em.merge(originalHeadIdNew);
            }
            if (isForReturnedOld != null && !isForReturnedOld.equals(isForReturnedNew)) {
                isForReturnedOld.getTranHeadCollection().remove(tranHead);
                isForReturnedOld = em.merge(isForReturnedOld);
            }
            if (isForReturnedNew != null && !isForReturnedNew.equals(isForReturnedOld)) {
                isForReturnedNew.getTranHeadCollection().add(tranHead);
                isForReturnedNew = em.merge(isForReturnedNew);
            }
            if (isTrainingModeOld != null && !isTrainingModeOld.equals(isTrainingModeNew)) {
                isTrainingModeOld.getTranHeadCollection().remove(tranHead);
                isTrainingModeOld = em.merge(isTrainingModeOld);
            }
            if (isTrainingModeNew != null && !isTrainingModeNew.equals(isTrainingModeOld)) {
                isTrainingModeNew.getTranHeadCollection().add(tranHead);
                isTrainingModeNew = em.merge(isTrainingModeNew);
            }
            /*for (TranDetail tranDetailCollectionNewTranDetail : tranDetailCollectionNew) {
                if (!tranDetailCollectionOld.contains(tranDetailCollectionNewTranDetail)) {
                    TranHead oldTranHeadIdOfTranDetailCollectionNewTranDetail = tranDetailCollectionNewTranDetail.getTranHeadId();
                    tranDetailCollectionNewTranDetail.setTranHeadId(tranHead);
                    tranDetailCollectionNewTranDetail = em.merge(tranDetailCollectionNewTranDetail);
                    if (oldTranHeadIdOfTranDetailCollectionNewTranDetail != null && !oldTranHeadIdOfTranDetailCollectionNewTranDetail.equals(tranHead)) {
                        oldTranHeadIdOfTranDetailCollectionNewTranDetail.getTranDetailCollection().remove(tranDetailCollectionNewTranDetail);
                        oldTranHeadIdOfTranDetailCollectionNewTranDetail = em.merge(oldTranHeadIdOfTranDetailCollectionNewTranDetail);
                    }
                }
            }
            for (TranPayment tranPaymentCollectionNewTranPayment : tranPaymentCollectionNew) {
                if (!tranPaymentCollectionOld.contains(tranPaymentCollectionNewTranPayment)) {
                    TranHead oldTranHeadIdOfTranPaymentCollectionNewTranPayment = tranPaymentCollectionNewTranPayment.getTranHeadId();
                    tranPaymentCollectionNewTranPayment.setTranHeadId(tranHead);
                    tranPaymentCollectionNewTranPayment = em.merge(tranPaymentCollectionNewTranPayment);
                    if (oldTranHeadIdOfTranPaymentCollectionNewTranPayment != null && !oldTranHeadIdOfTranPaymentCollectionNewTranPayment.equals(tranHead)) {
                        oldTranHeadIdOfTranPaymentCollectionNewTranPayment.getTranPaymentCollection().remove(tranPaymentCollectionNewTranPayment);
                        oldTranHeadIdOfTranPaymentCollectionNewTranPayment = em.merge(oldTranHeadIdOfTranPaymentCollectionNewTranPayment);
                    }
                }
            }*/
            for (TranHead tranHeadCollectionOldTranHead : tranHeadCollectionOld) {
                if (!tranHeadCollectionNew.contains(tranHeadCollectionOldTranHead)) {
                    tranHeadCollectionOldTranHead.setOriginalHeadId(null);
                    tranHeadCollectionOldTranHead = em.merge(tranHeadCollectionOldTranHead);
                }
            }
            for (TranHead tranHeadCollectionNewTranHead : tranHeadCollectionNew) {
                if (!tranHeadCollectionOld.contains(tranHeadCollectionNewTranHead)) {
                    TranHead oldOriginalHeadIdOfTranHeadCollectionNewTranHead = tranHeadCollectionNewTranHead.getOriginalHeadId();
                    tranHeadCollectionNewTranHead.setOriginalHeadId(tranHead);
                    tranHeadCollectionNewTranHead = em.merge(tranHeadCollectionNewTranHead);
                    if (oldOriginalHeadIdOfTranHeadCollectionNewTranHead != null && !oldOriginalHeadIdOfTranHeadCollectionNewTranHead.equals(tranHead)) {
                        oldOriginalHeadIdOfTranHeadCollectionNewTranHead.getTranHeadCollection().remove(tranHeadCollectionNewTranHead);
                        oldOriginalHeadIdOfTranHeadCollectionNewTranHead = em.merge(oldOriginalHeadIdOfTranHeadCollectionNewTranHead);
                    }
                }
            }
            /*for (ReturnTranHead returnTranHeadCollectionOldReturnTranHead : returnTranHeadCollectionOld) {
                if (!returnTranHeadCollectionNew.contains(returnTranHeadCollectionOldReturnTranHead)) {
                    returnTranHeadCollectionOldReturnTranHead.setOriginalTranHeadId(null);
                    returnTranHeadCollectionOldReturnTranHead = em.merge(returnTranHeadCollectionOldReturnTranHead);
                }
            }
            for (ReturnTranHead returnTranHeadCollectionNewReturnTranHead : returnTranHeadCollectionNew) {
                if (!returnTranHeadCollectionOld.contains(returnTranHeadCollectionNewReturnTranHead)) {
                    TranHead oldOriginalTranHeadIdOfReturnTranHeadCollectionNewReturnTranHead = returnTranHeadCollectionNewReturnTranHead.getOriginalTranHeadId();
                    returnTranHeadCollectionNewReturnTranHead.setOriginalTranHeadId(tranHead);
                    returnTranHeadCollectionNewReturnTranHead = em.merge(returnTranHeadCollectionNewReturnTranHead);
                    if (oldOriginalTranHeadIdOfReturnTranHeadCollectionNewReturnTranHead != null && !oldOriginalTranHeadIdOfReturnTranHeadCollectionNewReturnTranHead.equals(tranHead)) {
                        oldOriginalTranHeadIdOfReturnTranHeadCollectionNewReturnTranHead.getReturnTranHeadCollection().remove(returnTranHeadCollectionNewReturnTranHead);
                        oldOriginalTranHeadIdOfReturnTranHeadCollectionNewReturnTranHead = em.merge(oldOriginalTranHeadIdOfReturnTranHeadCollectionNewReturnTranHead);
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
                Integer id = tranHead.getTranHeadId();
                if (findTranHead(id) == null) {
                    throw new NonexistentEntityException("The tranHead with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TranHead tranHead;
            try {
                tranHead = em.getReference(TranHead.class, id);
                tranHead.getTranHeadId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tranHead with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TranDetail> tranDetailCollectionOrphanCheck = tranHead.getTranDetailCollection();
            for (TranDetail tranDetailCollectionOrphanCheckTranDetail : tranDetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TranHead (" + tranHead + ") cannot be destroyed since the TranDetail " + tranDetailCollectionOrphanCheckTranDetail + " in its tranDetailCollection field has a non-nullable tranHeadId field.");
            }
            Collection<TranPayment> tranPaymentCollectionOrphanCheck = tranHead.getTranPaymentCollection();
            for (TranPayment tranPaymentCollectionOrphanCheckTranPayment : tranPaymentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TranHead (" + tranHead + ") cannot be destroyed since the TranPayment " + tranPaymentCollectionOrphanCheckTranPayment + " in its tranPaymentCollection field has a non-nullable tranHeadId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Discount headManagerDiscount = tranHead.getHeadManagerDiscount();
            if (headManagerDiscount != null) {
                headManagerDiscount.getTranHeadCollection().remove(tranHead);
                headManagerDiscount = em.merge(headManagerDiscount);
            }
            EmpUsers userId = tranHead.getUserId();
            if (userId != null) {
                userId.getTranHeadCollection().remove(tranHead);
                userId = em.merge(userId);
            }
            TranHead originalHeadId = tranHead.getOriginalHeadId();
            if (originalHeadId != null) {
                originalHeadId.getTranHeadCollection().remove(tranHead);
                originalHeadId = em.merge(originalHeadId);
            }
            YesNo isForReturned = tranHead.getIsForReturned();
            if (isForReturned != null) {
                isForReturned.getTranHeadCollection().remove(tranHead);
                isForReturned = em.merge(isForReturned);
            }
            YesNo isTrainingMode = tranHead.getIsTrainingMode();
            if (isTrainingMode != null) {
                isTrainingMode.getTranHeadCollection().remove(tranHead);
                isTrainingMode = em.merge(isTrainingMode);
            }
            Collection<TranHead> tranHeadCollection = tranHead.getTranHeadCollection();
            for (TranHead tranHeadCollectionTranHead : tranHeadCollection) {
                tranHeadCollectionTranHead.setOriginalHeadId(null);
                tranHeadCollectionTranHead = em.merge(tranHeadCollectionTranHead);
            }
            Collection<ReturnTranHead> returnTranHeadCollection = tranHead.getReturnTranHeadCollection();
            for (ReturnTranHead returnTranHeadCollectionReturnTranHead : returnTranHeadCollection) {
                returnTranHeadCollectionReturnTranHead.setOriginalTranHeadId(null);
                returnTranHeadCollectionReturnTranHead = em.merge(returnTranHeadCollectionReturnTranHead);
            }
            em.remove(tranHead);
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

    public List<TranHead> findTranHeadEntities() {
        return findTranHeadEntities(true, -1, -1);
    }

    public List<TranHead> findTranHeadEntities(int maxResults, int firstResult) {
        return findTranHeadEntities(false, maxResults, firstResult);
    }

    private List<TranHead> findTranHeadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TranHead as o");
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

    public TranHead findTranHead(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TranHead.class, id);
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }

    public int getTranHeadCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TranHead as o").getSingleResult()).intValue();
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }
}
