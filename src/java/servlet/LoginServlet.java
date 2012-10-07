/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import jpa.entities.EmpUsers;

/**
 *
 * @author achen
 */
@PersistenceContext(name = "persistence/LogicalName", unitName = "caPU")
public class LoginServlet extends HttpServlet {

    @Resource
    private UserTransaction utx;

    protected List<EmpUsers> findEmpUsers() {
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

    protected EmpUsers findEmpUserByID(String id) {
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String uid = request.getParameter("pwd");
            EmpUsers user = findEmpUserByID(uid);
            //ServletContext context = getServletContext();
            String pageToGo = "";

            if (user == null) {
                pageToGo = "/faces/login.jsp";
            } else {
                pageToGo = "/faces/index.jsp";
                request.getSession().setAttribute("empName", user.getUserName());
                request.getSession().setAttribute("empRole", user.getRole());
                request.getSession().setAttribute("empId", user.getId());
                request.getSession().setAttribute("empUser", user);
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pageToGo);
            dispatcher.forward(request, response);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
