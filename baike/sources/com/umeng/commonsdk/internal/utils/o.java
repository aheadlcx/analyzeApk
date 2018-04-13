package com.umeng.commonsdk.internal.utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

final class o implements SensorEventListener {
    o() {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (j.e < 15) {
            j.c();
            return;
        }
        if (j.d < 20) {
            j.e();
            j.k.add(sensorEvent.values);
        }
        if (j.d == 20) {
            j.e();
            if (j.g == 1) {
                j.h = System.currentTimeMillis();
            }
            if (j.g == 2) {
                j.i = System.currentTimeMillis();
            }
            j.h();
            j.l();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
