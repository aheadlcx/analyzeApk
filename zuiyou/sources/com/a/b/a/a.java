package com.a.b.a;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.ahocorasick.a.c;

final class a {
    static final a a = new a();

    static final class a implements Comparator<org.ahocorasick.a.a> {
        a() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((org.ahocorasick.a.a) obj, (org.ahocorasick.a.a) obj2);
        }

        public int a(org.ahocorasick.a.a aVar, org.ahocorasick.a.a aVar2) {
            int i = -1;
            if (aVar.a() != aVar2.a()) {
                if (aVar.a() >= aVar2.a()) {
                    i = aVar.a() == aVar2.a() ? 0 : 1;
                }
                return i;
            } else if (aVar.c() < aVar2.c()) {
                return 1;
            } else {
                return aVar.c() == aVar2.c() ? 0 : -1;
            }
        }
    }

    static String a(String str, c cVar, List<g> list, String str2, h hVar) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int i;
        if (cVar == null || hVar == null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (i = 0; i < str.length(); i++) {
                stringBuffer.append(b.a(str.charAt(i)));
                if (i != str.length() - 1) {
                    stringBuffer.append(str2);
                }
            }
            return stringBuffer.toString();
        }
        List a = hVar.a(cVar.a(str));
        Collections.sort(a, a);
        StringBuffer stringBuffer2 = new StringBuffer();
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            if (i3 >= a.size() || i2 != ((org.ahocorasick.a.a) a.get(i3)).a()) {
                stringBuffer2.append(b.a(str.charAt(i2)));
                i = i2 + 1;
                i2 = i3;
            } else {
                String[] a2 = a(((org.ahocorasick.a.a) a.get(i3)).d(), list);
                for (i = 0; i < a2.length; i++) {
                    stringBuffer2.append(a2[i].toUpperCase());
                    if (i != a2.length - 1) {
                        stringBuffer2.append(str2);
                    }
                }
                i = ((org.ahocorasick.a.a) a.get(i3)).c() + i2;
                i2 = i3 + 1;
            }
            if (i != str.length()) {
                stringBuffer2.append(str2);
                i3 = i2;
                i2 = i;
            } else {
                i3 = i2;
                i2 = i;
            }
        }
        return stringBuffer2.toString();
    }

    static String[] a(String str, List<g> list) {
        if (list != null) {
            for (g gVar : list) {
                if (gVar != null && gVar.a() != null && gVar.a().contains(str)) {
                    return gVar.a(str);
                }
            }
        }
        throw new IllegalArgumentException("No pinyin dict contains word: " + str);
    }
}
