package com.umeng.update;

import android.content.Context;
import u.upd.b;

class UmengUpdateAgent$a implements Runnable {
    Context a;

    public UmengUpdateAgent$a(Context context) {
        this.a = context;
    }

    public void run() {
        try {
            UpdateResponse b = new b(this.a).b();
            if (b == null) {
                UmengUpdateAgent.a(3, null);
            } else if (b.hasUpdate) {
                UmengUpdateAgent.a(0, b);
            } else {
                UmengUpdateAgent.a(1, b);
            }
        } catch (Exception e) {
            UmengUpdateAgent.a(1, null);
            b.a(UpdateConfig.a, "request update error", e);
        } catch (Error e2) {
            b.a(UpdateConfig.a, "request update error" + e2.getMessage());
        }
    }
}
