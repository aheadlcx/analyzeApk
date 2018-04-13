package cn.v6.sixrooms.room.verify;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.bean.ChangzhanStatusBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class VerifyEngine {
    protected static final String TAG = ChangzhanStatusBean.class.getSimpleName();
    private VerifyEngine$CallBack a;

    public VerifyEngine(VerifyEngine$CallBack verifyEngine$CallBack) {
        this.a = verifyEngine$CallBack;
    }

    public void postCaptcha(String str, String str2, String str3, String str4, String str5, String str6) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("padapi", "event-match-verify.php"));
        String url = UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList);
        List arrayList2 = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("geetest_challenge", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("geetest_validate", str2);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("geetest_seccode", str3);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("eid", str4);
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair("logiuid", str5);
        BasicNameValuePair basicNameValuePair6 = new BasicNameValuePair("encpass", str6);
        arrayList2.add(basicNameValuePair5);
        arrayList2.add(basicNameValuePair6);
        arrayList2.add(basicNameValuePair);
        arrayList2.add(basicNameValuePair2);
        arrayList2.add(basicNameValuePair3);
        arrayList2.add(basicNameValuePair4);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new b(this), url, arrayList2);
    }
}
