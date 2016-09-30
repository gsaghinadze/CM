/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.ws;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import static com.sun.jndi.toolkit.dir.DirSearch.search;
import ge.com.cm.entities.Car;
import ge.com.cm.entities.Driver;
import ge.com.cm.entities.Route;
import ge.com.cm.utils.GridParams;
import ge.com.cm.utils.MethodResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @PersistenceContext
    private EntityManager em;

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public MethodResponse getRoutes(GridParams p) {
        
        String sql = "from Route r";
        
        String sqlc = "select count(1) from Route r";
        
        String where = " where r.isActive = 1";
        
        String order = " order by r.startTime desc , r.id";
        
        Map<String, Object> hm = new HashMap<String, Object>();
        
        
            
            if (p.getFilterDate() != null){
                 where+= " and :filterDate between r.startTime and r.finishTime";
                 hm.put("filterDate", p.getFilterDate());
            }
            
            if (p.getFilterCarId() != null){
                 where += " and r.carId = :carId";
                 hm.put("carId", p.getFilterCarId());
            }
            
            
            if (p.getFilterRouteDesc() != null && (p.getFilterRouteDesc()).trim() != ""){
                
                Splitter splitter = Splitter.on(CharMatcher.anyOf(" ;,.'\"")).trimResults().omitEmptyStrings();
                List<String> searchWords = splitter.splitToList(Strings.nullToEmpty((String)p.getFilterRouteDesc()));
                
                int i = 0;

                for (String searchWord : searchWords) {

                    where += " and r.routeDescription like :search_" + i;

                    hm.put("search_" + i, "%" + searchWord + "%");
                    i++;
                }
            }
        
        
        
        Query q = em.createQuery(sql + where + order);
        q.setFirstResult(p.getStart());
        q.setMaxResults(p.getLimit());
        
        Query qc = em.createQuery(sqlc + where);
        

        for(Entry<String,Object> entry: hm.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            qc.setParameter(entry.getKey(), entry.getValue());
        }

        System.out.println("sql == " + sql + where + order);
        
        
        List<Route> routes = q.getResultList();
        Long totalCount = (Long)(qc.getResultList().get(0));
        return new MethodResponse(routes, totalCount);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void editRoute(Route p) {

        Route route = null;
        if (p.getId() == null) {
            route = new Route();
            route.setStatus(1);
        } else {
            route = em.find(Route.class, p.getId(), LockModeType.WRITE);
            route.setStatus(p.getStatus());
        }

        Car car = em.find(Car.class, p.getCarId());
        Driver driver = em.find(Driver.class, p.getDriverId());

        route.setCarId(car.getId());
        route.setDriverId(driver.getId());
        route.setStartTime(p.getStartTime());
        route.setFinishTime(p.getFinishTime());
        route.setRouteDescription(p.getRouteDescription());

        em.merge(route);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void removeRoute(Integer id) {
        Route route = em.find(Route.class, id, LockModeType.WRITE);
        route.setIsActive(false);
        em.merge(route);
    }

}
