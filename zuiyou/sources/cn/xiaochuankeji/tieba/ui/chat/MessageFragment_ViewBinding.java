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

public class MessageFragment_ViewBinding implements Unbinder {
    private MessageFragment b;

    @UiThread
    public MessageFragment_ViewBinding(MessageFragment messageFragment, View view) {
        this.b = messageFragment;
        messageFragment.mStateLayout = (StateLayout) b.b(view, R.id.state, "field 'mStateLayout'", StateLayout.class);
        messageFragment.recycler = (RecyclerView) b.b(view, R.id.recycler, "field 'recycler'", RecyclerView.class);
        messageFragment.customEmptyView = (CustomEmptyView) b.b(view, R.id.custom_empty_view, "field 'customEmptyView'", CustomEmptyView.class);
        messageFragment.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.refresh, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void a() {
        MessageFragment messageFragment = this.b;
        if (messageFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        messageFragment.mStateLayout = null;
        messageFragment.recycler = null;
        messageFragment.customEmptyView = null;
        messageFragment.refreshLayout = null;
    }
}
