package cn.xiaochuankeji.tieba.ui.topic.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class PostListFragment_ViewBinding implements Unbinder {
    private PostListFragment b;

    @UiThread
    public PostListFragment_ViewBinding(PostListFragment postListFragment, View view) {
        this.b = postListFragment;
        postListFragment.recyclerView = (RecyclerView) b.b(view, R.id.id_stickynavlayout_innerscrollview, "field 'recyclerView'", RecyclerView.class);
        postListFragment.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.topic_refresh_layout, "field 'refreshLayout'", SmartRefreshLayout.class);
        postListFragment.emptyView = (CustomEmptyView) b.b(view, R.id.empty_view, "field 'emptyView'", CustomEmptyView.class);
    }

    @CallSuper
    public void a() {
        PostListFragment postListFragment = this.b;
        if (postListFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        postListFragment.recyclerView = null;
        postListFragment.refreshLayout = null;
        postListFragment.emptyView = null;
    }
}
