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
@Table(name = "TRAN_DETAIL_LINE_EXPIRATION_QTY")
@NamedQueries({@NamedQuery(name = "TranDetailLineExpirationQty.findAll", query = "SELECT t FROM TranDetailLineExpirationQty t"), @NamedQuery(name = "TranDetailLineExpirationQty.findByTranDetailId", query = "SELECT t FROM TranDetailLineExpirationQty t WHERE t.tranDetailLineExpirationQtyPK.tranDetailId = :tranDetailId"), @NamedQuery(name = "TranDetailLineExpirationQty.findByExpirationDate", query = "SELECT t FROM TranDetailLineExpirationQty t WHERE t.tranDetailLineExpirationQtyPK.expirationDate = :expirationDate"), @NamedQuery(name = "TranDetailLineExpirationQty.findByQty", query = "SELECT t FROM TranDetailLineExpirationQty t WHERE t.qty = :qty")})
public class TranDetailLineExpirationQty implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TranDetailLineExpirationQtyPK tranDetailLineExpirationQtyPK;
    @Column(name = "QTY")
    private BigDecimal qty;
    @JoinColumn(name = "TRAN_DETAIL_ID", referencedColumnName = "TRAN_DETAIL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TranDetail tranDetail;

    public TranDetailLineExpirationQty() {
    }

    public TranDetailLineExpirationQty(TranDetailLineExpirationQtyPK tranDetailLineExpirationQtyPK) {
        this.tranDetailLineExpirationQtyPK = tranDetailLineExpirationQtyPK;
    }

    public TranDetailLineExpirationQty(int tranDetailId, Date expirationDate) {
        this.tranDetailLineExpirationQtyPK = new TranDetailLineExpirationQtyPK(tranDetailId, expirationDate);
    }

    public TranDetailLineExpirationQtyPK getTranDetailLineExpirationQtyPK() {
        return tranDetailLineExpirationQtyPK;
    }

    public void setTranDetailLineExpirationQtyPK(TranDetailLineExpirationQtyPK tranDetailLineExpirationQtyPK) {
        this.tranDetailLineExpirationQtyPK = tranDetailLineExpirationQtyPK;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public TranDetail getTranDetail() {
        return tranDetail;
    }

    public void setTranDetail(TranDetail tranDetail) {
        this.tranDetail = tranDetail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tranDetailLineExpirationQtyPK != null ? tranDetailLineExpirationQtyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TranDetailLineExpirationQty)) {
            return false;
        }
        TranDetailLineExpirationQty other = (TranDetailLineExpirationQty) object;
        if ((this.tranDetailLineExpirationQtyPK == null && other.tranDetailLineExpirationQtyPK != null) || (this.tranDetailLineExpirationQtyPK != null && !this.tranDetailLineExpirationQtyPK.equals(other.tranDetailLineExpirationQtyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.TranDetailLineExpirationQty[tranDetailLineExpirationQtyPK=" + tranDetailLineExpirationQtyPK + "]";
    }

}
