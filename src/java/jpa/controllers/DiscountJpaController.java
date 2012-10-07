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
import jpa.entities.DiscountType;
import jpa.entities.Promotion;
import java.util.ArrayList;
import java.util.Collection;
import jpa.entities.TranDetail;
import jpa.entities.ReturnTranDetail;
import jpa.entities.TranHead;
import jpa.entities.ReturnTranHead;

/**
 *
 * @author achen
 */
public class DiscountJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Discount discount) throws RollbackFailureException, Exception {
        if (discount.getPromotionCollection() == null) {
            discount.setPromotionCollection(new ArrayList<Promotion>());
        }
        if (discount.getTranDetailCollection() == null) {
            discount.setTranDetailCollection(new ArrayList<TranDetail>());
        }
        if (discount.getTranDetailCollection1() == null) {
            discount.setTranDetailCollection1(new ArrayList<TranDetail>());
        }
        if (discount.getReturnTranDetailCollection() == null) {
            discount.setReturnTranDetailCollection(new ArrayList<ReturnTranDetail>());
        }
        if (discount.getReturnTranDetailCollection1() == null) {
            discount.setReturnTranDetailCollection1(new ArrayList<ReturnTranDetail>());
        }
        if (discount.getTranHeadCollection() == null) {
            discount.setTranHeadCollection(new ArrayList<TranHead>());
        }
        if (discount.getReturnTranHeadCollection() == null) {
            discount.setReturnTranHeadCollection(new ArrayList<ReturnTranHead>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            DiscountType type = discount.getType();
            if (type != null) {
                type = em.getReference(type.getClass(), type.getType());
                discount.setType(type);
            }
            Collection<Promotion> attachedPromotionCollection = new ArrayList<Promotion>();
            for (Promotion promotionCollectionPromotionToAttach : discount.getPromotionCollection()) {
                promotionCollectionPromotionToAttach = em.getReference(promotionCollectionPromotionToAttach.getClass(), promotionCollectionPromotionToAttach.getPromotionId());
                attachedPromotionCollection.add(promotionCollectionPromotionToAttach);
            }
            discount.setPromotionCollection(attachedPromotionCollection);
            Collection<TranDetail> attachedTranDetailCollection = new ArrayList<TranDetail>();
            for (TranDetail tranDetailCollectionTranDetailToAttach : discount.getTranDetailCollection()) {
                tranDetailCollectionTranDetailToAttach = em.getReference(tranDetailCollectionTranDetailToAttach.getClass(), tranDetailCollectionTranDetailToAttach.getTranDetailId());
                attachedTranDetailCollection.add(tranDetailCollectionTranDetailToAttach);
            }
            discount.setTranDetailCollection(attachedTranDetailCollection);
            Collection<TranDetail> attachedTranDetailCollection1 = new ArrayList<TranDetail>();
            for (TranDetail tranDetailCollection1TranDetailToAttach : discount.getTranDetailCollection1()) {
                tranDetailCollection1TranDetailToAttach = em.getReference(tranDetailCollection1TranDetailToAttach.getClass(), tranDetailCollection1TranDetailToAttach.getTranDetailId());
                attachedTranDetailCollection1.add(tranDetailCollection1TranDetailToAttach);
            }
            discount.setTranDetailCollection1(attachedTranDetailCollection1);
            Collection<ReturnTranDetail> attachedReturnTranDetailCollection = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetailToAttach : discount.getReturnTranDetailCollection()) {
                returnTranDetailCollectionReturnTranDetailToAttach = em.getReference(returnTranDetailCollectionReturnTranDetailToAttach.getClass(), returnTranDetailCollectionReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollection.add(returnTranDetailCollectionReturnTranDetailToAttach);
            }
            discount.setReturnTranDetailCollection(attachedReturnTranDetailCollection);
            Collection<ReturnTranDetail> attachedReturnTranDetailCollection1 = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollection1ReturnTranDetailToAttach : discount.getReturnTranDetailCollection1()) {
                returnTranDetailCollection1ReturnTranDetailToAttach = em.getReference(returnTranDetailCollection1ReturnTranDetailToAttach.getClass(), returnTranDetailCollection1ReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollection1.add(returnTranDetailCollection1ReturnTranDetailToAttach);
            }
            discount.setReturnTranDetailCollection1(attachedReturnTranDetailCollection1);
            Collection<TranHead> attachedTranHeadCollection = new ArrayList<TranHead>();
            for (TranHead tranHeadCollectionTranHeadToAttach : discount.getTranHeadCollection()) {
                tranHeadCollectionTranHeadToAttach = em.getReference(tranHeadCollectionTranHeadToAttach.getClass(), tranHeadCollectionTranHeadToAttach.getTranHeadId());
                attachedTranHeadCollection.add(tranHeadCollectionTranHeadToAttach);
            }
            discount.setTranHeadCollection(attachedTranHeadCollection);
            Collection<ReturnTranHead> attachedReturnTranHeadCollection = new ArrayList<ReturnTranHead>();
            for (ReturnTranHead returnTranHeadCollectionReturnTranHeadToAttach : discount.getReturnTranHeadCollection()) {
                returnTranHeadCollectionReturnTranHeadToAttach = em.getReference(returnTranHeadCollectionReturnTranHeadToAttach.getClass(), returnTranHeadCollectionReturnTranHeadToAttach.getTranHeadId());
                attachedReturnTranHeadCollection.add(returnTranHeadCollectionReturnTranHeadToAttach);
            }
            discount.setReturnTranHeadCollection(attachedReturnTranHeadCollection);
            em.persist(discount);
            
            if (type != null) {
                type.getDiscountCollection().add(discount);
                type = em.merge(type);
            }
            
            /*for (Promotion promotionCollectionPromotion : discount.getPromotionCollection()) {
                Discount oldDiscountOfPromotionCollectionPromotion = promotionCollectionPromotion.getDiscount();
                promotionCollectionPromotion.setDiscount(discount);
                promotionCollectionPromotion = em.merge(promotionCollectionPromotion);
                if (oldDiscountOfPromotionCollectionPromotion != null) {
                    oldDiscountOfPromotionCollectionPromotion.getPromotionCollection().remove(promotionCollectionPromotion);
                    oldDiscountOfPromotionCollectionPromotion = em.merge(oldDiscountOfPromotionCollectionPromotion);
                }
            }
            for (TranDetail tranDetailCollectionTranDetail : discount.getTranDetailCollection()) {
                Discount oldLineManagerDiscountOfTranDetailCollectionTranDetail = tranDetailCollectionTranDetail.getLineManagerDiscount();
                tranDetailCollectionTranDetail.setLineManagerDiscount(discount);
                tranDetailCollectionTranDetail = em.merge(tranDetailCollectionTranDetail);
                if (oldLineManagerDiscountOfTranDetailCollectionTranDetail != null) {
                    oldLineManagerDiscountOfTranDetailCollectionTranDetail.getTranDetailCollection().remove(tranDetailCollectionTranDetail);
                    oldLineManagerDiscountOfTranDetailCollectionTranDetail = em.merge(oldLineManagerDiscountOfTranDetailCollectionTranDetail);
                }
            }
            for (TranDetail tranDetailCollection1TranDetail : discount.getTranDetailCollection1()) {
                Discount oldDiscountOfTranDetailCollection1TranDetail = tranDetailCollection1TranDetail.getDiscount();
                tranDetailCollection1TranDetail.setDiscount(discount);
                tranDetailCollection1TranDetail = em.merge(tranDetailCollection1TranDetail);
                if (oldDiscountOfTranDetailCollection1TranDetail != null) {
                    oldDiscountOfTranDetailCollection1TranDetail.getTranDetailCollection1().remove(tranDetailCollection1TranDetail);
                    oldDiscountOfTranDetailCollection1TranDetail = em.merge(oldDiscountOfTranDetailCollection1TranDetail);
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetail : discount.getReturnTranDetailCollection()) {
                Discount oldLineManagerDiscountOfReturnTranDetailCollectionReturnTranDetail = returnTranDetailCollectionReturnTranDetail.getLineManagerDiscount();
                returnTranDetailCollectionReturnTranDetail.setLineManagerDiscount(discount);
                returnTranDetailCollectionReturnTranDetail = em.merge(returnTranDetailCollectionReturnTranDetail);
                if (oldLineManagerDiscountOfReturnTranDetailCollectionReturnTranDetail != null) {
                    oldLineManagerDiscountOfReturnTranDetailCollectionReturnTranDetail.getReturnTranDetailCollection().remove(returnTranDetailCollectionReturnTranDetail);
                    oldLineManagerDiscountOfReturnTranDetailCollectionReturnTranDetail = em.merge(oldLineManagerDiscountOfReturnTranDetailCollectionReturnTranDetail);
                }
            }
            for (ReturnTranDetail returnTranDetailCollection1ReturnTranDetail : discount.getReturnTranDetailCollection1()) {
                Discount oldDiscountOfReturnTranDetailCollection1ReturnTranDetail = returnTranDetailCollection1ReturnTranDetail.getDiscount();
                returnTranDetailCollection1ReturnTranDetail.setDiscount(discount);
                returnTranDetailCollection1ReturnTranDetail = em.merge(returnTranDetailCollection1ReturnTranDetail);
                if (oldDiscountOfReturnTranDetailCollection1ReturnTranDetail != null) {
                    oldDiscountOfReturnTranDetailCollection1ReturnTranDetail.getReturnTranDetailCollection1().remove(returnTranDetailCollection1ReturnTranDetail);
                    oldDiscountOfReturnTranDetailCollection1ReturnTranDetail = em.merge(oldDiscountOfReturnTranDetailCollection1ReturnTranDetail);
                }
            }
            for (TranHead tranHeadCollectionTranHead : discount.getTranHeadCollection()) {
                Discount oldHeadManagerDiscountOfTranHeadCollectionTranHead = tranHeadCollectionTranHead.getHeadManagerDiscount();
                tranHeadCollectionTranHead.setHeadManagerDiscount(discount);
                tranHeadCollectionTranHead = em.merge(tranHeadCollectionTranHead);
                if (oldHeadManagerDiscountOfTranHeadCollectionTranHead != null) {
                    oldHeadManagerDiscountOfTranHeadCollectionTranHead.getTranHeadCollection().remove(tranHeadCollectionTranHead);
                    oldHeadManagerDiscountOfTranHeadCollectionTranHead = em.merge(oldHeadManagerDiscountOfTranHeadCollectionTranHead);
                }
            }
            for (ReturnTranHead returnTranHeadCollectionReturnTranHead : discount.getReturnTranHeadCollection()) {
                Discount oldHeadManagerDiscountOfReturnTranHeadCollectionReturnTranHead = returnTranHeadCollectionReturnTranHead.getHeadManagerDiscount();
                returnTranHeadCollectionReturnTranHead.setHeadManagerDiscount(discount);
                returnTranHeadCollectionReturnTranHead = em.merge(returnTranHeadCollectionReturnTranHead);
                if (oldHeadManagerDiscountOfReturnTranHeadCollectionReturnTranHead != null) {
                    oldHeadManagerDiscountOfReturnTranHeadCollectionReturnTranHead.getReturnTranHeadCollection().remove(returnTranHeadCollectionReturnTranHead);
                    oldHeadManagerDiscountOfReturnTranHeadCollectionReturnTranHead = em.merge(oldHeadManagerDiscountOfReturnTranHeadCollectionReturnTranHead);
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

    public void edit(Discount discount) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Discount persistentDiscount = em.find(Discount.class, discount.getDiscountId());
            DiscountType typeOld = persistentDiscount.getType();
            DiscountType typeNew = discount.getType();
            Collection<Promotion> promotionCollectionOld = persistentDiscount.getPromotionCollection();
            Collection<Promotion> promotionCollectionNew = discount.getPromotionCollection();
            Collection<TranDetail> tranDetailCollectionOld = persistentDiscount.getTranDetailCollection();
            Collection<TranDetail> tranDetailCollectionNew = discount.getTranDetailCollection();
            Collection<TranDetail> tranDetailCollection1Old = persistentDiscount.getTranDetailCollection1();
            Collection<TranDetail> tranDetailCollection1New = discount.getTranDetailCollection1();
            Collection<ReturnTranDetail> returnTranDetailCollectionOld = persistentDiscount.getReturnTranDetailCollection();
            Collection<ReturnTranDetail> returnTranDetailCollectionNew = discount.getReturnTranDetailCollection();
            Collection<ReturnTranDetail> returnTranDetailCollection1Old = persistentDiscount.getReturnTranDetailCollection1();
            Collection<ReturnTranDetail> returnTranDetailCollection1New = discount.getReturnTranDetailCollection1();
            Collection<TranHead> tranHeadCollectionOld = persistentDiscount.getTranHeadCollection();
            Collection<TranHead> tranHeadCollectionNew = discount.getTranHeadCollection();
            Collection<ReturnTranHead> returnTranHeadCollectionOld = persistentDiscount.getReturnTranHeadCollection();
            Collection<ReturnTranHead> returnTranHeadCollectionNew = discount.getReturnTranHeadCollection();
            List<String> illegalOrphanMessages = null;
            for (Promotion promotionCollectionOldPromotion : promotionCollectionOld) {
                if (!promotionCollectionNew.contains(promotionCollectionOldPromotion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Promotion " + promotionCollectionOldPromotion + " since its discount field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (typeNew != null) {
                typeNew = em.getReference(typeNew.getClass(), typeNew.getType());
                discount.setType(typeNew);
            }
            Collection<Promotion> attachedPromotionCollectionNew = new ArrayList<Promotion>();
            for (Promotion promotionCollectionNewPromotionToAttach : promotionCollectionNew) {
                promotionCollectionNewPromotionToAttach = em.getReference(promotionCollectionNewPromotionToAttach.getClass(), promotionCollectionNewPromotionToAttach.getPromotionId());
                attachedPromotionCollectionNew.add(promotionCollectionNewPromotionToAttach);
            }
            promotionCollectionNew = attachedPromotionCollectionNew;
            discount.setPromotionCollection(promotionCollectionNew);
            Collection<TranDetail> attachedTranDetailCollectionNew = new ArrayList<TranDetail>();
            for (TranDetail tranDetailCollectionNewTranDetailToAttach : tranDetailCollectionNew) {
                tranDetailCollectionNewTranDetailToAttach = em.getReference(tranDetailCollectionNewTranDetailToAttach.getClass(), tranDetailCollectionNewTranDetailToAttach.getTranDetailId());
                attachedTranDetailCollectionNew.add(tranDetailCollectionNewTranDetailToAttach);
            }
            tranDetailCollectionNew = attachedTranDetailCollectionNew;
            discount.setTranDetailCollection(tranDetailCollectionNew);
            Collection<TranDetail> attachedTranDetailCollection1New = new ArrayList<TranDetail>();
            for (TranDetail tranDetailCollection1NewTranDetailToAttach : tranDetailCollection1New) {
                tranDetailCollection1NewTranDetailToAttach = em.getReference(tranDetailCollection1NewTranDetailToAttach.getClass(), tranDetailCollection1NewTranDetailToAttach.getTranDetailId());
                attachedTranDetailCollection1New.add(tranDetailCollection1NewTranDetailToAttach);
            }
            tranDetailCollection1New = attachedTranDetailCollection1New;
            discount.setTranDetailCollection1(tranDetailCollection1New);
            Collection<ReturnTranDetail> attachedReturnTranDetailCollectionNew = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollectionNewReturnTranDetailToAttach : returnTranDetailCollectionNew) {
                returnTranDetailCollectionNewReturnTranDetailToAttach = em.getReference(returnTranDetailCollectionNewReturnTranDetailToAttach.getClass(), returnTranDetailCollectionNewReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollectionNew.add(returnTranDetailCollectionNewReturnTranDetailToAttach);
            }
            returnTranDetailCollectionNew = attachedReturnTranDetailCollectionNew;
            discount.setReturnTranDetailCollection(returnTranDetailCollectionNew);
            Collection<ReturnTranDetail> attachedReturnTranDetailCollection1New = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollection1NewReturnTranDetailToAttach : returnTranDetailCollection1New) {
                returnTranDetailCollection1NewReturnTranDetailToAttach = em.getReference(returnTranDetailCollection1NewReturnTranDetailToAttach.getClass(), returnTranDetailCollection1NewReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollection1New.add(returnTranDetailCollection1NewReturnTranDetailToAttach);
            }
            returnTranDetailCollection1New = attachedReturnTranDetailCollection1New;
            discount.setReturnTranDetailCollection1(returnTranDetailCollection1New);
            Collection<TranHead> attachedTranHeadCollectionNew = new ArrayList<TranHead>();
            for (TranHead tranHeadCollectionNewTranHeadToAttach : tranHeadCollectionNew) {
                tranHeadCollectionNewTranHeadToAttach = em.getReference(tranHeadCollectionNewTranHeadToAttach.getClass(), tranHeadCollectionNewTranHeadToAttach.getTranHeadId());
                attachedTranHeadCollectionNew.add(tranHeadCollectionNewTranHeadToAttach);
            }
            tranHeadCollectionNew = attachedTranHeadCollectionNew;
            discount.setTranHeadCollection(tranHeadCollectionNew);
            Collection<ReturnTranHead> attachedReturnTranHeadCollectionNew = new ArrayList<ReturnTranHead>();
            for (ReturnTranHead returnTranHeadCollectionNewReturnTranHeadToAttach : returnTranHeadCollectionNew) {
                returnTranHeadCollectionNewReturnTranHeadToAttach = em.getReference(returnTranHeadCollectionNewReturnTranHeadToAttach.getClass(), returnTranHeadCollectionNewReturnTranHeadToAttach.getTranHeadId());
                attachedReturnTranHeadCollectionNew.add(returnTranHeadCollectionNewReturnTranHeadToAttach);
            }
            returnTranHeadCollectionNew = attachedReturnTranHeadCollectionNew;
            discount.setReturnTranHeadCollection(returnTranHeadCollectionNew);
            discount = em.merge(discount);
            
            if (typeOld != null && !typeOld.equals(typeNew)) {
                typeOld.getDiscountCollection().remove(discount);
                typeOld = em.merge(typeOld);
            }
            if (typeNew != null && !typeNew.equals(typeOld)) {
                typeNew.getDiscountCollection().add(discount);
                typeNew = em.merge(typeNew);
            }
            /*
            for (Promotion promotionCollectionNewPromotion : promotionCollectionNew) {
                if (!promotionCollectionOld.contains(promotionCollectionNewPromotion)) {
                    Discount oldDiscountOfPromotionCollectionNewPromotion = promotionCollectionNewPromotion.getDiscount();
                    promotionCollectionNewPromotion.setDiscount(discount);
                    promotionCollectionNewPromotion = em.merge(promotionCollectionNewPromotion);
                    if (oldDiscountOfPromotionCollectionNewPromotion != null && !oldDiscountOfPromotionCollectionNewPromotion.equals(discount)) {
                        oldDiscountOfPromotionCollectionNewPromotion.getPromotionCollection().remove(promotionCollectionNewPromotion);
                        oldDiscountOfPromotionCollectionNewPromotion = em.merge(oldDiscountOfPromotionCollectionNewPromotion);
                    }
                }
            }
            for (TranDetail tranDetailCollectionOldTranDetail : tranDetailCollectionOld) {
                if (!tranDetailCollectionNew.contains(tranDetailCollectionOldTranDetail)) {
                    tranDetailCollectionOldTranDetail.setLineManagerDiscount(null);
                    tranDetailCollectionOldTranDetail = em.merge(tranDetailCollectionOldTranDetail);
                }
            }
            for (TranDetail tranDetailCollectionNewTranDetail : tranDetailCollectionNew) {
                if (!tranDetailCollectionOld.contains(tranDetailCollectionNewTranDetail)) {
                    Discount oldLineManagerDiscountOfTranDetailCollectionNewTranDetail = tranDetailCollectionNewTranDetail.getLineManagerDiscount();
                    tranDetailCollectionNewTranDetail.setLineManagerDiscount(discount);
                    tranDetailCollectionNewTranDetail = em.merge(tranDetailCollectionNewTranDetail);
                    if (oldLineManagerDiscountOfTranDetailCollectionNewTranDetail != null && !oldLineManagerDiscountOfTranDetailCollectionNewTranDetail.equals(discount)) {
                        oldLineManagerDiscountOfTranDetailCollectionNewTranDetail.getTranDetailCollection().remove(tranDetailCollectionNewTranDetail);
                        oldLineManagerDiscountOfTranDetailCollectionNewTranDetail = em.merge(oldLineManagerDiscountOfTranDetailCollectionNewTranDetail);
                    }
                }
            }
            for (TranDetail tranDetailCollection1OldTranDetail : tranDetailCollection1Old) {
                if (!tranDetailCollection1New.contains(tranDetailCollection1OldTranDetail)) {
                    tranDetailCollection1OldTranDetail.setDiscount(null);
                    tranDetailCollection1OldTranDetail = em.merge(tranDetailCollection1OldTranDetail);
                }
            }
            for (TranDetail tranDetailCollection1NewTranDetail : tranDetailCollection1New) {
                if (!tranDetailCollection1Old.contains(tranDetailCollection1NewTranDetail)) {
                    Discount oldDiscountOfTranDetailCollection1NewTranDetail = tranDetailCollection1NewTranDetail.getDiscount();
                    tranDetailCollection1NewTranDetail.setDiscount(discount);
                    tranDetailCollection1NewTranDetail = em.merge(tranDetailCollection1NewTranDetail);
                    if (oldDiscountOfTranDetailCollection1NewTranDetail != null && !oldDiscountOfTranDetailCollection1NewTranDetail.equals(discount)) {
                        oldDiscountOfTranDetailCollection1NewTranDetail.getTranDetailCollection1().remove(tranDetailCollection1NewTranDetail);
                        oldDiscountOfTranDetailCollection1NewTranDetail = em.merge(oldDiscountOfTranDetailCollection1NewTranDetail);
                    }
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionOldReturnTranDetail : returnTranDetailCollectionOld) {
                if (!returnTranDetailCollectionNew.contains(returnTranDetailCollectionOldReturnTranDetail)) {
                    returnTranDetailCollectionOldReturnTranDetail.setLineManagerDiscount(null);
                    returnTranDetailCollectionOldReturnTranDetail = em.merge(returnTranDetailCollectionOldReturnTranDetail);
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionNewReturnTranDetail : returnTranDetailCollectionNew) {
                if (!returnTranDetailCollectionOld.contains(returnTranDetailCollectionNewReturnTranDetail)) {
                    Discount oldLineManagerDiscountOfReturnTranDetailCollectionNewReturnTranDetail = returnTranDetailCollectionNewReturnTranDetail.getLineManagerDiscount();
                    returnTranDetailCollectionNewReturnTranDetail.setLineManagerDiscount(discount);
                    returnTranDetailCollectionNewReturnTranDetail = em.merge(returnTranDetailCollectionNewReturnTranDetail);
                    if (oldLineManagerDiscountOfReturnTranDetailCollectionNewReturnTranDetail != null && !oldLineManagerDiscountOfReturnTranDetailCollectionNewReturnTranDetail.equals(discount)) {
                        oldLineManagerDiscountOfReturnTranDetailCollectionNewReturnTranDetail.getReturnTranDetailCollection().remove(returnTranDetailCollectionNewReturnTranDetail);
                        oldLineManagerDiscountOfReturnTranDetailCollectionNewReturnTranDetail = em.merge(oldLineManagerDiscountOfReturnTranDetailCollectionNewReturnTranDetail);
                    }
                }
            }
            for (ReturnTranDetail returnTranDetailCollection1OldReturnTranDetail : returnTranDetailCollection1Old) {
                if (!returnTranDetailCollection1New.contains(returnTranDetailCollection1OldReturnTranDetail)) {
                    returnTranDetailCollection1OldReturnTranDetail.setDiscount(null);
                    returnTranDetailCollection1OldReturnTranDetail = em.merge(returnTranDetailCollection1OldReturnTranDetail);
                }
            }
            for (ReturnTranDetail returnTranDetailCollection1NewReturnTranDetail : returnTranDetailCollection1New) {
                if (!returnTranDetailCollection1Old.contains(returnTranDetailCollection1NewReturnTranDetail)) {
                    Discount oldDiscountOfReturnTranDetailCollection1NewReturnTranDetail = returnTranDetailCollection1NewReturnTranDetail.getDiscount();
                    returnTranDetailCollection1NewReturnTranDetail.setDiscount(discount);
                    returnTranDetailCollection1NewReturnTranDetail = em.merge(returnTranDetailCollection1NewReturnTranDetail);
                    if (oldDiscountOfReturnTranDetailCollection1NewReturnTranDetail != null && !oldDiscountOfReturnTranDetailCollection1NewReturnTranDetail.equals(discount)) {
                        oldDiscountOfReturnTranDetailCollection1NewReturnTranDetail.getReturnTranDetailCollection1().remove(returnTranDetailCollection1NewReturnTranDetail);
                        oldDiscountOfReturnTranDetailCollection1NewReturnTranDetail = em.merge(oldDiscountOfReturnTranDetailCollection1NewReturnTranDetail);
                    }
                }
            }
            for (TranHead tranHeadCollectionOldTranHead : tranHeadCollectionOld) {
                if (!tranHeadCollectionNew.contains(tranHeadCollectionOldTranHead)) {
                    tranHeadCollectionOldTranHead.setHeadManagerDiscount(null);
                    tranHeadCollectionOldTranHead = em.merge(tranHeadCollectionOldTranHead);
                }
            }
            for (TranHead tranHeadCollectionNewTranHead : tranHeadCollectionNew) {
                if (!tranHeadCollectionOld.contains(tranHeadCollectionNewTranHead)) {
                    Discount oldHeadManagerDiscountOfTranHeadCollectionNewTranHead = tranHeadCollectionNewTranHead.getHeadManagerDiscount();
                    tranHeadCollectionNewTranHead.setHeadManagerDiscount(discount);
                    tranHeadCollectionNewTranHead = em.merge(tranHeadCollectionNewTranHead);
                    if (oldHeadManagerDiscountOfTranHeadCollectionNewTranHead != null && !oldHeadManagerDiscountOfTranHeadCollectionNewTranHead.equals(discount)) {
                        oldHeadManagerDiscountOfTranHeadCollectionNewTranHead.getTranHeadCollection().remove(tranHeadCollectionNewTranHead);
                        oldHeadManagerDiscountOfTranHeadCollectionNewTranHead = em.merge(oldHeadManagerDiscountOfTranHeadCollectionNewTranHead);
                    }
                }
            }
            for (ReturnTranHead returnTranHeadCollectionOldReturnTranHead : returnTranHeadCollectionOld) {
                if (!returnTranHeadCollectionNew.contains(returnTranHeadCollectionOldReturnTranHead)) {
                    returnTranHeadCollectionOldReturnTranHead.setHeadManagerDiscount(null);
                    returnTranHeadCollectionOldReturnTranHead = em.merge(returnTranHeadCollectionOldReturnTranHead);
                }
            }
            for (ReturnTranHead returnTranHeadCollectionNewReturnTranHead : returnTranHeadCollectionNew) {
                if (!returnTranHeadCollectionOld.contains(returnTranHeadCollectionNewReturnTranHead)) {
                    Discount oldHeadManagerDiscountOfReturnTranHeadCollectionNewReturnTranHead = returnTranHeadCollectionNewReturnTranHead.getHeadManagerDiscount();
                    returnTranHeadCollectionNewReturnTranHead.setHeadManagerDiscount(discount);
                    returnTranHeadCollectionNewReturnTranHead = em.merge(returnTranHeadCollectionNewReturnTranHead);
                    if (oldHeadManagerDiscountOfReturnTranHeadCollectionNewReturnTranHead != null && !oldHeadManagerDiscountOfReturnTranHeadCollectionNewReturnTranHead.equals(discount)) {
                        oldHeadManagerDiscountOfReturnTranHeadCollectionNewReturnTranHead.getReturnTranHeadCollection().remove(returnTranHeadCollectionNewReturnTranHead);
                        oldHeadManagerDiscountOfReturnTranHeadCollectionNewReturnTranHead = em.merge(oldHeadManagerDiscountOfReturnTranHeadCollectionNewReturnTranHead);
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
                Integer id = discount.getDiscountId();
                if (findDiscount(id) == null) {
                    throw new NonexistentEntityException("The discount with id " + id + " no longer exists.");
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
            Discount discount;
            try {
                discount = em.getReference(Discount.class, id);
                discount.getDiscountId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The discount with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Promotion> promotionCollectionOrphanCheck = discount.getPromotionCollection();
            for (Promotion promotionCollectionOrphanCheckPromotion : promotionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Discount (" + discount + ") cannot be destroyed since the Promotion " + promotionCollectionOrphanCheckPromotion + " in its promotionCollection field has a non-nullable discount field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            DiscountType type = discount.getType();
            if (type != null) {
                type.getDiscountCollection().remove(discount);
                type = em.merge(type);
            }
            Collection<TranDetail> tranDetailCollection = discount.getTranDetailCollection();
            for (TranDetail tranDetailCollectionTranDetail : tranDetailCollection) {
                tranDetailCollectionTranDetail.setLineManagerDiscount(null);
                tranDetailCollectionTranDetail = em.merge(tranDetailCollectionTranDetail);
            }
            Collection<TranDetail> tranDetailCollection1 = discount.getTranDetailCollection1();
            for (TranDetail tranDetailCollection1TranDetail : tranDetailCollection1) {
                tranDetailCollection1TranDetail.setDiscount(null);
                tranDetailCollection1TranDetail = em.merge(tranDetailCollection1TranDetail);
            }
            Collection<ReturnTranDetail> returnTranDetailCollection = discount.getReturnTranDetailCollection();
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetail : returnTranDetailCollection) {
                returnTranDetailCollectionReturnTranDetail.setLineManagerDiscount(null);
                returnTranDetailCollectionReturnTranDetail = em.merge(returnTranDetailCollectionReturnTranDetail);
            }
            Collection<ReturnTranDetail> returnTranDetailCollection1 = discount.getReturnTranDetailCollection1();
            for (ReturnTranDetail returnTranDetailCollection1ReturnTranDetail : returnTranDetailCollection1) {
                returnTranDetailCollection1ReturnTranDetail.setDiscount(null);
                returnTranDetailCollection1ReturnTranDetail = em.merge(returnTranDetailCollection1ReturnTranDetail);
            }
            Collection<TranHead> tranHeadCollection = discount.getTranHeadCollection();
            for (TranHead tranHeadCollectionTranHead : tranHeadCollection) {
                tranHeadCollectionTranHead.setHeadManagerDiscount(null);
                tranHeadCollectionTranHead = em.merge(tranHeadCollectionTranHead);
            }
            Collection<ReturnTranHead> returnTranHeadCollection = discount.getReturnTranHeadCollection();
            for (ReturnTranHead returnTranHeadCollectionReturnTranHead : returnTranHeadCollection) {
                returnTranHeadCollectionReturnTranHead.setHeadManagerDiscount(null);
                returnTranHeadCollectionReturnTranHead = em.merge(returnTranHeadCollectionReturnTranHead);
            }
            em.remove(discount);
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

    public List<Discount> findDiscountEntities() {
        return findDiscountEntities(true, -1, -1);
    }

    public List<Discount> findDiscountEntities(int maxResults, int firstResult) {
        return findDiscountEntities(false, maxResults, firstResult);
    }

    private List<Discount> findDiscountEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Discount as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Discount findDiscount(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Discount.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiscountCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Discount as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
