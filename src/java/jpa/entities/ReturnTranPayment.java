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
@Table(name = "RETURN_TRAN_PAYMENT")
@NamedQueries({@NamedQuery(name = "ReturnTranPayment.findAll", query = "SELECT r FROM ReturnTranPayment r"), @NamedQuery(name = "ReturnTranPayment.findById", query = "SELECT r FROM ReturnTranPayment r WHERE r.id = :id"), @NamedQuery(name = "ReturnTranPayment.findByAmount", query = "SELECT r FROM ReturnTranPayment r WHERE r.amount = :amount"), @NamedQuery(name = "ReturnTranPayment.findByPayDate", query = "SELECT r FROM ReturnTranPayment r WHERE r.payDate = :payDate")})
public class ReturnTranPayment implements Serializable {
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
    @JoinColumn(name = "PAYMENT_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PaymentType paymentType;
    @JoinColumn(name = "TRAN_HEAD_ID", referencedColumnName = "TRAN_HEAD_ID")
    @ManyToOne(optional = false)
    private ReturnTranHead tranHeadId;

    public ReturnTranPayment() {
    }

    public ReturnTranPayment(Integer id) {
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
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public ReturnTranHead getTranHeadId() {
        return tranHeadId;
    }

    public void setTranHeadId(ReturnTranHead tranHeadId) {
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
        if (!(object instanceof ReturnTranPayment)) {
            return false;
        }
        ReturnTranPayment other = (ReturnTranPayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.ReturnTranPayment[id=" + id + "]";
    }

}
