package com.alibaba.mtl.log.e;

import com.budejie.www.R$styleable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class j {
    public static char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            stringBuilder.append(a[(bArr[i] & R$styleable.Theme_Custom_shape_cmt_like4_bg) >>> 4]);
            stringBuilder.append(a[bArr[i] & 15]);
        }
        return stringBuilder.toString();
    }

    public static String b(byte[] bArr) {
        byte[] a = a(bArr);
        if (a != null) {
            return a(a);
        }
        return "0000000000000000";
    }

    /* renamed from: a */
    public static byte[] m25a(byte[] bArr) {
        if (bArr != null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(bArr);
                return instance.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
