package cn.xiaochuankeji.tieba.abmgr.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;

public class ActivitySelectType_ViewBinding implements Unbinder {
    private ActivitySelectType b;

    @UiThread
    public ActivitySelectType_ViewBinding(ActivitySelectType activitySelectType, View view) {
        this.b = activitySelectType;
        activitySelectType.typeList = (RecyclerView) b.b(view, R.id.version_type_list, "field 'typeList'", RecyclerView.class);
        activitySelectType.navigationBar = (NavigationBar) b.b(view, R.id.navBar, "field 'navigationBar'", NavigationBar.class);
    }

    @CallSuper
    public void a() {
        ActivitySelectType activitySelectType = this.b;
        if (activitySelectType == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        activitySelectType.typeList = null;
        activitySelectType.navigationBar = null;
    }
}
