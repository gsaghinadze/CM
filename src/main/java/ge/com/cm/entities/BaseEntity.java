/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.cm.entities;

import ge.com.cm.entities.listeners.BaseEntityListener;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.envers.Audited;

/**
 *
 * @author Giorgi
 */

@Audited
@EntityListeners(BaseEntityListener.class)
@MappedSuperclass
public class BaseEntity {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_DATE" , nullable = false)
    private Date modifyDate;
    
    @Column(name = "IS_ACTIVE" , nullable = false)
    private Boolean isActive;
    
    @Version
    private Long version;
    
    //TODO add userid

    
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    
}
