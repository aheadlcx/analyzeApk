package com.opensource.svgaplayer.proto;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import java.io.IOException;

final class Layout$a extends ProtoAdapter<Layout> {
    public Layout$a() {
        super(FieldEncoding.LENGTH_DELIMITED, Layout.class);
    }

    public int encodedSize(Layout layout) {
        return (((ProtoAdapter.FLOAT.encodedSizeWithTag(1, layout.x) + ProtoAdapter.FLOAT.encodedSizeWithTag(2, layout.y)) + ProtoAdapter.FLOAT.encodedSizeWithTag(3, layout.width)) + ProtoAdapter.FLOAT.encodedSizeWithTag(4, layout.height)) + layout.unknownFields().size();
    }

    public void encode(ProtoWriter protoWriter, Layout layout) throws IOException {
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, layout.x);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, layout.y);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, layout.width);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, layout.height);
        protoWriter.writeBytes(layout.unknownFields());
    }

    public Layout decode(ProtoReader protoReader) throws IOException {
        Layout$Builder layout$Builder = new Layout$Builder();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        layout$Builder.x((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 2:
                        layout$Builder.y((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 3:
                        layout$Builder.width((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 4:
                        layout$Builder.height((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    default:
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        layout$Builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
            protoReader.endMessage(beginMessage);
            return layout$Builder.build();
        }
    }

    public Layout redact(Layout layout) {
        Layout$Builder newBuilder = layout.newBuilder();
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }
}
