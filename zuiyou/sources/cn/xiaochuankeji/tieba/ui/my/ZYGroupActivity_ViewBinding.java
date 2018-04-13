package cn.xiaochuankeji.tieba.ui.my;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class ZYGroupActivity_ViewBinding implements Unbinder {
    private ZYGroupActivity b;
    private View c;

    @UiThread
    public ZYGroupActivity_ViewBinding(final ZYGroupActivity zYGroupActivity, View view) {
        this.b = zYGroupActivity;
        View a = b.a(view, R.id.back, "method 'back'");
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ ZYGroupActivity_ViewBinding c;

            public void a(View view) {
                zYGroupActivity.back();
            }
        });
    }

    @CallSuper
    public void a() {
        if (this.b == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
    }
}
