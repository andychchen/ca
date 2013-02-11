/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities.extn;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jpa.controllers.TranDetailJpaController;
import jpa.controllers.TranDetailLineExpirationQtyJpaController;
import jpa.controllers.TranPaymentJpaController;
import jpa.entities.PaymentType;
import jpa.entities.Product;
import jpa.entities.Promotion;
import jpa.entities.Tax;
import jpa.entities.TranDetail;
import jpa.entities.TranDetailLineExpirationQty;
import jpa.entities.TranHead;
import jpa.entities.TranPayment;

/**
 *
 * @author achen
 */
public class TranHeadExtn extends TranHead {

    BigDecimal gstTax;
    BigDecimal pstTax;
    BigDecimal hstTax;
    int numberOfItems = 0;

    String justAddedIsbn = "";

    //TranHead tranHead;
    public String getJustAddedIsbn()
    {
        return justAddedIsbn;
    }
    public TranHeadExtn() {
        super();
        setBottleRefund(BigDecimal.ZERO);
        setHeadNetTotal(BigDecimal.ZERO);
        setManagerDiscountAmt(BigDecimal.ZERO);
        setLineDiscountAmt(BigDecimal.ZERO);
        setLineTaxAmt(BigDecimal.ZERO);
        setLineTotalAmt(BigDecimal.ZERO);
        setTaxAfterHeadDiscount(BigDecimal.ZERO);
        setTotalAfterHeadDiscount(BigDecimal.ZERO);
        setTranDate(new Date());
    }

    /*public TranHead getTranHead() {
    try {
    return (TranHead) super.clone();
    } catch (Exception e) {
    e.printStackTrace();
    }
    return null;
    }*/
    public boolean isIsbnExistAndAdd(Product product, String expirationDate, BigDecimal qty, StringBuffer message, boolean isForReturn) {
        if (getTranDetailCollection() == null || getTranDetailCollection().size() == 0) {
            return false;
        }
        List<TranDetail> tranDetailList = (List<TranDetail>) getTranDetailCollection();
        for (int i = 0; i < tranDetailList.size(); i++) {
            TranDetail tranDetail = tranDetailList.get(i);
            if (tranDetail.getIsbn().getIsbn().equals(product.getIsbn())) {

                boolean ret = tranDetail.addOrPlusOneExpirationDateQty(product, expirationDate, qty, message);
                if (!ret) {
                    return true;
                }
                BigDecimal originalQty = tranDetailList.get(i).getQty();
                if (!isForReturn && qty.compareTo(BigDecimal.ZERO) > 0) {
                    tranDetail.setExtnIsbn(product);
                }
                tranDetail.setExtnQty(originalQty.add(qty));

                return true;
            }
        }
        return false;
    }

    protected void zeroCheck() {
        List<TranDetail> tranDetailList = (List<TranDetail>) getTranDetailCollection();
        for (int i = 0; i < tranDetailList.size(); i++) {
            TranDetail tranDetail = tranDetailList.get(i);
            if (tranDetail.getQty().compareTo(new BigDecimal(-0.001)) > 0 && tranDetail.getQty().compareTo(new BigDecimal(0.001)) < 0) {
                //tranDetail.setExtnQty(BigDecimal.ZERO);
                tranDetailList.remove(i);
                i--;
            }
        }
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void subtotal() {
        BigDecimal total = new BigDecimal(0);
        BigDecimal tax = new BigDecimal(0);
        BigDecimal discount = new BigDecimal(0);
        gstTax = new BigDecimal(0);
        pstTax = new BigDecimal(0);
        hstTax = new BigDecimal(0);


        List<TranDetail> tranDetailList = (List<TranDetail>) getTranDetailCollection();
        //numberOfItems = tranDetailList.size();
        numberOfItems = 0;
        if (tranDetailList != null) {
            for (int i = 0; i < tranDetailList.size(); i++) {
                TranDetail tranDetail = tranDetailList.get(i);
                numberOfItems += tranDetail.getQty().intValue();
                total = total.add(tranDetail.getSubTotal());
                tax = tax.add(tranDetail.getSubTax());
                discount = discount.add(tranDetail.getDiscountAmt());
                Tax taxTemp = tranDetail.getTax();
                if (taxTemp != null) {
                    if (taxTemp.getName().equals("GST")) {
                        gstTax = gstTax.add(tranDetail.getSubTax());
                    } else if (taxTemp.getName().equals("GST & PST")) {
                        gstTax = gstTax.add(tranDetail.getSubTax().multiply(new BigDecimal(5.0 / 13.0)));
                        pstTax = pstTax.add(tranDetail.getSubTax().multiply(new BigDecimal(8.0 / 13.0)));
                    } else if (taxTemp.getName().equals("HST")) {
                        hstTax = hstTax.add(tranDetail.getSubTax());
                    }
                }
            }
        }
        setLineDiscountAmt(discount);
        setLineTaxAmt(tax);
        setLineTotalAmt(total);
    }

    public BigDecimal getGstTax() {
        return TranDetail.roundToTwo(gstTax);
    }

    public BigDecimal getHstTax() {
        return TranDetail.roundToTwo(hstTax);
    }

    public BigDecimal getPstTax() {
        return TranDetail.roundToTwo(pstTax);
    }

    public void applyPromotionToNoPromoDetail(Promotion pro, boolean isToRemove) {
        List<TranDetail> list = (List<TranDetail>) getTranDetailCollection();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                TranDetail tranDetail = list.get(i);
                if (isToRemove) {
                    if (tranDetail.getPromotion() != null && tranDetail.getPromotion().getPromotionId().equals(pro.getPromotionId())) {
                        tranDetail.setExtnPromotion(null);
                        tranDetail.setExtnQty(tranDetail.getQty());
                    }
                } else {
                    if (tranDetail.getPromotion() == null && pro.isPromotionValid(tranDetail.getIsbn())) {
                        tranDetail.setExtnPromotion(pro);
                        tranDetail.setExtnQty(tranDetail.getQty());
                    }
                }
            }
        }
        subtotal();
    }

    public boolean addOrPlusOneTranDetail(Product product, String expirationDate, BigDecimal qty, StringBuffer message, boolean isForRetrun) {        
        justAddedIsbn = product.getIsbn();
        if (getTranDetailCollection() == null) {
            setTranDetailCollection(new ArrayList<TranDetail>());
        }
        if (!isIsbnExistAndAdd(product, expirationDate, qty, message, isForRetrun)) {
            TranDetail tranDetail = new TranDetail();

            tranDetail.setExtnIsbn(product);
            //tranDetail.setCost(product.getCost());
            tranDetail.setDiscountAmt(new BigDecimal(0));
            tranDetail.setDistributedHeadDiscountAmt(new BigDecimal(0));
            tranDetail.setLineManagerDiscount(null);
            tranDetail.setLineNetTotal(new BigDecimal(0));
            tranDetail.setManagerDiscountAmt(new BigDecimal(0));
            //tranDetail.setPrice(product.getPrice());
            //tranDetail.setPromotion(product.getPromotion());
            tranDetail.setSubSubTax(new BigDecimal(0));
            tranDetail.setSubSubTotal(new BigDecimal(0));
            tranDetail.setSubTax(new BigDecimal(0));
            //tranDetail.setTax(product.getTax());
            tranDetail.setTaxAmt(new BigDecimal(0));
            tranDetail.setTotal(new BigDecimal(0));
            if (expirationDate != null && expirationDate.length() > 0) {
                boolean ret = tranDetail.addOrPlusOneExpirationDateQty(product, expirationDate, qty, message);
                if (!ret) {
                    return false;
                }
            }
            tranDetail.setTranHeadId(this);
            getTranDetailCollection().add(tranDetail);
            tranDetail.setExtnQty(qty);

        }

        zeroCheck();
        if (message.length() > 0) {
            return false;
        }
        subtotal();
        return true;
    }

    public boolean isPayTypeExistAndAdd(Integer id, BigDecimal amt) {
        if (getTranPaymentCollection() == null || getTranPaymentCollection().size() == 0) {
            return false;
        }
        List<TranPayment> list = (List<TranPayment>) getTranPaymentCollection();
        for (int i = 0; i < list.size(); i++) {
            TranPayment tp = list.get(i);
            if (tp.getPaymentType().getId().equals(id)) {
                BigDecimal originalAmt = tp.getAmount();
                tp.setAmount(originalAmt.add(amt));
                return true;
            }
        }
        return false;
    }

    public void addOrPlusTranPayment(PaymentType pType, String amt) {
        BigDecimal amount = null;
        try {
            amount = new BigDecimal(amt);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        if (amount == null || (amount.compareTo(new BigDecimal("0.01")) < 0 && amount.compareTo(new BigDecimal("-0.01")) > 0)) {
            return;
        }
        if (getTranPaymentCollection() == null) {
            setTranPaymentCollection(new ArrayList<TranPayment>());
        }
        if (!isPayTypeExistAndAdd(pType.getId(), amount)) {
            TranPayment tp = new TranPayment();
            tp.setAmount(amount);
            tp.setPayDate(new Date());
            tp.setPaymentType(pType);
            tp.setTranHeadId(this);
            getTranPaymentCollection().add(tp);
        }
        tranPayZeroCheck();
    }

    protected void tranPayZeroCheck() {
        List<TranPayment> list = (List<TranPayment>) getTranPaymentCollection();
        for (int i = 0; i < list.size(); i++) {
            TranPayment p = list.get(i);
            if (p.getAmount().compareTo(new BigDecimal(-0.001)) > 0 && p.getAmount().compareTo(new BigDecimal(0.001)) < 0) {
                //p.setAmount(BigDecimal.ZERO);
                list.remove(i);
                i--;
            }
        }
    }

    public BigDecimal getPaymentTotal() {
        BigDecimal total = BigDecimal.ZERO;
        List<TranPayment> list = (List<TranPayment>) getTranPaymentCollection();
        if (list == null) {
            return total;
        }
        for (int i = 0; i < list.size(); i++) {
            total = total.add(list.get(i).getAmount());
        }
        return total;
    }

    protected BigDecimal canadaPennyRound(BigDecimal in)
    {
        //return in.multiply(new BigDecimal(2)).divide(new BigDecimal(10)).round(new MathContext(2)).multiply(new BigDecimal(10)).divide(new BigDecimal(2));
        return in.multiply(new BigDecimal(2)).setScale(1, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(2));
    }

    public BigDecimal getTotal() {
        return canadaPennyRound(getLineTotalAmt().add(getLineTaxAmt())).setScale(2);
    }

    public BigDecimal getBalance() {
        return getTotal().subtract(getBottleRefund()).subtract(getPaymentTotal()).setScale(2);
    }

    public BigDecimal getPayAmountByPayType(Integer id) {
        List<TranPayment> list = (List<TranPayment>) getTranPaymentCollection();
        if (list == null) {
            return BigDecimal.ZERO;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPaymentType().getId().equals(id)) {
                return list.get(i).getAmount();
            }
        }
        return BigDecimal.ZERO;
    }

    public void saveTranPayment(TranPaymentJpaController controller) throws Exception {
        List<TranPayment> list = (List<TranPayment>) getTranPaymentCollection();
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            TranPayment tp = list.get(i);
            tp.setTranHeadId(this);
            controller.create(tp);
        }
    }

    public void saveTranDetails(TranDetailJpaController tranDetailJpaController,
            TranDetailLineExpirationQtyJpaController tranDetailLineExpirationQtyJpaController)
            throws Exception {
        List<TranDetail> list = (List<TranDetail>) getTranDetailCollection();
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            TranDetail td = list.get(i);
            td.setTranHeadId(this);
            Object tranLineExpQtys = td.getTranDetailLineExpirationQtyCollection();
            td.setTranDetailLineExpirationQtyCollection(null);
            tranDetailJpaController.create(td);
            td.setTranDetailLineExpirationQtyCollection((List<TranDetailLineExpirationQty>) tranLineExpQtys);
            td.saveTranlineExpirationDate(tranDetailLineExpirationQtyJpaController);
        }
    }

    protected TranHead getTheLatestTranHeadFromColection(TranHead tranHead) {
        List<TranHead> list = (List<TranHead>) tranHead.getTranHeadCollection();
        if (list == null || list.size() == 0) {
            return tranHead;
        }
        TranHead latestTh = tranHead;
        for (int i = 0; i < list.size(); i++) {
            TranHead th = list.get(i);
            if (th.getIsForReturned() == null || th.getIsForReturned().getName().equals(new Character('N'))) {
                if (th.getTranDate().after(latestTh.getTranDate())) {
                    latestTh = th;
                }

            }
        }
        return latestTh;
    }

    public void copyToSuper(TranHead in, boolean isRegularCopy, boolean isGettingLatest) {
        //find the latest TranHead
        if (isGettingLatest) {
            TranHead latest = getTheLatestTranHeadFromColection(in);
            while (!in.equals(latest)) {
                in = latest;
                latest = getTheLatestTranHeadFromColection(in);
            }
        }
        setUserId(in.getUserId());
        setBottleRefund(in.getBottleRefund());
        setHeadManagerDiscount(in.getHeadManagerDiscount());
        setHeadNetTotal(in.getHeadNetTotal());
        setIsTrainingMode(in.getIsTrainingMode());
        setLineDiscountAmt(in.getLineDiscountAmt());
        setLineTaxAmt(in.getLineTaxAmt());
        setLineTotalAmt(in.getLineTotalAmt());
        setManagerDiscountAmt(in.getManagerDiscountAmt());
        setNote(in.getNote());
        if (!isRegularCopy) {
            setOriginalHeadId(in);
        } else {
            setOriginalHeadId(in.getOriginalHeadId());
        }
        setReturnTranHeadCollection(in.getReturnTranHeadCollection());
        setTaxAfterHeadDiscount(in.getTaxAfterHeadDiscount());
        setTotalAfterHeadDiscount(in.getTotalAfterHeadDiscount());
        setTranDate(in.getTranDate());
        if (in.getTranDetailCollection() != null) {
            List<TranDetail> l = (List<TranDetail>) in.getTranDetailCollection();
            if (!isRegularCopy) {
                for (int i = 0; i <
                        l.size(); i++) {
                    TranDetail tp = l.get(i);
                    tp.setTranDetailId(null);
                //List<TranDetailLineExpirationQty> ll = (List<TranDetailLineExpirationQty>) tp.getTranDetailLineExpirationQtyCollection();
                //if ( ll != null )

                }
            }
            setTranDetailCollection(l);
        }

        setTranHeadId(in.getTranHeadId());
        if (isRegularCopy) {
            setTranHeadCollection(in.getTranHeadCollection());
        }
        if (in.getTranPaymentCollection() != null) {
            List<TranPayment> l = (List<TranPayment>) in.getTranPaymentCollection();
            if (!isRegularCopy) {
                for (int i = 0; i <
                        l.size(); i++) {
                    TranPayment tp = l.get(i);
                    tp.setId(null);
                }
            }
            setTranPaymentCollection(l);
        }
        subtotal();
    }
}
