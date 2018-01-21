package org.dashboard.main.controller;

import org.dashboard.main.data.User;
import org.dashboard.main.data.UserDAO;
import org.dashboard.main.data.UserDTO;
import org.dashboard.main.security.GoogleValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@RestController
public class LoginController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login")
    public void login(@RequestBody UserDTO userDTO, HttpServletResponse response){
        User user = userDAO.findByUsername(userDTO.getUsername());
        if (user != null && passwordEncoder.matches(userDTO.getPassword(), user.getPassword())){
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/register")
    public void register(@RequestBody UserDTO userDTO, HttpServletResponse response){
        User user = userDAO.findByUsername(userDTO.getUsername());
        if (user == null){
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            User newUser = convert(userDTO);
            userDAO.save(newUser);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/googlelogin")
    public void googleLogin(@RequestBody String idTokenString, HttpServletResponse response){
        String userId = GoogleValidator.getUserID(idTokenString);
        if (userId != null){
            if (userDAO.findByUsername(userId) == null){
                User googleUser = new User();
                googleUser.setUsername(userId);
                googleUser.setPassword(passwordEncoder.encode(alphaNumericString(15)));
                googleUser.setGoogleUser(true);
                userDAO.save(googleUser);
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    private User convert(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private String alphaNumericString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
}
