package com.umeng.update;

import android.content.Context;
import com.umeng.update.util.DeltaUpdate;
import org.json.JSONObject;
import u.upd.a;
import u.upd.b;
import u.upd.g;
import u.upd.m;

public class d extends g {
    private final String d = d.class.getName();
    private final String e = UpdateConfig.a;
    private JSONObject f;

    public d(Context context) {
        super(null);
        this.f = a(context);
    }

    private JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", UpdateConfig.a);
            jSONObject.put("appkey", UpdateConfig.getAppkey(context));
            jSONObject.put("version_code", a.a(context));
            jSONObject.put("package", a.i(context));
            jSONObject.put("idmd5", m.b(a.b(context)));
            jSONObject.put("channel", UpdateConfig.getChannel(context));
            jSONObject.put(a.j, UpdateConfig.c);
            jSONObject.put("sdk_version", UpdateConfig.b);
            jSONObject.put(a.k, DeltaUpdate.b(context));
            String str = a.l;
            boolean z = DeltaUpdate.a() && UpdateConfig.isDeltaUpdate();
            jSONObject.put(str, z);
            return jSONObject;
        } catch (Exception e) {
            b.b(this.d, "exception in updateInternal", e);
            return null;
        }
    }

    public JSONObject a() {
        return this.f;
    }

    public String b() {
        return this.c;
    }
}
