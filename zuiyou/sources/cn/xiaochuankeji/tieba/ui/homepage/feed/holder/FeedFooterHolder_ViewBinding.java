package cn.xiaochuankeji.tieba.ui.homepage.feed.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class FeedFooterHolder_ViewBinding implements Unbinder {
    private FeedFooterHolder b;

    @UiThread
    public FeedFooterHolder_ViewBinding(FeedFooterHolder feedFooterHolder, View view) {
        this.b = feedFooterHolder;
        feedFooterHolder.reload = b.a(view, R.id.reload, "field 'reload'");
    }

    @CallSuper
    public void a() {
        FeedFooterHolder feedFooterHolder = this.b;
        if (feedFooterHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        feedFooterHolder.reload = null;
    }
}
