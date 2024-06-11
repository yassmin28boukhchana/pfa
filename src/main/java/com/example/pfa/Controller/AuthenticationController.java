package com.example.pfa.Controller;

import com.example.pfa.Dto.AuthenticationRequest;
import com.example.pfa.Dto.AuthenticationResponse;
import com.example.pfa.Dto.RegisterRequest;
import com.example.pfa.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService ;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request ){
        System.out.println("Received register request: " + request);
        AuthenticationResponse response = authenticationService.register(request);
        System.out.println("Generated register response: " + response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
