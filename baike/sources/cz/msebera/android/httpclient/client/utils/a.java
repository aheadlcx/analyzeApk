package cz.msebera.android.httpclient.client.utils;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

final class a extends ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> {
    a() {
    }

    protected /* synthetic */ Object initialValue() {
        return a();
    }

    protected SoftReference<Map<String, SimpleDateFormat>> a() {
        return new SoftReference(new HashMap());
    }
}
