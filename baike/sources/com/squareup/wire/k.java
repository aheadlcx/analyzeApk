package com.squareup.wire;

import java.io.IOException;

final class k extends ProtoAdapter<Integer> {
    k(FieldEncoding fieldEncoding, Class cls) {
        super(fieldEncoding, cls);
    }

    public int encodedSize(Integer num) {
        return ProtoWriter.b(num.intValue());
    }

    public void encode(ProtoWriter protoWriter, Integer num) throws IOException {
        protoWriter.f(num.intValue());
    }

    public Integer decode(ProtoReader protoReader) throws IOException {
        return Integer.valueOf(protoReader.readVarint32());
    }
}
