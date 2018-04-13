package qsbk.app.open.auth;

import android.support.v4.app.NotificationCompat;
import android.util.Pair;
import com.alipay.sdk.sys.a;
import com.sina.weibo.sdk.constant.WBConstants;
import cz.msebera.android.httpclient.HttpHeaders;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import org.json.JSONObject;
import qsbk.app.utils.LogUtil;

public class AuthApi {
    public static final String BASE_API_URL = "https://open.qiushibaike.com";
    public static final String DEFAULT_REFIRECT_URL = "https://open.qiushibaike.com/sdk/default.html";
    Http a = new Http();

    public Pair<Integer, String> requestAuthCode(RequestInfo requestInfo, String str, String str2) throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.put(WBConstants.AUTH_PARAMS_RESPONSE_TYPE, "code");
        hashMap.put("client_id", requestInfo.getClientId());
        hashMap.put("redirect_uri", requestInfo.getRedirectURI());
        hashMap.put("scope", requestInfo.getPermissions());
        HashMap hashMap2 = new HashMap();
        hashMap2.put("Qbtoken", str);
        hashMap2.put("Qbuin", str2);
        HttpURLConnection post = this.a.post("https://open.qiushibaike.com/oauth2/authorize", hashMap, hashMap2, false);
        int responseCode = post.getResponseCode();
        LogUtil.d("code:" + responseCode);
        if (responseCode == 302) {
            String headerField = post.getHeaderField(HttpHeaders.LOCATION);
            LogUtil.d("location:" + headerField);
            return new Pair(Integer.valueOf(0), getParamsFromQuery(new URL(headerField).getQuery(), "code"));
        }
        LogUtil.d("code:" + responseCode);
        headerField = this.a.readStream(post);
        LogUtil.d("output:" + headerField);
        JSONObject jSONObject = new JSONObject(headerField);
        return new Pair(Integer.valueOf(jSONObject.optInt(NotificationCompat.CATEGORY_ERROR)), jSONObject.optString("err_msg"));
    }

    public String getParamsFromQuery(String str, String str2) {
        LogUtil.d("query:" + str);
        LogUtil.d("key:" + str2);
        String[] split = str.split(a.b);
        if (split == null || split.length == 0) {
            LogUtil.d("is null");
            return null;
        }
        for (int i = 0; i < split.length; i++) {
            LogUtil.d("parts:" + split[i]);
            String[] split2 = split[i].split("=", 2);
            if (split2[0].equals(str2)) {
                return split2[1];
            }
        }
        return null;
    }

    public String requestAuthCode_test(RequestInfo requestInfo, String str, String str2) throws Exception {
        Thread.currentThread();
        Thread.sleep(4000);
        return "12345677";
    }

    public String requestAccessToken(RequestInfo requestInfo, String str, String str2) throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.put("grant_type", "code");
        hashMap.put("native_client_secret", str2);
        hashMap.put("client_id", requestInfo.getClientId());
        hashMap.put("code", str);
        hashMap.put("redirect_uri", requestInfo.getRedirectURI());
        return this.a.request(this.a.post("https://open.qiushibaike.com/oauth2/access_token", hashMap));
    }

    public String requestAccessToken_test(String str, String str2, String str3) throws Exception {
        Thread.currentThread();
        Thread.sleep(4000);
        return "{\"err\":0,\"access_token\":\"hahaha\",\"expire_in\":1426220412,\"open_id\":123456}";
    }

    public String getAppInfo(String str) throws Exception {
        return this.a.request(this.a.post("https://open.qiushibaike.com/api/app/" + str, null));
    }

    public String getAppInfo_test(String str) throws Exception {
        return "{\"app_icon\":\"http://img5.douban.com/lpic/s27680999.jpg\",\"app_name\":\"糗百登录DEMO\",\"app_signature\":\"ecfee25bda37db5b5f8b19e2d257e837\",\"err\":0}";
    }

    public String requestTestAPI() throws Exception {
        return null;
    }
}
