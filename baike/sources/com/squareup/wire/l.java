package com.squareup.wire;

import java.io.IOException;

final class l extends ProtoAdapter<Integer> {
    l(FieldEncoding fieldEncoding, Class cls) {
        super(fieldEncoding, cls);
    }

    public int encodedSize(Integer num) {
        return ProtoWriter.c(num.intValue());
    }

    public void encode(ProtoWriter protoWriter, Integer num) throws IOException {
        protoWriter.writeVarint32(num.intValue());
    }

    public Integer decode(ProtoReader protoReader) throws IOException {
        return Integer.valueOf(protoReader.readVarint32());
    }
}
