package com.opensource.svgaplayer;

import com.opensource.svgaplayer.SVGAParser.ParseCompletion;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 6})
final class h implements Runnable {
    final /* synthetic */ SVGAVideoEntity a;
    final /* synthetic */ SVGAParser b;
    final /* synthetic */ ParseCompletion c;

    h(SVGAVideoEntity sVGAVideoEntity, SVGAParser sVGAParser, ParseCompletion parseCompletion) {
        this.a = sVGAVideoEntity;
        this.b = sVGAParser;
        this.c = parseCompletion;
    }

    public final void run() {
        this.c.onComplete(this.a);
    }
}
