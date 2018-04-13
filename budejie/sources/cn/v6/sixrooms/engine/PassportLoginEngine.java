package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.bean.PassportLoginAndRegisterParams;
import cn.v6.sixrooms.encrypt.MyEncrypt;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.MD5Utils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.umeng.analytics.pro.x;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"HandlerLeak"})
public class PassportLoginEngine {
    protected static final String TAG = "PassportLoginEngine";
    private PassportLoginAndRegisterParams a;
    private CallBack b;
    private String c = "";
    private String d = "";
    private String e = "";

    public interface CallBack {
        void error(int i);

        void loginSuccess(int i, String str);

        void otherPlaceLogin(String str, String str2);

        void preLogin(Boolean bool, boolean z, String str, String str2, String str3);
    }

    public PassportLoginEngine(PassportLoginAndRegisterParams passportLoginAndRegisterParams, CallBack callBack) {
        this.a = passportLoginAndRegisterParams;
        this.b = callBack;
    }

    public void perLogin(String str, boolean z) {
        try {
            NetworkServiceSingleton.createInstance().sendAsyncRequest(new aj(this, z), "http://passport.6.cn/sso/aPadPre.php?username=" + URLEncoder.encode(str, "UTF-8") + "&domain=Android&act=1&v=1", "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            this.b.error(1006);
        }
    }

    public void setGeetestData(String str, String str2, String str3) {
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    public void login() {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("pck", this.a.getPck());
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("dc", a());
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new ak(this), "http://passport.6.cn/sso/aPadLogin.php?un=" + MD5Utils.getMD5Str(this.a.getUsername()) + "&domain=Android", arrayList);
    }

    private String a() {
        LogUtils.d(TAG, "params.getCode()----" + this.a.getCode());
        int parseInt = Integer.parseInt(this.a.getCode().substring(0, 4)) % 32;
        String username = this.a.getUsername();
        String password = this.a.getPassword(true);
        String secret = MyEncrypt.instance().getSecret(this.a.getUsername(), AppInfoUtils.getUUID(), this.a.getPassword(true), this.a.getCode());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HistoryOpenHelper.COLUMN_USERNAME, username);
            jSONObject.put("password", password);
            jSONObject.put("prod", "10007");
            jSONObject.put(x.c, secret);
            jSONObject.put(IXAdRequestInfo.V, "1");
            jSONObject.put("geetest_challenge", this.c);
            jSONObject.put("geetest_validate", this.d);
            jSONObject.put("geetest_seccode", this.e);
            LogUtils.d(TAG, "encryptData---jsonObj----" + jSONObject.toString());
            return MyEncrypt.instance().encrypt(jSONObject.toString(), AppInfoUtils.getUUID(), parseInt);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
