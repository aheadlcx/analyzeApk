package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class TaleCommentTextHolder_ViewBinding implements Unbinder {
    private TaleCommentTextHolder b;

    @UiThread
    public TaleCommentTextHolder_ViewBinding(TaleCommentTextHolder taleCommentTextHolder, View view) {
        this.b = taleCommentTextHolder;
        taleCommentTextHolder.comment_holder = (AppCompatTextView) b.b(view, R.id.comment_holder, "field 'comment_holder'", AppCompatTextView.class);
    }

    @CallSuper
    public void a() {
        TaleCommentTextHolder taleCommentTextHolder = this.b;
        if (taleCommentTextHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleCommentTextHolder.comment_holder = null;
    }
}
