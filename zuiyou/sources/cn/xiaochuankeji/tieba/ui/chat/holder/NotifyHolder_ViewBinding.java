package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class NotifyHolder_ViewBinding implements Unbinder {
    private NotifyHolder b;

    @UiThread
    public NotifyHolder_ViewBinding(NotifyHolder notifyHolder, View view) {
        this.b = notifyHolder;
        notifyHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        notifyHolder.layout_right = b.a(view, R.id.layout_right, "field 'layout_right'");
        notifyHolder.thumb = (WebImageView) b.b(view, R.id.thumbnail, "field 'thumb'", WebImageView.class);
        notifyHolder.brief = (TextView) b.b(view, R.id.brief, "field 'brief'", TextView.class);
        notifyHolder.brief_type = (ImageView) b.b(view, R.id.brief_type, "field 'brief_type'", ImageView.class);
        notifyHolder.ugc_tag = (WebImageView) b.b(view, R.id.ugc_tag, "field 'ugc_tag'", WebImageView.class);
        notifyHolder.main = (TextView) b.b(view, R.id.main, "field 'main'", TextView.class);
        notifyHolder.icon_group = (LinearLayout) b.b(view, R.id.icon_group, "field 'icon_group'", LinearLayout.class);
        notifyHolder.likes = (TextView) b.b(view, R.id.likes, "field 'likes'", TextView.class);
        notifyHolder.ugc = (TextView) b.b(view, R.id.ugc, "field 'ugc'", TextView.class);
        notifyHolder.review = (TextView) b.b(view, R.id.review, "field 'review'", TextView.class);
        notifyHolder.vote = (TextView) b.b(view, R.id.vote, "field 'vote'", TextView.class);
        notifyHolder.hug = (TextView) b.b(view, R.id.hug, "field 'hug'", TextView.class);
        notifyHolder.share = (TextView) b.b(view, R.id.share, "field 'share'", TextView.class);
        notifyHolder.danmaku = (TextView) b.b(view, R.id.danmaku, "field 'danmaku'", TextView.class);
        notifyHolder.more = (ImageView) b.b(view, R.id.more, "field 'more'", ImageView.class);
        notifyHolder.divider = b.a(view, R.id.divider, "field 'divider'");
    }

    @CallSuper
    public void a() {
        NotifyHolder notifyHolder = this.b;
        if (notifyHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        notifyHolder.avatar = null;
        notifyHolder.layout_right = null;
        notifyHolder.thumb = null;
        notifyHolder.brief = null;
        notifyHolder.brief_type = null;
        notifyHolder.ugc_tag = null;
        notifyHolder.main = null;
        notifyHolder.icon_group = null;
        notifyHolder.likes = null;
        notifyHolder.ugc = null;
        notifyHolder.review = null;
        notifyHolder.vote = null;
        notifyHolder.hug = null;
        notifyHolder.share = null;
        notifyHolder.danmaku = null;
        notifyHolder.more = null;
        notifyHolder.divider = null;
    }
}
