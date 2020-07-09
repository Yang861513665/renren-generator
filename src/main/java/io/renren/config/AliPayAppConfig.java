package io.renren.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxj
 * @date 2020-07-01 11:27
 */
@Configuration
public class AliPayAppConfig {
    @Value("${aliPay.app_id:2016102600763909}")
    private String appId;
    @Value("${aliPay.app_secret:MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwSYK428Y3MPLLCA03wVXj8/iAE7YakIOtKGECJRvana3RqjTh27GjPUXfwzR4FoNwLEArmcmbq875xKPywstmny8JGkNrORQl+io+B1Zo+sig/1h+AMYMFzJnH1dy/GkdydFONXVEbFZ5aGhIBwWRyQ5oHnARhIUMXFJWXlV7hC66MYMv6lWZjwxi55eNfg7jCq/wLnohxl5+ZJT5WnSkcVEMaTe6UKnm5p6i+X9UqyOwBQt+0KdjCdEXJkR00CckE1uXaHtFlaRl2S99F8TWMIEDNahQ5gMhJNqEYN/EdBBvXzBdpnucGwPq2sDssetbNQkUfxiaOLlu9y3bSTtBAgMBAAECggEAMhliFikXRt4ZUgwCvaKrRzIxoCx550jEszQzhYmIRfPjzx8hEkL+U9b5AaOXSL2VMw7QWdPF0QDdvGve8AHeDlcU5fBZ0qIREM3srV4md4U1+VM4Ee8kWZFKGLZ2gs4rDXUqj6BvqDdlx/EdGcMtGQZbmslzrfbMXPvrxnHet1AbhKDfR4WPoZ61QZLRt5Mc0zF5g1nJiY81iRk5Dj6BY3l4gifV+8PXiqDiuyEOHqAoGPfRaWKh56BiUdNeBpTUrAnVH+XDZGd8aeyv0iODnPuBorjNGORpFcjsqMP/Fd7ZmLcEs1fHzI+c9VoEtS+is9AacB7PEa1ilYnJgQFgAQKBgQDhkS+zRIPS1PX6sM+92l2U4FKSFu+BHGGP5hlMsix4shAd6O4qc9OlhmhO31Y3upha8VNlRSxZMduI/teCxCRr4EjpnVIMSWgm5IrHtT+D8VpNxYtFCMYCFAonzXmbhDuszDPG5kZVJOYdAbK0MEFWIDFEX42wEsNXxv4GsqNUgQKBgQDIEkD4uRGNEv6a2lLagVhNnDSDOFtmHC+4/1Df2eK8S0w9Ke+ifwYXaEDhYNB/SX8gM9Di645Zyg/kad/kSXK2wh0lfWiY0ou98xl2maEqxXuZPThAiebFN3dVREZs0GfmLecfC80FreDiuBLQHjP4Gwvt8EpV7O5uBxVUXmuGwQKBgFqyFNrgzpxEEo907mKo3X3R4IFrBWwFbFj+rOeAsI7rEb3k/XZGUjA45dHozPLlbQMMC3spoBZRDLN9vEuQ8BAMA2UjY20bSuxwcjiwcNRtg+DHcCMWzv7g7eBpJt9YAOKOzLbJujfb+UZqe18mE+88THLwdLj6Il813Mf2peOBAoGACnPpsRT6NcSoSK7fZUscsfFXTQPEwrD2TVQJhah8kBnfAdMPEg5Q4ygvO5czTXMpkSUj5Lcp2McVzXk6mVK9C49Ytny22kUlx+bLjca5BOMMchUguz55MBSVx36pr0W0J/J+AME9FO7gsPetA/KQBIZlOSk0uQiH7HeyRZYpSAECgYEAxbQZWcfr9iqAZdeJfwDTapdyNnaY1kZ2cTm5nWESiKY+5Vdj0bkkOZG26YHgh4U+s/bxcxsK0kACl+9HlMhA08b/d/3pO5VLfDe8TJD4rjbUec6g62BBOoJZ5z7iGKWFFSs+aKQgF7RMcSSzcLgxwAJ/1UjxizEk4gHzkhufYX0=}")
    private String appSecret; // 应用私钥
    @Value("${aliPay.pub_key:MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqVBDOQ9j7S3uqkJaZm9QxqWYUHONPgOS6Xr8c/C3ozeLdmXxgzJwZ+erAnPTS2NCaLB2hSpTww3neSUfztdAY1U7bwtE8mK8BUmduK37m7I7+WGBLebBPWeQg0PcN71wpH7XfvHlrdVovVzGFzrI3mQY9U9hA0HLzIr/K7ClZiO8PctPE3r4VvFMaSPZ0/QRFtVEA7f7MUUrBdH4iYZdGoY0AK7SxlcQ3t5rdoJk1wXDf/v8OlgIXj9hw05Zc5GlMQGsr6dwv0bAZnZ6MBJ/OJhjC21LClXAwDbzg4iVlRCG7o+yu1zlIyzeT4S/QLIEcDM5/G17bLEGmam5G8z5rwIDAQAB}")
    private String publicKey; // 支付宝公钥
    @Value("${aliPay.redirect_uri:http://renren.gen.com/aliPay/success}")
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

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
