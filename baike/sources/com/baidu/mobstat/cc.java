package com.baidu.mobstat;

import android.content.Context;
import java.io.File;
import java.util.Arrays;

class cc implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ by b;

    cc(by byVar, Context context) {
        this.b = byVar;
        this.a = context;
    }

    public void run() {
        File filesDir = this.a.getFilesDir();
        if (filesDir != null && filesDir.exists()) {
            String[] list = filesDir.list(new cd(this));
            if (list != null && list.length != 0) {
                try {
                    Arrays.sort(list, new ce(this));
                } catch (Throwable e) {
                    db.b(e);
                }
                try {
                    int i = 0;
                    for (String str : list) {
                        String a = cu.a(this.a, str);
                        if (this.b.b(this.a, a)) {
                            cu.b(this.a, str);
                            i = 0;
                        } else {
                            by.b(this.a, str, a);
                            i++;
                            if (i >= 5) {
                                return;
                            }
                        }
                    }
                } catch (Throwable e2) {
                    db.b(e2);
                }
            }
        }
    }
}
