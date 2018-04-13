package com.opensource.svgaplayer;

import com.opensource.svgaplayer.SVGAParser.ParseCompletion;
import java.io.InputStream;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 6})
final class n implements Runnable {
    final /* synthetic */ SVGAParser a;
    final /* synthetic */ InputStream b;
    final /* synthetic */ String c;
    final /* synthetic */ ParseCompletion d;

    n(SVGAParser sVGAParser, InputStream inputStream, String str, ParseCompletion parseCompletion) {
        this.a = sVGAParser;
        this.b = inputStream;
        this.c = str;
        this.d = parseCompletion;
    }

    public final void run() {
        new Thread(new o(this, this.a.parse(this.b, this.c))).start();
    }
}
