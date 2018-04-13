package com.microquation.linkedme.android.b;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.c.a;
import com.microquation.linkedme.android.util.c.g;
import com.microquation.linkedme.android.util.h;
import org.json.JSONException;
import org.json.JSONObject;

class i extends h {
    i(Context context) {
        super(context, g.APPList.a());
        h hVar = new h(context);
        JSONObject jSONObject = new JSONObject();
        try {
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
            b.a("linkedme", String.valueOf(Process.myTid()));
            a(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.c = true;
        }
    }

    i(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void a(int i, String str) {
    }

    public void a(u uVar, com.microquation.linkedme.android.a aVar) {
        this.b.v();
        this.b.F();
    }

    public boolean a(Context context) {
        return !super.b(context);
    }

    public boolean c() {
        return false;
    }

    public void d() {
    }
}
