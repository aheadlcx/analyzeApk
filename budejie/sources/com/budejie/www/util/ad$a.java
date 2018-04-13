package com.budejie.www.util;

import android.telephony.PhoneStateListener;
import android.util.Log;

class ad$a extends PhoneStateListener {
    boolean a = false;
    final /* synthetic */ ad b;

    ad$a(ad adVar) {
        this.b = adVar;
    }

    public void onCallStateChanged(int i, String str) {
        switch (i) {
            case 0:
                if (this.a && this.b.k() > 0) {
                    Log.i("position", "断电:" + this.b.k());
                    ad.l().a(this.b.k());
                    ad.l().d();
                    this.a = false;
                    break;
                }
            case 1:
                if (ad.l().c()) {
                    this.b.b(ad.l().i());
                    ad.l().e();
                    this.a = true;
                    break;
                }
                break;
        }
        super.onCallStateChanged(i, str);
    }
}
