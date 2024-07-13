import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;

public class JwtTest {

    @Test
    public void test1(){

        HashMap<String, Object> hashMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 60);
        String token = JWT.create()
                .withHeader(hashMap)
                .withClaim("name", "张三")
                .withClaim("userid",21)
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256("sdasdasfsadasdas"));

        System.out.println("token = " + token);
    }
    @Test
    public void test2(){
        JWTVerifier sdasdasfsadasdas = JWT.require(Algorithm.HMAC256("sdasdasfsadasdas")).build();
        DecodedJWT verify = sdasdasfsadasdas.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5byg5LiJIiwiZXhwIjoxNzIwNzc2NjA5LCJ1c2VyaWQiOjIxfQ.BPrULYIdFlFLnoIT-0URwMIPE7JIuwxwlHVTrYxWrm0");

        System.out.println(verify.getClaim("name").asString());
        System.out.println(verify.getClaim("userid").asInt());
    }
}
