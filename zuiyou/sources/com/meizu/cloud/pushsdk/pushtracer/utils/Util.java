package com.meizu.cloud.pushsdk.pushtracer.utils;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Base64;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
    private static final String TAG = Util.class.getSimpleName();

    public static JSONObject mapToJSONObject(Map map) {
        if (VERSION.SDK_INT >= 19) {
            return new JSONObject(map);
        }
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            try {
                jSONObject.put((String) entry.getKey(), getJsonSafeObject(entry.getValue()));
            } catch (JSONException e) {
                Logger.e(TAG, "Could not put key '%s' and value '%s' into new JSONObject: %s", r1, r0, e);
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    private static Object getJsonSafeObject(Object obj) {
        if (VERSION.SDK_INT >= 19) {
            return obj;
        }
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj;
        }
        JSONArray jSONArray;
        if (obj instanceof Collection) {
            jSONArray = new JSONArray();
            for (Object jsonSafeObject : (Collection) obj) {
                jSONArray.put(getJsonSafeObject(jsonSafeObject));
            }
            return jSONArray;
        } else if (obj.getClass().isArray()) {
            jSONArray = new JSONArray();
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                jSONArray.put(getJsonSafeObject(Array.get(obj, i)));
            }
            return jSONArray;
        } else if (obj instanceof Map) {
            return mapToJSONObject((Map) obj);
        } else {
            if ((obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof String)) {
                return obj;
            }
            if (obj.getClass().getPackage().getName().startsWith("java.")) {
                return obj.toString();
            }
            return null;
        }
    }

    public static long getUTF8Length(String str) {
        long j = 0;
        int i = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt <= '') {
                j++;
            } else if (charAt <= '߿') {
                j += 2;
            } else if (charAt >= '?' && charAt <= '?') {
                j += 4;
                i++;
            } else if (charAt < '￿') {
                j += 3;
            } else {
                j += 4;
            }
            i++;
        }
        return j;
    }

    public static String base64Encode(String str) {
        return Base64.encodeToString(str.getBytes(), 2);
    }

    public static String getTimestamp() {
        return Long.toString(System.currentTimeMillis());
    }

    public static boolean isOnline(Context context) {
        Logger.i(TAG, "Checking tracker internet connectivity.", new Object[0]);
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
            Logger.d(TAG, "Tracker connection online: %s", Boolean.valueOf(z));
            return z;
        } catch (SecurityException e) {
            Logger.e(TAG, "Security exception checking connection: %s", e.toString());
            return true;
        }
    }

    public static String getCarrier(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return null;
    }

    public static Location getLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(1);
        criteria.setAccuracy(2);
        String bestProvider = locationManager.getBestProvider(criteria, true);
        if (bestProvider != null) {
            try {
                Logger.d(TAG, "Location found: %s", locationManager.getLastKnownLocation(bestProvider));
                return locationManager.getLastKnownLocation(bestProvider);
            } catch (SecurityException e) {
                Logger.e(TAG, "Failed to retrieve location: %s", e.toString());
                return null;
            }
        }
        Logger.e(TAG, "Location Manager provider is null.", new Object[0]);
        return null;
    }

    public static boolean isTimeInRange(long j, long j2, long j3) {
        return j > j2 - j3;
    }

    public static String getEventId() {
        return UUID.randomUUID().toString();
    }
}
