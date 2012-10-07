/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author achen
 */
@Entity
@Table(name = "RETURN_TRAN_DETAIL")
@NamedQueries({@NamedQuery(name = "ReturnTranDetail.findAll", query = "SELECT r FROM ReturnTranDetail r"), @NamedQuery(name = "ReturnTranDetail.findByTranDetailId", query = "SELECT r FROM ReturnTranDetail r WHERE r.tranDetailId = :tranDetailId"), @NamedQuery(name = "ReturnTranDetail.findByQty", query = "SELECT r FROM ReturnTranDetail r WHERE r.qty = :qty"), @NamedQuery(name = "ReturnTranDetail.findByCost", query = "SELECT r FROM ReturnTranDetail r WHERE r.cost = :cost"), @NamedQuery(name = "ReturnTranDetail.findByPrice", query = "SELECT r FROM ReturnTranDetail r WHERE r.price = :price"), @NamedQuery(name = "ReturnTranDetail.findByDiscountAmt", query = "SELECT r FROM ReturnTranDetail r WHERE r.discountAmt = :discountAmt"), @NamedQuery(name = "ReturnTranDetail.findByTotal", query = "SELECT r FROM ReturnTranDetail r WHERE r.total = :total"), @NamedQuery(name = "ReturnTranDetail.findByTaxAmt", query = "SELECT r FROM ReturnTranDetail r WHERE r.taxAmt = :taxAmt"), @NamedQuery(name = "ReturnTranDetail.findByManagerDiscountAmt", query = "SELECT r FROM ReturnTranDetail r WHERE r.managerDiscountAmt = :managerDiscountAmt"), @NamedQuery(name = "ReturnTranDetail.findBySubTotal", query = "SELECT r FROM ReturnTranDetail r WHERE r.subTotal = :subTotal"), @NamedQuery(name = "ReturnTranDetail.findBySubTax", query = "SELECT r FROM ReturnTranDetail r WHERE r.subTax = :subTax"), @NamedQuery(name = "ReturnTranDetail.findByDistributedHeadDiscountAmt", query = "SELECT r FROM ReturnTranDetail r WHERE r.distributedHeadDiscountAmt = :distributedHeadDiscountAmt"), @NamedQuery(name = "ReturnTranDetail.findBySubSubTotal", query = "SELECT r FROM ReturnTranDetail r WHERE r.subSubTotal = :subSubTotal"), @NamedQuery(name = "ReturnTranDetail.findBySubSubTax", query = "SELECT r FROM ReturnTranDetail r WHERE r.subSubTax = :subSubTax"), @NamedQuery(name = "ReturnTranDetail.findByLineNetTotal", query = "SELECT r FROM ReturnTranDetail r WHERE r.lineNetTotal = :lineNetTotal")})
public class ReturnTranDetail implements Serializable {
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
    @JoinColumn(name = "TRAN_HEAD_ID", referencedColumnName = "TRAN_HEAD_ID")
    @ManyToOne(optional = false)
    private ReturnTranHead tranHeadId;
    @JoinColumn(name = "TAX", referencedColumnName = "TAX_ID")
    @ManyToOne
    private Tax tax;
    @JoinColumn(name = "IS_RETURNED", referencedColumnName = "NAME")
    @ManyToOne
    private YesNo isReturned;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "returnTranDetail")
    private Collection<ReturnTranDetailLineExpirationQty> returnTranDetailLineExpirationQtyCollection;

    public ReturnTranDetail() {
    }

    public ReturnTranDetail(Integer tranDetailId) {
        this.tranDetailId = tranDetailId;
    }

    public ReturnTranDetail(Integer tranDetailId, BigDecimal qty, BigDecimal price) {
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

    public ReturnTranHead getTranHeadId() {
        return tranHeadId;
    }

    public void setTranHeadId(ReturnTranHead tranHeadId) {
        this.tranHeadId = tranHeadId;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public YesNo getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(YesNo isReturned) {
        this.isReturned = isReturned;
    }

    public Collection<ReturnTranDetailLineExpirationQty> getReturnTranDetailLineExpirationQtyCollection() {
        return returnTranDetailLineExpirationQtyCollection;
    }

    public void setReturnTranDetailLineExpirationQtyCollection(Collection<ReturnTranDetailLineExpirationQty> returnTranDetailLineExpirationQtyCollection) {
        this.returnTranDetailLineExpirationQtyCollection = returnTranDetailLineExpirationQtyCollection;
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
        if (!(object instanceof ReturnTranDetail)) {
            return false;
        }
        ReturnTranDetail other = (ReturnTranDetail) object;
        if ((this.tranDetailId == null && other.tranDetailId != null) || (this.tranDetailId != null && !this.tranDetailId.equals(other.tranDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.ReturnTranDetail[tranDetailId=" + tranDetailId + "]";
    }

}
