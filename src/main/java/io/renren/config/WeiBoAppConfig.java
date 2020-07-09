package io.renren.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxj
 * @date 2020-06-30 20:35
 *
 * 微博登录 应用细腻些
 */
@Configuration
public class WeiBoAppConfig {
    @Value("${weibo.app_id}")
    private String appId;
    @Value("${weibo.app_secret}")
    private String appSecret;
    @Value("${weibo.redirect_uri}")
    private String redirectUri;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
