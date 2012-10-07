/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author achen
 */
@Entity
@Table(name = "RETURN_TRAN_HEAD")
@NamedQueries({@NamedQuery(name = "ReturnTranHead.findAll", query = "SELECT r FROM ReturnTranHead r"), @NamedQuery(name = "ReturnTranHead.findByTranHeadId", query = "SELECT r FROM ReturnTranHead r WHERE r.tranHeadId = :tranHeadId"), @NamedQuery(name = "ReturnTranHead.findByTranDate", query = "SELECT r FROM ReturnTranHead r WHERE r.tranDate = :tranDate"), @NamedQuery(name = "ReturnTranHead.findByLineTotalAmt", query = "SELECT r FROM ReturnTranHead r WHERE r.lineTotalAmt = :lineTotalAmt"), @NamedQuery(name = "ReturnTranHead.findByLineTaxAmt", query = "SELECT r FROM ReturnTranHead r WHERE r.lineTaxAmt = :lineTaxAmt"), @NamedQuery(name = "ReturnTranHead.findByLineDiscountAmt", query = "SELECT r FROM ReturnTranHead r WHERE r.lineDiscountAmt = :lineDiscountAmt"), @NamedQuery(name = "ReturnTranHead.findByTotalAfterHeadDiscount", query = "SELECT r FROM ReturnTranHead r WHERE r.totalAfterHeadDiscount = :totalAfterHeadDiscount"), @NamedQuery(name = "ReturnTranHead.findByTaxAfterHeadDiscount", query = "SELECT r FROM ReturnTranHead r WHERE r.taxAfterHeadDiscount = :taxAfterHeadDiscount"), @NamedQuery(name = "ReturnTranHead.findByManagerDiscountAmt", query = "SELECT r FROM ReturnTranHead r WHERE r.managerDiscountAmt = :managerDiscountAmt"), @NamedQuery(name = "ReturnTranHead.findByBottleRefund", query = "SELECT r FROM ReturnTranHead r WHERE r.bottleRefund = :bottleRefund"), @NamedQuery(name = "ReturnTranHead.findByHeadNetTotal", query = "SELECT r FROM ReturnTranHead r WHERE r.headNetTotal = :headNetTotal"), @NamedQuery(name = "ReturnTranHead.findByNote", query = "SELECT r FROM ReturnTranHead r WHERE r.note = :note"), @NamedQuery(name = "ReturnTranHead.findByReturnDate", query = "SELECT r FROM ReturnTranHead r WHERE r.returnDate = :returnDate")})
public class ReturnTranHead implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRAN_HEAD_ID")
    private Integer tranHeadId;
    @Basic(optional = false)
    @Column(name = "TRAN_DATE")
    @Temporal(TemporalType.DATE)
    private Date tranDate;
    @Basic(optional = false)
    @Column(name = "LINE_TOTAL_AMT")
    private BigDecimal lineTotalAmt;
    @Basic(optional = false)
    @Column(name = "LINE_TAX_AMT")
    private BigDecimal lineTaxAmt;
    @Basic(optional = false)
    @Column(name = "LINE_DISCOUNT_AMT")
    private BigDecimal lineDiscountAmt;
    @Basic(optional = false)
    @Column(name = "TOTAL_AFTER_HEAD_DISCOUNT")
    private BigDecimal totalAfterHeadDiscount;
    @Basic(optional = false)
    @Column(name = "TAX_AFTER_HEAD_DISCOUNT")
    private BigDecimal taxAfterHeadDiscount;
    @Basic(optional = false)
    @Column(name = "MANAGER_DISCOUNT_AMT")
    private BigDecimal managerDiscountAmt;
    @Basic(optional = false)
    @Column(name = "BOTTLE_REFUND")
    private BigDecimal bottleRefund;
    @Basic(optional = false)
    @Column(name = "HEAD_NET_TOTAL")
    private BigDecimal headNetTotal;
    @Column(name = "NOTE")
    private String note;
    @Basic(optional = false)
    @Column(name = "RETURN_DATE")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tranHeadId")
    private Collection<ReturnTranDetail> returnTranDetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tranHeadId")
    private Collection<ReturnTranPayment> returnTranPaymentCollection;
    @JoinColumn(name = "HEAD_MANAGER_DISCOUNT", referencedColumnName = "DISCOUNT_ID")
    @ManyToOne
    private Discount headManagerDiscount;
    @JoinColumn(name = "ORIGINAL_TRAN_HEAD_ID", referencedColumnName = "TRAN_HEAD_ID")
    @ManyToOne
    private TranHead originalTranHeadId;
    @JoinColumn(name = "IS_TRAINING_MODE", referencedColumnName = "NAME")
    @ManyToOne
    private YesNo isTrainingMode;

    public ReturnTranHead() {
    }

    public ReturnTranHead(Integer tranHeadId) {
        this.tranHeadId = tranHeadId;
    }

    public ReturnTranHead(Integer tranHeadId, Date tranDate, BigDecimal lineTotalAmt, BigDecimal lineTaxAmt, BigDecimal lineDiscountAmt, BigDecimal totalAfterHeadDiscount, BigDecimal taxAfterHeadDiscount, BigDecimal managerDiscountAmt, BigDecimal bottleRefund, BigDecimal headNetTotal, Date returnDate) {
        this.tranHeadId = tranHeadId;
        this.tranDate = tranDate;
        this.lineTotalAmt = lineTotalAmt;
        this.lineTaxAmt = lineTaxAmt;
        this.lineDiscountAmt = lineDiscountAmt;
        this.totalAfterHeadDiscount = totalAfterHeadDiscount;
        this.taxAfterHeadDiscount = taxAfterHeadDiscount;
        this.managerDiscountAmt = managerDiscountAmt;
        this.bottleRefund = bottleRefund;
        this.headNetTotal = headNetTotal;
        this.returnDate = returnDate;
    }

    public Integer getTranHeadId() {
        return tranHeadId;
    }

    public void setTranHeadId(Integer tranHeadId) {
        this.tranHeadId = tranHeadId;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public BigDecimal getLineTotalAmt() {
        return lineTotalAmt;
    }

    public void setLineTotalAmt(BigDecimal lineTotalAmt) {
        this.lineTotalAmt = lineTotalAmt;
    }

    public BigDecimal getLineTaxAmt() {
        return lineTaxAmt;
    }

    public void setLineTaxAmt(BigDecimal lineTaxAmt) {
        this.lineTaxAmt = lineTaxAmt;
    }

    public BigDecimal getLineDiscountAmt() {
        return lineDiscountAmt;
    }

    public void setLineDiscountAmt(BigDecimal lineDiscountAmt) {
        this.lineDiscountAmt = lineDiscountAmt;
    }

    public BigDecimal getTotalAfterHeadDiscount() {
        return totalAfterHeadDiscount;
    }

    public void setTotalAfterHeadDiscount(BigDecimal totalAfterHeadDiscount) {
        this.totalAfterHeadDiscount = totalAfterHeadDiscount;
    }

    public BigDecimal getTaxAfterHeadDiscount() {
        return taxAfterHeadDiscount;
    }

    public void setTaxAfterHeadDiscount(BigDecimal taxAfterHeadDiscount) {
        this.taxAfterHeadDiscount = taxAfterHeadDiscount;
    }

    public BigDecimal getManagerDiscountAmt() {
        return managerDiscountAmt;
    }

    public void setManagerDiscountAmt(BigDecimal managerDiscountAmt) {
        this.managerDiscountAmt = managerDiscountAmt;
    }

    public BigDecimal getBottleRefund() {
        return bottleRefund;
    }

    public void setBottleRefund(BigDecimal bottleRefund) {
        this.bottleRefund = bottleRefund;
    }

    public BigDecimal getHeadNetTotal() {
        return headNetTotal;
    }

    public void setHeadNetTotal(BigDecimal headNetTotal) {
        this.headNetTotal = headNetTotal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Collection<ReturnTranDetail> getReturnTranDetailCollection() {
        return returnTranDetailCollection;
    }

    public void setReturnTranDetailCollection(Collection<ReturnTranDetail> returnTranDetailCollection) {
        this.returnTranDetailCollection = returnTranDetailCollection;
    }

    public Collection<ReturnTranPayment> getReturnTranPaymentCollection() {
        return returnTranPaymentCollection;
    }

    public void setReturnTranPaymentCollection(Collection<ReturnTranPayment> returnTranPaymentCollection) {
        this.returnTranPaymentCollection = returnTranPaymentCollection;
    }

    public Discount getHeadManagerDiscount() {
        return headManagerDiscount;
    }

    public void setHeadManagerDiscount(Discount headManagerDiscount) {
        this.headManagerDiscount = headManagerDiscount;
    }

    public TranHead getOriginalTranHeadId() {
        return originalTranHeadId;
    }

    public void setOriginalTranHeadId(TranHead originalTranHeadId) {
        this.originalTranHeadId = originalTranHeadId;
    }

    public YesNo getIsTrainingMode() {
        return isTrainingMode;
    }

    public void setIsTrainingMode(YesNo isTrainingMode) {
        this.isTrainingMode = isTrainingMode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tranHeadId != null ? tranHeadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReturnTranHead)) {
            return false;
        }
        ReturnTranHead other = (ReturnTranHead) object;
        if ((this.tranHeadId == null && other.tranHeadId != null) || (this.tranHeadId != null && !this.tranHeadId.equals(other.tranHeadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.ReturnTranHead[tranHeadId=" + tranHeadId + "]";
    }

}
