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
public class CaptchaEngine {
    protected static final String TAG = ChangzhanStatusBean.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(CaptchaBean captchaBean);
    }

    public CaptchaEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void initCaptcha(String str, String str2) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("padapi", "event-match-captcha.php"));
        String url = UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList);
        List arrayList2 = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", str2);
        arrayList2.add(basicNameValuePair);
        arrayList2.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new a(this), url, arrayList2);
    }
}
