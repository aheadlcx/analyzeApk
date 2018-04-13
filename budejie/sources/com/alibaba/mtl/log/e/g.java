package com.alibaba.mtl.log.e;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.Comparator;

public class g {
    private static g a = new g();
    /* renamed from: a */
    private a f40a = new a();
    /* renamed from: a */
    private b f41a = new b();

    private class a implements Comparator<String> {
        final /* synthetic */ g b;

        private a(g gVar) {
            this.b = gVar;
        }

        public int compare(String str, String str2) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return 0;
            }
            return str.compareTo(str2);
        }
    }

    private class b implements Comparator<String> {
        final /* synthetic */ g b;

        private b(g gVar) {
            this.b = gVar;
        }

        public int compare(String str, String str2) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return 0;
            }
            return str.compareTo(str2) * -1;
        }
    }

    private g() {
    }

    public static g a() {
        return a;
    }

    public String[] a(String[] strArr, boolean z) {
        Comparator comparator;
        if (z) {
            comparator = this.f40a;
        } else {
            comparator = this.f41a;
        }
        if (comparator == null || strArr == null || strArr.length <= 0) {
            return null;
        }
        Arrays.sort(strArr, comparator);
        return strArr;
    }
}
