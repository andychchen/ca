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
import jpa.entities.Tax;
import jpa.entities.TranDetail;
import jpa.entities.TranHead;
import jpa.entities.TranDetailLineExpirationQty;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author achen
 */
public class TranDetailJpaController {
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

    public void create(TranDetail tranDetail) throws RollbackFailureException, Exception {
        if (tranDetail.getTranDetailLineExpirationQtyCollection() == null) {
            tranDetail.setTranDetailLineExpirationQtyCollection(new ArrayList<TranDetailLineExpirationQty>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Discount lineManagerDiscount = tranDetail.getLineManagerDiscount();
            if (lineManagerDiscount != null) {
                lineManagerDiscount = em.getReference(lineManagerDiscount.getClass(), lineManagerDiscount.getDiscountId());
                tranDetail.setLineManagerDiscount(lineManagerDiscount);
            }
            Discount discount = tranDetail.getDiscount();
            if (discount != null) {
                discount = em.getReference(discount.getClass(), discount.getDiscountId());
                tranDetail.setDiscount(discount);
            }
            Product isbn = tranDetail.getIsbn();
            if (isbn != null) {
                isbn = em.getReference(isbn.getClass(), isbn.getIsbn());
                tranDetail.setIsbn(isbn);
            }
            Promotion promotion = tranDetail.getPromotion();
            if (promotion != null) {
                promotion = em.getReference(promotion.getClass(), promotion.getPromotionId());
                tranDetail.setPromotion(promotion);
            }
            Tax tax = tranDetail.getTax();
            if (tax != null) {
                tax = em.getReference(tax.getClass(), tax.getTaxId());
                tranDetail.setTax(tax);
            }
            TranHead tranHeadId = tranDetail.getTranHeadId();
            if (tranHeadId != null) {
                tranHeadId = em.getReference(tranHeadId.getClass(), tranHeadId.getTranHeadId());
                tranDetail.setTranHeadId(tranHeadId);
            }
            Collection<TranDetailLineExpirationQty> attachedTranDetailLineExpirationQtyCollection = new ArrayList<TranDetailLineExpirationQty>();
            for (TranDetailLineExpirationQty tranDetailLineExpirationQtyCollectionTranDetailLineExpirationQtyToAttach : tranDetail.getTranDetailLineExpirationQtyCollection()) {
                tranDetailLineExpirationQtyCollectionTranDetailLineExpirationQtyToAttach = em.getReference(tranDetailLineExpirationQtyCollectionTranDetailLineExpirationQtyToAttach.getClass(), tranDetailLineExpirationQtyCollectionTranDetailLineExpirationQtyToAttach.getTranDetailLineExpirationQtyPK());
                attachedTranDetailLineExpirationQtyCollection.add(tranDetailLineExpirationQtyCollectionTranDetailLineExpirationQtyToAttach);
            }
            tranDetail.setTranDetailLineExpirationQtyCollection(attachedTranDetailLineExpirationQtyCollection);
            em.persist(tranDetail);
            if (lineManagerDiscount != null) {
                lineManagerDiscount.getTranDetailCollection().add(tranDetail);
                lineManagerDiscount = em.merge(lineManagerDiscount);
            }
            if (discount != null) {
                discount.getTranDetailCollection().add(tranDetail);
                discount = em.merge(discount);
            }
            if (isbn != null) {
                isbn.getTranDetailCollection().add(tranDetail);
                isbn = em.merge(isbn);
            }
            if (promotion != null) {
                promotion.getTranDetailCollection().add(tranDetail);
                promotion = em.merge(promotion);
            }
            /*if (tax != null) {
                tax.getTranDetailCollection().add(tranDetail);
                tax = em.merge(tax);
            }*/
            if (tranHeadId != null) {
                tranHeadId.getTranDetailCollection().add(tranDetail);
                tranHeadId = em.merge(tranHeadId);
            }
            /*for (TranDetailLineExpirationQty tranDetailLineExpirationQtyCollectionTranDetailLineExpirationQty : tranDetail.getTranDetailLineExpirationQtyCollection()) {
                TranDetail oldTranDetailOfTranDetailLineExpirationQtyCollectionTranDetailLineExpirationQty = tranDetailLineExpirationQtyCollectionTranDetailLineExpirationQty.getTranDetail();
                tranDetailLineExpirationQtyCollectionTranDetailLineExpirationQty.setTranDetail(tranDetail);
                tranDetailLineExpirationQtyCollectionTranDetailLineExpirationQty = em.merge(tranDetailLineExpirationQtyCollectionTranDetailLineExpirationQty);
                if (oldTranDetailOfTranDetailLineExpirationQtyCollectionTranDetailLineExpirationQty != null) {
                    oldTranDetailOfTranDetailLineExpirationQtyCollectionTranDetailLineExpirationQty.getTranDetailLineExpirationQtyCollection().remove(tranDetailLineExpirationQtyCollectionTranDetailLineExpirationQty);
                    oldTranDetailOfTranDetailLineExpirationQtyCollectionTranDetailLineExpirationQty = em.merge(oldTranDetailOfTranDetailLineExpirationQtyCollectionTranDetailLineExpirationQty);
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
            if (em != null&& !isFromOutside) {
                em.close();
            }
        }
    }

    public void edit(TranDetail tranDetail) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TranDetail persistentTranDetail = em.find(TranDetail.class, tranDetail.getTranDetailId());
            Discount lineManagerDiscountOld = persistentTranDetail.getLineManagerDiscount();
            Discount lineManagerDiscountNew = tranDetail.getLineManagerDiscount();
            Discount discountOld = persistentTranDetail.getDiscount();
            Discount discountNew = tranDetail.getDiscount();
            Product isbnOld = persistentTranDetail.getIsbn();
            Product isbnNew = tranDetail.getIsbn();
            Promotion promotionOld = persistentTranDetail.getPromotion();
            Promotion promotionNew = tranDetail.getPromotion();
            Tax taxOld = persistentTranDetail.getTax();
            Tax taxNew = tranDetail.getTax();
            TranHead tranHeadIdOld = persistentTranDetail.getTranHeadId();
            TranHead tranHeadIdNew = tranDetail.getTranHeadId();
            Collection<TranDetailLineExpirationQty> tranDetailLineExpirationQtyCollectionOld = persistentTranDetail.getTranDetailLineExpirationQtyCollection();
            Collection<TranDetailLineExpirationQty> tranDetailLineExpirationQtyCollectionNew = tranDetail.getTranDetailLineExpirationQtyCollection();
            List<String> illegalOrphanMessages = null;
            for (TranDetailLineExpirationQty tranDetailLineExpirationQtyCollectionOldTranDetailLineExpirationQty : tranDetailLineExpirationQtyCollectionOld) {
                if (!tranDetailLineExpirationQtyCollectionNew.contains(tranDetailLineExpirationQtyCollectionOldTranDetailLineExpirationQty)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TranDetailLineExpirationQty " + tranDetailLineExpirationQtyCollectionOldTranDetailLineExpirationQty + " since its tranDetail field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (lineManagerDiscountNew != null) {
                lineManagerDiscountNew = em.getReference(lineManagerDiscountNew.getClass(), lineManagerDiscountNew.getDiscountId());
                tranDetail.setLineManagerDiscount(lineManagerDiscountNew);
            }
            if (discountNew != null) {
                discountNew = em.getReference(discountNew.getClass(), discountNew.getDiscountId());
                tranDetail.setDiscount(discountNew);
            }
            if (isbnNew != null) {
                isbnNew = em.getReference(isbnNew.getClass(), isbnNew.getIsbn());
                tranDetail.setIsbn(isbnNew);
            }
            if (promotionNew != null) {
                promotionNew = em.getReference(promotionNew.getClass(), promotionNew.getPromotionId());
                tranDetail.setPromotion(promotionNew);
            }
            if (taxNew != null) {
                taxNew = em.getReference(taxNew.getClass(), taxNew.getTaxId());
                tranDetail.setTax(taxNew);
            }
            if (tranHeadIdNew != null) {
                tranHeadIdNew = em.getReference(tranHeadIdNew.getClass(), tranHeadIdNew.getTranHeadId());
                tranDetail.setTranHeadId(tranHeadIdNew);
            }
            Collection<TranDetailLineExpirationQty> attachedTranDetailLineExpirationQtyCollectionNew = new ArrayList<TranDetailLineExpirationQty>();
            for (TranDetailLineExpirationQty tranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQtyToAttach : tranDetailLineExpirationQtyCollectionNew) {
                tranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQtyToAttach = em.getReference(tranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQtyToAttach.getClass(), tranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQtyToAttach.getTranDetailLineExpirationQtyPK());
                attachedTranDetailLineExpirationQtyCollectionNew.add(tranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQtyToAttach);
            }
            tranDetailLineExpirationQtyCollectionNew = attachedTranDetailLineExpirationQtyCollectionNew;
            tranDetail.setTranDetailLineExpirationQtyCollection(tranDetailLineExpirationQtyCollectionNew);
            tranDetail = em.merge(tranDetail);
            if (lineManagerDiscountOld != null && !lineManagerDiscountOld.equals(lineManagerDiscountNew)) {
                lineManagerDiscountOld.getTranDetailCollection().remove(tranDetail);
                lineManagerDiscountOld = em.merge(lineManagerDiscountOld);
            }
            if (lineManagerDiscountNew != null && !lineManagerDiscountNew.equals(lineManagerDiscountOld)) {
                lineManagerDiscountNew.getTranDetailCollection().add(tranDetail);
                lineManagerDiscountNew = em.merge(lineManagerDiscountNew);
            }
            if (discountOld != null && !discountOld.equals(discountNew)) {
                discountOld.getTranDetailCollection().remove(tranDetail);
                discountOld = em.merge(discountOld);
            }
            if (discountNew != null && !discountNew.equals(discountOld)) {
                discountNew.getTranDetailCollection().add(tranDetail);
                discountNew = em.merge(discountNew);
            }
            if (isbnOld != null && !isbnOld.equals(isbnNew)) {
                isbnOld.getTranDetailCollection().remove(tranDetail);
                isbnOld = em.merge(isbnOld);
            }
            if (isbnNew != null && !isbnNew.equals(isbnOld)) {
                isbnNew.getTranDetailCollection().add(tranDetail);
                isbnNew = em.merge(isbnNew);
            }
            if (promotionOld != null && !promotionOld.equals(promotionNew)) {
                promotionOld.getTranDetailCollection().remove(tranDetail);
                promotionOld = em.merge(promotionOld);
            }
            if (promotionNew != null && !promotionNew.equals(promotionOld)) {
                promotionNew.getTranDetailCollection().add(tranDetail);
                promotionNew = em.merge(promotionNew);
            }
            if (taxOld != null && !taxOld.equals(taxNew)) {
                taxOld.getTranDetailCollection().remove(tranDetail);
                taxOld = em.merge(taxOld);
            }
            if (taxNew != null && !taxNew.equals(taxOld)) {
                taxNew.getTranDetailCollection().add(tranDetail);
                taxNew = em.merge(taxNew);
            }
            if (tranHeadIdOld != null && !tranHeadIdOld.equals(tranHeadIdNew)) {
                tranHeadIdOld.getTranDetailCollection().remove(tranDetail);
                tranHeadIdOld = em.merge(tranHeadIdOld);
            }
            if (tranHeadIdNew != null && !tranHeadIdNew.equals(tranHeadIdOld)) {
                tranHeadIdNew.getTranDetailCollection().add(tranDetail);
                tranHeadIdNew = em.merge(tranHeadIdNew);
            }
            /*for (TranDetailLineExpirationQty tranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty : tranDetailLineExpirationQtyCollectionNew) {
                if (!tranDetailLineExpirationQtyCollectionOld.contains(tranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty)) {
                    TranDetail oldTranDetailOfTranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty = tranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty.getTranDetail();
                    tranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty.setTranDetail(tranDetail);
                    tranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty = em.merge(tranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty);
                    if (oldTranDetailOfTranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty != null && !oldTranDetailOfTranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty.equals(tranDetail)) {
                        oldTranDetailOfTranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty.getTranDetailLineExpirationQtyCollection().remove(tranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty);
                        oldTranDetailOfTranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty = em.merge(oldTranDetailOfTranDetailLineExpirationQtyCollectionNewTranDetailLineExpirationQty);
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
                Integer id = tranDetail.getTranDetailId();
                if (findTranDetail(id) == null) {
                    throw new NonexistentEntityException("The tranDetail with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null&& !isFromOutside) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TranDetail tranDetail;
            try {
                tranDetail = em.getReference(TranDetail.class, id);
                tranDetail.getTranDetailId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tranDetail with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TranDetailLineExpirationQty> tranDetailLineExpirationQtyCollectionOrphanCheck = tranDetail.getTranDetailLineExpirationQtyCollection();
            for (TranDetailLineExpirationQty tranDetailLineExpirationQtyCollectionOrphanCheckTranDetailLineExpirationQty : tranDetailLineExpirationQtyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TranDetail (" + tranDetail + ") cannot be destroyed since the TranDetailLineExpirationQty " + tranDetailLineExpirationQtyCollectionOrphanCheckTranDetailLineExpirationQty + " in its tranDetailLineExpirationQtyCollection field has a non-nullable tranDetail field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Discount lineManagerDiscount = tranDetail.getLineManagerDiscount();
            if (lineManagerDiscount != null) {
                lineManagerDiscount.getTranDetailCollection().remove(tranDetail);
                lineManagerDiscount = em.merge(lineManagerDiscount);
            }
            Discount discount = tranDetail.getDiscount();
            if (discount != null) {
                discount.getTranDetailCollection().remove(tranDetail);
                discount = em.merge(discount);
            }
            Product isbn = tranDetail.getIsbn();
            if (isbn != null) {
                isbn.getTranDetailCollection().remove(tranDetail);
                isbn = em.merge(isbn);
            }
            Promotion promotion = tranDetail.getPromotion();
            if (promotion != null) {
                promotion.getTranDetailCollection().remove(tranDetail);
                promotion = em.merge(promotion);
            }
            Tax tax = tranDetail.getTax();
            if (tax != null) {
                tax.getTranDetailCollection().remove(tranDetail);
                tax = em.merge(tax);
            }
            TranHead tranHeadId = tranDetail.getTranHeadId();
            if (tranHeadId != null) {
                tranHeadId.getTranDetailCollection().remove(tranDetail);
                tranHeadId = em.merge(tranHeadId);
            }
            em.remove(tranDetail);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null&& !isFromOutside) {
                em.close();
            }
        }
    }

    public List<TranDetail> findTranDetailEntities() {
        return findTranDetailEntities(true, -1, -1);
    }

    public List<TranDetail> findTranDetailEntities(int maxResults, int firstResult) {
        return findTranDetailEntities(false, maxResults, firstResult);
    }

    private List<TranDetail> findTranDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TranDetail as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            if ( !isFromOutside )
            em.close();
        }
    }

    public TranDetail findTranDetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TranDetail.class, id);
        } finally {
            if ( !isFromOutside )
            em.close();
        }
    }

    public int getTranDetailCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TranDetail as o").getSingleResult()).intValue();
        } finally {
            if ( !isFromOutside )
            em.close();
        }
    }

}
