package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.a;

public class c extends a {
    private Context a;

    public c(Context context) {
        super("idfa");
        this.a = context;
    }

    public String f() {
        String a = a.a(this.a);
        return a == null ? "" : a;
    }
}
