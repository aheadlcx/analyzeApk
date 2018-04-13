package cn.xiaochuankeji.tieba.ui.my.licence;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;

public class LicenceActivity_ViewBinding implements Unbinder {
    private LicenceActivity b;

    @UiThread
    public LicenceActivity_ViewBinding(LicenceActivity licenceActivity, View view) {
        this.b = licenceActivity;
        licenceActivity.navBar = (NavigationBar) b.b(view, R.id.navBar, "field 'navBar'", NavigationBar.class);
    }

    @CallSuper
    public void a() {
        LicenceActivity licenceActivity = this.b;
        if (licenceActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        licenceActivity.navBar = null;
    }
}
