package c.a.f;

import android.content.Context;
import c.a.d.a.a;

public class c implements c.a.c.c {
    public String a(Context context, String str) {
        a.a().a(context.getResources(), context.getPackageName(), str, this);
        return str;
    }

    public String a(Context context, String str, int i) {
        return str + "_" + context.getResources().getResourceEntryName(i);
    }

    public int a() {
        return 2;
    }
}
