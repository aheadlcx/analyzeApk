package com.loc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.umeng.commonsdk.proguard.g;

public final class ca implements SensorEventListener {
    SensorManager a = null;
    Sensor b = null;
    Sensor c = null;
    Sensor d = null;
    public boolean e = false;
    public double f = 0.0d;
    public float g = 0.0f;
    public double h = 0.0d;
    Handler i = new dv(this);
    double j = 0.0d;
    double k = 0.0d;
    double l = 0.0d;
    double m = 0.0d;
    double[] n = new double[3];
    volatile double o = 0.0d;
    long p = 0;
    long q = 0;
    private Context r = null;
    private float s = 1013.25f;
    private float t = 0.0f;

    public ca(Context context) {
        try {
            this.r = context;
            if (this.a == null) {
                this.a = (SensorManager) this.r.getSystemService(g.aa);
            }
            try {
                this.b = this.a.getDefaultSensor(6);
            } catch (Throwable th) {
            }
            try {
                this.c = this.a.getDefaultSensor(11);
            } catch (Throwable th2) {
            }
            try {
                this.d = this.a.getDefaultSensor(1);
            } catch (Throwable th3) {
            }
        } catch (Throwable th4) {
            cw.a(th4, "AMapSensorManager", "<init>");
        }
    }

    public final void a() {
        if (this.a != null && !this.e) {
            this.e = true;
            try {
                if (this.b != null) {
                    this.a.registerListener(this, this.b, 3, this.i);
                }
            } catch (Throwable th) {
                cw.a(th, "AMapSensorManager", "registerListener mPressure");
            }
            try {
                if (this.c != null) {
                    this.a.registerListener(this, this.c, 3, this.i);
                }
            } catch (Throwable th2) {
                cw.a(th2, "AMapSensorManager", "registerListener mRotationVector");
            }
            try {
                if (this.d != null) {
                    this.a.registerListener(this, this.d, 3, this.i);
                }
            } catch (Throwable th22) {
                cw.a(th22, "AMapSensorManager", "registerListener mAcceleroMeterVector");
            }
        }
    }

    public final void b() {
        if (this.a != null && this.e) {
            this.e = false;
            try {
                if (this.b != null) {
                    this.a.unregisterListener(this, this.b);
                }
            } catch (Throwable th) {
            }
            try {
                if (this.c != null) {
                    this.a.unregisterListener(this, this.c);
                }
            } catch (Throwable th2) {
            }
            try {
                if (this.d != null) {
                    this.a.unregisterListener(this, this.d);
                }
            } catch (Throwable th3) {
            }
        }
    }

    public final double c() {
        return this.f;
    }

    public final float d() {
        return this.t;
    }

    public final double e() {
        return this.m;
    }

    public final void f() {
        try {
            b();
            this.b = null;
            this.c = null;
            this.a = null;
            this.d = null;
            this.e = false;
        } catch (Throwable th) {
            cw.a(th, "AMapSensorManager", "destroy");
        }
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent != null) {
            float[] fArr;
            switch (sensorEvent.sensor.getType()) {
                case 1:
                    try {
                        if (this.d != null) {
                            fArr = (float[]) sensorEvent.values.clone();
                            this.n[0] = (this.n[0] * 0.800000011920929d) + ((double) (fArr[0] * 0.19999999f));
                            this.n[1] = (this.n[1] * 0.800000011920929d) + ((double) (fArr[1] * 0.19999999f));
                            this.n[2] = (this.n[2] * 0.800000011920929d) + ((double) (fArr[2] * 0.19999999f));
                            this.j = ((double) fArr[0]) - this.n[0];
                            this.k = ((double) fArr[1]) - this.n[1];
                            this.l = ((double) fArr[2]) - this.n[2];
                            long currentTimeMillis = System.currentTimeMillis();
                            if (currentTimeMillis - this.p >= 100) {
                                double sqrt = Math.sqrt(((this.j * this.j) + (this.k * this.k)) + (this.l * this.l));
                                this.q++;
                                this.p = currentTimeMillis;
                                this.o += sqrt;
                                if (this.q >= 30) {
                                    this.m = this.o / ((double) this.q);
                                    this.o = 0.0d;
                                    this.q = 0;
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        cw.a(th, "AMapSensorManager", "accelerometer");
                        return;
                    }
                case 6:
                    try {
                        if (this.b != null) {
                            fArr = (float[]) sensorEvent.values.clone();
                            if (fArr != null) {
                                this.g = fArr[0];
                            }
                            if (fArr != null) {
                                this.f = (double) de.a(SensorManager.getAltitude(this.s, fArr[0]));
                                return;
                            }
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        cw.a(th2, "AMapSensorManager", "doComputeAltitude");
                        return;
                    }
                case 11:
                    try {
                        if (this.c != null) {
                            fArr = (float[]) sensorEvent.values.clone();
                            if (fArr != null) {
                                float[] fArr2 = new float[9];
                                SensorManager.getRotationMatrixFromVector(fArr2, fArr);
                                fArr = new float[3];
                                SensorManager.getOrientation(fArr2, fArr);
                                this.t = (float) Math.toDegrees((double) fArr[0]);
                                this.t = (float) Math.floor(this.t > 0.0f ? (double) this.t : (double) (this.t + 360.0f));
                                return;
                            }
                            return;
                        }
                        return;
                    } catch (Throwable th22) {
                        cw.a(th22, "AMapSensorManager", "doComputeBearing");
                        return;
                    }
                default:
                    return;
            }
        }
    }
}
