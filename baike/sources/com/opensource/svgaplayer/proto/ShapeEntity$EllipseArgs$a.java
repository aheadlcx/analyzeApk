package com.opensource.svgaplayer.proto;

import com.opensource.svgaplayer.proto.ShapeEntity.EllipseArgs;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import java.io.IOException;

final class ShapeEntity$EllipseArgs$a extends ProtoAdapter<EllipseArgs> {
    public ShapeEntity$EllipseArgs$a() {
        super(FieldEncoding.LENGTH_DELIMITED, EllipseArgs.class);
    }

    public int encodedSize(EllipseArgs ellipseArgs) {
        return (((ProtoAdapter.FLOAT.encodedSizeWithTag(1, ellipseArgs.x) + ProtoAdapter.FLOAT.encodedSizeWithTag(2, ellipseArgs.y)) + ProtoAdapter.FLOAT.encodedSizeWithTag(3, ellipseArgs.radiusX)) + ProtoAdapter.FLOAT.encodedSizeWithTag(4, ellipseArgs.radiusY)) + ellipseArgs.unknownFields().size();
    }

    public void encode(ProtoWriter protoWriter, EllipseArgs ellipseArgs) throws IOException {
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, ellipseArgs.x);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, ellipseArgs.y);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, ellipseArgs.radiusX);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, ellipseArgs.radiusY);
        protoWriter.writeBytes(ellipseArgs.unknownFields());
    }

    public EllipseArgs decode(ProtoReader protoReader) throws IOException {
        ShapeEntity$EllipseArgs$Builder shapeEntity$EllipseArgs$Builder = new ShapeEntity$EllipseArgs$Builder();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        shapeEntity$EllipseArgs$Builder.x((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 2:
                        shapeEntity$EllipseArgs$Builder.y((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 3:
                        shapeEntity$EllipseArgs$Builder.radiusX((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 4:
                        shapeEntity$EllipseArgs$Builder.radiusY((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    default:
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        shapeEntity$EllipseArgs$Builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
            protoReader.endMessage(beginMessage);
            return shapeEntity$EllipseArgs$Builder.build();
        }
    }

    public EllipseArgs redact(EllipseArgs ellipseArgs) {
        ShapeEntity$EllipseArgs$Builder newBuilder = ellipseArgs.newBuilder();
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }
}
