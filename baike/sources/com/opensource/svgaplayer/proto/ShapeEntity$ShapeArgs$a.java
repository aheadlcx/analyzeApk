package com.opensource.svgaplayer.proto;

import com.opensource.svgaplayer.proto.ShapeEntity.ShapeArgs;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import java.io.IOException;

final class ShapeEntity$ShapeArgs$a extends ProtoAdapter<ShapeArgs> {
    public ShapeEntity$ShapeArgs$a() {
        super(FieldEncoding.LENGTH_DELIMITED, ShapeArgs.class);
    }

    public int encodedSize(ShapeArgs shapeArgs) {
        return ProtoAdapter.STRING.encodedSizeWithTag(1, shapeArgs.d) + shapeArgs.unknownFields().size();
    }

    public void encode(ProtoWriter protoWriter, ShapeArgs shapeArgs) throws IOException {
        ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, shapeArgs.d);
        protoWriter.writeBytes(shapeArgs.unknownFields());
    }

    public ShapeArgs decode(ProtoReader protoReader) throws IOException {
        ShapeEntity$ShapeArgs$Builder shapeEntity$ShapeArgs$Builder = new ShapeEntity$ShapeArgs$Builder();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        shapeEntity$ShapeArgs$Builder.d((String) ProtoAdapter.STRING.decode(protoReader));
                        break;
                    default:
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        shapeEntity$ShapeArgs$Builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
            protoReader.endMessage(beginMessage);
            return shapeEntity$ShapeArgs$Builder.build();
        }
    }

    public ShapeArgs redact(ShapeArgs shapeArgs) {
        ShapeEntity$ShapeArgs$Builder newBuilder = shapeArgs.newBuilder();
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }
}
