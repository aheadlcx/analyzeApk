package mtopsdk.xstate.b;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.alipay.sdk.util.h;
import mtopsdk.common.util.c;
import mtopsdk.common.util.m;

public class a {
    private static c a = c.a();

    public static String a(Context context) {
        String str = "";
        try {
            String str2 = VERSION.RELEASE;
            String str3 = Build.MANUFACTURER;
            String str4 = Build.MODEL;
            StringBuilder stringBuilder = new StringBuilder("MTOPSDK/open_1.0.0");
            stringBuilder.append(" (").append("Android").append(h.b);
            stringBuilder.append(str2).append(h.b);
            stringBuilder.append(str3).append(h.b);
            stringBuilder.append(str4).append(")");
            str = stringBuilder.toString();
        } catch (Throwable th) {
            m.d("mtopsdk.PhoneInfo", "[getPhoneBaseInfo] error ---" + th.toString());
        }
        return str;
    }
}
