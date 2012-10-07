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
import jpa.controllers.exceptions.PreexistingEntityException;
import jpa.controllers.exceptions.RollbackFailureException;
import jpa.entities.Product;
import jpa.entities.ProductExpirationQty;
import jpa.entities.ProductExpirationQtyPK;

/**
 *
 * @author achen
 */
public class ProductExpirationQtyJpaController {

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

    public void create(ProductExpirationQty productExpirationQty) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (productExpirationQty.getProductExpirationQtyPK() == null) {
            productExpirationQty.setProductExpirationQtyPK(new ProductExpirationQtyPK());
        }
        productExpirationQty.getProductExpirationQtyPK().setIsbn(productExpirationQty.getProduct().getIsbn());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Product product = productExpirationQty.getProduct();
            if (product != null) {
                product = em.getReference(product.getClass(), product.getIsbn());
                productExpirationQty.setProduct(product);
            }
            em.persist(productExpirationQty);
            if (product != null) {
                product.getProductExpirationQtyCollection().add(productExpirationQty);
                product = em.merge(product);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProductExpirationQty(productExpirationQty.getProductExpirationQtyPK()) != null) {
                throw new PreexistingEntityException("ProductExpirationQty " + productExpirationQty + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public void edit(ProductExpirationQty productExpirationQty) throws NonexistentEntityException, RollbackFailureException, Exception {
        productExpirationQty.getProductExpirationQtyPK().setIsbn(productExpirationQty.getProduct().getIsbn());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductExpirationQty persistentProductExpirationQty = em.find(ProductExpirationQty.class, productExpirationQty.getProductExpirationQtyPK());
            Product productOld = persistentProductExpirationQty.getProduct();
            Product productNew = productExpirationQty.getProduct();
            if (productNew != null) {
                productNew = em.getReference(productNew.getClass(), productNew.getIsbn());
                productExpirationQty.setProduct(productNew);
            }
            productExpirationQty = em.merge(productExpirationQty);
            if (productOld != null && !productOld.equals(productNew)) {
                productOld.getProductExpirationQtyCollection().remove(productExpirationQty);
                productOld = em.merge(productOld);
            }
            if (productNew != null && !productNew.equals(productOld)) {
                productNew.getProductExpirationQtyCollection().add(productExpirationQty);
                productNew = em.merge(productNew);
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
                ProductExpirationQtyPK id = productExpirationQty.getProductExpirationQtyPK();
                if (findProductExpirationQty(id) == null) {
                    throw new NonexistentEntityException("The productExpirationQty with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public void destroy(ProductExpirationQtyPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductExpirationQty productExpirationQty;
            try {
                productExpirationQty = em.getReference(ProductExpirationQty.class, id);
                productExpirationQty.getProductExpirationQtyPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productExpirationQty with id " + id + " no longer exists.", enfe);
            }
            Product product = productExpirationQty.getProduct();
            if (product != null) {
                product.getProductExpirationQtyCollection().remove(productExpirationQty);
                product = em.merge(product);
            }
            em.remove(productExpirationQty);
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

    public List<ProductExpirationQty> findProductExpirationQtyEntities() {
        return findProductExpirationQtyEntities(true, -1, -1);
    }

    public List<ProductExpirationQty> findProductExpirationQtyEntities(int maxResults, int firstResult) {
        return findProductExpirationQtyEntities(false, maxResults, firstResult);
    }

    private List<ProductExpirationQty> findProductExpirationQtyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ProductExpirationQty as o");
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

    public ProductExpirationQty findProductExpirationQty(ProductExpirationQtyPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductExpirationQty.class, id);
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }

    public int getProductExpirationQtyCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from ProductExpirationQty as o").getSingleResult()).intValue();
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }
}
