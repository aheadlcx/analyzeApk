package cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class CustomStickerFragment_ViewBinding implements Unbinder {
    private CustomStickerFragment b;

    @UiThread
    public CustomStickerFragment_ViewBinding(CustomStickerFragment customStickerFragment, View view) {
        this.b = customStickerFragment;
        customStickerFragment.recyclerView = (RecyclerView) b.b(view, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
        customStickerFragment.progress = b.a(view, R.id.progress, "field 'progress'");
    }

    @CallSuper
    public void a() {
        CustomStickerFragment customStickerFragment = this.b;
        if (customStickerFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        customStickerFragment.recyclerView = null;
        customStickerFragment.progress = null;
    }
}
