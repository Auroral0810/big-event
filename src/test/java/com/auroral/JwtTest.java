package com.auroral;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "1");
        claims.put("username", "张三");
        //生成jwt的代码
        String token = JWT.create().withClaim("user", claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 1000 * 60 * 12))//添加过期时间
                .sign(Algorithm.HMAC256("auroral"));//指定算法，配置密钥
        System.out.println(token);
    }

    @Test
    public void testParse() {
        //定义字符串，模拟用户传递过来的token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoi5" +
                "byg5LiJIn0sImV4cCI6MTczNjcyNDE2OX0.yRZF2H8mIOo2Ka2t69xsYAtSauIH1MAkLWsuPdw1coU";
        //解析token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("auroral")).build();//验证算法和密钥
        DecodedJWT decodedJWT = jwtVerifier.verify(token);//验证token,生成一个解析后的JWT对象
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));

        //如果篡改了头部和载荷部分的数据，验证会失败
        //如果密钥改了，验证会失败
        //如果token过期，验证也会失败
    }
}
