package com.microquation.linkedme.android.b;

import android.content.Context;
import com.microquation.linkedme.android.c.c;
import com.microquation.linkedme.android.c.d;
import com.microquation.linkedme.android.util.h;
import org.json.JSONObject;

public final class k {
    public static h a(Context context) {
        return new i(context);
    }

    public static h a(Context context, c cVar) {
        return new p(context, cVar);
    }

    public static h a(Context context, h hVar, d dVar) {
        return new r(context, dVar, hVar);
    }

    public static h a(Context context, String str) {
        return new l(context, str);
    }

    public static h a(Context context, String str, h hVar, d dVar) {
        return new q(context, dVar, hVar, str);
    }

    public static h a(JSONObject jSONObject, Context context) {
        return new n(jSONObject, context);
    }
}
