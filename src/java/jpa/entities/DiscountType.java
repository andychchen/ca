/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "DISCOUNT_TYPE")
@NamedQueries({@NamedQuery(name = "DiscountType.findAll", query = "SELECT d FROM DiscountType d"), @NamedQuery(name = "DiscountType.findByName", query = "SELECT d FROM DiscountType d WHERE d.name = :name"), @NamedQuery(name = "DiscountType.findByType", query = "SELECT d FROM DiscountType d WHERE d.type = :type")})
public class DiscountType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "NAME")
    private String name;
    @Id
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type", fetch=FetchType.LAZY)
    private Collection<Discount> discountCollection;

    public DiscountType() {
    }

    public DiscountType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Discount> getDiscountCollection() {
        return discountCollection;
    }

    public void setDiscountCollection(Collection<Discount> discountCollection) {
        this.discountCollection = discountCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (type != null ? type.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiscountType)) {
            return false;
        }
        DiscountType other = (DiscountType) object;
        if ((this.type == null && other.type != null) || (this.type != null && !this.type.equals(other.type))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

}
