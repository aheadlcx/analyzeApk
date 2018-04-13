package com.opensource.svgaplayer;

import android.graphics.Path;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.xiaomi.mipush.sdk.Constants;
import java.util.StringTokenizer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006J \u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/opensource/svgaplayer/SVGAPath;", "", "originValue", "", "(Ljava/lang/String;)V", "cachedPath", "Landroid/graphics/Path;", "replacedValue", "buildPath", "", "toPath", "operate", "finalPath", "method", "args", "Ljava/util/StringTokenizer;", "library_release"}, k = 1, mv = {1, 1, 6})
public final class SVGAPath {
    private final String a;
    private Path b;

    public SVGAPath(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "originValue");
        if (u.contains$default((CharSequence) str, (CharSequence) Constants.ACCEPT_TIME_SEPARATOR_SP, false, 2, null)) {
            str = t.replace$default(str, Constants.ACCEPT_TIME_SEPARATOR_SP, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, false, 4, null);
        }
        this.a = str;
    }

    public final void buildPath(@NotNull Path path) {
        Intrinsics.checkParameterIsNotNull(path, "toPath");
        Path path2 = this.b;
        if (path2 != null) {
            path.addPath(path2);
            return;
        }
        Path path3 = new Path();
        StringTokenizer stringTokenizer = new StringTokenizer(this.a, "MLHVCSQRAZmlhvcsqraz", true);
        String str = "";
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (!(((CharSequence) nextToken).length() == 0)) {
                if (SVGAPathKt.a.contains(nextToken)) {
                    Intrinsics.checkExpressionValueIsNotNull(nextToken, "segment");
                    if (Intrinsics.areEqual(nextToken, "Z") || Intrinsics.areEqual(nextToken, "z")) {
                        a(path3, nextToken, new StringTokenizer("", ""));
                    }
                } else {
                    a(path3, str, new StringTokenizer(nextToken, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR));
                    nextToken = str;
                }
                str = nextToken;
            }
        }
        this.b = path3;
        path.addPath(path3);
    }

    private final void a(Path path, String str, StringTokenizer stringTokenizer) {
        float f;
        int i = 0;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        while (stringTokenizer.hasMoreTokens()) {
            try {
                Object obj;
                String nextToken = stringTokenizer.nextToken();
                if (nextToken.length() == 0) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj == null) {
                    if (i == 0) {
                        f7 = Float.parseFloat(nextToken);
                    }
                    if (i == 1) {
                        f6 = Float.parseFloat(nextToken);
                    }
                    if (i == 2) {
                        f5 = Float.parseFloat(nextToken);
                    }
                    if (i == 3) {
                        f4 = Float.parseFloat(nextToken);
                    }
                    if (i == 4) {
                        f3 = Float.parseFloat(nextToken);
                    }
                    if (i == 5) {
                        f2 = Float.parseFloat(nextToken);
                    }
                    i++;
                }
            } catch (Exception e) {
                f = f7;
            }
        }
        f = f7;
        SVGAPoint sVGAPoint = new SVGAPoint(0.0f, 0.0f, 0.0f);
        if (Intrinsics.areEqual(str, "M")) {
            path.moveTo(f, f6);
            sVGAPoint = new SVGAPoint(f, f6, 0.0f);
        } else if (Intrinsics.areEqual(str, "m")) {
            path.rMoveTo(f, f6);
            sVGAPoint = new SVGAPoint(sVGAPoint.getX() + f, sVGAPoint.getY() + f6, 0.0f);
        }
        if (Intrinsics.areEqual(str, "L")) {
            path.lineTo(f, f6);
        } else if (Intrinsics.areEqual(str, "l")) {
            path.rLineTo(f, f6);
        }
        if (Intrinsics.areEqual(str, "C")) {
            path.cubicTo(f, f6, f5, f4, f3, f2);
        } else if (Intrinsics.areEqual(str, "c")) {
            path.rCubicTo(f, f6, f5, f4, f3, f2);
        }
        if (Intrinsics.areEqual(str, "Q")) {
            path.quadTo(f, f6, f5, f4);
        } else if (Intrinsics.areEqual(str, IXAdRequestInfo.COST_NAME)) {
            path.rQuadTo(f, f6, f5, f4);
        }
        if (Intrinsics.areEqual(str, "H")) {
            path.lineTo(f, sVGAPoint.getY());
        } else if (Intrinsics.areEqual(str, "h")) {
            path.rLineTo(f, 0.0f);
        }
        if (Intrinsics.areEqual(str, "V")) {
            path.lineTo(sVGAPoint.getX(), f);
        } else if (Intrinsics.areEqual(str, "v")) {
            path.rLineTo(0.0f, f);
        }
        if (Intrinsics.areEqual(str, "Z")) {
            path.close();
        } else if (Intrinsics.areEqual(str, "z")) {
            path.close();
        }
    }
}
