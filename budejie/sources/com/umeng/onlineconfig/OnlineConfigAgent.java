package com.umeng.onlineconfig;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import java.util.Iterator;
import org.json.JSONObject;

public class OnlineConfigAgent {
    public static final String KEY_APPKEY = "appkey";
    public static final String KEY_CHANNEL = "channel";
    public static final String KEY_ID = "idmd5";
    public static final String KEY_PACKAGE = "package";
    public static final String KEY_SDK_VERSION = "sdk_version";
    public static final String KEY_TYPE = "type";
    public static final String KEY_VERSION_CODE = "version_code";
    private static final String a = "last_config_time";
    private static final String b = "online_config";
    private static final long c = 600000;
    private static OnlineConfigAgent e = null;
    private UmengOnlineConfigureListener d = null;
    private String f;
    private String g;

    private OnlineConfigAgent() {
    }

    public static synchronized OnlineConfigAgent getInstance() {
        OnlineConfigAgent onlineConfigAgent;
        synchronized (OnlineConfigAgent.class) {
            if (e == null) {
                e = new OnlineConfigAgent();
            }
            onlineConfigAgent = e;
        }
        return onlineConfigAgent;
    }

    public void updateOnlineConfig(Context context) {
        if (context == null) {
            try {
                OnlineConfigLog.e(a.a, "unexpected null context in updateOnlineConfig");
                return;
            } catch (Exception e) {
                OnlineConfigLog.e(a.a, "exception in updateOnlineConfig");
                return;
            }
        }
        new Thread(new OnlineConfigAgent$b(this, context.getApplicationContext())).start();
    }

    public void updateOnlineConfig(Context context, String str, String str2) {
        if (context == null) {
            try {
                OnlineConfigLog.e(a.a, "unexpected null context in updateOnlineConfig");
                return;
            } catch (Exception e) {
                OnlineConfigLog.e(a.a, "exception in updateOnlineConfig");
                return;
            }
        }
        this.f = str;
        this.g = str2;
        new Thread(new OnlineConfigAgent$b(this, context.getApplicationContext())).start();
    }

    public void setOnlineConfigListener(UmengOnlineConfigureListener umengOnlineConfigureListener) {
        this.d = umengOnlineConfigureListener;
    }

    public void removeOnlineConfigListener() {
        this.d = null;
    }

    private void a(JSONObject jSONObject) {
        if (this.d != null) {
            this.d.onDataReceived(jSONObject);
        }
    }

    private long a(Context context) {
        return d.a(context).a().getLong("oc_mdf_told", 0);
    }

    private void a(Context context, c cVar) {
        if (cVar.a != null && cVar.a.length() != 0) {
            Editor edit = d.a(context).a().edit();
            try {
                JSONObject jSONObject = cVar.a;
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    edit.putString(str, jSONObject.getString(str));
                }
                edit.commit();
                OnlineConfigLog.i(a.a, "get online setting params: " + jSONObject);
            } catch (Exception e) {
                OnlineConfigLog.d(a.a, "save online config params", e);
            }
        }
    }

    public void setDebugMode(boolean z) {
        OnlineConfigLog.LOG = z;
    }

    public String getConfigParams(Context context, String str) {
        return d.a(context).a().getString(str, "");
    }
}
