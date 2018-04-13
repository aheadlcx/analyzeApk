package com.microquation.linkedme.android;

import java.util.TimerTask;

class a$3 extends TimerTask {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    public void run() {
        new Thread(new Runnable(this) {
            final /* synthetic */ a$3 a;

            {
                this.a = r1;
            }

            public void run() {
                a.a(this.a.a, false);
            }
        }).start();
    }
}
