/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.cm.ctrl;

import ge.com.cm.entities.Route;
import ge.com.cm.utils.GridParams;
import ge.com.cm.utils.MethodResponse;
import ge.com.ws.RouteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Giorgi
 */
@RestController
@RequestMapping(value = "/api/RouteController", produces = "application/json;charset=UTF-8")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/getRoutes")
    public MethodResponse getRoutes(GridParams p) {
        MethodResponse mr = routeService.getRoutes(p);
        return mr;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/editRoute")
    public MethodResponse editRoute(@RequestBody Route route) {
        routeService.editRoute(route);
        return MethodResponse.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/removeRoute")
    public MethodResponse removeRoute(Integer id) {
        routeService.removeRoute(id);
        return MethodResponse.SUCCESS;
    }
}
