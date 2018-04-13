package com.baidu.lbsapi.auth;

import android.os.Handler;
import android.os.Looper;

class m extends Thread {
    Handler a = null;
    private Object b = new Object();
    private boolean c = false;

    m() {
    }

    m(String str) {
        super(str);
    }

    public void a() {
        if (b.a) {
            b.a("Looper thread quit()");
        }
        this.a.getLooper().quit();
    }

    public void b() {
        synchronized (this.b) {
            try {
                if (!this.c) {
                    this.b.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void c() {
        synchronized (this.b) {
            this.c = true;
            this.b.notifyAll();
        }
    }

    public void run() {
        Looper.prepare();
        this.a = new Handler();
        if (b.a) {
            b.a("new Handler() finish!!");
        }
        Looper.loop();
        if (b.a) {
            b.a("LooperThread run() thread id:" + String.valueOf(Thread.currentThread().getId()));
        }
    }
}
