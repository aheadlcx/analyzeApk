package com.opensource.svgaplayer.proto;

import com.opensource.svgaplayer.proto.ShapeEntity.RectArgs;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import java.io.IOException;

final class ShapeEntity$RectArgs$a extends ProtoAdapter<RectArgs> {
    public ShapeEntity$RectArgs$a() {
        super(FieldEncoding.LENGTH_DELIMITED, RectArgs.class);
    }

    public int encodedSize(RectArgs rectArgs) {
        return ((((ProtoAdapter.FLOAT.encodedSizeWithTag(1, rectArgs.x) + ProtoAdapter.FLOAT.encodedSizeWithTag(2, rectArgs.y)) + ProtoAdapter.FLOAT.encodedSizeWithTag(3, rectArgs.width)) + ProtoAdapter.FLOAT.encodedSizeWithTag(4, rectArgs.height)) + ProtoAdapter.FLOAT.encodedSizeWithTag(5, rectArgs.cornerRadius)) + rectArgs.unknownFields().size();
    }

    public void encode(ProtoWriter protoWriter, RectArgs rectArgs) throws IOException {
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, rectArgs.x);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, rectArgs.y);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, rectArgs.width);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, rectArgs.height);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 5, rectArgs.cornerRadius);
        protoWriter.writeBytes(rectArgs.unknownFields());
    }

    public RectArgs decode(ProtoReader protoReader) throws IOException {
        ShapeEntity$RectArgs$Builder shapeEntity$RectArgs$Builder = new ShapeEntity$RectArgs$Builder();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        shapeEntity$RectArgs$Builder.x((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 2:
                        shapeEntity$RectArgs$Builder.y((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 3:
                        shapeEntity$RectArgs$Builder.width((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 4:
                        shapeEntity$RectArgs$Builder.height((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 5:
                        shapeEntity$RectArgs$Builder.cornerRadius((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    default:
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        shapeEntity$RectArgs$Builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
            protoReader.endMessage(beginMessage);
            return shapeEntity$RectArgs$Builder.build();
        }
    }

    public RectArgs redact(RectArgs rectArgs) {
        ShapeEntity$RectArgs$Builder newBuilder = rectArgs.newBuilder();
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }
}
