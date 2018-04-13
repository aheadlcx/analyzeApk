package com.budejie.www.activity.video;

import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashSet;

public class o {
    public static ArrayList<String> a = new ArrayList();
    static final char[] b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final HashSet<String> c = new HashSet();
    private static RectF d = new RectF();
    private static Path e = new Path();

    static {
        c.add(UriUtil.HTTP_SCHEME);
        c.add("https");
    }

    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo networkInfo : allNetworkInfo) {
            Object typeName = networkInfo.getTypeName();
            Log.d("SisterUtil", "net.getTypeName()=" + typeName);
            if (!TextUtils.isEmpty(typeName)) {
                String toUpperCase = typeName.toUpperCase();
                Log.d("SisterUtil", "typeName.toUpperCase()=" + toUpperCase);
                if ("WIFI".equals(toUpperCase) && networkInfo.isConnected()) {
                    return true;
                }
                if ("MOBILE".equals(toUpperCase) && networkInfo.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String a(String str) {
        int i = 0;
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            byte[] digest = instance.digest();
            int length = digest.length;
            char[] cArr = new char[(length * 2)];
            int i2 = 0;
            while (i < length) {
                byte b = digest[i];
                int i3 = i2 + 1;
                cArr[i2] = b[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr[i3] = b[b & 15];
                i++;
            }
            return new String(cArr);
        } catch (Exception e) {
            return null;
        }
    }
}
