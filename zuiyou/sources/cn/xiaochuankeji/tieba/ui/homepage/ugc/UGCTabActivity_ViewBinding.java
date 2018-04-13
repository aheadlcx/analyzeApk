package cn.xiaochuankeji.tieba.ui.homepage.ugc;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.TBViewPager;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.indicator.MagicIndicator;

public class UGCTabActivity_ViewBinding implements Unbinder {
    private UGCTabActivity b;
    private View c;
    private View d;

    @UiThread
    public UGCTabActivity_ViewBinding(final UGCTabActivity uGCTabActivity, View view) {
        this.b = uGCTabActivity;
        uGCTabActivity.indicator = (MagicIndicator) b.b(view, R.id.indicator, "field 'indicator'", MagicIndicator.class);
        uGCTabActivity.viewPager = (TBViewPager) b.b(view, R.id.viewPager, "field 'viewPager'", TBViewPager.class);
        uGCTabActivity.header = b.a(view, R.id.header, "field 'header'");
        uGCTabActivity.wivIcon = (WebImageView) b.b(view, R.id.wivIcon, "field 'wivIcon'", WebImageView.class);
        uGCTabActivity.rlRootView = (RelativeLayout) b.b(view, R.id.rlRootView, "field 'rlRootView'", RelativeLayout.class);
        View a = b.a(view, R.id.search, "method 'search'");
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ UGCTabActivity_ViewBinding c;

            public void a(View view) {
                uGCTabActivity.search();
            }
        });
        a = b.a(view, R.id.post, "method 'post'");
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ UGCTabActivity_ViewBinding c;

            public void a(View view) {
                uGCTabActivity.post();
            }
        });
    }

    @CallSuper
    public void a() {
        UGCTabActivity uGCTabActivity = this.b;
        if (uGCTabActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        uGCTabActivity.indicator = null;
        uGCTabActivity.viewPager = null;
        uGCTabActivity.header = null;
        uGCTabActivity.wivIcon = null;
        uGCTabActivity.rlRootView = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
    }
}
