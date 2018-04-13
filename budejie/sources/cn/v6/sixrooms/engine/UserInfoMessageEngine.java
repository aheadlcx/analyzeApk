package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class UserInfoMessageEngine {
    protected static final String TAG = "UserInfoMessageEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void handleInfo(UserInfoBean userInfoBean, int i);
    }

    public UserInfoMessageEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getUserInfoMessage(String str, String str2, String str3, int i) {
        List arrayList = new ArrayList();
        String str4 = "";
        if (!(LoginUtils.getLoginUserBean() == null || TextUtils.isEmpty(LoginUtils.getLoginUserBean().getId()))) {
            str4 = LoginUtils.getLoginUserBean().getId();
        }
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("tuid", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("rid", str3);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, str2);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("logiuid", str4);
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair("encpass", SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        arrayList.add(basicNameValuePair5);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new bi(this, i), UrlStrs.URL_INDEX_INFO + "?padapi=message-userinfo.php", arrayList);
    }
}
