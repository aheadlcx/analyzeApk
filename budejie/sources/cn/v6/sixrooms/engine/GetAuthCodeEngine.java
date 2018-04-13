package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.UrlUtils;
import com.tencent.open.SocialConstants;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class GetAuthCodeEngine {
    private static final String a = GetAuthCodeEngine.class.getSimpleName();
    private CallBack b;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void verifyCodeSucceed(String str);
    }

    public GetAuthCodeEngine(CallBack callBack) {
        this.b = callBack;
    }

    public void getVerifyCode(String str, String str2) {
        List arrayList = new ArrayList();
        if (GlobleValue.getUserBean() != null) {
            arrayList.add(new BasicNameValuePair("logiuid", GlobleValue.getUserBean().getId()));
        }
        arrayList.add(new BasicNameValuePair("encpass", str));
        List arrayList2 = new ArrayList();
        arrayList2.add(new BasicNameValuePair(SocialConstants.PARAM_ACT, str2));
        arrayList2.add(new BasicNameValuePair("padapi", "auth-getAuthCode.php"));
        a(UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList2), arrayList);
    }

    public void getBundleVerifyCode(String str, String str2, String str3, String str4) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", GlobleValue.getUserBean().getId());
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", str3);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("mobile", str);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("psw", str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        List arrayList2 = new ArrayList();
        arrayList2.add(new BasicNameValuePair(SocialConstants.PARAM_ACT, str4));
        arrayList2.add(new BasicNameValuePair("padapi", "auth-getAuthCode.php"));
        a(UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList2), arrayList);
    }

    public void getFinduNameVerifyCode(String str, String str2) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("mobile", str));
        List arrayList2 = new ArrayList();
        arrayList2.add(new BasicNameValuePair(SocialConstants.PARAM_ACT, str2));
        arrayList2.add(new BasicNameValuePair("padapi", "auth-getAuthCode.php"));
        a(UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList2), arrayList);
    }

    public void getLoginVerifyCode(String str, String str2) {
        String encode = URLEncoder.encode(str);
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair(SocialConstants.PARAM_ACT, str2));
        arrayList.add(new BasicNameValuePair("ticket", encode));
        arrayList.add(new BasicNameValuePair("padapi", "auth-getAuthCode.php"));
        a(UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList).replace("|", "%7C"), null);
    }

    private void a(String str, List<NameValuePair> list) {
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new v(this), str, list);
    }
}
