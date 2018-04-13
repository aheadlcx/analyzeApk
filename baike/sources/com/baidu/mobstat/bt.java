package com.baidu.mobstat;

import android.content.Context;

class bt {
    private static bt a = new bt();
    private boolean b = false;

    public static bt a() {
        return a;
    }

    private bt() {
    }

    public void a(Context context, boolean z) {
        db.a("openExceptonAnalysis");
        if (!this.b) {
            this.b = true;
            bl.a().a(context);
            if (!z) {
                NativeCrashHandler.init(context);
            }
        }
    }
}
