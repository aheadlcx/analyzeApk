package cn.xiaochuankeji.tieba.ui.videomaker.edittext;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import java.io.File;

public class a {
    private static Typeface a;
    private static Typeface b;

    public static Typeface a(Context context, int i) {
        File a;
        if (1 == i) {
            if (a != null) {
                return a;
            }
            a = a(1);
            if (a == null) {
                return null;
            }
            a = Typeface.createFromFile(a);
            return a;
        } else if (2 != i) {
            return null;
        } else {
            if (b != null) {
                return b;
            }
            a = a(2);
            if (a == null) {
                return null;
            }
            b = Typeface.createFromFile(a);
            return b;
        }
    }

    public static void a(int i, String str) {
        cn.xiaochuankeji.tieba.background.a.a().edit().putString(i == 1 ? "key_wen_yue" : "key_pian_pian", str).apply();
    }

    public static File a(int i) {
        Object string = cn.xiaochuankeji.tieba.background.a.a().getString(i == 1 ? "key_wen_yue" : "key_pian_pian", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        File file = new File(string);
        if (!file.exists() || file.length() == 0) {
            return null;
        }
        return file;
    }

    public static void a() {
        a = null;
        b = null;
    }
}
