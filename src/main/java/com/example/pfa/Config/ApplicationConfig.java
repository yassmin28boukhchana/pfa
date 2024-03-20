package com.example.pfa.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // bsh spring yefhem eli page hedhi mte3 configuration
@RequiredArgsConstructor
public class ApplicationConfig implements WebMvcConfigurer {
    @Bean  // bsh tgoula ki nwali hajti nik lanci rouhek khatr rzin mayetlansesh wahda
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
