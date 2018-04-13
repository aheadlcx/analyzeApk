package com.microquation.linkedme.android;

import android.os.Looper;
import android.text.TextUtils;
import com.microquation.linkedme.android.b.h;
import com.microquation.linkedme.android.b.k;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.a;
import com.microquation.linkedme.android.util.c.c;
import com.microquation.linkedme.android.util.c.g;
import org.json.JSONException;
import org.json.JSONObject;

class a$5 implements Runnable {
    final /* synthetic */ a a;

    a$5(a aVar) {
        this.a = aVar;
    }

    public void run() {
        try {
            if (a.z(this.a) != null) {
                b.a("durationTimer is canceled!");
                a.z(this.a).cancel();
                a.a(this.a, null);
            }
            if (a.k(this.a).Z()) {
                Object X = a.k(this.a).X();
                if (!TextUtils.isEmpty(X)) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.putOpt(c.LC_DATA.a(), a.a(X, "linkedme2017nble"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    a.a().a(k.a(jSONObject, a.a().i()));
                }
            }
            b.a("scheduleGAL 是否主线程===" + (Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId()));
            b.a("scheduleGAL: start");
            h a = k.a(a.A(this.a), g.GAL.a());
            if (!a.n() && !a.a(a.A(this.a))) {
                new a$c(this.a, a).execute(new Void[0]);
            }
        } catch (Exception e2) {
            if (b.a()) {
                e2.printStackTrace();
            }
        }
    }
}
