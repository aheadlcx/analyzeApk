package com.crashlytics.android.internal;

import android.os.Build;
import android.text.TextUtils;
import com.crashlytics.android.Crashlytics;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

enum br {
    X86_32,
    X86_64,
    ARM_UNKNOWN,
    PPC,
    PPC64,
    ARMV6,
    ARMV7,
    h,
    ARMV7S,
    ARM64;
    
    private static final Map<String, br> k = null;

    static {
        Map hashMap = new HashMap(4);
        k = hashMap;
        hashMap.put("armeabi-v7a", ARMV7);
        k.put("armeabi", ARMV6);
        k.put("x86", X86_32);
    }

    static br a() {
        Object obj = Build.CPU_ABI;
        if (TextUtils.isEmpty(obj)) {
            v.a().b().a(Crashlytics.TAG, "Architecture#getValue()::Build.CPU_ABI returned null or empty");
            return h;
        }
        br brVar = (br) k.get(obj.toLowerCase(Locale.US));
        if (brVar == null) {
            return h;
        }
        return brVar;
    }
}
