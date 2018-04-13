package cn.xiaochuankeji.tieba.ui.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;

public class FlowSessionActivity_ViewBinding implements Unbinder {
    private FlowSessionActivity b;

    @UiThread
    public FlowSessionActivity_ViewBinding(FlowSessionActivity flowSessionActivity, View view) {
        this.b = flowSessionActivity;
        flowSessionActivity.recycler = (RecyclerView) b.b(view, R.id.recycler, "field 'recycler'", RecyclerView.class);
        flowSessionActivity.navBar = (NavigationBar) b.b(view, R.id.navBar, "field 'navBar'", NavigationBar.class);
    }

    @CallSuper
    public void a() {
        FlowSessionActivity flowSessionActivity = this.b;
        if (flowSessionActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        flowSessionActivity.recycler = null;
        flowSessionActivity.navBar = null;
    }
}
