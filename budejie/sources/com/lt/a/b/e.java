package com.lt.a.b;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class e {
    public static String a(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((String) obj, (String) obj2);
            }

            public int a(String str, String str2) {
                return str.compareTo(str2);
            }
        });
        StringBuffer stringBuffer = new StringBuffer();
        for (String append : list) {
            stringBuffer.append(append);
        }
        return stringBuffer.toString();
    }
}
