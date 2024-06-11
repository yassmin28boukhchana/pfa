package com.example.pfa.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthentificationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService; // it s an interface available within the spring framework

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization"); // the header that contains jwt token
        final String jwt;
        final String userEmail; //email
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt); //todo extract the userEmail from jwt token
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // when the authentication is null it means that the user is not yes authenticated it means the user is not connected yet
          // we get our user details from database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
          // we check if the user is valid or not
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // if it is valid we create the following object
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // we don't t have credentials
                        userDetails.getAuthorities()
                );
                // we enforce this authentication token with the details of our request
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
               // we update the authentication token
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // we should call our filter
        filterChain.doFilter(request,response);
    }
}