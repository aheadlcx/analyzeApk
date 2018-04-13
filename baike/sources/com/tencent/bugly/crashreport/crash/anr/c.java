package com.tencent.bugly.crashreport.crash.anr;

import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.a;
import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.b;
import com.tencent.bugly.proguard.x;
import java.util.HashMap;

final class c implements b {
    private /* synthetic */ a a;
    private /* synthetic */ boolean b;

    c(a aVar, boolean z) {
        this.a = aVar;
        this.b = z;
    }

    public final boolean a(String str, int i, String str2, String str3) {
        x.c("new thread %s", str);
        if (this.a.a > 0 && this.a.c > 0 && this.a.b != null) {
            if (this.a.d == null) {
                this.a.d = new HashMap();
            }
            this.a.d.put(str, new String[]{str2, str3, i});
        }
        return true;
    }

    public final boolean a(long j, long j2, String str) {
        x.c("new process %s", str);
        if (!str.equals(str)) {
            return true;
        }
        this.a.a = j;
        this.a.b = str;
        this.a.c = j2;
        if (this.b) {
            return true;
        }
        return false;
    }

    public final boolean a(long j) {
        x.c("process end %d", Long.valueOf(j));
        if (this.a.a <= 0 || this.a.c <= 0 || this.a.b == null) {
            return true;
        }
        return false;
    }
}
