/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "TAX")
@NamedQueries({@NamedQuery(name = "Tax.findAll", query = "SELECT t FROM Tax t"), @NamedQuery(name = "Tax.findByTaxId", query = "SELECT t FROM Tax t WHERE t.taxId = :taxId"), @NamedQuery(name = "Tax.findByName", query = "SELECT t FROM Tax t WHERE t.name = :name"), @NamedQuery(name = "Tax.findByRate", query = "SELECT t FROM Tax t WHERE t.rate = :rate")})
public class Tax implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TAX_ID")
    private Integer taxId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "RATE")
    private BigDecimal rate;
    @OneToMany(mappedBy = "tax", fetch=FetchType.LAZY)
    private Collection<TranDetail> tranDetailCollection;
    @OneToMany(mappedBy = "tax", fetch=FetchType.LAZY)
    private Collection<ReturnTranDetail> returnTranDetailCollection;
    @OneToMany(mappedBy = "tax", fetch=FetchType.LAZY)
    private Collection<Product> productCollection;

    public Tax() {
    }

    public Tax(Integer taxId) {
        this.taxId = taxId;
    }

    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
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

    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxId != null ? taxId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tax)) {
            return false;
        }
        Tax other = (Tax) object;
        if ((this.taxId == null && other.taxId != null) || (this.taxId != null && !this.taxId.equals(other.taxId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName() + " - " + getRate().doubleValue() * 100 + "%";
    }
}
