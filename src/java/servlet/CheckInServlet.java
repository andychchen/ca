/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.controllers.ProductExpirationQtyJpaController;
import jpa.controllers.ProductJpaController;
import jpa.controllers.YesNoJpaController;
import jpa.entities.Product;
import jpa.entities.ProductExpirationQty;
import jpa.entities.YesNo;
import jpa.entities.ProductExpirationQtyPK;

/**
 *
 * @author achen
 */
@PersistenceContext(name = "persistence/LogicalName", unitName = "caPU")
public class CheckInServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Resource
    private javax.transaction.UserTransaction utx;
    Product product;
    ProductExpirationQty productExpirationQty;
    ProductJpaController productJpaController;
    ProductExpirationQtyJpaController productExpirationQtyJpaController;
    ProductExpirationQtyPK productExpirationQtyPK;
    YesNoJpaController yesNoJpaController;

    protected void reset() {
        product = null;
        productExpirationQty = null;
    }

    protected void setupController() {
        if (productJpaController == null) {
            productJpaController = new ProductJpaController();
            productJpaController.setUtx(utx);
        }
        if (productExpirationQtyJpaController == null) {
            productExpirationQtyJpaController = new ProductExpirationQtyJpaController();
            productExpirationQtyJpaController.setUtx(utx);
        }
        if (yesNoJpaController == null) {
            yesNoJpaController = new YesNoJpaController();
            yesNoJpaController.setUtx(utx);
        }
    }

    protected boolean doDatabase(String isbn, String qty, String experationDate, StringBuffer message) {
        try {
            reset();
            setupController();
            ProductExpirationQtyPK productExpirationQtyPK = null;
            BigDecimal addQty = new BigDecimal(qty);
            product = productJpaController.findProduct(isbn);
            if (product == null) {
                product = new Product();
                char yesNo = 'N';
                if (experationDate.length() != 0) {
                    yesNo = 'Y';
                }
                product.setHasExpirationDate(yesNoJpaController.findYesNo(yesNo));
                product.setIsbn(isbn);
                product.setPrice(BigDecimal.ZERO);
                productJpaController.create(product);
            }
            BigDecimal originalQty = product.getQty();
            if (originalQty == null) {
                originalQty = new BigDecimal(0);
            }
            product.setQty(originalQty.add(addQty));
            if (product.getHasExpirationDate() == null || product.getHasExpirationDate().getName() == null || product.getHasExpirationDate().getName() == 'N') {

                productJpaController.edit(product);
                return true;
            }
            if (experationDate.length() == 0) {
                message.append("This product needs Experation Date!");
                return false;
            }
            productExpirationQtyPK = new ProductExpirationQtyPK(isbn, new Date(experationDate));


            productExpirationQty = productExpirationQtyJpaController.findProductExpirationQty(productExpirationQtyPK);

            if (productExpirationQty == null) {
                productExpirationQty = new ProductExpirationQty(productExpirationQtyPK, new BigDecimal(0));
                productExpirationQty.setProduct(product);
                productExpirationQtyJpaController.create(productExpirationQty);
            }

            BigDecimal originalQtyInt = productExpirationQty.getQty();
            productExpirationQty.setQty(originalQtyInt.add(addQty));
            productExpirationQtyJpaController.edit(productExpirationQty);

            product = productJpaController.findProduct(isbn);
            originalQty = product.getQty();
            if (originalQty == null) {
                originalQty = new BigDecimal(0);
            }
            product.setQty(originalQty.add(addQty));
            productJpaController.edit(product);
        } catch (Exception e) {
            e.printStackTrace();
            message.append(e.toString());
            return false;
        }
        return true;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String experationDate = request.getParameter("experationDate");
            if (experationDate == null) {
                experationDate = "";
            }
            experationDate = experationDate.trim();
            String isbn = request.getParameter("isbn");
            String qty = request.getParameter("qty");
            StringBuffer message = new StringBuffer();
            boolean isResultGood = false;
            //Product product = null;
            //ProductExpirationQty productExpirationQty = null;
            //Boolean isNewProduct = new Boolean(false);
            //Boolean isNewProductExperationQty = new Boolean(false);

            if (isbn != null && qty != null && isbn.length() > 0 && qty.length() > 0) {
                isResultGood = doDatabase(isbn, qty, experationDate, message);
            }


            out.println("<html>");
            out.println("<head>");
            out.println("<title>Check In</title>");
            out.println("<link rel=\"stylesheet\" href=\"/ca/faces/calendarview.css\">\n    <style>\n      body {\n        font-family: Trebuchet MS;\n      }\n      div.calendar {\n        max-width: 240px;\n        margin-left: auto;\n        margin-right: auto;\n      }\n      div.calendar table {\n        width: 100%;\n      }\n      div.dateField {\n        width: 140px;\n        padding: 6px;\n        -webkit-border-radius: 6px;\n        -moz-border-radius: 6px;\n        color: #555;\n        background-color: white;\n        margin-left: auto;\n        margin-right: auto;\n        text-align: center;\n      }\n      div#popupDateField:hover {\n        background-color: #cde;\n        cursor: pointer;\n      }\n    </style>\n    <script src=\"/ca/faces/prototype.js\"></script>\n    <script src=\"/ca/faces/calendarview.js\"></script>\n    <script>\n      function setupCalendars() {\n        // Embedded Calendar\n        Calendar.setup(\n          {\n            dateField: 'experationDate',\n            parentElement: 'embeddedCalendar'\n          }\n        )\n\n        // Popup Calendar\n        Calendar.setup(\n          {\n            dateField: 'popupDateField',\n            triggerElement: 'popupDateField'\n          }\n        )\n      }\n\n      Event.observe(window, 'load', function() { setupCalendars() })\n    </script>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/ca/faces/jsfcrud.css\" /><script type=\"text/javascript\" src=\"/ca/faces/jsfcrud.js\"></script>");
            out.println("</head>");
            out.println("<body OnLoad=\"document.myForm.isbn.focus();\">");
            out.println("<h1>Check In Item</h1>");

            out.println("<br><h2>Please make sure the Experation Date is Correct before Scan</h2>");

            out.println("<form method=\"post\" name=\"myForm\" action=\"/ca/CheckIn\">");
            out.println("<table><tr><td><br><br><br><br><br><br>Experation Date ( MM/DD/YYYY ): <input type='text' id='experationDate' name='experationDate' size='18' value='");
            out.println(experationDate.trim());

            out.println("'/></td>");
            out.println("<td><div style=\"height: 190px; background-color: #efefef; padding: 10px; -webkit-border-radius: 12px; -moz-border-radius: 12px; margin-right: 10px\">\n\n        <div id=\"embeddedExample\" style=\"\">\n          <div id=\"embeddedCalendar\" style=\"margin-left: auto; margin-right: auto\">\n          </div>\n          <br />\n\n          <br />\n        </div>\n      </div></td></tr></table>");
            //out.println("<div style=\"height: 190px;width: 400px; background-color: #efefef; padding: 10px; -webkit-border-radius: 12px; -moz-border-radius: 12px; margin-right: 10px\">\n\n        <div id=\"embeddedExample\" style=\"\">\n          <div id=\"embeddedCalendar\" style=\"margin-left: auto; margin-right: auto\">\n Experation Date ( MM/DD/YYYY ): <input type='text' id='experationDate' name='experationDate' size='18'/>          </div>\n          <br />\n\n          <br />\n        </div>\n      </div>");
            out.println("<br></br>");
            out.println("Scan / Enter ISBN: <input type='text' name='isbn' size='18'/>");
            out.println("Qty to Add: <input type='text' name='qty' value='1' size='2'/>");
            out.println("<input type=submit value='Add' />");
            out.println("</form>");


            out.println("<br><br>");

            if (!isResultGood) {
                out.println("<h3><font color=\"RED\">" + message.toString() + "</font></h3>" + "<br>");
            } else {
                out.println("Product - ");
                if (product != null) {
                    if (product.getName() != null && product.getName().length() > 0) {
                        out.println(product.getName() + " - ");
                    }
                    out.println(product.getIsbn());
                    if (productExpirationQty != null) {
                        out.println(" expires on " + new SimpleDateFormat("MM/dd/yyyy").format(productExpirationQty.getProductExpirationQtyPK().getExpirationDate()));
                    }
                    out.println(" Qty is updated to ");
                    if (productExpirationQty != null) {
                        out.println(decimalCheck(productExpirationQty.getQty()));
                    } else {
                        out.println(decimalCheck(product.getQty()));
                    }
                    out.println("<br>");
                }
            }
            out.println("<br><br><a href=\"/ca/faces/index.jsp\">Main Menu</a>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            out.close();
        }
    }

    protected String decimalCheck(BigDecimal in) {
        double remain = in.subtract(new BigDecimal(in.intValue())).doubleValue();

        if (remain >= 0.01 || remain <= -0.01) {
            return in.toString();
        }
        return in.toBigInteger().toString();
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

    protected void persist(Object object) {
        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("persistence/LogicalName");
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
