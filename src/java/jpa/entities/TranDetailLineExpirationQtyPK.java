/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author achen
 */
@Embeddable
public class TranDetailLineExpirationQtyPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "TRAN_DETAIL_ID")
    private int tranDetailId;
    @Basic(optional = false)
    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    public TranDetailLineExpirationQtyPK() {
    }

    public TranDetailLineExpirationQtyPK(int tranDetailId, Date expirationDate) {
        this.tranDetailId = tranDetailId;
        this.expirationDate = expirationDate;
    }

    public int getTranDetailId() {
        return tranDetailId;
    }

    public void setTranDetailId(int tranDetailId) {
        this.tranDetailId = tranDetailId;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tranDetailId;
        hash += (expirationDate != null ? expirationDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TranDetailLineExpirationQtyPK)) {
            return false;
        }
        TranDetailLineExpirationQtyPK other = (TranDetailLineExpirationQtyPK) object;
        if (this.tranDetailId != other.tranDetailId) {
            return false;
        }
        if ((this.expirationDate == null && other.expirationDate != null) || (this.expirationDate != null && !this.expirationDate.equals(other.expirationDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.TranDetailLineExpirationQtyPK[tranDetailId=" + tranDetailId + ", expirationDate=" + expirationDate + "]";
    }

}
