/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.ws;

import ge.com.cm.entities.Car;
import ge.com.cm.entities.Driver;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class DriverService {
    @PersistenceContext
    private EntityManager em;
    
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<Driver> getDrivers() {
        return em.createQuery("from Driver where isActive = 1", Driver.class).getResultList();
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void editDriver(Driver p) {
        
        Driver driver = null;
        if (p.getId() == null){
           driver = new Driver();
           driver.setStatus(1);
        } else{
            driver = em.find(Driver.class, p.getId(), LockModeType.WRITE);
            driver.setStatus(p.getStatus());
        }   
        
        driver.setFirstName(p.getFirstName());
        driver.setLastName(p.getLastName());
        driver.setAddress(p.getAddress());
        driver.setPid(p.getPid());
        driver.setAddress(p.getAddress());
        driver.setPhone(p.getPhone());
        
        em.merge(driver);           
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void removeDriver(Integer id) {
        Driver driver = em.find(Driver.class, id , LockModeType.WRITE);
        driver.setIsActive(false);
        em.merge(driver);
    }
    
}
