package cn.xiaochuankeji.tieba.ui.homepage.feed.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class FeedMemberHolder_ViewBinding implements Unbinder {
    private FeedMemberHolder b;

    @UiThread
    public FeedMemberHolder_ViewBinding(FeedMemberHolder feedMemberHolder, View view) {
        this.b = feedMemberHolder;
        feedMemberHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        feedMemberHolder.nickname = (AppCompatTextView) b.b(view, R.id.nickname, "field 'nickname'", AppCompatTextView.class);
        feedMemberHolder.follow = (AppCompatTextView) b.b(view, R.id.follow, "field 'follow'", AppCompatTextView.class);
    }

    @CallSuper
    public void a() {
        FeedMemberHolder feedMemberHolder = this.b;
        if (feedMemberHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        feedMemberHolder.avatar = null;
        feedMemberHolder.nickname = null;
        feedMemberHolder.follow = null;
    }
}
