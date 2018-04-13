package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.bigImage.BigImageView;

public class TaleImageHolder_ViewBinding implements Unbinder {
    private TaleImageHolder b;

    @UiThread
    public TaleImageHolder_ViewBinding(TaleImageHolder taleImageHolder, View view) {
        this.b = taleImageHolder;
        taleImageHolder.image = (BigImageView) b.b(view, R.id.image, "field 'image'", BigImageView.class);
        taleImageHolder.tv_loading = (TextView) b.b(view, R.id.tv_loading, "field 'tv_loading'", TextView.class);
    }

    @CallSuper
    public void a() {
        TaleImageHolder taleImageHolder = this.b;
        if (taleImageHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleImageHolder.image = null;
        taleImageHolder.tv_loading = null;
    }
}
