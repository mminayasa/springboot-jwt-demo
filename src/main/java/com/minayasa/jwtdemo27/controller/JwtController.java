package com.minayasa.jwtdemo27.controller;

import com.minayasa.jwtdemo27.model.JwtRequest;
import com.minayasa.jwtdemo27.model.JwtResponse;
import com.minayasa.jwtdemo27.model.UserModel;
import com.minayasa.jwtdemo27.service.CustomUserDetailService;
import com.minayasa.jwtdemo27.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class JwtController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserModel> register(@RequestBody UserModel userModel) {
        UserModel userModel1 = customUserDetailService.register(userModel);
        return new ResponseEntity<>(userModel1, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) {
        System.out.println(jwtRequest.getPassword() + " " + jwtRequest.getUserName());
        UsernamePasswordAuthenticationToken usrPswrdAuth = new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(),
                jwtRequest.getPassword());

        // authenticate the user
        authenticationManager.authenticate(usrPswrdAuth);

        // check user exist or not
        UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getUserName());

        String token = jwtUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(token);
        return ResponseEntity.ok(jwtResponse);
    }

    @GetMapping("/current-user")
    public UserModel getCurrentUser(Principal principal) {
        UserDetails userDetails = this.customUserDetailService.loadUserByUsername(principal.getName());
        return (UserModel) userDetails;
    }
}
