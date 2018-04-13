package com.microquation.linkedme.android.b;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import com.microquation.linkedme.android.c.d;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.c.a;
import com.microquation.linkedme.android.util.c.g;
import com.microquation.linkedme.android.util.h;
import org.json.JSONException;
import org.json.JSONObject;

class r extends m {
    d f;

    public r(Context context, d dVar, h hVar) {
        super(context, g.RegisterOpen.a());
        this.f = dVar;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(a.LKME_DEVICE_ID.a(), com.microquation.linkedme.android.a.a().f());
            jSONObject.putOpt(a.LKME_DEVICE_TYPE.a(), Integer.valueOf(12));
            jSONObject.putOpt(a.LKME_DEVICE_IMEI.a(), hVar.q());
            jSONObject.putOpt(a.LKME_DEVICE_ANDROID_ID.a(), hVar.r());
            jSONObject.putOpt(a.LKME_DEVICE_SERIAL_NUMBER.a(), hVar.s());
            jSONObject.putOpt(a.LKME_DEVICE_MAC.a(), hVar.t());
            jSONObject.putOpt(a.LKME_LOCAL_IP.a(), hVar.w());
            jSONObject.putOpt(a.LKME_DEVICE_MODEL.a(), hVar.i());
            jSONObject.putOpt(a.LKME_DF_ID.a(), this.b.j());
            jSONObject.putOpt(a.LKME_IDENTITY_ID.a(), this.b.l());
            jSONObject.putOpt(a.LKME_IS_REFERRABLE.a(), Integer.valueOf(this.b.s()));
            if (!TextUtils.equals(hVar.b(), "lkme_no_value")) {
                jSONObject.putOpt(a.LKME_APP_VERSION.a(), hVar.b());
            }
            jSONObject.putOpt(a.LKME_APP_VERSION_CODE.a(), Integer.valueOf(hVar.c()));
            jSONObject.putOpt(a.LKME_OS_VERSION.a(), hVar.l());
            jSONObject.putOpt(a.LKME_SDK_UPDATE.a(), Integer.valueOf(hVar.a(true)));
            if (!TextUtils.equals(hVar.j(), "lkme_no_value")) {
                jSONObject.putOpt(a.LKME_OS.a(), hVar.j());
            }
            String a = a.LKME_IS_DEBUG.a();
            boolean z = this.b.z() || this.b.x();
            jSONObject.putOpt(a, Boolean.valueOf(z));
            jSONObject.putOpt(a.LKME_LAT_VAL.a(), Boolean.valueOf(hVar.p()));
            if (!this.b.m().equals("lkme_no_value")) {
                jSONObject.putOpt(a.External_Intent_URI.a(), this.b.m());
            }
            jSONObject.putOpt(a.LKME_DEVICE_UPDATE.a(), hVar.E());
            a(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.c = true;
        }
    }

    public r(String str, JSONObject jSONObject, Context context) {
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
            this.b.j("lkme_no_value");
            JSONObject b = uVar.b();
            this.b.h("lkme_no_value");
            this.b.i("lkme_no_value");
            this.b.k("lkme_no_value");
            this.b.g(b.optString(a.LKME_IDENTITY.a()));
            b bVar = this.b;
            if (b.optInt(a.LKME_CLOSE_ENABLE.a(), 0) == 1) {
                z = true;
            }
            bVar.f(z);
            if (b.optBoolean(a.LKME_CLICKED_LINKEDME_LINK.a()) && TextUtils.equals(this.b.q(), "lkme_no_value") && this.b.s() == 1) {
                this.b.m(new JSONObject(b, new String[]{a.LKME_IS_FIRST_SESSION.a(), a.LKME_CLICKED_LINKEDME_LINK.a(), a.Params.a()}).toString());
            }
            if (b.has(a.LKME_IS_FIRST_SESSION.a()) && b.has(a.LKME_CLICKED_LINKEDME_LINK.a())) {
                this.b.l(new JSONObject(b, new String[]{a.LKME_IS_FIRST_SESSION.a(), a.LKME_CLICKED_LINKEDME_LINK.a(), a.Params.a()}).toString());
            } else {
                this.b.l("lkme_no_value");
            }
            if (this.f != null) {
                this.f.a(aVar.d(), null);
            }
            if (b.optBoolean(a.LKME_IS_TEST_URL.a())) {
                Context g = com.microquation.linkedme.android.a.a().g();
                if (g != null) {
                    AlertDialog create = new Builder(g).create();
                    create.setMessage("您的SDK已正确集成！\n（该提示只在扫描测试二维码时出现）");
                    create.setTitle("温馨提示");
                    create.setButton(-3, "OK", new OnClickListener(this) {
                        final /* synthetic */ r a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    create.show();
                }
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
