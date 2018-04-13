package com.qiushibaike.statsdk.common;

import android.content.Context;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.qiushibaike.statsdk.L;
import com.qiushibaike.statsdk.StatSDK;
import com.qiushibaike.statsdk.common.security.AESUtil;
import com.qiushibaike.statsdk.common.security.Base64;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public final class DeviceId {
    public static String getDeviceID(Context context) {
        String md5;
        try {
            a(context, "android.permission.WRITE_SETTINGS");
            a(context, "android.permission.READ_PHONE_STATE");
            a(context, "android.permission.WRITE_EXTERNAL_STORAGE");
        } catch (SecurityException e) {
        }
        String str = "";
        try {
            str = System.getString(context.getContentResolver(), "qb_imei");
            if (str == null) {
                str = getIMEI(context);
            }
            System.putString(context.getContentResolver(), "qb_imei", str);
        } catch (Exception e2) {
        }
        String androidId = getAndroidId(context);
        String str2 = "";
        try {
            str2 = System.getString(context.getContentResolver(), "com.qiushibaike.deviceid");
        } catch (SecurityException e3) {
        }
        if (TextUtils.isEmpty(str2)) {
            md5 = MD5Util.md5(("com.qiushibaike" + str + androidId).getBytes(), true);
            try {
                str2 = System.getString(context.getContentResolver(), md5);
            } catch (SecurityException e4) {
            }
            String str3;
            if (TextUtils.isEmpty(str2)) {
                str3 = md5;
                md5 = str2;
                str2 = str3;
            } else {
                try {
                    System.putString(context.getContentResolver(), "com.qiushibaike.deviceid", str2);
                    a(str, str2);
                    str3 = md5;
                    md5 = str2;
                    str2 = str3;
                } catch (SecurityException e5) {
                    str3 = md5;
                    md5 = str2;
                    str2 = str3;
                }
            }
        } else {
            md5 = str2;
            str2 = null;
        }
        if (TextUtils.isEmpty(md5)) {
            md5 = a(str);
            if (!TextUtils.isEmpty(md5)) {
                try {
                    System.putString(context.getContentResolver(), str2, md5);
                    System.putString(context.getContentResolver(), "com.qiushibaike.deviceid", md5);
                } catch (SecurityException e6) {
                }
            }
        }
        if (TextUtils.isEmpty(md5)) {
            md5 = MD5Util.md5((str + androidId + UUID.randomUUID().toString()).getBytes(), true);
            try {
                System.putString(context.getContentResolver(), str2, md5);
                System.putString(context.getContentResolver(), "com.qiushibaike.deviceid", md5);
            } catch (SecurityException e7) {
            }
            a(str, md5);
        }
        return md5;
    }

    private static void a(Context context, String str) {
        if ((context.checkCallingOrSelfPermission(str) == 0 ? 1 : null) == null) {
            String str2 = "Permission Denial: requires permission " + str;
            L.e(StatSDK.class.getSimpleName(), str2);
            throw new SecurityException(str2);
        }
    }

    public static String getIMEI(Context context) {
        String str = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return str;
            }
            String deviceId = telephonyManager.getDeviceId();
            if (deviceId != null) {
                return deviceId;
            }
            try {
                return "";
            } catch (SecurityException e) {
                return deviceId;
            }
        } catch (SecurityException e2) {
            return str;
        }
    }

    public static String getAndroidId(Context context) {
        try {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (string == null) {
                return "";
            }
            return string;
        } catch (SecurityException e) {
            return "";
        }
    }

    private static void a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("=");
            stringBuilder.append(str2);
            File file = new File(Environment.getExternalStorageDirectory(), "qsbk.app/.cuid");
            try {
                new File(file.getParent()).mkdirs();
                FileWriter fileWriter = new FileWriter(file, false);
                fileWriter.write(Base64.encode(AESUtil.encrypt("xiaojdmg20050101", "xiaojdmg20050101", stringBuilder.toString().getBytes()), "utf-8"));
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
            } catch (Exception e2) {
            }
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(Environment.getExternalStorageDirectory(), "qsbk.app/.cuid")));
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
                stringBuilder.append("\r\n");
            }
            bufferedReader.close();
            String[] split = new String(AESUtil.decrypt("xiaojdmg20050101", "xiaojdmg20050101", Base64.decode(stringBuilder.toString().getBytes()))).split("=");
            if (split != null && split.length == 2 && str.equals(split[0])) {
                return split[1];
            }
            return str2;
        } catch (FileNotFoundException e) {
            return str2;
        } catch (IOException e2) {
            return str2;
        } catch (Exception e3) {
            return str2;
        }
    }
}
