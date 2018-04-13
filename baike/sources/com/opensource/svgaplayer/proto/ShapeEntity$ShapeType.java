package com.opensource.svgaplayer.proto;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;

public enum ShapeEntity$ShapeType implements WireEnum {
    SHAPE(0),
    RECT(1),
    ELLIPSE(2),
    KEEP(3);
    
    public static final ProtoAdapter<ShapeEntity$ShapeType> ADAPTER = null;
    private final int a;

    private static final class a extends EnumAdapter<ShapeEntity$ShapeType> {
        protected /* synthetic */ WireEnum b(int i) {
            return a(i);
        }

        a() {
            super(ShapeEntity$ShapeType.class);
        }

        protected ShapeEntity$ShapeType a(int i) {
            return ShapeEntity$ShapeType.fromValue(i);
        }
    }

    static {
        ADAPTER = new a();
    }

    private ShapeEntity$ShapeType(int i) {
        this.a = i;
    }

    public static ShapeEntity$ShapeType fromValue(int i) {
        switch (i) {
            case 0:
                return SHAPE;
            case 1:
                return RECT;
            case 2:
                return ELLIPSE;
            case 3:
                return KEEP;
            default:
                return null;
        }
    }

    public int getValue() {
        return this.a;
    }
}
