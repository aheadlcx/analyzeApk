package com.opensource.svgaplayer;

import android.graphics.Bitmap;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 6})
final class b implements Runnable {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ a b;

    b(Bitmap bitmap, a aVar) {
        this.a = bitmap;
        this.b = aVar;
    }

    public final void run() {
        this.b.a.setDynamicImage(this.a, this.b.d);
    }
}
