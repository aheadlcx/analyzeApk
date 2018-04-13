package com.baidu.mobads.production.f;

import android.view.ViewGroup;

class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void run() {
        this.a.w.d("remote Interstitial.removeAd");
        this.a.A = false;
        try {
            this.a.e.removeAllViews();
            ViewGroup a = this.a.c(this.a.e.getContext());
            this.a.C.removeAllViews();
            a.removeView(this.a.C);
        } catch (Throwable e) {
            this.a.w.d("Interstitial.removeAd", e);
        }
    }
}
