package cn.yimi.util;

import cn.yimi.service.OAuthService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * token工具类
 * token生成，解析，验证
 * @author huangzs
 */
@Component
public class TokenUtil {

    private static String tokenOutTime;

    private static String secret;

    /**
     * 创建token，利用JWT创建
     * @return
     */
    public static String createToken(String empid) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap();
        map.put("alg","HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("outtime", System.currentTimeMillis()+"")
                .withClaim("empid", empid)
                .sign(Algorithm.HMAC256(secret));

        return token;
    }

    /**
     * 对token信息进行解析验证，并判断token是否属于当前用户
     * 同时判断用户是否属于系统管理员
     * @param token
     * @param empid
     * @return
     */
    public static Boolean verifyToken(String token, String empid) {
        // 判断用户是否为系统管理员
        OAuthService oAuthService = SpringBeanUtil.getBean(OAuthService.class);
        if (!oAuthService.isSystemRole(empid)){
            return false;
        }

        // 验证token的合法性
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(empid)) {
            return false;
        }

        JWTVerifier verifier;
        try {
            verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        } catch (Exception e) {
            return false;
        }

        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim>  claims =jwt.getClaims();
        Long outtime = Long.parseLong(claims.get("outtime").asString());
        String  tokenEmpid = claims.get("empid").asString();
        if (System.currentTimeMillis() - outtime <= Long.parseLong(tokenOutTime) && tokenEmpid.equals(empid) ) {
            return true;
        }
        return false;
    }

    // 为静态资源设置属性值（从配置文件中取得）
    @Value("${token.outTime}")
    private String t;

    @Value("${token.secret}")
    private String s;

    @PostConstruct
    public void init() {
        tokenOutTime = this.t;
        secret = this.s;
    }
}
