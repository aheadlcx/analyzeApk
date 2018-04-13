package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.UrlUtils;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.message.BasicNameValuePair;

public class GetAuthCodeForResetPwdEngine {
    protected static final String TAG = GetAuthCodeForResetPwdEngine.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void verifyCodeSucceed(Map<String, String> map);
    }

    public GetAuthCodeForResetPwdEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getFindPswVerifyCode(String str, String str2, String str3) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("mobile", str));
        List arrayList2 = new ArrayList();
        arrayList2.add(new BasicNameValuePair(SocialConstants.PARAM_ACT, str3));
        arrayList2.add(new BasicNameValuePair("uname", str2));
        arrayList2.add(new BasicNameValuePair("padapi", "auth-getAuthCode.php"));
        String replace = UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList2).replace("|", "%7C");
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new w(this), replace, arrayList);
        LogUtils.i(TAG, "url=" + replace + "  list=" + arrayList);
    }
}
