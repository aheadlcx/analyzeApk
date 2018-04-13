package com.microquation.linkedme.android.b;

import android.content.Context;
import android.text.TextUtils;
import com.microquation.linkedme.android.util.c.a;
import com.microquation.linkedme.android.util.c.c;
import com.microquation.linkedme.android.util.c.g;
import com.microquation.linkedme.android.util.h;
import org.json.JSONException;
import org.json.JSONObject;

public class n extends h {
    private String f;

    public n(JSONObject jSONObject, Context context) {
        super(g.LC.a(), jSONObject, context);
        h hVar = new h(context);
        try {
            this.f = jSONObject.optString(c.LC_DATA.a(), "");
            jSONObject.putOpt(a.LKME_DEVICE_ID.a(), com.microquation.linkedme.android.a.a().f());
            jSONObject.putOpt(a.LKME_ANDROID_ID.a(), hVar.r());
            jSONObject.putOpt(a.LKME_DEVICE_IMEI.a(), hVar.q());
            if (!TextUtils.equals(hVar.j(), "lkme_no_value")) {
                jSONObject.putOpt(a.LKME_OS.a(), hVar.j());
            }
            jSONObject.putOpt(a.LKME_OS_VERSION_INT.a(), Integer.valueOf(hVar.k()));
            jSONObject.putOpt(a.LKME_OS_VERSION.a(), String.valueOf(hVar.l()));
            jSONObject.putOpt(a.LKME_IDENTITY_ID.a(), this.b.l());
            jSONObject.put(a.DeviceFingerprintID.a(), this.b.j());
            jSONObject.put(a.LKME_TIMESTAMP.a(), System.currentTimeMillis());
            a(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.c = true;
        }
    }

    public void a(int i, String str) {
        this.b.a(this.f, false);
    }

    public void a(u uVar, com.microquation.linkedme.android.a aVar) {
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        this.b.a(this.f, false);
        return true;
    }

    public boolean c() {
        return false;
    }

    public void d() {
    }
}
