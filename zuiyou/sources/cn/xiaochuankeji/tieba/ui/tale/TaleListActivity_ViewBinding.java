package cn.xiaochuankeji.tieba.ui.tale;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class TaleListActivity_ViewBinding implements Unbinder {
    private TaleListActivity b;
    private View c;
    private View d;
    private View e;
    private View f;

    @UiThread
    public TaleListActivity_ViewBinding(final TaleListActivity taleListActivity, View view) {
        this.b = taleListActivity;
        View a = b.a(view, R.id.back, "field 'back' and method 'onClick'");
        taleListActivity.back = a;
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ TaleListActivity_ViewBinding c;

            public void a(View view) {
                taleListActivity.onClick(view);
            }
        });
        View a2 = b.a(view, R.id.tv_title, "field 'tv_title' and method 'onClick'");
        taleListActivity.tv_title = (TextView) b.c(a2, R.id.tv_title, "field 'tv_title'", TextView.class);
        this.d = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ TaleListActivity_ViewBinding c;

            public void a(View view) {
                taleListActivity.onClick(view);
            }
        });
        taleListActivity.tv_count = (TextView) b.b(view, R.id.tv_count, "field 'tv_count'", TextView.class);
        taleListActivity.ll_title = b.a(view, R.id.ll_title, "field 'll_title'");
        taleListActivity.recycler_view = (RecyclerView) b.b(view, R.id.recycler_view, "field 'recycler_view'", RecyclerView.class);
        a2 = b.a(view, R.id.iv_create, "field 'iv_create' and method 'onClick'");
        taleListActivity.iv_create = (ImageView) b.c(a2, R.id.iv_create, "field 'iv_create'", ImageView.class);
        this.e = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ TaleListActivity_ViewBinding c;

            public void a(View view) {
                taleListActivity.onClick(view);
            }
        });
        taleListActivity.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.root, "field 'refreshLayout'", SmartRefreshLayout.class);
        a = b.a(view, R.id.iv_share, "method 'onClick'");
        this.f = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ TaleListActivity_ViewBinding c;

            public void a(View view) {
                taleListActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void a() {
        TaleListActivity taleListActivity = this.b;
        if (taleListActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleListActivity.back = null;
        taleListActivity.tv_title = null;
        taleListActivity.tv_count = null;
        taleListActivity.ll_title = null;
        taleListActivity.recycler_view = null;
        taleListActivity.iv_create = null;
        taleListActivity.refreshLayout = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
        this.f.setOnClickListener(null);
        this.f = null;
    }
}
