package com.opensource.svgaplayer.proto;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.internal.Internal;
import java.io.IOException;

final class FrameEntity$a extends ProtoAdapter<FrameEntity> {
    public FrameEntity$a() {
        super(FieldEncoding.LENGTH_DELIMITED, FrameEntity.class);
    }

    public int encodedSize(FrameEntity frameEntity) {
        return ((((ProtoAdapter.FLOAT.encodedSizeWithTag(1, frameEntity.alpha) + Layout.ADAPTER.encodedSizeWithTag(2, frameEntity.layout)) + Transform.ADAPTER.encodedSizeWithTag(3, frameEntity.transform)) + ProtoAdapter.STRING.encodedSizeWithTag(4, frameEntity.clipPath)) + ShapeEntity.ADAPTER.asRepeated().encodedSizeWithTag(5, frameEntity.shapes)) + frameEntity.unknownFields().size();
    }

    public void encode(ProtoWriter protoWriter, FrameEntity frameEntity) throws IOException {
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, frameEntity.alpha);
        Layout.ADAPTER.encodeWithTag(protoWriter, 2, frameEntity.layout);
        Transform.ADAPTER.encodeWithTag(protoWriter, 3, frameEntity.transform);
        ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, frameEntity.clipPath);
        ShapeEntity.ADAPTER.asRepeated().encodeWithTag(protoWriter, 5, frameEntity.shapes);
        protoWriter.writeBytes(frameEntity.unknownFields());
    }

    public FrameEntity decode(ProtoReader protoReader) throws IOException {
        FrameEntity$Builder frameEntity$Builder = new FrameEntity$Builder();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        frameEntity$Builder.alpha((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 2:
                        frameEntity$Builder.layout((Layout) Layout.ADAPTER.decode(protoReader));
                        break;
                    case 3:
                        frameEntity$Builder.transform((Transform) Transform.ADAPTER.decode(protoReader));
                        break;
                    case 4:
                        frameEntity$Builder.clipPath((String) ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 5:
                        frameEntity$Builder.shapes.add(ShapeEntity.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        frameEntity$Builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
            protoReader.endMessage(beginMessage);
            return frameEntity$Builder.build();
        }
    }

    public FrameEntity redact(FrameEntity frameEntity) {
        FrameEntity$Builder newBuilder = frameEntity.newBuilder();
        if (newBuilder.layout != null) {
            newBuilder.layout = (Layout) Layout.ADAPTER.redact(newBuilder.layout);
        }
        if (newBuilder.transform != null) {
            newBuilder.transform = (Transform) Transform.ADAPTER.redact(newBuilder.transform);
        }
        Internal.redactElements(newBuilder.shapes, ShapeEntity.ADAPTER);
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }
}
