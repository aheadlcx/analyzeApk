package qsbk.app.http;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;

public class EncryptHttpTask extends HttpTask {
    public EncryptHttpTask(String str, String str2, HttpCallBack httpCallBack) {
        super(str, str2, httpCallBack);
    }

    public EncryptHttpTask(String str, HttpCallBack httpCallBack) {
        super(str, str, httpCallBack);
    }

    protected String a(Void... voidArr) {
        String str = null;
        try {
            str = HttpClient.getIntentce().post(getUrl(), c());
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        }
        return str;
    }

    protected void a(String str) {
        JSONObject jSONObject;
        JSONException e;
        String str2 = null;
        if (this.c != null && !isCancelled()) {
            if (TextUtils.isEmpty(str)) {
                this.c.onFailure(this.a, "服务器无响应");
                return;
            }
            int i = 0;
            try {
                Object optString = new JSONObject(str).optString("data");
                jSONObject = new JSONObject(TextUtils.isEmpty(optString) ? "" : EncryptDecryptUtil.processDecrypt(optString));
                try {
                    i = jSONObject.optInt(NotificationCompat.CATEGORY_ERROR);
                    str2 = jSONObject.optString("err_msg");
                } catch (JSONException e2) {
                    e = e2;
                    e.printStackTrace();
                    this.c.onFailure(this.a, "数据解析错误");
                    if (i == 0) {
                        if (SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue() == jSONObject.optInt(NotificationCompat.CATEGORY_ERROR)) {
                        }
                        this.c.onFailure(i + "", str2);
                    }
                    this.c.onSuccess(this.a, jSONObject);
                }
            } catch (JSONException e3) {
                e = e3;
                jSONObject = str2;
                e.printStackTrace();
                this.c.onFailure(this.a, "数据解析错误");
                if (i == 0) {
                    this.c.onSuccess(this.a, jSONObject);
                }
                if (SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue() == jSONObject.optInt(NotificationCompat.CATEGORY_ERROR)) {
                }
                this.c.onFailure(i + "", str2);
            }
            if (i == 0) {
                this.c.onSuccess(this.a, jSONObject);
            } else if (SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue() == jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) || QsbkApp.currentUser == null) {
                this.c.onFailure(i + "", str2);
            } else {
                QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext));
            }
        }
    }

    protected String c() {
        Map mapParams = getMapParams();
        if (mapParams == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : mapParams.entrySet()) {
            try {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return EncryptDecryptUtil.processParamsEncrypt(jSONObject.toString());
    }

    public byte[] getSecret() {
        return HttpClient.getEnString().getBytes();
    }
}
