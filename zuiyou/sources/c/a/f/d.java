package c.a.f;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import c.a.c.c;
import c.a.h.a;

public abstract class d implements c {
    protected abstract String b(Context context, String str);

    public String a(Context context, String str) {
        String b = b(context, str);
        if (a.a(b)) {
            Object b2 = c.a.c.e().b(b);
            Resources c = c.a.c.e().c(b);
            if (!(c == null || TextUtils.isEmpty(b2))) {
                c.a.d.a.a.a().a(c, b2, str, this);
                return str;
            }
        }
        return null;
    }

    public String a(Context context, String str, int i) {
        return null;
    }
}
