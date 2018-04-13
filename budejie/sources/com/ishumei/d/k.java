package com.ishumei.d;

import android.content.Context;
import com.ishumei.f.c;
import com.ishumei.f.d;
import java.lang.reflect.Method;
import java.util.HashMap;

public class k {
    private static k a = null;

    public static k a() {
        if (a == null) {
            synchronized (k.class) {
                if (a == null) {
                    a = new k();
                }
            }
        }
        return a;
    }

    public String a(String str) {
        String str2 = "";
        try {
            Class loadClass = Context.class.getClassLoader().loadClass(d.g("9e919b8d90969bd1908cd1ac868c8b9a92af8d908f9a8d8b969a8c"));
            Method method = loadClass.getMethod(d.g("989a8b"), new Class[]{String.class});
            method.setAccessible(true);
            String str3 = (String) method.invoke(loadClass, new Object[]{str});
            if (str3 != null) {
                return str3;
            }
            try {
                return "";
            } catch (Exception e) {
                return str3;
            }
        } catch (Exception e2) {
            return str2;
        }
    }

    public HashMap<String, String> b() {
        HashMap<String, String> hashMap = new HashMap();
        try {
            Class loadClass = Context.class.getClassLoader().loadClass(d.g("9e919b8d90969bd1908cd1ac868c8b9a92af8d908f9a8d8b969a8c"));
            Method method = loadClass.getMethod(d.g("989a8b"), new Class[]{String.class});
            method.setAccessible(true);
            String[] strArr = new String[]{d.g("8d90d19b9a9d8a98989e9d939a"), d.g("8d90d18c9a8d969e939190"), d.g("8d90d19d90908bd1979e8d9b889e8d9a"), d.g("8d90d19d90908bd18c9a8d969e939190"), d.g("8d90d1949a8d919a93d18e9a928a"), d.g("8d90d1949a8d919a93d19e919b8d90969b9d90908bd1979e8d9b889e8d9a"), d.g("8d90d18f8d909b8a9c8bd19b9a89969c9a"), d.g("8d90d19d8a96939bd1abbeb88c"), d.g("8d90d19d8a96939bd19b9e8b9ad18a8b9c"), d.g("988c92d1919a8b88908d94d18b868f9a"), d.g("988c92d18c9692d18c8b9e8b9a"), d.g("988c92d19b9a89969c9ad18c91"), d.g("8f9a8d8c968c8bd18c868cd19c908a918b8d86"), d.g("8f9a8d8c968c8bd18c868cd1939e91988a9e989a"), d.g("8c868cd18a8c9dd18c8b9e8b9a"), d.g("919a8bd19b918cce"), d.g("919a8bd197908c8b919e929a"), d.g("919a8bd19a8b97cfd19888"), d.g("919a8bd1988f8d8cd193909c9e93d2968f")};
            for (int i = 0; i < 19; i++) {
                String str = (String) method.invoke(loadClass, new Object[]{strArr[i]});
                if (!(str == null || str.isEmpty())) {
                    hashMap.put(r6, str);
                }
            }
        } catch (Throwable e) {
            c.a(e);
        }
        return hashMap;
    }
}
