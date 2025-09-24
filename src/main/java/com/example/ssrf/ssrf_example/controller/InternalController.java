package com.example.ssrf.ssrf_example.controller;

import com.example.ssrf.ssrf_example.model.UserEntity;
import com.example.ssrf.ssrf_example.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/internal")
public class InternalController {
    private final UserRepository userRepository;


    public InternalController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<?> allUsers(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        String serverIp = request.getLocalAddr();

        if (!clientIp.equals(serverIp) && !"127.0.0.1".equals(clientIp) && !"0:0:0:0:0:0:0:1".equals(clientIp)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Forbidden: only local requests are allowed");
        }

        return ResponseEntity.ok(userRepository.findAll());
    }

}