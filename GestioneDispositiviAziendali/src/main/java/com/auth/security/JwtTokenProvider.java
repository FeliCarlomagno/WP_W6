
//GENERA UN TOKE:

package com.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth.exception.MyAPIException;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}") //<-- LEGGIAMO LA PASSWORD DA APPLICAZITION.PROPETIES
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // generate JWT token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        Date currentDate = new Date(); //DATA DI QUANDO FACCIAMO IL LOGIN POICHE GENERA UN TOKEN DA OGGI

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate); // CALCOLIAMO LA SCADENZA DEL TOKEN PARTENDO DALLA
        																		//DATA DI LOGIN

        String token = Jwts.builder() //<-- QUI GENERIAMO UN TOKEN CON "Jwts"
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
        return token;
    }
    
    //CRIPTA LA CHIAVE :
    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // get username from Jwt token - LO UTILIZZIAMO QUANDO DOBBIAMO VERIFICARE IL TOKEN DEL CLIENT
    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    // validate Jwt token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }
}