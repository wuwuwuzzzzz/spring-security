package com.example.springsecurity.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author wxz
 * @date 16:02 2023/2/16
 */
public class JwtUtil {

    /**
     * 过期时间
     */
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    /**
     * 密钥明文
     */
    public static final String JWT_KEY = "wxz";

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成jtw
     *
     * @param subject token要存放的数据(json格式)
     * @return java.lang.String
     * @author wxz
     * @date 16:07 2023/2/16
     */
    public static String createJwt(String subject) {
        return getJwtBuilder(subject, null, getUUID()).compact();
    }

    /**
     * 生成jtw
     *
     * @param subject   token要存放的数据(json格式)
     * @param ttlMillis token过期时间
     * @return java.lang.String
     * @author wxz
     * @date 16:06 2023/2/16
     */
    public static String createJwt(String subject, Long ttlMillis) {
        return getJwtBuilder(subject, ttlMillis, getUUID()).compact();
    }

    public static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        SecretKey secretKey = generalKey();

        long nowMillis = System.currentTimeMillis();

        Date now = new Date(nowMillis);

        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        long expMillis = nowMillis + ttlMillis;

        Date expDate = new Date(expMillis);

        return Jwts.builder()
                   .setId(uuid)
                   .setSubject(subject)
                   .setIssuer("wxz")
                   .setIssuedAt(now)
                   .signWith(signatureAlgorithm, secretKey)
                   .setExpiration(expDate);
    }

    /**
     * 创建token
     *
     * @param id        UUID
     * @param subject   token要存放的数据(json格式)
     * @param ttlMillis 过期时间
     * @return java.lang.String
     * @author wxz
     * @date 16:15 2023/2/16
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        return getJwtBuilder(subject, ttlMillis, id).compact();
    }

    /**
     * 生成加密后的密钥 secretKey
     *
     * @return javax.crypto.SecretKey
     * @author wxz
     * @date 16:25 2023/2/16
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析
     *
     * @param jwt token
     * @return io.jsonwebtoken.Claims
     * @author wxz
     * @date 16:27 2023/2/16
     */
    public static Claims parseJWT(String jwt) throws Exception {
        return Jwts.parser().setSigningKey(generalKey()).parseClaimsJwt(jwt).getBody();
    }
}
