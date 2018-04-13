package com.iflytek.cloud.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.iflytek.msc.MSC;
import java.util.Random;

public class VerifierUtil {
    public static Bitmap ARGB2Gray(Bitmap bitmap) {
        if (bitmap.getConfig() != Config.ARGB_8888) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ALPHA_8);
        return !a(bitmap, createBitmap) ? null : createBitmap;
    }

    private static boolean a(Bitmap bitmap, Bitmap bitmap2) {
        return MSC.doARGB2Gray(bitmap, bitmap2);
    }

    public static String generateNumberPassword(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        String str = "023456789".charAt(random.nextInt("023456789".length())) + "";
        stringBuffer.append(str);
        for (int i2 = 0; i2 < i - 1; i2++) {
            Boolean valueOf = Boolean.valueOf(false);
            while (!valueOf.booleanValue()) {
                str = "023456789".charAt(random.nextInt("023456789".length())) + "";
                valueOf = stringBuffer.indexOf(str) >= 0 ? Boolean.valueOf(false) : Integer.parseInt(new StringBuilder().append(stringBuffer.charAt(stringBuffer.length() + -1)).append("").toString()) * Integer.parseInt(str) == 10 ? Boolean.valueOf(false) : Boolean.valueOf(true);
            }
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public static int getBitmapsize(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
