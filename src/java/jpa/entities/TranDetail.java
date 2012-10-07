/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jpa.controllers.TranDetailLineExpirationQtyJpaController;

/**
 *
 * @author achen
 */
@Entity
@Table(name = "TRAN_DETAIL")
@NamedQueries({@NamedQuery(name = "TranDetail.findAll", query = "SELECT t FROM TranDetail t"), @NamedQuery(name = "TranDetail.findByTranDetailId", query = "SELECT t FROM TranDetail t WHERE t.tranDetailId = :tranDetailId"), @NamedQuery(name = "TranDetail.findByQty", query = "SELECT t FROM TranDetail t WHERE t.qty = :qty"), @NamedQuery(name = "TranDetail.findByCost", query = "SELECT t FROM TranDetail t WHERE t.cost = :cost"), @NamedQuery(name = "TranDetail.findByPrice", query = "SELECT t FROM TranDetail t WHERE t.price = :price"), @NamedQuery(name = "TranDetail.findByDiscountAmt", query = "SELECT t FROM TranDetail t WHERE t.discountAmt = :discountAmt"), @NamedQuery(name = "TranDetail.findByTotal", query = "SELECT t FROM TranDetail t WHERE t.total = :total"), @NamedQuery(name = "TranDetail.findByTaxAmt", query = "SELECT t FROM TranDetail t WHERE t.taxAmt = :taxAmt"), @NamedQuery(name = "TranDetail.findByManagerDiscountAmt", query = "SELECT t FROM TranDetail t WHERE t.managerDiscountAmt = :managerDiscountAmt"), @NamedQuery(name = "TranDetail.findBySubTotal", query = "SELECT t FROM TranDetail t WHERE t.subTotal = :subTotal"), @NamedQuery(name = "TranDetail.findBySubTax", query = "SELECT t FROM TranDetail t WHERE t.subTax = :subTax"), @NamedQuery(name = "TranDetail.findByDistributedHeadDiscountAmt", query = "SELECT t FROM TranDetail t WHERE t.distributedHeadDiscountAmt = :distributedHeadDiscountAmt"), @NamedQuery(name = "TranDetail.findBySubSubTotal", query = "SELECT t FROM TranDetail t WHERE t.subSubTotal = :subSubTotal"), @NamedQuery(name = "TranDetail.findBySubSubTax", query = "SELECT t FROM TranDetail t WHERE t.subSubTax = :subSubTax"), @NamedQuery(name = "TranDetail.findByLineNetTotal", query = "SELECT t FROM TranDetail t WHERE t.lineNetTotal = :lineNetTotal")})
public class TranDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRAN_DETAIL_ID")
    private Integer tranDetailId;
    @Basic(optional = false)
    @Column(name = "QTY")
    private BigDecimal qty;
    @Column(name = "COST")
    private BigDecimal cost;
    @Basic(optional = false)
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "DISCOUNT_AMT")
    private BigDecimal discountAmt;
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Column(name = "TAX_AMT")
    private BigDecimal taxAmt;
    @Column(name = "MANAGER_DISCOUNT_AMT")
    private BigDecimal managerDiscountAmt;
    @Column(name = "SUB_TOTAL")
    private BigDecimal subTotal;
    @Column(name = "SUB_TAX")
    private BigDecimal subTax;
    @Column(name = "DISTRIBUTED_HEAD_DISCOUNT_AMT")
    private BigDecimal distributedHeadDiscountAmt;
    @Column(name = "SUB_SUB_TOTAL")
    private BigDecimal subSubTotal;
    @Column(name = "SUB_SUB_TAX")
    private BigDecimal subSubTax;
    @Column(name = "LINE_NET_TOTAL")
    private BigDecimal lineNetTotal;
    @JoinColumn(name = "LINE_MANAGER_DISCOUNT", referencedColumnName = "DISCOUNT_ID")
    @ManyToOne
    private Discount lineManagerDiscount;
    @JoinColumn(name = "DISCOUNT", referencedColumnName = "DISCOUNT_ID")
    @ManyToOne
    private Discount discount;
    @JoinColumn(name = "ISBN", referencedColumnName = "ISBN")
    @ManyToOne(optional = false)
    private Product isbn;
    @JoinColumn(name = "PROMOTION", referencedColumnName = "PROMOTION_ID")
    @ManyToOne
    private Promotion promotion;
    @JoinColumn(name = "TAX", referencedColumnName = "TAX_ID")
    @ManyToOne
    private Tax tax;
    @JoinColumn(name = "TRAN_HEAD_ID", referencedColumnName = "TRAN_HEAD_ID")
    @ManyToOne(optional = false)
    private TranHead tranHeadId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tranDetail", fetch=FetchType.LAZY)
    private Collection<TranDetailLineExpirationQty> tranDetailLineExpirationQtyCollection;

    public TranDetail() {
    }

    public TranDetail(Integer tranDetailId) {
        this.tranDetailId = tranDetailId;
    }

    public TranDetail(Integer tranDetailId, BigDecimal qty, BigDecimal price) {
        this.tranDetailId = tranDetailId;
        this.qty = qty;
        this.price = price;
    }

    public Integer getTranDetailId() {
        return tranDetailId;
    }

    public void setTranDetailId(Integer tranDetailId) {
        this.tranDetailId = tranDetailId;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(BigDecimal discountAmt) {
        this.discountAmt = discountAmt;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    public BigDecimal getManagerDiscountAmt() {
        return managerDiscountAmt;
    }

    public void setManagerDiscountAmt(BigDecimal managerDiscountAmt) {
        this.managerDiscountAmt = managerDiscountAmt;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getSubTax() {
        return subTax;
    }

    public void setSubTax(BigDecimal subTax) {
        this.subTax = subTax;
    }

    public BigDecimal getDistributedHeadDiscountAmt() {
        return distributedHeadDiscountAmt;
    }

    public void setDistributedHeadDiscountAmt(BigDecimal distributedHeadDiscountAmt) {
        this.distributedHeadDiscountAmt = distributedHeadDiscountAmt;
    }

    public BigDecimal getSubSubTotal() {
        return subSubTotal;
    }

    public void setSubSubTotal(BigDecimal subSubTotal) {
        this.subSubTotal = subSubTotal;
    }

    public BigDecimal getSubSubTax() {
        return subSubTax;
    }

    public void setSubSubTax(BigDecimal subSubTax) {
        this.subSubTax = subSubTax;
    }

    public BigDecimal getLineNetTotal() {
        return lineNetTotal;
    }

    public void setLineNetTotal(BigDecimal lineNetTotal) {
        this.lineNetTotal = lineNetTotal;
    }

    public Discount getLineManagerDiscount() {
        return lineManagerDiscount;
    }

    public void setLineManagerDiscount(Discount lineManagerDiscount) {
        this.lineManagerDiscount = lineManagerDiscount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Product getIsbn() {
        return isbn;
    }

    public void setIsbn(Product isbn) {
        this.isbn = isbn;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public TranHead getTranHeadId() {
        return tranHeadId;
    }

    public void setTranHeadId(TranHead tranHeadId) {
        this.tranHeadId = tranHeadId;
    }

    public Collection<TranDetailLineExpirationQty> getTranDetailLineExpirationQtyCollection() {
        return tranDetailLineExpirationQtyCollection;
    }

    public void setTranDetailLineExpirationQtyCollection(Collection<TranDetailLineExpirationQty> tranDetailLineExpirationQtyCollection) {
        this.tranDetailLineExpirationQtyCollection = tranDetailLineExpirationQtyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tranDetailId != null ? tranDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TranDetail)) {
            return false;
        }
        TranDetail other = (TranDetail) object;
        if ((this.tranDetailId == null && other.tranDetailId != null) || (this.tranDetailId != null && !this.tranDetailId.equals(other.tranDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIsbn() + " - " + getQty();
    }

    protected void reDoBuyMixedPromotion(int promotionId, String discountType, BigDecimal buyHow, BigDecimal getHow) {

        BigDecimal remain = new BigDecimal(0);
        BigDecimal totalDolar = new BigDecimal(0);

        List<TranDetail> list = (List<TranDetail>) getTranHeadId().getTranDetailCollection();
        if (discountType.equals("BMF")) {
            buyHow = buyHow.add(getHow);
        }
        for (int i = 0; i < list.size(); i++) {
            TranDetail tranDetail = list.get(i);
            if (tranDetail.getPromotion() != null) {
                if (tranDetail.getPromotion().getPromotionId() == promotionId) {
                    if (discountType.equals("BMDP") || discountType.equals("BMODP") || discountType.equals("BMDD")) {
                        totalDolar = totalDolar.add(tranDetail.getTotal());
                    } else {

                        //BigDecimal ret = (tranDetail.getQty().add(remain)).divide(buyHow, 0, RoundingMode.DOWN);
                        BigDecimal ret = (tranDetail.getQty().add(remain)).divide(buyHow, 2, RoundingMode.HALF_UP);
                        if (ret.intValue() >= 1) {
                            if (discountType.equals("BMF")) {
                                tranDetail.setSubTotal(tranDetail.getTotal().subtract(ret.multiply(tranDetail.getPrice().multiply(getHow))));
                            } else if (discountType.equals("BMD")) {
                                tranDetail.setSubTotal(tranDetail.getTotal().subtract(ret.multiply(getHow)));
                            }
                        } else {
                            tranDetail.setSubTotal(tranDetail.getTotal());
                        }
                        tranDetail.wrapCalculate();
                        remain = tranDetail.getQty().add(remain).remainder(buyHow);
                    }
                }
            }
        }

        BigDecimal restDollarOff = getHow;
        if (discountType.equals("BMDP") || discountType.equals("BMODP") || discountType.equals("BMDD")) {
            for (int i = 0; i < list.size(); i++) {
                TranDetail tranDetail = list.get(i);
                if (tranDetail.getPromotion() != null && tranDetail.getPromotion().getPromotionId() == promotionId) {
                    if (totalDolar.compareTo(buyHow) >= 0) {
                        if (discountType.equals("BMDD")) {
                            //distribut the $ off to line item
                            //tranDetail.setSubTotal(tranDetail.getTotal().subtract(getHow.multiply(tranDetail.getTotal()).divide(totalDolar)));
                            BigDecimal dollarOff = new BigDecimal(getHow.doubleValue() * tranDetail.getTotal().doubleValue() / totalDolar.doubleValue());
                            if (restDollarOff.subtract(dollarOff).compareTo(new BigDecimal(0.01)) >= 0) {
                                tranDetail.setSubTotal(tranDetail.getTotal().subtract(dollarOff));
                                restDollarOff = restDollarOff.subtract(dollarOff);
                            } else {
                                tranDetail.setSubTotal(tranDetail.getTotal().subtract(restDollarOff));
                            }
                        } else {
                            tranDetail.setSubTotal(tranDetail.getTotal().multiply(new BigDecimal(1 - getHow.doubleValue())));
                        }
                    } else {
                        tranDetail.setSubTotal(tranDetail.getTotal());
                    }
                    tranDetail.wrapCalculate();
                }
            }
        }
    }

    protected void wrapCalculate() {
        setSubTax(getSubTotal().multiply(taxNullFilter(getTax())));
        setDiscountAmt(getTotal().subtract(getSubTotal()));
        setLineNetTotal(getSubTotal().add(getSubTax()));
        roundAll();
    }

    protected void calculatePromotion() {
        //if (getPromotion() == null || !getPromotion().isPromotionValid() || getDiscount() == null) {
        //return;
        //}
        if (getPromotion() == null || getDiscount() == null) {
            return;
        }

        //String discountType = getPromotion().getDiscount().getType().getType();
        // BigDecimal buyHow = getPromotion().getDiscount().getBuyHow();
        // BigDecimal getHow = getPromotion().getDiscount().getGetHow();

        String discountType = getDiscount().getType().getType();
        BigDecimal buyHow = getDiscount().getBuyHow();
        BigDecimal getHow = getDiscount().getGetHow();

        if (discountType.equals("P")) {
            setSubTotal(getTotal().multiply(new BigDecimal(1 - getHow.doubleValue())));
            wrapCalculate();

        } else if (discountType.equals("D")) {
            setSubTotal(getTotal().subtract(getHow.multiply(getQty())));
            wrapCalculate();
        } else if (discountType.equals("BF")) {
            setSubTotal(getTotal().subtract(getQty().divide(buyHow.add(getHow), 0, RoundingMode.DOWN).multiply(getPrice().multiply(getHow))));
            wrapCalculate();
        } else if (discountType.equals("BD")) {
            setSubTotal(getTotal().subtract(getQty().divide(buyHow, 0, RoundingMode.DOWN).multiply(getHow)));
            wrapCalculate();
        } else if (discountType.equals("BMF")) {
            reDoBuyMixedPromotion(getPromotion().getPromotionId(), discountType, buyHow, getHow);
        } else if (discountType.equals("BMD")) {
            reDoBuyMixedPromotion(getPromotion().getPromotionId(), discountType, buyHow, getHow);
        } else if (discountType.equals("BMDP")) {
            reDoBuyMixedPromotion(getPromotion().getPromotionId(), discountType, buyHow, getHow);
        } else if (discountType.equals("BMODP")) {
            reDoBuyMixedPromotion(getPromotion().getPromotionId(), discountType, buyHow, getHow);
        } else if (discountType.equals("BMDD")) {
            reDoBuyMixedPromotion(getPromotion().getPromotionId(), discountType, buyHow, getHow);
        }

    }

    protected BigDecimal taxNullFilter(Tax in) {
        if (in == null) {
            return BigDecimal.ZERO;
        }
        return bigDecimalNullFilter(in.getRate());
    }

    protected BigDecimal bigDecimalNullFilter(BigDecimal in) {
        if (in == null) {
            return BigDecimal.ZERO;
        }
        return in;
    }

    public static BigDecimal roundToTwo(BigDecimal in) {
        if (in == null) {
            return BigDecimal.ZERO;
        }
        return in.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    protected void roundAll() {
        setTaxAmt(roundToTwo(getTaxAmt()));
        setTotal(roundToTwo(getTotal()));
        setSubTax(roundToTwo(getSubTax()));
        setSubTotal(roundToTwo(getSubTotal()));
        setDiscountAmt(roundToTwo(getDiscountAmt()));
        setLineNetTotal(roundToTwo(getLineNetTotal()));
    }

    public void calculate() {


        BigDecimal taxRate = taxNullFilter(getTax());

        setTotal(getQty().multiply(bigDecimalNullFilter(getPrice())));
        setTaxAmt(taxRate.multiply(getTotal()));

        setSubTotal(getTotal());

        wrapCalculate();

        calculatePromotion();

        roundAll();

    }

    public boolean isDetailLineExpirationExist(String isbn, String expirationDate, BigDecimal qty, StringBuffer message) {

        List<TranDetailLineExpirationQty> list = (List<TranDetailLineExpirationQty>) getTranDetailLineExpirationQtyCollection();
        if (list == null || list.size() == 0) {
            return false;
        }

        for (int i = 0; i <
                list.size(); i++) {
            if (list.get(i).getTranDetailLineExpirationQtyPK().getExpirationDate().equals(new Date(expirationDate))) {
                BigDecimal originalQty = list.get(i).getQty();
                if (originalQty.add(qty).compareTo(BigDecimal.ZERO) < 0) {
                    message.append("Too many ISBN " + isbn + " " + expirationDate + " to remove!<br>");
                    message.append("Only " + originalQty + " available to be removed!");
                    return true;
                }
                list.get(i).setQty(originalQty.add(qty));
                return true;
            }

        }
        return false;
    }

    protected void zeroCheck() {
        List<TranDetailLineExpirationQty> list = (List<TranDetailLineExpirationQty>) getTranDetailLineExpirationQtyCollection();
        if (list == null) {
            return;
        }
        for (int i = 0; i <
                list.size(); i++) {
            TranDetailLineExpirationQty unit = list.get(i);
            if (unit.getQty().compareTo(new BigDecimal(0.001)) < 0 && unit.getQty().compareTo(new BigDecimal(-0.001)) > 0) {
                list.remove(i);
                i--;
            }
        }
    }

    public boolean addOrPlusOneExpirationDateQty(Product product, String expirationDate, BigDecimal qty, StringBuffer message) {
        if (product.getHasExpirationDate() == null || product.getHasExpirationDate().getName().equals(new Character('N'))) {
            return true;
        }
        String isbn = product.getIsbn();
        if (getTranDetailLineExpirationQtyCollection() == null) {
            setTranDetailLineExpirationQtyCollection(new ArrayList<TranDetailLineExpirationQty>());
        }

        if (!isDetailLineExpirationExist(isbn, expirationDate, qty, message)) {
            if (qty.compareTo(BigDecimal.ZERO) < 0) {
                message.append("ISBN " + isbn + " expires on " + expirationDate + " is not available to remove from this transaction.");
                return false;
            }
            TranDetailLineExpirationQty exQty = new TranDetailLineExpirationQty();

            TranDetailLineExpirationQtyPK pk = new TranDetailLineExpirationQtyPK();
            pk.setExpirationDate(new Date(expirationDate));
            //pk.setTranDetailId(getTranDetailId());
            exQty.setQty(qty);
            exQty.setTranDetail(this);
            exQty.setTranDetailLineExpirationQtyPK(pk);
            getTranDetailLineExpirationQtyCollection().add(exQty);
        }
        if (message.length() > 0) {
            return false;
        }
        //zeroCheck();
        return true;

    }

    public void setExtnIsbn(Product isbn) {
        this.isbn = isbn;
        setCost(getIsbn().getCost());
        setPrice(getIsbn().getPrice());
        setExtnPromotion(getIsbn().getPromotion());
        Tax tempTax = getIsbn().getTax();
        if (tempTax == null) {
            tempTax = new Tax();
            tempTax.setName("Tax Free");
            tempTax.setRate(BigDecimal.ZERO);
            tempTax.setTaxId(new Integer(3));
        }
        setTax(tempTax);
    }

    public void setExtnPromotion(Promotion promotion) {
        if (promotion == null) {
            this.promotion = null;
        } else if (promotion != null && promotion.isPromotionValid(getIsbn())) {
            this.promotion = promotion;
            setDiscount(getPromotion().getDiscount());
        } else if (getIsbn().getBrandDiscountExcluded() == null || getIsbn().getBrandDiscountExcluded().getName().charValue() == 'N') {
            if (getIsbn().getBrand() != null && getIsbn().getBrand().getPromotion() != null && getIsbn().getBrand().getPromotion().isPromotionValid(getIsbn())) {
                this.promotion = getIsbn().getBrand().getPromotion();
                setDiscount(getPromotion().getDiscount());
            }
        }
    }

    public void setExtnQty(BigDecimal qty) {
        this.qty = qty;
        calculate();
    }

    public void saveTranlineExpirationDate(TranDetailLineExpirationQtyJpaController controller)
            throws Exception {
        List<TranDetailLineExpirationQty> list = (List<TranDetailLineExpirationQty>) getTranDetailLineExpirationQtyCollection();
        if (list == null || list.size() == 0) {
            return;
        }
        zeroCheck();
        for (int i = 0; i < list.size(); i++) {
            TranDetailLineExpirationQty td = list.get(i);
            td.getTranDetailLineExpirationQtyPK().setTranDetailId(getTranDetailId().intValue());
            //td.setTranDetail(this);
            controller.create(td);
        }
    }
}
