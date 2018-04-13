package com.opensource.svgaplayer;

import android.os.Handler;
import com.opensource.svgaplayer.SVGAParser.ParseCompletion;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Ljava/lang/Exception;", "Lkotlin/Exception;", "invoke"}, k = 3, mv = {1, 1, 6})
final class l extends Lambda implements Function1<Exception, Unit> {
    final /* synthetic */ SVGAParser a;
    final /* synthetic */ ParseCompletion b;

    l(SVGAParser sVGAParser, ParseCompletion parseCompletion) {
        this.a = sVGAParser;
        this.b = parseCompletion;
        super(1);
    }

    public final void invoke(@NotNull Exception exception) {
        Intrinsics.checkParameterIsNotNull(exception, "it");
        new Handler(this.a.b.getMainLooper()).post(new m(this));
    }
}
