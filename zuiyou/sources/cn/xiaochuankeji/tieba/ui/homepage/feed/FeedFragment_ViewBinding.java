package cn.xiaochuankeji.tieba.ui.homepage.feed;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class FeedFragment_ViewBinding implements Unbinder {
    private FeedFragment b;

    @UiThread
    public FeedFragment_ViewBinding(FeedFragment feedFragment, View view) {
        this.b = feedFragment;
        feedFragment.refresh = (SmartRefreshLayout) b.b(view, R.id.refresh, "field 'refresh'", SmartRefreshLayout.class);
        feedFragment.recycler = (RecyclerView) b.b(view, R.id.recycler, "field 'recycler'", RecyclerView.class);
        feedFragment.tips = (PostLoadedTipsView) b.b(view, R.id.tips, "field 'tips'", PostLoadedTipsView.class);
    }

    @CallSuper
    public void a() {
        FeedFragment feedFragment = this.b;
        if (feedFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        feedFragment.refresh = null;
        feedFragment.recycler = null;
        feedFragment.tips = null;
    }
}
