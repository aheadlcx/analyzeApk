package com.squareup.wire;

import java.io.IOException;
import okio.ByteString;

final class h extends ProtoAdapter<ByteString> {
    h(FieldEncoding fieldEncoding, Class cls) {
        super(fieldEncoding, cls);
    }

    public int encodedSize(ByteString byteString) {
        return byteString.size();
    }

    public void encode(ProtoWriter protoWriter, ByteString byteString) throws IOException {
        protoWriter.writeBytes(byteString);
    }

    public ByteString decode(ProtoReader protoReader) throws IOException {
        return protoReader.readBytes();
    }
}
