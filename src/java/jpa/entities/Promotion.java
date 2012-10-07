/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author achen
 */
@Entity
@Table(name = "PROMOTION")
@NamedQueries({@NamedQuery(name = "Promotion.findAppliedToAll", query = "SELECT p FROM Promotion p, YesNo yn WHERE p.isAppliedToAll = yn.name and yn.name = 'Y' "), @NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p"), @NamedQuery(name = "Promotion.findByPromotionId", query = "SELECT p FROM Promotion p WHERE p.promotionId = :promotionId"), @NamedQuery(name = "Promotion.findByName", query = "SELECT p FROM Promotion p WHERE p.name = :name"), @NamedQuery(name = "Promotion.findByStartDate", query = "SELECT p FROM Promotion p WHERE p.startDate = :startDate"), @NamedQuery(name = "Promotion.findByEndDate", query = "SELECT p FROM Promotion p WHERE p.endDate = :endDate"), @NamedQuery(name = "Promotion.findByYearlyMonth", query = "SELECT p FROM Promotion p WHERE p.yearlyMonth = :yearlyMonth"), @NamedQuery(name = "Promotion.findByMonthlyWeek", query = "SELECT p FROM Promotion p WHERE p.monthlyWeek = :monthlyWeek")})
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PROMOTION_ID")
    private Integer promotionId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "YEARLY_MONTH")
    private Integer yearlyMonth;
    @Column(name = "MONTHLY_WEEK")
    private Integer monthlyWeek;
    @OneToMany(mappedBy = "promotion", fetch=FetchType.LAZY)
    private Collection<TranDetail> tranDetailCollection;
    @JoinColumn(name = "DISCOUNT", referencedColumnName = "DISCOUNT_ID")
    @ManyToOne(optional = false)
    private Discount discount;
    @JoinColumn(name = "WEEKLY_DAY", referencedColumnName = "DAY")
    @ManyToOne
    private WeekDays weeklyDay;
    @JoinColumn(name = "IS_ENABLED", referencedColumnName = "NAME")
    @ManyToOne
    private YesNo isEnabled;
    @JoinColumn(name = "IS_APPLIED_TO_ALL", referencedColumnName = "NAME")
    @ManyToOne
    private YesNo isAppliedToAll;
    @OneToMany(mappedBy = "promotion", fetch=FetchType.LAZY)
    private Collection<ReturnTranDetail> returnTranDetailCollection;
    @OneToMany(mappedBy = "promotion", fetch=FetchType.LAZY)
    private Collection<Product> productCollection;
    @OneToMany(mappedBy = "promotion", fetch=FetchType.LAZY)
    private Collection<Brand> brandCollection;

    public Promotion() {
    }

    public Promotion(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public boolean isPromotionValid(Product product) {
        if (getIsEnabled() == null || getIsEnabled().getName() == null || getIsEnabled().getName().toString().equals("N")) {
            return false;
        }
        Date startDate = getStartDate();
        Date endDate = getEndDate();
        if (endDate != null) {
            endDate.setTime(endDate.getTime() + 24 * 60 * 60 * 1000);
        }
        Date today = new Date();


        if (startDate != null && endDate != null) {
            if (today.before(startDate) || today.after(endDate)) {
                return false;
            }
        } else if (startDate != null) {
            if (today.before(startDate)) {
                return false;
            }
        } else if (endDate != null) {
            if (today.after(endDate)) {
                return false;
            }
        }

        Integer yearlyMonth = getYearlyMonth();
        if (yearlyMonth != null && yearlyMonth.intValue() != (today.getMonth() + 1)) {
            return false;
        }

        Integer monthlyWeek = getMonthlyWeek();
        WeekDays weekDay = getWeeklyDay();
        Integer weekDayInt = null;
        if (weekDay != null) {
            weekDayInt = weekDay.getDay();
        }

        if (monthlyWeek != null && weekDay != null) {
            //creat the calendar for the month of the N th week's day ( Sunday, monday...)
            Calendar promoCal = Calendar.getInstance();
            promoCal.set(Calendar.DAY_OF_WEEK_IN_MONTH, monthlyWeek.intValue());


            if (today.getDay() != weekDayInt.intValue() || promoCal.getTime().getDate() != today.getDate()) {
                return false;
            }

        } else {
            if (weekDay != null) {
                if (today.getDay() != weekDayInt.intValue()) {
                    return false;
                }
            }
        }
        if (getDiscount().getType().getType().equals("BMODP")) {
            //if this promotion is for organic only
            if (product != null && (product.getIsOrganic() == null || product.getIsOrganic().getName().equals(new Character('N')))) {
                //not organic so return false
                return false;
            }
        }
        return true;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getYearlyMonth() {
        return yearlyMonth;
    }

    public void setYearlyMonth(Integer yearlyMonth) {
        this.yearlyMonth = yearlyMonth;
    }

    public Integer getMonthlyWeek() {
        return monthlyWeek;
    }

    public void setMonthlyWeek(Integer monthlyWeek) {
        this.monthlyWeek = monthlyWeek;
    }

    public Collection<TranDetail> getTranDetailCollection() {
        return tranDetailCollection;
    }

    public void setTranDetailCollection(Collection<TranDetail> tranDetailCollection) {
        this.tranDetailCollection = tranDetailCollection;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public WeekDays getWeeklyDay() {
        return weeklyDay;
    }

    public void setWeeklyDay(WeekDays weeklyDay) {
        this.weeklyDay = weeklyDay;
    }

    public YesNo getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(YesNo isEnabled) {
        this.isEnabled = isEnabled;
    }

    public YesNo getIsAppliedToAll() {
        return isAppliedToAll;
    }

    public void setIsAppliedToAll(YesNo isAppliedToAll) {
        this.isAppliedToAll = isAppliedToAll;
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

    public Collection<Brand> getBrandCollection() {
        return brandCollection;
    }

    public void setBrandCollection(Collection<Brand> brandCollection) {
        this.brandCollection = brandCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promotionId != null ? promotionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        if ((this.promotionId == null && other.promotionId != null) || (this.promotionId != null && !this.promotionId.equals(other.promotionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }
}
