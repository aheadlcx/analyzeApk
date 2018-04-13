package com.crashlytics.android.internal;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.crashlytics.android.Crashlytics;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class ao {
    private static final Pattern a = Pattern.compile("[^\\p{Alnum}]");
    private static final String b = Pattern.quote(MqttTopic.TOPIC_LEVEL_SEPARATOR);
    private final ReentrantLock c = new ReentrantLock();
    private final boolean d;
    private final boolean e;
    private final Context f;

    public ao(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        }
        this.f = context;
        this.d = C0003ab.a(context, "com.crashlytics.CollectDeviceIdentifiers", true);
        if (!this.d) {
            v.a().b().a(Crashlytics.TAG, "Device ID collection disabled for " + context.getPackageName());
        }
        this.e = C0003ab.a(context, "com.crashlytics.CollectUserIdentifiers", true);
        if (!this.e) {
            v.a().b().a(Crashlytics.TAG, "User information collection disabled for " + context.getPackageName());
        }
    }

    public final boolean a() {
        return this.e;
    }

    private boolean a(String str) {
        return this.f.checkCallingPermission(str) == 0;
    }

    private static String b(String str) {
        return str == null ? null : a.matcher(str).replaceAll("").toLowerCase(Locale.US);
    }

    public final String b() {
        String i = v.a().i();
        if (i != null) {
            return i;
        }
        SharedPreferences a = C0003ab.a();
        i = a.getString("crashlytics.installation.id", null);
        if (i == null) {
            return a(a);
        }
        return i;
    }

    public final String c() {
        return String.format(Locale.US, "%s/%s", new Object[]{c(VERSION.RELEASE), c(VERSION.INCREMENTAL)});
    }

    public final String d() {
        return String.format(Locale.US, "%s/%s", new Object[]{c(Build.MANUFACTURER), c(Build.MODEL)});
    }

    private static String c(String str) {
        return str.replaceAll(b, "");
    }

    public final String e() {
        String str = "";
        if (!this.d) {
            return str;
        }
        str = g();
        if (str != null) {
            return str;
        }
        SharedPreferences a = C0003ab.a();
        str = a.getString("crashlytics.installation.id", null);
        if (str == null) {
            return a(a);
        }
        return str;
    }

    private String a(SharedPreferences sharedPreferences) {
        this.c.lock();
        try {
            String string = sharedPreferences.getString("crashlytics.installation.id", null);
            if (string == null) {
                string = b(UUID.randomUUID().toString());
                sharedPreferences.edit().putString("crashlytics.installation.id", string).commit();
            }
            this.c.unlock();
            return string;
        } catch (Throwable th) {
            this.c.unlock();
        }
    }

    public final Map<C0009ap, String> f() {
        String b;
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        String str = null;
        Map hashMap = new HashMap();
        a(hashMap, C0009ap.ANDROID_ID, g());
        C0009ap c0009ap = C0009ap.ANDROID_DEVICE_ID;
        if (this.d && a("android.permission.READ_PHONE_STATE")) {
            TelephonyManager telephonyManager = (TelephonyManager) this.f.getSystemService("phone");
            if (telephonyManager != null) {
                b = b(telephonyManager.getDeviceId());
                a(hashMap, c0009ap, b);
                a(hashMap, C0009ap.ANDROID_SERIAL, i());
                c0009ap = C0009ap.WIFI_MAC_ADDRESS;
                if (this.d && a("android.permission.ACCESS_WIFI_STATE")) {
                    wifiManager = (WifiManager) this.f.getSystemService("wifi");
                    if (wifiManager != null) {
                        connectionInfo = wifiManager.getConnectionInfo();
                        if (connectionInfo != null) {
                            str = b(connectionInfo.getMacAddress());
                        }
                    }
                }
                a(hashMap, c0009ap, str);
                a(hashMap, C0009ap.BLUETOOTH_MAC_ADDRESS, h());
                return Collections.unmodifiableMap(hashMap);
            }
        }
        b = null;
        a(hashMap, c0009ap, b);
        a(hashMap, C0009ap.ANDROID_SERIAL, i());
        c0009ap = C0009ap.WIFI_MAC_ADDRESS;
        wifiManager = (WifiManager) this.f.getSystemService("wifi");
        if (wifiManager != null) {
            connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null) {
                str = b(connectionInfo.getMacAddress());
            }
        }
        a(hashMap, c0009ap, str);
        a(hashMap, C0009ap.BLUETOOTH_MAC_ADDRESS, h());
        return Collections.unmodifiableMap(hashMap);
    }

    private static void a(Map<C0009ap, String> map, C0009ap c0009ap, String str) {
        if (str != null) {
            map.put(c0009ap, str);
        }
    }

    public final String g() {
        if (!this.d) {
            return null;
        }
        String string = Secure.getString(this.f.getContentResolver(), "android_id");
        if ("9774d56d682e549c".equals(string)) {
            return null;
        }
        return b(string);
    }

    public final String h() {
        if (this.d && a("android.permission.BLUETOOTH")) {
            try {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    b(defaultAdapter.getAddress());
                }
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, "Utils#getBluetoothMacAddress failed, returning null. Requires prior call to BluetoothAdatpter.getDefaultAdapter() on thread that has called Looper.prepare()", e);
            }
        }
        return null;
    }

    private String i() {
        if (this.d && VERSION.SDK_INT >= 9) {
            try {
                return b((String) Build.class.getField("SERIAL").get(null));
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, "Could not retrieve android.os.Build.SERIAL value", e);
            }
        }
        return null;
    }
}
