package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.g;

public class h extends a {
    private Context a;

    public h(Context context) {
        super("newumid");
        this.a = context;
    }

    public String f() {
        return UMEnvelopeBuild.imprintProperty(this.a, g.f, null);
    }
}
