package com.facebook.cache.common;

import com.facebook.common.util.b;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public final class c {
    public static List<String> a(b bVar) {
        try {
            if (bVar instanceof d) {
                List b = ((d) bVar).b();
                List<String> arrayList = new ArrayList(b.size());
                for (int i = 0; i < b.size(); i++) {
                    arrayList.add(c((b) b.get(i)));
                }
                return arrayList;
            }
            List<String> arrayList2 = new ArrayList(1);
            arrayList2.add(c(bVar));
            return arrayList2;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String b(b bVar) {
        try {
            if (bVar instanceof d) {
                return c((b) ((d) bVar).b().get(0));
            }
            return c(bVar);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static String c(b bVar) throws UnsupportedEncodingException {
        return b.a(bVar.a().getBytes("UTF-8"));
    }
}
