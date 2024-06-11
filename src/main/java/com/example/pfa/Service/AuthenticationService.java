package com.example.pfa.Service;

import com.example.pfa.Dto.AuthenticationRequest;
import com.example.pfa.Dto.AuthenticationResponse;
import com.example.pfa.Dto.RegisterRequest;

public interface AuthenticationService {
    public AuthenticationResponse register (RegisterRequest request) ;
    public AuthenticationResponse authenticate(AuthenticationRequest request) ;
}
