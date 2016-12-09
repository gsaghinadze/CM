/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.cm.ctrl;

import ge.com.cm.App;
import ge.com.cm.entities.Driver;
import ge.com.cm.utils.MethodResponse;
import ge.com.ws.DriverService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/DriverController" , produces = "application/json;charset=UTF-8")
public class DriverController {
    
    private static final Logger logger = LogManager.getLogger(App.class);
    
    @Autowired
    private DriverService driverService;
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/getDrivers")
    public MethodResponse getDrivers() {
        List<Driver> drivers = driverService.getDrivers();
        return new MethodResponse(drivers , drivers.size());
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/editDriver")
    public MethodResponse editDriver(@RequestBody Driver driver){
        logger.info(System.currentTimeMillis() + " driver controller starts");
        driverService.editDriver(driver);
        logger.info(System.currentTimeMillis() + " driver controller finishs");
        return MethodResponse.SUCCESS;
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/removeDriver")
    public MethodResponse removeDriver(Integer id){
        driverService.removeDriver(id);
        return MethodResponse.SUCCESS;
    }
}
