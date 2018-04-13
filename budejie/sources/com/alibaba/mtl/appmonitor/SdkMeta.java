package com.alibaba.mtl.appmonitor;

import android.content.Context;
import com.alibaba.mtl.log.a;
import com.alibaba.mtl.log.e.i;
import java.util.HashMap;
import java.util.Map;

public class SdkMeta {
    public static final String SDK_VERSION = "2.5.1_for_bc";
    private static final Map<String, String> b = new HashMap();

    public static Map<String, String> getSDKMetaData() {
        if (a.getContext() != null) {
        }
        if (!b.containsKey("sdk-version")) {
            b.put("sdk-version", SDK_VERSION);
        }
        return b;
    }

    static {
        b.put("sdk-version", SDK_VERSION);
    }

    public static void setExtra(Map<String, String> map) {
        if (map != null) {
            b.putAll(map);
        }
    }

    public static String getString(Context context, String str) {
        if (context == null) {
            return null;
        }
        int identifier;
        try {
            identifier = context.getResources().getIdentifier(str, "string", context.getPackageName());
        } catch (Throwable th) {
            i.a("SdkMeta", "getString Id error", th);
            identifier = 0;
        }
        if (identifier != 0) {
            return context.getString(identifier);
        }
        return null;
    }
}
