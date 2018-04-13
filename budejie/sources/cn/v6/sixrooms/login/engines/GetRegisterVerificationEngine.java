package cn.v6.sixrooms.login.engines;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.login.interfaces.GetVerifyCodeCallback;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.UrlUtils;
import com.alipay.sdk.sys.a;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class GetRegisterVerificationEngine {
    private GetVerifyCodeCallback a;
    private String b = "";
    private String c = "";
    private String d = "";

    public GetRegisterVerificationEngine(GetVerifyCodeCallback getVerifyCodeCallback) {
        this.a = getVerifyCodeCallback;
    }

    public GetRegisterVerificationEngine setChallenge(String str) {
        this.b = str;
        return this;
    }

    public GetRegisterVerificationEngine setValidate(String str) {
        this.c = str;
        return this;
    }

    public GetRegisterVerificationEngine setSeccode(String str) {
        this.d = str;
        return this;
    }

    public void getAuthCode(String str) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair(SocialConstants.PARAM_ACT, "user_register"));
        arrayList.add(new BasicNameValuePair("mobile", str));
        arrayList.add(new BasicNameValuePair("prod", "10007"));
        arrayList.add(new BasicNameValuePair("padapi", "auth-getAuthCode.php"));
        arrayList.add(new BasicNameValuePair("geetest_challenge", this.b));
        arrayList.add(new BasicNameValuePair("geetest_validate", this.c));
        arrayList.add(new BasicNameValuePair("geetest_seccode", this.d));
        arrayList.add(new BasicNameValuePair(a.k, String.valueOf(AppInfoUtils.getAppCodeI())));
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new a(this), UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList).replace("|", "%7C"), "");
    }
}
