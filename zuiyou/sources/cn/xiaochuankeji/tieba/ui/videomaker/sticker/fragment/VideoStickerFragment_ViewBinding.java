package cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.indicator.MagicIndicator;

public class VideoStickerFragment_ViewBinding implements Unbinder {
    private VideoStickerFragment b;
    private View c;

    @UiThread
    public VideoStickerFragment_ViewBinding(final VideoStickerFragment videoStickerFragment, View view) {
        this.b = videoStickerFragment;
        videoStickerFragment.magic_indicator = (MagicIndicator) b.b(view, R.id.magic_indicator, "field 'magic_indicator'", MagicIndicator.class);
        videoStickerFragment.viewPager = (ViewPager) b.b(view, R.id.viewPager, "field 'viewPager'", ViewPager.class);
        videoStickerFragment.panel = b.a(view, R.id.panel, "field 'panel'");
        View a = b.a(view, R.id.root, "method 'close'");
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ VideoStickerFragment_ViewBinding c;

            public void a(View view) {
                videoStickerFragment.close();
            }
        });
    }

    @CallSuper
    public void a() {
        VideoStickerFragment videoStickerFragment = this.b;
        if (videoStickerFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        videoStickerFragment.magic_indicator = null;
        videoStickerFragment.viewPager = null;
        videoStickerFragment.panel = null;
        this.c.setOnClickListener(null);
        this.c = null;
    }
}
