package com.budejie.www.service;

import android.telephony.PhoneStateListener;
import com.budejie.www.service.MediaPlayerServer.a;
import com.budejie.www.util.aa;

class MediaPlayerServer$a$b extends PhoneStateListener {
    boolean a = false;
    final /* synthetic */ a b;

    MediaPlayerServer$a$b(a aVar) {
        this.b = aVar;
    }

    public void onCallStateChanged(int i, String str) {
        switch (i) {
            case 0:
                if (this.a && this.b.l() > 0) {
                    aa.a("position", "断电:" + this.b.l());
                    this.b.a(this.b.l());
                    this.b.b();
                    this.a = false;
                    break;
                }
            case 1:
                if (MediaPlayerServer.a.isPlaying()) {
                    this.b.c(MediaPlayerServer.a.getCurrentPosition());
                    this.b.c();
                    this.a = true;
                    break;
                }
                break;
        }
        super.onCallStateChanged(i, str);
    }
}
