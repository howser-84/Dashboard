package org.dashboard.main.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class LoginController {

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
}
