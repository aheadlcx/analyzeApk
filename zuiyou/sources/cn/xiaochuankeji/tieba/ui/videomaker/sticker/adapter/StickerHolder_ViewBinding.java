package cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class StickerHolder_ViewBinding implements Unbinder {
    private StickerHolder b;

    @UiThread
    public StickerHolder_ViewBinding(StickerHolder stickerHolder, View view) {
        this.b = stickerHolder;
        stickerHolder.image = (ImageView) b.a(view, R.id.image, "field 'image'", ImageView.class);
        stickerHolder.state = (AppCompatImageView) b.a(view, R.id.state, "field 'state'", AppCompatImageView.class);
        stickerHolder.progres = (AppCompatImageView) b.a(view, R.id.progres, "field 'progres'", AppCompatImageView.class);
        stickerHolder.resource = (RecyclerView) b.a(view, R.id.resource, "field 'resource'", RecyclerView.class);
    }

    @CallSuper
    public void a() {
        StickerHolder stickerHolder = this.b;
        if (stickerHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        stickerHolder.image = null;
        stickerHolder.state = null;
        stickerHolder.progres = null;
        stickerHolder.resource = null;
    }
}
