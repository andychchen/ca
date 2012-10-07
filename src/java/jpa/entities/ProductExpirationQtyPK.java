/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
public class ProductExpirationQtyPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ISBN")
    private String isbn;
    @Basic(optional = false)
    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    public ProductExpirationQtyPK() {
    }

    public ProductExpirationQtyPK(String isbn, Date expirationDate) {
        this.isbn = isbn;
        this.expirationDate = expirationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
        hash += (isbn != null ? isbn.hashCode() : 0);
        hash += (expirationDate != null ? expirationDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductExpirationQtyPK)) {
            return false;
        }
        ProductExpirationQtyPK other = (ProductExpirationQtyPK) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        if ((this.expirationDate == null && other.expirationDate != null) || (this.expirationDate != null && !this.expirationDate.equals(other.expirationDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIsbn() + " - " + new SimpleDateFormat("MM/dd/yyyy").format(getExpirationDate());
    }
}
