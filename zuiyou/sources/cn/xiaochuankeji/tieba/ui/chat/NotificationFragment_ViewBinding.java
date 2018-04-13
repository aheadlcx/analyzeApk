package cn.xiaochuankeji.tieba.ui.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import cn.xiaochuankeji.tieba.widget.StateLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class NotificationFragment_ViewBinding implements Unbinder {
    private NotificationFragment b;

    @UiThread
    public NotificationFragment_ViewBinding(NotificationFragment notificationFragment, View view) {
        this.b = notificationFragment;
        notificationFragment.recycler = (RecyclerView) b.b(view, R.id.recycler, "field 'recycler'", RecyclerView.class);
        notificationFragment.mStateLayout = (StateLayout) b.b(view, R.id.state, "field 'mStateLayout'", StateLayout.class);
        notificationFragment.customEmptyView = (CustomEmptyView) b.b(view, R.id.custom_empty_view, "field 'customEmptyView'", CustomEmptyView.class);
        notificationFragment.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.refresh, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void a() {
        NotificationFragment notificationFragment = this.b;
        if (notificationFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        notificationFragment.recycler = null;
        notificationFragment.mStateLayout = null;
        notificationFragment.customEmptyView = null;
        notificationFragment.refreshLayout = null;
    }
}
