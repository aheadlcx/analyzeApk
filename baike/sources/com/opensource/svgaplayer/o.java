package com.opensource.svgaplayer;

import android.os.Handler;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 6})
final class o implements Runnable {
    final /* synthetic */ n a;
    final /* synthetic */ SVGAVideoEntity b;

    o(n nVar, SVGAVideoEntity sVGAVideoEntity) {
        this.a = nVar;
        this.b = sVGAVideoEntity;
    }

    public final void run() {
        if (this.b != null) {
            new Handler(this.a.a.b.getMainLooper()).post(new p(this));
        } else {
            new Handler(this.a.a.b.getMainLooper()).post(new q(this));
        }
    }
}
