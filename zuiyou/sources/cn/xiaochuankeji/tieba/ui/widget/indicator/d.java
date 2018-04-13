package cn.xiaochuankeji.tieba.ui.widget.indicator;

import android.annotation.TargetApi;
import java.util.List;

@TargetApi(11)
public class d {
    public static n a(List<n> list, int i) {
        if (i >= 0 && i <= list.size() - 1) {
            return (n) list.get(i);
        }
        n nVar;
        n nVar2 = new n();
        if (i < 0) {
            nVar = (n) list.get(0);
        } else {
            i = (i - list.size()) + 1;
            nVar = (n) list.get(list.size() - 1);
        }
        nVar2.a = nVar.a + (nVar.a() * i);
        nVar2.b = nVar.b;
        nVar2.c = nVar.c + (nVar.a() * i);
        nVar2.d = nVar.d;
        nVar2.e = nVar.e + (nVar.a() * i);
        nVar2.f = nVar.f;
        nVar2.g = nVar.g + (nVar.a() * i);
        nVar2.h = nVar.h;
        return nVar2;
    }
}
