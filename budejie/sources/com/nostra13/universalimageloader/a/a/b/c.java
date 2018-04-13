package com.nostra13.universalimageloader.a.a.b;

import com.nostra13.universalimageloader.b.e;
import java.math.BigInteger;
import java.security.MessageDigest;

public class c implements a {
    public String a(String str) {
        return new BigInteger(a(str.getBytes())).abs().toString(36);
    }

    private byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            bArr2 = instance.digest();
        } catch (Throwable e) {
            e.a(e);
        }
        return bArr2;
    }
}
