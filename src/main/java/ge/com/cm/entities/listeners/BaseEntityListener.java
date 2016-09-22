/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.cm.entities.listeners;

import ge.com.cm.entities.BaseEntity;
import static java.lang.Boolean.TRUE;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author Giorgi
 */
public class BaseEntityListener {
    
    
    @PrePersist
    public void persistListener(BaseEntity entity) {
        entity.setIsActive(TRUE);
        entity.setModifyDate(new Date());
        
        //TODO add user who inserted 
    }
    
    @PreUpdate
    public void updateListener(BaseEntity entity) {
        entity.setModifyDate(new Date());
        
        //TODO add user who updated 
    }
    
}
