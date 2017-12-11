package org.dashboard.main.controller;

import org.dashboard.main.data.User;
import org.dashboard.main.data.UserDAO;
import org.dashboard.main.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/register")
    public @ResponseBody User register(@PathVariable("username") String username, @PathVariable("password") String password){
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        return userDAO.save(newUser);
    }
}
