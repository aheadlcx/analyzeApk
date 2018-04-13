package u.fb;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.analytics.pro.x;
import com.umeng.fb.model.Constants;
import org.json.JSONObject;

public class p {
    private static final String a = p.class.getName();

    public static String a(Context context) {
        return "FB[" + a.i(context) + "_" + a.d(context) + "]" + String.valueOf(System.currentTimeMillis()) + String.valueOf((int) (1000.0d + (Math.random() * 9000.0d)));
    }

    public static String a() {
        return "RP" + String.valueOf(System.currentTimeMillis()) + String.valueOf((int) (1000.0d + (Math.random() * 9000.0d)));
    }

    public static JSONObject b(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(x.u, a.c(context));
            jSONObject.put("idmd5", a.d(context));
            jSONObject.put(x.v, Build.MODEL);
            jSONObject.put("appkey", a.i(context));
            jSONObject.put("channel", a.m(context));
            jSONObject.put("app_version", a.b(context));
            jSONObject.put("version_code", a.a(context));
            jSONObject.put(x.k, "Android");
            jSONObject.put("sdk_version", Constants.SDK_VERSION);
            jSONObject.put("os", "Android");
            jSONObject.put(x.q, VERSION.RELEASE);
            jSONObject.put(x.G, a.h(context)[0]);
            jSONObject.put(x.F, a.h(context)[1]);
            jSONObject.put(x.E, a.g(context));
            jSONObject.put(x.r, a.k(context));
            jSONObject.put(x.I, a.f(context)[0]);
            jSONObject.put(x.J, a.f(context)[1]);
            jSONObject.put(x.H, a.e(context));
            jSONObject.put(x.o, a.a());
            jSONObject.put("package", a.n(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
