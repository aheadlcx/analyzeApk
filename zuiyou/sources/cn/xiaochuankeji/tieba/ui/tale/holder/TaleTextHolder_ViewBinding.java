package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class TaleTextHolder_ViewBinding implements Unbinder {
    private TaleTextHolder b;

    @UiThread
    public TaleTextHolder_ViewBinding(TaleTextHolder taleTextHolder, View view) {
        this.b = taleTextHolder;
        taleTextHolder.text = (AppCompatTextView) b.b(view, R.id.text, "field 'text'", AppCompatTextView.class);
    }

    @CallSuper
    public void a() {
        TaleTextHolder taleTextHolder = this.b;
        if (taleTextHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleTextHolder.text = null;
    }
}
