package com.ta.utdid2.b.a;

import android.annotation.TargetApi;
import android.util.Base64;

public class c {
    @TargetApi(8)
    public static String encodeToString(byte[] bArr, int i) {
        return Base64.encodeToString(bArr, i);
    }
}
