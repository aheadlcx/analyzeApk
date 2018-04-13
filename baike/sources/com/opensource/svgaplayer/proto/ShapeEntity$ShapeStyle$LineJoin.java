package com.opensource.svgaplayer.proto;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;

public enum ShapeEntity$ShapeStyle$LineJoin implements WireEnum {
    LineJoin_MITER(0),
    LineJoin_ROUND(1),
    LineJoin_BEVEL(2);
    
    public static final ProtoAdapter<ShapeEntity$ShapeStyle$LineJoin> ADAPTER = null;
    private final int a;

    private static final class a extends EnumAdapter<ShapeEntity$ShapeStyle$LineJoin> {
        protected /* synthetic */ WireEnum b(int i) {
            return a(i);
        }

        a() {
            super(ShapeEntity$ShapeStyle$LineJoin.class);
        }

        protected ShapeEntity$ShapeStyle$LineJoin a(int i) {
            return ShapeEntity$ShapeStyle$LineJoin.fromValue(i);
        }
    }

    static {
        ADAPTER = new a();
    }

    private ShapeEntity$ShapeStyle$LineJoin(int i) {
        this.a = i;
    }

    public static ShapeEntity$ShapeStyle$LineJoin fromValue(int i) {
        switch (i) {
            case 0:
                return LineJoin_MITER;
            case 1:
                return LineJoin_ROUND;
            case 2:
                return LineJoin_BEVEL;
            default:
                return null;
        }
    }

    public int getValue() {
        return this.a;
    }
}
