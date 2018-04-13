package com.opensource.svgaplayer.proto;

import com.opensource.svgaplayer.proto.ShapeEntity.EllipseArgs;
import com.opensource.svgaplayer.proto.ShapeEntity.RectArgs;
import com.opensource.svgaplayer.proto.ShapeEntity.ShapeArgs;
import com.opensource.svgaplayer.proto.ShapeEntity.ShapeStyle;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoAdapter.EnumConstantNotFoundException;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import java.io.IOException;

final class ShapeEntity$a extends ProtoAdapter<ShapeEntity> {
    public ShapeEntity$a() {
        super(FieldEncoding.LENGTH_DELIMITED, ShapeEntity.class);
    }

    public int encodedSize(ShapeEntity shapeEntity) {
        return (((((ShapeEntity$ShapeType.ADAPTER.encodedSizeWithTag(1, shapeEntity.type) + ShapeStyle.ADAPTER.encodedSizeWithTag(10, shapeEntity.styles)) + Transform.ADAPTER.encodedSizeWithTag(11, shapeEntity.transform)) + ShapeArgs.ADAPTER.encodedSizeWithTag(2, shapeEntity.shape)) + RectArgs.ADAPTER.encodedSizeWithTag(3, shapeEntity.rect)) + EllipseArgs.ADAPTER.encodedSizeWithTag(4, shapeEntity.ellipse)) + shapeEntity.unknownFields().size();
    }

    public void encode(ProtoWriter protoWriter, ShapeEntity shapeEntity) throws IOException {
        ShapeEntity$ShapeType.ADAPTER.encodeWithTag(protoWriter, 1, shapeEntity.type);
        ShapeStyle.ADAPTER.encodeWithTag(protoWriter, 10, shapeEntity.styles);
        Transform.ADAPTER.encodeWithTag(protoWriter, 11, shapeEntity.transform);
        ShapeArgs.ADAPTER.encodeWithTag(protoWriter, 2, shapeEntity.shape);
        RectArgs.ADAPTER.encodeWithTag(protoWriter, 3, shapeEntity.rect);
        EllipseArgs.ADAPTER.encodeWithTag(protoWriter, 4, shapeEntity.ellipse);
        protoWriter.writeBytes(shapeEntity.unknownFields());
    }

    public ShapeEntity decode(ProtoReader protoReader) throws IOException {
        ShapeEntity$Builder shapeEntity$Builder = new ShapeEntity$Builder();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        try {
                            shapeEntity$Builder.type((ShapeEntity$ShapeType) ShapeEntity$ShapeType.ADAPTER.decode(protoReader));
                            break;
                        } catch (EnumConstantNotFoundException e) {
                            shapeEntity$Builder.addUnknownField(nextTag, FieldEncoding.VARINT, Long.valueOf((long) e.value));
                            break;
                        }
                    case 2:
                        shapeEntity$Builder.shape((ShapeArgs) ShapeArgs.ADAPTER.decode(protoReader));
                        break;
                    case 3:
                        shapeEntity$Builder.rect((RectArgs) RectArgs.ADAPTER.decode(protoReader));
                        break;
                    case 4:
                        shapeEntity$Builder.ellipse((EllipseArgs) EllipseArgs.ADAPTER.decode(protoReader));
                        break;
                    case 10:
                        shapeEntity$Builder.styles((ShapeStyle) ShapeStyle.ADAPTER.decode(protoReader));
                        break;
                    case 11:
                        shapeEntity$Builder.transform((Transform) Transform.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        shapeEntity$Builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
            protoReader.endMessage(beginMessage);
            return shapeEntity$Builder.build();
        }
    }

    public ShapeEntity redact(ShapeEntity shapeEntity) {
        ShapeEntity$Builder newBuilder = shapeEntity.newBuilder();
        if (newBuilder.styles != null) {
            newBuilder.styles = (ShapeStyle) ShapeStyle.ADAPTER.redact(newBuilder.styles);
        }
        if (newBuilder.transform != null) {
            newBuilder.transform = (Transform) Transform.ADAPTER.redact(newBuilder.transform);
        }
        if (newBuilder.shape != null) {
            newBuilder.shape = (ShapeArgs) ShapeArgs.ADAPTER.redact(newBuilder.shape);
        }
        if (newBuilder.rect != null) {
            newBuilder.rect = (RectArgs) RectArgs.ADAPTER.redact(newBuilder.rect);
        }
        if (newBuilder.ellipse != null) {
            newBuilder.ellipse = (EllipseArgs) EllipseArgs.ADAPTER.redact(newBuilder.ellipse);
        }
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }
}
