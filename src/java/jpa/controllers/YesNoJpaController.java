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
import javax.naming.Context;
import javax.naming.InitialContext;
import jpa.entities.ReturnTranDetail;
import jpa.entities.TranHead;
import jpa.entities.Product;
import jpa.entities.ReturnTranHead;
import jpa.entities.YesNo;

/**
 *
 * @author achen
 */
public class YesNoJpaController {

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

    public void create(YesNo yesNo) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (yesNo.getPromotionCollection() == null) {
            yesNo.setPromotionCollection(new ArrayList<Promotion>());
        }
        if (yesNo.getPromotionCollection1() == null) {
            yesNo.setPromotionCollection1(new ArrayList<Promotion>());
        }
        if (yesNo.getReturnTranDetailCollection() == null) {
            yesNo.setReturnTranDetailCollection(new ArrayList<ReturnTranDetail>());
        }
        if (yesNo.getTranHeadCollection() == null) {
            yesNo.setTranHeadCollection(new ArrayList<TranHead>());
        }
        if (yesNo.getProductCollection() == null) {
            yesNo.setProductCollection(new ArrayList<Product>());
        }
        if (yesNo.getProductCollection1() == null) {
            yesNo.setProductCollection1(new ArrayList<Product>());
        }
        if (yesNo.getProductCollection2() == null) {
            yesNo.setProductCollection2(new ArrayList<Product>());
        }
        if (yesNo.getReturnTranHeadCollection() == null) {
            yesNo.setReturnTranHeadCollection(new ArrayList<ReturnTranHead>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            /*
            Collection<Promotion> attachedPromotionCollection = new ArrayList<Promotion>();
            for (Promotion promotionCollectionPromotionToAttach : yesNo.getPromotionCollection()) {
                promotionCollectionPromotionToAttach = em.getReference(promotionCollectionPromotionToAttach.getClass(), promotionCollectionPromotionToAttach.getPromotionId());
                attachedPromotionCollection.add(promotionCollectionPromotionToAttach);
            }
            yesNo.setPromotionCollection(attachedPromotionCollection);
            Collection<Promotion> attachedPromotionCollection1 = new ArrayList<Promotion>();
            for (Promotion promotionCollection1PromotionToAttach : yesNo.getPromotionCollection1()) {
                promotionCollection1PromotionToAttach = em.getReference(promotionCollection1PromotionToAttach.getClass(), promotionCollection1PromotionToAttach.getPromotionId());
                attachedPromotionCollection1.add(promotionCollection1PromotionToAttach);
            }
            yesNo.setPromotionCollection1(attachedPromotionCollection1);
            Collection<ReturnTranDetail> attachedReturnTranDetailCollection = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetailToAttach : yesNo.getReturnTranDetailCollection()) {
                returnTranDetailCollectionReturnTranDetailToAttach = em.getReference(returnTranDetailCollectionReturnTranDetailToAttach.getClass(), returnTranDetailCollectionReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollection.add(returnTranDetailCollectionReturnTranDetailToAttach);
            }
            yesNo.setReturnTranDetailCollection(attachedReturnTranDetailCollection);
            Collection<TranHead> attachedTranHeadCollection = new ArrayList<TranHead>();
            for (TranHead tranHeadCollectionTranHeadToAttach : yesNo.getTranHeadCollection()) {
                tranHeadCollectionTranHeadToAttach = em.getReference(tranHeadCollectionTranHeadToAttach.getClass(), tranHeadCollectionTranHeadToAttach.getTranHeadId());
                attachedTranHeadCollection.add(tranHeadCollectionTranHeadToAttach);
            }
            yesNo.setTranHeadCollection(attachedTranHeadCollection);
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : yesNo.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getIsbn());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            yesNo.setProductCollection(attachedProductCollection);
            Collection<Product> attachedProductCollection1 = new ArrayList<Product>();
            for (Product productCollection1ProductToAttach : yesNo.getProductCollection1()) {
                productCollection1ProductToAttach = em.getReference(productCollection1ProductToAttach.getClass(), productCollection1ProductToAttach.getIsbn());
                attachedProductCollection1.add(productCollection1ProductToAttach);
            }
            yesNo.setProductCollection1(attachedProductCollection1);
            Collection<Product> attachedProductCollection2 = new ArrayList<Product>();
            for (Product productCollection2ProductToAttach : yesNo.getProductCollection2()) {
                productCollection2ProductToAttach = em.getReference(productCollection2ProductToAttach.getClass(), productCollection2ProductToAttach.getIsbn());
                attachedProductCollection2.add(productCollection2ProductToAttach);
            }
            yesNo.setProductCollection2(attachedProductCollection2);
            Collection<ReturnTranHead> attachedReturnTranHeadCollection = new ArrayList<ReturnTranHead>();
            for (ReturnTranHead returnTranHeadCollectionReturnTranHeadToAttach : yesNo.getReturnTranHeadCollection()) {
                returnTranHeadCollectionReturnTranHeadToAttach = em.getReference(returnTranHeadCollectionReturnTranHeadToAttach.getClass(), returnTranHeadCollectionReturnTranHeadToAttach.getTranHeadId());
                attachedReturnTranHeadCollection.add(returnTranHeadCollectionReturnTranHeadToAttach);
            }
            yesNo.setReturnTranHeadCollection(attachedReturnTranHeadCollection);
            */
            em.persist(yesNo);
            /*
            for (Promotion promotionCollectionPromotion : yesNo.getPromotionCollection()) {
                YesNo oldIsEnabledOfPromotionCollectionPromotion = promotionCollectionPromotion.getIsEnabled();
                promotionCollectionPromotion.setIsEnabled(yesNo);
                promotionCollectionPromotion = em.merge(promotionCollectionPromotion);
                if (oldIsEnabledOfPromotionCollectionPromotion != null) {
                    oldIsEnabledOfPromotionCollectionPromotion.getPromotionCollection().remove(promotionCollectionPromotion);
                    oldIsEnabledOfPromotionCollectionPromotion = em.merge(oldIsEnabledOfPromotionCollectionPromotion);
                }
            }
            for (Promotion promotionCollection1Promotion : yesNo.getPromotionCollection1()) {
                YesNo oldIsAppliedToAllOfPromotionCollection1Promotion = promotionCollection1Promotion.getIsAppliedToAll();
                promotionCollection1Promotion.setIsAppliedToAll(yesNo);
                promotionCollection1Promotion = em.merge(promotionCollection1Promotion);
                if (oldIsAppliedToAllOfPromotionCollection1Promotion != null) {
                    oldIsAppliedToAllOfPromotionCollection1Promotion.getPromotionCollection1().remove(promotionCollection1Promotion);
                    oldIsAppliedToAllOfPromotionCollection1Promotion = em.merge(oldIsAppliedToAllOfPromotionCollection1Promotion);
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetail : yesNo.getReturnTranDetailCollection()) {
                YesNo oldIsReturnedOfReturnTranDetailCollectionReturnTranDetail = returnTranDetailCollectionReturnTranDetail.getIsReturned();
                returnTranDetailCollectionReturnTranDetail.setIsReturned(yesNo);
                returnTranDetailCollectionReturnTranDetail = em.merge(returnTranDetailCollectionReturnTranDetail);
                if (oldIsReturnedOfReturnTranDetailCollectionReturnTranDetail != null) {
                    oldIsReturnedOfReturnTranDetailCollectionReturnTranDetail.getReturnTranDetailCollection().remove(returnTranDetailCollectionReturnTranDetail);
                    oldIsReturnedOfReturnTranDetailCollectionReturnTranDetail = em.merge(oldIsReturnedOfReturnTranDetailCollectionReturnTranDetail);
                }
            }
            for (TranHead tranHeadCollectionTranHead : yesNo.getTranHeadCollection()) {
                YesNo oldIsTrainingModeOfTranHeadCollectionTranHead = tranHeadCollectionTranHead.getIsTrainingMode();
                tranHeadCollectionTranHead.setIsTrainingMode(yesNo);
                tranHeadCollectionTranHead = em.merge(tranHeadCollectionTranHead);
                if (oldIsTrainingModeOfTranHeadCollectionTranHead != null) {
                    oldIsTrainingModeOfTranHeadCollectionTranHead.getTranHeadCollection().remove(tranHeadCollectionTranHead);
                    oldIsTrainingModeOfTranHeadCollectionTranHead = em.merge(oldIsTrainingModeOfTranHeadCollectionTranHead);
                }
            }
            for (Product productCollectionProduct : yesNo.getProductCollection()) {
                YesNo oldIsOrganicOfProductCollectionProduct = productCollectionProduct.getIsOrganic();
                productCollectionProduct.setIsOrganic(yesNo);
                productCollectionProduct = em.merge(productCollectionProduct);
                if (oldIsOrganicOfProductCollectionProduct != null) {
                    oldIsOrganicOfProductCollectionProduct.getProductCollection().remove(productCollectionProduct);
                    oldIsOrganicOfProductCollectionProduct = em.merge(oldIsOrganicOfProductCollectionProduct);
                }
            }
            for (Product productCollection1Product : yesNo.getProductCollection1()) {
                YesNo oldHasExpirationDateOfProductCollection1Product = productCollection1Product.getHasExpirationDate();
                productCollection1Product.setHasExpirationDate(yesNo);
                productCollection1Product = em.merge(productCollection1Product);
                if (oldHasExpirationDateOfProductCollection1Product != null) {
                    oldHasExpirationDateOfProductCollection1Product.getProductCollection1().remove(productCollection1Product);
                    oldHasExpirationDateOfProductCollection1Product = em.merge(oldHasExpirationDateOfProductCollection1Product);
                }
            }
            for (Product productCollection2Product : yesNo.getProductCollection2()) {
                YesNo oldBrandDiscountExcludedOfProductCollection2Product = productCollection2Product.getBrandDiscountExcluded();
                productCollection2Product.setBrandDiscountExcluded(yesNo);
                productCollection2Product = em.merge(productCollection2Product);
                if (oldBrandDiscountExcludedOfProductCollection2Product != null) {
                    oldBrandDiscountExcludedOfProductCollection2Product.getProductCollection2().remove(productCollection2Product);
                    oldBrandDiscountExcludedOfProductCollection2Product = em.merge(oldBrandDiscountExcludedOfProductCollection2Product);
                }
            }
            for (ReturnTranHead returnTranHeadCollectionReturnTranHead : yesNo.getReturnTranHeadCollection()) {
                YesNo oldIsTrainingModeOfReturnTranHeadCollectionReturnTranHead = returnTranHeadCollectionReturnTranHead.getIsTrainingMode();
                returnTranHeadCollectionReturnTranHead.setIsTrainingMode(yesNo);
                returnTranHeadCollectionReturnTranHead = em.merge(returnTranHeadCollectionReturnTranHead);
                if (oldIsTrainingModeOfReturnTranHeadCollectionReturnTranHead != null) {
                    oldIsTrainingModeOfReturnTranHeadCollectionReturnTranHead.getReturnTranHeadCollection().remove(returnTranHeadCollectionReturnTranHead);
                    oldIsTrainingModeOfReturnTranHeadCollectionReturnTranHead = em.merge(oldIsTrainingModeOfReturnTranHeadCollectionReturnTranHead);
                }
            }*/
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findYesNo(yesNo.getName()) != null) {
                throw new PreexistingEntityException("YesNo " + yesNo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public void edit(YesNo yesNo) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            //YesNo persistentYesNo = em.find(YesNo.class, yesNo.getName());
            /*
            Collection<Promotion> promotionCollectionOld = persistentYesNo.getPromotionCollection();
            Collection<Promotion> promotionCollectionNew = yesNo.getPromotionCollection();
            Collection<Promotion> promotionCollection1Old = persistentYesNo.getPromotionCollection1();
            Collection<Promotion> promotionCollection1New = yesNo.getPromotionCollection1();
            Collection<ReturnTranDetail> returnTranDetailCollectionOld = persistentYesNo.getReturnTranDetailCollection();
            Collection<ReturnTranDetail> returnTranDetailCollectionNew = yesNo.getReturnTranDetailCollection();
            Collection<TranHead> tranHeadCollectionOld = persistentYesNo.getTranHeadCollection();
            Collection<TranHead> tranHeadCollectionNew = yesNo.getTranHeadCollection();
            Collection<Product> productCollectionOld = persistentYesNo.getProductCollection();
            Collection<Product> productCollectionNew = yesNo.getProductCollection();
            Collection<Product> productCollection1Old = persistentYesNo.getProductCollection1();
            Collection<Product> productCollection1New = yesNo.getProductCollection1();
            Collection<Product> productCollection2Old = persistentYesNo.getProductCollection2();
            Collection<Product> productCollection2New = yesNo.getProductCollection2();
            Collection<ReturnTranHead> returnTranHeadCollectionOld = persistentYesNo.getReturnTranHeadCollection();
            Collection<ReturnTranHead> returnTranHeadCollectionNew = yesNo.getReturnTranHeadCollection();
            Collection<Promotion> attachedPromotionCollectionNew = new ArrayList<Promotion>();
            for (Promotion promotionCollectionNewPromotionToAttach : promotionCollectionNew) {
                promotionCollectionNewPromotionToAttach = em.getReference(promotionCollectionNewPromotionToAttach.getClass(), promotionCollectionNewPromotionToAttach.getPromotionId());
                attachedPromotionCollectionNew.add(promotionCollectionNewPromotionToAttach);
            }
            promotionCollectionNew = attachedPromotionCollectionNew;
            yesNo.setPromotionCollection(promotionCollectionNew);
            Collection<Promotion> attachedPromotionCollection1New = new ArrayList<Promotion>();
            for (Promotion promotionCollection1NewPromotionToAttach : promotionCollection1New) {
                promotionCollection1NewPromotionToAttach = em.getReference(promotionCollection1NewPromotionToAttach.getClass(), promotionCollection1NewPromotionToAttach.getPromotionId());
                attachedPromotionCollection1New.add(promotionCollection1NewPromotionToAttach);
            }
            promotionCollection1New = attachedPromotionCollection1New;
            yesNo.setPromotionCollection1(promotionCollection1New);
            Collection<ReturnTranDetail> attachedReturnTranDetailCollectionNew = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollectionNewReturnTranDetailToAttach : returnTranDetailCollectionNew) {
                returnTranDetailCollectionNewReturnTranDetailToAttach = em.getReference(returnTranDetailCollectionNewReturnTranDetailToAttach.getClass(), returnTranDetailCollectionNewReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollectionNew.add(returnTranDetailCollectionNewReturnTranDetailToAttach);
            }
            returnTranDetailCollectionNew = attachedReturnTranDetailCollectionNew;
            yesNo.setReturnTranDetailCollection(returnTranDetailCollectionNew);
            Collection<TranHead> attachedTranHeadCollectionNew = new ArrayList<TranHead>();
            for (TranHead tranHeadCollectionNewTranHeadToAttach : tranHeadCollectionNew) {
                tranHeadCollectionNewTranHeadToAttach = em.getReference(tranHeadCollectionNewTranHeadToAttach.getClass(), tranHeadCollectionNewTranHeadToAttach.getTranHeadId());
                attachedTranHeadCollectionNew.add(tranHeadCollectionNewTranHeadToAttach);
            }
            tranHeadCollectionNew = attachedTranHeadCollectionNew;
            yesNo.setTranHeadCollection(tranHeadCollectionNew);
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getIsbn());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            yesNo.setProductCollection(productCollectionNew);
            Collection<Product> attachedProductCollection1New = new ArrayList<Product>();
            for (Product productCollection1NewProductToAttach : productCollection1New) {
                productCollection1NewProductToAttach = em.getReference(productCollection1NewProductToAttach.getClass(), productCollection1NewProductToAttach.getIsbn());
                attachedProductCollection1New.add(productCollection1NewProductToAttach);
            }
            productCollection1New = attachedProductCollection1New;
            yesNo.setProductCollection1(productCollection1New);
            Collection<Product> attachedProductCollection2New = new ArrayList<Product>();
            for (Product productCollection2NewProductToAttach : productCollection2New) {
                productCollection2NewProductToAttach = em.getReference(productCollection2NewProductToAttach.getClass(), productCollection2NewProductToAttach.getIsbn());
                attachedProductCollection2New.add(productCollection2NewProductToAttach);
            }
            productCollection2New = attachedProductCollection2New;
            yesNo.setProductCollection2(productCollection2New);
            Collection<ReturnTranHead> attachedReturnTranHeadCollectionNew = new ArrayList<ReturnTranHead>();
            for (ReturnTranHead returnTranHeadCollectionNewReturnTranHeadToAttach : returnTranHeadCollectionNew) {
                returnTranHeadCollectionNewReturnTranHeadToAttach = em.getReference(returnTranHeadCollectionNewReturnTranHeadToAttach.getClass(), returnTranHeadCollectionNewReturnTranHeadToAttach.getTranHeadId());
                attachedReturnTranHeadCollectionNew.add(returnTranHeadCollectionNewReturnTranHeadToAttach);
            }
            returnTranHeadCollectionNew = attachedReturnTranHeadCollectionNew;
            yesNo.setReturnTranHeadCollection(returnTranHeadCollectionNew);
             * */
            yesNo = em.merge(yesNo);
            /*
            for (Promotion promotionCollectionOldPromotion : promotionCollectionOld) {
                if (!promotionCollectionNew.contains(promotionCollectionOldPromotion)) {
                    promotionCollectionOldPromotion.setIsEnabled(null);
                    promotionCollectionOldPromotion = em.merge(promotionCollectionOldPromotion);
                }
            }
            for (Promotion promotionCollectionNewPromotion : promotionCollectionNew) {
                if (!promotionCollectionOld.contains(promotionCollectionNewPromotion)) {
                    YesNo oldIsEnabledOfPromotionCollectionNewPromotion = promotionCollectionNewPromotion.getIsEnabled();
                    promotionCollectionNewPromotion.setIsEnabled(yesNo);
                    promotionCollectionNewPromotion = em.merge(promotionCollectionNewPromotion);
                    if (oldIsEnabledOfPromotionCollectionNewPromotion != null && !oldIsEnabledOfPromotionCollectionNewPromotion.equals(yesNo)) {
                        oldIsEnabledOfPromotionCollectionNewPromotion.getPromotionCollection().remove(promotionCollectionNewPromotion);
                        oldIsEnabledOfPromotionCollectionNewPromotion = em.merge(oldIsEnabledOfPromotionCollectionNewPromotion);
                    }
                }
            }
            for (Promotion promotionCollection1OldPromotion : promotionCollection1Old) {
                if (!promotionCollection1New.contains(promotionCollection1OldPromotion)) {
                    promotionCollection1OldPromotion.setIsAppliedToAll(null);
                    promotionCollection1OldPromotion = em.merge(promotionCollection1OldPromotion);
                }
            }
            for (Promotion promotionCollection1NewPromotion : promotionCollection1New) {
                if (!promotionCollection1Old.contains(promotionCollection1NewPromotion)) {
                    YesNo oldIsAppliedToAllOfPromotionCollection1NewPromotion = promotionCollection1NewPromotion.getIsAppliedToAll();
                    promotionCollection1NewPromotion.setIsAppliedToAll(yesNo);
                    promotionCollection1NewPromotion = em.merge(promotionCollection1NewPromotion);
                    if (oldIsAppliedToAllOfPromotionCollection1NewPromotion != null && !oldIsAppliedToAllOfPromotionCollection1NewPromotion.equals(yesNo)) {
                        oldIsAppliedToAllOfPromotionCollection1NewPromotion.getPromotionCollection1().remove(promotionCollection1NewPromotion);
                        oldIsAppliedToAllOfPromotionCollection1NewPromotion = em.merge(oldIsAppliedToAllOfPromotionCollection1NewPromotion);
                    }
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionOldReturnTranDetail : returnTranDetailCollectionOld) {
                if (!returnTranDetailCollectionNew.contains(returnTranDetailCollectionOldReturnTranDetail)) {
                    returnTranDetailCollectionOldReturnTranDetail.setIsReturned(null);
                    returnTranDetailCollectionOldReturnTranDetail = em.merge(returnTranDetailCollectionOldReturnTranDetail);
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionNewReturnTranDetail : returnTranDetailCollectionNew) {
                if (!returnTranDetailCollectionOld.contains(returnTranDetailCollectionNewReturnTranDetail)) {
                    YesNo oldIsReturnedOfReturnTranDetailCollectionNewReturnTranDetail = returnTranDetailCollectionNewReturnTranDetail.getIsReturned();
                    returnTranDetailCollectionNewReturnTranDetail.setIsReturned(yesNo);
                    returnTranDetailCollectionNewReturnTranDetail = em.merge(returnTranDetailCollectionNewReturnTranDetail);
                    if (oldIsReturnedOfReturnTranDetailCollectionNewReturnTranDetail != null && !oldIsReturnedOfReturnTranDetailCollectionNewReturnTranDetail.equals(yesNo)) {
                        oldIsReturnedOfReturnTranDetailCollectionNewReturnTranDetail.getReturnTranDetailCollection().remove(returnTranDetailCollectionNewReturnTranDetail);
                        oldIsReturnedOfReturnTranDetailCollectionNewReturnTranDetail = em.merge(oldIsReturnedOfReturnTranDetailCollectionNewReturnTranDetail);
                    }
                }
            }
            for (TranHead tranHeadCollectionOldTranHead : tranHeadCollectionOld) {
                if (!tranHeadCollectionNew.contains(tranHeadCollectionOldTranHead)) {
                    tranHeadCollectionOldTranHead.setIsTrainingMode(null);
                    tranHeadCollectionOldTranHead = em.merge(tranHeadCollectionOldTranHead);
                }
            }
            for (TranHead tranHeadCollectionNewTranHead : tranHeadCollectionNew) {
                if (!tranHeadCollectionOld.contains(tranHeadCollectionNewTranHead)) {
                    YesNo oldIsTrainingModeOfTranHeadCollectionNewTranHead = tranHeadCollectionNewTranHead.getIsTrainingMode();
                    tranHeadCollectionNewTranHead.setIsTrainingMode(yesNo);
                    tranHeadCollectionNewTranHead = em.merge(tranHeadCollectionNewTranHead);
                    if (oldIsTrainingModeOfTranHeadCollectionNewTranHead != null && !oldIsTrainingModeOfTranHeadCollectionNewTranHead.equals(yesNo)) {
                        oldIsTrainingModeOfTranHeadCollectionNewTranHead.getTranHeadCollection().remove(tranHeadCollectionNewTranHead);
                        oldIsTrainingModeOfTranHeadCollectionNewTranHead = em.merge(oldIsTrainingModeOfTranHeadCollectionNewTranHead);
                    }
                }
            }
            for (Product productCollectionOldProduct : productCollectionOld) {
                if (!productCollectionNew.contains(productCollectionOldProduct)) {
                    productCollectionOldProduct.setIsOrganic(null);
                    productCollectionOldProduct = em.merge(productCollectionOldProduct);
                }
            }
            for (Product productCollectionNewProduct : productCollectionNew) {
                if (!productCollectionOld.contains(productCollectionNewProduct)) {
                    YesNo oldIsOrganicOfProductCollectionNewProduct = productCollectionNewProduct.getIsOrganic();
                    productCollectionNewProduct.setIsOrganic(yesNo);
                    productCollectionNewProduct = em.merge(productCollectionNewProduct);
                    if (oldIsOrganicOfProductCollectionNewProduct != null && !oldIsOrganicOfProductCollectionNewProduct.equals(yesNo)) {
                        oldIsOrganicOfProductCollectionNewProduct.getProductCollection().remove(productCollectionNewProduct);
                        oldIsOrganicOfProductCollectionNewProduct = em.merge(oldIsOrganicOfProductCollectionNewProduct);
                    }
                }
            }
            for (Product productCollection1OldProduct : productCollection1Old) {
                if (!productCollection1New.contains(productCollection1OldProduct)) {
                    productCollection1OldProduct.setHasExpirationDate(null);
                    productCollection1OldProduct = em.merge(productCollection1OldProduct);
                }
            }
            for (Product productCollection1NewProduct : productCollection1New) {
                if (!productCollection1Old.contains(productCollection1NewProduct)) {
                    YesNo oldHasExpirationDateOfProductCollection1NewProduct = productCollection1NewProduct.getHasExpirationDate();
                    productCollection1NewProduct.setHasExpirationDate(yesNo);
                    productCollection1NewProduct = em.merge(productCollection1NewProduct);
                    if (oldHasExpirationDateOfProductCollection1NewProduct != null && !oldHasExpirationDateOfProductCollection1NewProduct.equals(yesNo)) {
                        oldHasExpirationDateOfProductCollection1NewProduct.getProductCollection1().remove(productCollection1NewProduct);
                        oldHasExpirationDateOfProductCollection1NewProduct = em.merge(oldHasExpirationDateOfProductCollection1NewProduct);
                    }
                }
            }
            for (Product productCollection2OldProduct : productCollection2Old) {
                if (!productCollection2New.contains(productCollection2OldProduct)) {
                    productCollection2OldProduct.setBrandDiscountExcluded(null);
                    productCollection2OldProduct = em.merge(productCollection2OldProduct);
                }
            }
            for (Product productCollection2NewProduct : productCollection2New) {
                if (!productCollection2Old.contains(productCollection2NewProduct)) {
                    YesNo oldBrandDiscountExcludedOfProductCollection2NewProduct = productCollection2NewProduct.getBrandDiscountExcluded();
                    productCollection2NewProduct.setBrandDiscountExcluded(yesNo);
                    productCollection2NewProduct = em.merge(productCollection2NewProduct);
                    if (oldBrandDiscountExcludedOfProductCollection2NewProduct != null && !oldBrandDiscountExcludedOfProductCollection2NewProduct.equals(yesNo)) {
                        oldBrandDiscountExcludedOfProductCollection2NewProduct.getProductCollection2().remove(productCollection2NewProduct);
                        oldBrandDiscountExcludedOfProductCollection2NewProduct = em.merge(oldBrandDiscountExcludedOfProductCollection2NewProduct);
                    }
                }
            }
            for (ReturnTranHead returnTranHeadCollectionOldReturnTranHead : returnTranHeadCollectionOld) {
                if (!returnTranHeadCollectionNew.contains(returnTranHeadCollectionOldReturnTranHead)) {
                    returnTranHeadCollectionOldReturnTranHead.setIsTrainingMode(null);
                    returnTranHeadCollectionOldReturnTranHead = em.merge(returnTranHeadCollectionOldReturnTranHead);
                }
            }
            for (ReturnTranHead returnTranHeadCollectionNewReturnTranHead : returnTranHeadCollectionNew) {
                if (!returnTranHeadCollectionOld.contains(returnTranHeadCollectionNewReturnTranHead)) {
                    YesNo oldIsTrainingModeOfReturnTranHeadCollectionNewReturnTranHead = returnTranHeadCollectionNewReturnTranHead.getIsTrainingMode();
                    returnTranHeadCollectionNewReturnTranHead.setIsTrainingMode(yesNo);
                    returnTranHeadCollectionNewReturnTranHead = em.merge(returnTranHeadCollectionNewReturnTranHead);
                    if (oldIsTrainingModeOfReturnTranHeadCollectionNewReturnTranHead != null && !oldIsTrainingModeOfReturnTranHeadCollectionNewReturnTranHead.equals(yesNo)) {
                        oldIsTrainingModeOfReturnTranHeadCollectionNewReturnTranHead.getReturnTranHeadCollection().remove(returnTranHeadCollectionNewReturnTranHead);
                        oldIsTrainingModeOfReturnTranHeadCollectionNewReturnTranHead = em.merge(oldIsTrainingModeOfReturnTranHeadCollectionNewReturnTranHead);
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
                Character id = yesNo.getName();
                if (findYesNo(id) == null) {
                    throw new NonexistentEntityException("The yesNo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public void destroy(Character id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            YesNo yesNo;
            try {
                yesNo = em.getReference(YesNo.class, id);
                yesNo.getName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The yesNo with id " + id + " no longer exists.", enfe);
            }
            Collection<Promotion> promotionCollection = yesNo.getPromotionCollection();
            for (Promotion promotionCollectionPromotion : promotionCollection) {
                promotionCollectionPromotion.setIsEnabled(null);
                promotionCollectionPromotion = em.merge(promotionCollectionPromotion);
            }
            Collection<Promotion> promotionCollection1 = yesNo.getPromotionCollection1();
            for (Promotion promotionCollection1Promotion : promotionCollection1) {
                promotionCollection1Promotion.setIsAppliedToAll(null);
                promotionCollection1Promotion = em.merge(promotionCollection1Promotion);
            }
            Collection<ReturnTranDetail> returnTranDetailCollection = yesNo.getReturnTranDetailCollection();
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetail : returnTranDetailCollection) {
                returnTranDetailCollectionReturnTranDetail.setIsReturned(null);
                returnTranDetailCollectionReturnTranDetail = em.merge(returnTranDetailCollectionReturnTranDetail);
            }
            Collection<TranHead> tranHeadCollection = yesNo.getTranHeadCollection();
            for (TranHead tranHeadCollectionTranHead : tranHeadCollection) {
                tranHeadCollectionTranHead.setIsTrainingMode(null);
                tranHeadCollectionTranHead = em.merge(tranHeadCollectionTranHead);
            }
            Collection<Product> productCollection = yesNo.getProductCollection();
            for (Product productCollectionProduct : productCollection) {
                productCollectionProduct.setIsOrganic(null);
                productCollectionProduct = em.merge(productCollectionProduct);
            }
            Collection<Product> productCollection1 = yesNo.getProductCollection1();
            for (Product productCollection1Product : productCollection1) {
                productCollection1Product.setHasExpirationDate(null);
                productCollection1Product = em.merge(productCollection1Product);
            }
            Collection<Product> productCollection2 = yesNo.getProductCollection2();
            for (Product productCollection2Product : productCollection2) {
                productCollection2Product.setBrandDiscountExcluded(null);
                productCollection2Product = em.merge(productCollection2Product);
            }
            Collection<ReturnTranHead> returnTranHeadCollection = yesNo.getReturnTranHeadCollection();
            for (ReturnTranHead returnTranHeadCollectionReturnTranHead : returnTranHeadCollection) {
                returnTranHeadCollectionReturnTranHead.setIsTrainingMode(null);
                returnTranHeadCollectionReturnTranHead = em.merge(returnTranHeadCollectionReturnTranHead);
            }
            em.remove(yesNo);
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

    public List<YesNo> findYesNoEntities() {
        return findYesNoEntities(true, -1, -1);
    }

    public List<YesNo> findYesNoEntities(int maxResults, int firstResult) {
        return findYesNoEntities(false, maxResults, firstResult);
    }

    private List<YesNo> findYesNoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from YesNo as o");
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

    public YesNo findYesNo(Character id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(YesNo.class, id);
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }

    public int getYesNoCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from YesNo as o").getSingleResult()).intValue();
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }
}
