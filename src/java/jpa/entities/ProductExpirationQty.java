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
@Table(name = "PRODUCT_EXPIRATION_QTY")
@NamedQueries({@NamedQuery(name = "ProductExpirationQty.findAll", query = "SELECT p FROM ProductExpirationQty p"), @NamedQuery(name = "ProductExpirationQty.findByIsbn", query = "SELECT p FROM ProductExpirationQty p WHERE p.productExpirationQtyPK.isbn = :isbn"), @NamedQuery(name = "ProductExpirationQty.findByExpirationDate", query = "SELECT p FROM ProductExpirationQty p WHERE p.productExpirationQtyPK.expirationDate = :expirationDate"), @NamedQuery(name = "ProductExpirationQty.findByQty", query = "SELECT p FROM ProductExpirationQty p WHERE p.qty = :qty")})
public class ProductExpirationQty implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductExpirationQtyPK productExpirationQtyPK;
    @Basic(optional = false)
    @Column(name = "QTY")
    private BigDecimal qty;
    @JoinColumn(name = "ISBN", referencedColumnName = "ISBN", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public ProductExpirationQty() {
    }

    public ProductExpirationQty(ProductExpirationQtyPK productExpirationQtyPK) {
        this.productExpirationQtyPK = productExpirationQtyPK;
    }

    public ProductExpirationQty(ProductExpirationQtyPK productExpirationQtyPK, BigDecimal qty) {
        this.productExpirationQtyPK = productExpirationQtyPK;
        this.qty = qty;
    }

    public ProductExpirationQty(String isbn, Date expirationDate) {
        this.productExpirationQtyPK = new ProductExpirationQtyPK(isbn, expirationDate);
    }

    public ProductExpirationQtyPK getProductExpirationQtyPK() {
        return productExpirationQtyPK;
    }

    public void setProductExpirationQtyPK(ProductExpirationQtyPK productExpirationQtyPK) {
        this.productExpirationQtyPK = productExpirationQtyPK;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productExpirationQtyPK != null ? productExpirationQtyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductExpirationQty)) {
            return false;
        }
        ProductExpirationQty other = (ProductExpirationQty) object;
        if ((this.productExpirationQtyPK == null && other.productExpirationQtyPK != null) || (this.productExpirationQtyPK != null && !this.productExpirationQtyPK.equals(other.productExpirationQtyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getProductExpirationQtyPK() + " - " + getQty();
    }
}
