package com.nostra13.universalimageloader.b;

import com.nostra13.universalimageloader.core.assist.c;
import java.util.Comparator;

public final class f {
    public static String a(String str, c cVar) {
        return "_" + cVar.a() + "x" + cVar.b();
    }

    public static Comparator<String> a() {
        return new Comparator<String>() {
            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((String) obj, (String) obj2);
            }

            public int a(String str, String str2) {
                return str.substring(0, str.lastIndexOf("_")).compareTo(str2.substring(0, str2.lastIndexOf("_")));
            }
        };
    }
}
