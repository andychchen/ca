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
import jpa.entities.Brand;
import jpa.entities.Promotion;
import jpa.entities.Product;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author achen
 */
public class BrandJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Brand brand) throws RollbackFailureException, Exception {
        if (brand.getProductCollection() == null) {
            brand.setProductCollection(new ArrayList<Product>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Promotion promotion = brand.getPromotion();
            if (promotion != null) {
                promotion = em.getReference(promotion.getClass(), promotion.getPromotionId());
                brand.setPromotion(promotion);
            }
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : brand.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getIsbn());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            brand.setProductCollection(attachedProductCollection);
            em.persist(brand);
            /*if (promotion != null) {
                promotion.getBrandCollection().add(brand);
                promotion = em.merge(promotion);
            }
            for (Product productCollectionProduct : brand.getProductCollection()) {
                Brand oldBrandOfProductCollectionProduct = productCollectionProduct.getBrand();
                productCollectionProduct.setBrand(brand);
                productCollectionProduct = em.merge(productCollectionProduct);
                if (oldBrandOfProductCollectionProduct != null) {
                    oldBrandOfProductCollectionProduct.getProductCollection().remove(productCollectionProduct);
                    oldBrandOfProductCollectionProduct = em.merge(oldBrandOfProductCollectionProduct);
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

    public void edit(Brand brand) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Brand persistentBrand = em.find(Brand.class, brand.getBrandId());
            Promotion promotionOld = persistentBrand.getPromotion();
            Promotion promotionNew = brand.getPromotion();
            Collection<Product> productCollectionOld = persistentBrand.getProductCollection();
            Collection<Product> productCollectionNew = brand.getProductCollection();
            if (promotionNew != null) {
                promotionNew = em.getReference(promotionNew.getClass(), promotionNew.getPromotionId());
                brand.setPromotion(promotionNew);
            }
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getIsbn());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            brand.setProductCollection(productCollectionNew);
            brand = em.merge(brand);
            if (promotionOld != null && !promotionOld.equals(promotionNew)) {
                promotionOld.getBrandCollection().remove(brand);
                promotionOld = em.merge(promotionOld);
            }
            if (promotionNew != null && !promotionNew.equals(promotionOld)) {
                promotionNew.getBrandCollection().add(brand);
                promotionNew = em.merge(promotionNew);
            }
            /*for (Product productCollectionOldProduct : productCollectionOld) {
                if (!productCollectionNew.contains(productCollectionOldProduct)) {
                    productCollectionOldProduct.setBrand(null);
                    productCollectionOldProduct = em.merge(productCollectionOldProduct);
                }
            }
            for (Product productCollectionNewProduct : productCollectionNew) {
                if (!productCollectionOld.contains(productCollectionNewProduct)) {
                    Brand oldBrandOfProductCollectionNewProduct = productCollectionNewProduct.getBrand();
                    productCollectionNewProduct.setBrand(brand);
                    productCollectionNewProduct = em.merge(productCollectionNewProduct);
                    if (oldBrandOfProductCollectionNewProduct != null && !oldBrandOfProductCollectionNewProduct.equals(brand)) {
                        oldBrandOfProductCollectionNewProduct.getProductCollection().remove(productCollectionNewProduct);
                        oldBrandOfProductCollectionNewProduct = em.merge(oldBrandOfProductCollectionNewProduct);
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
                Integer id = brand.getBrandId();
                if (findBrand(id) == null) {
                    throw new NonexistentEntityException("The brand with id " + id + " no longer exists.");
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
            Brand brand;
            try {
                brand = em.getReference(Brand.class, id);
                brand.getBrandId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The brand with id " + id + " no longer exists.", enfe);
            }
            Promotion promotion = brand.getPromotion();
            if (promotion != null) {
                promotion.getBrandCollection().remove(brand);
                promotion = em.merge(promotion);
            }
            Collection<Product> productCollection = brand.getProductCollection();
            for (Product productCollectionProduct : productCollection) {
                productCollectionProduct.setBrand(null);
                productCollectionProduct = em.merge(productCollectionProduct);
            }
            em.remove(brand);
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

    public List<Brand> findBrandEntities() {
        return findBrandEntities(true, -1, -1);
    }

    public List<Brand> findBrandEntities(int maxResults, int firstResult) {
        return findBrandEntities(false, maxResults, firstResult);
    }

    private List<Brand> findBrandEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Brand as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Brand findBrand(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Brand.class, id);
        } finally {
            em.close();
        }
    }

    public int getBrandCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Brand as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
