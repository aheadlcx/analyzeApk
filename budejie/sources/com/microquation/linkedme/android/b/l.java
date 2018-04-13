package com.microquation.linkedme.android.b;

import android.content.Context;
import android.util.Log;
import com.microquation.linkedme.android.a;
import com.microquation.linkedme.android.a.d;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.c;
import org.json.JSONException;
import org.json.JSONObject;

public class l extends h {
    public l(Context context, String str) {
        super(context, str);
    }

    public void a(int i, String str) {
    }

    public void a(u uVar, a aVar) {
        JSONObject b = uVar.b();
        if (b != null) {
            this.b.b(b.optBoolean(c.a.LKME_IS_GAL.a(), false));
            if (b.has(c.a.LKME_GAL_INTERVAL.a())) {
                this.b.a(b.optInt(c.a.LKME_GAL_INTERVAL.a(), this.b.E()));
            }
            if (b.has(c.a.LKME_GAL_REQ_INTERVAL.a())) {
                this.b.b(b.optInt(c.a.LKME_GAL_REQ_INTERVAL.a(), this.b.I()));
            }
            if (b.has(c.a.LKME_GAL_TRACK.a())) {
                try {
                    JSONObject jSONObject = new JSONObject(b.optString(c.a.LKME_GAL_TRACK.a()));
                    this.b.c(jSONObject.optBoolean(c.c.IS_LC.a(), this.b.K()));
                    this.b.e(jSONObject.optBoolean(c.c.LC_FINE.a(), this.b.U()));
                    this.b.c(jSONObject.optInt(c.c.LC_INTERVAL.a(), this.b.L()));
                    this.b.d(jSONObject.optBoolean(c.c.KEEP_TRACKING.a(), this.b.M()));
                    this.b.d(jSONObject.optInt(c.c.MIN_TIME.a(), this.b.N()));
                    this.b.e(jSONObject.optInt(c.c.MIN_DISTANCE.a(), this.b.O()));
                    this.b.f(jSONObject.optInt(c.c.DELAY.a(), this.b.P()));
                    this.b.g(jSONObject.optInt(c.c.PERIOD.a(), this.b.Q()));
                    this.b.h(jSONObject.optInt(c.c.DURATION.a(), this.b.R()));
                    this.b.g(jSONObject.optBoolean(c.c.LC_UP.a(), this.b.Z()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            b.a("校验是否上传LC数据");
            if (this.b.V()) {
                d.a().b();
            }
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        Log.i(a.a, "联网失败！ 请检查是否在manifest文件中添加了网络权限");
        return true;
    }

    public boolean c() {
        return true;
    }

    public void d() {
    }
}
