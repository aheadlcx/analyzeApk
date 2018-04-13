package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class TaleHeaderHolder_ViewBinding implements Unbinder {
    private TaleHeaderHolder b;

    @UiThread
    public TaleHeaderHolder_ViewBinding(TaleHeaderHolder taleHeaderHolder, View view) {
        this.b = taleHeaderHolder;
        taleHeaderHolder.theme_title = (AppCompatTextView) b.b(view, R.id.theme_title, "field 'theme_title'", AppCompatTextView.class);
        taleHeaderHolder.theme_count = (AppCompatTextView) b.b(view, R.id.theme_count, "field 'theme_count'", AppCompatTextView.class);
    }

    @CallSuper
    public void a() {
        TaleHeaderHolder taleHeaderHolder = this.b;
        if (taleHeaderHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleHeaderHolder.theme_title = null;
        taleHeaderHolder.theme_count = null;
    }
}
