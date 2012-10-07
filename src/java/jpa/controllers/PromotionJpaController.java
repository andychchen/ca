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
import jpa.entities.Discount;
import jpa.entities.Promotion;
import jpa.entities.WeekDays;
import jpa.entities.YesNo;
import jpa.entities.TranDetail;
import java.util.ArrayList;
import java.util.Collection;
import jpa.entities.ReturnTranDetail;
import jpa.entities.Product;
import jpa.entities.Brand;

/**
 *
 * @author achen
 */
public class PromotionJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Promotion promotion) throws RollbackFailureException, Exception {
        if (promotion.getTranDetailCollection() == null) {
            promotion.setTranDetailCollection(new ArrayList<TranDetail>());
        }
        if (promotion.getReturnTranDetailCollection() == null) {
            promotion.setReturnTranDetailCollection(new ArrayList<ReturnTranDetail>());
        }
        if (promotion.getProductCollection() == null) {
            promotion.setProductCollection(new ArrayList<Product>());
        }
        if (promotion.getBrandCollection() == null) {
            promotion.setBrandCollection(new ArrayList<Brand>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Discount discount = promotion.getDiscount();
            if (discount != null) {
                discount = em.getReference(discount.getClass(), discount.getDiscountId());
                promotion.setDiscount(discount);
            }
            WeekDays weeklyDay = promotion.getWeeklyDay();
            if (weeklyDay != null) {
                weeklyDay = em.getReference(weeklyDay.getClass(), weeklyDay.getDay());
                promotion.setWeeklyDay(weeklyDay);
            }
            YesNo isEnabled = promotion.getIsEnabled();
            if (isEnabled != null) {
                isEnabled = em.getReference(isEnabled.getClass(), isEnabled.getName());
                promotion.setIsEnabled(isEnabled);
            }
            YesNo isAppliedToAll = promotion.getIsAppliedToAll();
            if (isAppliedToAll != null) {
                isAppliedToAll = em.getReference(isAppliedToAll.getClass(), isAppliedToAll.getName());
                promotion.setIsAppliedToAll(isAppliedToAll);
            }
            Collection<TranDetail> attachedTranDetailCollection = new ArrayList<TranDetail>();
            for (TranDetail tranDetailCollectionTranDetailToAttach : promotion.getTranDetailCollection()) {
                tranDetailCollectionTranDetailToAttach = em.getReference(tranDetailCollectionTranDetailToAttach.getClass(), tranDetailCollectionTranDetailToAttach.getTranDetailId());
                attachedTranDetailCollection.add(tranDetailCollectionTranDetailToAttach);
            }
            promotion.setTranDetailCollection(attachedTranDetailCollection);
            Collection<ReturnTranDetail> attachedReturnTranDetailCollection = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetailToAttach : promotion.getReturnTranDetailCollection()) {
                returnTranDetailCollectionReturnTranDetailToAttach = em.getReference(returnTranDetailCollectionReturnTranDetailToAttach.getClass(), returnTranDetailCollectionReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollection.add(returnTranDetailCollectionReturnTranDetailToAttach);
            }
            promotion.setReturnTranDetailCollection(attachedReturnTranDetailCollection);
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : promotion.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getIsbn());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            promotion.setProductCollection(attachedProductCollection);
            Collection<Brand> attachedBrandCollection = new ArrayList<Brand>();
            for (Brand brandCollectionBrandToAttach : promotion.getBrandCollection()) {
                brandCollectionBrandToAttach = em.getReference(brandCollectionBrandToAttach.getClass(), brandCollectionBrandToAttach.getBrandId());
                attachedBrandCollection.add(brandCollectionBrandToAttach);
            }
            promotion.setBrandCollection(attachedBrandCollection);
            em.persist(promotion);

            if (discount != null) {
                discount.getPromotionCollection().add(promotion);
                discount = em.merge(discount);
            }
            if (weeklyDay != null) {
                weeklyDay.getPromotionCollection().add(promotion);
                weeklyDay = em.merge(weeklyDay);
            }
            /*if (isEnabled != null) {
                isEnabled.getPromotionCollection().add(promotion);
                isEnabled = em.merge(isEnabled);
            }
            if (isAppliedToAll != null) {
                isAppliedToAll.getPromotionCollection().add(promotion);
                isAppliedToAll = em.merge(isAppliedToAll);
            }
            
            for (TranDetail tranDetailCollectionTranDetail : promotion.getTranDetailCollection()) {
                Promotion oldPromotionOfTranDetailCollectionTranDetail = tranDetailCollectionTranDetail.getPromotion();
                tranDetailCollectionTranDetail.setPromotion(promotion);
                tranDetailCollectionTranDetail = em.merge(tranDetailCollectionTranDetail);
                if (oldPromotionOfTranDetailCollectionTranDetail != null) {
                    oldPromotionOfTranDetailCollectionTranDetail.getTranDetailCollection().remove(tranDetailCollectionTranDetail);
                    oldPromotionOfTranDetailCollectionTranDetail = em.merge(oldPromotionOfTranDetailCollectionTranDetail);
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetail : promotion.getReturnTranDetailCollection()) {
                Promotion oldPromotionOfReturnTranDetailCollectionReturnTranDetail = returnTranDetailCollectionReturnTranDetail.getPromotion();
                returnTranDetailCollectionReturnTranDetail.setPromotion(promotion);
                returnTranDetailCollectionReturnTranDetail = em.merge(returnTranDetailCollectionReturnTranDetail);
                if (oldPromotionOfReturnTranDetailCollectionReturnTranDetail != null) {
                    oldPromotionOfReturnTranDetailCollectionReturnTranDetail.getReturnTranDetailCollection().remove(returnTranDetailCollectionReturnTranDetail);
                    oldPromotionOfReturnTranDetailCollectionReturnTranDetail = em.merge(oldPromotionOfReturnTranDetailCollectionReturnTranDetail);
                }
            }
            for (Product productCollectionProduct : promotion.getProductCollection()) {
                Promotion oldPromotionOfProductCollectionProduct = productCollectionProduct.getPromotion();
                productCollectionProduct.setPromotion(promotion);
                productCollectionProduct = em.merge(productCollectionProduct);
                if (oldPromotionOfProductCollectionProduct != null) {
                    oldPromotionOfProductCollectionProduct.getProductCollection().remove(productCollectionProduct);
                    oldPromotionOfProductCollectionProduct = em.merge(oldPromotionOfProductCollectionProduct);
                }
            }
            for (Brand brandCollectionBrand : promotion.getBrandCollection()) {
                Promotion oldPromotionOfBrandCollectionBrand = brandCollectionBrand.getPromotion();
                brandCollectionBrand.setPromotion(promotion);
                brandCollectionBrand = em.merge(brandCollectionBrand);
                if (oldPromotionOfBrandCollectionBrand != null) {
                    oldPromotionOfBrandCollectionBrand.getBrandCollection().remove(brandCollectionBrand);
                    oldPromotionOfBrandCollectionBrand = em.merge(oldPromotionOfBrandCollectionBrand);
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

    public void edit(Promotion promotion) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Promotion persistentPromotion = em.find(Promotion.class, promotion.getPromotionId());
            Discount discountOld = persistentPromotion.getDiscount();
            Discount discountNew = promotion.getDiscount();
            WeekDays weeklyDayOld = persistentPromotion.getWeeklyDay();
            WeekDays weeklyDayNew = promotion.getWeeklyDay();
            YesNo isEnabledOld = persistentPromotion.getIsEnabled();
            YesNo isEnabledNew = promotion.getIsEnabled();
            YesNo isAppliedToAllOld = persistentPromotion.getIsAppliedToAll();
            YesNo isAppliedToAllNew = promotion.getIsAppliedToAll();
            Collection<TranDetail> tranDetailCollectionOld = persistentPromotion.getTranDetailCollection();
            Collection<TranDetail> tranDetailCollectionNew = promotion.getTranDetailCollection();
            Collection<ReturnTranDetail> returnTranDetailCollectionOld = persistentPromotion.getReturnTranDetailCollection();
            Collection<ReturnTranDetail> returnTranDetailCollectionNew = promotion.getReturnTranDetailCollection();
            Collection<Product> productCollectionOld = persistentPromotion.getProductCollection();
            Collection<Product> productCollectionNew = promotion.getProductCollection();
            Collection<Brand> brandCollectionOld = persistentPromotion.getBrandCollection();
            Collection<Brand> brandCollectionNew = promotion.getBrandCollection();
            if (discountNew != null) {
                discountNew = em.getReference(discountNew.getClass(), discountNew.getDiscountId());
                promotion.setDiscount(discountNew);
            }
            if (weeklyDayNew != null) {
                weeklyDayNew = em.getReference(weeklyDayNew.getClass(), weeklyDayNew.getDay());
                promotion.setWeeklyDay(weeklyDayNew);
            }
            if (isEnabledNew != null) {
                isEnabledNew = em.getReference(isEnabledNew.getClass(), isEnabledNew.getName());
                promotion.setIsEnabled(isEnabledNew);
            }
            if (isAppliedToAllNew != null) {
                isAppliedToAllNew = em.getReference(isAppliedToAllNew.getClass(), isAppliedToAllNew.getName());
                promotion.setIsAppliedToAll(isAppliedToAllNew);
            }
            Collection<TranDetail> attachedTranDetailCollectionNew = new ArrayList<TranDetail>();
            for (TranDetail tranDetailCollectionNewTranDetailToAttach : tranDetailCollectionNew) {
                tranDetailCollectionNewTranDetailToAttach = em.getReference(tranDetailCollectionNewTranDetailToAttach.getClass(), tranDetailCollectionNewTranDetailToAttach.getTranDetailId());
                attachedTranDetailCollectionNew.add(tranDetailCollectionNewTranDetailToAttach);
            }
            tranDetailCollectionNew = attachedTranDetailCollectionNew;
            promotion.setTranDetailCollection(tranDetailCollectionNew);
            Collection<ReturnTranDetail> attachedReturnTranDetailCollectionNew = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollectionNewReturnTranDetailToAttach : returnTranDetailCollectionNew) {
                returnTranDetailCollectionNewReturnTranDetailToAttach = em.getReference(returnTranDetailCollectionNewReturnTranDetailToAttach.getClass(), returnTranDetailCollectionNewReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollectionNew.add(returnTranDetailCollectionNewReturnTranDetailToAttach);
            }
            returnTranDetailCollectionNew = attachedReturnTranDetailCollectionNew;
            promotion.setReturnTranDetailCollection(returnTranDetailCollectionNew);
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getIsbn());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            promotion.setProductCollection(productCollectionNew);
            Collection<Brand> attachedBrandCollectionNew = new ArrayList<Brand>();
            for (Brand brandCollectionNewBrandToAttach : brandCollectionNew) {
                brandCollectionNewBrandToAttach = em.getReference(brandCollectionNewBrandToAttach.getClass(), brandCollectionNewBrandToAttach.getBrandId());
                attachedBrandCollectionNew.add(brandCollectionNewBrandToAttach);
            }
            brandCollectionNew = attachedBrandCollectionNew;
            promotion.setBrandCollection(brandCollectionNew);
            promotion = em.merge(promotion);
            if (discountOld != null && !discountOld.equals(discountNew)) {
                discountOld.getPromotionCollection().remove(promotion);
                discountOld = em.merge(discountOld);
            }
            if (discountNew != null && !discountNew.equals(discountOld)) {
                discountNew.getPromotionCollection().add(promotion);
                discountNew = em.merge(discountNew);
            }
            if (weeklyDayOld != null && !weeklyDayOld.equals(weeklyDayNew)) {
                weeklyDayOld.getPromotionCollection().remove(promotion);
                weeklyDayOld = em.merge(weeklyDayOld);
            }
            if (weeklyDayNew != null && !weeklyDayNew.equals(weeklyDayOld)) {
                weeklyDayNew.getPromotionCollection().add(promotion);
                weeklyDayNew = em.merge(weeklyDayNew);
            }
            if (isEnabledOld != null && !isEnabledOld.equals(isEnabledNew)) {
                isEnabledOld.getPromotionCollection().remove(promotion);
                isEnabledOld = em.merge(isEnabledOld);
            }
            if (isEnabledNew != null && !isEnabledNew.equals(isEnabledOld)) {
                isEnabledNew.getPromotionCollection().add(promotion);
                isEnabledNew = em.merge(isEnabledNew);
            }
            if (isAppliedToAllOld != null && !isAppliedToAllOld.equals(isAppliedToAllNew)) {
                isAppliedToAllOld.getPromotionCollection().remove(promotion);
                isAppliedToAllOld = em.merge(isAppliedToAllOld);
            }
            if (isAppliedToAllNew != null && !isAppliedToAllNew.equals(isAppliedToAllOld)) {
                isAppliedToAllNew.getPromotionCollection().add(promotion);
                isAppliedToAllNew = em.merge(isAppliedToAllNew);
            }
            /*for (TranDetail tranDetailCollectionOldTranDetail : tranDetailCollectionOld) {
                if (!tranDetailCollectionNew.contains(tranDetailCollectionOldTranDetail)) {
                    tranDetailCollectionOldTranDetail.setPromotion(null);
                    tranDetailCollectionOldTranDetail = em.merge(tranDetailCollectionOldTranDetail);
                }
            }
            for (TranDetail tranDetailCollectionNewTranDetail : tranDetailCollectionNew) {
                if (!tranDetailCollectionOld.contains(tranDetailCollectionNewTranDetail)) {
                    Promotion oldPromotionOfTranDetailCollectionNewTranDetail = tranDetailCollectionNewTranDetail.getPromotion();
                    tranDetailCollectionNewTranDetail.setPromotion(promotion);
                    tranDetailCollectionNewTranDetail = em.merge(tranDetailCollectionNewTranDetail);
                    if (oldPromotionOfTranDetailCollectionNewTranDetail != null && !oldPromotionOfTranDetailCollectionNewTranDetail.equals(promotion)) {
                        oldPromotionOfTranDetailCollectionNewTranDetail.getTranDetailCollection().remove(tranDetailCollectionNewTranDetail);
                        oldPromotionOfTranDetailCollectionNewTranDetail = em.merge(oldPromotionOfTranDetailCollectionNewTranDetail);
                    }
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionOldReturnTranDetail : returnTranDetailCollectionOld) {
                if (!returnTranDetailCollectionNew.contains(returnTranDetailCollectionOldReturnTranDetail)) {
                    returnTranDetailCollectionOldReturnTranDetail.setPromotion(null);
                    returnTranDetailCollectionOldReturnTranDetail = em.merge(returnTranDetailCollectionOldReturnTranDetail);
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionNewReturnTranDetail : returnTranDetailCollectionNew) {
                if (!returnTranDetailCollectionOld.contains(returnTranDetailCollectionNewReturnTranDetail)) {
                    Promotion oldPromotionOfReturnTranDetailCollectionNewReturnTranDetail = returnTranDetailCollectionNewReturnTranDetail.getPromotion();
                    returnTranDetailCollectionNewReturnTranDetail.setPromotion(promotion);
                    returnTranDetailCollectionNewReturnTranDetail = em.merge(returnTranDetailCollectionNewReturnTranDetail);
                    if (oldPromotionOfReturnTranDetailCollectionNewReturnTranDetail != null && !oldPromotionOfReturnTranDetailCollectionNewReturnTranDetail.equals(promotion)) {
                        oldPromotionOfReturnTranDetailCollectionNewReturnTranDetail.getReturnTranDetailCollection().remove(returnTranDetailCollectionNewReturnTranDetail);
                        oldPromotionOfReturnTranDetailCollectionNewReturnTranDetail = em.merge(oldPromotionOfReturnTranDetailCollectionNewReturnTranDetail);
                    }
                }
            }
            for (Product productCollectionOldProduct : productCollectionOld) {
                if (!productCollectionNew.contains(productCollectionOldProduct)) {
                    productCollectionOldProduct.setPromotion(null);
                    productCollectionOldProduct = em.merge(productCollectionOldProduct);
                }
            }
            for (Product productCollectionNewProduct : productCollectionNew) {
                if (!productCollectionOld.contains(productCollectionNewProduct)) {
                    Promotion oldPromotionOfProductCollectionNewProduct = productCollectionNewProduct.getPromotion();
                    productCollectionNewProduct.setPromotion(promotion);
                    productCollectionNewProduct = em.merge(productCollectionNewProduct);
                    if (oldPromotionOfProductCollectionNewProduct != null && !oldPromotionOfProductCollectionNewProduct.equals(promotion)) {
                        oldPromotionOfProductCollectionNewProduct.getProductCollection().remove(productCollectionNewProduct);
                        oldPromotionOfProductCollectionNewProduct = em.merge(oldPromotionOfProductCollectionNewProduct);
                    }
                }
            }
            for (Brand brandCollectionOldBrand : brandCollectionOld) {
                if (!brandCollectionNew.contains(brandCollectionOldBrand)) {
                    brandCollectionOldBrand.setPromotion(null);
                    brandCollectionOldBrand = em.merge(brandCollectionOldBrand);
                }
            }
            for (Brand brandCollectionNewBrand : brandCollectionNew) {
                if (!brandCollectionOld.contains(brandCollectionNewBrand)) {
                    Promotion oldPromotionOfBrandCollectionNewBrand = brandCollectionNewBrand.getPromotion();
                    brandCollectionNewBrand.setPromotion(promotion);
                    brandCollectionNewBrand = em.merge(brandCollectionNewBrand);
                    if (oldPromotionOfBrandCollectionNewBrand != null && !oldPromotionOfBrandCollectionNewBrand.equals(promotion)) {
                        oldPromotionOfBrandCollectionNewBrand.getBrandCollection().remove(brandCollectionNewBrand);
                        oldPromotionOfBrandCollectionNewBrand = em.merge(oldPromotionOfBrandCollectionNewBrand);
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
                Integer id = promotion.getPromotionId();
                if (findPromotion(id) == null) {
                    throw new NonexistentEntityException("The promotion with id " + id + " no longer exists.");
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
            Promotion promotion;
            try {
                promotion = em.getReference(Promotion.class, id);
                promotion.getPromotionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The promotion with id " + id + " no longer exists.", enfe);
            }
            Discount discount = promotion.getDiscount();
            if (discount != null) {
                discount.getPromotionCollection().remove(promotion);
                discount = em.merge(discount);
            }
            WeekDays weeklyDay = promotion.getWeeklyDay();
            if (weeklyDay != null) {
                weeklyDay.getPromotionCollection().remove(promotion);
                weeklyDay = em.merge(weeklyDay);
            }
            YesNo isEnabled = promotion.getIsEnabled();
            if (isEnabled != null) {
                isEnabled.getPromotionCollection().remove(promotion);
                isEnabled = em.merge(isEnabled);
            }
            YesNo isAppliedToAll = promotion.getIsAppliedToAll();
            if (isAppliedToAll != null) {
                isAppliedToAll.getPromotionCollection().remove(promotion);
                isAppliedToAll = em.merge(isAppliedToAll);
            }
            Collection<TranDetail> tranDetailCollection = promotion.getTranDetailCollection();
            for (TranDetail tranDetailCollectionTranDetail : tranDetailCollection) {
                tranDetailCollectionTranDetail.setPromotion(null);
                tranDetailCollectionTranDetail = em.merge(tranDetailCollectionTranDetail);
            }
            Collection<ReturnTranDetail> returnTranDetailCollection = promotion.getReturnTranDetailCollection();
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetail : returnTranDetailCollection) {
                returnTranDetailCollectionReturnTranDetail.setPromotion(null);
                returnTranDetailCollectionReturnTranDetail = em.merge(returnTranDetailCollectionReturnTranDetail);
            }
            Collection<Product> productCollection = promotion.getProductCollection();
            for (Product productCollectionProduct : productCollection) {
                productCollectionProduct.setPromotion(null);
                productCollectionProduct = em.merge(productCollectionProduct);
            }
            Collection<Brand> brandCollection = promotion.getBrandCollection();
            for (Brand brandCollectionBrand : brandCollection) {
                brandCollectionBrand.setPromotion(null);
                brandCollectionBrand = em.merge(brandCollectionBrand);
            }
            em.remove(promotion);
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
    public List<Promotion> findAppliedToAllPromotions() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Promotion.findAppliedToAll");
            return q.getResultList();
        } finally {
            em.close();
        }

    }

    public List<Promotion> findPromotionEntities() {
        return findPromotionEntities(true, -1, -1);
    }

    public List<Promotion> findPromotionEntities(int maxResults, int firstResult) {
        return findPromotionEntities(false, maxResults, firstResult);
    }

    private List<Promotion> findPromotionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Promotion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Promotion findPromotion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Promotion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPromotionCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Promotion as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
