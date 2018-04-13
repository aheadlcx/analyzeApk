package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.g.c.d;
import com.liulishuo.filedownloader.g.f;

public class b implements d {
    public int a(int i, String str, String str2, boolean z) {
        return a(str, str2, z);
    }

    public int a(String str, String str2, boolean z) {
        if (z) {
            return f.e(f.a("%sp%s@dir", str, str2)).hashCode();
        }
        return f.e(f.a("%sp%s", str, str2)).hashCode();
    }
}
