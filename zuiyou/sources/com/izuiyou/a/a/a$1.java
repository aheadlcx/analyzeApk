package com.izuiyou.a.a;

import android.content.Context;
import com.getkeepsafe.relinker.b;
import com.getkeepsafe.relinker.b.c;
import com.tencent.mars.xlog.Xlog;
import java.io.File;

class a$1 implements c {
    final /* synthetic */ Context a;
    final /* synthetic */ boolean b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;

    a$1(Context context, boolean z, String str, String str2) {
        this.a = context;
        this.b = z;
        this.c = str;
        this.d = str2;
    }

    public void a() {
        b.a(this.a, "marsxlog", new c(this) {
            final /* synthetic */ a$1 a;

            {
                this.a = r1;
            }

            public void a() {
                File file = new File(this.a.a.getFilesDir(), "xlog");
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (this.a.b) {
                    Xlog.appenderOpen(0, 0, file.getAbsolutePath(), this.a.c, this.a.d, "8d4122688ef94379b5d9db854596d611d538d71577a3a398388293d84c000b794dfd93aa610226713eb438f4085b0ab15b71268b1ba538430e15d6c645f58074");
                    Xlog.setConsoleLogOpen(true);
                } else {
                    Xlog.appenderOpen(1, 0, file.getAbsolutePath(), this.a.c, this.a.d, "8d4122688ef94379b5d9db854596d611d538d71577a3a398388293d84c000b794dfd93aa610226713eb438f4085b0ab15b71268b1ba538430e15d6c645f58074");
                    Xlog.setConsoleLogOpen(false);
                }
                a.a(new Xlog());
            }

            public void a(Throwable th) {
                a.a(th);
            }
        });
    }

    public void a(Throwable th) {
        a.a(th);
    }
}
