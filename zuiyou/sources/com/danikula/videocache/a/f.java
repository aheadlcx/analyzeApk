package com.danikula.videocache.a;

import android.text.TextUtils;
import com.danikula.videocache.m;

public class f implements c {
    public String a(String str) {
        Object b = b(str);
        String d = m.d(str);
        return TextUtils.isEmpty(b) ? d : d + "." + b;
    }

    private String b(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return (lastIndexOf == -1 || lastIndexOf <= str.lastIndexOf(47) || (lastIndexOf + 2) + 4 <= str.length()) ? "" : str.substring(lastIndexOf + 1, str.length());
    }
}
