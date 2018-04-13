package cn.xiaochuankeji.tieba.ui.utils;

import android.app.Activity;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.b;

public class a implements b {
    private a a;
    private SDBottomSheet b;

    public interface a {
        void a(int i);
    }

    public a(Activity activity, a aVar) {
        this.b = new SDBottomSheet(activity, this);
        this.b.a(this.b.c(), null);
        this.a = aVar;
    }

    public void a() {
        this.b.b();
    }

    public void a(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }
}
