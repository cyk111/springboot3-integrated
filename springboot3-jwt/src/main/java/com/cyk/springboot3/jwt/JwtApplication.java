package com.cyk.springboot3.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyk
 * @date 2023/10/30 22:41
 */
@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class,args);
    }

    /***
     * 测试方法
     * # http://localhost:8080/login?userName=zhangsan&password=123456
     * 生成jwt 字符串
     * 验证
     * http://localhost:8080/secure/getUserInfo
     *  header 添加 jwt信息
     *  key: Authorization
     *  value: jwt 字符串
     *
     *  Session cookie JWT token  SSO  OAuth2.0
     *
     *  cookie-Session 机制
     *
     *   原理：
 *          其实就是server自己生成一个sessionId给client。client每次请求都带上，
     *      server在自己的内存里找有没有该sessionId对应的HttpSession对象，没有，
 *          就返回认证失败，如果有，就拿到用户状态信息，进行下一步操作
     *
     *   缺点：
     *      如果用户量在一段时间内非常大， 那给服务器造成的内存压力非常大，占用非常大的内存。
     *      如果做了负载均衡（多个机器），那么请求可能到了一台没有sessionID的机器，造成session丢失
     *      存在安全问题，CSRF跨站伪造请求攻击，cookie可能被截获
     *
     *  ---------------------------
     *
 *      token机制
     *
     *   原理：
     *      是在用户输入账号密码校验通过的时候，服务器下发一个token，以后每次浏览器请求都带上token，
     *      服务器进行校验，成功了，就返回对应的数据信息
     *
     *      access token
     *      refresh token
     *
     *      Access Token 的有效期比较短，当 Acesss Token 由于过期而失效时，使用 Refresh Token 就可以获取到新的 Token，
     *      如果 Refresh Token 也失效了，用户就只能重新登录了
     *
     *      Refresh Token 及过期时间是存储在服务器的数据库中，只有在申请新的 Acesss Token 时才会验证，
     *      不会对业务接口响应时间造成影响，也不需要向 Session 一样一直保持在内存中以应对大量的请求
     *
     *      token的组成：
     *           header密文，payload密文，签名;header、 payload使用base64分别加密，再合起来，使用密钥和HS256加密生成签名，放后面
     *           token = base64(header).base64(payload).HS256(密钥,base64(header).base64(payload))
     *
     *    jwt机制：
     *
     *    优点：
     *      1.可扩展性好，不像session需要多机数据共享（内存或者redis）
     *      2.无状态
     *    缺点：
     *      1. 不安全，base64编码大家都知道
     *      2. 性能：如果payload的很大（可能有信息交换），经过base64编码后会非常大，cookie的4k内存可能存不下，
     *         就得放storage里，每次请求的时候都放header里，导致header比body还大，所以可能要比session的开销还大
     *      3.一次性,无法废弃：我后来想在jwt的payload里加个字段或改一下值，所以签发了新的jwt，但是旧的还没过期，
     *        使用旧的jwt获取的是过期的payload。所以需要额外加个过期jwt的黑名单，避免使用过时的
     *
     *     token 和  jwt 区别
     *
     *     1. Token：服务端验证客户端发送过来的 Token 时，还需要查询数据库获取用户信息，然后验证 Token 是否有效
     *     2. JWT：用户的信息第二部分 Payload加密后和第三部分签名里都有，存储于客户端，
     *          服务端只需要使用密钥解密进行校验（校验也是 JWT 自己实现的）即可，不需要查询或者减少查询数据库，
     *          因为 JWT 自包含了用户信息和加密的数据。就是服务端校验通过后，选择直接使用请求端给到的用户信息进行数据返回
     *
     *
     *     SSO(Single Sign On)单点登录机制：
     *
     *     意思就是把多于一个产品的用户登录逻辑抽离出来，达到只输入一次用户名和密码就能同时登录多个产品的效果
     *
     *     实现：
 *              1.同域
     *          2.不同域
     *              CAS center Authentication service 中心授权服务
     *              SSO 是一种设计，CAS是实现它的其中方式之一，如果是同域名或者同父域可以使用session | token进行认证，
     *              但是跨域的情况下，多个产品不同域名，需要CAS实现认证
     *
     *
     *     OAuth2.0
     *
     *     OIDC  Ids4
     *
     */
}
