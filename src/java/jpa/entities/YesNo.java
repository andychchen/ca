/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author achen
 */
@Entity
@Table(name = "YES_NO")
@NamedQueries({@NamedQuery(name = "YesNo.findAll", query = "SELECT y FROM YesNo y"), @NamedQuery(name = "YesNo.findByName", query = "SELECT y FROM YesNo y WHERE y.name = :name")})
public class YesNo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NAME")
    private Character name;
    @OneToMany(mappedBy = "isEnabled", fetch=FetchType.LAZY)
    private Collection<Promotion> promotionCollection;
    @OneToMany(mappedBy = "isAppliedToAll", fetch=FetchType.LAZY)
    private Collection<Promotion> promotionCollection1;
    @OneToMany(mappedBy = "isReturned", fetch=FetchType.LAZY)
    private Collection<ReturnTranDetail> returnTranDetailCollection;
    @OneToMany(mappedBy = "isTrainingMode", fetch=FetchType.LAZY)
    private Collection<TranHead> tranHeadCollection;
    @OneToMany(mappedBy = "isOrganic", fetch=FetchType.LAZY)
    private Collection<Product> productCollection;
    @OneToMany(mappedBy = "hasExpirationDate", fetch=FetchType.LAZY)
    private Collection<Product> productCollection1;
    @OneToMany(mappedBy = "brandDiscountExcluded", fetch=FetchType.LAZY)
    private Collection<Product> productCollection2;
    @OneToMany(mappedBy = "isTrainingMode", fetch=FetchType.LAZY)
    private Collection<ReturnTranHead> returnTranHeadCollection;

    public YesNo() {
    }

    public YesNo(Character name) {
        this.name = name;
    }

    public Character getName() {
        return name;
    }

    public void setName(Character name) {
        this.name = name;
    }

    public Collection<Promotion> getPromotionCollection() {
        return promotionCollection;
    }

    public void setPromotionCollection(Collection<Promotion> promotionCollection) {
        this.promotionCollection = promotionCollection;
    }

    public Collection<Promotion> getPromotionCollection1() {
        return promotionCollection1;
    }

    public void setPromotionCollection1(Collection<Promotion> promotionCollection1) {
        this.promotionCollection1 = promotionCollection1;
    }

    public Collection<ReturnTranDetail> getReturnTranDetailCollection() {
        return returnTranDetailCollection;
    }

    public void setReturnTranDetailCollection(Collection<ReturnTranDetail> returnTranDetailCollection) {
        this.returnTranDetailCollection = returnTranDetailCollection;
    }

    public Collection<TranHead> getTranHeadCollection() {
        return tranHeadCollection;
    }

    public void setTranHeadCollection(Collection<TranHead> tranHeadCollection) {
        this.tranHeadCollection = tranHeadCollection;
    }

    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    public Collection<Product> getProductCollection1() {
        return productCollection1;
    }

    public void setProductCollection1(Collection<Product> productCollection1) {
        this.productCollection1 = productCollection1;
    }

    public Collection<Product> getProductCollection2() {
        return productCollection2;
    }

    public void setProductCollection2(Collection<Product> productCollection2) {
        this.productCollection2 = productCollection2;
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
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof YesNo)) {
            return false;
        }
        YesNo other = (YesNo) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(getName());
    }
}
