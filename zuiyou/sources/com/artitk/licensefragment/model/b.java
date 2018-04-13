package com.artitk.licensefragment.model;

import android.content.Context;
import com.artitk.licensefragment.a.a;
import java.util.HashMap;

public class b {
    private static b b = new b();
    private HashMap<LicenseType, String> a = new HashMap();

    public static b a() {
        return b;
    }

    public String a(Context context, LicenseType licenseType) {
        if (this.a.containsKey(licenseType)) {
            return (String) this.a.get(licenseType);
        }
        String a = new a(context).a(licenseType);
        this.a.put(licenseType, a);
        return a;
    }
}
