package cn.xiaochuankeji.tieba.ui.tale;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class TaleDetailFragment_ViewBinding implements Unbinder {
    private TaleDetailFragment b;

    @UiThread
    public TaleDetailFragment_ViewBinding(TaleDetailFragment taleDetailFragment, View view) {
        this.b = taleDetailFragment;
        taleDetailFragment.recycler_view = (RecyclerView) b.b(view, R.id.recycler_view, "field 'recycler_view'", RecyclerView.class);
        taleDetailFragment.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.refresh, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void a() {
        TaleDetailFragment taleDetailFragment = this.b;
        if (taleDetailFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleDetailFragment.recycler_view = null;
        taleDetailFragment.refreshLayout = null;
    }
}
