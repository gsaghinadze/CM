/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.cm.ctrl;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Giorgi
 */
@Controller
public class defaultController {
    
    
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ModelAndView redirectMain(HttpServletRequest request, HttpServletResponse response ) throws IOException {
        return new ModelAndView("main");
    }
    
}
