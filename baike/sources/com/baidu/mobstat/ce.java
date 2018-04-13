package com.baidu.mobstat;

import java.util.Comparator;

class ce implements Comparator<String> {
    final /* synthetic */ cc a;

    ce(cc ccVar) {
        this.a = ccVar;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((String) obj, (String) obj2);
    }

    public int a(String str, String str2) {
        return str.compareTo(str2);
    }
}
