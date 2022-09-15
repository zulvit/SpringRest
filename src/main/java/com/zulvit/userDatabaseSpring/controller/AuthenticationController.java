package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.database.UserRepository;
import com.zulvit.userDatabaseSpring.model.User;
import com.zulvit.userDatabaseSpring.security.AuthenticationRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
//    private final AuthenticationManager authenticationManager;
//    private UserRepository userRepository;
//
//    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository) {
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "loginPage";
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request){
//        try{
//            String email = request.getEmail();
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
//            Map<Object, Object> response = new HashMap<>();
//            response.put("email", request.getEmail());
//            System.out.println("Great");
//            return ResponseEntity.ok(response);
//        } catch (AuthenticationException e){
//            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
//        }
//    }


    @GetMapping("/success")
    public String getSuccessPage(){
        return "success";
    }
    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
