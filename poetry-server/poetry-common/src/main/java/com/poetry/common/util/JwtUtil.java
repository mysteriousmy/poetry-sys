package com.poetry.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.poetry.common.consts.enums.ErrorCode;
import com.poetry.common.exception.CustomException;

import java.util.Date;

public class JwtUtil {
    /**
     * 过期5分钟
     */
    private static final long EXPIRE_TIME =  1440 * 60 * 1000;
    /**
     * jwt密钥
     */
    private static final String SECRET = "poetry_sys";
    /**
     * 生成签名，五分钟后过期
     */
    public static String sign(String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            System.out.println(date);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    // 将 user id 保存到 token 里面
                    .withAudience(userId)
                    // 五分钟后token过期
                    .withExpiresAt(date)
                    // token 的密钥
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 根据token获取userId
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            System.out.println(token);
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.SERVER_TOKEN_DECODE_ERROR.getCode(), ErrorCode.SERVER_TOKEN_DECODE_ERROR.getSemantics());
        }
    }
    /**
     * 校验token
     * @param token
     * @return
     */
    public static boolean checkSign(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    // .withClaim("username", username)
                    .build();
            System.out.println(token);
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            throw new CustomException(ErrorCode.SERVER_TOKEN_EXPIRE.getCode(),"token过期");
        }
    }
}
