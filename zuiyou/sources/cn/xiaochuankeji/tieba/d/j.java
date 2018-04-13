package cn.xiaochuankeji.tieba.d;

import android.text.TextUtils;
import java.util.regex.Pattern;

public class j {
    private static final Pattern a = Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");

    public static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String str4 = str2 + "=" + str3;
        if (str.endsWith("?")) {
            return str + str4;
        }
        return str + (str.contains("?") ? "&" : "?") + str4;
    }

    public static boolean a(String str) {
        return a.matcher(str).find();
    }
}
