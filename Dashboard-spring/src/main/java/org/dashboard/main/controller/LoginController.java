package org.dashboard.main.controller;

import org.dashboard.main.data.User;
import org.dashboard.main.data.UserDAO;
import org.dashboard.main.data.UserDTO;
import org.dashboard.main.service.LoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/register")
    public @ResponseBody User register(@RequestBody UserDTO userDTO){
        User newUser = convert(userDTO);
        return userDAO.save(newUser);
    }

    private User convert(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
