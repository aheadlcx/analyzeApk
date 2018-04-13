package com.baidu.location.a;

import android.location.Location;

class d implements Runnable {
    final /* synthetic */ Location a;
    final /* synthetic */ c b;

    d(c cVar, Location location) {
        this.b = cVar;
        this.a = location;
    }

    public void run() {
        this.b.b(this.a);
    }
}
