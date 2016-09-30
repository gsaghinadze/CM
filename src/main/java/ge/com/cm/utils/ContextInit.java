/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.cm.utils;

import ge.com.cm.entities.User;
import ge.com.ws.UserService;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giorgi
 */
@Component
public class ContextInit {

    @Autowired
    private UserService userService;

    public ContextInit() {
        System.out.println("init ContextInit");
    }

    @PostConstruct
    public void contextInitialized() {

        try {

            User admin = new User();
            admin.setUserName("admin");
            admin.setPassword("admin123");
            admin.setRole("admin");
            userService.editUser(admin);
        } catch (Exception e) {
            //e.printStackTrace();
        }

        try {
            User user = new User();
            user.setUserName("user");
            user.setPassword("user123");
            user.setRole("user");
            userService.editUser(user);
        } catch (Exception e) {
            //e.printStackTrace();
        }

    }

    @PreDestroy
    public void contextDestroyed() {

    }
}
