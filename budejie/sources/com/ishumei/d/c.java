package com.ishumei.d;

import com.ishumei.f.d;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.ListIterator;

public class c {
    private static c a = null;

    public static c a() {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c();
                }
            }
        }
        return a;
    }

    public HashMap<String, String> b() {
        HashMap<String, String> hashMap = new HashMap();
        try {
            ListIterator listIterator = new net.vidageek.a.b.c().b(d.g("9e919b8d90969bd1908cd1bd8a96939b")).c().a().listIterator();
            while (listIterator.hasNext()) {
                Field field = (Field) listIterator.next();
                field.setAccessible(true);
                CharSequence toLowerCase = field.getName().toLowerCase();
                if (d.g("9d909e8d9bd39b9a89969c9ad3979e8d9b889e8d9ad392909b9a93d38c9a8d969e93d39d9e919bd39d8d9e919bd39b968c8f939e86d3929e918a999e9c8b8a8d9a8dd38f8d909b8a9c8bd3999691989a8d8f8d96918bd39c8f8aa09e9d96d39c8f8aa09e9d96cd").contains(toLowerCase)) {
                    hashMap.put(toLowerCase, field.get(null).toString());
                }
            }
        } catch (Exception e) {
        }
        return hashMap;
    }
}
