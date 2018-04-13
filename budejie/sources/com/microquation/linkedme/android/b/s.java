package com.microquation.linkedme.android.b;

import android.content.Context;
import android.text.TextUtils;
import cn.v6.sixrooms.room.RoomActivity;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.c.a;
import com.microquation.linkedme.android.util.c.g;
import org.json.JSONException;
import org.json.JSONObject;

class s extends h {
    s(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
        b.a("LinkedME Tracking:", "Start sending data.");
        try {
            if (!(TextUtils.equals(g.TrackingRegister.a(), f()) || TextUtils.equals(g.TrackingLogin.a(), f()))) {
                jSONObject.putOpt(a.LKME_USER_ID.a(), this.b.B());
            }
            jSONObject.putOpt(a.LKME_LAT.a(), RoomActivity.VIDEOTYPE_UNKNOWN);
            jSONObject.putOpt(a.LKME_LNG.a(), RoomActivity.VIDEOTYPE_UNKNOWN);
            a(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a(int i, String str) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.microquation.linkedme.android.b.u r4, com.microquation.linkedme.android.a r5) {
        /*
        r3 = this;
        r0 = "LinkedME Tracking:";
        r1 = "Send data success!";
        com.microquation.linkedme.android.f.b.a(r0, r1);
        r0 = r4.b();
        if (r0 == 0) goto L_0x001d;
    L_0x000d:
        r1 = com.microquation.linkedme.android.util.c.g.TrackingRegister;	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
        r1 = r1.a();	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
        r2 = r3.f();	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
        if (r1 != 0) goto L_0x002d;
    L_0x001d:
        r1 = com.microquation.linkedme.android.util.c.g.TrackingLogin;	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
        r1 = r1.a();	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
        r2 = r3.f();	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
        if (r1 == 0) goto L_0x003c;
    L_0x002d:
        r1 = r3.b;	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
        r2 = com.microquation.linkedme.android.util.c.a.LKME_USER_ID;	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
        r2 = r2.a();	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
        r0 = r0.getString(r2);	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
        r1.u(r0);	 Catch:{ JSONException -> 0x003d, Exception -> 0x0042 }
    L_0x003c:
        return;
    L_0x003d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x003c;
    L_0x0042:
        r0 = move-exception;
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microquation.linkedme.android.b.s.a(com.microquation.linkedme.android.b.u, com.microquation.linkedme.android.a):void");
    }

    public boolean a(Context context) {
        return !super.b(context);
    }

    public boolean c() {
        return false;
    }

    public void d() {
    }

    public boolean e() {
        return true;
    }

    public String g() {
        return this.b.c() + this.a;
    }
}
