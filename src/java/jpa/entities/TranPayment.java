/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author achen
 */
@Entity
@Table(name = "TRAN_PAYMENT")
@NamedQueries({@NamedQuery(name = "TranPayment.findAll", query = "SELECT t FROM TranPayment t"), @NamedQuery(name = "TranPayment.findById", query = "SELECT t FROM TranPayment t WHERE t.id = :id"), @NamedQuery(name = "TranPayment.findByAmount", query = "SELECT t FROM TranPayment t WHERE t.amount = :amount"), @NamedQuery(name = "TranPayment.findByPayDate", query = "SELECT t FROM TranPayment t WHERE t.payDate = :payDate"), @NamedQuery(name = "TranPayment.findByPayTimestamp", query = "SELECT t FROM TranPayment t WHERE t.payTimestamp = :payTimestamp")})
public class TranPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "PAY_DATE")
    @Temporal(TemporalType.DATE)
    private Date payDate;
    @Column(name = "PAY_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payTimestamp;
    @JoinColumn(name = "PAYMENT_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PaymentType paymentType;
    @JoinColumn(name = "TRAN_HEAD_ID", referencedColumnName = "TRAN_HEAD_ID")
    @ManyToOne(optional = false)
    private TranHead tranHeadId;

    public TranPayment() {
    }

    public TranPayment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPayDate() {
        if (payTimestamp != null) {
            return payTimestamp;
        }
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
        setPayTimestamp(payDate);
    }

    public Date getPayTimestamp() {
        return payTimestamp;
    }

    public void setPayTimestamp(Date payTimestamp) {
        this.payTimestamp = payTimestamp;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public TranHead getTranHeadId() {
        return tranHeadId;
    }

    public void setTranHeadId(TranHead tranHeadId) {
        this.tranHeadId = tranHeadId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TranPayment)) {
            return false;
        }
        TranPayment other = (TranPayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getPaymentType() + " - $" + getAmount();
    }
}
