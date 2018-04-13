package com.umeng.commonsdk.framework;

import android.content.Context;
import com.umeng.commonsdk.framework.UMLogDataProtocol.UMBusinessType;
import com.umeng.commonsdk.statistics.b;
import com.umeng.commonsdk.statistics.common.e;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import org.json.JSONObject;

public class a {
    public static long a(Context context) {
        if (context == null) {
            return 0;
        }
        return b.i(context.getApplicationContext());
    }

    public static boolean a(Context context, UMBusinessType uMBusinessType) {
        boolean z = false;
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            boolean b = b.b(applicationContext);
            int c = b.c(applicationContext);
            if (b && !b.a(applicationContext, uMBusinessType)) {
                z = true;
            }
            if (b && c > 0) {
                g.b();
            }
        }
        return z;
    }

    public static JSONObject a(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        e.b("--->>> buildEnvelopeFile Enter.");
        return new b().a(context.getApplicationContext(), jSONObject, jSONObject2);
    }

    public static String a(Context context, String str, String str2) {
        return context == null ? str2 : ImprintHandler.getImprintService(context.getApplicationContext()).b().a(str, str2);
    }

    public static long b(Context context) {
        if (context == null) {
            return 0;
        }
        return b.a(context.getApplicationContext());
    }
}
