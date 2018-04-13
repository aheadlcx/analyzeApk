package qsbk.app.fragments;

import com.alipay.android.phone.mrpc.core.Headers;

class hq implements Runnable {
    final /* synthetic */ ho a;

    hq(ho hoVar) {
        this.a = hoVar;
    }

    public void run() {
        NewsFragment.a(this.a.a, false);
        NewsFragment.b(this.a.a, Headers.REFRESH);
        NewsFragment.a(this.a.a, 1);
        NewsFragment.b(this.a.a).refresh();
    }
}
