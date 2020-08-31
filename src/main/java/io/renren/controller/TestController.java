package io.renren.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import io.renren.annotation.Log;
import io.renren.config.AliPayAppConfig;
import io.renren.config.WeiBoAppConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;


/**
 * @author yangxj
 * @date 2020-06-30 13:52
 */
@Controller
@Slf4j
public class TestController {
    @Autowired
    WeiBoAppConfig weiBoAppConfig;
    @Autowired
    AliPayAppConfig aliPayAppConfig;

    AlipayClient alipayClient;

    @PostConstruct
    public void init() {
        // 初始化支付宝client
        alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", aliPayAppConfig.getAppId(), aliPayAppConfig.getAppSecret(), "json", "utf-8", aliPayAppConfig.getPublicKey(), "RSA2");
    }

    @GetMapping("/ping")
    @ResponseBody
    public String ping() {
        log.error("====测试记录错误日志====");
        return "pong";
    }

    /**
     * 微博授权登录成功,回调地址
     *
     * @param code 回调的临时票据
     * @return access_token
     */
    @GetMapping("/weibo/success")
    public String weibo_success(String code, Model model) {
        HttpRequest post = HttpUtil.createPost("https://api.weibo.com/oauth2/access_token?client_id=" + weiBoAppConfig.getAppId() + "&client_secret=" + weiBoAppConfig.getAppSecret() + "&grant_type=authorization_code&redirect_uri=" + weiBoAppConfig.getRedirectUri() + "&code=" + code);
        String body = post.execute().body();
        model.addAttribute("access_token", body);
        System.out.println(body);
        return "weibo-success";
    }

    /**
     * 微博授权登录成功,回调地址
     *
     * @return access_token
     */
    @GetMapping("/aliPay/success")
    public String alipay_success(String auth_code, Model model) throws Exception {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(auth_code);
        request.setGrantType("authorization_code");
        AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
        model.addAttribute("access_token", oauthTokenResponse.getAccessToken());
        return "alipay-success";
    }

    @GetMapping("/aliPay/userInfo")
    @ResponseBody
    public String userInfo(String token) throws Exception {
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        AlipayUserInfoShareResponse response = alipayClient.execute(request, token);
        return ReflectionToStringBuilder.toString(response);
    }

    @GetMapping("fail")
    public String fail() {
        return "fail";
    }

    @GetMapping("rest/{msg}")
    @ResponseBody
    @Log
    public String rest(@PathVariable(value = "msg", required = false) String msg) {
        return msg;
    }
    @ResponseBody
    @GetMapping("rest2/{msg}")
    @Log
    public String rest2(@PathVariable(value = "msg") String msg) {
        return msg;
    }

}
