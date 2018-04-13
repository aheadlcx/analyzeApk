package cn.xiaochuankeji.tieba.ui.picker;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class RegionHolder_ViewBinding implements Unbinder {
    private RegionHolder b;

    @UiThread
    public RegionHolder_ViewBinding(RegionHolder regionHolder, View view) {
        this.b = regionHolder;
        regionHolder.flag = (AppCompatImageView) b.b(view, R.id.flag, "field 'flag'", AppCompatImageView.class);
        regionHolder.name = (AppCompatTextView) b.b(view, R.id.name, "field 'name'", AppCompatTextView.class);
        regionHolder.divider = b.a(view, R.id.divider, "field 'divider'");
    }

    @CallSuper
    public void a() {
        RegionHolder regionHolder = this.b;
        if (regionHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        regionHolder.flag = null;
        regionHolder.name = null;
        regionHolder.divider = null;
    }
}
