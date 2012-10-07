/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author achen
 */
@Entity
@Table(name = "RETURN_TRAN_DETAIL_LINE_EXPIRATION_QTY")
@NamedQueries({@NamedQuery(name = "ReturnTranDetailLineExpirationQty.findAll", query = "SELECT r FROM ReturnTranDetailLineExpirationQty r"), @NamedQuery(name = "ReturnTranDetailLineExpirationQty.findByTranDetailId", query = "SELECT r FROM ReturnTranDetailLineExpirationQty r WHERE r.returnTranDetailLineExpirationQtyPK.tranDetailId = :tranDetailId"), @NamedQuery(name = "ReturnTranDetailLineExpirationQty.findByExpirationDate", query = "SELECT r FROM ReturnTranDetailLineExpirationQty r WHERE r.returnTranDetailLineExpirationQtyPK.expirationDate = :expirationDate"), @NamedQuery(name = "ReturnTranDetailLineExpirationQty.findByQty", query = "SELECT r FROM ReturnTranDetailLineExpirationQty r WHERE r.qty = :qty")})
public class ReturnTranDetailLineExpirationQty implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReturnTranDetailLineExpirationQtyPK returnTranDetailLineExpirationQtyPK;
    @Column(name = "QTY")
    private BigDecimal qty;
    @JoinColumn(name = "TRAN_DETAIL_ID", referencedColumnName = "TRAN_DETAIL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ReturnTranDetail returnTranDetail;

    public ReturnTranDetailLineExpirationQty() {
    }

    public ReturnTranDetailLineExpirationQty(ReturnTranDetailLineExpirationQtyPK returnTranDetailLineExpirationQtyPK) {
        this.returnTranDetailLineExpirationQtyPK = returnTranDetailLineExpirationQtyPK;
    }

    public ReturnTranDetailLineExpirationQty(int tranDetailId, Date expirationDate) {
        this.returnTranDetailLineExpirationQtyPK = new ReturnTranDetailLineExpirationQtyPK(tranDetailId, expirationDate);
    }

    public ReturnTranDetailLineExpirationQtyPK getReturnTranDetailLineExpirationQtyPK() {
        return returnTranDetailLineExpirationQtyPK;
    }

    public void setReturnTranDetailLineExpirationQtyPK(ReturnTranDetailLineExpirationQtyPK returnTranDetailLineExpirationQtyPK) {
        this.returnTranDetailLineExpirationQtyPK = returnTranDetailLineExpirationQtyPK;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public ReturnTranDetail getReturnTranDetail() {
        return returnTranDetail;
    }

    public void setReturnTranDetail(ReturnTranDetail returnTranDetail) {
        this.returnTranDetail = returnTranDetail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (returnTranDetailLineExpirationQtyPK != null ? returnTranDetailLineExpirationQtyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReturnTranDetailLineExpirationQty)) {
            return false;
        }
        ReturnTranDetailLineExpirationQty other = (ReturnTranDetailLineExpirationQty) object;
        if ((this.returnTranDetailLineExpirationQtyPK == null && other.returnTranDetailLineExpirationQtyPK != null) || (this.returnTranDetailLineExpirationQtyPK != null && !this.returnTranDetailLineExpirationQtyPK.equals(other.returnTranDetailLineExpirationQtyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.ReturnTranDetailLineExpirationQty[returnTranDetailLineExpirationQtyPK=" + returnTranDetailLineExpirationQtyPK + "]";
    }

}
