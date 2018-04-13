package cn.xiaochuankeji.tieba.ui.tale;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.dreamtobe.kpswitch.widget.KPSwitchPanelFrameLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class TaleDetailActivity_ViewBinding implements Unbinder {
    private TaleDetailActivity b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;

    @UiThread
    public TaleDetailActivity_ViewBinding(final TaleDetailActivity taleDetailActivity, View view) {
        this.b = taleDetailActivity;
        View a = b.a(view, R.id.back, "field 'back' and method 'onClick'");
        taleDetailActivity.back = a;
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ TaleDetailActivity_ViewBinding c;

            public void a(View view) {
                taleDetailActivity.onClick(view);
            }
        });
        View a2 = b.a(view, R.id.iv_avatar, "field 'iv_avatar' and method 'onClick'");
        taleDetailActivity.iv_avatar = (WebImageView) b.c(a2, R.id.iv_avatar, "field 'iv_avatar'", WebImageView.class);
        this.d = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ TaleDetailActivity_ViewBinding c;

            public void a(View view) {
                taleDetailActivity.onClick(view);
            }
        });
        a2 = b.a(view, R.id.tv_title, "field 'tv_title' and method 'onClick'");
        taleDetailActivity.tv_title = (TextView) b.c(a2, R.id.tv_title, "field 'tv_title'", TextView.class);
        this.e = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ TaleDetailActivity_ViewBinding c;

            public void a(View view) {
                taleDetailActivity.onClick(view);
            }
        });
        taleDetailActivity.viewpager = (ViewPager) b.b(view, R.id.viewpager, "field 'viewpager'", ViewPager.class);
        taleDetailActivity.appbar = (AppBarLayout) b.b(view, R.id.appbar, "field 'appbar'", AppBarLayout.class);
        a2 = b.a(view, R.id.theme_title, "field 'theme_title' and method 'onClick'");
        taleDetailActivity.theme_title = (TextView) b.c(a2, R.id.theme_title, "field 'theme_title'", TextView.class);
        this.f = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ TaleDetailActivity_ViewBinding c;

            public void a(View view) {
                taleDetailActivity.onClick(view);
            }
        });
        a2 = b.a(view, R.id.theme_count, "field 'theme_count' and method 'onClick'");
        taleDetailActivity.theme_count = (TextView) b.c(a2, R.id.theme_count, "field 'theme_count'", TextView.class);
        this.g = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ TaleDetailActivity_ViewBinding c;

            public void a(View view) {
                taleDetailActivity.onClick(view);
            }
        });
        taleDetailActivity.recycler_view = (RecyclerView) b.b(view, R.id.recycler_view, "field 'recycler_view'", RecyclerView.class);
        taleDetailActivity.recycler_view_title = (RecyclerView) b.b(view, R.id.recycler_view_title, "field 'recycler_view_title'", RecyclerView.class);
        taleDetailActivity.ll_title = b.a(view, R.id.ll_title, "field 'll_title'");
        taleDetailActivity.input_container = (FrameLayout) b.b(view, R.id.input_container, "field 'input_container'", FrameLayout.class);
        taleDetailActivity.panel_root = (KPSwitchPanelFrameLayout) b.b(view, R.id.panel_root, "field 'panel_root'", KPSwitchPanelFrameLayout.class);
        a = b.a(view, R.id.iv_share, "method 'onClick'");
        this.h = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ TaleDetailActivity_ViewBinding c;

            public void a(View view) {
                taleDetailActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void a() {
        TaleDetailActivity taleDetailActivity = this.b;
        if (taleDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleDetailActivity.back = null;
        taleDetailActivity.iv_avatar = null;
        taleDetailActivity.tv_title = null;
        taleDetailActivity.viewpager = null;
        taleDetailActivity.appbar = null;
        taleDetailActivity.theme_title = null;
        taleDetailActivity.theme_count = null;
        taleDetailActivity.recycler_view = null;
        taleDetailActivity.recycler_view_title = null;
        taleDetailActivity.ll_title = null;
        taleDetailActivity.input_container = null;
        taleDetailActivity.panel_root = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
        this.f.setOnClickListener(null);
        this.f = null;
        this.g.setOnClickListener(null);
        this.g = null;
        this.h.setOnClickListener(null);
        this.h = null;
    }
}
