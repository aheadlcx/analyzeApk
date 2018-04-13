package com.budejie.www.util;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import com.budejie.www.util.f.a;

class f$1 extends Handler {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ f c;

    f$1(f fVar, a aVar, String str) {
        this.c = fVar;
        this.a = aVar;
        this.b = str;
    }

    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.a.a((Drawable) message.obj, this.b);
        } else if (i == 2) {
            this.a.a(null, this.b);
        }
    }
}
