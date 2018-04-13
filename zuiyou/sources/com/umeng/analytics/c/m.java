package com.umeng.analytics.c;

import android.content.Context;
import com.umeng.a.d;

public class m extends a {
    private static final String a = "idmd5";
    private Context b;

    public m(Context context) {
        super("idmd5");
        this.b = context;
    }

    public String f() {
        return d.d(this.b);
    }
}
