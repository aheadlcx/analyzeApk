package qsbk.app.core.web.plugin;

import android.support.v4.app.NotificationCompat;
import android.webkit.WebView;
import com.xiaomi.mipush.sdk.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class Callback {
    public static final int STATUS_FAIL = 1;
    public static final int STATUS_SUCESS = 0;
    private String a;
    private WebView b;

    public Callback(WebView webView, String str) {
        this.b = webView;
        this.a = str;
    }

    public void sendResult(int i, String str, String str2) {
        String str3 = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(NotificationCompat.CATEGORY_ERROR, i);
            jSONObject.put("err_msg", str);
            jSONObject.put("content", str2);
            str3 = jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.b.loadUrl("javascript:Interface.onNativeResp('" + this.a + "'" + Constants.ACCEPT_TIME_SEPARATOR_SP + "'" + str3 + "'" + ")");
    }

    public void sendResult(JSONObject jSONObject, int i, String str) {
        String str2 = "";
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put(NotificationCompat.CATEGORY_ERROR, i);
            jSONObject.put("err_msg", str);
            str2 = jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.b.loadUrl("javascript:Interface.onNativeResp('" + this.a + "'" + Constants.ACCEPT_TIME_SEPARATOR_SP + "'" + str2 + "'" + ")");
    }

    public void sendResult(int i, String str, JSONObject jSONObject) {
        String str2 = "";
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(NotificationCompat.CATEGORY_ERROR, i);
            jSONObject2.put("err_msg", str);
            jSONObject2.put("content", jSONObject);
            str2 = jSONObject2.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.b.loadUrl("javascript:Interface.onNativeResp('" + this.a + "'" + Constants.ACCEPT_TIME_SEPARATOR_SP + "'" + str2 + "'" + ")");
    }
}
