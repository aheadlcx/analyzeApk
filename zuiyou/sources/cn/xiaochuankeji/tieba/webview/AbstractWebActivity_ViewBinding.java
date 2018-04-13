package cn.xiaochuankeji.tieba.webview;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class AbstractWebActivity_ViewBinding implements Unbinder {
    private AbstractWebActivity b;

    @UiThread
    public AbstractWebActivity_ViewBinding(AbstractWebActivity abstractWebActivity, View view) {
        this.b = abstractWebActivity;
        abstractWebActivity.webContainer = (FrameLayout) b.b(view, R.id.webContainer, "field 'webContainer'", FrameLayout.class);
        abstractWebActivity.action_bar = (FrameLayout) b.b(view, R.id.action_bar, "field 'action_bar'", FrameLayout.class);
        abstractWebActivity.divider = b.a(view, R.id.divider, "field 'divider'");
    }

    @CallSuper
    public void a() {
        AbstractWebActivity abstractWebActivity = this.b;
        if (abstractWebActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        abstractWebActivity.webContainer = null;
        abstractWebActivity.action_bar = null;
        abstractWebActivity.divider = null;
    }
}
