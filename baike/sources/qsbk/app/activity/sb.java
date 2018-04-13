package qsbk.app.activity;

import qsbk.app.utils.SharePreferenceUtils;

class sb implements Runnable {
    final /* synthetic */ MainActivity a;

    sb(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        SharePreferenceUtils.trimEmpty();
        MainActivity.h(this.a);
    }
}
