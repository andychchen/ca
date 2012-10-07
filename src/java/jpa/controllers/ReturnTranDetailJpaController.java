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
import jpa.entities.Product;
import jpa.entities.Promotion;
import jpa.entities.ReturnTranDetail;
import jpa.entities.ReturnTranHead;
import jpa.entities.Tax;
import jpa.entities.YesNo;
import jpa.entities.ReturnTranDetailLineExpirationQty;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author achen
 */
public class ReturnTranDetailJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReturnTranDetail returnTranDetail) throws RollbackFailureException, Exception {
        if (returnTranDetail.getReturnTranDetailLineExpirationQtyCollection() == null) {
            returnTranDetail.setReturnTranDetailLineExpirationQtyCollection(new ArrayList<ReturnTranDetailLineExpirationQty>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Discount lineManagerDiscount = returnTranDetail.getLineManagerDiscount();
            if (lineManagerDiscount != null) {
                lineManagerDiscount = em.getReference(lineManagerDiscount.getClass(), lineManagerDiscount.getDiscountId());
                returnTranDetail.setLineManagerDiscount(lineManagerDiscount);
            }
            Discount discount = returnTranDetail.getDiscount();
            if (discount != null) {
                discount = em.getReference(discount.getClass(), discount.getDiscountId());
                returnTranDetail.setDiscount(discount);
            }
            Product isbn = returnTranDetail.getIsbn();
            if (isbn != null) {
                isbn = em.getReference(isbn.getClass(), isbn.getIsbn());
                returnTranDetail.setIsbn(isbn);
            }
            Promotion promotion = returnTranDetail.getPromotion();
            if (promotion != null) {
                promotion = em.getReference(promotion.getClass(), promotion.getPromotionId());
                returnTranDetail.setPromotion(promotion);
            }
            ReturnTranHead tranHeadId = returnTranDetail.getTranHeadId();
            if (tranHeadId != null) {
                tranHeadId = em.getReference(tranHeadId.getClass(), tranHeadId.getTranHeadId());
                returnTranDetail.setTranHeadId(tranHeadId);
            }
            Tax tax = returnTranDetail.getTax();
            if (tax != null) {
                tax = em.getReference(tax.getClass(), tax.getTaxId());
                returnTranDetail.setTax(tax);
            }
            YesNo isReturned = returnTranDetail.getIsReturned();
            if (isReturned != null) {
                isReturned = em.getReference(isReturned.getClass(), isReturned.getName());
                returnTranDetail.setIsReturned(isReturned);
            }
            Collection<ReturnTranDetailLineExpirationQty> attachedReturnTranDetailLineExpirationQtyCollection = new ArrayList<ReturnTranDetailLineExpirationQty>();
            for (ReturnTranDetailLineExpirationQty returnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQtyToAttach : returnTranDetail.getReturnTranDetailLineExpirationQtyCollection()) {
                returnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQtyToAttach = em.getReference(returnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQtyToAttach.getClass(), returnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQtyToAttach.getReturnTranDetailLineExpirationQtyPK());
                attachedReturnTranDetailLineExpirationQtyCollection.add(returnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQtyToAttach);
            }
            returnTranDetail.setReturnTranDetailLineExpirationQtyCollection(attachedReturnTranDetailLineExpirationQtyCollection);
            em.persist(returnTranDetail);
           /*if (lineManagerDiscount != null) {
                lineManagerDiscount.getReturnTranDetailCollection().add(returnTranDetail);
                lineManagerDiscount = em.merge(lineManagerDiscount);
            }
            if (discount != null) {
                discount.getReturnTranDetailCollection().add(returnTranDetail);
                discount = em.merge(discount);
            }
            if (isbn != null) {
                isbn.getReturnTranDetailCollection().add(returnTranDetail);
                isbn = em.merge(isbn);
            }
            if (promotion != null) {
                promotion.getReturnTranDetailCollection().add(returnTranDetail);
                promotion = em.merge(promotion);
            }
            if (tranHeadId != null) {
                tranHeadId.getReturnTranDetailCollection().add(returnTranDetail);
                tranHeadId = em.merge(tranHeadId);
            }
            if (tax != null) {
                tax.getReturnTranDetailCollection().add(returnTranDetail);
                tax = em.merge(tax);
            }
            if (isReturned != null) {
                isReturned.getReturnTranDetailCollection().add(returnTranDetail);
                isReturned = em.merge(isReturned);
            }
            for (ReturnTranDetailLineExpirationQty returnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQty : returnTranDetail.getReturnTranDetailLineExpirationQtyCollection()) {
                ReturnTranDetail oldReturnTranDetailOfReturnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQty = returnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQty.getReturnTranDetail();
                returnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQty.setReturnTranDetail(returnTranDetail);
                returnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQty = em.merge(returnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQty);
                if (oldReturnTranDetailOfReturnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQty != null) {
                    oldReturnTranDetailOfReturnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQty.getReturnTranDetailLineExpirationQtyCollection().remove(returnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQty);
                    oldReturnTranDetailOfReturnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQty = em.merge(oldReturnTranDetailOfReturnTranDetailLineExpirationQtyCollectionReturnTranDetailLineExpirationQty);
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

    public void edit(ReturnTranDetail returnTranDetail) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ReturnTranDetail persistentReturnTranDetail = em.find(ReturnTranDetail.class, returnTranDetail.getTranDetailId());
            Discount lineManagerDiscountOld = persistentReturnTranDetail.getLineManagerDiscount();
            Discount lineManagerDiscountNew = returnTranDetail.getLineManagerDiscount();
            Discount discountOld = persistentReturnTranDetail.getDiscount();
            Discount discountNew = returnTranDetail.getDiscount();
            Product isbnOld = persistentReturnTranDetail.getIsbn();
            Product isbnNew = returnTranDetail.getIsbn();
            Promotion promotionOld = persistentReturnTranDetail.getPromotion();
            Promotion promotionNew = returnTranDetail.getPromotion();
            ReturnTranHead tranHeadIdOld = persistentReturnTranDetail.getTranHeadId();
            ReturnTranHead tranHeadIdNew = returnTranDetail.getTranHeadId();
            Tax taxOld = persistentReturnTranDetail.getTax();
            Tax taxNew = returnTranDetail.getTax();
            YesNo isReturnedOld = persistentReturnTranDetail.getIsReturned();
            YesNo isReturnedNew = returnTranDetail.getIsReturned();
            Collection<ReturnTranDetailLineExpirationQty> returnTranDetailLineExpirationQtyCollectionOld = persistentReturnTranDetail.getReturnTranDetailLineExpirationQtyCollection();
            Collection<ReturnTranDetailLineExpirationQty> returnTranDetailLineExpirationQtyCollectionNew = returnTranDetail.getReturnTranDetailLineExpirationQtyCollection();
            List<String> illegalOrphanMessages = null;
            for (ReturnTranDetailLineExpirationQty returnTranDetailLineExpirationQtyCollectionOldReturnTranDetailLineExpirationQty : returnTranDetailLineExpirationQtyCollectionOld) {
                if (!returnTranDetailLineExpirationQtyCollectionNew.contains(returnTranDetailLineExpirationQtyCollectionOldReturnTranDetailLineExpirationQty)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReturnTranDetailLineExpirationQty " + returnTranDetailLineExpirationQtyCollectionOldReturnTranDetailLineExpirationQty + " since its returnTranDetail field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (lineManagerDiscountNew != null) {
                lineManagerDiscountNew = em.getReference(lineManagerDiscountNew.getClass(), lineManagerDiscountNew.getDiscountId());
                returnTranDetail.setLineManagerDiscount(lineManagerDiscountNew);
            }
            if (discountNew != null) {
                discountNew = em.getReference(discountNew.getClass(), discountNew.getDiscountId());
                returnTranDetail.setDiscount(discountNew);
            }
            if (isbnNew != null) {
                isbnNew = em.getReference(isbnNew.getClass(), isbnNew.getIsbn());
                returnTranDetail.setIsbn(isbnNew);
            }
            if (promotionNew != null) {
                promotionNew = em.getReference(promotionNew.getClass(), promotionNew.getPromotionId());
                returnTranDetail.setPromotion(promotionNew);
            }
            if (tranHeadIdNew != null) {
                tranHeadIdNew = em.getReference(tranHeadIdNew.getClass(), tranHeadIdNew.getTranHeadId());
                returnTranDetail.setTranHeadId(tranHeadIdNew);
            }
            if (taxNew != null) {
                taxNew = em.getReference(taxNew.getClass(), taxNew.getTaxId());
                returnTranDetail.setTax(taxNew);
            }
            if (isReturnedNew != null) {
                isReturnedNew = em.getReference(isReturnedNew.getClass(), isReturnedNew.getName());
                returnTranDetail.setIsReturned(isReturnedNew);
            }
            Collection<ReturnTranDetailLineExpirationQty> attachedReturnTranDetailLineExpirationQtyCollectionNew = new ArrayList<ReturnTranDetailLineExpirationQty>();
            for (ReturnTranDetailLineExpirationQty returnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQtyToAttach : returnTranDetailLineExpirationQtyCollectionNew) {
                returnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQtyToAttach = em.getReference(returnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQtyToAttach.getClass(), returnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQtyToAttach.getReturnTranDetailLineExpirationQtyPK());
                attachedReturnTranDetailLineExpirationQtyCollectionNew.add(returnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQtyToAttach);
            }
            returnTranDetailLineExpirationQtyCollectionNew = attachedReturnTranDetailLineExpirationQtyCollectionNew;
            returnTranDetail.setReturnTranDetailLineExpirationQtyCollection(returnTranDetailLineExpirationQtyCollectionNew);
            returnTranDetail = em.merge(returnTranDetail);
            if (lineManagerDiscountOld != null && !lineManagerDiscountOld.equals(lineManagerDiscountNew)) {
                lineManagerDiscountOld.getReturnTranDetailCollection().remove(returnTranDetail);
                lineManagerDiscountOld = em.merge(lineManagerDiscountOld);
            }
            if (lineManagerDiscountNew != null && !lineManagerDiscountNew.equals(lineManagerDiscountOld)) {
                lineManagerDiscountNew.getReturnTranDetailCollection().add(returnTranDetail);
                lineManagerDiscountNew = em.merge(lineManagerDiscountNew);
            }
            if (discountOld != null && !discountOld.equals(discountNew)) {
                discountOld.getReturnTranDetailCollection().remove(returnTranDetail);
                discountOld = em.merge(discountOld);
            }
            if (discountNew != null && !discountNew.equals(discountOld)) {
                discountNew.getReturnTranDetailCollection().add(returnTranDetail);
                discountNew = em.merge(discountNew);
            }
            if (isbnOld != null && !isbnOld.equals(isbnNew)) {
                isbnOld.getReturnTranDetailCollection().remove(returnTranDetail);
                isbnOld = em.merge(isbnOld);
            }
            if (isbnNew != null && !isbnNew.equals(isbnOld)) {
                isbnNew.getReturnTranDetailCollection().add(returnTranDetail);
                isbnNew = em.merge(isbnNew);
            }
            if (promotionOld != null && !promotionOld.equals(promotionNew)) {
                promotionOld.getReturnTranDetailCollection().remove(returnTranDetail);
                promotionOld = em.merge(promotionOld);
            }
            if (promotionNew != null && !promotionNew.equals(promotionOld)) {
                promotionNew.getReturnTranDetailCollection().add(returnTranDetail);
                promotionNew = em.merge(promotionNew);
            }
            if (tranHeadIdOld != null && !tranHeadIdOld.equals(tranHeadIdNew)) {
                tranHeadIdOld.getReturnTranDetailCollection().remove(returnTranDetail);
                tranHeadIdOld = em.merge(tranHeadIdOld);
            }
            if (tranHeadIdNew != null && !tranHeadIdNew.equals(tranHeadIdOld)) {
                tranHeadIdNew.getReturnTranDetailCollection().add(returnTranDetail);
                tranHeadIdNew = em.merge(tranHeadIdNew);
            }
            if (taxOld != null && !taxOld.equals(taxNew)) {
                taxOld.getReturnTranDetailCollection().remove(returnTranDetail);
                taxOld = em.merge(taxOld);
            }
            if (taxNew != null && !taxNew.equals(taxOld)) {
                taxNew.getReturnTranDetailCollection().add(returnTranDetail);
                taxNew = em.merge(taxNew);
            }
            if (isReturnedOld != null && !isReturnedOld.equals(isReturnedNew)) {
                isReturnedOld.getReturnTranDetailCollection().remove(returnTranDetail);
                isReturnedOld = em.merge(isReturnedOld);
            }
            if (isReturnedNew != null && !isReturnedNew.equals(isReturnedOld)) {
                isReturnedNew.getReturnTranDetailCollection().add(returnTranDetail);
                isReturnedNew = em.merge(isReturnedNew);
            }
            for (ReturnTranDetailLineExpirationQty returnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty : returnTranDetailLineExpirationQtyCollectionNew) {
                if (!returnTranDetailLineExpirationQtyCollectionOld.contains(returnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty)) {
                    ReturnTranDetail oldReturnTranDetailOfReturnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty = returnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty.getReturnTranDetail();
                    returnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty.setReturnTranDetail(returnTranDetail);
                    returnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty = em.merge(returnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty);
                    if (oldReturnTranDetailOfReturnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty != null && !oldReturnTranDetailOfReturnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty.equals(returnTranDetail)) {
                        oldReturnTranDetailOfReturnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty.getReturnTranDetailLineExpirationQtyCollection().remove(returnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty);
                        oldReturnTranDetailOfReturnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty = em.merge(oldReturnTranDetailOfReturnTranDetailLineExpirationQtyCollectionNewReturnTranDetailLineExpirationQty);
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
                Integer id = returnTranDetail.getTranDetailId();
                if (findReturnTranDetail(id) == null) {
                    throw new NonexistentEntityException("The returnTranDetail with id " + id + " no longer exists.");
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
            ReturnTranDetail returnTranDetail;
            try {
                returnTranDetail = em.getReference(ReturnTranDetail.class, id);
                returnTranDetail.getTranDetailId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The returnTranDetail with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ReturnTranDetailLineExpirationQty> returnTranDetailLineExpirationQtyCollectionOrphanCheck = returnTranDetail.getReturnTranDetailLineExpirationQtyCollection();
            for (ReturnTranDetailLineExpirationQty returnTranDetailLineExpirationQtyCollectionOrphanCheckReturnTranDetailLineExpirationQty : returnTranDetailLineExpirationQtyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ReturnTranDetail (" + returnTranDetail + ") cannot be destroyed since the ReturnTranDetailLineExpirationQty " + returnTranDetailLineExpirationQtyCollectionOrphanCheckReturnTranDetailLineExpirationQty + " in its returnTranDetailLineExpirationQtyCollection field has a non-nullable returnTranDetail field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Discount lineManagerDiscount = returnTranDetail.getLineManagerDiscount();
            if (lineManagerDiscount != null) {
                lineManagerDiscount.getReturnTranDetailCollection().remove(returnTranDetail);
                lineManagerDiscount = em.merge(lineManagerDiscount);
            }
            Discount discount = returnTranDetail.getDiscount();
            if (discount != null) {
                discount.getReturnTranDetailCollection().remove(returnTranDetail);
                discount = em.merge(discount);
            }
            Product isbn = returnTranDetail.getIsbn();
            if (isbn != null) {
                isbn.getReturnTranDetailCollection().remove(returnTranDetail);
                isbn = em.merge(isbn);
            }
            Promotion promotion = returnTranDetail.getPromotion();
            if (promotion != null) {
                promotion.getReturnTranDetailCollection().remove(returnTranDetail);
                promotion = em.merge(promotion);
            }
            ReturnTranHead tranHeadId = returnTranDetail.getTranHeadId();
            if (tranHeadId != null) {
                tranHeadId.getReturnTranDetailCollection().remove(returnTranDetail);
                tranHeadId = em.merge(tranHeadId);
            }
            Tax tax = returnTranDetail.getTax();
            if (tax != null) {
                tax.getReturnTranDetailCollection().remove(returnTranDetail);
                tax = em.merge(tax);
            }
            YesNo isReturned = returnTranDetail.getIsReturned();
            if (isReturned != null) {
                isReturned.getReturnTranDetailCollection().remove(returnTranDetail);
                isReturned = em.merge(isReturned);
            }
            em.remove(returnTranDetail);
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

    public List<ReturnTranDetail> findReturnTranDetailEntities() {
        return findReturnTranDetailEntities(true, -1, -1);
    }

    public List<ReturnTranDetail> findReturnTranDetailEntities(int maxResults, int firstResult) {
        return findReturnTranDetailEntities(false, maxResults, firstResult);
    }

    private List<ReturnTranDetail> findReturnTranDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ReturnTranDetail as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ReturnTranDetail findReturnTranDetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReturnTranDetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getReturnTranDetailCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from ReturnTranDetail as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
