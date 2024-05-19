package com.iuh.authenticationservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;
@Component
public class JWTUtils {
    private final SecretKey secretKey;
    //time Hết Hạn là 1 ngày
    private static final long EXPiRATION_TIME = 86400000L;

    public JWTUtils(){
        String secretString = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
        //chuẩn hóa theo UTF-8
        byte[] keyByte = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));

        this.secretKey = new SecretKeySpec(keyByte, "hmacSHA256");
    }
//tao token
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPiRATION_TIME))
                .signWith(secretKey)
                .compact();
    }
    //lamf mwois token
    public String generateRefreshToken(HashMap<String, Objects> claims,UserDetails userDetails){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPiRATION_TIME))
                .signWith(secretKey)
                .compact();
    }
    //lay username tu token
    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }
    //Hàm get userName từ token
    public <T> T extractClaims(String token, Function<Claims,T> claimsTFunction){
        return claimsTFunction.apply(
                        Jwts.parser()
                                .verifyWith(secretKey)
                                .build()
                                .parseSignedClaims(token)
                                .getPayload());
    }
    //check token còn sử dụng đucợ hay không
    public boolean isTokenvalid(String token,UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals((userDetails.getUsername())) && isExpiration(token));
    }

    //token đã hết hạn hay chưa
    public boolean isExpiration (String token){
        return extractClaims(token,Claims::getExpiration).before(new Date());
    }
}
