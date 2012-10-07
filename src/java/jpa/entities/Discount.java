/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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

/**
 *
 * @author achen
 */
@Entity
@Table(name = "DISCOUNT")
@NamedQueries({@NamedQuery(name = "Discount.findAll", query = "SELECT d FROM Discount d"), @NamedQuery(name = "Discount.findByDiscountId", query = "SELECT d FROM Discount d WHERE d.discountId = :discountId"), @NamedQuery(name = "Discount.findByName", query = "SELECT d FROM Discount d WHERE d.name = :name"), @NamedQuery(name = "Discount.findByBuyHow", query = "SELECT d FROM Discount d WHERE d.buyHow = :buyHow"), @NamedQuery(name = "Discount.findByGetHow", query = "SELECT d FROM Discount d WHERE d.getHow = :getHow")})
public class Discount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DISCOUNT_ID")
    private Integer discountId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "BUY_HOW")
    private BigDecimal buyHow;
    @Column(name = "GET_HOW")
    private BigDecimal getHow;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discount", fetch=FetchType.LAZY)
    private Collection<Promotion> promotionCollection;
    @OneToMany(mappedBy = "lineManagerDiscount", fetch=FetchType.LAZY)
    private Collection<TranDetail> tranDetailCollection;
    @OneToMany(mappedBy = "discount", fetch=FetchType.LAZY)
    private Collection<TranDetail> tranDetailCollection1;
    @JoinColumn(name = "TYPE", referencedColumnName = "TYPE")
    @ManyToOne(optional = false)
    private DiscountType type;
    @OneToMany(mappedBy = "lineManagerDiscount", fetch=FetchType.LAZY)
    private Collection<ReturnTranDetail> returnTranDetailCollection;
    @OneToMany(mappedBy = "discount", fetch=FetchType.LAZY)
    private Collection<ReturnTranDetail> returnTranDetailCollection1;
    @OneToMany(mappedBy = "headManagerDiscount", fetch=FetchType.LAZY)
    private Collection<TranHead> tranHeadCollection;
    @OneToMany(mappedBy = "headManagerDiscount", fetch=FetchType.LAZY)
    private Collection<ReturnTranHead> returnTranHeadCollection;

    public Discount() {
    }

    public Discount(Integer discountId) {
        this.discountId = discountId;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBuyHow() {
        return buyHow;
    }

    public void setBuyHow(BigDecimal buyHow) {
        this.buyHow = buyHow;
    }

    public BigDecimal getGetHow() {
        return getHow;
    }

    public void setGetHow(BigDecimal getHow) {
        this.getHow = getHow;
    }

    public Collection<Promotion> getPromotionCollection() {
        return promotionCollection;
    }

    public void setPromotionCollection(Collection<Promotion> promotionCollection) {
        this.promotionCollection = promotionCollection;
    }

    public Collection<TranDetail> getTranDetailCollection() {
        return tranDetailCollection;
    }

    public void setTranDetailCollection(Collection<TranDetail> tranDetailCollection) {
        this.tranDetailCollection = tranDetailCollection;
    }

    public Collection<TranDetail> getTranDetailCollection1() {
        return tranDetailCollection1;
    }

    public void setTranDetailCollection1(Collection<TranDetail> tranDetailCollection1) {
        this.tranDetailCollection1 = tranDetailCollection1;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public Collection<ReturnTranDetail> getReturnTranDetailCollection() {
        return returnTranDetailCollection;
    }

    public void setReturnTranDetailCollection(Collection<ReturnTranDetail> returnTranDetailCollection) {
        this.returnTranDetailCollection = returnTranDetailCollection;
    }

    public Collection<ReturnTranDetail> getReturnTranDetailCollection1() {
        return returnTranDetailCollection1;
    }

    public void setReturnTranDetailCollection1(Collection<ReturnTranDetail> returnTranDetailCollection1) {
        this.returnTranDetailCollection1 = returnTranDetailCollection1;
    }

    public Collection<TranHead> getTranHeadCollection() {
        return tranHeadCollection;
    }

    public void setTranHeadCollection(Collection<TranHead> tranHeadCollection) {
        this.tranHeadCollection = tranHeadCollection;
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
        hash += (discountId != null ? discountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discount)) {
            return false;
        }
        Discount other = (Discount) object;
        if ((this.discountId == null && other.discountId != null) || (this.discountId != null && !this.discountId.equals(other.discountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

}
