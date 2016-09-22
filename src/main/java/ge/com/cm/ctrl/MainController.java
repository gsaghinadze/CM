/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.cm.ctrl;

import ge.com.cm.entities.Car;
import ge.com.cm.entities.Driver;
import ge.com.cm.utils.MethodResponse;
import ge.com.ws.CarService;
import ge.com.ws.DriverService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Giorgi
 */
@RestController
@RequestMapping(value = "/api/MainController" , produces = "application/json;charset=UTF-8")
public class MainController {
    
    @Autowired
    private CarService carService;
    
    @Autowired
    private DriverService driverService;
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/getInitObjects")
    public MethodResponse getInitObjects() {
        List<Driver> drivers = driverService.getDrivers();
        List<Car> cars = carService.getCars();
        
        Map<String , Object> mp = new TreeMap<> ();        
        mp.put("cars", cars);
        mp.put("drivers", drivers);
        
        return new MethodResponse(mp);
    }
    
    
    
}
