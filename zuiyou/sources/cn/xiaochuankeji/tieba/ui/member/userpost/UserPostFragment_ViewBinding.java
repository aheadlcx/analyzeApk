package cn.xiaochuankeji.tieba.ui.member.userpost;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class UserPostFragment_ViewBinding implements Unbinder {
    private UserPostFragment b;

    @UiThread
    public UserPostFragment_ViewBinding(UserPostFragment userPostFragment, View view) {
        this.b = userPostFragment;
        userPostFragment.recyclerView = (RecyclerView) b.b(view, R.id.id_stickynavlayout_innerscrollview, "field 'recyclerView'", RecyclerView.class);
        userPostFragment.customEmptyView = (CustomEmptyView) b.b(view, R.id.custom_empty_view, "field 'customEmptyView'", CustomEmptyView.class);
        userPostFragment.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.refresh_layout, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void a() {
        UserPostFragment userPostFragment = this.b;
        if (userPostFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        userPostFragment.recyclerView = null;
        userPostFragment.customEmptyView = null;
        userPostFragment.refreshLayout = null;
    }
}
