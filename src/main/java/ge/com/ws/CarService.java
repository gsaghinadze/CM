/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.ws;

import ge.com.cm.entities.Car;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;


@Service
public class CarService {
    @PersistenceContext
    private EntityManager em;
    
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<Car> getCars() {
        return em.createQuery("from Car where isActive = 1", Car.class).getResultList();
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void editCar(Car p) {
        
        Car car = null;
        if (p.getId() == null){
           car = new Car();
           car.setStatus(1);
        } else{
            car = em.find(Car.class, p.getId(), LockModeType.WRITE);
            car.setStatus(p.getStatus());
            
            //car.setStatus(1);
            //TODO web ze statusebi ar maqvs da unda davabruno mere ukan.
            
        }   
        
        car.setCarNumber(p.getCarNumber());
        car.setCarModel(p.getCarModel());
        car.setDateIssued(p.getDateIssued());
        
        em.merge(car);           
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void removeCar(Integer id) {
        Car car = em.find(Car.class, id , LockModeType.WRITE);
        car.setIsActive(false);
        em.merge(car);
    }
    
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<Car> getGarageCars( @DateTimeFormat(pattern = "yyyy-MM-dd") Date date ) {
        if(date == null) {
            date = new Date();
        }
        Query q = em.createQuery("from Car c where c.isActive = 1 and not exists(select 1 from Route r where r.carId = c.id and :filterDate between r.startTime and r.finishTime)");
        
        q.setParameter("filterDate", date);
                
        return q.getResultList();
    }

}
