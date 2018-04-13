package com.baidu.mobads.production;

import android.content.Context;
import android.net.Uri;
import com.baidu.mobads.utils.d;

class l implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Uri b;
    final /* synthetic */ a c;

    l(a aVar, String str, Uri uri) {
        this.c = aVar;
        this.a = str;
        this.b = uri;
    }

    public void run() {
        try {
            d.a(Class.forName("com.baidu.vr.vrplayer.VrImageView"), "boost", new Class[]{Context.class, String.class, Uri.class, Class.forName("com.baidu.vr.vrplayer.VrImageView$OnBoostListener")}, new Object[]{this.c.getApplicationContext(), this.a, this.b, null});
        } catch (Throwable e) {
            com.baidu.mobads.utils.l.a().d(e);
        }
    }
}
