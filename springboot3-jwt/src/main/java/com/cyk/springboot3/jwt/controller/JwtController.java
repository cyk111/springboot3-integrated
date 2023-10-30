package com.cyk.springboot3.jwt.controller;

import com.cyk.springboot3.jwt.entity.User;
import com.cyk.springboot3.jwt.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cyk
 * @date 2023/10/30 22:42
 */
@Slf4j
@RestController
public class JwtController {

    /**
     *  session-cookie 验证机制 不是是移动端 所以发展为token 验证信息
     *
     *  token验证信息，每次都要花费时间校验信息浪费时间 jwt
     *
     *  jwt  包含了 访问的url 压缩，但是会有时效性的问题
     *
     *  json web token
     * 定义了一种简洁的，自包含的方法用于通信双方之间以 JSON 对象的形式安全的传递信息。
     * 因为数字签名的存在，这些信息是可信的，JWT 可以使用 HMAC 算法或者是 RSA 的公私秘钥对进行签名
     *
     * JWT 请求流程:
     *   1.用户使用账号和密码发起 POST 请求；
     *   2.服务器使用私钥创建一个 JWT；
     *   3.服务器返回这个 JWT 给浏览器；
     *   4.浏览器将该 JWT 串在请求头中像服务器发送请求；
     *   5.服务器验证该 JWT；
     *   6.返回响应的资源给浏览器
     */


    static Map<Integer, User> userMap = new HashMap<>();

    static {
        //模拟数据库
        User user1 = new User(1,"zhangsan","123456");
        userMap.put(1, user1);
        User user2 = new User(2,"lisi","123123");
        userMap.put(2, user2);
    }

    /**
     * 模拟用户 登录
     */
    @RequestMapping("/login")
    public String login(User user) {
        for (User dbUser : userMap.values()) {
            if (dbUser.getUserName().equals(user.getUserName()) && dbUser.getPassword().equals(user.getPassword())) {
                log.info("登录成功！生成token！");
                String token = JwtUtil.createToken(dbUser);
                return token;
            }
        }
        return "";
    }

}
