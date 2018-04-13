package com.squareup.wire;

import java.io.IOException;

final class n extends ProtoAdapter<Integer> {
    n(FieldEncoding fieldEncoding, Class cls) {
        super(fieldEncoding, cls);
    }

    public int encodedSize(Integer num) {
        return 4;
    }

    public void encode(ProtoWriter protoWriter, Integer num) throws IOException {
        protoWriter.writeFixed32(num.intValue());
    }

    public Integer decode(ProtoReader protoReader) throws IOException {
        return Integer.valueOf(protoReader.readFixed32());
    }
}
