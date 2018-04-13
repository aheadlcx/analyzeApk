package qsbk.app.activity;

import android.support.v4.app.Fragment;
import qsbk.app.fragments.QiushiListFragment;

class sg implements Runnable {
    final /* synthetic */ Fragment a;
    final /* synthetic */ MainActivity b;

    sg(MainActivity mainActivity, Fragment fragment) {
        this.b = mainActivity;
        this.a = fragment;
    }

    public void run() {
        if (this.a != null && (this.a instanceof QiushiListFragment)) {
            ((QiushiListFragment) this.a).gotoSubscribeFragment();
        }
    }
}
