package com.budejie.www.util;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.budejie.www.util.f.b;

class f$3 extends Handler {
    final /* synthetic */ b a;
    final /* synthetic */ String b;
    final /* synthetic */ f c;

    f$3(f fVar, b bVar, String str) {
        this.c = fVar;
        this.a = bVar;
        this.b = str;
    }

    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.a.a((Drawable) message.obj, this.b);
        } else if (i == 2) {
            this.a.a(null, this.b);
        } else if (i == 3) {
            r0 = (Bundle) message.obj;
            this.a.a((int) r0.getLong("currentLength"), r0.getString("imgUrl"));
        } else if (i == 4) {
            r0 = (Bundle) message.obj;
            this.a.b((int) r0.getLong("totalLength"), r0.getString("imgUrl"));
        } else if (i == 5) {
            String string = ((Bundle) message.obj).getString("imgUrl");
            this.a.b(100, string);
            this.a.a(2, string);
        }
    }
}
