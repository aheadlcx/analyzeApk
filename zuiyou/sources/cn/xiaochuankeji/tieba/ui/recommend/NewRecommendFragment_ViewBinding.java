package cn.xiaochuankeji.tieba.ui.recommend;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class NewRecommendFragment_ViewBinding implements Unbinder {
    private NewRecommendFragment b;

    @UiThread
    public NewRecommendFragment_ViewBinding(NewRecommendFragment newRecommendFragment, View view) {
        this.b = newRecommendFragment;
        newRecommendFragment.recyclerView = (RecyclerView) b.b(view, R.id.recommend_content, "field 'recyclerView'", RecyclerView.class);
        newRecommendFragment.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.root, "field 'refreshLayout'", SmartRefreshLayout.class);
        newRecommendFragment.tipsView = (PostLoadedTipsView) b.b(view, R.id.tips_view, "field 'tipsView'", PostLoadedTipsView.class);
    }

    @CallSuper
    public void a() {
        NewRecommendFragment newRecommendFragment = this.b;
        if (newRecommendFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        newRecommendFragment.recyclerView = null;
        newRecommendFragment.refreshLayout = null;
        newRecommendFragment.tipsView = null;
    }
}
