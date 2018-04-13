package cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class StickerBrowserFragment_ViewBinding implements Unbinder {
    private StickerBrowserFragment b;
    private View c;

    @UiThread
    public StickerBrowserFragment_ViewBinding(final StickerBrowserFragment stickerBrowserFragment, View view) {
        this.b = stickerBrowserFragment;
        stickerBrowserFragment.title = (AppCompatTextView) b.b(view, R.id.title, "field 'title'", AppCompatTextView.class);
        stickerBrowserFragment.recyclerView = (RecyclerView) b.b(view, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
        View a = b.a(view, R.id.close, "method 'close'");
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ StickerBrowserFragment_ViewBinding c;

            public void a(View view) {
                stickerBrowserFragment.close();
            }
        });
    }

    @CallSuper
    public void a() {
        StickerBrowserFragment stickerBrowserFragment = this.b;
        if (stickerBrowserFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        stickerBrowserFragment.title = null;
        stickerBrowserFragment.recyclerView = null;
        this.c.setOnClickListener(null);
        this.c = null;
    }
}
