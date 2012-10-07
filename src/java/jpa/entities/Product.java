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
import javax.persistence.FetchType;
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
@Table(name = "PRODUCT")
@NamedQueries({@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"), @NamedQuery(name = "Product.findByIsbn", query = "SELECT p FROM Product p WHERE p.isbn = :isbn"), @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"), @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"), @NamedQuery(name = "Product.findByQty", query = "SELECT p FROM Product p WHERE p.qty = :qty"), @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"), @NamedQuery(name = "Product.findByCost", query = "SELECT p FROM Product p WHERE p.cost = :cost"), @NamedQuery(name = "Product.findByLastUpdateDate", query = "SELECT p FROM Product p WHERE p.lastUpdateDate = :lastUpdateDate")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "QTY")
    private BigDecimal qty;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "COST")
    private BigDecimal cost;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "isbn", fetch=FetchType.LAZY)
    private Collection<TranDetail> tranDetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "isbn", fetch=FetchType.LAZY)
    private Collection<ReturnTranDetail> returnTranDetailCollection;
    @JoinColumn(name = "BRAND", referencedColumnName = "BRAND_ID")
    @ManyToOne
    private Brand brand;
    @JoinColumn(name = "PROMOTION", referencedColumnName = "PROMOTION_ID")
    @ManyToOne
    private Promotion promotion;
    @JoinColumn(name = "TAX", referencedColumnName = "TAX_ID")
    @ManyToOne
    private Tax tax;
    @JoinColumn(name = "IS_ORGANIC", referencedColumnName = "NAME")
    @ManyToOne
    private YesNo isOrganic;
    @JoinColumn(name = "HAS_EXPIRATION_DATE", referencedColumnName = "NAME")
    @ManyToOne
    private YesNo hasExpirationDate;
    @JoinColumn(name = "BRAND_DISCOUNT_EXCLUDED", referencedColumnName = "NAME")
    @ManyToOne
    private YesNo brandDiscountExcluded;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch=FetchType.LAZY)
    private Collection<ProductExpirationQty> productExpirationQtyCollection;

    public Product() {
    }

    public Product(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Collection<TranDetail> getTranDetailCollection() {
        return tranDetailCollection;
    }

    public void setTranDetailCollection(Collection<TranDetail> tranDetailCollection) {
        this.tranDetailCollection = tranDetailCollection;
    }

    public Collection<ReturnTranDetail> getReturnTranDetailCollection() {
        return returnTranDetailCollection;
    }

    public void setReturnTranDetailCollection(Collection<ReturnTranDetail> returnTranDetailCollection) {
        this.returnTranDetailCollection = returnTranDetailCollection;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public YesNo getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(YesNo isOrganic) {
        this.isOrganic = isOrganic;
    }

    public YesNo getHasExpirationDate() {
        return hasExpirationDate;
    }

    public void setHasExpirationDate(YesNo hasExpirationDate) {
        this.hasExpirationDate = hasExpirationDate;
    }

    public YesNo getBrandDiscountExcluded() {
        return brandDiscountExcluded;
    }

    public void setBrandDiscountExcluded(YesNo brandDiscountExcluded) {
        this.brandDiscountExcluded = brandDiscountExcluded;
    }

    public Collection<ProductExpirationQty> getProductExpirationQtyCollection() {
        return productExpirationQtyCollection;
    }

    public void setProductExpirationQtyCollection(Collection<ProductExpirationQty> productExpirationQtyCollection) {
        this.productExpirationQtyCollection = productExpirationQtyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName() + " - " + getIsbn();
    }

}
