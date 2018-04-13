package com.squareup.wire;

import java.io.IOException;

final class g extends ProtoAdapter<String> {
    g(FieldEncoding fieldEncoding, Class cls) {
        super(fieldEncoding, cls);
    }

    public int encodedSize(String str) {
        return ProtoWriter.a(str);
    }

    public void encode(ProtoWriter protoWriter, String str) throws IOException {
        protoWriter.writeString(str);
    }

    public String decode(ProtoReader protoReader) throws IOException {
        return protoReader.readString();
    }
}
