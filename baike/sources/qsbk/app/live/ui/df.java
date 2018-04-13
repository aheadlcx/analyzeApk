package qsbk.app.live.ui;

import android.view.View;

class df implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ View c;
    final /* synthetic */ int d;
    final /* synthetic */ int e;
    final /* synthetic */ int f;
    final /* synthetic */ LiveBaseActivity g;

    df(LiveBaseActivity liveBaseActivity, int i, int i2, View view, int i3, int i4, int i5) {
        this.g = liveBaseActivity;
        this.a = i;
        this.b = i2;
        this.c = view;
        this.d = i3;
        this.e = i4;
        this.f = i5;
    }

    public void run() {
        if (this.a < this.b - 1) {
            this.c.scrollBy(this.d, this.e);
        } else {
            this.c.scrollTo(this.f, this.e);
        }
    }
}
