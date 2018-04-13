package cn.xiaochuankeji.tieba.ui.tale;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class TaleThumbUsersActivity_ViewBinding implements Unbinder {
    private TaleThumbUsersActivity b;

    @UiThread
    public TaleThumbUsersActivity_ViewBinding(TaleThumbUsersActivity taleThumbUsersActivity, View view) {
        this.b = taleThumbUsersActivity;
        taleThumbUsersActivity.navBar = (NavigationBar) b.b(view, R.id.navBar, "field 'navBar'", NavigationBar.class);
        taleThumbUsersActivity.recycler = (RecyclerView) b.b(view, R.id.recycler_view, "field 'recycler'", RecyclerView.class);
        taleThumbUsersActivity.refresh = (SmartRefreshLayout) b.b(view, R.id.refresh, "field 'refresh'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void a() {
        TaleThumbUsersActivity taleThumbUsersActivity = this.b;
        if (taleThumbUsersActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleThumbUsersActivity.navBar = null;
        taleThumbUsersActivity.recycler = null;
        taleThumbUsersActivity.refresh = null;
    }
}
