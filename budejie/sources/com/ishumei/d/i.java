package com.ishumei.d;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.ishumei.b.d;
import com.ishumei.f.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class i {
    private static i c = null;
    SensorManager a = null;
    public final a b;

    public class a {
        int a = 0;
        boolean b = false;
        boolean c = false;
        float[] d = new float[3];
        float[] e = new float[3];
        SensorManager f = null;
        ArrayList<Boolean> g = new ArrayList(2);
        ArrayList<b> h = new ArrayList(2);
        final /* synthetic */ i i;
        private final String j = "Sensor-Gyro";
        private boolean k = false;
        private float l = 0.0f;
        private float m = 0.0f;
        private float n = 0.0f;
        private SensorEventListener o = new SensorEventListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            public void onSensorChanged(SensorEvent sensorEvent) {
                c.a("Sensor-Gyro", "onSensorChanged begin");
                a aVar;
                a aVar2;
                try {
                    if (sensorEvent.sensor.getType() == 2) {
                        this.a.e = sensorEvent.values;
                        this.a.c = true;
                    }
                    if (sensorEvent.sensor.getType() == 1) {
                        this.a.d = sensorEvent.values;
                        this.a.b = true;
                    }
                    if (this.a.b && this.a.c) {
                        float[] fArr = new float[3];
                        float[] fArr2 = new float[9];
                        SensorManager.getRotationMatrix(fArr2, null, this.a.d, this.a.e);
                        SensorManager.getOrientation(fArr2, fArr);
                        this.a.l = (float) Math.toDegrees((double) fArr[0]);
                        this.a.m = (float) Math.toDegrees((double) fArr[1]);
                        this.a.n = (float) Math.toDegrees((double) fArr[2]);
                        this.a.k = true;
                        c.a("Sensor-Gyro", this.a.l + " " + this.a.m + " " + this.a.n);
                    }
                    synchronized (this) {
                        if (this.a.k) {
                            c.a("Sensor-Gyro", "notifyAll");
                            notifyAll();
                            this.a.a(this.a.l, this.a.m, this.a.n);
                            aVar = this.a;
                            aVar2 = this.a;
                            this.a.c = false;
                            aVar2.b = false;
                            aVar.k = false;
                        }
                    }
                } catch (Exception e) {
                    c.d("Sensor-Gyro", "gyro onSensorChanged failed: " + e.getMessage());
                    synchronized (this) {
                        if (this.a.k) {
                            c.a("Sensor-Gyro", "notifyAll");
                            notifyAll();
                            this.a.a(this.a.l, this.a.m, this.a.n);
                            aVar = this.a;
                            aVar2 = this.a;
                            this.a.c = false;
                            aVar2.b = false;
                            aVar.k = false;
                        }
                    }
                } catch (Throwable th) {
                    synchronized (this) {
                        if (this.a.k) {
                            c.a("Sensor-Gyro", "notifyAll");
                            notifyAll();
                            this.a.a(this.a.l, this.a.m, this.a.n);
                            aVar2 = this.a;
                            a aVar3 = this.a;
                            this.a.c = false;
                            aVar3.b = false;
                            aVar2.k = false;
                        }
                    }
                }
            }
        };

        public a(i iVar, Context context) {
            this.i = iVar;
            if (context != null) {
                try {
                    this.f = (SensorManager) context.getSystemService("sensor");
                } catch (Exception e) {
                    c.d("Sensor-Gyro", "get SENSOR_SERVICE failed: " + e.getMessage());
                }
            }
        }

        private synchronized void a(float f, float f2, float f3) {
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                c.a("Sensor-Gyro", "onUpdate begin");
                ((b) it.next()).a(f, f2, f3);
                c();
            }
            this.h.clear();
        }

        private synchronized void a(b bVar) {
            try {
                if (this.f != null) {
                    this.a--;
                    c.a("Sensor-Gyro", "registerSuccessedCount-1 = " + this.a);
                    if (this.a == 0) {
                        this.f.unregisterListener(this.o);
                        c.a("Sensor-Gyro", "unregisterListener");
                    }
                }
                if (bVar != null) {
                    this.h.remove(bVar);
                }
            } catch (Exception e) {
                c.d("Sensor-Gyro", "gyro unregister failed: " + e.getMessage());
            }
        }

        private synchronized void b() {
            try {
                if (this.f != null) {
                    if (this.a == 0) {
                        c.a("Sensor-Gyro", "register listener");
                        Sensor defaultSensor = this.f.getDefaultSensor(1);
                        Sensor defaultSensor2 = this.f.getDefaultSensor(2);
                        if (!this.f.registerListener(this.o, defaultSensor, 3)) {
                            c.d("Sensor-Gyro", "sensor registerListener SENSOR_DELAY_NORMAL failed: ");
                        } else if (!this.f.registerListener(this.o, defaultSensor2, 3)) {
                            c.d("Sensor-Gyro", "sensor registerListener SENSOR_DELAY_NORMAL failed: ");
                        }
                    }
                    this.a++;
                    c.a("Sensor-Gyro", "registerSuccessedCount+1 = " + this.a);
                }
            } catch (Exception e) {
                c.d("Sensor-Gyro", "gyro register failed: " + e.getMessage());
            }
        }

        private synchronized void c() {
            a(null);
        }

        public float[] a() {
            float[] fArr = new float[3];
            b();
            try {
                synchronized (this.o) {
                    int i = 0;
                    while (this.m == 0.0f && i < 4) {
                        i++;
                        c.a("Sensor-Gyro", "wait cnt=" + i);
                        this.o.wait(2000);
                    }
                }
                fArr[0] = this.l;
                fArr[1] = this.m;
                fArr[2] = this.n;
                c();
            } catch (Exception e) {
                try {
                    c.d("Sensor-Gyro", "gyro getValuesSync failed: " + e.getMessage());
                    fArr[0] = this.l;
                    fArr[1] = this.m;
                    fArr[2] = this.n;
                    c();
                } catch (Throwable th) {
                    fArr[0] = this.l;
                    fArr[1] = this.m;
                    fArr[2] = this.n;
                    c();
                }
            }
            return fArr;
        }
    }

    public interface b {
        void a(float f, float f2, float f3);
    }

    private i() {
        if (d.a != null) {
            this.a = (SensorManager) d.a.getSystemService("sensor");
        }
        this.b = new a(this, d.a);
    }

    public static i a() {
        if (c == null) {
            synchronized (i.class) {
                if (c == null) {
                    c = new i();
                }
            }
        }
        return c;
    }

    public final List<String> b() {
        List<String> arrayList = new ArrayList();
        try {
            for (Sensor sensor : this.a.getSensorList(-1)) {
                arrayList.add(sensor.getType() + "," + sensor.getVendor());
            }
        } catch (Exception e) {
            c.a("Sensor", "Get sensor info error", e);
        }
        return arrayList;
    }
}
