package com.opensource.svgaplayer.proto;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.internal.Internal;
import java.io.IOException;

final class SpriteEntity$a extends ProtoAdapter<SpriteEntity> {
    public SpriteEntity$a() {
        super(FieldEncoding.LENGTH_DELIMITED, SpriteEntity.class);
    }

    public int encodedSize(SpriteEntity spriteEntity) {
        return (ProtoAdapter.STRING.encodedSizeWithTag(1, spriteEntity.imageKey) + FrameEntity.ADAPTER.asRepeated().encodedSizeWithTag(2, spriteEntity.frames)) + spriteEntity.unknownFields().size();
    }

    public void encode(ProtoWriter protoWriter, SpriteEntity spriteEntity) throws IOException {
        ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, spriteEntity.imageKey);
        FrameEntity.ADAPTER.asRepeated().encodeWithTag(protoWriter, 2, spriteEntity.frames);
        protoWriter.writeBytes(spriteEntity.unknownFields());
    }

    public SpriteEntity decode(ProtoReader protoReader) throws IOException {
        SpriteEntity$Builder spriteEntity$Builder = new SpriteEntity$Builder();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        spriteEntity$Builder.imageKey((String) ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        spriteEntity$Builder.frames.add(FrameEntity.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        spriteEntity$Builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
            protoReader.endMessage(beginMessage);
            return spriteEntity$Builder.build();
        }
    }

    public SpriteEntity redact(SpriteEntity spriteEntity) {
        SpriteEntity$Builder newBuilder = spriteEntity.newBuilder();
        Internal.redactElements(newBuilder.frames, FrameEntity.ADAPTER);
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }
}
