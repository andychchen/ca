/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.controllers.ProductExpirationQtyJpaController;
import jpa.controllers.ProductJpaController;
import jpa.entities.Product;
import jpa.entities.ProductExpirationQty;
import jpa.entities.YesNo;
import jpa.entities.ProductExpirationQtyPK;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import jpa.controllers.TranDetailJpaController;
import jpa.controllers.TranDetailLineExpirationQtyJpaController;
import jpa.controllers.TranHeadJpaController;
import jpa.controllers.TranPaymentJpaController;
import jpa.entities.EmpUsers;
import jpa.entities.PaymentType;
import jpa.entities.Promotion;
import jpa.entities.Tax;
import jpa.entities.TranDetail;
import jpa.entities.TranDetailLineExpirationQty;
import jpa.entities.TranHead;
import jpa.entities.TranPayment;
import jpa.entities.extn.TranHeadExtn;
import jsf.util.DBProcessor;

/**
 *
 * @author achen
 */
@PersistenceContext(name = "persistence/LogicalName", unitName = "caPU")
public class CheckOutServlet extends HttpServlet {

    static final protected String Mode = "mode";
    static final protected String ReturnMode = "returnMode";
    static final protected String CheckOutMode = "checkOutMode";
    static final protected String IsJustSaved = "IsJustSaved";
    static final protected String WhenTimeoutHappen = "WhenTimeoutHappen";
    static final protected String Add = "Add";
    static final protected String Remove = "REMOVE";
    static final protected String SetBottleReturn = "Bottle Return";
    static final protected String Promotion = "Promotion";
    static final protected String ToDo = "toDo";
    static final protected String BackToCheckout = "Back To Checkout";
    static final protected String Subtotal = "Subtotal";
    static final protected String Cancel = "Cancel";
    static final protected String TranHeadInServlet = "tranHeadInServlet";
    static final protected String ProductInServlet = "productInServlet";
    static final protected String ProductExpirationQty = "productExpirationQtyInServlet";
    static final protected String SetToTrainingMode = "Set To Training Mode";
    static final protected String RemoveTrainingMode = "Remove Training Mode";
    static final protected String Pay = "Pay";
    static final protected String Save = "Save";
    static final protected String Return = "return";
    static final protected String Find = "Find";
    static final protected String TranHeadId = "tranHeadId";
    static final protected String Done = "Done";
    static final protected String ReturnCartInServlet = "returnCartInServlet";
    static final protected String FindOK = "Find OK";
    static final protected String ExpirationDate = "expirationDate";
    static final protected String ConfirmExpirationDate = "confirmExpirationDate";
    static final protected String AddExpirationDate = "addExpirationDate";
    static final protected String AddTemp = "Add Temp";
    static final protected String Isbn = "isbn";
    static final protected String Qty = "qty";
    static final protected String EmpUser = "empUser";
    static protected Vector<TranHeadExtn> paymentTranDetailVector;
    static protected Vector<TranHeadExtn> invertoryVector;
    static protected Vector<TranHeadExtn> returnInvertoryVector;
    static protected DBProcessor dbProcessor;
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Resource
    private UserTransaction utx;
    //Product product;
    //ProductExpirationQty productExpirationQty;
    //ProductExpirationQtyPK productExpirationQtyPK;
    //////////////////
    ProductJpaController productJpaController;
    ProductExpirationQtyJpaController productExpirationQtyJpaController;
    /////////////////
    TranHeadJpaController tranHeadJpaController;
    TranDetailJpaController tranDetailJpaController;
    TranPaymentJpaController tranPaymentJpaController;
    TranDetailLineExpirationQtyJpaController tranDetailLineExpirationQtyJpaController;

    /*protected void reset() {
    product = null;
    productExpirationQty = null;
    }*/
    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        //setupController();
        paymentTranDetailVector = new Vector<TranHeadExtn>();
        invertoryVector = new Vector<TranHeadExtn>();
        returnInvertoryVector = new Vector<TranHeadExtn>();
    //dbProcessor = new DBProcessor(paymentTranDetailVector, invertoryVector, returnInvertoryVector, tranDetailJpaController, tranPaymentJpaController, tranDetailLineExpirationQtyJpaController, productJpaController, productExpirationQtyJpaController);
    //dbProcessor.start();
    //System.out.println("INIT DONE!");
    }

    public void startDBProcessor() {
        // TODO Auto-generated method stub
        if (DBProcessor.isRunning.get()) {
            return;
        }
        setupController();
        dbProcessor = new DBProcessor(paymentTranDetailVector, invertoryVector, returnInvertoryVector, tranDetailJpaController, tranPaymentJpaController, tranDetailLineExpirationQtyJpaController, productJpaController, productExpirationQtyJpaController);
        dbProcessor.setPriority(Thread.MIN_PRIORITY);
        dbProcessor.start();
        System.out.println("DB Processor started!");
    }

    public void destroy() {
        // TODO Auto-generated method stub
        //dbProcessor.stop();
        dbProcessor = null;
        System.out.println("Destroied!");
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
        if (tranHeadJpaController == null) {
            tranHeadJpaController = new TranHeadJpaController();
            tranHeadJpaController.setUtx(utx);
        }
        if (tranDetailJpaController == null) {
            tranDetailJpaController = new TranDetailJpaController();
            tranDetailJpaController.setUtx(utx);
        }
        if (tranPaymentJpaController == null) {
            tranPaymentJpaController = new TranPaymentJpaController();
            tranPaymentJpaController.setUtx(utx);
        }
        if (tranDetailLineExpirationQtyJpaController == null) {
            tranDetailLineExpirationQtyJpaController = new TranDetailLineExpirationQtyJpaController();
            tranDetailLineExpirationQtyJpaController.setUtx(utx);
        }
    }

    protected void setMode(HttpServletRequest request) {
        String toDo = nullStringToBlankAndTrim(request.getParameter(ToDo));
        if (toDo == null || toDo.trim().length() == 0) {
            addObjectBackToSession(request, CheckOutMode, Mode);
        } else if (toDo.equals(ReturnMode)) {
            addObjectBackToSession(request, ReturnMode, Mode);
        }
    }

    /*protected boolean doDatabase(String isbn, String qty, String experationDate, StringBuffer message) {
    try {
    reset();
    setupController();
    ProductExpirationQtyPK productExpirationQtyPK = null;
    BigDecimal addQty = new BigDecimal(qty);
    product = productJpaController.findProduct(isbn);
    if (product == null) {
    product = new Product();

    YesNo yesNo = new YesNo();
    char ans = 'Y';
    if (experationDate.length() == 0) {
    ans = 'N';
    }
    yesNo.setName(ans);
    product.setHasExpirationDate(yesNo);
    product.setIsbn(isbn);
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
    }*/
    protected void doPromotion(HttpServletRequest request, boolean isToRemove) {
        applyPromotionToNoPromoProduct(request, nullStringToBlankAndTrim(request.getParameter("Id")), isToRemove);
    }

    protected void doBottleReturn(HttpServletRequest request) {
        TranHeadExtn tranHead = getTranHeadFromSession(request);
        BigDecimal bottleReturn = null;
        try {
            bottleReturn = new BigDecimal(request.getParameter("bottleReturn"));
        } catch (Exception e) {
        }
        if (bottleReturn != null) {
            tranHead.setBottleRefund(TranDetail.roundToTwo(bottleReturn));
            addTranHeadBackToSession(request, tranHead);
        }
    }

    protected void doPay(HttpServletRequest request) {
        TranHeadExtn tranHead = getTranHeadFromSession(request);
        PaymentType pt = findPaymentTypeByID(nullStringToBlankAndTrim(request.getParameter("id")));
        if (pt.getName().equals("Gift Certifcate")) {
            if (new BigDecimal(request.getParameter("amount")).compareTo(tranHead.getBalance()) > 0) {
                return;
            }
        }
        tranHead.addOrPlusTranPayment(pt, request.getParameter("amount"));
        addTranHeadBackToSession(request, tranHead);
        if (isThisReturnTransaction(request)) {
            TranHeadExtn returnCart = getReturnCartFromSession(request);
            returnCart.addOrPlusTranPayment(pt, request.getParameter("amount"));
            addReturnCartBackToSession(request, returnCart);
        }
    }

    protected boolean doFind(HttpServletRequest request, StringBuffer message) throws Exception {
        String id = nullStringToBlankAndTrim(request.getParameter(TranHeadId));
        TranHeadExtn tranHead = findTranHeadByID(id);
        if (tranHead != null) {
            //tranHead.subtotal();
            addTranHeadBackToSession(request, tranHead);
        } else {
            message.append("Receipt ID: " + id + " can not be found!");
            return false;
        }
        return true;
    }

    protected void doInventory(HttpServletRequest request, TranHeadExtn tranHead) {
        //TranHeadExtn tranHead = null;
        if (isThisReturnTransaction(request)) {
            returnInvertoryVector.add(tranHead);
        } else {
            invertoryVector.add(tranHead);
        }
    /*try {

    //if (isThisReturnTransaction(request)) {
    // tranHead = getReturnCartFromSession(request);
    //} else {
    //tranHead = getTranHeadFromSession(request);
    //}
    setupController();
    List<TranDetail> list = (List<TranDetail>) tranHead.getTranDetailCollection();
    if (list == null || list.size() == 0) {
    return;
    }
    for (int i = 0; i < list.size(); i++) {
    TranDetail td = list.get(i);
    Product product = productJpaController.findProduct(td.getIsbn().getIsbn());
    BigDecimal oldQty = product.getQty();
    if (oldQty == null) {
    oldQty = BigDecimal.ZERO;
    }
    BigDecimal qtyToAdd = td.getQty();
    if (qtyToAdd == null) {
    qtyToAdd = BigDecimal.ZERO;
    }
    if (isThisReturnTransaction(request)) {
    qtyToAdd = qtyToAdd.multiply(new BigDecimal(-1));
    }
    product.setQty(oldQty.subtract(qtyToAdd));
    productJpaController.edit(product);
    if (product.getHasExpirationDate() != null && product.getHasExpirationDate().getName().equals(new Character('Y'))) {
    ArrayList<TranDetailLineExpirationQty> eList = (ArrayList<TranDetailLineExpirationQty>) td.getTranDetailLineExpirationQtyCollection();
    if (eList == null || eList.size() == 0) {
    continue;
    }
    for (int y = 0; y < eList.size(); y++) {
    ProductExpirationQtyPK ppk = new ProductExpirationQtyPK();
    ppk.setIsbn(product.getIsbn());
    ppk.setExpirationDate(eList.get(y).getTranDetailLineExpirationQtyPK().getExpirationDate());
    ProductExpirationQty productExpirationQty = productExpirationQtyJpaController.findProductExpirationQty(ppk);
    oldQty = productExpirationQty.getQty();
    qtyToAdd = eList.get(y).getQty();
    if (isThisReturnTransaction(request)) {
    qtyToAdd = qtyToAdd.multiply(new BigDecimal(-1));
    }
    productExpirationQty.setQty(oldQty.subtract(qtyToAdd));
    productExpirationQtyJpaController.edit(productExpirationQty);
    }
    }
    }
    } catch (Exception e) {
    e.printStackTrace();
    }*/
    }

    protected void doSaveTranHeadToDB(TranHeadExtn tranHead, PrintWriter out, boolean isForReceipt, HttpServletRequest request) {
        setupController();
        Object tranDetails = tranHead.getTranDetailCollection();
//        System.err.println(((List<TranDetail>) tranDetails).size());
        Object tranPayments = tranHead.getTranPaymentCollection();
        //Object oriTranTranHeads = tranHead.getTranHeadCollection();

        tranHead.setTranDetailCollection(null);
        tranHead.setTranPaymentCollection(null);

        try {
            tranHeadJpaController.create(tranHead);
            tranHead.setTranDetailCollection((List<TranDetail>) tranDetails);
            tranHead.setTranPaymentCollection((List<TranPayment>) tranPayments);

        //tranHead.saveTranPayment(tranPaymentJpaController);
        //tranHead.saveTranDetails(tranDetailJpaController, tranDetailLineExpirationQtyJpaController);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void savePaymentAndTranDetailToDB(TranHeadExtn tranHead, TranHeadExtn returnCart, HttpServletRequest request) {


        try {
            paymentTranDetailVector.add(tranHead);
            //tranHead.saveTranPayment(tranPaymentJpaController);
            //tranHead.saveTranDetails(tranDetailJpaController, tranDetailLineExpirationQtyJpaController);
            if (isThisReturnTransaction(request)) {
                paymentTranDetailVector.add(returnCart);
            //returnCart.saveTranPayment(tranPaymentJpaController);
            //returnCart.saveTranDetails(tranDetailJpaController, tranDetailLineExpirationQtyJpaController);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doSave(HttpServletRequest request, PrintWriter out, boolean isForReceipt) {
        TranHeadExtn tranHead = getTranHeadFromSession(request);
        setupController();

        if (isThisReturnTransaction(request)) {
            tranHead.setTranHeadId(null);
            tranHead.setTranDate(new Date());
            tranHead.setUserId((EmpUsers) getObjectFromSession(request, EmpUser));
        }
        doSaveTranHeadToDB(tranHead, out, isForReceipt, request);
        addTranHeadBackToSession(request, tranHead);
        if (isThisReturnTransaction(request)) {
            TranHeadExtn returnCart = getReturnCartFromSession(request);
            returnCart.setOriginalHeadId(tranHead);
            doSaveTranHeadToDB(returnCart, out, isForReceipt, request);
            addReturnCartBackToSession(request, returnCart);
        }
        //Get them out before the get clean up by the Check out page or...
        tranHead = getTranHeadFromSession(request);
        TranHeadExtn returnCart = getReturnCartFromSession(request);
        //now we can let them to be clean up                
        printCart(tranHead, returnCart, out, request, isForReceipt, false);
        //out.close();

        doCancel(request);
        addObjectBackToSession(request, new Boolean(true), IsJustSaved);

        savePaymentAndTranDetailToDB(tranHead, returnCart, request);

        if (isThisReturnTransaction(request)) {
            doInventory(request, returnCart);
        } else {
            doInventory(request, tranHead);
        }
        startDBProcessor();
    }

    protected void doCancel(HttpServletRequest request) {
        //TranHeadExtn tranHead = getTranHeadFromSession(request);
        //HttpSession session = request.getSession(true);
        //session.removeAttribute(TranHeadInServlet);
        //session.removeAttribute(ReturnCartInServlet);
        //tranHead = null;
        addTranHeadBackToSession(request, null);
        addProductBackToRequest(request, null);
        addProductExpirationQtyBackToRequest(request, null);
        addReturnCartBackToSession(request, null);
    }

    protected void doSetTrainingMode(HttpServletRequest request, boolean isTraining) {
        TranHeadExtn tranHead = getTranHeadFromSession(request);
        char ans = 'Y';
        if (!isTraining) {
            ans = 'N';
        }
        tranHead.setIsTrainingMode(new YesNo(ans));
    }

    protected boolean isNeedAskForExpirationDate(Product product, HttpServletRequest request, ArrayList<String> dates) {
        if (!isYes(product.getHasExpirationDate())) {
            return false;
        }
        //boolean gotOne = false;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        int needCount = 1;

        if (!isThisReturnTransaction(request) && new BigDecimal(getAddTempOrNotTemp(request, Qty)).compareTo(BigDecimal.ZERO) > 0) {
            List<ProductExpirationQty> list = (List<ProductExpirationQty>) product.getProductExpirationQtyCollection();
            if (list == null || list.size() < 1) {
                return false;
            }
            for (int i = 0; i < list.size(); i++) {
                ProductExpirationQty peq = list.get(i);
                if (peq.getQty().compareTo(new BigDecimal(0.001)) > 0 && !dates.contains(peq.getProductExpirationQtyPK().getExpirationDate())) {
                    dates.add(sdf.format(peq.getProductExpirationQtyPK().getExpirationDate()));
                }
            }
        } else {
            needCount = 0;
            List<TranDetailLineExpirationQty> list = getTranDetailLineExpirationQtyListFromTranDetailTranHeadFromSessionByID(request, product.getIsbn());
            if (list == null || list.size() < 1) {
                return false;
            }
            for (int i = 0; i < list.size(); i++) {
                TranDetailLineExpirationQty tdeqt = list.get(i);
                if (tdeqt.getQty().compareTo(new BigDecimal(0.01)) > 0 && !dates.contains(tdeqt.getTranDetailLineExpirationQtyPK().getExpirationDate())) {
                    dates.add(sdf.format(tdeqt.getTranDetailLineExpirationQtyPK().getExpirationDate()));
                }
            }
        }
        if (dates.size() <= needCount) {
            return false;
        }
        return true;
    }

    protected boolean isAddTempRequest(HttpServletRequest request) {
        String toDo = request.getParameter(ToDo);
        if (toDo != null && toDo.equals(AddTemp)) {
            return true;
        }
        return false;
    }

    protected Product checkScanedProduct(HttpServletRequest request, PrintWriter out, StringBuffer message) {
        String isbn = getAddTempOrNotTemp(request, Isbn).trim();
        String qtyString = getAddTempOrNotTemp(request, Qty);
        if (qtyString == null || qtyString.trim().length() == 0) {
            qtyString = "0";
        }
        BigDecimal qty = new BigDecimal(qtyString);

        Product product;


        if (!isThisReturnTransaction(request) && qty.compareTo(BigDecimal.ZERO) >= 0) {
            product = productJpaController.findProduct(isbn);
        } else {
            //If this is an return product or qty < 0 product
            product = getProductFromTranHeadFromSessionById(request, isbn);
        }
        if (product == null) {
            message.append("Product " + isbn + " is not found!");
            return null;
        }
        addProductBackToRequest(request, product);
        return product;
    }

    protected boolean isYes(YesNo ans) {
        if (ans != null && ans.getName().toString().trim().toUpperCase().equals("Y")) {
            return true;
        }
        return false;
    }

    protected boolean doAdd(Product product, HttpServletRequest request, StringBuffer message, String expirationDate) {




        //if (tranHead.getTranDetailCollection() == null) {
        // tranHead.setTranDetailCollection(new ArrayList<TranDetail>());
        //}
        //TranDetail tranDetail = new TranDetail();
        //String isbn = request.getParameter("isbn");
        //String expirationDate = request.getParameter(ExpirationDate);

        BigDecimal qty = new BigDecimal(getAddTempOrNotTemp(request, Qty));


        //String expirationDate = request.getParameter(ExpirationDate);

        ProductExpirationQty productExpirationQty = null;
        if (isYes(product.getHasExpirationDate())) {
            if (expirationDate.length() == 0) {
                message.append(product + " needs Expiration Date!");
                return false;
            }
            ProductExpirationQtyPK productExpirationQtyPK = new ProductExpirationQtyPK(product.getIsbn(), new Date(expirationDate));
            if (!isThisReturnTransaction(request)) {
                productExpirationQty = productExpirationQtyJpaController.findProductExpirationQty(productExpirationQtyPK);
            } else {
                TranDetailLineExpirationQty tdeq = getTranDetailLineExpirationQtyFromTranHeadFromSessionByPk(request, productExpirationQtyPK);
                if (tdeq != null) {
                    productExpirationQty = new ProductExpirationQty();
                    productExpirationQty.setProduct(product);
                    productExpirationQty.setQty(tdeq.getQty());
                    productExpirationQty.setProductExpirationQtyPK(productExpirationQtyPK);
                }
            }

            if (productExpirationQty == null) {
                message.append(product + " - Expiration Date - " + expirationDate + " not found!");
                return false;
            }
        }
        TranHeadExtn tranHead = getTranHeadFromSession(request);
        boolean ret = tranHead.addOrPlusOneTranDetail(product, expirationDate, qty, message, isThisReturnTransaction(request));
        if (!ret) {
            return false;
        }

        addTranHeadBackToSession(request, tranHead);
        addProductBackToRequest(request, product);
        addProductExpirationQtyBackToRequest(request, productExpirationQty);

        if (isThisReturnTransaction(request)) {
            TranHeadExtn returnCart = getReturnCartFromSession(request);
            returnCart.addOrPlusOneTranDetail(product, expirationDate, qty.multiply(new BigDecimal(-1)), message, isThisReturnTransaction(request));
            addReturnCartBackToSession(request, returnCart);
        }
        return true;
    }

    protected void addTranHeadBackToSession(HttpServletRequest request, TranHeadExtn tranHead) {
        addObjectBackToSession(request, tranHead, TranHeadInServlet);
    }

    protected void addReturnCartBackToSession(HttpServletRequest request, TranHeadExtn tranHead) {
        addObjectBackToSession(request, tranHead, ReturnCartInServlet);
    }

    protected void addProductBackToRequest(HttpServletRequest request, Product product) {
        addObjectBackToRequest(request, product, ProductInServlet);
    }

    protected void addProductExpirationQtyBackToRequest(HttpServletRequest request, ProductExpirationQty in) {
        addObjectBackToRequest(request, in, ProductExpirationQty);
    }

    protected void addObjectBackToSession(HttpServletRequest request, Object obj, String name) {
        HttpSession session = request.getSession(true);
        session.setAttribute(name, obj);
    }

    protected void addObjectBackToRequest(HttpServletRequest request, Object obj, String name) {
        request.setAttribute(name, obj);
    }

    protected Object getObjectFromRequest(HttpServletRequest request, String name) {
        return request.getAttribute(name);
    }

    protected void addAppliedToAllPromotionToProduct(Product product) {
        //The first place and maybe the LAST as well to add promotion to product
        List appliedToAllPromotionList = findAppliedToAllPromotions();
        if (appliedToAllPromotionList.size() <= 0 || (product.getPromotion() != null && product.getPromotion().isPromotionValid(product))) {
            return;
        }
        for (int i = 0; i < appliedToAllPromotionList.size(); i++) {
            Promotion pro = (Promotion) appliedToAllPromotionList.get(i);
            if (pro.isPromotionValid(product)) {
                product.setPromotion(pro);
            }
        }
    }

    protected Object getObjectFromSession(HttpServletRequest request, String name) {
        HttpSession session = request.getSession(true);
        Object object = session.getAttribute(name);
        if (name.equals(EmpUser)) {
            if (object == null) {
                DBUtil dBUtil = new DBUtil();
                EmpUsers user = dBUtil.findEmpUserByID(WhenTimeoutHappen);
                addObjectBackToSession(request, user, EmpUser);
                addObjectBackToSession(request, user.getRole(), "empRole");
                addObjectBackToSession(request, user.getUserName(), "empName");
                addObjectBackToSession(request, user.getId(), "empId");
                object = user;
            }
        }
        return object;
    }

    protected Product getProductFromRequest(HttpServletRequest request) {
        return (Product) getObjectFromRequest(request, ProductInServlet);
    //if (product == null) {
    //    product = new Product();
    //}
    //return product;
    }

    protected ProductExpirationQty getProductExpirationQtytFromRequest(HttpServletRequest request) {
        return (ProductExpirationQty) getObjectFromRequest(request, ProductExpirationQty);
    }

    protected TranHeadExtn getReturnCartFromSession(HttpServletRequest request) {
        TranHeadExtn returnCart = getTranHeadFromSessionByName(request, ReturnCartInServlet);
        returnCart.setIsForReturned(new YesNo('Y'));
        return returnCart;
    }

    protected TranHeadExtn getTranHeadFromSession(HttpServletRequest request) {
        return getTranHeadFromSessionByName(request, TranHeadInServlet);
    }

    protected TranHeadExtn getTranHeadFromSessionByName(HttpServletRequest request, String name) {
        TranHeadExtn tranHead = (TranHeadExtn) getObjectFromSession(request, name);

        if (tranHead == null) {
            tranHead = new TranHeadExtn();
            tranHead.setUserId((EmpUsers) getObjectFromSession(request, EmpUser));
        } else {
            if (tranHead.getTranDetailCollection() == null || tranHead.getTranDetailCollection().size() == 0) {
                tranHead.setTranDate(new Date());
            }
        }
        return tranHead;
    }

    protected Promotion getAppliedAllPromotionById(
            String id) {
        List<Promotion> list = findAppliedToAllPromotions();
        if (list != null) {
            for (int i = 0; i <
                    list.size(); i++) {
                Promotion pro = list.get(i);
                if (pro.getPromotionId().equals(new Integer(id))) {
                    return pro;
                }

            }
        }
        return null;
    }

    protected void applyPromotionToNoPromoProduct(HttpServletRequest request, String promoId, boolean isToRemove) {

        //The first place and maybe the LAST as well to add promotion to product
        TranHeadExtn tranHead = getTranHeadFromSession(request);
        tranHead.applyPromotionToNoPromoDetail(getAppliedAllPromotionById(promoId), isToRemove);
        addTranHeadBackToSession(request, tranHead);
    }

    protected boolean isThisReturnTransaction(HttpServletRequest request) {
        String mode = (String) getObjectFromSession(request, Mode);
        if (mode != null && mode.equals(ReturnMode)) {
            return true;
        }
        return false;
    /*String toDo = request.getParameter(ToDo);
    if (toDo != null && (toDo.equals(Return) || toDo.equals(Find))) {
    return true;
    }
    if (getTranHeadFromSession(request) == null) {
    return false;
    }
    return (getTranHeadFromSession(request).getOriginalHeadId() != null);
     * */
    }

    protected void printHead(HttpServletRequest request, PrintWriter out, boolean isFromReturn) {
        String title = "Check Out";
        String h1 = "Check Out Item";
        //String toDo = request.getParameter(ToDo);
        //if (toDo != null && (toDo.equals(Return) || toDo.equals(Find))) {
        if (isFromReturn || isThisReturnTransaction(request)) {
            title = "Return";
            h1 = "Return Item";
        }
        String pending = "";
        int payCount = paymentTranDetailVector.size();
        int invCount = invertoryVector.size();
        int retInvCount = returnInvertoryVector.size();

        if (payCount > 0 || invCount > 0 || retInvCount > 0) {
            pending += " --- ";
            if (payCount > 0) {
                pending += payCount + " Receipt pending.";
            }

            if (invCount > 0) {
                pending += invCount + " Inventory pending.";
            }

            if (retInvCount > 0) {
                pending += retInvCount + " Return Invertory pending.";
            }

        }


        out.println("<html>");

        out.println("<head>");

        out.println("<title>" + title + "</title>");
        out.println(
                "<link rel=\"stylesheet\" href=\"/ca/faces/calendarview.css\">\n    <style>\n      body {\n        font-family: Trebuchet MS;\n      }\n      div.calendar {\n        max-width: 240px;\n        margin-left: auto;\n        margin-right: auto;\n      }\n      div.calendar table {\n        width: 100%;\n      }\n      div.dateField {\n        width: 140px;\n        padding: 6px;\n        -webkit-border-radius: 6px;\n        -moz-border-radius: 6px;\n        color: #555;\n        background-color: white;\n        margin-left: auto;\n        margin-right: auto;\n        text-align: center;\n      }\n      div#popupDateField:hover {\n        background-color: #cde;\n        cursor: pointer;\n      }\n    </style>\n    <script src=\"/ca/faces/prototype.js\"></script>\n    <script src=\"/ca/faces/calendarview.js\"></script>\n    <script>\n      function setupCalendars() {\n        // Embedded Calendar\n        Calendar.setup(\n          {\n            dateField: 'experationDate',\n            parentElement: 'embeddedCalendar'\n          }\n        )\n\n        // Popup Calendar\n        Calendar.setup(\n          {\n            dateField: 'popupDateField',\n            triggerElement: 'popupDateField'\n          }\n        )\n      }\n\n      Event.observe(window, 'load', function() { setupCalendars() })\n    </script>");
        out.println(
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"/ca/faces/jsfcrud.css\" /><script type=\"text/javascript\" src=\"/ca/faces/jsfcrud.js\"></script>" + "<script type=\"text/javascript\" src=\"/ca/faces/check.js\"></script>");
        out.println(
                "</head>");
        //////////////////////
        out.println(
                "<h1>" + h1 + "</h1>");
        out.println(
                "<font color=\"RED\">" + pending + "</font>");
    //////////////////////////////
    }

    protected void printReturnBodyStart(PrintWriter out) {
        //////////////////////////////
        out.println("<body OnLoad=\"document.myForm.tranHeadId.focus();\">");
        //////////////////////
        //Table
        out.println("<table border='0' width='100%'><td>");

        out.println("<form name=\"myForm\" action=\"/ca/CheckOut?toDo=Find\">");
        out.println("<br><h2>Please Scan/Type in the Receip Number</h2>");
        out.println("<input name='tranHeadId'>");
        out.println("<input type='hidden'name='toDo' value='Find'>");
        out.println("<input type='submit' name='toDo' value='Find'");
        out.println("</from>");

    }

    protected void printCheckOutBodyStart(PrintWriter out) {
        //////////////////////////////
        out.println("<body OnLoad=\"document.myForm.isbn.focus();\">");
        //////////////////////
        //Table
        out.println("<table border='0' width='100%'><td>");
    }

    protected void printForm(HttpServletRequest request, PrintWriter out) {
        //out.println("<html>");
        //out.println("<head>");
        //out.println("<title>Check Out</title>");
        //out.println("<link rel=\"stylesheet\" href=\"/ca/faces/calendarview.css\">\n    <style>\n      body {\n        font-family: Trebuchet MS;\n      }\n      div.calendar {\n        max-width: 240px;\n        margin-left: auto;\n        margin-right: auto;\n      }\n      div.calendar table {\n        width: 100%;\n      }\n      div.dateField {\n        width: 140px;\n        padding: 6px;\n        -webkit-border-radius: 6px;\n        -moz-border-radius: 6px;\n        color: #555;\n        background-color: white;\n        margin-left: auto;\n        margin-right: auto;\n        text-align: center;\n      }\n      div#popupDateField:hover {\n        background-color: #cde;\n        cursor: pointer;\n      }\n    </style>\n    <script src=\"/ca/faces/prototype.js\"></script>\n    <script src=\"/ca/faces/calendarview.js\"></script>\n    <script>\n      function setupCalendars() {\n        // Embedded Calendar\n        Calendar.setup(\n          {\n            dateField: 'experationDate',\n            parentElement: 'embeddedCalendar'\n          }\n        )\n\n        // Popup Calendar\n        Calendar.setup(\n          {\n            dateField: 'popupDateField',\n            triggerElement: 'popupDateField'\n          }\n        )\n      }\n\n      Event.observe(window, 'load', function() { setupCalendars() })\n    </script>");
        //out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/ca/faces/jsfcrud.css\" /><script type=\"text/javascript\" src=\"/ca/faces/jsfcrud.js\"></script>");
        //out.println("</head>");
        //out.println("<body OnLoad=\"document.myForm.isbn.focus();\">");
        //////////////////////
        //Table
        //out.println("<table border='0' width='100%'><td>");
        //////////////////////
        //out.println("<h1>Check Out Item</h1>");

        //out.println("<br><h2>Please make sure the Expiration Date is Correct before Scan</h2>");
//Add Form
        out.println("<form  method=\"post\" name=\"myForm\" action=\"/ca/CheckOut\">");
        /*out.println("<table><tr><td><br><br><br><br><br><br>Experation Date ( MM/DD/YYYY ): <input type='text' id='experationDate' name='expirationDate' size='18' value='");
        out.println(expirationDate.trim());

        out.println("'/></td>");
        out.println("<td><div style=\"height: 190px; background-color: #efefef; padding: 10px; -webkit-border-radius: 12px; -moz-border-radius: 12px; margin-right: 10px\">\n\n        <div id=\"embeddedExample\" style=\"\">\n          <div id=\"embeddedCalendar\" style=\"margin-left: auto; margin-right: auto\">\n          </div>\n          <br />\n\n          <br />\n        </div>\n      </div></td></tr></table>");
        //out.println("<div style=\"height: 190px;width: 400px; background-color: #efefef; padding: 10px; -webkit-border-radius: 12px; -moz-border-radius: 12px; margin-right: 10px\">\n\n        <div id=\"embeddedExample\" style=\"\">\n          <div id=\"embeddedCalendar\" style=\"margin-left: auto; margin-right: auto\">\n Experation Date ( MM/DD/YYYY ): <input type='text' id='experationDate' name='experationDate' size='18'/>          </div>\n          <br />\n\n          <br />\n        </div>\n      </div>");
         */
        out.println("<br></br>");
        out.println("Scan / Enter ISBN: <input type='text' name='isbn' size='18'/>");


        if (isThisReturnTransaction(request)) {
            out.println("<input type='hidden' name='" + ToDo + "' value='Add'/>");
            out.println("<input type='hidden' name='" + "qty" + "' value='-1'/>");
            out.println("<input name='" + "Dummy" + "' type='submit' value='" + "Return" + "'/>");
        } else {
            out.println("<input name='" + ToDo + "' type='submit' value='Add'/>");
            out.println("Qty to Add: <input type='text' name='qty' value='1' size='2'/>");
        }
        out.println("</form>");

        if (!isThisReturnTransaction(request)) {
            //for temp item

            out.println("<br></br>");
            out.println("Temp Item:");
            out.println("<form method=\"post\" name=\"addTempForm\" action=\"/ca/CheckOut\">");

            out.println("<table>");

            out.println("<tr>");
            out.println("<td>Item Description:</td><td> <input type='text' name='desc' size='36'/></td>");
            out.println("</tr><tr>");
            out.println("<td>Item Price: $</td><td><input type='text' name='price' size='6'/></td>");
            ///////
            out.println("<td>");
            out.println("<input name='" + ToDo + "' type='submit' value='Add Temp' onclick=\"return checkRequiredField();\"/>");
            out.println("Qty to Add: <input type='text' name='tempQty' value='1' size='2'/>");
            out.println("</td>");
            /////////////
            out.println("</tr><tr>");
            //Payment Form
            List<Tax> list = findAllTax();

            if (list == null) {
                return;
            }
            out.println("<td>Item Tax: </td><td><select id='tax' name='tax' size='1' title='Tax'>");

            for (int i = 0; i < list.size(); i++) {
                int taxToPrint = list.get(i).getRate().multiply(new BigDecimal(100)).intValue();
                out.println("<option value='" + list.get(i).getTaxId() + "'");
                if (taxToPrint == 0) {
                    out.println(" selected='selected'");
                }

                out.println(">" + list.get(i).getName() + " - " + taxToPrint + "%</option>");
            }
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
        }






//Subtotal Form
        out.println("<form method=\"post\" name=\"subtotalForm\" action=\"/ca/CheckOut\">");
        out.println("<input name='" + ToDo + "' type='submit' value='" + Subtotal + "'/>");
        out.println("</form>");
    //out.println("<br><br>");
    ////////////////////////////

    //////////////////////////////
    }

    protected void printBackToCheckOutButton(PrintWriter out) {
        //Payment Form
        out.println("<form method=\"post\" name=\"backToCheckoutForm\" action=\"/ca/CheckOut\">");
        out.println("<input name='" + ToDo + "' type='submit' value='" + BackToCheckout + "'/>");
        out.println("</form>");
    }

    protected void printCancelButton(PrintWriter out) {
        //Payment Form
        out.println("<form method=\"post\" name=\"CancelForm\" action=\"/ca/CheckOut\">");
        out.println("<input name='" + ToDo + "' type='submit' value='" + Cancel + "' onclick='return confirm(\"Are you sure you want to cancel this transaction?\")'/>");
        out.println("</form>");
    }

    protected void printFindOkButton(PrintWriter out) {
        //Payment Form
        out.println("<form method=\"post\" name=\"findOkForm\" action=\"/ca/CheckOut\">");
        out.println("<input name='" + ToDo + "' type='submit' value='" + FindOK + "' />");
        out.println("</form>");
    }

    protected void printDoneButton(PrintWriter out) {
        //Payment Form
        out.println("<form method=\"post\" name=\"doneForm\" action=\"/ca/CheckOut\">");
        out.println("<input name='" + ToDo + "' type='submit' value='" + Done + "'/>");
        out.println("</form>");
    }

    protected void printSaveButton(HttpServletRequest request, PrintWriter out) {
        //Payment Form
        float balance = getTranHeadFromSession(request).getBalance().floatValue();
        out.println("<form method=\"get\" name=\"saveForm\" action=\"/ca/CheckOut\">");
        //out.println("<form name=\"saveForm\" action=\"/ca/CheckOut?" + ToDo + "=" + Done + "\">");
        //out.println("<input name='" + ToDo + "' type='submit' value='" + Save + "'/>");

        out.print("<input ");
        if (balance >= 0.006 || balance <= -0.006) {
            out.print("disabled ");
            out.print("value=\"Balance MUST BE 0\" style=\"height: 100px; width: 240px; color: yellow; background: red; font-size: 150%; font-weight: 900\"");

        } else {
            out.print("value=\"Print Receipt\" style=\"height: 100px; width: 240px; color: #00CC33; background: #33FF66; font-size: 199%; font-weight: 900\"");
        }
        out.println(" type='submit' " + "" + " onclick=\"window.open('/ca/CheckOut?" + ToDo + "=" + Save + "', 'Receipt', 'addressbar=0,location=0,menubar=1,scrollbars=1,copyhistory=yes,left=0,top=0,screenX=0,screenY=0,' + 'height=' + screen.height + ',width=' + screen.width + '' );\"/>");
        //if (balance >= 0.006 || balance <= -0.006) {
        //    out.println("<br><b><font color='red'>** balance MUST BE 0 **</font><b>");
//        }
        out.println("<input type='hidden' name='" + ToDo + "' value='" + Done + "'/>");
        out.println("</form>");
    }

    protected void printIsTrainingModeButton(PrintWriter out, HttpServletRequest request) {
        //Training Form
        String value = SetToTrainingMode;
        TranHeadExtn tranHead = getTranHeadFromSession(request);
        if (tranHead.getIsTrainingMode() != null && tranHead.getIsTrainingMode().getName().equals(new Character('Y'))) {
            value = RemoveTrainingMode;
        }

        out.println("<form method=\"post\" name=\"trainingForm\" action=\"/ca/CheckOut\">");
        out.println("<input name='" + ToDo + "' type='submit' value='" + value + "'/>");
        out.println("</form>");
    }

    protected void printOnePaymentTypeRow(PaymentType p, TranHeadExtn tranHead, PrintWriter out, boolean isChangeDueType) {
        out.println("<tr>");
        out.println("<form method=\"post\" name=\"paymentForm\" action=\"/ca/CheckOut\">");
        out.print("<td>");
        BigDecimal amount = tranHead.getPayAmountByPayType(p.getId());
        if (isChangeDueType) {
            out.print("<b>");
            amount = amount.multiply(new BigDecimal(-1));
        }
        out.print(p.getName() + " ( $" + amount + " )");
        if (isChangeDueType) {
            out.print("</b>");
        }
        out.print("</td><td>" + " $ <input size='7' name='amount' value='" + tranHead.getBalance() + "'/>" + "</td>");
        out.println("<td>" + "<input name='" + ToDo + "' type='submit' value='Pay'/>" + "</td>");
        out.println("<input type='hidden' name='id' value='" + p.getId() + "'/>");
        out.println("</form>");
        out.println("</tr>");
    }

    protected void printChangeDueRow(PaymentType p, TranHeadExtn tranHead, PrintWriter out) {
        if (p != null) {
            printOnePaymentTypeRow(p, tranHead, out, true);
        }
    }

    protected void printPaymentButton(HttpServletRequest request, PrintWriter out) {
        //Payment Form
        List<PaymentType> list = findAllPaymentType();

        if (list == null) {
            return;
        }

        TranHeadExtn tranHead = getTranHeadFromSession(request);
        PaymentType changeDue = null;
        out.println("<h4>Payment ( $" + tranHead.getPaymentTotal() + " ):</h4>");
        out.println("<table border='0'>");

        for (int i = 0; i <
                list.size(); i++) {
            PaymentType p = list.get(i);
            if (p.getName().equals("Change Due")) {
                changeDue = p;
                continue;
            }
            printOnePaymentTypeRow(p, tranHead, out, false);
        }
        //now print the Change Due Row
        printChangeDueRow(changeDue, tranHead, out);
        out.println("</table>");
    }

    protected void printConfirmExpirationDates(HttpServletRequest request, PrintWriter out, ArrayList<String> dates) {
        Product product = getProductFromRequest(request);
        out.println("<h4>Please select the Expiration Date for the Item -<br>" + product + "</h4><br><br>");

        out.println("<form method=\"post\" name=\"dateForm\" action=\"/ca/CheckOut\">");
        for (int i = 0; i <
                dates.size(); i++) {
            String date = dates.get(i);
            out.println("<input name='" + ExpirationDate + "' type='submit' value='" + date + "'/><br><br>");
        }
        out.println("<input name='" + ToDo + "' type='hidden' value='" + AddExpirationDate + "'/>");
        out.println("<input name='" + Isbn + "' type='hidden' value='" + product.getIsbn() + "'/>");
        String qty = null;

        out.println("<input name='" + Qty + "' type='hidden' value='" + getAddTempOrNotTemp(request, Qty) + "'/>");
        out.println("</form>");
    }

    protected void printBottleReturn(HttpServletRequest request, PrintWriter out) {
        //Bottle Form
        if (isThisReturnTransaction(request)) {
            return;
        }
        TranHeadExtn tranHead = getTranHeadFromSession(request);
        out.println("<h4>Bottle Return ( $" + tranHead.getBottleRefund() + " ):</h4>");
        out.println("<form method=\"post\" name=\"bottleReturnForm\" action=\"/ca/CheckOut\">");
        out.println("$ <input name='bottleReturn'/>");
        out.println("<input name='" + ToDo + "' type='submit' value='" + SetBottleReturn + "'/>");
        out.println("</form>");
    }

    protected void printAppliedToAllPromotions(HttpServletRequest request, PrintWriter out) {
        if (isThisReturnTransaction(request)) {
            return;
        }
        List<Promotion> list = findAppliedToAllPromotions();
        if (list != null) {
            out.println("<h4>Promotions:</h4>");
            for (int i = 0; i <
                    list.size(); i++) {
                Promotion pro = list.get(i);
                out.println("<form method=\"post\" name=\"promo" + pro.getPromotionId() + "Form\" action=\"/ca/CheckOut" + "\">");
                out.println("<input type='submit' value='" + pro.getName() + "'/>");
                out.println("<input type='submit' name='" + ToDo + "' value='REMOVE" + "'/>");
                out.println("<input type='hidden' name='" + ToDo + "' value=\"Promotion\"/>");
                out.println("<input type='hidden' name='Id' value=\"" + pro.getPromotionId() + "\"/>");

                out.println("</form>");
            }

        }
    }

    protected void printColumnTd(PrintWriter out) {
        out.println("</td>");
        out.println("<td width='40%'>");
    }

    protected void redirectBackToMain(PrintWriter out) {
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Redirecting...</title>");
        out.println("<meta http-equiv=\"REFRESH\" content=\"0;url=/ca/\"></HEAD>");
        out.println("<BODY>");
        out.println("Redirecting...");
        out.println("</BODY>");
        out.println("</HTML>");
    }

    protected String getAddTempOrNotTemp(HttpServletRequest request, String name) {
        String value = null;
        if (isAddTempRequest(request)) {
            value = (String) getObjectFromRequest(request, name);
        } else {
            value = request.getParameter(name);
        }

        return value;
    }

    protected void printResult(HttpServletRequest request, PrintWriter out, boolean isResultGood, StringBuffer message) {

        if (!isResultGood) {
            out.println("<h3><font color=\"RED\">" + message.toString() + "</font></h3>" + "<br>");
        } else {
            //out.println("Product - ");
            Product product = getProductFromRequest(request);
            if (product != null) {
                if (product.getName() != null && product.getName().length() > 0) {
                    out.println(product.getName() + " - ");
                }

                out.println(product.getIsbn());
                ProductExpirationQty productExpirationQty = getProductExpirationQtytFromRequest(request);
                if (productExpirationQty != null) {
                    out.println(" expires on " + new SimpleDateFormat("MM/dd/yyyy").format(productExpirationQty.getProductExpirationQtyPK().getExpirationDate()));
                }

                out.println(" Qty " + getAddTempOrNotTemp(request, Qty) + " is added");


            }

        }
    }

    protected void printMainMenu(PrintWriter out) {
        out.println("<br><br><br><a href=\"/ca/faces/index.jsp\">Main Menu</a>");
    }

    protected void printEnd(PrintWriter out) {
        out.println("</td></table>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void printHistory(PrintWriter out, HttpServletRequest request) {
        TranHeadExtn latestTranHead = getTranHeadFromSession(request);
        TranHeadExtn tranHeadExtn = new TranHeadExtn();
        TranHead tranHead = findNoExtnTranHeadByID(latestTranHead.getTranHeadId().toString());
        tranHeadExtn.copyToSuper(tranHead, true, false);
        printFindOkButton(out);
        do {
            //TranHeadExtn tranHeadExtnTemp = tranHeadExtn;
            //tranHeadExtn.subtotal();
            printTranHeadReciept(tranHeadExtn, request, out, false, false);
            List<TranHead> list = (List<TranHead>) tranHeadExtn.getTranHeadCollection();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    TranHead th = list.get(i);
                    if (th.getIsForReturned() != null && th.getIsForReturned().getName().equals(new Character('Y'))) {
                        TranHeadExtn temp = new TranHeadExtn();
                        temp.copyToSuper(th, true, false);
                        printReturnCartTranHead(temp, out);
                    }
                }
            }
            tranHead = tranHeadExtn.getOriginalHeadId();
            if (tranHead != null) {
                //tranHeadExtn = new TranHeadExtn();
                tranHeadExtn.copyToSuper(tranHead, true, false);
            }
            out.println("<br>++++++++++++++++++++++++++++++++++++++++++++<br>");
        } while (tranHead != null);
    }

    protected void printTranHeadReciept(TranHeadExtn tranHead, HttpServletRequest request, PrintWriter out, boolean isForReceipt, boolean isNeedHighLightItem) {
        if (isForReceipt) {
            out.println("<h3>");
        }
        if (tranHead.getTranHeadId() != null) {
            out.println("<form>");

            if ( isForReceipt )
            {
            out.println("<input style=\"display: none; height: 120px; width: 480px; color: yellow; background: red; font-size: 200%; font-weight: 900\" id='closeButton' type='button' value='Close & Back' onclick=\"window.close();\">");
            out.println("<input style=\"height: 120px; width: 480px; color: #00CC33; background: #33FF66; font-size: 200%; font-weight: 900\" id='printButton' type='button' value='Print' onclick=\"document.getElementById('printButton').style.display = 'none';document.getElementById('closeButton').style.display = 'none';window.print();document.getElementById('closeButton').style.display = 'block';document.getElementById('printButton').style.display = 'block';\">");
            }
            out.println("</form>");
            out.println("<i>Enchie Organic & Japanese Food Market</i>");
            out.println("<br>Mon-Thu: 10 - 7  &nbsp &nbsp &nbsp &nbsp Fri: 10 - 8");
            out.println("<br>Sat: 9 - 5 &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp Sun: closed");
            out.println("<br>Tel/Fax: (519) 250-3566");
            out.println("<br>2001 Provincial Rd. , Windsor, ON N8W 5V7");
            out.println("<br>=========================================");
            out.println("<br><h3>ITEMS SOLD: " + tranHead.getNumberOfItems() + "</h3>");
            out.println("Cashier: " + tranHead.getUserId().getUserName() + " , ");
            out.println("Reciept ID: " + tranHead.getTranHeadId() + "<br>");
        } else {
            out.println("<br><h3>ITEMS SOLD: " + tranHead.getNumberOfItems() + "</h3>");
            out.println("Cashier: " + getObjectFromSession(request, "empName") + "<br>");
        }

        out.println(new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(tranHead.getTranDate()) + "<br>");

        printTranHeadDetailLine(tranHead, out, isForReceipt, isNeedHighLightItem);

        //out.println("<br>");
        out.println("Subtotal: $" + tranHead.getLineTotalAmt() + "<br>");
        //tranHead.subtotal();
        out.println("HST Tax: $" + tranHead.getHstTax() + "<br>");
        out.println("GST Tax: $" + tranHead.getGstTax() + "<br>");
        //out.println("PST Tax: $" + tranHead.getPstTax() + "<br>");
        out.println("Total Tax: $" + tranHead.getLineTaxAmt() + "<br>");
        out.println("Total: $" + tranHead.getTotal() + "<br>");
        if (tranHead.getLineDiscountAmt().compareTo(new BigDecimal("0.01")) >= 0) {
            out.println("Total Saving: $" + tranHead.getLineDiscountAmt() + "<br>");
        }

        if (tranHead.getBottleRefund() != null && tranHead.getBottleRefund().compareTo(new BigDecimal("0.01")) >= 0) {
            out.println("<br>Bottle Refund: $" + tranHead.getBottleRefund() + "<br>");
        }



        printTranPaymentLine(tranHead, out, "Payment:");


        BigDecimal balance = tranHead.getLineTotalAmt().add(tranHead.getLineTaxAmt()).subtract(tranHead.getBottleRefund()).subtract(tranHead.getPaymentTotal());

        out.println("<font color='green'>");
        out.println("Balance: $" + tranHead.getBalance());
        out.println("</font>");



        if (isForReceipt) {

            out.println("<br><br>All perishable, refrigerated and frozen products");
            out.println("<br>are non-refundable. Groceries must be returned");
            out.println("<br>within 10 days in good condition with receipt.");
            out.println("</h3>");
        }

    }

    protected void printReturnCartTranHead(TranHeadExtn returnCart, PrintWriter out) {
        boolean isItemPrinted = false;
        if (returnCart != null && returnCart.getTranDetailCollection() != null && returnCart.getTranDetailCollection().size() > 0) {
            isItemPrinted = true;
            out.println("<br>--------------------<br>Item Returned:<br>");
            printTranHeadDetailLine(returnCart, out, false, true);
        }
        if (returnCart != null && returnCart.getTranPaymentCollection() != null && returnCart.getTranPaymentCollection().size() > 0) {
            if (!isItemPrinted) {
                out.println("<br>--------------------");
            }
            printTranPaymentLine(returnCart, out, "Payment Refunded:");
        }
        out.flush();
    }

    protected void printCart(TranHeadExtn tranHead, TranHeadExtn returnCart, PrintWriter out, HttpServletRequest request, boolean isForReceipt, boolean isNeedHighLightItem) {
        //TranHeadExtn tranHead = getTranHeadFromSession(request);
        printTranHeadReciept(tranHead, request, out, isForReceipt, isNeedHighLightItem);
        if (isThisReturnTransaction(request)) {
            //TranHeadExtn returnCart = getReturnCartFromSession(request);
            printReturnCartTranHead(returnCart, out);
        }
        out.flush();
    }

    protected String decimalCheck(BigDecimal in) {
        double remain = in.subtract(new BigDecimal(in.intValue())).doubleValue();

        if (remain >= 0.01 || remain <= -0.01) {
            return in.toString();
        }
        return in.toBigInteger().toString();
    }

    protected void printTranHeadDetailLine(TranHeadExtn tranHead, PrintWriter out, boolean isForReceipt, boolean isNeedHighLightItem) {
        List<TranDetail> list = (List<TranDetail>) tranHead.getTranDetailCollection();
        //Object list = tranHead.getTranDetailCollection();
        //System.out.println(list.getClass());
        if (list == null) {
            return;
        }
        String justAddedIsbn = tranHead.getJustAddedIsbn();

        for (int i = 0; i <
                list.size(); i++) {
            TranDetail tranDetail = list.get(i);
            if (justAddedIsbn.equals(tranDetail.getIsbn().getIsbn()) && !isForReceipt && isNeedHighLightItem) {
                out.println("<font color='red'>");
            }
            //out.println("Qty " + tranDetail.getQty() + " x " + tranDetail.getIsbn() + " $" + tranDetail.getSubTotal() + " " + tranDetail.getTax() + " after tax - $" + tranDetail.getLineNetTotal() + "<br>");
            //out.println("Qty " + tranDetail.getQty() + " " + tranDetail.getIsbn().getName() + "(" + tranDetail.getIsbn().getIsbn() + ") @ $" + tranDetail.getIsbn().getPrice() + " total - $" + tranDetail.getSubTotal() + "<br>");
            out.println(decimalCheck(tranDetail.getQty()) + "@$" + tranDetail.getPrice() + " " + tranDetail.getIsbn().getName());
            if (!isForReceipt) {
                out.println(" ( " + tranDetail.getIsbn().getIsbn() + " )");
            }
            out.println(" - $" + tranDetail.getSubTotal() + "<br>");
            if (tranDetail.getPromotion() != null && Math.abs(tranDetail.getSubTotal().subtract(tranDetail.getTotal()).doubleValue()) >= 0.01) {
                out.println("on sale - " + tranDetail.getPromotion() + " Reg 1/$" + tranDetail.getPrice() + " You save --- $" + tranDetail.getDiscountAmt() + " !!<br>");
            }

            if (justAddedIsbn.equals(tranDetail.getIsbn().getIsbn()) && !isForReceipt) {
                out.println("</font>");
            }

        //out.println("<br>");
        }
    }

    protected void printTranPaymentLine(TranHeadExtn tranHead, PrintWriter out, String title) {
        List<TranPayment> pList = (List<TranPayment>) tranHead.getTranPaymentCollection();
        if (pList != null && pList.size() > 0) {
            TranPayment changeDue = null;
            out.println("" + title + "<br>");

            for (int i = 0; i <
                    pList.size(); i++) {
                TranPayment tp = pList.get(i);
                if (tp.getPaymentType().getName().equals("Change Due")) {
                    changeDue = tp;
                    continue;
                }
                out.println(tp.getPaymentType().getName() + ": $" + tp.getAmount() + "<br>");
            }
            if (changeDue != null) {
                out.println("<br><font color='red'><b>" + changeDue.getPaymentType().getName() + ": $" + changeDue.getAmount().multiply(new BigDecimal(-1)) + "</b></font><br>");
            }
            out.println("<font color='orange'>Total " + title + " $" + tranHead.getPaymentTotal() + "</font><br>");
        }
    }

    protected boolean isExpirationDateEntered(HttpServletRequest request) {
        String expDate = nullStringToBlankAndTrim(request.getParameter(ExpirationDate));
        if (expDate != null && expDate.trim().length() > 0) {
            return true;
        }
        return false;
    }

    protected String nullStringToBlankAndTrim(String in) {
        if (in == null) {
            return "";
        }
        return in.toLowerCase();
    }

    protected boolean createTempProduct(HttpServletRequest request, StringBuffer message) {

        String desc = nullStringToBlankAndTrim(request.getParameter("desc"));
        String price = nullStringToBlankAndTrim(request.getParameter("price"));
        String taxId = nullStringToBlankAndTrim(request.getParameter("tax"));
        String qty = nullStringToBlankAndTrim(request.getParameter("tempQty"));
        try {
            new Float(price);
            if (desc.trim().length() == 0 || price.trim().length() == 0 || qty.trim().length() == 0) {
                message.append("Invalid Temp Product Description / Price / Qty!");
                return false;
            }
        } catch (Exception e) {
            message.append("Invalid Temp Product Description / Price / Qty!");
            return false;
        }

        Product product = new Product();
        //YesNo no = new YesNo();
        //no.setName('N');
        //product.setHasExpirationDate(no);
        String isbn = "TEMP-" + String.valueOf(new Date().getTime());
        product.setIsbn(isbn);
        product.setLastUpdateDate(new Date());
        product.setDescription(desc);
        product.setName(desc);
        product.setPrice(new BigDecimal(price));
        product.setQty(new BigDecimal(qty));
        product.setTax(findTaxeByID(taxId));
        try {
            productJpaController.create(product);
        } catch (Exception e) {
            e.printStackTrace();
            message.append("Temp Product " + isbn + " created failed!");
            return false;
        }
        addObjectBackToRequest(request, isbn, Isbn);
        addObjectBackToRequest(request, qty, Qty);
        return true;
    }

    protected void waitForDBTransaction(HttpServletRequest request) {
        try {
            while (true) {
                if (getTranHeadFromSession(request).getTranHeadId() != null) {
                    break;
                }
                Thread.sleep(10l);
                System.out.println("wait...");
            }
            if (isThisReturnTransaction(request)) {
                while (true) {
                    if (getReturnCartFromSession(request).getTranHeadId() != null) {
                        break;
                    }
                    Thread.sleep(10l);
                    System.out.println("wait...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean isJustSaved(HttpServletRequest request) {
        if (getObjectFromSession(request, IsJustSaved) == null) {
            return false;
        }
        if (((Boolean) getObjectFromSession(request, IsJustSaved)).booleanValue()) {
            return true;
        }
        return false;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //setMode(request);
        boolean isFromReturn = false;
        //HttpSession session = request.getSession(true);


        try {
            //reset();
            setupController();

            String toDo = request.getParameter(ToDo);
            if (toDo == null || toDo.trim().equals("")) {
                //the begining of Check Out
                toDo = "";
                doCancel(request);
                addObjectBackToSession(request, CheckOutMode, Mode);
            //addTranHeadBackToSession(request, null);
            } else if (toDo.equals(Return)) {
                doCancel(request);
                addObjectBackToSession(request, ReturnMode, Mode);
            }

            StringBuffer message = new StringBuffer();
            boolean isResultGood = false;
            ArrayList<String> expirationDates = null;
            //action could be "add" "subtotal" "senior discount" "manager discount" "bottle return"
            if (toDo.equals(AddTemp) || toDo.equals(AddExpirationDate) || toDo.equals(Add)) {
                if (isJustSaved(request)) {
                    if (!isThisReturnTransaction(request)) {
                        doCancel(request);
                    }
                    addObjectBackToSession(request, new Boolean(false), IsJustSaved);
                }
            }
            if (toDo.equals(AddTemp)) {
                toDo = Add;
                createTempProduct(request, message);
            }

            if (toDo.equals(AddExpirationDate)) {
                Product product = checkScanedProduct(request, out, message);
                if (product != null) {
                    isResultGood = doAdd(product, request, message, nullStringToBlankAndTrim(request.getParameter(ExpirationDate)));
                    toDo =
                            Add;
                }

            } else if (toDo.equals(Add)) {

                Product product = checkScanedProduct(request, out, message);
                if (product != null) {
                    expirationDates = new ArrayList<String>();
                    if (!isNeedAskForExpirationDate(product, request, expirationDates)) {
                        if (expirationDates.size() > 1) {
                        } else {
                            String expirationDate = "";
                            if (expirationDates.size() == 1) {
                                expirationDate = expirationDates.get(0);
                            }

                            isResultGood = doAdd(product, request, message, expirationDate);
                        }

                    } else {
                        isResultGood = true;
                        toDo =
                                ConfirmExpirationDate;
                    }

                } else {
                    isResultGood = false;
                }
            /*if (isResultGood && (!isYes(product.getHasExpirationDate()) || (isYes(product.getHasExpirationDate()) && isExpirationDateEntered(request)))) {
            isResultGood = false;
            isResultGood = doAdd(product, request, message);
            } else if (isResultGood) {
            toDo = "ConfirmExpirationDate";
            }*/

            } else if (toDo.equals(BackToCheckout)) {
                isResultGood = true;
            } else if (toDo.equals(Promotion) || toDo.equals(Remove)) {
                doPromotion(request, toDo.equals(Remove));
            } else if (toDo.equals(SetBottleReturn)) {
                doBottleReturn(request);
            } else if (toDo.equals(SetToTrainingMode)) {
                doSetTrainingMode(request, true);
            } else if (toDo.equals(RemoveTrainingMode)) {
                doSetTrainingMode(request, false);
            } else if (toDo.equals(Pay)) {
                doPay(request);
            } else if (toDo.equals(Save)) {
                doSave(request, out, true);
            } else if (toDo.equals(Cancel)) {
                doCancel(request);
                isResultGood = true;
                if (isThisReturnTransaction(request)) {
                    isFromReturn = true;
                    toDo = Return;
                }
            } else if (toDo.equals(Done)) {
                Boolean isJustSaved = (Boolean) getObjectFromSession(request, IsJustSaved);
                while (isJustSaved == null || isJustSaved.booleanValue() == false) {
                    //Wait until the DB part is done
                    Thread.sleep(1l * 1000l);
                    isJustSaved = (Boolean) getObjectFromSession(request, IsJustSaved);
                }
                if (isThisReturnTransaction(request)) {
                    isFromReturn = true;
                    toDo = Return;
                }
                isResultGood = true;
            }


            if (toDo.equals(Find)) {
                isResultGood = doFind(request, message);
            //doCancel(request);
            //isResultGood = true;
            } else if (toDo.equals(FindOK)) {
                isResultGood = true;
            }

            /* String experationDate = request.getParameter(ExpirationDate);
            if (experationDate == null) {
            experationDate = "";
            }*/

// experationDate = experationDate.trim();
// String isbn = request.getParameter("isbn");
// String qty = request.getParameter("qty");
//Product product = null;
//ProductExpirationQty productExpirationQty = null;
//Boolean isNewProduct = new Boolean(false);
//Boolean isNewProductExperationQty = new Boolean(false);

//if (isbn != null && qty != null && isbn.length() > 0 && qty.length() > 0) {
//isResultGood = doDatabase(isbn, qty, experationDate, message);
//}
            if (!toDo.equals(Save)) {
                printHead(request, out, isFromReturn);
            }







            if (toDo.equals(Return) || (toDo.equals(Find) && !isResultGood)) {
                if (!isResultGood) {
                    printResult(request, out, isResultGood, message);
                }

                printReturnBodyStart(out);

                printMainMenu(out);
            } else {
                printCheckOutBodyStart(out);
                if (toDo.equals(ConfirmExpirationDate)) {
                    printConfirmExpirationDates(request, out, expirationDates);
                } else if (toDo.equals(Find)) {
                    printHistory(out, request);
                } else if (toDo.equals("") || toDo.equals(Add) || toDo.equals(BackToCheckout) || toDo.equals(Cancel) || toDo.equals(FindOK) || toDo.equals(Done)) {
                    printForm(request, out);
                    printCancelButton(out);
                    printMainMenu(out);
                    printColumnTd(out);
                    printResult(request, out, isResultGood, message);

                    if (isResultGood && !toDo.equals(Done)) {
                        printCart(getTranHeadFromSession(request), getReturnCartFromSession(request),
                                out,
                                request,
                                false, true);
                    }

                } else if (toDo.equals(Subtotal) || toDo.equals(Promotion) || toDo.equals(Remove) || toDo.equals(SetBottleReturn) || toDo.equals(SetToTrainingMode) || toDo.equals(RemoveTrainingMode) || toDo.equals(Pay)) {
                    printAppliedToAllPromotions(request, out);
                    printBottleReturn(request, out);
                    printPaymentButton(request, out);
                   // out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                   out.println("<td>");
                   out.println("<br>");

                    printBackToCheckOutButton(out);
                  // out.println("</td>");
                  //  out.println("<td rowspan = \"3\">");

                   
                 //   out.println("</td>");
                 //   out.println("</tr>");
                 //   out.println("<tr>");
                 //   out.println("<td>");
                    printIsTrainingModeButton(out, request);
                //    out.println("</td>");
                //    out.println("</tr>");
                //    out.println("<tr>");
                //    out.println("<td>");
                    printCancelButton(out);
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<br>");
                     printSaveButton(request, out);

                    out.println("</td>");
                    out.println("</tr>");
                    out.println("</table>");
                    ////////////////////////////
                    printColumnTd(out);
                    printCart(getTranHeadFromSession(request), getReturnCartFromSession(request), out, request, false, false);
                } else if (toDo.equals(Save)) {
                    //addObjectBackToSession(request, new Boolean(true), IsJustSaved);
                    //printCart(out, request, true);

                    //printDoneButton(out);
                }

            }

            printEnd(out);

            out.flush();
            if (toDo.equals(Save)) {
                //try {
                //Thread.sleep(3L * 1000L);
                //} catch (Exception e) {
                // e.printStackTrace();
                // }
                //doCancel(request);
            }
        } catch (Exception e) {
            e.printStackTrace();

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

    protected List findAppliedToAllPromotions() {
        List<Promotion> result = null;
        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("persistence/LogicalName");
            Query q = em.createNamedQuery("Promotion.findAll");
            result =
                    q.getResultList();
            if (result != null) {
                for (int i = 0; i <
                        result.size(); i++) {
                    Promotion pro = result.get(i);
                    if (pro.getIsAppliedToAll() == null || !pro.getIsAppliedToAll().getName().equals(new Character('Y')) || !pro.isPromotionValid(null)) {
                        result.remove(i);
                        i--;

                    }








                }
            }
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    protected List<Tax> findAllTax() {
        List<Tax> result = null;
        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("persistence/LogicalName");
            Query q = em.createNamedQuery("Tax.findAll");
            result =
                    q.getResultList();
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    protected List<PaymentType> findAllPaymentType() {
        List<PaymentType> result = null;
        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("persistence/LogicalName");
            Query q = em.createNamedQuery("PaymentType.findAll");
            result =
                    q.getResultList();
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    protected Tax findTaxeByID(String id) {
        List<Tax> list = findAllTax();
        if (list == null) {
            return null;
        }

        for (int i = 0; i <
                list.size(); i++) {
            if (list.get(i).getTaxId().equals(new Integer(id))) {
                return list.get(i);
            }

        }
        return null;
    }

    protected PaymentType findPaymentTypeByID(String id) {
        List<PaymentType> list = findAllPaymentType();
        if (list == null) {
            return null;
        }

        for (int i = 0; i <
                list.size(); i++) {
            if (list.get(i).getId().equals(new Integer(id))) {
                return list.get(i);
            }

        }
        return null;
    }

    protected TranHeadExtn findTranHeadByID(String id) {
        setupController();
        TranHeadExtn tranHead = null;
        TranHead dbTranHead = findNoExtnTranHeadByID(id);
        if (dbTranHead == null) {
            return null;
        }

        tranHead =
                new TranHeadExtn();
        tranHead.copyToSuper(dbTranHead, false, true);
        //tranHead.subtotal();
        return tranHead;
    }

    protected TranHead findNoExtnTranHeadByID(String id) {
        setupController();
        //TranHeadExtn tranHead = null;
        TranHead dbTranHead = null;
        try {
            dbTranHead = tranHeadJpaController.findTranHead(new Integer(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dbTranHead == null || (dbTranHead.getIsForReturned() != null && dbTranHead.getIsForReturned().getName().equals(new Character('Y')))) {
            return null;
        }

        return dbTranHead;
    }

    protected Product getProductFromTranHeadFromSessionById(HttpServletRequest request, String id) {
        if (getTranDetailFromTranHeadFromSessionByID(request, id) != null) {
            return getTranDetailFromTranHeadFromSessionByID(request, id).getIsbn();
        }

        return null;
    }

    protected List<TranDetailLineExpirationQty> getTranDetailLineExpirationQtyListFromTranDetailTranHeadFromSessionByID(HttpServletRequest request, String isbn) {
        TranDetail td = getTranDetailFromTranHeadFromSessionByID(request, isbn);
        if (td == null) {
            return null;
        }

        return (List<TranDetailLineExpirationQty>) td.getTranDetailLineExpirationQtyCollection();
    }

    protected TranDetailLineExpirationQty getTranDetailLineExpirationQtyFromTranHeadFromSessionByPk(HttpServletRequest request, ProductExpirationQtyPK pk) {

        List<TranDetailLineExpirationQty> list = getTranDetailLineExpirationQtyListFromTranDetailTranHeadFromSessionByID(request, pk.getIsbn());
        if (list == null || list.size() == 0) {
            return null;
        }

        for (int i = 0; i <
                list.size(); i++) {
            TranDetailLineExpirationQty teq = list.get(i);
            if (teq.getTranDetailLineExpirationQtyPK().getExpirationDate().equals(pk.getExpirationDate())) {
                return teq;
            }

        }
        return null;
    }

    protected TranDetail getTranDetailFromTranHeadFromSessionByID(HttpServletRequest request, String id) {
        TranHeadExtn tranHead = getTranHeadFromSession(request);
        if (tranHead == null) {
            return null;
        }

        List<TranDetail> list = (List<TranDetail>) tranHead.getTranDetailCollection();
        if (list == null || list.size() == 0) {
            return null;
        }

        for (int i = 0; i <
                list.size(); i++) {
            TranDetail td = list.get(i);
            if (td.getIsbn().getIsbn().equals(id)) {
                if ( isThisReturnTransaction(request) )
                {
                    td.getIsbn().setCost(td.getCost());
                    td.getIsbn().setPrice(td.getPrice());
                    td.getIsbn().setTax(td.getTax());
                    td.getIsbn().setPromotion(td.getPromotion());
                }

                return td;
            }

        }
        return null;
    }
}
