package com.sprite.ads.internal.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Random;

public class a {
    public static String a(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String string = contentResolver == null ? null : Secure.getString(contentResolver, "android_id");
        return (string == null || a()) ? "emulator" : string;
    }

    private static String a(String str) {
        try {
            MessageDigest.getInstance("MD5").update(str.getBytes());
            return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, r0.digest())});
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static boolean a() {
        return Build.DEVICE.startsWith("generic");
    }

    public static String b() {
        return d.a() ? "1" : "0";
    }

    public static String b(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String string = contentResolver == null ? null : Secure.getString(contentResolver, "android_id");
        if (string == null || a()) {
            string = "emulator";
        }
        return a(string);
    }

    private static String c() {
        StringBuffer stringBuffer = new StringBuffer();
        long currentTimeMillis = System.currentTimeMillis();
        String l = Long.toString(currentTimeMillis);
        stringBuffer.append(l.substring(l.length() - 5));
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(Build.MODEL.replaceAll(" ", ""));
        while (stringBuffer2.length() < 6) {
            stringBuffer2.append('0');
        }
        stringBuffer.append(stringBuffer2.substring(0, 6));
        Random random = new Random(currentTimeMillis);
        currentTimeMillis = 0;
        while (currentTimeMillis < PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            currentTimeMillis = random.nextLong();
        }
        stringBuffer.append(Long.toHexString(currentTimeMillis).substring(0, 4));
        return stringBuffer.toString();
    }

    public static String c(Context context) {
        String macAddress = ((WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI)).getConnectionInfo().getMacAddress();
        if (macAddress == null || "".equals(macAddress)) {
            return context.getSharedPreferences("mac_address", 0).getString("mac_address", "");
        }
        Editor edit = context.getSharedPreferences("mac_address", 0).edit();
        edit.putString("mac_address", macAddress);
        edit.commit();
        return macAddress;
    }

    public static String d(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("imei", 0);
        String string = sharedPreferences.getString("imei", null);
        if (string == null || string.length() == 0) {
            string = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (string == null || string.length() == 0) {
                string = c();
            }
            string = string.replaceAll(" ", "").trim();
            while (string.length() < 15) {
                string = "0" + string;
            }
            Editor edit = sharedPreferences.edit();
            edit.putString("imei", string);
            edit.commit();
        }
        return string.trim();
    }

    public static String e(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "NONE";
        }
        String subscriberId = telephonyManager.getSubscriberId();
        return subscriberId == null ? "NONE" : (subscriberId.startsWith("46000") || subscriberId.startsWith("46002")) ? "1" : subscriberId.startsWith("46001") ? "2" : subscriberId.startsWith("46003") ? "3" : "UNKNOWN";
    }
}
