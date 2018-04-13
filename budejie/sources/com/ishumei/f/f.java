package com.ishumei.f;

import java.util.zip.Inflater;

public class f {
    public static byte[] a(byte[] bArr) {
        Object obj = new byte[4096];
        Inflater inflater = new Inflater();
        inflater.setInput(bArr, 0, bArr.length);
        int inflate = inflater.inflate(obj, 0, 4096);
        inflater.end();
        Object obj2 = new byte[inflate];
        System.arraycopy(obj, 0, obj2, 0, inflate);
        return obj2;
    }
}
