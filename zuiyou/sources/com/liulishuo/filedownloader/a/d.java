package com.liulishuo.filedownloader.a;

import com.liulishuo.filedownloader.download.c;
import com.liulishuo.filedownloader.g.f;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class d {
    public static b a(Map<String, List<String>> map, b bVar, List<String> list) throws IOException, IllegalAccessException {
        int e = bVar.e();
        String a = bVar.a("Location");
        Collection arrayList = new ArrayList();
        int i = e;
        String str = a;
        int i2 = 0;
        while (a(i)) {
            if (str == null) {
                throw new IllegalAccessException(f.a("receive %d (redirect) but the location is null with response [%s]", Integer.valueOf(i), bVar.c()));
            }
            if (com.liulishuo.filedownloader.g.d.a) {
                com.liulishuo.filedownloader.g.d.c(d.class, "redirect to %s with %d, %s", str, Integer.valueOf(i), arrayList);
            }
            bVar.f();
            bVar = a(map, str);
            arrayList.add(str);
            bVar.d();
            i = bVar.e();
            str = bVar.a("Location");
            i2++;
            if (i2 >= 10) {
                throw new IllegalAccessException(f.a("redirect too many times! %s", arrayList));
            }
        }
        if (list != null) {
            list.addAll(arrayList);
        }
        return bVar;
    }

    private static boolean a(int i) {
        return i == 301 || i == 302 || i == 303 || i == 300 || i == TinkerReport.KEY_LOADED_MISSING_DEX_OPT || i == TinkerReport.KEY_LOADED_MISSING_RES;
    }

    private static b a(Map<String, List<String>> map, String str) throws IOException {
        b a = c.a().a(str);
        for (Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            List<String> list = (List) entry.getValue();
            if (list != null) {
                for (String a2 : list) {
                    a.a(str2, a2);
                }
            }
        }
        return a;
    }
}
