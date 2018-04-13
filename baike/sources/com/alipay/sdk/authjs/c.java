package com.alipay.sdk.authjs;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.alipay.sdk.authjs.a.a;
import com.umeng.analytics.pro.b;
import org.json.JSONException;
import org.json.JSONObject;

public final class c {
    b a;
    Context b;

    public c(Context context, b bVar) {
        this.b = context;
        this.a = bVar;
    }

    public final void a(a aVar) throws JSONException {
        if (TextUtils.isEmpty(aVar.k)) {
            a(aVar.i, a.c);
            return;
        }
        Runnable dVar = new d(this, aVar);
        if ((Looper.getMainLooper() == Looper.myLooper() ? 1 : null) != null) {
            dVar.run();
        } else {
            new Handler(Looper.getMainLooper()).post(dVar);
        }
    }

    public final void a(String str, int i) throws JSONException {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(b.J, i - 1);
            a aVar = new a(a.c);
            aVar.m = jSONObject;
            aVar.i = str;
            this.a.a(aVar);
        }
    }
}
