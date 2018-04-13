package com.opensource.svgaplayer;

import android.content.res.TypedArray;
import com.opensource.svgaplayer.SVGAParser.ParseCompletion;
import java.net.URL;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 6})
final class c implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ SVGAParser b;
    final /* synthetic */ SVGAImageView c;
    final /* synthetic */ boolean d;
    final /* synthetic */ TypedArray e;

    c(String str, SVGAParser sVGAParser, SVGAImageView sVGAImageView, boolean z, TypedArray typedArray) {
        this.a = str;
        this.b = sVGAParser;
        this.c = sVGAImageView;
        this.d = z;
        this.e = typedArray;
    }

    public final void run() {
        if (t.startsWith$default(this.a, "http://", false, 2, null) || t.startsWith$default(this.a, "https://", false, 2, null)) {
            this.b.parse(new URL(this.a), (ParseCompletion) new SVGAImageView$loadAttrs$$inlined$let$lambda$1$1(this));
            return;
        }
        this.b.parse(this.a, (ParseCompletion) new SVGAImageView$loadAttrs$$inlined$let$lambda$1$2(this));
    }
}
