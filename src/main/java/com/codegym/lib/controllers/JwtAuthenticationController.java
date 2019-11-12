package com.codegym.lib.controllers;

import com.codegym.lib.models.User;
import com.codegym.lib.models.jwt.JwtRequest;
import com.codegym.lib.models.jwt.JwtResponse;
import com.codegym.lib.services.JwtUserDetailsService;
import com.codegym.lib.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signIn(@RequestBody JwtRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }
}
