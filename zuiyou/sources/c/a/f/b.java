package c.a.f;

import android.content.Context;
import c.a.c.c;
import c.a.d.a.a;

public class b implements c {
    public String a(Context context, String str) {
        a.a().a(context.getResources(), context.getPackageName(), str, this);
        return str;
    }

    public String a(Context context, String str, int i) {
        return context.getResources().getResourceEntryName(i) + "_" + str;
    }

    public int a() {
        return 1;
    }
}
