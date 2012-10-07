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
import jpa.entities.Brand;
import jpa.entities.Product;
import jpa.entities.Promotion;
import jpa.entities.Tax;
import jpa.entities.YesNo;
import jpa.entities.TranDetail;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import jpa.entities.ReturnTranDetail;
import jpa.entities.ProductExpirationQty;

/**
 *
 * @author achen
 */
public class ProductJpaController {

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

    public void create(Product product) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (product.getTranDetailCollection() == null) {
            product.setTranDetailCollection(new ArrayList<TranDetail>());
        }
        if (product.getReturnTranDetailCollection() == null) {
            product.setReturnTranDetailCollection(new ArrayList<ReturnTranDetail>());
        }
        if (product.getProductExpirationQtyCollection() == null) {
            product.setProductExpirationQtyCollection(new ArrayList<ProductExpirationQty>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            product.setLastUpdateDate(new Date());
            Brand brand = product.getBrand();
            if (brand != null) {
                brand = em.getReference(brand.getClass(), brand.getBrandId());
                product.setBrand(brand);
            }
            Promotion promotion = product.getPromotion();
            if (promotion != null) {
                promotion = em.getReference(promotion.getClass(), promotion.getPromotionId());
                product.setPromotion(promotion);
            }
            Tax tax = product.getTax();
            if (tax != null) {
                tax = em.getReference(tax.getClass(), tax.getTaxId());
                product.setTax(tax);
            }
            YesNo isOrganic = product.getIsOrganic();
            if (isOrganic != null) {
                isOrganic = em.getReference(isOrganic.getClass(), isOrganic.getName());
                product.setIsOrganic(isOrganic);
            }
            YesNo hasExpirationDate = product.getHasExpirationDate();
            if (hasExpirationDate != null) {
                hasExpirationDate = em.getReference(hasExpirationDate.getClass(), hasExpirationDate.getName());
                product.setHasExpirationDate(hasExpirationDate);
            }
            YesNo brandDiscountExcluded = product.getBrandDiscountExcluded();
            if (brandDiscountExcluded != null) {
                brandDiscountExcluded = em.getReference(brandDiscountExcluded.getClass(), brandDiscountExcluded.getName());
                product.setBrandDiscountExcluded(brandDiscountExcluded);
            }
            Collection<TranDetail> attachedTranDetailCollection = new ArrayList<TranDetail>();
            for (TranDetail tranDetailCollectionTranDetailToAttach : product.getTranDetailCollection()) {
                tranDetailCollectionTranDetailToAttach = em.getReference(tranDetailCollectionTranDetailToAttach.getClass(), tranDetailCollectionTranDetailToAttach.getTranDetailId());
                attachedTranDetailCollection.add(tranDetailCollectionTranDetailToAttach);
            }
            product.setTranDetailCollection(attachedTranDetailCollection);
            Collection<ReturnTranDetail> attachedReturnTranDetailCollection = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetailToAttach : product.getReturnTranDetailCollection()) {
                returnTranDetailCollectionReturnTranDetailToAttach = em.getReference(returnTranDetailCollectionReturnTranDetailToAttach.getClass(), returnTranDetailCollectionReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollection.add(returnTranDetailCollectionReturnTranDetailToAttach);
            }
            product.setReturnTranDetailCollection(attachedReturnTranDetailCollection);
            Collection<ProductExpirationQty> attachedProductExpirationQtyCollection = new ArrayList<ProductExpirationQty>();
            for (ProductExpirationQty productExpirationQtyCollectionProductExpirationQtyToAttach : product.getProductExpirationQtyCollection()) {
                productExpirationQtyCollectionProductExpirationQtyToAttach = em.getReference(productExpirationQtyCollectionProductExpirationQtyToAttach.getClass(), productExpirationQtyCollectionProductExpirationQtyToAttach.getProductExpirationQtyPK());
                attachedProductExpirationQtyCollection.add(productExpirationQtyCollectionProductExpirationQtyToAttach);
            }
            product.setProductExpirationQtyCollection(attachedProductExpirationQtyCollection);
            em.persist(product);
            if (brand != null) {
                brand.getProductCollection().add(product);
                brand = em.merge(brand);
            }
            if (promotion != null) {
                promotion.getProductCollection().add(product);
                promotion = em.merge(promotion);
            }
            /*if (tax != null) {
                tax.getProductCollection().add(product);
                tax = em.merge(tax);
            }
            if (isOrganic != null) {
                isOrganic.getProductCollection().add(product);
                isOrganic = em.merge(isOrganic);
            }
            if (hasExpirationDate != null) {
                hasExpirationDate.getProductCollection().add(product);
                hasExpirationDate = em.merge(hasExpirationDate);
            }*/
            /*if (brandDiscountExcluded != null) {
                brandDiscountExcluded.getProductCollection().add(product);
                brandDiscountExcluded = em.merge(brandDiscountExcluded);
            }*/
            /*for (TranDetail tranDetailCollectionTranDetail : product.getTranDetailCollection()) {
                Product oldIsbnOfTranDetailCollectionTranDetail = tranDetailCollectionTranDetail.getIsbn();
                tranDetailCollectionTranDetail.setIsbn(product);
                tranDetailCollectionTranDetail = em.merge(tranDetailCollectionTranDetail);
                if (oldIsbnOfTranDetailCollectionTranDetail != null) {
                    oldIsbnOfTranDetailCollectionTranDetail.getTranDetailCollection().remove(tranDetailCollectionTranDetail);
                    oldIsbnOfTranDetailCollectionTranDetail = em.merge(oldIsbnOfTranDetailCollectionTranDetail);
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetail : product.getReturnTranDetailCollection()) {
                Product oldIsbnOfReturnTranDetailCollectionReturnTranDetail = returnTranDetailCollectionReturnTranDetail.getIsbn();
                returnTranDetailCollectionReturnTranDetail.setIsbn(product);
                returnTranDetailCollectionReturnTranDetail = em.merge(returnTranDetailCollectionReturnTranDetail);
                if (oldIsbnOfReturnTranDetailCollectionReturnTranDetail != null) {
                    oldIsbnOfReturnTranDetailCollectionReturnTranDetail.getReturnTranDetailCollection().remove(returnTranDetailCollectionReturnTranDetail);
                    oldIsbnOfReturnTranDetailCollectionReturnTranDetail = em.merge(oldIsbnOfReturnTranDetailCollectionReturnTranDetail);
                }
            }
            for (ProductExpirationQty productExpirationQtyCollectionProductExpirationQty : product.getProductExpirationQtyCollection()) {
                Product oldProductOfProductExpirationQtyCollectionProductExpirationQty = productExpirationQtyCollectionProductExpirationQty.getProduct();
                productExpirationQtyCollectionProductExpirationQty.setProduct(product);
                productExpirationQtyCollectionProductExpirationQty = em.merge(productExpirationQtyCollectionProductExpirationQty);
                if (oldProductOfProductExpirationQtyCollectionProductExpirationQty != null) {
                    oldProductOfProductExpirationQtyCollectionProductExpirationQty.getProductExpirationQtyCollection().remove(productExpirationQtyCollectionProductExpirationQty);
                    oldProductOfProductExpirationQtyCollectionProductExpirationQty = em.merge(oldProductOfProductExpirationQtyCollectionProductExpirationQty);
                }
            }*/
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProduct(product.getIsbn()) != null) {
                throw new PreexistingEntityException("Product " + product + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public void edit(Product product) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            product.setLastUpdateDate(new Date());

            em = getEntityManager();
            Product persistentProduct = em.find(Product.class, product.getIsbn());
            Brand brandOld = persistentProduct.getBrand();
            Brand brandNew = product.getBrand();
            Promotion promotionOld = persistentProduct.getPromotion();
            Promotion promotionNew = product.getPromotion();
            Tax taxOld = persistentProduct.getTax();
            Tax taxNew = product.getTax();
            YesNo isOrganicOld = persistentProduct.getIsOrganic();
            YesNo isOrganicNew = product.getIsOrganic();
            YesNo hasExpirationDateOld = persistentProduct.getHasExpirationDate();
            YesNo hasExpirationDateNew = product.getHasExpirationDate();
            YesNo brandDiscountExcludedOld = persistentProduct.getBrandDiscountExcluded();
            YesNo brandDiscountExcludedNew = product.getBrandDiscountExcluded();
            Collection<TranDetail> tranDetailCollectionOld = persistentProduct.getTranDetailCollection();
            Collection<TranDetail> tranDetailCollectionNew = tranDetailCollectionOld;//product.getTranDetailCollection();
            Collection<ReturnTranDetail> returnTranDetailCollectionOld = persistentProduct.getReturnTranDetailCollection();
            Collection<ReturnTranDetail> returnTranDetailCollectionNew = returnTranDetailCollectionOld;//product.getReturnTranDetailCollection();
            Collection<ProductExpirationQty> productExpirationQtyCollectionOld = persistentProduct.getProductExpirationQtyCollection();
            Collection<ProductExpirationQty> productExpirationQtyCollectionNew = productExpirationQtyCollectionOld;//product.getProductExpirationQtyCollection();
            List<String> illegalOrphanMessages = null;
            for (TranDetail tranDetailCollectionOldTranDetail : tranDetailCollectionOld) {
                if (!tranDetailCollectionNew.contains(tranDetailCollectionOldTranDetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TranDetail " + tranDetailCollectionOldTranDetail + " since its isbn field is not nullable.");
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionOldReturnTranDetail : returnTranDetailCollectionOld) {
                if (!returnTranDetailCollectionNew.contains(returnTranDetailCollectionOldReturnTranDetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReturnTranDetail " + returnTranDetailCollectionOldReturnTranDetail + " since its isbn field is not nullable.");
                }
            }
            for (ProductExpirationQty productExpirationQtyCollectionOldProductExpirationQty : productExpirationQtyCollectionOld) {
                if (!productExpirationQtyCollectionNew.contains(productExpirationQtyCollectionOldProductExpirationQty)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductExpirationQty " + productExpirationQtyCollectionOldProductExpirationQty + " since its product field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (brandNew != null) {
                brandNew = em.getReference(brandNew.getClass(), brandNew.getBrandId());
                product.setBrand(brandNew);
            }
            if (promotionNew != null) {
                promotionNew = em.getReference(promotionNew.getClass(), promotionNew.getPromotionId());
                product.setPromotion(promotionNew);
            }
            if (taxNew != null) {
                taxNew = em.getReference(taxNew.getClass(), taxNew.getTaxId());
                product.setTax(taxNew);
            }
            if (isOrganicNew != null) {
                isOrganicNew = em.getReference(isOrganicNew.getClass(), isOrganicNew.getName());
                product.setIsOrganic(isOrganicNew);
            }
            if (hasExpirationDateNew != null) {
                hasExpirationDateNew = em.getReference(hasExpirationDateNew.getClass(), hasExpirationDateNew.getName());
                product.setHasExpirationDate(hasExpirationDateNew);
            }
            if (brandDiscountExcludedNew != null) {
                brandDiscountExcludedNew = em.getReference(brandDiscountExcludedNew.getClass(), brandDiscountExcludedNew.getName());
                product.setBrandDiscountExcluded(brandDiscountExcludedNew);
            }
            Collection<TranDetail> attachedTranDetailCollectionNew = new ArrayList<TranDetail>();
            for (TranDetail tranDetailCollectionNewTranDetailToAttach : tranDetailCollectionNew) {
                tranDetailCollectionNewTranDetailToAttach = em.getReference(tranDetailCollectionNewTranDetailToAttach.getClass(), tranDetailCollectionNewTranDetailToAttach.getTranDetailId());
                attachedTranDetailCollectionNew.add(tranDetailCollectionNewTranDetailToAttach);
            }
            tranDetailCollectionNew = attachedTranDetailCollectionNew;
            product.setTranDetailCollection(tranDetailCollectionNew);
            Collection<ReturnTranDetail> attachedReturnTranDetailCollectionNew = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollectionNewReturnTranDetailToAttach : returnTranDetailCollectionNew) {
                returnTranDetailCollectionNewReturnTranDetailToAttach = em.getReference(returnTranDetailCollectionNewReturnTranDetailToAttach.getClass(), returnTranDetailCollectionNewReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollectionNew.add(returnTranDetailCollectionNewReturnTranDetailToAttach);
            }
            returnTranDetailCollectionNew = attachedReturnTranDetailCollectionNew;
            product.setReturnTranDetailCollection(returnTranDetailCollectionNew);
            Collection<ProductExpirationQty> attachedProductExpirationQtyCollectionNew = new ArrayList<ProductExpirationQty>();
            for (ProductExpirationQty productExpirationQtyCollectionNewProductExpirationQtyToAttach : productExpirationQtyCollectionNew) {
                productExpirationQtyCollectionNewProductExpirationQtyToAttach = em.getReference(productExpirationQtyCollectionNewProductExpirationQtyToAttach.getClass(), productExpirationQtyCollectionNewProductExpirationQtyToAttach.getProductExpirationQtyPK());
                attachedProductExpirationQtyCollectionNew.add(productExpirationQtyCollectionNewProductExpirationQtyToAttach);
            }
            productExpirationQtyCollectionNew = attachedProductExpirationQtyCollectionNew;
            product.setProductExpirationQtyCollection(productExpirationQtyCollectionNew);
            product = em.merge(product);
            if (brandOld != null && !brandOld.equals(brandNew)) {
                brandOld.getProductCollection().remove(product);
                brandOld = em.merge(brandOld);
            }
            if (brandNew != null && !brandNew.equals(brandOld)) {
                brandNew.getProductCollection().add(product);
                brandNew = em.merge(brandNew);
            }
            if (promotionOld != null && !promotionOld.equals(promotionNew)) {
                promotionOld.getProductCollection().remove(product);
                promotionOld = em.merge(promotionOld);
            }
            if (promotionNew != null && !promotionNew.equals(promotionOld)) {
                promotionNew.getProductCollection().add(product);
                promotionNew = em.merge(promotionNew);
            }
            /*if (taxOld != null && !taxOld.equals(taxNew)) {
                taxOld.getProductCollection().remove(product);
                taxOld = em.merge(taxOld);
            }
            if (taxNew != null && !taxNew.equals(taxOld)) {
                taxNew.getProductCollection().add(product);
                taxNew = em.merge(taxNew);
            }
            if (isOrganicOld != null && !isOrganicOld.equals(isOrganicNew)) {
                isOrganicOld.getProductCollection().remove(product);
                isOrganicOld = em.merge(isOrganicOld);
            }
            if (isOrganicNew != null && !isOrganicNew.equals(isOrganicOld)) {
                isOrganicNew.getProductCollection().add(product);
                isOrganicNew = em.merge(isOrganicNew);
            }
            if (hasExpirationDateOld != null && !hasExpirationDateOld.equals(hasExpirationDateNew)) {
                hasExpirationDateOld.getProductCollection().remove(product);
                hasExpirationDateOld = em.merge(hasExpirationDateOld);
            }
            if (hasExpirationDateNew != null && !hasExpirationDateNew.equals(hasExpirationDateOld)) {
                hasExpirationDateNew.getProductCollection().add(product);
                hasExpirationDateNew = em.merge(hasExpirationDateNew);
            }
            if (brandDiscountExcludedOld != null && !brandDiscountExcludedOld.equals(brandDiscountExcludedNew)) {
                brandDiscountExcludedOld.getProductCollection().remove(product);
                brandDiscountExcludedOld = em.merge(brandDiscountExcludedOld);
            }
            if (brandDiscountExcludedNew != null && !brandDiscountExcludedNew.equals(brandDiscountExcludedOld)) {
                brandDiscountExcludedNew.getProductCollection().add(product);
                brandDiscountExcludedNew = em.merge(brandDiscountExcludedNew);
            }*/
            /*for (TranDetail tranDetailCollectionNewTranDetail : tranDetailCollectionNew) {
                if (!tranDetailCollectionOld.contains(tranDetailCollectionNewTranDetail)) {
                    Product oldIsbnOfTranDetailCollectionNewTranDetail = tranDetailCollectionNewTranDetail.getIsbn();
                    tranDetailCollectionNewTranDetail.setIsbn(product);
                    tranDetailCollectionNewTranDetail = em.merge(tranDetailCollectionNewTranDetail);
                    if (oldIsbnOfTranDetailCollectionNewTranDetail != null && !oldIsbnOfTranDetailCollectionNewTranDetail.equals(product)) {
                        oldIsbnOfTranDetailCollectionNewTranDetail.getTranDetailCollection().remove(tranDetailCollectionNewTranDetail);
                        oldIsbnOfTranDetailCollectionNewTranDetail = em.merge(oldIsbnOfTranDetailCollectionNewTranDetail);
                    }
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionNewReturnTranDetail : returnTranDetailCollectionNew) {
                if (!returnTranDetailCollectionOld.contains(returnTranDetailCollectionNewReturnTranDetail)) {
                    Product oldIsbnOfReturnTranDetailCollectionNewReturnTranDetail = returnTranDetailCollectionNewReturnTranDetail.getIsbn();
                    returnTranDetailCollectionNewReturnTranDetail.setIsbn(product);
                    returnTranDetailCollectionNewReturnTranDetail = em.merge(returnTranDetailCollectionNewReturnTranDetail);
                    if (oldIsbnOfReturnTranDetailCollectionNewReturnTranDetail != null && !oldIsbnOfReturnTranDetailCollectionNewReturnTranDetail.equals(product)) {
                        oldIsbnOfReturnTranDetailCollectionNewReturnTranDetail.getReturnTranDetailCollection().remove(returnTranDetailCollectionNewReturnTranDetail);
                        oldIsbnOfReturnTranDetailCollectionNewReturnTranDetail = em.merge(oldIsbnOfReturnTranDetailCollectionNewReturnTranDetail);
                    }
                }
            }
            for (ProductExpirationQty productExpirationQtyCollectionNewProductExpirationQty : productExpirationQtyCollectionNew) {
                if (!productExpirationQtyCollectionOld.contains(productExpirationQtyCollectionNewProductExpirationQty)) {
                    Product oldProductOfProductExpirationQtyCollectionNewProductExpirationQty = productExpirationQtyCollectionNewProductExpirationQty.getProduct();
                    productExpirationQtyCollectionNewProductExpirationQty.setProduct(product);
                    productExpirationQtyCollectionNewProductExpirationQty = em.merge(productExpirationQtyCollectionNewProductExpirationQty);
                    if (oldProductOfProductExpirationQtyCollectionNewProductExpirationQty != null && !oldProductOfProductExpirationQtyCollectionNewProductExpirationQty.equals(product)) {
                        oldProductOfProductExpirationQtyCollectionNewProductExpirationQty.getProductExpirationQtyCollection().remove(productExpirationQtyCollectionNewProductExpirationQty);
                        oldProductOfProductExpirationQtyCollectionNewProductExpirationQty = em.merge(oldProductOfProductExpirationQtyCollectionNewProductExpirationQty);
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
                String id = product.getIsbn();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getIsbn();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TranDetail> tranDetailCollectionOrphanCheck = product.getTranDetailCollection();
            for (TranDetail tranDetailCollectionOrphanCheckTranDetail : tranDetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the TranDetail " + tranDetailCollectionOrphanCheckTranDetail + " in its tranDetailCollection field has a non-nullable isbn field.");
            }
            Collection<ReturnTranDetail> returnTranDetailCollectionOrphanCheck = product.getReturnTranDetailCollection();
            for (ReturnTranDetail returnTranDetailCollectionOrphanCheckReturnTranDetail : returnTranDetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the ReturnTranDetail " + returnTranDetailCollectionOrphanCheckReturnTranDetail + " in its returnTranDetailCollection field has a non-nullable isbn field.");
            }
            Collection<ProductExpirationQty> productExpirationQtyCollectionOrphanCheck = product.getProductExpirationQtyCollection();
            for (ProductExpirationQty productExpirationQtyCollectionOrphanCheckProductExpirationQty : productExpirationQtyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the ProductExpirationQty " + productExpirationQtyCollectionOrphanCheckProductExpirationQty + " in its productExpirationQtyCollection field has a non-nullable product field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Brand brand = product.getBrand();
            if (brand != null) {
                brand.getProductCollection().remove(product);
                brand = em.merge(brand);
            }
            Promotion promotion = product.getPromotion();
            if (promotion != null) {
                promotion.getProductCollection().remove(product);
                promotion = em.merge(promotion);
            }
            Tax tax = product.getTax();
            if (tax != null) {
                tax.getProductCollection().remove(product);
                tax = em.merge(tax);
            }
            YesNo isOrganic = product.getIsOrganic();
            if (isOrganic != null) {
                isOrganic.getProductCollection().remove(product);
                isOrganic = em.merge(isOrganic);
            }
            YesNo hasExpirationDate = product.getHasExpirationDate();
            if (hasExpirationDate != null) {
                hasExpirationDate.getProductCollection().remove(product);
                hasExpirationDate = em.merge(hasExpirationDate);
            }
            YesNo brandDiscountExcluded = product.getBrandDiscountExcluded();
            if (brandDiscountExcluded != null) {
                brandDiscountExcluded.getProductCollection().remove(product);
                brandDiscountExcluded = em.merge(brandDiscountExcluded);
            }
            em.remove(product);
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

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Product as o");
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

    public Product findProduct(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Product as o").getSingleResult()).intValue();
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }
}
