package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;

public class TaleSocialHolder_ViewBinding implements Unbinder {
    private TaleSocialHolder b;

    @UiThread
    public TaleSocialHolder_ViewBinding(TaleSocialHolder taleSocialHolder, View view) {
        this.b = taleSocialHolder;
        taleSocialHolder.comment = (AppCompatTextView) b.b(view, R.id.comment, "field 'comment'", AppCompatTextView.class);
        taleSocialHolder.tvShare = (AppCompatTextView) b.b(view, R.id.tvShare, "field 'tvShare'", AppCompatTextView.class);
        taleSocialHolder.postItemUpDownView = (PostItemUpDownView) b.b(view, R.id.like, "field 'postItemUpDownView'", PostItemUpDownView.class);
    }

    @CallSuper
    public void a() {
        TaleSocialHolder taleSocialHolder = this.b;
        if (taleSocialHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleSocialHolder.comment = null;
        taleSocialHolder.tvShare = null;
        taleSocialHolder.postItemUpDownView = null;
    }
}
