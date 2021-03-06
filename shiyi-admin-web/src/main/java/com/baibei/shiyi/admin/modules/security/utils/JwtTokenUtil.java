package com.baibei.shiyi.admin.modules.security.utils;

import com.baibei.component.redis.util.RedisUtil;
import com.baibei.shiyi.common.tool.constants.RedisConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;
    private Clock clock = DefaultClock.INSTANCE;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private RedisUtil redisUtil;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

//    public Boolean validateToken(String token, UserDetails userDetails) {
//        JwtUser user = (JwtUser) userDetails;
//        final Date created = getIssuedAtDateFromToken(token);
////        final Date expiration = getExpirationDateFromToken(token);
////        如果token存在，且token创建日期 > 最后修改密码的日期 则代表token有效
//        return (!isTokenExpired(token)
//                && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())
//        );
//    }

    private Date calculateExpirationDate(Date createdDate) {
        Date dateTime = new Date(createdDate.getTime() + expiration);
        log.info("当前jwt生成的token过期时间为{}", dateTime);
        return dateTime;
    }

    /**
     * 把token存进redis中
     * 并设置多少秒过期
     *
     * @return
     */
    public Boolean redisTokenSave(String userName, String token) {
        Long seconds = (expiration / 1000);
        userName = MessageFormat.format(RedisConstant.PREFIX_SHIYI_ADMIN_USER_TOKEN, userName);
        redisUtil.set(userName, token, seconds.intValue());
        log.info("重新生成当前保存的token时间为{}", seconds);
        return Boolean.TRUE;
    }

    /**
     * 获取用户对应的token
     *
     * @param
     * @return
     */
    public String getUserNameToken(String userName) {
        userName = MessageFormat.format(RedisConstant.PREFIX_SHIYI_ADMIN_USER_TOKEN, userName);
        return redisUtil.get(userName);
    }

    /**
     * 清除用户的token
     *
     * @param
     */
    public void redisTokenClear(String userName) {
        userName = MessageFormat.format(RedisConstant.PREFIX_SHIYI_ADMIN_USER_TOKEN, userName);
        redisUtil.delete(userName);
    }
}

