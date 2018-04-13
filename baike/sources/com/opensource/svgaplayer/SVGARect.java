package com.opensource.svgaplayer;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lcom/opensource/svgaplayer/SVGARect;", "", "x", "", "y", "width", "height", "(DDDD)V", "getHeight", "()D", "getWidth", "getX", "getY", "library_release"}, k = 1, mv = {1, 1, 6})
public final class SVGARect {
    private final double a;
    private final double b;
    private final double c;
    private final double d;

    public SVGARect(double d, double d2, double d3, double d4) {
        this.a = d;
        this.b = d2;
        this.c = d3;
        this.d = d4;
    }

    public final double getHeight() {
        return this.d;
    }

    public final double getWidth() {
        return this.c;
    }

    public final double getX() {
        return this.a;
    }

    public final double getY() {
        return this.b;
    }
}
