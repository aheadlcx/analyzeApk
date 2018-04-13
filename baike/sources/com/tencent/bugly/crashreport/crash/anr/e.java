package com.tencent.bugly.crashreport.crash.anr;

import android.os.FileObserver;
import com.baidu.mobstat.Config;
import com.tencent.bugly.proguard.x;

final class e extends FileObserver {
    private /* synthetic */ b a;

    e(b bVar, String str, int i) {
        this.a = bVar;
        super(str, 8);
    }

    public final void onEvent(int i, String str) {
        if (str != null) {
            String str2 = "/data/anr/" + str;
            if (str2.contains(Config.TRACE_PART)) {
                this.a.a(str2);
                return;
            }
            x.d("not anr file %s", str2);
        }
    }
}
