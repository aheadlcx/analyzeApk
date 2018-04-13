package cn.xiaochuankeji.tieba.ui.hollow.recommend;

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

public class TreeHoleFragment_ViewBinding implements Unbinder {
    private TreeHoleFragment b;

    @UiThread
    public TreeHoleFragment_ViewBinding(TreeHoleFragment treeHoleFragment, View view) {
        this.b = treeHoleFragment;
        treeHoleFragment.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.root, "field 'refreshLayout'", SmartRefreshLayout.class);
        treeHoleFragment.contentRecyclerView = (RecyclerView) b.b(view, R.id.recommend_content, "field 'contentRecyclerView'", RecyclerView.class);
        treeHoleFragment.tipsView = (PostLoadedTipsView) b.b(view, R.id.tips_view, "field 'tipsView'", PostLoadedTipsView.class);
        treeHoleFragment.tipsEmpty = (ImageView) b.b(view, R.id.empty_hollow, "field 'tipsEmpty'", ImageView.class);
    }

    @CallSuper
    public void a() {
        TreeHoleFragment treeHoleFragment = this.b;
        if (treeHoleFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        treeHoleFragment.refreshLayout = null;
        treeHoleFragment.contentRecyclerView = null;
        treeHoleFragment.tipsView = null;
        treeHoleFragment.tipsEmpty = null;
    }
}
