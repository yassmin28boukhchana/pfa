package com.example.pfa.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service 
public class JwtService {
    private static final String SECRET_KEY = "3967773e405462755b52456c2f423750432d4f3929512e7b2a2c3b506f";
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject); // the subject is the email or the username of the user
    }

    public <T> T extractClaim(String token, Function <Claims,T> claimsResolver ){
        final Claims claims = extractAllClaims(token) ;
        return claimsResolver.apply(claims);
    }

    // la methode generateToken va generer un token with userdetails only it means without extra claims
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);

    }
    public String generateToken( // map contain the extra claims that we want to add it could be authorization
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) // when this claim was created and it will help us calculate the expiration date an check if the token is still valid or not
                .setExpiration(new Date (System.currentTimeMillis() + 1000 *60 * 60* 24)) // the token will be valid 24 hours from the moment it was issued
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)    // with wich key you want to sign this token
                .compact(); // it will generate and return the token
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
       // why it takes 2 parameters bcz we want to validate if this token belongs to this userDetails
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes) ;
    }
}
