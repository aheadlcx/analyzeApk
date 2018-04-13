package cn.xiaochuankeji.tieba.ui.picker;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.IndexLayout;

public class RegionPicker_ViewBinding implements Unbinder {
    private RegionPicker b;
    private View c;

    @UiThread
    public RegionPicker_ViewBinding(final RegionPicker regionPicker, View view) {
        this.b = regionPicker;
        regionPicker.mSearchView = (AppCompatEditText) b.b(view, R.id.search, "field 'mSearchView'", AppCompatEditText.class);
        regionPicker.mProgressBar = (FrameLayout) b.b(view, R.id.progress, "field 'mProgressBar'", FrameLayout.class);
        regionPicker.mIndexLayout = (IndexLayout) b.b(view, R.id.indexLayout, "field 'mIndexLayout'", IndexLayout.class);
        regionPicker.title = (TextView) b.b(view, R.id.title, "field 'title'", TextView.class);
        View a = b.a(view, R.id.back, "method 'back'");
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ RegionPicker_ViewBinding c;

            public void a(View view) {
                regionPicker.back();
            }
        });
    }

    @CallSuper
    public void a() {
        RegionPicker regionPicker = this.b;
        if (regionPicker == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        regionPicker.mSearchView = null;
        regionPicker.mProgressBar = null;
        regionPicker.mIndexLayout = null;
        regionPicker.title = null;
        this.c.setOnClickListener(null);
        this.c = null;
    }
}
