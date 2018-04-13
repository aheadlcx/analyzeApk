package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.framework.b;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.e;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class j {
    private static boolean a = false;
    private static HandlerThread b;
    private static Context c;
    private static int d = 0;
    private static int e = 0;
    private static int f = 0;
    private static int g = 1;
    private static long h = 0;
    private static long i = 0;
    private static SensorManager j;
    private static ArrayList<float[]> k = new ArrayList();
    private static SensorEventListener l = new o();

    public static class a {
        public int a;
        public int b;
        public long c;
    }

    static /* synthetic */ int c() {
        int i = e;
        e = i + 1;
        return i;
    }

    static /* synthetic */ int e() {
        int i = d;
        d = i + 1;
        return i;
    }

    static /* synthetic */ int h() {
        int i = g;
        g = i + 1;
        return i;
    }

    public static List<Sensor> a(Context context) {
        if (context == null) {
            return null;
        }
        return ((SensorManager) context.getSystemService(g.aa)).getSensorList(-1);
    }

    public static void b(Context context) {
        if (context != null && !a()) {
            a = true;
            c = context.getApplicationContext();
            String a = b.a(context);
            String packageName = context.getPackageName();
            if (a != null && a.equals(packageName)) {
                j = (SensorManager) context.getSystemService(g.aa);
                Sensor defaultSensor = j.getDefaultSensor(4);
                Sensor defaultSensor2 = j.getDefaultSensor(1);
                if (defaultSensor != null) {
                    f = 4;
                    j.registerListener(l, defaultSensor, 50000);
                } else if (defaultSensor2 != null) {
                    f = 1;
                    j.registerListener(l, defaultSensor2, 50000);
                }
                int nextInt = (new Random().nextInt(3) * 1000) + 4000;
                b = new HandlerThread("sensor_thread");
                b.start();
                new Handler(b.getLooper()).postDelayed(new p(defaultSensor, defaultSensor2), (long) nextInt);
            }
        }
    }

    private static void l() {
        if (j != null) {
            j.unregisterListener(l);
        }
        if (k.size() == 40) {
            f(c);
            if (k != null) {
                k.clear();
            }
            if (b != null) {
                b.quit();
                b = null;
            }
            c = null;
            a = false;
        }
    }

    public static JSONArray c(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString("stat", null);
            if (string != null) {
                try {
                    return new JSONArray(string);
                } catch (JSONException e) {
                    return null;
                }
            }
        }
        return null;
    }

    private static void f(Context context) {
        if (context != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < 2; i++) {
                    int i2;
                    int i3;
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray2 = new JSONArray();
                    if (i == 1) {
                        i2 = 20;
                        i3 = 40;
                    } else {
                        i2 = 0;
                        i3 = 20;
                    }
                    while (i2 < i3) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("x", (double) ((float[]) k.get(i2))[0]);
                        jSONObject2.put("y", (double) ((float[]) k.get(i2))[1]);
                        jSONObject2.put("z", (double) ((float[]) k.get(i2))[2]);
                        jSONArray2.put(jSONObject2);
                        i2++;
                    }
                    if (f == 4) {
                        jSONObject.put(IXAdRequestInfo.GPS, jSONArray2);
                    } else if (f == 1) {
                        jSONObject.put("a", jSONArray2);
                    }
                    if (i == 0) {
                        jSONObject.put("ts", h);
                    } else {
                        jSONObject.put("ts", i);
                    }
                    jSONArray.put(jSONObject);
                    UMWorkDispatch.sendEvent(context, com.umeng.commonsdk.internal.a.l, com.umeng.commonsdk.internal.b.a(context).a(), jSONArray.toString());
                }
            } catch (Throwable e) {
                com.umeng.commonsdk.proguard.b.a(context, e);
            }
        }
    }

    public static void d(Context context) {
        if (context != null) {
            context.getApplicationContext().getSharedPreferences("info", 0).edit().remove("stat").commit();
        }
    }

    public static synchronized boolean a() {
        boolean z;
        synchronized (j.class) {
            z = a;
        }
        return z;
    }

    public static List<a> e(Context context) {
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.CAMERA")) {
            return null;
        }
        List<a> arrayList = new ArrayList();
        try {
            if (VERSION.SDK_INT >= 21) {
                CameraManager cameraManager = (CameraManager) context.getSystemService("camera");
                String[] cameraIdList = cameraManager.getCameraIdList();
                for (String cameraCharacteristics : cameraIdList) {
                    Size size = (Size) cameraManager.getCameraCharacteristics(cameraCharacteristics).get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
                    if (size != null) {
                        a aVar = new a();
                        aVar.a = size.getWidth();
                        aVar.b = size.getHeight();
                        aVar.c = System.currentTimeMillis();
                        arrayList.add(aVar);
                    }
                }
            }
        } catch (Exception e) {
            e.c("camera access exception");
        }
        return arrayList;
    }
}
