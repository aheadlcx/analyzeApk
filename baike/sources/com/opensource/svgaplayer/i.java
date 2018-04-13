package com.opensource.svgaplayer;

import android.os.Handler;
import com.opensource.svgaplayer.SVGAParser.ParseCompletion;
import java.io.InputStream;
import java.net.URL;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Ljava/io/InputStream;", "invoke"}, k = 3, mv = {1, 1, 6})
final class i extends Lambda implements Function1<InputStream, Unit> {
    final /* synthetic */ SVGAParser a;
    final /* synthetic */ URL b;
    final /* synthetic */ ParseCompletion c;

    i(SVGAParser sVGAParser, URL url, ParseCompletion parseCompletion) {
        this.a = sVGAParser;
        this.b = url;
        this.c = parseCompletion;
        super(1);
    }

    public final void invoke(@NotNull InputStream inputStream) {
        Intrinsics.checkParameterIsNotNull(inputStream, "it");
        SVGAVideoEntity parse = this.a.parse(inputStream, this.a.a(this.b));
        if (parse != null) {
            new Handler(this.a.b.getMainLooper()).post(new j(this, parse));
            return;
        }
        Boolean valueOf = Boolean.valueOf(new Handler(this.a.b.getMainLooper()).post(new k(this)));
        if (!(valueOf instanceof Unit)) {
            valueOf = null;
        }
        if (((Unit) valueOf) == null) {
            Unit unit = Unit.INSTANCE;
        }
    }
}
