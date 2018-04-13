package com.iflytek.cloud.thirdparty;

import android.util.DisplayMetrics;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

public class bt {
    public static DisplayMetrics a = null;

    public static String a(long j) {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS").format(Long.valueOf(j));
    }

    public static byte[] a(String str) {
        byte[] bArr;
        UnsupportedEncodingException e;
        try {
            byte[] bytes = str.getBytes("utf-8");
            bArr = new byte[(bytes.length + 3)];
            try {
                bArr[0] = (byte) -17;
                bArr[1] = (byte) -69;
                bArr[2] = (byte) -65;
                for (int i = 3; i < bArr.length; i++) {
                    bArr[i] = bytes[i - 3];
                }
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                e.printStackTrace();
                return bArr;
            }
        } catch (UnsupportedEncodingException e3) {
            UnsupportedEncodingException unsupportedEncodingException = e3;
            bArr = null;
            e = unsupportedEncodingException;
            e.printStackTrace();
            return bArr;
        }
        return bArr;
    }

    public static byte[] a(String str, String str2) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        if (str.length() > 0) {
            Object bytes = str.getBytes(str2);
            Object obj = new byte[(bytes.length + 1)];
            System.arraycopy(bytes, 0, obj, 0, bytes.length);
            obj[bytes.length] = null;
            return obj;
        }
        return new byte[]{(byte) 0};
    }

    public static byte[] a(byte[] bArr) {
        Object obj = new byte[(bArr.length + 3)];
        obj[0] = (byte) -17;
        obj[1] = (byte) -69;
        obj[2] = (byte) -65;
        System.arraycopy(bArr, 0, obj, 3, bArr.length);
        return obj;
    }
}
