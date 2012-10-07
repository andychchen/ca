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
@Table(name = "EMP_USERS")
@NamedQueries({@NamedQuery(name = "EmpUsers.findAll", query = "SELECT e FROM EmpUsers e"), @NamedQuery(name = "EmpUsers.findById", query = "SELECT e FROM EmpUsers e WHERE e.id = :id"), @NamedQuery(name = "EmpUsers.findByUserName", query = "SELECT e FROM EmpUsers e WHERE e.userName = :userName"), @NamedQuery(name = "EmpUsers.findByRole", query = "SELECT e FROM EmpUsers e WHERE e.role = :role")})
public class EmpUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "ROLE")
    private String role;
    @OneToMany(mappedBy = "userId", fetch=FetchType.LAZY)
    private Collection<TranHead> tranHeadCollection;

    public EmpUsers() {
    }

    public EmpUsers(String id) {
        this.id = id;
    }

    public EmpUsers(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<TranHead> getTranHeadCollection() {
        return tranHeadCollection;
    }

    public void setTranHeadCollection(Collection<TranHead> tranHeadCollection) {
        this.tranHeadCollection = tranHeadCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpUsers)) {
            return false;
        }
        EmpUsers other = (EmpUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getUserName() + "(" + getId() + ")";
    }
}
