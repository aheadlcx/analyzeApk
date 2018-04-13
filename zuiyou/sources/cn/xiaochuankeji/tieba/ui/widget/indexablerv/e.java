package cn.xiaochuankeji.tieba.ui.widget.indexablerv;

import java.util.List;

public abstract class e<T> extends a<T> {

    public interface a<T> extends a<T> {
    }

    public e(String str, String str2, List<T> list) {
        super(str, str2, list);
    }

    int b() {
        return 1;
    }

    public void a(a<T> aVar) {
        this.b = aVar;
    }
}
