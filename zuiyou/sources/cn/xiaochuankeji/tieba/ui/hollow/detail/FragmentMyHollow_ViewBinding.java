package cn.xiaochuankeji.tieba.ui.hollow.detail;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class FragmentMyHollow_ViewBinding implements Unbinder {
    private FragmentMyHollow b;
    private View c;
    private View d;

    @UiThread
    public FragmentMyHollow_ViewBinding(final FragmentMyHollow fragmentMyHollow, View view) {
        this.b = fragmentMyHollow;
        fragmentMyHollow.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.my_hollow_refresh_layout, "field 'refreshLayout'", SmartRefreshLayout.class);
        fragmentMyHollow.emptyView = (RelativeLayout) b.b(view, R.id.my_hollow_empty_view, "field 'emptyView'", RelativeLayout.class);
        fragmentMyHollow.recyclerView = (RecyclerView) b.b(view, R.id.my_hollow_list_view, "field 'recyclerView'", RecyclerView.class);
        View a = b.a(view, R.id.empty_hollow_img, "method 'onClickEmpty'");
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ FragmentMyHollow_ViewBinding c;

            public void a(View view) {
                fragmentMyHollow.onClickEmpty(view);
            }
        });
        a = b.a(view, R.id.empty_hollow_text, "method 'onClickEmpty'");
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ FragmentMyHollow_ViewBinding c;

            public void a(View view) {
                fragmentMyHollow.onClickEmpty(view);
            }
        });
    }

    @CallSuper
    public void a() {
        FragmentMyHollow fragmentMyHollow = this.b;
        if (fragmentMyHollow == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        fragmentMyHollow.refreshLayout = null;
        fragmentMyHollow.emptyView = null;
        fragmentMyHollow.recyclerView = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
    }
}
