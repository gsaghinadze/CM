/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.ws;

import ge.com.cm.entities.Car;
import ge.com.cm.entities.User;
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
public class UserService {

    @PersistenceContext
    private EntityManager em;

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public User checkAuth(String userName, String password) {
        try {
            Query q = em.createQuery("from User u where u.isActive = 1 and u.userName = :userName and u.password = :password", User.class);
            q.setParameter("userName", userName);
            q.setParameter("password", password);
            Object o = q.getResultList();
            User user = (User)q.getResultList().get(0);
            
            return user;
        } catch (Exception e) {
            return new User();
        }
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void editUser(User p) {
        
        User user = null;
        if (p.getId() == null){
           user = new User();
           user.setStatus(1);
        } else{
            user = em.find(User.class, p.getId(), LockModeType.WRITE);
            user.setStatus(p.getStatus());
            
            //car.setStatus(1);
            //TODO web ze statusebi ar maqvs da unda davabruno mere ukan.
            
        }   
        
        user.setPassword(p.getPassword());
        user.setUserName(p.getUserName());
        user.setRole(p.getRole());
        em.merge(user);           
    }
}
