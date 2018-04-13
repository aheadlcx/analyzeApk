package cn.xiaochuankeji.tieba.ui.homepage.feed.newfeed;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class FeedMainFragment_ViewBinding implements Unbinder {
    private FeedMainFragment b;

    @UiThread
    public FeedMainFragment_ViewBinding(FeedMainFragment feedMainFragment, View view) {
        this.b = feedMainFragment;
        feedMainFragment.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.feed_refresh_layout, "field 'refreshLayout'", SmartRefreshLayout.class);
        feedMainFragment.recyclerView = (RecyclerView) b.b(view, R.id.feed_recycler_view, "field 'recyclerView'", RecyclerView.class);
        feedMainFragment.tipsView = (PostLoadedTipsView) b.b(view, R.id.feed_tips_view, "field 'tipsView'", PostLoadedTipsView.class);
        feedMainFragment.loadingView = b.a(view, R.id.fragment_loading, "field 'loadingView'");
    }

    @CallSuper
    public void a() {
        FeedMainFragment feedMainFragment = this.b;
        if (feedMainFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        feedMainFragment.refreshLayout = null;
        feedMainFragment.recyclerView = null;
        feedMainFragment.tipsView = null;
        feedMainFragment.loadingView = null;
    }
}
