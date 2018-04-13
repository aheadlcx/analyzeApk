package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class TaleWebImageHolder_ViewBinding implements Unbinder {
    private TaleWebImageHolder b;

    @UiThread
    public TaleWebImageHolder_ViewBinding(TaleWebImageHolder taleWebImageHolder, View view) {
        this.b = taleWebImageHolder;
        taleWebImageHolder.image = (WebImageView) b.b(view, R.id.image, "field 'image'", WebImageView.class);
        taleWebImageHolder.tv_loading = (TextView) b.b(view, R.id.tv_loading, "field 'tv_loading'", TextView.class);
    }

    @CallSuper
    public void a() {
        TaleWebImageHolder taleWebImageHolder = this.b;
        if (taleWebImageHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleWebImageHolder.image = null;
        taleWebImageHolder.tv_loading = null;
    }
}
