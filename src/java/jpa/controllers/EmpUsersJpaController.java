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
import jpa.entities.EmpUsers;
import jpa.entities.TranHead;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author achen
 */
public class EmpUsersJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "caPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmpUsers empUsers) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (empUsers.getTranHeadCollection() == null) {
            empUsers.setTranHeadCollection(new ArrayList<TranHead>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<TranHead> attachedTranHeadCollection = new ArrayList<TranHead>();
            for (TranHead tranHeadCollectionTranHeadToAttach : empUsers.getTranHeadCollection()) {
                tranHeadCollectionTranHeadToAttach = em.getReference(tranHeadCollectionTranHeadToAttach.getClass(), tranHeadCollectionTranHeadToAttach.getTranHeadId());
                attachedTranHeadCollection.add(tranHeadCollectionTranHeadToAttach);
            }
            empUsers.setTranHeadCollection(attachedTranHeadCollection);
            em.persist(empUsers);
            /*for (TranHead tranHeadCollectionTranHead : empUsers.getTranHeadCollection()) {
                EmpUsers oldUserIdOfTranHeadCollectionTranHead = tranHeadCollectionTranHead.getUserId();
                tranHeadCollectionTranHead.setUserId(empUsers);
                tranHeadCollectionTranHead = em.merge(tranHeadCollectionTranHead);
                if (oldUserIdOfTranHeadCollectionTranHead != null) {
                    oldUserIdOfTranHeadCollectionTranHead.getTranHeadCollection().remove(tranHeadCollectionTranHead);
                    oldUserIdOfTranHeadCollectionTranHead = em.merge(oldUserIdOfTranHeadCollectionTranHead);
                }
            }*/
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEmpUsers(empUsers.getId()) != null) {
                throw new PreexistingEntityException("EmpUsers " + empUsers + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmpUsers empUsers) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            EmpUsers persistentEmpUsers = em.find(EmpUsers.class, empUsers.getId());
            Collection<TranHead> tranHeadCollectionOld = persistentEmpUsers.getTranHeadCollection();
            Collection<TranHead> tranHeadCollectionNew = empUsers.getTranHeadCollection();
            Collection<TranHead> attachedTranHeadCollectionNew = new ArrayList<TranHead>();
            for (TranHead tranHeadCollectionNewTranHeadToAttach : tranHeadCollectionNew) {
                tranHeadCollectionNewTranHeadToAttach = em.getReference(tranHeadCollectionNewTranHeadToAttach.getClass(), tranHeadCollectionNewTranHeadToAttach.getTranHeadId());
                attachedTranHeadCollectionNew.add(tranHeadCollectionNewTranHeadToAttach);
            }
            tranHeadCollectionNew = attachedTranHeadCollectionNew;
            empUsers.setTranHeadCollection(tranHeadCollectionNew);
            empUsers = em.merge(empUsers);
            /*for (TranHead tranHeadCollectionOldTranHead : tranHeadCollectionOld) {
                if (!tranHeadCollectionNew.contains(tranHeadCollectionOldTranHead)) {
                    tranHeadCollectionOldTranHead.setUserId(null);
                    tranHeadCollectionOldTranHead = em.merge(tranHeadCollectionOldTranHead);
                }
            }
            for (TranHead tranHeadCollectionNewTranHead : tranHeadCollectionNew) {
                if (!tranHeadCollectionOld.contains(tranHeadCollectionNewTranHead)) {
                    EmpUsers oldUserIdOfTranHeadCollectionNewTranHead = tranHeadCollectionNewTranHead.getUserId();
                    tranHeadCollectionNewTranHead.setUserId(empUsers);
                    tranHeadCollectionNewTranHead = em.merge(tranHeadCollectionNewTranHead);
                    if (oldUserIdOfTranHeadCollectionNewTranHead != null && !oldUserIdOfTranHeadCollectionNewTranHead.equals(empUsers)) {
                        oldUserIdOfTranHeadCollectionNewTranHead.getTranHeadCollection().remove(tranHeadCollectionNewTranHead);
                        oldUserIdOfTranHeadCollectionNewTranHead = em.merge(oldUserIdOfTranHeadCollectionNewTranHead);
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
                String id = empUsers.getId();
                if (findEmpUsers(id) == null) {
                    throw new NonexistentEntityException("The empUsers with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            EmpUsers empUsers;
            try {
                empUsers = em.getReference(EmpUsers.class, id);
                empUsers.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empUsers with id " + id + " no longer exists.", enfe);
            }
            Collection<TranHead> tranHeadCollection = empUsers.getTranHeadCollection();
            for (TranHead tranHeadCollectionTranHead : tranHeadCollection) {
                tranHeadCollectionTranHead.setUserId(null);
                tranHeadCollectionTranHead = em.merge(tranHeadCollectionTranHead);
            }
            em.remove(empUsers);
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

    public List<EmpUsers> findEmpUsersEntities() {
        return findEmpUsersEntities(true, -1, -1);
    }

    public List<EmpUsers> findEmpUsersEntities(int maxResults, int firstResult) {
        return findEmpUsersEntities(false, maxResults, firstResult);
    }

    private List<EmpUsers> findEmpUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from EmpUsers as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EmpUsers findEmpUsers(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmpUsers.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpUsersCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from EmpUsers as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
