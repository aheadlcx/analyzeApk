package qsbk.app.fragments;

import android.text.TextUtils;
import com.alipay.android.phone.mrpc.core.Headers;
import qsbk.app.cache.MemoryCache;

class hp implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ho b;

    hp(ho hoVar, String str) {
        this.b = hoVar;
        this.a = str;
    }

    public void run() {
        if (TextUtils.isEmpty(this.a)) {
            NewsFragment.a(this.b.a, false);
            NewsFragment.b(this.b.a, Headers.REFRESH);
            NewsFragment.a(this.b.a, 1);
            NewsFragment.b(this.b.a).refresh();
            return;
        }
        NewsFragment.a(this.b.a, this.a);
        MemoryCache.findOrCreateMemoryCache().clear();
        NewsFragment.a(this.b.a, false);
    }
}
