package qsbk.app.nearby.api;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import com.umeng.commonsdk.proguard.g;

public class ShakeListener implements SensorEventListener {
    private static final String a = ShakeListener.class.getSimpleName();
    private float b = 9.81f;
    private float[] c = new float[3];
    private Sensor d;
    private SensorManager e;
    private long f;
    private OnShakeListener g;
    private boolean h = true;

    public interface OnShakeListener {
        void onShakeDown();

        void onShakeUp(float f);
    }

    public ShakeListener(Context context) {
        this.e = (SensorManager) context.getSystemService(g.aa);
        if (this.e != null) {
            this.d = this.e.getDefaultSensor(1);
        }
    }

    public void start() {
        if (this.d != null) {
            this.e.registerListener(this, this.d, 1);
        }
    }

    public void stop() {
        this.e.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (getAllowShaking()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - this.f >= 150) {
                this.f = elapsedRealtime;
                float a = a(sensorEvent);
                if (a >= this.b && this.g != null) {
                    this.g.onShakeUp(a);
                } else if (a < this.b && this.g != null) {
                    this.g.onShakeDown();
                }
            }
        }
    }

    public float getAccelerationThreshold() {
        return this.b;
    }

    public void setAccelerationThreshold(float f) {
        this.b = Math.max(f, 9.81f);
    }

    public void setOnShakeListener(OnShakeListener onShakeListener) {
        this.g = onShakeListener;
    }

    public boolean getAllowShaking() {
        return this.h;
    }

    public void setAllowShaking(boolean z) {
        this.h = z;
    }

    private float a(SensorEvent sensorEvent) {
        this.c[0] = a(sensorEvent.values[0], 0);
        this.c[1] = a(sensorEvent.values[1], 1);
        this.c[2] = a(sensorEvent.values[2], 2);
        return Math.max(Math.max(sensorEvent.values[0] - this.c[0], sensorEvent.values[1] - this.c[1]), sensorEvent.values[2] - this.c[2]);
    }

    private float a(float f, int i) {
        return (0.8f * this.c[i]) + (0.19999999f * f);
    }
}
