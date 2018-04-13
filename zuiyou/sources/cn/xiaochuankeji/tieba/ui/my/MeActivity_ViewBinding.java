package cn.xiaochuankeji.tieba.ui.my;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.pullzoom.PullToZoomScrollViewEx;

public class MeActivity_ViewBinding implements Unbinder {
    private MeActivity b;
    private View c;

    @UiThread
    public MeActivity_ViewBinding(final MeActivity meActivity, View view) {
        this.b = meActivity;
        meActivity.setting_crumb = (AppCompatImageView) b.b(view, R.id.setting_crumb, "field 'setting_crumb'", AppCompatImageView.class);
        meActivity.scrollView = (PullToZoomScrollViewEx) b.b(view, R.id.scroll_view, "field 'scrollView'", PullToZoomScrollViewEx.class);
        View a = b.a(view, R.id.setting_layout, "field 'setting_layout' and method 'setting'");
        meActivity.setting_layout = a;
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ MeActivity_ViewBinding c;

            public void a(View view) {
                meActivity.setting();
            }
        });
    }

    @CallSuper
    public void a() {
        MeActivity meActivity = this.b;
        if (meActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        meActivity.setting_crumb = null;
        meActivity.scrollView = null;
        meActivity.setting_layout = null;
        this.c.setOnClickListener(null);
        this.c = null;
    }
}
