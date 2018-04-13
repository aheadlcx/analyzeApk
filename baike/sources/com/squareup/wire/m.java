package com.squareup.wire;

import java.io.IOException;

final class m extends ProtoAdapter<Integer> {
    m(FieldEncoding fieldEncoding, Class cls) {
        super(fieldEncoding, cls);
    }

    public int encodedSize(Integer num) {
        return ProtoWriter.c(ProtoWriter.d(num.intValue()));
    }

    public void encode(ProtoWriter protoWriter, Integer num) throws IOException {
        protoWriter.writeVarint32(ProtoWriter.d(num.intValue()));
    }

    public Integer decode(ProtoReader protoReader) throws IOException {
        return Integer.valueOf(ProtoWriter.e(protoReader.readVarint32()));
    }
}
