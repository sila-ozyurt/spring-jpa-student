package com.hediyesilaozyurt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {


    @Value("${jwt.secret:awRbp7XaHLRFsiPsUWbx0WvZEJfYP5+JDOAdNkj0m44=}")
    private String SECRET_KEY;

    //24 hours default
    @Value("${jwt.expiration:86400000}")
    private Long JWT_EXPIRATION;

    //extract username from token
    public String extractUsername(String token){

        return extractClaim(token,Claims::getSubject);
    }

    //extract claim from token
    public <T> T extractClaim(String token,Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    //control if this token belongs to user and unexpired
    //Is the username in the token the same as the currently authenticated user in the system?
    //If yes → proceed (token is likely valid)
    //If not → the token might be fake or stolen!
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //overload method without extra claims
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    //overload method
    public String generateToken(Map<String,Object> extraClaims,UserDetails userDetails){
        return buildToken(extraClaims,userDetails,JWT_EXPIRATION);
    }

    private String buildToken(Map<String,Object> extraClaims,UserDetails userDetails,long expiration){
       return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private SecretKey getSignInKey(){
        byte [] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }

    //extract all claims from token
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //checks out if token is expired
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //extracts date of expiration
    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

}
