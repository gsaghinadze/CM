package ge.com.cm.ctrl;

import ge.com.cm.entities.Car;
import ge.com.cm.entities.User;
import ge.com.cm.utils.MethodResponse;
import ge.com.ws.UserService;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/UserController", produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/checkAuth")
    public MethodResponse checkAuth(String userName ,String password) {
        User user = userService.checkAuth(userName, password);
        
        if (user.getId() == null)
            return new MethodResponse("მომხმარებლის სახელი ან პაროლი არასწორია");
        else
            return new MethodResponse(user);
    }
    
    
    
}
