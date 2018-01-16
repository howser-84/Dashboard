package org.dashboard.main.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.dashboard.main.data.User;
import org.dashboard.main.data.UserDAO;
import org.dashboard.main.data.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;

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
    public void googleLogin(@RequestBody String idTokenString) throws Exception{
        //version that works in company network :)
        //HttpTransport transport = new NetHttpTransport.Builder().setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.144.1.10", 8080))).build();
        HttpTransport transport = new NetHttpTransport();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, new JacksonFactory())
                .setAudience(Collections.singletonList("743988849298-93ivuok2t10fh6crilmtdkagp8rhlfs0.apps.googleusercontent.com"))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            // Use or store profile information
            // ...

        } else {
            System.out.println("Invalid ID token.");
        }

    }

    private User convert(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
