package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.UrlUtils;
import com.alipay.sdk.sys.a;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.apache.http.message.BasicNameValuePair;

public class AuthCodeEngine {
    protected static final String TAG = "AuthCodeEngine";
    private final String a = "auth-getAuthCode.php";
    private OnRequestFailedListener b;

    public interface GetAuthCodeCallBack {
        void success(String str);
    }

    public interface OnRequestFailedListener {
        void error(int i);

        void handleErrorInfo(String str, String str2);
    }

    public AuthCodeEngine(OnRequestFailedListener onRequestFailedListener) {
        this.b = onRequestFailedListener;
    }

    public void getAuthCode(String str, GetAuthCodeCallBack getAuthCodeCallBack) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("padapi", "auth-getAuthCode.php"));
        arrayList.add(new BasicNameValuePair(SocialConstants.PARAM_ACT, "user_register"));
        arrayList.add(new BasicNameValuePair("mobile", str));
        arrayList.add(new BasicNameValuePair(a.k, String.valueOf(AppInfoUtils.getAppCode())));
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new AuthCodeEngine$a(this, new c(this, getAuthCodeCallBack), this.b), UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList).replace("|", "%7C"), arrayList);
    }

    public void getAuthCode(String str, String str2, GetAuthCodeCallBack getAuthCodeCallBack) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        List arrayList2 = new ArrayList();
        arrayList2.add(new BasicNameValuePair(SocialConstants.PARAM_ACT, "exchange6coin"));
        arrayList2.add(new BasicNameValuePair("padapi", "auth-getAuthCode.php"));
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new AuthCodeEngine$a(this, new d(this, getAuthCodeCallBack), this.b), UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList2), arrayList);
    }

    public void verifyAuthCode(String str, String str2, String str3, AuthCodeEngine$VerifyAuthCodeCallBack authCodeEngine$VerifyAuthCodeCallBack) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        List arrayList2 = new ArrayList();
        arrayList2.add(new BasicNameValuePair(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, str3));
        arrayList2.add(new BasicNameValuePair("padapi", "auth-exchangeCoin.php"));
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new AuthCodeEngine$a(this, new e(this, authCodeEngine$VerifyAuthCodeCallBack), this.b), UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList2), arrayList);
    }
}
