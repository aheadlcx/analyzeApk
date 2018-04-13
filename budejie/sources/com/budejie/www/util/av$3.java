package com.budejie.www.util;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

class av$3 implements OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ Handler b;
    final /* synthetic */ av c;

    av$3(av avVar, View view, Handler handler) {
        this.c = avVar;
        this.a = view;
        this.b = handler;
    }

    public void onClick(View view) {
        this.a.setVisibility(8);
        this.b.sendEmptyMessageDelayed(10, 50);
        this.b.sendEmptyMessageDelayed(11, 100);
        this.b.sendEmptyMessageDelayed(12, 80);
        this.b.sendEmptyMessageDelayed(13, 0);
        this.b.sendEmptyMessageDelayed(14, 30);
        this.b.sendEmptyMessageDelayed(15, 20);
        this.b.sendEmptyMessageDelayed(16, 120);
    }
}
