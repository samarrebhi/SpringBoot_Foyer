package com.ey.springboot3security.controller;

import com.ey.springboot3security.entity.Etudiant;
import com.ey.springboot3security.entity.User;
import com.ey.springboot3security.entity.Admin;
import com.ey.springboot3security.service.user.AdminService;
import com.ey.springboot3security.service.user.EtudiantService;
import com.ey.springboot3security.service.user.JwtService;
import com.ey.springboot3security.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private AdminService adminService;


    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestBody Map<String, String> token) throws JSONException {

        String[] chunks = token.get("token").split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));
        JSONObject payloadJson = new JSONObject(payload);

        String email = payloadJson.getString("email");
        String role = payloadJson.getString("role");
        if (role.equals("ROLE_ETUDIANT")) {
            log.info("here etudiant");
            Etudiant etudiant = etudiantService.findEtudiantByEmail(email);
            return ResponseEntity.ok().body(etudiant != null);
        } else {
            log.info("here admin " + email);
            Admin admin = adminService.getAdminByEmail(email);
            return ResponseEntity.ok().body(admin != null);
        }


    }

    @PostMapping("/addEtudiant")
    public String addNewUser(@RequestBody Map<String, String> data) {
        return service.addEtudiant(data);
    }

    @PostMapping("/addAdmin")
    public String addNewAdmin(@RequestBody Map<String, String> data) {
        return adminService.addAdmin(data);
    }


    @PostMapping("/generateToken")
    public ResponseEntity<Map<String, Object>> authenticateAndGetToken(@RequestBody User authRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            User user = service.getUserByEmail(authRequest.getEmail());
            Etudiant etudiant = etudiantService.findEtudiantByEmail(user.getEmail());
            if (etudiant != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("user", etudiant);
                response.put("token", jwtService.generateToken(user.getEmail(), user.getRole()));
                return ResponseEntity.ok().body(response);
            } else {
                Admin admin = adminService.getAdminByEmail(user.getEmail());
                Map<String, Object> response = new HashMap<>();
                response.put("user", admin);
                response.put("token", jwtService.generateToken(user.getEmail(), user.getRole()));
                return ResponseEntity.ok().body(response);
            }
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }




}
