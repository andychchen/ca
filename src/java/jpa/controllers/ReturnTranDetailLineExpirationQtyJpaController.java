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
import jpa.entities.ReturnTranDetail;
import jpa.entities.ReturnTranDetailLineExpirationQty;
import jpa.entities.ReturnTranDetailLineExpirationQtyPK;

/**
 *
 * @author achen
 */
public class ReturnTranDetailLineExpirationQtyJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReturnTranDetailLineExpirationQty returnTranDetailLineExpirationQty) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (returnTranDetailLineExpirationQty.getReturnTranDetailLineExpirationQtyPK() == null) {
            returnTranDetailLineExpirationQty.setReturnTranDetailLineExpirationQtyPK(new ReturnTranDetailLineExpirationQtyPK());
        }
        returnTranDetailLineExpirationQty.getReturnTranDetailLineExpirationQtyPK().setTranDetailId(returnTranDetailLineExpirationQty.getReturnTranDetail().getTranDetailId());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ReturnTranDetail returnTranDetail = returnTranDetailLineExpirationQty.getReturnTranDetail();
            if (returnTranDetail != null) {
                returnTranDetail = em.getReference(returnTranDetail.getClass(), returnTranDetail.getTranDetailId());
                returnTranDetailLineExpirationQty.setReturnTranDetail(returnTranDetail);
            }
            em.persist(returnTranDetailLineExpirationQty);
            /*if (returnTranDetail != null) {
                returnTranDetail.getReturnTranDetailLineExpirationQtyCollection().add(returnTranDetailLineExpirationQty);
                returnTranDetail = em.merge(returnTranDetail);
            }*/
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findReturnTranDetailLineExpirationQty(returnTranDetailLineExpirationQty.getReturnTranDetailLineExpirationQtyPK()) != null) {
                throw new PreexistingEntityException("ReturnTranDetailLineExpirationQty " + returnTranDetailLineExpirationQty + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReturnTranDetailLineExpirationQty returnTranDetailLineExpirationQty) throws NonexistentEntityException, RollbackFailureException, Exception {
        returnTranDetailLineExpirationQty.getReturnTranDetailLineExpirationQtyPK().setTranDetailId(returnTranDetailLineExpirationQty.getReturnTranDetail().getTranDetailId());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ReturnTranDetailLineExpirationQty persistentReturnTranDetailLineExpirationQty = em.find(ReturnTranDetailLineExpirationQty.class, returnTranDetailLineExpirationQty.getReturnTranDetailLineExpirationQtyPK());
            ReturnTranDetail returnTranDetailOld = persistentReturnTranDetailLineExpirationQty.getReturnTranDetail();
            ReturnTranDetail returnTranDetailNew = returnTranDetailLineExpirationQty.getReturnTranDetail();
            if (returnTranDetailNew != null) {
                returnTranDetailNew = em.getReference(returnTranDetailNew.getClass(), returnTranDetailNew.getTranDetailId());
                returnTranDetailLineExpirationQty.setReturnTranDetail(returnTranDetailNew);
            }
            returnTranDetailLineExpirationQty = em.merge(returnTranDetailLineExpirationQty);
            if (returnTranDetailOld != null && !returnTranDetailOld.equals(returnTranDetailNew)) {
                returnTranDetailOld.getReturnTranDetailLineExpirationQtyCollection().remove(returnTranDetailLineExpirationQty);
                returnTranDetailOld = em.merge(returnTranDetailOld);
            }
            if (returnTranDetailNew != null && !returnTranDetailNew.equals(returnTranDetailOld)) {
                returnTranDetailNew.getReturnTranDetailLineExpirationQtyCollection().add(returnTranDetailLineExpirationQty);
                returnTranDetailNew = em.merge(returnTranDetailNew);
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
                ReturnTranDetailLineExpirationQtyPK id = returnTranDetailLineExpirationQty.getReturnTranDetailLineExpirationQtyPK();
                if (findReturnTranDetailLineExpirationQty(id) == null) {
                    throw new NonexistentEntityException("The returnTranDetailLineExpirationQty with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ReturnTranDetailLineExpirationQtyPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ReturnTranDetailLineExpirationQty returnTranDetailLineExpirationQty;
            try {
                returnTranDetailLineExpirationQty = em.getReference(ReturnTranDetailLineExpirationQty.class, id);
                returnTranDetailLineExpirationQty.getReturnTranDetailLineExpirationQtyPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The returnTranDetailLineExpirationQty with id " + id + " no longer exists.", enfe);
            }
            ReturnTranDetail returnTranDetail = returnTranDetailLineExpirationQty.getReturnTranDetail();
            if (returnTranDetail != null) {
                returnTranDetail.getReturnTranDetailLineExpirationQtyCollection().remove(returnTranDetailLineExpirationQty);
                returnTranDetail = em.merge(returnTranDetail);
            }
            em.remove(returnTranDetailLineExpirationQty);
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

    public List<ReturnTranDetailLineExpirationQty> findReturnTranDetailLineExpirationQtyEntities() {
        return findReturnTranDetailLineExpirationQtyEntities(true, -1, -1);
    }

    public List<ReturnTranDetailLineExpirationQty> findReturnTranDetailLineExpirationQtyEntities(int maxResults, int firstResult) {
        return findReturnTranDetailLineExpirationQtyEntities(false, maxResults, firstResult);
    }

    private List<ReturnTranDetailLineExpirationQty> findReturnTranDetailLineExpirationQtyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ReturnTranDetailLineExpirationQty as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ReturnTranDetailLineExpirationQty findReturnTranDetailLineExpirationQty(ReturnTranDetailLineExpirationQtyPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReturnTranDetailLineExpirationQty.class, id);
        } finally {
            em.close();
        }
    }

    public int getReturnTranDetailLineExpirationQtyCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from ReturnTranDetailLineExpirationQty as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
