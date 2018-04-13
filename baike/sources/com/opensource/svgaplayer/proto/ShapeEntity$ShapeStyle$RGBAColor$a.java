package com.opensource.svgaplayer.proto;

import com.opensource.svgaplayer.proto.ShapeEntity.ShapeStyle.RGBAColor;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import java.io.IOException;

final class ShapeEntity$ShapeStyle$RGBAColor$a extends ProtoAdapter<RGBAColor> {
    public ShapeEntity$ShapeStyle$RGBAColor$a() {
        super(FieldEncoding.LENGTH_DELIMITED, RGBAColor.class);
    }

    public int encodedSize(RGBAColor rGBAColor) {
        return (((ProtoAdapter.FLOAT.encodedSizeWithTag(1, rGBAColor.r) + ProtoAdapter.FLOAT.encodedSizeWithTag(2, rGBAColor.g)) + ProtoAdapter.FLOAT.encodedSizeWithTag(3, rGBAColor.b)) + ProtoAdapter.FLOAT.encodedSizeWithTag(4, rGBAColor.a)) + rGBAColor.unknownFields().size();
    }

    public void encode(ProtoWriter protoWriter, RGBAColor rGBAColor) throws IOException {
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, rGBAColor.r);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, rGBAColor.g);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, rGBAColor.b);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, rGBAColor.a);
        protoWriter.writeBytes(rGBAColor.unknownFields());
    }

    public RGBAColor decode(ProtoReader protoReader) throws IOException {
        ShapeEntity$ShapeStyle$RGBAColor$Builder shapeEntity$ShapeStyle$RGBAColor$Builder = new ShapeEntity$ShapeStyle$RGBAColor$Builder();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        shapeEntity$ShapeStyle$RGBAColor$Builder.r((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 2:
                        shapeEntity$ShapeStyle$RGBAColor$Builder.g((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 3:
                        shapeEntity$ShapeStyle$RGBAColor$Builder.b((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 4:
                        shapeEntity$ShapeStyle$RGBAColor$Builder.a((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    default:
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        shapeEntity$ShapeStyle$RGBAColor$Builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
            protoReader.endMessage(beginMessage);
            return shapeEntity$ShapeStyle$RGBAColor$Builder.build();
        }
    }

    public RGBAColor redact(RGBAColor rGBAColor) {
        ShapeEntity$ShapeStyle$RGBAColor$Builder newBuilder = rGBAColor.newBuilder();
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }
}
