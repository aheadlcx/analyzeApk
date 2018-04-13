package cn.xiaochuankeji.tieba.ui.tale;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class TaleInvisibleActivity_ViewBinding implements Unbinder {
    private TaleInvisibleActivity b;
    private View c;

    @UiThread
    public TaleInvisibleActivity_ViewBinding(final TaleInvisibleActivity taleInvisibleActivity, View view) {
        this.b = taleInvisibleActivity;
        taleInvisibleActivity.recycler_view = (RecyclerView) b.b(view, R.id.recycler_view, "field 'recycler_view'", RecyclerView.class);
        taleInvisibleActivity.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.root, "field 'refreshLayout'", SmartRefreshLayout.class);
        View a = b.a(view, R.id.back, "method 'onClick'");
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ TaleInvisibleActivity_ViewBinding c;

            public void a(View view) {
                taleInvisibleActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void a() {
        TaleInvisibleActivity taleInvisibleActivity = this.b;
        if (taleInvisibleActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleInvisibleActivity.recycler_view = null;
        taleInvisibleActivity.refreshLayout = null;
        this.c.setOnClickListener(null);
        this.c = null;
    }
}
