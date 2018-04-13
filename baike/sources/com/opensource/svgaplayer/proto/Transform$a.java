package com.opensource.svgaplayer.proto;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import java.io.IOException;

final class Transform$a extends ProtoAdapter<Transform> {
    public Transform$a() {
        super(FieldEncoding.LENGTH_DELIMITED, Transform.class);
    }

    public int encodedSize(Transform transform) {
        return (((((ProtoAdapter.FLOAT.encodedSizeWithTag(1, transform.a) + ProtoAdapter.FLOAT.encodedSizeWithTag(2, transform.b)) + ProtoAdapter.FLOAT.encodedSizeWithTag(3, transform.c)) + ProtoAdapter.FLOAT.encodedSizeWithTag(4, transform.d)) + ProtoAdapter.FLOAT.encodedSizeWithTag(5, transform.tx)) + ProtoAdapter.FLOAT.encodedSizeWithTag(6, transform.ty)) + transform.unknownFields().size();
    }

    public void encode(ProtoWriter protoWriter, Transform transform) throws IOException {
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, transform.a);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, transform.b);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, transform.c);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, transform.d);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 5, transform.tx);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 6, transform.ty);
        protoWriter.writeBytes(transform.unknownFields());
    }

    public Transform decode(ProtoReader protoReader) throws IOException {
        Transform$Builder transform$Builder = new Transform$Builder();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        transform$Builder.a((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 2:
                        transform$Builder.b((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 3:
                        transform$Builder.c((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 4:
                        transform$Builder.d((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 5:
                        transform$Builder.tx((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 6:
                        transform$Builder.ty((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    default:
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        transform$Builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
            protoReader.endMessage(beginMessage);
            return transform$Builder.build();
        }
    }

    public Transform redact(Transform transform) {
        Transform$Builder newBuilder = transform.newBuilder();
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }
}
