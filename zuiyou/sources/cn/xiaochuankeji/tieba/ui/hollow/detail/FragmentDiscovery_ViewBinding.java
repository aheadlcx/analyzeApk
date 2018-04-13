package cn.xiaochuankeji.tieba.ui.hollow.detail;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class FragmentDiscovery_ViewBinding implements Unbinder {
    private FragmentDiscovery b;

    @UiThread
    public FragmentDiscovery_ViewBinding(FragmentDiscovery fragmentDiscovery, View view) {
        this.b = fragmentDiscovery;
        fragmentDiscovery.contentRecyclerView = (RecyclerView) b.b(view, R.id.recommend_content, "field 'contentRecyclerView'", RecyclerView.class);
        fragmentDiscovery.tipsView = (PostLoadedTipsView) b.b(view, R.id.tips_view, "field 'tipsView'", PostLoadedTipsView.class);
        fragmentDiscovery.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.root, "field 'refreshLayout'", SmartRefreshLayout.class);
        fragmentDiscovery.tipsEmpty = (ImageView) b.b(view, R.id.empty_hollow, "field 'tipsEmpty'", ImageView.class);
    }

    @CallSuper
    public void a() {
        FragmentDiscovery fragmentDiscovery = this.b;
        if (fragmentDiscovery == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        fragmentDiscovery.contentRecyclerView = null;
        fragmentDiscovery.tipsView = null;
        fragmentDiscovery.refreshLayout = null;
        fragmentDiscovery.tipsEmpty = null;
    }
}
