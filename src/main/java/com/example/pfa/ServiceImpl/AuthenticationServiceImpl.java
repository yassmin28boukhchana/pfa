package com.example.pfa.ServiceImpl;

import ch.qos.logback.classic.Logger;
import com.example.pfa.Config.JwtService;
import com.example.pfa.Dto.AuthenticationRequest;
import com.example.pfa.Dto.AuthenticationResponse;
import com.example.pfa.Dto.RegisterRequest;
import com.example.pfa.Entities.Etablissement;
import com.example.pfa.Entities.Role;
import com.example.pfa.Entities.User;
import com.example.pfa.Repository.EtablissementRepository;
import com.example.pfa.Repository.UserRepository;
import com.example.pfa.Service.AuthenticationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Autowired
    private JwtService jwtService ;
    @Autowired
    private AuthenticationManager authenticationManager ;
    @Autowired
    private EtablissementRepository etablissementRepository ;
     Logger logger;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Etablissement etablissement = etablissementRepository.findById(request.getEtablissementId())
                .orElseThrow(() -> new NoSuchElementException("Etablissement not found with id: " + request.getEtablissementId()));
        var user = User.builder()
                .Nom(request.getNom())
                .Prenom(request.getPrenom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole()) // Assume default role USER if not set
                .Adresse((request.getAdresse()))
                .Telephone(request.getTelephone())
                .etablissement(etablissement)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}", request.getEmail(), e);
            throw new AuthenticationServiceException("Authentication failed", e);
        }
    }
}
