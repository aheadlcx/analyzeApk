package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class TaleAuthorHolder_ViewBinding implements Unbinder {
    private TaleAuthorHolder b;

    @UiThread
    public TaleAuthorHolder_ViewBinding(TaleAuthorHolder taleAuthorHolder, View view) {
        this.b = taleAuthorHolder;
        taleAuthorHolder.name = (AppCompatTextView) b.b(view, R.id.name, "field 'name'", AppCompatTextView.class);
        taleAuthorHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
    }

    @CallSuper
    public void a() {
        TaleAuthorHolder taleAuthorHolder = this.b;
        if (taleAuthorHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleAuthorHolder.name = null;
        taleAuthorHolder.avatar = null;
    }
}
