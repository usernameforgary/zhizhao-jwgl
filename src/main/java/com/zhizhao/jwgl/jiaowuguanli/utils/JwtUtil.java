package com.zhizhao.jwgl.jiaowuguanli.utils;

import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${jiaowugaunli.security.jwt.key}")
    private String SECRET_KEY;

    /**
     * token中取得用户名（实际是手机号）
     * @param token
     * @return
     */
    public String extractUsername(String token) {
       return extractClaim(token, Claims::getSubject);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     *
     * @return
     */
    public String generateToken(ZhangHao zhangHao) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", zhangHao.getId());
        return createToken(claims, zhangHao.getShouJi());
    }

    //TODO token过期时间有待改进，存到redis里
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 365 * 100))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
