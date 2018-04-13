package com.opensource.svgaplayer;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 6})
final class j implements Runnable {
    final /* synthetic */ i a;
    final /* synthetic */ SVGAVideoEntity b;

    j(i iVar, SVGAVideoEntity sVGAVideoEntity) {
        this.a = iVar;
        this.b = sVGAVideoEntity;
    }

    public final void run() {
        this.a.c.onComplete(this.b);
    }
}
