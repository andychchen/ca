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
@Table(name = "WEEK_DAYS")
@NamedQueries({@NamedQuery(name = "WeekDays.findAll", query = "SELECT w FROM WeekDays w"), @NamedQuery(name = "WeekDays.findByName", query = "SELECT w FROM WeekDays w WHERE w.name = :name"), @NamedQuery(name = "WeekDays.findByDay", query = "SELECT w FROM WeekDays w WHERE w.day = :day")})
public class WeekDays implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Id
    @Basic(optional = false)
    @Column(name = "DAY")
    private Integer day;
    @OneToMany(mappedBy = "weeklyDay", fetch=FetchType.LAZY)
    private Collection<Promotion> promotionCollection;

    public WeekDays() {
    }

    public WeekDays(Integer day) {
        this.day = day;
    }

    public WeekDays(Integer day, String name) {
        this.day = day;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Collection<Promotion> getPromotionCollection() {
        return promotionCollection;
    }

    public void setPromotionCollection(Collection<Promotion> promotionCollection) {
        this.promotionCollection = promotionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (day != null ? day.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WeekDays)) {
            return false;
        }
        WeekDays other = (WeekDays) object;
        if ((this.day == null && other.day != null) || (this.day != null && !this.day.equals(other.day))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

}
