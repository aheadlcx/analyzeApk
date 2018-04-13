package com.opensource.svgaplayer.proto;

import com.opensource.svgaplayer.proto.ShapeEntity.ShapeStyle;
import com.opensource.svgaplayer.proto.ShapeEntity.ShapeStyle.RGBAColor;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoAdapter.EnumConstantNotFoundException;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import java.io.IOException;

final class ShapeEntity$ShapeStyle$a extends ProtoAdapter<ShapeStyle> {
    public ShapeEntity$ShapeStyle$a() {
        super(FieldEncoding.LENGTH_DELIMITED, ShapeStyle.class);
    }

    public int encodedSize(ShapeStyle shapeStyle) {
        return ((((((((RGBAColor.ADAPTER.encodedSizeWithTag(1, shapeStyle.fill) + RGBAColor.ADAPTER.encodedSizeWithTag(2, shapeStyle.stroke)) + ProtoAdapter.FLOAT.encodedSizeWithTag(3, shapeStyle.strokeWidth)) + ShapeEntity$ShapeStyle$LineCap.ADAPTER.encodedSizeWithTag(4, shapeStyle.lineCap)) + ShapeEntity$ShapeStyle$LineJoin.ADAPTER.encodedSizeWithTag(5, shapeStyle.lineJoin)) + ProtoAdapter.FLOAT.encodedSizeWithTag(6, shapeStyle.miterLimit)) + ProtoAdapter.FLOAT.encodedSizeWithTag(7, shapeStyle.lineDashI)) + ProtoAdapter.FLOAT.encodedSizeWithTag(8, shapeStyle.lineDashII)) + ProtoAdapter.FLOAT.encodedSizeWithTag(9, shapeStyle.lineDashIII)) + shapeStyle.unknownFields().size();
    }

    public void encode(ProtoWriter protoWriter, ShapeStyle shapeStyle) throws IOException {
        RGBAColor.ADAPTER.encodeWithTag(protoWriter, 1, shapeStyle.fill);
        RGBAColor.ADAPTER.encodeWithTag(protoWriter, 2, shapeStyle.stroke);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, shapeStyle.strokeWidth);
        ShapeEntity$ShapeStyle$LineCap.ADAPTER.encodeWithTag(protoWriter, 4, shapeStyle.lineCap);
        ShapeEntity$ShapeStyle$LineJoin.ADAPTER.encodeWithTag(protoWriter, 5, shapeStyle.lineJoin);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 6, shapeStyle.miterLimit);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 7, shapeStyle.lineDashI);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 8, shapeStyle.lineDashII);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 9, shapeStyle.lineDashIII);
        protoWriter.writeBytes(shapeStyle.unknownFields());
    }

    public ShapeStyle decode(ProtoReader protoReader) throws IOException {
        ShapeEntity$ShapeStyle$Builder shapeEntity$ShapeStyle$Builder = new ShapeEntity$ShapeStyle$Builder();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        shapeEntity$ShapeStyle$Builder.fill((RGBAColor) RGBAColor.ADAPTER.decode(protoReader));
                        break;
                    case 2:
                        shapeEntity$ShapeStyle$Builder.stroke((RGBAColor) RGBAColor.ADAPTER.decode(protoReader));
                        break;
                    case 3:
                        shapeEntity$ShapeStyle$Builder.strokeWidth((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 4:
                        try {
                            shapeEntity$ShapeStyle$Builder.lineCap((ShapeEntity$ShapeStyle$LineCap) ShapeEntity$ShapeStyle$LineCap.ADAPTER.decode(protoReader));
                            break;
                        } catch (EnumConstantNotFoundException e) {
                            shapeEntity$ShapeStyle$Builder.addUnknownField(nextTag, FieldEncoding.VARINT, Long.valueOf((long) e.value));
                            break;
                        }
                    case 5:
                        try {
                            shapeEntity$ShapeStyle$Builder.lineJoin((ShapeEntity$ShapeStyle$LineJoin) ShapeEntity$ShapeStyle$LineJoin.ADAPTER.decode(protoReader));
                            break;
                        } catch (EnumConstantNotFoundException e2) {
                            shapeEntity$ShapeStyle$Builder.addUnknownField(nextTag, FieldEncoding.VARINT, Long.valueOf((long) e2.value));
                            break;
                        }
                    case 6:
                        shapeEntity$ShapeStyle$Builder.miterLimit((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 7:
                        shapeEntity$ShapeStyle$Builder.lineDashI((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 8:
                        shapeEntity$ShapeStyle$Builder.lineDashII((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 9:
                        shapeEntity$ShapeStyle$Builder.lineDashIII((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    default:
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        shapeEntity$ShapeStyle$Builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
            protoReader.endMessage(beginMessage);
            return shapeEntity$ShapeStyle$Builder.build();
        }
    }

    public ShapeStyle redact(ShapeStyle shapeStyle) {
        ShapeEntity$ShapeStyle$Builder newBuilder = shapeStyle.newBuilder();
        if (newBuilder.fill != null) {
            newBuilder.fill = (RGBAColor) RGBAColor.ADAPTER.redact(newBuilder.fill);
        }
        if (newBuilder.stroke != null) {
            newBuilder.stroke = (RGBAColor) RGBAColor.ADAPTER.redact(newBuilder.stroke);
        }
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }
}
