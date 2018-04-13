package com.opensource.svgaplayer;

import android.os.Handler;
import com.opensource.svgaplayer.SVGAParser.ParseCompletion;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016¨\u0006\b"}, d2 = {"com/opensource/svgaplayer/SVGAImageView$loadAttrs$1$1$1$1", "Lcom/opensource/svgaplayer/SVGAParser$ParseCompletion;", "(Lcom/opensource/svgaplayer/SVGAImageView$loadAttrs$1$1$1;)V", "onComplete", "", "videoItem", "Lcom/opensource/svgaplayer/SVGAVideoEntity;", "onError", "library_release"}, k = 1, mv = {1, 1, 6})
public final class SVGAImageView$loadAttrs$$inlined$let$lambda$1$1 implements ParseCompletion {
    final /* synthetic */ c a;

    SVGAImageView$loadAttrs$$inlined$let$lambda$1$1(c cVar) {
        this.a = cVar;
    }

    public void onComplete(SVGAVideoEntity sVGAVideoEntity) {
        Handler handler = this.a.c.getHandler();
        if (handler != null) {
            handler.post(new d(this, sVGAVideoEntity));
        }
    }

    public void onError() {
    }
}
