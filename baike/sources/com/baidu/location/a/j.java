package com.baidu.location.a;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.baidu.location.c.a;
import com.baidu.location.f;
import com.baidu.mobstat.Config;
import com.umeng.commonsdk.proguard.g;

public class j implements SensorEventListener {
    private static j d;
    private float[] a;
    private float[] b;
    private SensorManager c;
    private float e;
    private double f = Double.MIN_VALUE;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private float j = 0.0f;
    private long k = 0;
    private boolean l = false;
    private long m = 0;

    private j() {
        try {
            if (this.c == null) {
                this.c = (SensorManager) f.getServiceContext().getSystemService(g.aa);
            }
            if (this.c.getDefaultSensor(6) != null) {
                this.i = true;
            }
        } catch (Exception e) {
            this.i = false;
        }
    }

    public static synchronized j a() {
        j jVar;
        synchronized (j.class) {
            if (d == null) {
                d = new j();
            }
            jVar = d;
        }
        return jVar;
    }

    private void k() {
        if (this.c != null) {
            Sensor defaultSensor = this.c.getDefaultSensor(6);
            if (defaultSensor != null) {
                this.c.registerListener(d, defaultSensor, 3);
            }
            a.a().postDelayed(new k(this), 2000);
        }
    }

    public void a(boolean z) {
        this.g = z;
    }

    public synchronized void b() {
        if (!this.l) {
            if (this.g || this.h) {
                if (this.c == null) {
                    this.c = (SensorManager) f.getServiceContext().getSystemService(g.aa);
                }
                if (this.c != null) {
                    Sensor defaultSensor = this.c.getDefaultSensor(11);
                    if (defaultSensor != null && this.g) {
                        this.c.registerListener(this, defaultSensor, 3);
                    }
                    defaultSensor = this.c.getDefaultSensor(6);
                    if (defaultSensor != null && this.h) {
                        this.c.registerListener(this, defaultSensor, 3);
                    }
                }
                this.l = true;
            }
        }
    }

    public void b(boolean z) {
    }

    public synchronized void c() {
        if (this.l) {
            if (this.c != null) {
                this.c.unregisterListener(this);
                this.c = null;
            }
            this.l = false;
            this.j = 0.0f;
        }
    }

    public void d() {
        if (!this.h && this.i && System.currentTimeMillis() - this.m > 60000) {
            this.m = System.currentTimeMillis();
            k();
        }
    }

    public float e() {
        return (!this.i || this.k <= 0 || Math.abs(System.currentTimeMillis() - this.k) >= Config.BPLUS_DELAY_TIME || this.j <= 0.0f) ? 0.0f : this.j;
    }

    public boolean f() {
        return this.g;
    }

    public boolean g() {
        return this.h;
    }

    public float h() {
        return this.e;
    }

    public double i() {
        return this.f;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @SuppressLint({"NewApi"})
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case 6:
                try {
                    this.b = (float[]) sensorEvent.values.clone();
                    this.j = this.b[0];
                    this.k = System.currentTimeMillis();
                    this.f = (double) SensorManager.getAltitude(1013.25f, this.b[0]);
                    return;
                } catch (Exception e) {
                    return;
                }
            case 11:
                this.a = (float[]) sensorEvent.values.clone();
                if (this.a != null) {
                    float[] fArr = new float[9];
                    try {
                        SensorManager.getRotationMatrixFromVector(fArr, this.a);
                        float[] fArr2 = new float[3];
                        SensorManager.getOrientation(fArr, fArr2);
                        this.e = (float) Math.toDegrees((double) fArr2[0]);
                        this.e = (float) Math.floor(this.e >= 0.0f ? (double) this.e : (double) (this.e + 360.0f));
                        return;
                    } catch (Exception e2) {
                        this.e = 0.0f;
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }
}
