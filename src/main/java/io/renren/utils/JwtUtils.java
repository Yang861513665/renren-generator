package io.renren.utils;

import com.google.common.collect.ImmutableMap;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxj
 * @date 2020-08-05 11:22
 * <p>
 * jwt: 由 header + payload + signature 组成
 */
public class JwtUtils {

    public static final String KEY = "123456";
    public static final long EXPIRE_TIME = 1000 * 5; // 1分分钟过期

    public static String genToken() {
        return Jwts.builder()
                .setSubject(ImmutableMap.of("user_name", "USER", "authorized", "true").toString())
                .signWith(SignatureAlgorithm.HS384, KEY)
                .setExpiration(new Date(new Date().getTime() + EXPIRE_TIME))
                .compact();
    }

    public static boolean parseToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        String token = genToken();

        System.out.println(parseToken(token) ? "token验证正确" : "token验证失败");

        TimeUnit.SECONDS.sleep(3);

        System.out.println(parseToken(token) ? "token验证正确" : "token验证失败");

        TimeUnit.SECONDS.sleep(3);

        System.out.println(parseToken(token) ? "token验证正确" : "token验证失败");

    }
}
