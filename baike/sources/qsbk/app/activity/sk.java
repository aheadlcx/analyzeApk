package qsbk.app.activity;

import android.support.v4.app.Fragment;
import qsbk.app.fragments.QiuyouCircleFragment;

class sk implements Runnable {
    final /* synthetic */ Fragment a;
    final /* synthetic */ MainActivity b;

    sk(MainActivity mainActivity, Fragment fragment) {
        this.b = mainActivity;
        this.a = fragment;
    }

    public void run() {
        if (this.a != null && (this.a instanceof QiuyouCircleFragment)) {
            ((QiuyouCircleFragment) this.a).gotoCircleVideo();
        }
    }
}
