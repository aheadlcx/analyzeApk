package qsbk.app.http;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Pair;
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

public class SimpleHttpTask extends AsyncTask<Void, Void, String> {
    public static final Integer ERROR_DOUBLE_LOGIN = Integer.valueOf(107);
    public static final int GET = 1;
    public static final int POST = 2;
    private String a;
    private SimpleCallBack b;
    private String c = null;
    private Map<String, Object> d = null;
    private boolean e = false;
    private String f = null;
    private int g = 1;

    public SimpleHttpTask(int i, String str, SimpleCallBack simpleCallBack) {
        this.g = i;
        this.a = str;
        this.b = simpleCallBack;
    }

    public SimpleHttpTask(String str, SimpleCallBack simpleCallBack) {
        this.a = str;
        this.b = simpleCallBack;
    }

    public static Pair<Integer, String> getLocalError() {
        return new Pair(Integer.valueOf(9999), HttpClient.getLocalErrorStr());
    }

    public static JSONObject syncHttp(String str, Map<String, Object> map) {
        try {
            if (HttpUtils.netIsAvailable()) {
                String post;
                if (map != null) {
                    try {
                        post = HttpClient.getIntentce().post(str, map);
                    } catch (QiushibaikeException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
                post = HttpClient.getIntentce().get(str);
                return new JSONObject(post);
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(NotificationCompat.CATEGORY_ERROR, 9999);
            jSONObject.put("err_msg", QsbkApp.getInstance().getString(R.string.network_not_connected));
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void setIsSubmit(boolean z) {
        this.e = z;
    }

    public void setFilePath(String str) {
        this.f = str;
    }

    public void setStringParams(String str) {
        this.g = 2;
        this.c = str;
    }

    public void setMapParams(Map<String, Object> map) {
        this.g = 2;
        this.d = map;
    }

    protected void a() {
        super.a();
        if (!HttpUtils.netIsAvailable() && this.b != null) {
            this.b.onFailure(9999, QsbkApp.getInstance().getString(R.string.network_not_connected));
            cancel(true);
        }
    }

    protected String a(Void... voidArr) {
        try {
            if (this.e) {
                return HttpClient.getIntentce().submit(this.a, this.d, this.f);
            }
            switch (this.g) {
                case 1:
                    return HttpClient.getIntentce().get(this.a);
                case 2:
                    if (this.d != null) {
                        return HttpClient.getIntentce().post(this.a, this.d);
                    }
                    if (this.c != null) {
                        return HttpClient.getIntentce().post(this.a, this.c);
                    }
                    return HttpClient.getIntentce().post(this.a);
                default:
                    return null;
            }
        } catch (QiushibaikeException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void a(String str) {
        super.a(str);
        DebugUtil.debug(this.a, "data:" + str);
        if (this.b != null) {
            if (TextUtils.isEmpty(str)) {
                this.b.onFailure(9999, "服务器无响应");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                int i = jSONObject.getInt(NotificationCompat.CATEGORY_ERROR);
                if (i == 0) {
                    this.b.onSuccess(jSONObject);
                } else if (i != ERROR_DOUBLE_LOGIN.intValue()) {
                    this.b.onFailure(i, jSONObject.getString("err_msg"));
                } else if (QsbkApp.currentUser != null) {
                    QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                this.b.onFailure(9999, "服务器出错");
            }
        }
    }

    public void syncRun() {
        a();
        a(a(new Void[0]));
    }

    public AsyncTask<Void, Void, String> execute() {
        return executeOnExecutor(THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
