package com.budejie.www.activity.video.barrage.a;

public class i extends Thread {
    volatile boolean b;

    public i(String str) {
        super(str);
    }

    public void a() {
        this.b = true;
    }

    public boolean b() {
        return this.b;
    }

    public void run() {
        if (!this.b) {
        }
    }
}
