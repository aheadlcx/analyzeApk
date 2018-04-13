package com.amap.api.location;

import com.loc.cw;

final class e implements Runnable {
    e() {
    }

    public final void run() {
        try {
            if (UmidtokenInfo.a != null) {
                UmidtokenInfo.a.onDestroy();
            }
        } catch (Throwable th) {
            cw.a(th, "UmidListener", "postDelayed");
        }
    }
}
