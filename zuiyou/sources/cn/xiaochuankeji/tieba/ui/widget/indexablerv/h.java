package cn.xiaochuankeji.tieba.ui.widget.indexablerv;

import android.support.annotation.NonNull;
import java.util.Comparator;

class h<T extends f> implements Comparator<b<T>> {
    h() {
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((b) obj, (b) obj2);
    }

    public int a(b<T> bVar, b<T> bVar2) {
        String d = bVar.d();
        String d2 = bVar2.d();
        if (d == null) {
            d = "";
        }
        if (d2 == null) {
            d2 = "";
        }
        return a(d.trim(), d2.trim());
    }

    private int a(String str, String str2) {
        int i = 0;
        String a = a(str, 0);
        String a2 = a(str2, 0);
        while (a.equals(a2) && !a.equals("")) {
            i++;
            a = a(str, i);
            a2 = a(str2, i);
        }
        return a.compareTo(a2);
    }

    @NonNull
    private String a(String str, int i) {
        if (str.length() < i + 1) {
            return "";
        }
        if (i.c(str)) {
            return i.a(i.f(str).substring(i, i + 1));
        }
        return i.a(str.substring(i, i + 1));
    }
}
