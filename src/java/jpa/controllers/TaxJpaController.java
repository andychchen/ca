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
import jpa.entities.Tax;
import jpa.entities.TranDetail;
import java.util.ArrayList;
import java.util.Collection;
import jpa.entities.ReturnTranDetail;
import jpa.entities.Product;

/**
 *
 * @author achen
 */
public class TaxJpaController {

    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tax tax) throws RollbackFailureException, Exception {
        if (tax.getTranDetailCollection() == null) {
            tax.setTranDetailCollection(new ArrayList<TranDetail>());
        }
        if (tax.getReturnTranDetailCollection() == null) {
            tax.setReturnTranDetailCollection(new ArrayList<ReturnTranDetail>());
        }
        if (tax.getProductCollection() == null) {
            tax.setProductCollection(new ArrayList<Product>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<TranDetail> attachedTranDetailCollection = new ArrayList<TranDetail>();
            for (TranDetail tranDetailCollectionTranDetailToAttach : tax.getTranDetailCollection()) {
                tranDetailCollectionTranDetailToAttach = em.getReference(tranDetailCollectionTranDetailToAttach.getClass(), tranDetailCollectionTranDetailToAttach.getTranDetailId());
                attachedTranDetailCollection.add(tranDetailCollectionTranDetailToAttach);
            }
            tax.setTranDetailCollection(attachedTranDetailCollection);
            Collection<ReturnTranDetail> attachedReturnTranDetailCollection = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetailToAttach : tax.getReturnTranDetailCollection()) {
                returnTranDetailCollectionReturnTranDetailToAttach = em.getReference(returnTranDetailCollectionReturnTranDetailToAttach.getClass(), returnTranDetailCollectionReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollection.add(returnTranDetailCollectionReturnTranDetailToAttach);
            }
            tax.setReturnTranDetailCollection(attachedReturnTranDetailCollection);
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : tax.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getIsbn());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            tax.setProductCollection(attachedProductCollection);
            em.persist(tax);
            /*
            for (TranDetail tranDetailCollectionTranDetail : tax.getTranDetailCollection()) {
                Tax oldTaxOfTranDetailCollectionTranDetail = tranDetailCollectionTranDetail.getTax();
                tranDetailCollectionTranDetail.setTax(tax);
                tranDetailCollectionTranDetail = em.merge(tranDetailCollectionTranDetail);
                if (oldTaxOfTranDetailCollectionTranDetail != null) {
                    oldTaxOfTranDetailCollectionTranDetail.getTranDetailCollection().remove(tranDetailCollectionTranDetail);
                    oldTaxOfTranDetailCollectionTranDetail = em.merge(oldTaxOfTranDetailCollectionTranDetail);
                }
            }
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetail : tax.getReturnTranDetailCollection()) {
                Tax oldTaxOfReturnTranDetailCollectionReturnTranDetail = returnTranDetailCollectionReturnTranDetail.getTax();
                returnTranDetailCollectionReturnTranDetail.setTax(tax);
                returnTranDetailCollectionReturnTranDetail = em.merge(returnTranDetailCollectionReturnTranDetail);
                if (oldTaxOfReturnTranDetailCollectionReturnTranDetail != null) {
                    oldTaxOfReturnTranDetailCollectionReturnTranDetail.getReturnTranDetailCollection().remove(returnTranDetailCollectionReturnTranDetail);
                    oldTaxOfReturnTranDetailCollectionReturnTranDetail = em.merge(oldTaxOfReturnTranDetailCollectionReturnTranDetail);
                }
            }
            for (Product productCollectionProduct : tax.getProductCollection()) {
                Tax oldTaxOfProductCollectionProduct = productCollectionProduct.getTax();
                productCollectionProduct.setTax(tax);
                productCollectionProduct = em.merge(productCollectionProduct);
                if (oldTaxOfProductCollectionProduct != null) {
                    oldTaxOfProductCollectionProduct.getProductCollection().remove(productCollectionProduct);
                    oldTaxOfProductCollectionProduct = em.merge(oldTaxOfProductCollectionProduct);
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

    public void edit(Tax tax) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tax persistentTax = em.find(Tax.class, tax.getTaxId());
            Collection<TranDetail> tranDetailCollectionOld = persistentTax.getTranDetailCollection();
            Collection<TranDetail> tranDetailCollectionNew = tax.getTranDetailCollection();
            Collection<ReturnTranDetail> returnTranDetailCollectionOld = persistentTax.getReturnTranDetailCollection();
            Collection<ReturnTranDetail> returnTranDetailCollectionNew = tax.getReturnTranDetailCollection();
            Collection<Product> productCollectionOld = persistentTax.getProductCollection();
            Collection<Product> productCollectionNew = tax.getProductCollection();
            Collection<TranDetail> attachedTranDetailCollectionNew = new ArrayList<TranDetail>();
            /*for (TranDetail tranDetailCollectionNewTranDetailToAttach : tranDetailCollectionNew) {
                tranDetailCollectionNewTranDetailToAttach = em.getReference(tranDetailCollectionNewTranDetailToAttach.getClass(), tranDetailCollectionNewTranDetailToAttach.getTranDetailId());
                attachedTranDetailCollectionNew.add(tranDetailCollectionNewTranDetailToAttach);
            }
            tranDetailCollectionNew = attachedTranDetailCollectionNew;
            tax.setTranDetailCollection(tranDetailCollectionNew);
            Collection<ReturnTranDetail> attachedReturnTranDetailCollectionNew = new ArrayList<ReturnTranDetail>();
            for (ReturnTranDetail returnTranDetailCollectionNewReturnTranDetailToAttach : returnTranDetailCollectionNew) {
                returnTranDetailCollectionNewReturnTranDetailToAttach = em.getReference(returnTranDetailCollectionNewReturnTranDetailToAttach.getClass(), returnTranDetailCollectionNewReturnTranDetailToAttach.getTranDetailId());
                attachedReturnTranDetailCollectionNew.add(returnTranDetailCollectionNewReturnTranDetailToAttach);
            }
            returnTranDetailCollectionNew = attachedReturnTranDetailCollectionNew;
            tax.setReturnTranDetailCollection(returnTranDetailCollectionNew);*/
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getIsbn());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            tax.setProductCollection(productCollectionNew);
            tax = em.merge(tax);
            /*
            for (TranDetail tranDetailCollectionOldTranDetail : tranDetailCollectionOld) {
            if (!tranDetailCollectionNew.contains(tranDetailCollectionOldTranDetail)) {
            tranDetailCollectionOldTranDetail.setTax(null);
            tranDetailCollectionOldTranDetail = em.merge(tranDetailCollectionOldTranDetail);
            }
            }
            for (TranDetail tranDetailCollectionNewTranDetail : tranDetailCollectionNew) {
            if (!tranDetailCollectionOld.contains(tranDetailCollectionNewTranDetail)) {
            Tax oldTaxOfTranDetailCollectionNewTranDetail = tranDetailCollectionNewTranDetail.getTax();
            tranDetailCollectionNewTranDetail.setTax(tax);
            tranDetailCollectionNewTranDetail = em.merge(tranDetailCollectionNewTranDetail);
            if (oldTaxOfTranDetailCollectionNewTranDetail != null && !oldTaxOfTranDetailCollectionNewTranDetail.equals(tax)) {
            oldTaxOfTranDetailCollectionNewTranDetail.getTranDetailCollection().remove(tranDetailCollectionNewTranDetail);
            oldTaxOfTranDetailCollectionNewTranDetail = em.merge(oldTaxOfTranDetailCollectionNewTranDetail);
            }
            }
            }
            for (ReturnTranDetail returnTranDetailCollectionOldReturnTranDetail : returnTranDetailCollectionOld) {
            if (!returnTranDetailCollectionNew.contains(returnTranDetailCollectionOldReturnTranDetail)) {
            returnTranDetailCollectionOldReturnTranDetail.setTax(null);
            returnTranDetailCollectionOldReturnTranDetail = em.merge(returnTranDetailCollectionOldReturnTranDetail);
            }
            }
            for (ReturnTranDetail returnTranDetailCollectionNewReturnTranDetail : returnTranDetailCollectionNew) {
            if (!returnTranDetailCollectionOld.contains(returnTranDetailCollectionNewReturnTranDetail)) {
            Tax oldTaxOfReturnTranDetailCollectionNewReturnTranDetail = returnTranDetailCollectionNewReturnTranDetail.getTax();
            returnTranDetailCollectionNewReturnTranDetail.setTax(tax);
            returnTranDetailCollectionNewReturnTranDetail = em.merge(returnTranDetailCollectionNewReturnTranDetail);
            if (oldTaxOfReturnTranDetailCollectionNewReturnTranDetail != null && !oldTaxOfReturnTranDetailCollectionNewReturnTranDetail.equals(tax)) {
            oldTaxOfReturnTranDetailCollectionNewReturnTranDetail.getReturnTranDetailCollection().remove(returnTranDetailCollectionNewReturnTranDetail);
            oldTaxOfReturnTranDetailCollectionNewReturnTranDetail = em.merge(oldTaxOfReturnTranDetailCollectionNewReturnTranDetail);
            }
            }
            }
            for (Product productCollectionOldProduct : productCollectionOld) {
            if (!productCollectionNew.contains(productCollectionOldProduct)) {
            productCollectionOldProduct.setTax(null);
            productCollectionOldProduct = em.merge(productCollectionOldProduct);
            }
            }
            for (Product productCollectionNewProduct : productCollectionNew) {
            if (!productCollectionOld.contains(productCollectionNewProduct)) {
            Tax oldTaxOfProductCollectionNewProduct = productCollectionNewProduct.getTax();
            productCollectionNewProduct.setTax(tax);
            productCollectionNewProduct = em.merge(productCollectionNewProduct);
            if (oldTaxOfProductCollectionNewProduct != null && !oldTaxOfProductCollectionNewProduct.equals(tax)) {
            oldTaxOfProductCollectionNewProduct.getProductCollection().remove(productCollectionNewProduct);
            oldTaxOfProductCollectionNewProduct = em.merge(oldTaxOfProductCollectionNewProduct);
            }
            }
            }
             */
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tax.getTaxId();
                if (findTax(id) == null) {
                    throw new NonexistentEntityException("The tax with id " + id + " no longer exists.");
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
            Tax tax;
            try {
                tax = em.getReference(Tax.class, id);
                tax.getTaxId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tax with id " + id + " no longer exists.", enfe);
            }
            Collection<TranDetail> tranDetailCollection = tax.getTranDetailCollection();
            for (TranDetail tranDetailCollectionTranDetail : tranDetailCollection) {
                tranDetailCollectionTranDetail.setTax(null);
                tranDetailCollectionTranDetail = em.merge(tranDetailCollectionTranDetail);
            }
            Collection<ReturnTranDetail> returnTranDetailCollection = tax.getReturnTranDetailCollection();
            for (ReturnTranDetail returnTranDetailCollectionReturnTranDetail : returnTranDetailCollection) {
                returnTranDetailCollectionReturnTranDetail.setTax(null);
                returnTranDetailCollectionReturnTranDetail = em.merge(returnTranDetailCollectionReturnTranDetail);
            }
            Collection<Product> productCollection = tax.getProductCollection();
            for (Product productCollectionProduct : productCollection) {
                productCollectionProduct.setTax(null);
                productCollectionProduct = em.merge(productCollectionProduct);
            }
            em.remove(tax);
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

    public List<Tax> findTaxEntities() {
        return findTaxEntities(true, -1, -1);
    }

    public List<Tax> findTaxEntities(int maxResults, int firstResult) {
        return findTaxEntities(false, maxResults, firstResult);
    }

    private List<Tax> findTaxEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tax as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tax findTax(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tax.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaxCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Tax as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
