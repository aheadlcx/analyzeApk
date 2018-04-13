package cn.xiaochuankeji.tieba.common.e;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import java.util.Random;
import java.util.UUID;

public class a {
    public static String a(Context context) {
        String str = null;
        if (context != null) {
            try {
                String deviceId;
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                } else {
                    deviceId = null;
                }
                str = deviceId;
            } catch (Exception e) {
            }
        }
        if (TextUtils.isEmpty(str)) {
            return a();
        }
        return str;
    }

    public static String a() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nanoTime = (int) System.nanoTime();
        int nextInt = new Random().nextInt();
        int nextInt2 = new Random().nextInt();
        Object obj = new byte[16];
        System.arraycopy(b.a(currentTimeMillis), 0, obj, 0, 4);
        System.arraycopy(b.a(nanoTime), 0, obj, 4, 4);
        System.arraycopy(b.a(nextInt), 0, obj, 8, 4);
        System.arraycopy(b.a(nextInt2), 0, obj, 12, 4);
        String encodeToString = Base64.encodeToString(obj, 2);
        if (encodeToString.endsWith("\n")) {
            return encodeToString.substring(0, encodeToString.length() - 1);
        }
        return encodeToString;
    }

    public static String b() {
        UUID randomUUID = UUID.randomUUID();
        if (randomUUID == null) {
            return null;
        }
        return randomUUID.toString();
    }
}
