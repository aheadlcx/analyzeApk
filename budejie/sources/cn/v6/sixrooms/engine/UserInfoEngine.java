package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.utils.GlobleValue;
import com.alipay.sdk.sys.a;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"HandlerLeak"})
public class UserInfoEngine {
    protected static final String TAG = "UserInfoEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void handleInfo(UserBean userBean);
    }

    public UserInfoEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getUserInfo(String str, String str2) {
        List arrayList = new ArrayList();
        String str3 = "";
        if (!(GlobleValue.getUserBean() == null || TextUtils.isEmpty(GlobleValue.getUserBean().getId()))) {
            str3 = GlobleValue.getUserBean().getId();
        }
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", str3);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("tuid", str2);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("encpass", str);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair(a.k, "1.3");
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new bh(this), UrlStrs.URL_INDEX_INFO + "?padapi=coop-mobile-getUserInfo.php", arrayList);
    }

    static /* synthetic */ void a(JSONObject jSONObject, UserBean userBean) throws JSONException {
        String str;
        JSONObject optJSONObject = jSONObject.optJSONObject(StatisticCodeTable.PROFILE);
        if (optJSONObject == null) {
            userBean.setAudioall("0");
            userBean.setPhotoall("0");
            userBean.setVideoall("0");
            str = "0";
        } else {
            userBean.setAudioall(optJSONObject.optString("audioall") == null ? "0" : optJSONObject.optString("audioall"));
            userBean.setPhotoall(optJSONObject.optString("photoall") == null ? "0" : optJSONObject.optString("photoall"));
            userBean.setVideoall(optJSONObject.optString("videoall") == null ? "0" : optJSONObject.optString("videoall"));
            str = optJSONObject.optString("weiboall") == null ? "0" : optJSONObject.optString("weiboall");
        }
        userBean.setWeiboall(str);
    }
}
