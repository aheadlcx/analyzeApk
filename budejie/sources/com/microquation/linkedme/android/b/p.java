package com.microquation.linkedme.android.b;

import android.content.Context;
import android.util.Log;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.microquation.linkedme.android.a;
import com.microquation.linkedme.android.c.c;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.c.g;
import com.microquation.linkedme.android.util.e;
import org.json.JSONException;
import org.json.JSONObject;

class p extends h {
    private c f;

    public p(Context context, c cVar) {
        super(context, g.RegisterClose.a());
        this.f = cVar;
        JSONObject jSONObject = new JSONObject();
        try {
            String X = this.b.X();
            this.b.W();
            if (!this.b.Z()) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("wc", a.a().h().B());
                jSONObject2.put("wl", a.a().h().C());
                jSONObject2.put(IXAdRequestInfo.COST_NAME, a.a().h().b(context));
                jSONObject2.put(IXAdRequestInfo.WIDTH, a.a().h().a(context));
                jSONObject2.put("l", X);
                jSONObject2.put("p", a.a().h().D());
                jSONObject2.put("s", a.a().h().z());
                b.a("close_session_json===" + jSONObject2.toString());
                jSONObject.putOpt(com.microquation.linkedme.android.util.c.a.LKME_CLOSE_SESSION.a(), com.microquation.linkedme.android.util.a.a(jSONObject2.toString(), "linkedme2017nble"));
            }
            jSONObject.putOpt(com.microquation.linkedme.android.util.c.a.LKME_DEVICE_ID.a(), a.a().f());
            jSONObject.putOpt(com.microquation.linkedme.android.util.c.a.LKME_DF_ID.a(), this.b.j());
            jSONObject.putOpt(com.microquation.linkedme.android.util.c.a.LKME_IDENTITY_ID.a(), this.b.l());
            jSONObject.putOpt(com.microquation.linkedme.android.util.c.a.LKME_SESSION_ID.a(), this.b.k());
            a(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.c = true;
        }
    }

    public p(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void a() {
        this.b.l("lkme_no_value");
        this.b.e("lkme_no_value");
    }

    public void a(int i, String str) {
        a();
    }

    public void a(u uVar, a aVar) {
        a();
        if (this.f != null) {
            this.f.a();
        }
        e h = aVar.h();
        aVar.h().b((((("" + h.x() + ",") + h.q() + ",") + h.r() + ",") + h.s() + ",") + h.t());
    }

    public boolean a(Context context) {
        a();
        if (super.b(context)) {
            return false;
        }
        Log.i(a.a, "无联网权限，请添加联网权限！");
        return true;
    }

    public boolean c() {
        return false;
    }

    public void d() {
    }
}
