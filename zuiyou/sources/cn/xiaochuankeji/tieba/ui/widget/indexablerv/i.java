package cn.xiaochuankeji.tieba.ui.widget.indexablerv;

import com.a.b.a.b;
import java.util.regex.Pattern;

public class i {
    public static String a(String str) {
        if (str == null) {
            return "";
        }
        return b.a(str, "").toLowerCase();
    }

    static boolean b(String str) {
        return Pattern.matches("^[a-zA-Z].*+", str);
    }

    static boolean c(String str) {
        return Pattern.matches("^#[a-zA-Z]+#.+", str);
    }

    static String d(String str) {
        return str.substring(1, 2);
    }

    static String e(String str) {
        return str.split("#")[1];
    }

    static String f(String str) {
        return str.split("#")[2];
    }
}
