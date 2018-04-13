package android.support.v7.app;

import android.view.View;

class e implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ View b;
    final /* synthetic */ AlertController c;

    e(AlertController alertController, View view, View view2) {
        this.c = alertController;
        this.a = view;
        this.b = view2;
    }

    public void run() {
        AlertController.a(this.c.i, this.a, this.b);
    }
}
