package com.baidu.mobads.production.e;

import android.os.CountDownTimer;

class e extends CountDownTimer {
    final /* synthetic */ b a;

    e(b bVar, long j, long j2) {
        this.a = bVar;
        super(j, j2);
    }

    public void onTick(long j) {
        int i = 5;
        int i2 = (int) (j / 1000);
        if (i2 <= 5) {
            i = i2;
        }
        this.a.y.setText(String.valueOf(i));
    }

    public void onFinish() {
        this.a.w.d("CountDownTimer finished");
        this.a.s();
        this.a.h.stop();
    }
}
