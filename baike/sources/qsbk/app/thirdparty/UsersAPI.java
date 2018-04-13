package qsbk.app.thirdparty;

import qsbk.app.thirdparty.net.AsyncWeiboRunner;
import qsbk.app.thirdparty.net.RequestListener;

public class UsersAPI {
    public static final String HTTPMETHOD_GET = "GET";
    public static final String HTTPMETHOD_POST = "POST";
    private ThirdOauth2AccessToken a;
    private String b;

    public UsersAPI(ThirdOauth2AccessToken thirdOauth2AccessToken) {
        this.a = thirdOauth2AccessToken;
        if (this.a != null) {
            this.b = this.a.getToken();
        }
    }

    public void getSinaUser(long j, RequestListener requestListener) {
        ThirdPartyParameters thirdPartyParameters = new ThirdPartyParameters();
        thirdPartyParameters.add("uid", j);
        thirdPartyParameters.add("access_token", this.b);
        a("https://api.weibo.com/2/users/show.json", thirdPartyParameters, "GET", requestListener);
    }

    public void postSina(String str, String str2, long j, RequestListener requestListener) {
        ThirdPartyParameters thirdPartyParameters = new ThirdPartyParameters();
        thirdPartyParameters.add("uid", j);
        thirdPartyParameters.add("access_token", this.b);
        a("https://api.weibo.com/2/users/show.json", thirdPartyParameters, "GET", requestListener);
    }

    public void getQQOpenId(RequestListener requestListener) {
        ThirdPartyParameters thirdPartyParameters = new ThirdPartyParameters();
        thirdPartyParameters.add("access_token", this.b);
        a("https://graph.qq.com/oauth2.0/me", thirdPartyParameters, "GET", requestListener);
    }

    public void getQQUser(String str, String str2, RequestListener requestListener) {
        ThirdPartyParameters thirdPartyParameters = new ThirdPartyParameters();
        thirdPartyParameters.add("openid", str);
        thirdPartyParameters.add("oauth_consumer_key", str2);
        thirdPartyParameters.add("access_token", this.b);
        a("https://graph.qq.com/user/get_user_info", thirdPartyParameters, "GET", requestListener);
    }

    protected void a(String str, ThirdPartyParameters thirdPartyParameters, String str2, RequestListener requestListener) {
        AsyncWeiboRunner.request(str, thirdPartyParameters, str2, requestListener);
    }
}
