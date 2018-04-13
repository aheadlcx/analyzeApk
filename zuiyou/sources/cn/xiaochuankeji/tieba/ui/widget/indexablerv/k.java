package cn.xiaochuankeji.tieba.ui.widget.indexablerv;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import java.util.List;

public class k<T extends f> extends e<T> {
    private c<T> d;

    public k(c<T> cVar, String str, String str2, List<T> list) {
        super(str, str2, list);
        this.d = cVar;
    }

    public int a() {
        return Integer.MAX_VALUE;
    }

    public ViewHolder a(ViewGroup viewGroup) {
        return this.d.b(viewGroup);
    }

    public void a(ViewHolder viewHolder, T t) {
        this.d.a(viewHolder, (f) t);
    }
}
