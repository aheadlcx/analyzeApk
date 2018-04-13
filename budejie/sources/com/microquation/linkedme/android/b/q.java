package com.microquation.linkedme.android.b;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.microquation.linkedme.android.c.d;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.c.a;
import com.microquation.linkedme.android.util.c.g;
import com.microquation.linkedme.android.util.e;
import org.json.JSONException;
import org.json.JSONObject;

class q extends m {
    d f;

    public q(Context context, d dVar, e eVar, String str) {
        super(context, g.RegisterInstall.a());
        this.f = dVar;
        JSONObject jSONObject = new JSONObject();
        try {
            String q = eVar.q();
            String t = eVar.t();
            if (!str.equals("lkme_no_value")) {
                jSONObject.put(a.LinkClickID.a(), str);
            }
            jSONObject.putOpt(a.LKME_DEVICE_ID.a(), com.microquation.linkedme.android.a.a().f());
            jSONObject.putOpt(a.LKME_DEVICE_NAME.a(), eVar.v());
            jSONObject.putOpt(a.LKME_DEVICE_TYPE.a(), Integer.valueOf(12));
            jSONObject.putOpt(a.LKME_DEVICE_IMEI.a(), q);
            this.b.v(q);
            jSONObject.putOpt(a.LKME_DEVICE_ANDROID_ID.a(), eVar.r());
            jSONObject.putOpt(a.LKME_DEVICE_SERIAL_NUMBER.a(), eVar.s());
            jSONObject.putOpt(a.LKME_DEVICE_MAC.a(), t);
            this.b.w(t);
            jSONObject.putOpt(a.LKME_LOCAL_IP.a(), eVar.w());
            jSONObject.putOpt(a.LKME_DEVICE_FINGERPRINT.a(), eVar.u());
            if (!TextUtils.equals(eVar.h(), "lkme_no_value")) {
                jSONObject.putOpt(a.LKME_DEVICE_BRAND.a(), eVar.h());
            }
            if (!TextUtils.equals(eVar.i(), "lkme_no_value")) {
                jSONObject.putOpt(a.LKME_DEVICE_MODEL.a(), eVar.i());
            }
            if (VERSION.SDK_INT >= 11) {
                jSONObject.putOpt(a.LKME_HAS_BLUETOOTH.a(), Boolean.valueOf(eVar.e()));
            }
            jSONObject.putOpt(a.LKME_HAS_NFC.a(), Boolean.valueOf(eVar.f()));
            jSONObject.putOpt(a.LKME_HAS_SIM.a(), Boolean.valueOf(eVar.g()));
            if (!TextUtils.equals(eVar.j(), "lkme_no_value")) {
                jSONObject.putOpt(a.LKME_OS.a(), eVar.j());
            }
            jSONObject.putOpt(a.LKME_OS_VERSION_INT.a(), Integer.valueOf(eVar.k()));
            jSONObject.putOpt(a.LKME_OS_VERSION.a(), String.valueOf(eVar.l()));
            DisplayMetrics m = eVar.m();
            jSONObject.putOpt(a.LKME_SCREEN_DPI.a(), Integer.valueOf(m.densityDpi));
            jSONObject.putOpt(a.LKME_SCREEN_HEIGHT.a(), Integer.valueOf(m.heightPixels));
            jSONObject.putOpt(a.LKME_SCREEN_WIDTH.a(), Integer.valueOf(m.widthPixels));
            jSONObject.putOpt(a.LKME_IS_WIFI.a(), Boolean.valueOf(eVar.n()));
            jSONObject.putOpt(a.LKME_IS_REFERRABLE.a(), Integer.valueOf(this.b.s()));
            t = a.LKME_IS_DEBUG.a();
            boolean z = this.b.z() || this.b.x();
            jSONObject.putOpt(t, Boolean.valueOf(z));
            jSONObject.putOpt(a.LKME_LAT_VAL.a(), Boolean.valueOf(eVar.p()));
            jSONObject.putOpt(a.LKME_GoogleAdvertisingID.a(), eVar.o());
            if (!this.b.m().equals("lkme_no_value")) {
                jSONObject.putOpt(a.External_Intent_URI.a(), this.b.m());
            }
            if (!TextUtils.equals(eVar.d(), "lkme_no_value")) {
                jSONObject.putOpt(a.LKME_CARRIER.a(), eVar.d());
            }
            if (!TextUtils.equals(eVar.b(), "lkme_no_value")) {
                jSONObject.putOpt(a.LKME_APP_VERSION.a(), eVar.b());
            }
            jSONObject.putOpt(a.LKME_APP_VERSION_CODE.a(), Integer.valueOf(eVar.c()));
            jSONObject.putOpt(a.LKME_SDK_UPDATE.a(), Integer.valueOf(eVar.a(true)));
            a(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.c = true;
        }
    }

    public q(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void a(int i, String str) {
        if (this.f != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("error_message", "Trouble reaching server. Please try again in a few minutes");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f.a(jSONObject, new com.microquation.linkedme.android.f.a("Trouble initializing LinkedME. " + str, i));
        }
    }

    public void a(u uVar, com.microquation.linkedme.android.a aVar) {
        boolean z = false;
        try {
            JSONObject b = uVar.b();
            this.b.n(b.optString(a.LKME_LINK.a()));
            this.b.j("lkme_no_value");
            this.b.h("lkme_no_value");
            this.b.i("lkme_no_value");
            this.b.k("lkme_no_value");
            b bVar = this.b;
            if (b.optInt(a.LKME_CLOSE_ENABLE.a(), 0) == 1) {
                z = true;
            }
            bVar.f(z);
            this.b.g(b.optString(a.LKME_IDENTITY.a()));
            if (b.optBoolean(a.LKME_CLICKED_LINKEDME_LINK.a()) && TextUtils.equals(this.b.q(), "lkme_no_value") && this.b.s() == 1) {
                this.b.m(new JSONObject(b, new String[]{a.LKME_IS_FIRST_SESSION.a(), a.LKME_CLICKED_LINKEDME_LINK.a(), a.Params.a()}).toString());
            }
            if (b.has(a.LKME_IS_FIRST_SESSION.a()) && b.has(a.LKME_CLICKED_LINKEDME_LINK.a())) {
                this.b.l(new JSONObject(b, new String[]{a.LKME_IS_FIRST_SESSION.a(), a.LKME_CLICKED_LINKEDME_LINK.a(), a.Params.a()}).toString());
            } else {
                this.b.l("lkme_no_value");
            }
            aVar.h().a(b.optString(a.LKME_DEVICE_ID.a()));
            this.b.F();
            this.b.S();
            if (this.f != null) {
                this.f.a(aVar.d(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(d dVar) {
        if (dVar != null) {
            this.f = dVar;
        }
    }

    public boolean a() {
        return this.f != null;
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        if (this.f != null) {
            this.f.a(null, new com.microquation.linkedme.android.f.a("Trouble initializing LinkedME.", -102));
        }
        return true;
    }

    public boolean c() {
        return false;
    }

    public void d() {
        this.f = null;
    }
}
