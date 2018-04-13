package com.budejie.www.activity.image;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class b implements SensorEventListener {
    float a;
    float b;
    float c;
    private a d;
    private SensorManager e;
    private Sensor f = this.e.getDefaultSensor(1);
    private boolean g;
    private int h;
    private long i;

    public interface a {
        void d();
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public b(Context context) {
        this.e = (SensorManager) context.getSystemService("sensor");
    }

    public void a() {
        this.g = true;
        this.e.registerListener(this, this.f, 3);
    }

    public void b() {
        this.e.unregisterListener(this, this.f);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.i + 100 <= currentTimeMillis) {
                long j = currentTimeMillis - this.i;
                this.i = currentTimeMillis;
                float f = sensorEvent.values[0];
                float f2 = sensorEvent.values[1];
                float f3 = sensorEvent.values[2];
                float f4 = f - this.a;
                float f5 = f2 - this.b;
                float f6 = f3 - this.c;
                float sqrt = (float) ((Math.sqrt((double) (((f4 * f4) + (f5 * f5)) + (f6 * f6))) / ((double) j)) * 10000.0d);
                this.a = f;
                this.b = f2;
                this.c = f3;
                int i;
                if (sqrt > 20.0f) {
                    if (this.h < 0) {
                        i = this.h - 1;
                        this.h = i;
                    } else {
                        i = -1;
                    }
                    this.h = i;
                    if (this.h + 2 <= 0) {
                        this.g = true;
                        return;
                    }
                    return;
                }
                if (this.h > 0) {
                    i = this.h + 1;
                    this.h = i;
                } else {
                    i = 1;
                }
                this.h = i;
                if (this.g && this.h >= 3) {
                    this.g = false;
                    if (this.d != null) {
                        this.d.d();
                    }
                }
            }
        }
    }
}
