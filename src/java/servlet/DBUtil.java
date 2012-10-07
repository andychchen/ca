/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.util.List;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import jpa.entities.EmpUsers;

/**
 *
 * @author achen
 */
@PersistenceContext(name = "persistence/LogicalName", unitName = "caPU")
public class DBUtil {

    @Resource
    private UserTransaction utx;

    public List<EmpUsers> findEmpUsers() {
        List<EmpUsers> result = null;
        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("persistence/LogicalName");
            Query q = em.createNamedQuery("EmpUsers.findAll");
            result =
                    q.getResultList();
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public EmpUsers findEmpUserByID(String id) {
        List<EmpUsers> list = findEmpUsers();
        if (list == null) {
            return null;
        }

        for (int i = 0; i <
                list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                return list.get(i);
            }

        }
        return null;
    }
}
