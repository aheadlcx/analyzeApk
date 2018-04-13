package com.opensource.svgaplayer.proto;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;

public enum ShapeEntity$ShapeStyle$LineCap implements WireEnum {
    LineCap_BUTT(0),
    LineCap_ROUND(1),
    LineCap_SQUARE(2);
    
    public static final ProtoAdapter<ShapeEntity$ShapeStyle$LineCap> ADAPTER = null;
    private final int a;

    private static final class a extends EnumAdapter<ShapeEntity$ShapeStyle$LineCap> {
        protected /* synthetic */ WireEnum b(int i) {
            return a(i);
        }

        a() {
            super(ShapeEntity$ShapeStyle$LineCap.class);
        }

        protected ShapeEntity$ShapeStyle$LineCap a(int i) {
            return ShapeEntity$ShapeStyle$LineCap.fromValue(i);
        }
    }

    static {
        ADAPTER = new a();
    }

    private ShapeEntity$ShapeStyle$LineCap(int i) {
        this.a = i;
    }

    public static ShapeEntity$ShapeStyle$LineCap fromValue(int i) {
        switch (i) {
            case 0:
                return LineCap_BUTT;
            case 1:
                return LineCap_ROUND;
            case 2:
                return LineCap_SQUARE;
            default:
                return null;
        }
    }

    public int getValue() {
        return this.a;
    }
}
