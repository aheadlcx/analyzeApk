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

public class MagicEmotionHolder_ViewBinding implements Unbinder {
    private MagicEmotionHolder b;

    @UiThread
    public MagicEmotionHolder_ViewBinding(MagicEmotionHolder magicEmotionHolder, View view) {
        this.b = magicEmotionHolder;
        magicEmotionHolder.image = (ImageView) b.a(view, R.id.image, "field 'image'", ImageView.class);
        magicEmotionHolder.state = (AppCompatImageView) b.a(view, R.id.state, "field 'state'", AppCompatImageView.class);
        magicEmotionHolder.progres = (AppCompatImageView) b.a(view, R.id.progres, "field 'progres'", AppCompatImageView.class);
        magicEmotionHolder.ivSelect = (AppCompatImageView) b.a(view, R.id.ivSelect, "field 'ivSelect'", AppCompatImageView.class);
        magicEmotionHolder.resource = (RecyclerView) b.a(view, R.id.resource, "field 'resource'", RecyclerView.class);
    }

    @CallSuper
    public void a() {
        MagicEmotionHolder magicEmotionHolder = this.b;
        if (magicEmotionHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        magicEmotionHolder.image = null;
        magicEmotionHolder.state = null;
        magicEmotionHolder.progres = null;
        magicEmotionHolder.ivSelect = null;
        magicEmotionHolder.resource = null;
    }
}
