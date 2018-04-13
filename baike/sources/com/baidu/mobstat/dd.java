package com.baidu.mobstat;

import android.net.LocalServerSocket;
import java.io.IOException;

public final class dd {
    private LocalServerSocket a;

    public final synchronized boolean a() {
        boolean z;
        try {
            if (this.a == null) {
                this.a = new LocalServerSocket("com.baidu.mobstat.bplus");
                z = true;
            }
        } catch (IOException e) {
        }
        z = false;
        return z;
    }

    public final synchronized void b() {
        if (this.a != null) {
            try {
                this.a.close();
                this.a = null;
            } catch (IOException e) {
            }
        }
    }
}
