package com.umeng.commonsdk.internal.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.v4.internal.view.SupportMenu;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.utils.UMUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class k {
    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        if (VERSION.SDK_INT >= 23) {
            return Secure.getString(context.getContentResolver(), "bluetooth_address");
        }
        if (UMUtils.checkPermission(context, "android.permission.BLUETOOTH")) {
            return BluetoothAdapter.getDefaultAdapter().getAddress();
        }
        return null;
    }

    public static String b(Context context) {
        if (context == null || !UMUtils.checkPermission(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
    }

    public static String c(Context context) {
        if (context == null || VERSION.SDK_INT < 23 || !UMUtils.checkPermission(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        try {
            String str;
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Class cls = telephonyManager.getClass();
            if (((Integer) cls.getMethod("getPhoneCount", new Class[0]).invoke(telephonyManager, new Object[0])).intValue() == 2) {
                str = (String) cls.getMethod("getDeviceId", new Class[]{Integer.TYPE}).invoke(telephonyManager, new Object[]{Integer.valueOf(2)});
            } else {
                str = null;
            }
            return str;
        } catch (Exception e) {
            return null;
        }
    }

    public static JSONObject d(Context context) {
        if (context == null || !UMUtils.checkPermission(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        CellLocation cellLocation = telephonyManager.getCellLocation();
        int phoneType = telephonyManager.getPhoneType();
        int cid;
        if (phoneType == 1 && (cellLocation instanceof GsmCellLocation)) {
            JSONObject jSONObject;
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            cid = gsmCellLocation.getCid();
            if (cid <= 0 || cid == SupportMenu.USER_MASK) {
                jSONObject = null;
            } else {
                int lac = gsmCellLocation.getLac();
                jSONObject = new JSONObject();
                try {
                    jSONObject.put(IXAdRequestInfo.CELL_ID, cid);
                    jSONObject.put("lacid", lac);
                    jSONObject.put("ts", System.currentTimeMillis());
                } catch (Exception e) {
                }
            }
            return jSONObject;
        } else if (phoneType != 2 || !(cellLocation instanceof CdmaCellLocation)) {
            return null;
        } else {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            phoneType = cdmaCellLocation.getBaseStationId();
            cid = cdmaCellLocation.getNetworkId();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(IXAdRequestInfo.CELL_ID, phoneType);
                jSONObject2.put("lacid", cid);
                jSONObject2.put("ts", System.currentTimeMillis());
                return jSONObject2;
            } catch (Exception e2) {
                return jSONObject2;
            }
        }
    }

    public static void a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("um_pri", 0);
            if (sharedPreferences != null) {
                sharedPreferences.edit().putString("um_common_strength", str).commit();
            }
        }
    }

    public static String e(Context context) {
        if (context == null) {
            return null;
        }
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("um_pri", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("um_common_strength", null);
        }
        return null;
    }

    public static String f(Context context) {
        if (context == null) {
            return null;
        }
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("um_pri", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("um_common_battery", null);
        }
        return null;
    }

    public static void b(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("um_pri", 0);
            if (sharedPreferences != null) {
                sharedPreferences.edit().putString("um_common_battery", str).commit();
            }
        }
    }

    public static JSONArray g(Context context) {
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            for (Sensor sensor : ((SensorManager) context.getSystemService(g.aa)).getSensorList(-1)) {
                if (sensor != null) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("a_type", sensor.getType());
                        jSONObject.put("a_ven", sensor.getVendor());
                        if (VERSION.SDK_INT >= 24) {
                            jSONObject.put("a_id", sensor.getId());
                        }
                        jSONObject.put("a_na", sensor.getName());
                        jSONObject.put("a_ver", sensor.getVersion());
                        jSONObject.put("a_mar", (double) sensor.getMaximumRange());
                        jSONObject.put("a_ver", sensor.getVersion());
                        jSONObject.put("a_res", (double) sensor.getResolution());
                        jSONObject.put("a_po", (double) sensor.getPower());
                        if (VERSION.SDK_INT >= 9) {
                            jSONObject.put("a_mid", sensor.getMinDelay());
                        }
                        if (VERSION.SDK_INT >= 21) {
                            jSONObject.put("a_mad", sensor.getMaxDelay());
                        }
                        jSONObject.put("ts", System.currentTimeMillis());
                    } catch (Exception e) {
                    }
                    jSONArray.put(jSONObject);
                }
            }
        }
        return jSONArray;
    }
}
