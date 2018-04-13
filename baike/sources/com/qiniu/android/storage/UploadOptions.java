package com.qiniu.android.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class UploadOptions {
    final Map<String, String> a;
    final String b;
    final boolean c;
    final UpProgressHandler d;
    final UpCancellationSignal e;
    final NetReadyHandler f;

    public UploadOptions(Map<String, String> map, String str, boolean z, UpProgressHandler upProgressHandler, UpCancellationSignal upCancellationSignal) {
        this(map, str, z, upProgressHandler, upCancellationSignal, null);
    }

    public UploadOptions(Map<String, String> map, String str, boolean z, UpProgressHandler upProgressHandler, UpCancellationSignal upCancellationSignal, NetReadyHandler netReadyHandler) {
        this.a = a((Map) map);
        this.b = a(str);
        this.c = z;
        if (upProgressHandler == null) {
            upProgressHandler = new s(this);
        }
        this.d = upProgressHandler;
        if (upCancellationSignal == null) {
            upCancellationSignal = new t(this);
        }
        this.e = upCancellationSignal;
        if (netReadyHandler == null) {
            netReadyHandler = new u(this);
        }
        this.f = netReadyHandler;
    }

    private static Map<String, String> a(Map<String, String> map) {
        Map<String, String> hashMap = new HashMap();
        if (map == null) {
            return hashMap;
        }
        for (Entry entry : map.entrySet()) {
            if (!(!((String) entry.getKey()).startsWith("x:") || entry.getValue() == null || ((String) entry.getValue()).equals(""))) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return hashMap;
    }

    static UploadOptions a() {
        return new UploadOptions(null, null, false, null, null);
    }

    private static String a(String str) {
        if (str == null || str.equals("")) {
            return "application/octet-stream";
        }
        return str;
    }
}
