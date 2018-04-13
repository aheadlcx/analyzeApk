package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.recommend.widget.MultiDraweeView;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.updown.CommentItemUpDownView;

public class TaleCommentHolder_ViewBinding implements Unbinder {
    private TaleCommentHolder b;

    @UiThread
    public TaleCommentHolder_ViewBinding(TaleCommentHolder taleCommentHolder, View view) {
        this.b = taleCommentHolder;
        taleCommentHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        taleCommentHolder.name = (AppCompatTextView) b.b(view, R.id.name, "field 'name'", AppCompatTextView.class);
        taleCommentHolder.time = (AppCompatTextView) b.b(view, R.id.time, "field 'time'", AppCompatTextView.class);
        taleCommentHolder.like = (CommentItemUpDownView) b.b(view, R.id.like, "field 'like'", CommentItemUpDownView.class);
        taleCommentHolder.review = (ExpandableTextView) b.b(view, R.id.review, "field 'review'", ExpandableTextView.class);
        taleCommentHolder.image = (MultiDraweeView) b.b(view, R.id.image, "field 'image'", MultiDraweeView.class);
        taleCommentHolder.iv_owner = (ImageView) b.b(view, R.id.iv_owner, "field 'iv_owner'", ImageView.class);
    }

    @CallSuper
    public void a() {
        TaleCommentHolder taleCommentHolder = this.b;
        if (taleCommentHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleCommentHolder.avatar = null;
        taleCommentHolder.name = null;
        taleCommentHolder.time = null;
        taleCommentHolder.like = null;
        taleCommentHolder.review = null;
        taleCommentHolder.image = null;
        taleCommentHolder.iv_owner = null;
    }
}
