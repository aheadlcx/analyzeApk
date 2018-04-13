package qsbk.app.widget;

import android.support.v4.app.Fragment;
import qsbk.app.fragments.QiuyouCircleFragment;

class bh implements Runnable {
    final /* synthetic */ Fragment a;
    final /* synthetic */ bg b;

    bh(bg bgVar, Fragment fragment) {
        this.b = bgVar;
        this.a = fragment;
    }

    public void run() {
        if (this.a != null && (this.a instanceof QiuyouCircleFragment)) {
            ((QiuyouCircleFragment) this.a).gotoCircleVideo();
        }
    }
}
