/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.ws;

import ge.com.cm.App;
import ge.com.cm.entities.Driver;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class DriverService {
    
    private static final Logger logger = LogManager.getLogger(App.class);
    @PersistenceContext
    private EntityManager em;
    
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<Driver> getDrivers() {
        return em.createQuery("from Driver where isActive = 1", Driver.class).getResultList();
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void editDriver(Driver p) {
        logger.info(System.currentTimeMillis() + " Edit driver starts");
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
        
        logger.info(System.currentTimeMillis() + " driver parameters set");
        em.merge(driver);
        em.flush();
        logger.info(System.currentTimeMillis() + " merged driver into database");
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void removeDriver(Integer id) {
        Driver driver = em.find(Driver.class, id , LockModeType.WRITE);
        driver.setIsActive(false);
        em.merge(driver);
    }
    
}
