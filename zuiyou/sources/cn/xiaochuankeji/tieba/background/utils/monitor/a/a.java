package cn.xiaochuankeji.tieba.background.utils.monitor.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuan.push.b.b;
import com.meizu.cloud.pushsdk.notification.model.TimeDisplaySetting;
import com.tencent.tauth.AuthActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static a a;
    private d b;

    private a() {
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public void a(String str, long j, long j2, long j3) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        Object string = b().getString("pending_list", null);
        if (TextUtils.isEmpty(string)) {
            jSONArray = new JSONArray();
        } else {
            try {
                jSONArray = new JSONArray(string);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONArray = new JSONArray();
            }
        }
        JSONArray jSONArray3 = new JSONArray();
        jSONArray3.put(str);
        jSONArray3.put(j);
        jSONArray3.put(j2);
        jSONArray3.put(j3);
        jSONArray3.put(String.valueOf(cn.xiaochuankeji.tieba.background.h.d.a().j()));
        jSONArray3.put(String.valueOf(cn.xiaochuankeji.tieba.background.h.d.a().k()));
        jSONArray3.put(String.valueOf(cn.xiaochuankeji.tieba.background.h.d.a().l()));
        cn.xiaochuankeji.tieba.background.h.d.a().m();
        Context appContext = BaseApplication.getAppContext();
        jSONArray3.put(b.a(appContext) ? 1 : 0);
        string = (cn.xiaochuan.a.a.b() || cn.xiaochuankeji.badge.b.b(appContext)) ? 1 : null;
        if (string != null) {
            int h = cn.xiaochuankeji.tieba.background.h.d.a().h();
            if (h > 0) {
                jSONArray3.put(h);
            } else {
                jSONArray3.put(0);
            }
        } else {
            jSONArray3.put(-1);
        }
        jSONArray.put(jSONArray3);
        Object string2 = b().getString("uploading_list", null);
        Object obj = null;
        if (TextUtils.isEmpty(string2)) {
            jSONArray2 = new JSONArray();
        } else {
            try {
                jSONArray2 = new JSONArray(string2);
            } catch (JSONException e2) {
                e2.printStackTrace();
                jSONArray2 = new JSONArray();
            }
        }
        if (jSONArray.length() > 100 || jSONArray2.length() == 0) {
            JSONArray jSONArray4 = new JSONArray();
            int max = Math.max(0, jSONArray.length() - 100);
            int length = jSONArray.length() - max;
            int i = 0;
            if (jSONArray2.length() == 0) {
                i = Math.min(length, 10);
                obj = 1;
                for (int i2 = max; i2 < max + i; i2++) {
                    Object obj2 = null;
                    try {
                        obj2 = jSONArray.get(i2);
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                    if (obj2 != null) {
                        jSONArray2.put(obj2);
                    }
                }
            }
            for (int i3 = i + max; i3 < max + length; i3++) {
                string2 = null;
                try {
                    string2 = jSONArray.get(i3);
                } catch (JSONException e4) {
                    e4.printStackTrace();
                }
                if (string2 != null) {
                    jSONArray4.put(string2);
                }
            }
            jSONArray = jSONArray4;
        }
        Editor edit = b().edit();
        edit.putString("pending_list", jSONArray.toString());
        if (obj != null) {
            edit.putString("uploading_list", jSONArray2.toString());
        }
        edit.apply();
        a(jSONArray2);
    }

    private void a(JSONArray jSONArray) {
        if (this.b == null) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONArray jSONArray3 = jSONArray.getJSONArray(i);
                    String string = jSONArray3.getString(0);
                    long j = jSONArray3.getLong(1);
                    long j2 = jSONArray3.getLong(2);
                    long j3 = jSONArray3.getLong(3);
                    Object string2 = jSONArray3.getString(4);
                    String string3 = jSONArray3.getString(5);
                    CharSequence string4 = jSONArray3.getString(6);
                    int i2 = jSONArray3.getInt(7);
                    int i3 = jSONArray3.getInt(8);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(AuthActivity.ACTION_KEY, string);
                    jSONObject2.put("otype", "appuse");
                    if (!(TextUtils.isEmpty(string2) || "null".equalsIgnoreCase(string2))) {
                        jSONObject2.put("src", string2);
                    }
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put(TimeDisplaySetting.START_SHOW_TIME, j);
                    jSONObject3.put("et", j2);
                    jSONObject3.put("dur", j3);
                    jSONObject3.put("pushset", i2);
                    if (i3 != 0) {
                        jSONObject3.put("badge", i3);
                    }
                    if (!(TextUtils.isEmpty(string3) || "null".equalsIgnoreCase(string3))) {
                        jSONObject3.put("from", string3);
                    }
                    if (!(TextUtils.isEmpty(string4) || "null".equalsIgnoreCase(string3))) {
                        jSONObject3.put("from_id", string4);
                    }
                    jSONObject2.put("data", jSONObject3);
                    jSONArray2.put(jSONObject2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            try {
                jSONObject.put("list", jSONArray2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
            this.b = new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/stat/action"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onTaskFinish(d dVar) {
                    this.a.b = null;
                    if (dVar.c.a) {
                        this.a.b().edit().putString("uploading_list", null).apply();
                    }
                }
            });
            this.b.b();
        }
    }

    private SharedPreferences b() {
        return BaseApplication.getAppContext().getSharedPreferences("app_duration", 0);
    }
}
