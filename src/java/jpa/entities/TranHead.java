/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author achen
 */
@Entity
@Table(name = "TRAN_HEAD")
@NamedQueries({@NamedQuery(name = "TranHead.findAll", query = "SELECT t FROM TranHead t"), @NamedQuery(name = "TranHead.findByTranHeadId", query = "SELECT t FROM TranHead t WHERE t.tranHeadId = :tranHeadId"), @NamedQuery(name = "TranHead.findByTranDate", query = "SELECT t FROM TranHead t WHERE t.tranDate = :tranDate"), @NamedQuery(name = "TranHead.findByTranTimestamp", query = "SELECT t FROM TranHead t WHERE t.tranTimestamp = :tranTimestamp"), @NamedQuery(name = "TranHead.findByLineTotalAmt", query = "SELECT t FROM TranHead t WHERE t.lineTotalAmt = :lineTotalAmt"), @NamedQuery(name = "TranHead.findByLineTaxAmt", query = "SELECT t FROM TranHead t WHERE t.lineTaxAmt = :lineTaxAmt"), @NamedQuery(name = "TranHead.findByLineDiscountAmt", query = "SELECT t FROM TranHead t WHERE t.lineDiscountAmt = :lineDiscountAmt"), @NamedQuery(name = "TranHead.findByTotalAfterHeadDiscount", query = "SELECT t FROM TranHead t WHERE t.totalAfterHeadDiscount = :totalAfterHeadDiscount"), @NamedQuery(name = "TranHead.findByTaxAfterHeadDiscount", query = "SELECT t FROM TranHead t WHERE t.taxAfterHeadDiscount = :taxAfterHeadDiscount"), @NamedQuery(name = "TranHead.findByManagerDiscountAmt", query = "SELECT t FROM TranHead t WHERE t.managerDiscountAmt = :managerDiscountAmt"), @NamedQuery(name = "TranHead.findByBottleRefund", query = "SELECT t FROM TranHead t WHERE t.bottleRefund = :bottleRefund"), @NamedQuery(name = "TranHead.findByHeadNetTotal", query = "SELECT t FROM TranHead t WHERE t.headNetTotal = :headNetTotal"), @NamedQuery(name = "TranHead.findByNote", query = "SELECT t FROM TranHead t WHERE t.note = :note")})
public class TranHead implements Serializable {

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
    @Column(name = "TRAN_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tranTimestamp;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tranHeadId", fetch=FetchType.LAZY)
    private Collection<TranDetail> tranDetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tranHeadId")
    private Collection<TranPayment> tranPaymentCollection;
    @JoinColumn(name = "HEAD_MANAGER_DISCOUNT", referencedColumnName = "DISCOUNT_ID")
    @ManyToOne
    private Discount headManagerDiscount;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne
    private EmpUsers userId;
    @OneToMany(mappedBy = "originalHeadId", fetch=FetchType.LAZY)
    private Collection<TranHead> tranHeadCollection;
    @JoinColumn(name = "ORIGINAL_HEAD_ID", referencedColumnName = "TRAN_HEAD_ID")
    @ManyToOne
    private TranHead originalHeadId;
    @JoinColumn(name = "IS_FOR_RETURNED", referencedColumnName = "NAME")
    @ManyToOne
    private YesNo isForReturned;
    @JoinColumn(name = "IS_TRAINING_MODE", referencedColumnName = "NAME")
    @ManyToOne
    private YesNo isTrainingMode;
    @OneToMany(mappedBy = "originalTranHeadId", fetch=FetchType.LAZY)
    private Collection<ReturnTranHead> returnTranHeadCollection;

    public TranHead() {
    }

    public TranHead(Integer tranHeadId) {
        this.tranHeadId = tranHeadId;
    }

    public TranHead(Integer tranHeadId, Date tranDate, BigDecimal lineTotalAmt, BigDecimal lineTaxAmt, BigDecimal lineDiscountAmt, BigDecimal totalAfterHeadDiscount, BigDecimal taxAfterHeadDiscount, BigDecimal managerDiscountAmt, BigDecimal bottleRefund, BigDecimal headNetTotal) {
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
    }

    public Integer getTranHeadId() {
        return tranHeadId;
    }

    public void setTranHeadId(Integer tranHeadId) {
        this.tranHeadId = tranHeadId;
    }

    public Date getTranDate() {
        if (tranTimestamp != null) {
            return tranTimestamp;
        }
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
        setTranTimestamp(tranDate);
    }

    public Date getTranTimestamp() {
        return tranTimestamp;
    }

    public void setTranTimestamp(Date tranTimestamp) {
        this.tranTimestamp = tranTimestamp;
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

    public Collection<TranDetail> getTranDetailCollection() {
        return tranDetailCollection;
    }

    public void setTranDetailCollection(Collection<TranDetail> tranDetailCollection) {
        this.tranDetailCollection = tranDetailCollection;
    }

    public Collection<TranPayment> getTranPaymentCollection() {
        return tranPaymentCollection;
    }

    public void setTranPaymentCollection(Collection<TranPayment> tranPaymentCollection) {
        this.tranPaymentCollection = tranPaymentCollection;
    }

    public Discount getHeadManagerDiscount() {
        return headManagerDiscount;
    }

    public void setHeadManagerDiscount(Discount headManagerDiscount) {
        this.headManagerDiscount = headManagerDiscount;
    }

    public EmpUsers getUserId() {
        return userId;
    }

    public void setUserId(EmpUsers userId) {
        this.userId = userId;
    }

    public Collection<TranHead> getTranHeadCollection() {
        return tranHeadCollection;
    }

    public void setTranHeadCollection(Collection<TranHead> tranHeadCollection) {
        this.tranHeadCollection = tranHeadCollection;
    }

    public TranHead getOriginalHeadId() {
        return originalHeadId;
    }

    public void setOriginalHeadId(TranHead originalHeadId) {
        this.originalHeadId = originalHeadId;
    }

    public YesNo getIsForReturned() {
        return isForReturned;
    }

    public void setIsForReturned(YesNo isForReturned) {
        this.isForReturned = isForReturned;
    }

    public YesNo getIsTrainingMode() {
        return isTrainingMode;
    }

    public void setIsTrainingMode(YesNo isTrainingMode) {
        this.isTrainingMode = isTrainingMode;
    }

    public Collection<ReturnTranHead> getReturnTranHeadCollection() {
        return returnTranHeadCollection;
    }

    public void setReturnTranHeadCollection(Collection<ReturnTranHead> returnTranHeadCollection) {
        this.returnTranHeadCollection = returnTranHeadCollection;
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
        if (!(object instanceof TranHead)) {
            return false;
        }
        TranHead other = (TranHead) object;
        if ((this.tranHeadId == null && other.tranHeadId != null) || (this.tranHeadId != null && !this.tranHeadId.equals(other.tranHeadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(getTranHeadId()) + " - " + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(getTranDate());
    }
}
