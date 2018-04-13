package qsbk.app.http;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.tencent.stat.DeviceInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.Constants;
import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;

public class HttpPayTask extends AsyncTask<Void, Void, String> {
    private static final String e = HttpPayTask.class.getSimpleName();
    protected String a;
    protected String b;
    protected HttpCallBack c;
    protected String d = null;
    private Map<String, Object> f = null;

    public HttpPayTask(String str, String str2, HttpCallBack httpCallBack) {
        this.a = str;
        this.b = str2;
        this.c = httpCallBack;
        DebugUtil.debug(e, this.a + " mUrl:" + this.b);
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
            a(c());
            if (this.f != null) {
                return HttpClient.getIntentce().post(this.b, this.f);
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
        DebugUtil.debug(e, this.a + " data:" + str);
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
        return this.f;
    }

    public void setMapParams(Map<String, Object> map) {
        this.f = map;
    }

    private void a(Map<String, String> map) {
        if (!TextUtils.isEmpty(this.b)) {
            if (!this.b.contains("?")) {
                this.b += "?";
            }
            if (!(this.b.charAt(this.b.length() - 1) == Typography.amp || this.b.charAt(this.b.length() - 1) == '?')) {
                this.b += a.b;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Object valueOf : map.keySet().toArray()) {
                String valueOf2 = String.valueOf(valueOf);
                String str = (String) map.get(valueOf2);
                if (!TextUtils.isEmpty(str)) {
                    try {
                        str = URLEncoder.encode(str, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    stringBuilder.append(valueOf2).append("=").append(str).append(a.b);
                }
            }
            if (stringBuilder.charAt(stringBuilder.length() - 1) == Typography.amp) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            this.b += stringBuilder.toString();
        }
    }

    private Map<String, String> c() {
        String country = AppUtils.getInstance().getAppContext().getResources().getConfiguration().locale.getCountry();
        int i = 2;
        if (country.equalsIgnoreCase("CN")) {
            i = 0;
        } else if (country.equalsIgnoreCase("TW") || country.equalsIgnoreCase("HK")) {
            i = 1;
        }
        Map<String, String> hashMap = new HashMap();
        hashMap.put("sdk", "7.1.2");
        hashMap.put("app", Integer.toString(Constants.SOURCE));
        hashMap.put("lan", Integer.toString(i));
        hashMap.put(DeviceInfo.TAG_VERSION, DeviceUtils.getAppVersion());
        hashMap.put(NotificationCompat.CATEGORY_SYSTEM, "android_" + DeviceUtils.getSystemVersion());
        hashMap.put("chn", AppUtils.getInstance().getChannel());
        hashMap.put("net", NetworkUtils.getInstance().getNetworkType() + "");
        hashMap.put("did", DeviceUtils.getDeviceId());
        hashMap.put("mod", DeviceUtils.getDeviceModel().replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
        hashMap.put("ts", Long.toString(System.currentTimeMillis()));
        return hashMap;
    }
}
