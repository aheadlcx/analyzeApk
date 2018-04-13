package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import android.os.Build;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.UrlUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.umeng.analytics.pro.x;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class CrashErrorInfoEngine {
    private String a = "coop-mobile-clientCrash.php";
    private CallBack b;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(boolean z);
    }

    public CrashErrorInfoEngine(CallBack callBack) {
        this.b = callBack;
    }

    @SuppressLint({"HandlerLeak"})
    public void sendCrashErroInfo(String str) {
        String visitorId;
        List arrayList = new ArrayList();
        String str2 = HistoryOpenHelper.COLUMN_UID;
        if (GlobleValue.getUserBean() == null) {
            visitorId = SaveUserInfoUtils.getVisitorId(V6Coop.getInstance().getContext());
        } else {
            visitorId = GlobleValue.getUserBean().getId();
        }
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair(str2, visitorId);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("errorInfo", str);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("manuFaturer", Build.MANUFACTURER);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("versionCode", String.valueOf(AppInfoUtils.getAppCode()));
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair(x.F, AppInfoUtils.getLanguage());
        BasicNameValuePair basicNameValuePair6 = new BasicNameValuePair("deviceVersion", AppInfoUtils.getDeviceVersion());
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        arrayList.add(basicNameValuePair5);
        arrayList.add(basicNameValuePair6);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new n(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, this.a), arrayList);
    }

    @SuppressLint({"HandlerLeak"})
    public void sendCrashErroInfo(String str, String str2, String str3) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, LoginUtils.getLoginUID());
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("errorInfo", str);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("manuFaturer", Build.MANUFACTURER);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("versionCode", String.valueOf(AppInfoUtils.getAppCode()));
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair(x.F, AppInfoUtils.getLanguage());
        BasicNameValuePair basicNameValuePair6 = new BasicNameValuePair("deviceVersion", AppInfoUtils.getDeviceVersion());
        BasicNameValuePair basicNameValuePair7 = new BasicNameValuePair(AppLinkConstants.TAG, str2);
        BasicNameValuePair basicNameValuePair8 = new BasicNameValuePair("data", str3);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        arrayList.add(basicNameValuePair5);
        arrayList.add(basicNameValuePair6);
        arrayList.add(basicNameValuePair7);
        arrayList.add(basicNameValuePair8);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new o(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, this.a), arrayList);
    }
}
