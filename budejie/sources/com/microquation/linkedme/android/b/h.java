package com.microquation.linkedme.android.b;

import android.content.Context;
import com.microquation.linkedme.android.a;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.c;
import com.microquation.linkedme.android.util.c.g;
import com.microquation.linkedme.android.util.e;
import com.umeng.update.UpdateConfig;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class h {
    protected String a;
    protected b b;
    protected boolean c = false;
    long d = 0;
    boolean e = false;
    private JSONObject f;

    public h(Context context, String str) {
        this.a = str;
        this.b = b.a(context);
        this.f = new JSONObject();
    }

    protected h(String str, JSONObject jSONObject, Context context) {
        this.a = str;
        this.f = jSONObject;
        this.b = b.a(context);
    }

    private static h a(String str, JSONObject jSONObject, Context context) {
        return str.equalsIgnoreCase(g.GetURL.a()) ? new j(str, jSONObject, context) : str.equalsIgnoreCase(g.RegisterClose.a()) ? new p(str, jSONObject, context) : str.equalsIgnoreCase(g.RegisterInstall.a()) ? new q(str, jSONObject, context) : str.equalsIgnoreCase(g.RegisterOpen.a()) ? new r(str, jSONObject, context) : str.equalsIgnoreCase(g.APPList.a()) ? new i(str, jSONObject, context) : str.equalsIgnoreCase(g.LC.a()) ? new n(jSONObject, context) : str.equalsIgnoreCase(g.GAL.a()) ? new l(context, str) : (str.equalsIgnoreCase(g.TrackingCustomPoint.a()) || str.equalsIgnoreCase(g.TrackingLogin.a()) || str.equalsIgnoreCase(g.TrackingPay.a()) || str.equalsIgnoreCase(g.TrackingRegister.a())) ? new s(str, jSONObject, context) : null;
    }

    public static h a(JSONObject jSONObject, Context context) {
        JSONObject jSONObject2;
        String string;
        String str = "";
        try {
            jSONObject2 = jSONObject.has("REQ_POST") ? jSONObject.getJSONObject("REQ_POST") : null;
        } catch (JSONException e) {
            jSONObject2 = null;
        }
        try {
            string = jSONObject.has("REQ_POST_PATH") ? jSONObject.getString("REQ_POST_PATH") : str;
        } catch (JSONException e2) {
            string = str;
        }
        return (string == null || string.length() <= 0) ? null : a(string, jSONObject2, context);
    }

    public JSONObject a(ConcurrentHashMap<String, String> concurrentHashMap) {
        return this.f;
    }

    public abstract void a(int i, String str);

    public abstract void a(u uVar, a aVar);

    public void a(e eVar) {
        try {
            String o = eVar.o();
            boolean p = eVar.p();
            if (!(this.e || o == null || h() == null)) {
                h().put(c.a.GoogleAdvertisingID.a(), o);
            }
            if (!this.e && h() != null) {
                h().put(c.a.LATVal.a(), p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a(JSONObject jSONObject) {
        this.f = jSONObject;
    }

    public abstract boolean a(Context context);

    protected boolean b(Context context) {
        int i = -1;
        try {
            i = context.checkCallingOrSelfPermission(UpdateConfig.h);
        } catch (Exception e) {
        }
        return i == 0;
    }

    public abstract boolean c();

    public abstract void d();

    public boolean e() {
        return false;
    }

    public final String f() {
        return this.a;
    }

    public String g() {
        return this.b.b() + this.a;
    }

    public JSONObject h() {
        return this.f;
    }

    public boolean i() {
        return false;
    }

    public JSONObject j() {
        return this.f;
    }

    public JSONObject k() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("REQ_POST", this.f);
            jSONObject.put("REQ_POST_PATH", this.a);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    public void l() {
        this.d = System.currentTimeMillis();
    }

    public long m() {
        return this.d > 0 ? System.currentTimeMillis() - this.d : 0;
    }

    public boolean n() {
        return this.c;
    }
}
