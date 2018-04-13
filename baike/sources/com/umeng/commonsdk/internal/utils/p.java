package com.umeng.commonsdk.internal.utils;

import android.hardware.Sensor;

final class p implements Runnable {
    final /* synthetic */ Sensor a;
    final /* synthetic */ Sensor b;

    p(Sensor sensor, Sensor sensor2) {
        this.a = sensor;
        this.b = sensor2;
    }

    public void run() {
        j.d = 0;
        if (this.a != null) {
            j.j.registerListener(j.l, this.a, 50000);
        } else if (this.b != null) {
            j.j.registerListener(j.l, this.b, 50000);
        }
    }
}
