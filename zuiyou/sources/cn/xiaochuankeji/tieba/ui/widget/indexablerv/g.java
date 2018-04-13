package cn.xiaochuankeji.tieba.ui.widget.indexablerv;

import java.util.Comparator;

class g<T extends f> implements Comparator<b<T>> {
    g() {
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((b) obj, (b) obj2);
    }

    public int a(b<T> bVar, b<T> bVar2) {
        return bVar.a().compareTo(bVar2.a());
    }
}
