package qsbk.app.http;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;

public class HttpTask extends AsyncTask<Void, Void, String> {
    private static final String f = HttpTask.class.getSimpleName();
    protected String a;
    protected String b;
    protected HttpCallBack c;
    protected String d = null;
    protected Map<String, Object> e = null;

    public HttpTask(String str, String str2, HttpCallBack httpCallBack) {
        this.a = str;
        this.b = str2;
        this.c = httpCallBack;
        DebugUtil.debug(f, this.a + " mUrl:" + this.b);
    }

    public HttpTask(String str, HttpCallBack httpCallBack) {
        this.a = str;
        this.b = str;
        this.c = httpCallBack;
        DebugUtil.debug(f, this.a + " mUrl:" + this.b);
    }

    public void setStringParams(String str) {
        this.d = str;
    }

    protected void a() {
        super.a();
        if (!HttpUtils.netIsAvailable() && this.c != null) {
            this.c.onFailure(this.a, QsbkApp.getInstance().getString(R.string.network_not_connected));
            cancel(true);
        }
    }

    protected String a(Void... voidArr) {
        try {
            if (this.e != null) {
                return HttpClient.getIntentce().post(this.b, this.e);
            }
            if (this.d != null) {
                return HttpClient.getIntentce().post(this.b, this.d);
            }
            return HttpClient.getIntentce().get(this.b);
        } catch (QiushibaikeException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void a(String str) {
        super.a(str);
        DebugUtil.debug(f, this.a + " data:" + str);
        if (this.c != null) {
            if (TextUtils.isEmpty(str)) {
                this.c.onFailure(this.a, "服务器无响应");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                    this.c.onSuccess(this.a, jSONObject);
                } else if (SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue() != jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) || QsbkApp.currentUser == null) {
                    this.c.onFailure(this.a, jSONObject.getString("err_msg"));
                } else {
                    QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                this.c.onFailure(this.a, "");
            }
        }
    }

    public String getUrl() {
        return this.b;
    }

    public Map<String, Object> getMapParams() {
        return this.e;
    }

    public void setMapParams(Map<String, Object> map) {
        this.e = map;
    }
}
