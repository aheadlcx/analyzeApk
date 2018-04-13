package com.budejie.www.util;

import android.util.Log;
import net.tsz.afinal.a.a;

class g$1 extends a<String> {
    final /* synthetic */ g a;

    g$1(g gVar) {
        this.a = gVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        this.a.a(g.a.a(str));
    }

    public void onFailure(Throwable th, int i, String str) {
        Log.i("BgMusicManager", "down load failed " + str);
        this.a.a(null);
    }
}
