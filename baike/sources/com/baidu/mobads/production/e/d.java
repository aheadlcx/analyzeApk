package com.baidu.mobads.production.e;

import android.view.ViewGroup;

class d implements Runnable {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public void run() {
        this.a.w.d("remote Interstitial.removeAd");
        try {
            if (this.a.e.getParent() != null) {
                ((ViewGroup) this.a.e.getParent()).removeView(this.a.e);
            }
            this.a.e.removeAllViews();
        } catch (Throwable e) {
            this.a.w.d("Interstitial.removeAd", e);
        }
    }
}
