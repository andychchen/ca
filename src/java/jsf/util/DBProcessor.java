/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import jpa.controllers.ProductExpirationQtyJpaController;
import jpa.controllers.ProductJpaController;
import jpa.entities.Product;
import jpa.entities.ProductExpirationQty;
import jpa.entities.ProductExpirationQtyPK;
import jpa.controllers.TranDetailJpaController;
import jpa.controllers.TranDetailLineExpirationQtyJpaController;
import jpa.controllers.TranPaymentJpaController;
import jpa.entities.TranDetail;
import jpa.entities.TranDetailLineExpirationQty;
import jpa.entities.extn.TranHeadExtn;

/**
 *
 * @author achen
 */
public class DBProcessor extends Thread {

    public static AtomicBoolean isRunning = new AtomicBoolean(false);
    Vector<TranHeadExtn> payVector;
    Vector<TranHeadExtn> invVector;
    Vector<TranHeadExtn> retInvVector;
    TranDetailJpaController tranDetailJpaController;
    TranPaymentJpaController tranPaymentJpaController;
    TranDetailLineExpirationQtyJpaController tranDetailLineExpirationQtyJpaController;
    ProductJpaController productJpaController;
    ProductExpirationQtyJpaController productExpirationQtyJpaController;

    public DBProcessor(Vector<TranHeadExtn> pV, Vector<TranHeadExtn> iV, Vector<TranHeadExtn> ri, TranDetailJpaController t,
            TranPaymentJpaController tp,
            TranDetailLineExpirationQtyJpaController te, ProductJpaController p,
            ProductExpirationQtyJpaController pe) {
        payVector = pV;
        invVector = iV;
        retInvVector = ri;
        tranPaymentJpaController = tp;
        tranDetailLineExpirationQtyJpaController = te;
        productJpaController = p;
        productExpirationQtyJpaController = pe;
        tranDetailJpaController = t;

    }

    protected void savePaymentAndTranDetailToDB(TranHeadExtn tranHead) {
        try {
            long start = (new Date()).getTime();
            tranHead.saveTranPayment(tranPaymentJpaController);
            tranHead.saveTranDetails(tranDetailJpaController, tranDetailLineExpirationQtyJpaController);
            System.out.println("[" + new Date() + "] Receipt ID: " + tranHead.getTranHeadId() + " saved");

            long end = (new Date()).getTime();
            System.out.println("Spend " + new Double((end - start) / 1000d) +
                    " seconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doInventory(
            TranHeadExtn tranHead, boolean isReturnTran) {
        //TranHeadExtn tranHead = null;
        try {
            long start = (new Date()).getTime();

            //if (isThisReturnTransaction(request)) {
            // tranHead = getReturnCartFromSession(request);
            //} else {
            //tranHead = getTranHeadFromSession(request);
            //}
            //setupController();
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
                if (isReturnTran) {
                    //qtyToAdd = qtyToAdd.multiply(new BigDecimal(-1));
                    qtyToAdd = BigDecimal.ZERO;
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
                        if (isReturnTran) {
                            qtyToAdd = qtyToAdd.multiply(new BigDecimal(-1));
                        }
                        productExpirationQty.setQty(oldQty.subtract(qtyToAdd));
                        productExpirationQtyJpaController.edit(productExpirationQty);
                    }
                }
            }
            System.out.println("[" + new Date() + "] Receipt ID: " + tranHead.getTranHeadId() + " invertory saved");
            long end = (new Date()).getTime();
            System.out.println("Spend " + new Double((end - start) / 1000d) +
                    " seconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        if (isRunning.get()) {
            return;
        } else {
            isRunning.set(true);
        }
        boolean isAdded = true;
        while (isAdded) {
            isAdded = false;
            while (payVector.size() > 0) {
                TranHeadExtn th = payVector.get(0);
                System.out.print("payVector processing...");
                savePaymentAndTranDetailToDB(th);
                payVector.remove(0);
                isAdded = true;
            }
            while (invVector.size() > 0) {
                TranHeadExtn th = invVector.get(0);
                System.out.print("invVector processing...");
                doInventory(th, false);
                invVector.remove(0);
                isAdded = true;
            }
            while (retInvVector.size() > 0) {
                TranHeadExtn th = retInvVector.get(0);
                System.out.print("retInvVector processing...");
                doInventory(th, true);
                retInvVector.remove(0);
                isAdded = true;
            }
        }
        isRunning.set(false);
    }
}
