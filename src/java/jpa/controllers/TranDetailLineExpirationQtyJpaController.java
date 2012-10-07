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
import jpa.entities.TranDetail;
import jpa.entities.TranDetailLineExpirationQty;
import jpa.entities.TranDetailLineExpirationQtyPK;

/**
 *
 * @author achen
 */
public class TranDetailLineExpirationQtyJpaController {

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

    public void create(TranDetailLineExpirationQty tranDetailLineExpirationQty) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (tranDetailLineExpirationQty.getTranDetailLineExpirationQtyPK() == null) {
            tranDetailLineExpirationQty.setTranDetailLineExpirationQtyPK(new TranDetailLineExpirationQtyPK());
        }
        tranDetailLineExpirationQty.getTranDetailLineExpirationQtyPK().setTranDetailId(tranDetailLineExpirationQty.getTranDetail().getTranDetailId());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TranDetail tranDetail = tranDetailLineExpirationQty.getTranDetail();
            if (tranDetail != null) {
                tranDetail = em.getReference(tranDetail.getClass(), tranDetail.getTranDetailId());
                tranDetailLineExpirationQty.setTranDetail(tranDetail);
            }
            em.persist(tranDetailLineExpirationQty);
            if (tranDetail != null) {
                tranDetail.getTranDetailLineExpirationQtyCollection().add(tranDetailLineExpirationQty);
                tranDetail = em.merge(tranDetail);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTranDetailLineExpirationQty(tranDetailLineExpirationQty.getTranDetailLineExpirationQtyPK()) != null) {
                throw new PreexistingEntityException("TranDetailLineExpirationQty " + tranDetailLineExpirationQty + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public void edit(TranDetailLineExpirationQty tranDetailLineExpirationQty) throws NonexistentEntityException, RollbackFailureException, Exception {
        tranDetailLineExpirationQty.getTranDetailLineExpirationQtyPK().setTranDetailId(tranDetailLineExpirationQty.getTranDetail().getTranDetailId());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TranDetailLineExpirationQty persistentTranDetailLineExpirationQty = em.find(TranDetailLineExpirationQty.class, tranDetailLineExpirationQty.getTranDetailLineExpirationQtyPK());
            TranDetail tranDetailOld = persistentTranDetailLineExpirationQty.getTranDetail();
            TranDetail tranDetailNew = tranDetailLineExpirationQty.getTranDetail();
            if (tranDetailNew != null) {
                tranDetailNew = em.getReference(tranDetailNew.getClass(), tranDetailNew.getTranDetailId());
                tranDetailLineExpirationQty.setTranDetail(tranDetailNew);
            }
            tranDetailLineExpirationQty = em.merge(tranDetailLineExpirationQty);
            if (tranDetailOld != null && !tranDetailOld.equals(tranDetailNew)) {
                tranDetailOld.getTranDetailLineExpirationQtyCollection().remove(tranDetailLineExpirationQty);
                tranDetailOld = em.merge(tranDetailOld);
            }
            if (tranDetailNew != null && !tranDetailNew.equals(tranDetailOld)) {
                tranDetailNew.getTranDetailLineExpirationQtyCollection().add(tranDetailLineExpirationQty);
                tranDetailNew = em.merge(tranDetailNew);
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
                TranDetailLineExpirationQtyPK id = tranDetailLineExpirationQty.getTranDetailLineExpirationQtyPK();
                if (findTranDetailLineExpirationQty(id) == null) {
                    throw new NonexistentEntityException("The tranDetailLineExpirationQty with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null && !isFromOutside) {
                em.close();
            }
        }
    }

    public void destroy(TranDetailLineExpirationQtyPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TranDetailLineExpirationQty tranDetailLineExpirationQty;
            try {
                tranDetailLineExpirationQty = em.getReference(TranDetailLineExpirationQty.class, id);
                tranDetailLineExpirationQty.getTranDetailLineExpirationQtyPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tranDetailLineExpirationQty with id " + id + " no longer exists.", enfe);
            }
            TranDetail tranDetail = tranDetailLineExpirationQty.getTranDetail();
            if (tranDetail != null) {
                tranDetail.getTranDetailLineExpirationQtyCollection().remove(tranDetailLineExpirationQty);
                tranDetail = em.merge(tranDetail);
            }
            em.remove(tranDetailLineExpirationQty);
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

    public List<TranDetailLineExpirationQty> findTranDetailLineExpirationQtyEntities() {
        return findTranDetailLineExpirationQtyEntities(true, -1, -1);
    }

    public List<TranDetailLineExpirationQty> findTranDetailLineExpirationQtyEntities(int maxResults, int firstResult) {
        return findTranDetailLineExpirationQtyEntities(false, maxResults, firstResult);
    }

    private List<TranDetailLineExpirationQty> findTranDetailLineExpirationQtyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TranDetailLineExpirationQty as o");
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

    public TranDetailLineExpirationQty findTranDetailLineExpirationQty(TranDetailLineExpirationQtyPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TranDetailLineExpirationQty.class, id);
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }

    public int getTranDetailLineExpirationQtyCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TranDetailLineExpirationQty as o").getSingleResult()).intValue();
        } finally {
            if (!isFromOutside) {
                em.close();
            }
        }
    }
}
