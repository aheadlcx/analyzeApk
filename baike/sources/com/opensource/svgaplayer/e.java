package com.opensource.svgaplayer;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 6})
final class e implements Runnable {
    final /* synthetic */ SVGAImageView$loadAttrs$$inlined$let$lambda$1$2 a;
    final /* synthetic */ SVGAVideoEntity b;

    e(SVGAImageView$loadAttrs$$inlined$let$lambda$1$2 sVGAImageView$loadAttrs$$inlined$let$lambda$1$2, SVGAVideoEntity sVGAVideoEntity) {
        this.a = sVGAImageView$loadAttrs$$inlined$let$lambda$1$2;
        this.b = sVGAVideoEntity;
    }

    public final void run() {
        this.b.setAntiAlias(this.a.a.d);
        this.a.a.c.setVideoItem(this.b);
        if (this.a.a.e.getBoolean(R.styleable.SVGAImageView_autoPlay, true)) {
            this.a.a.c.startAnimation();
        }
    }
}
