package com.opensource.svgaplayer;

import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextPaint;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\n\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0005J\u0016\u0010\n\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005J\u001e\u0010\u000e\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0005R6\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR6\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR6\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0010`\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\t\"\u0004\b\u0012\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/opensource/svgaplayer/SVGADynamicEntity;", "", "()V", "dynamicImage", "Ljava/util/HashMap;", "", "Landroid/graphics/Bitmap;", "Lkotlin/collections/HashMap;", "getDynamicImage", "()Ljava/util/HashMap;", "setDynamicImage", "(Ljava/util/HashMap;)V", "dynamicText", "getDynamicText", "setDynamicText", "dynamicTextPaint", "Landroid/text/TextPaint;", "getDynamicTextPaint", "setDynamicTextPaint", "clearDynamicObjects", "", "bitmap", "forKey", "url", "text", "textPaint", "library_release"}, k = 1, mv = {1, 1, 6})
public final class SVGADynamicEntity {
    @NotNull
    private HashMap<String, Bitmap> a = new HashMap();
    @NotNull
    private HashMap<String, String> b = new HashMap();
    @NotNull
    private HashMap<String, TextPaint> c = new HashMap();

    @NotNull
    public final HashMap<String, Bitmap> getDynamicImage() {
        return this.a;
    }

    public final void setDynamicImage(@NotNull HashMap<String, Bitmap> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.a = hashMap;
    }

    @NotNull
    public final HashMap<String, String> getDynamicText() {
        return this.b;
    }

    public final void setDynamicText(@NotNull HashMap<String, String> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.b = hashMap;
    }

    @NotNull
    public final HashMap<String, TextPaint> getDynamicTextPaint() {
        return this.c;
    }

    public final void setDynamicTextPaint(@NotNull HashMap<String, TextPaint> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.c = hashMap;
    }

    public final void setDynamicImage(@NotNull Bitmap bitmap, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        Intrinsics.checkParameterIsNotNull(str, "forKey");
        this.a.put(str, bitmap);
    }

    public final void setDynamicImage(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(str2, "forKey");
        new Thread(new a(this, str, new Handler(), str2)).start();
    }

    public final void setDynamicText(@NotNull String str, @NotNull TextPaint textPaint, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "text");
        Intrinsics.checkParameterIsNotNull(textPaint, "textPaint");
        Intrinsics.checkParameterIsNotNull(str2, "forKey");
        this.b.put(str2, str);
        this.c.put(str2, textPaint);
    }

    public final void clearDynamicObjects() {
        this.a.clear();
        this.b.clear();
        this.c.clear();
    }
}
