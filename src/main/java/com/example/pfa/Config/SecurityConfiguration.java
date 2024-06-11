package com.example.pfa.Config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static com.example.pfa.Entities.Permission.*;
import static com.example.pfa.Entities.Role.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthentificationFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/register", "/api/v1/auth/authenticate", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/webjars/**")
                .permitAll()
                // Secure "Etablissements" endpoints
                .requestMatchers("/api/Etablissements/**").hasAnyRole(Admin.name(), USER.name())
                .requestMatchers(HttpMethod.GET, "/api/Etablissements/**").hasAnyAuthority(Admin_READ.name(), USER_READ.name())
                .requestMatchers(HttpMethod.POST, "/api/Etablissements/**").hasAnyAuthority(Admin_CREATE.name(), USER_CREATE.name())
                .requestMatchers(HttpMethod.PUT, "/api/Etablissements/**").hasAnyAuthority(Admin_UPDATE.name(), USER_UPDATE.name())
                .requestMatchers(HttpMethod.DELETE, "/api/Etablissements/**").hasAnyAuthority(Admin_DELETE.name(), USER_DELETE.name())
                // Secure "Users" endpoints
                .requestMatchers("/api/Users/**").hasAnyRole(Admin.name(), USER.name())
                .requestMatchers(HttpMethod.GET, "/api/Users/**").hasAnyAuthority(Admin_READ.name(), USER_READ.name())
                .requestMatchers(HttpMethod.POST, "/api/Users/**").hasAnyAuthority(Admin_CREATE.name(), USER_CREATE.name())
                .requestMatchers(HttpMethod.PUT, "/api/Users/**").hasAnyAuthority(Admin_UPDATE.name(), USER_UPDATE.name())
                .requestMatchers(HttpMethod.DELETE, "/api/Users/**").hasAnyAuthority(Admin_DELETE.name(), USER_DELETE.name())
                // Secure "Categories" endpoints
                .requestMatchers("/api/Categories/**").hasAnyRole(Admin.name(), USER.name())
                .requestMatchers(HttpMethod.GET, "/api/Categories/**").hasAnyAuthority(Admin_READ.name(), USER_READ.name())
                .requestMatchers(HttpMethod.POST, "/api/Categories/**").hasAnyAuthority(Admin_CREATE.name(), USER_CREATE.name())
                .requestMatchers(HttpMethod.PUT, "/api/Categories/**").hasAnyAuthority(Admin_UPDATE.name(), USER_UPDATE.name())
                .requestMatchers(HttpMethod.DELETE, "/api/Categories/**").hasAnyAuthority(Admin_DELETE.name(), USER_DELETE.name())
                // Secure "Departements" endpoints
                .requestMatchers("/api/Departements/**").hasAnyRole(Admin.name(), USER.name())
                .requestMatchers(HttpMethod.GET, "/api/Departements/**").hasAnyAuthority(Admin_READ.name(), USER_READ.name())
                .requestMatchers(HttpMethod.POST, "/api/Departements/**").hasAnyAuthority(Admin_CREATE.name(), USER_CREATE.name())
                .requestMatchers(HttpMethod.PUT, "/api/Departements/**").hasAnyAuthority(Admin_UPDATE.name(), USER_UPDATE.name())
                .requestMatchers(HttpMethod.DELETE, "/api/Departements/**").hasAnyAuthority(Admin_DELETE.name(), USER_DELETE.name())
                // Secure "Reservations" endpoints
                .requestMatchers("/api/Reservations/**").hasAnyRole(Admin.name(), USER.name())
                .requestMatchers(HttpMethod.GET, "/api/Reservations/**").hasAnyAuthority(Admin_READ.name(), USER_READ.name())
                .requestMatchers(HttpMethod.POST, "/api/Reservations/**").hasAnyAuthority(Admin_CREATE.name(), USER_CREATE.name())
                .requestMatchers(HttpMethod.PUT, "/api/Reservations/**").hasAnyAuthority(Admin_UPDATE.name(), USER_UPDATE.name())
                .requestMatchers(HttpMethod.DELETE, "/api/Reservations/**").hasAnyAuthority(Admin_DELETE.name(), USER_DELETE.name())
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}