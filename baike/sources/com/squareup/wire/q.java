package com.squareup.wire;

import java.io.IOException;

final class q extends ProtoAdapter<Long> {
    q(FieldEncoding fieldEncoding, Class cls) {
        super(fieldEncoding, cls);
    }

    public int encodedSize(Long l) {
        return ProtoWriter.a(ProtoWriter.b(l.longValue()));
    }

    public void encode(ProtoWriter protoWriter, Long l) throws IOException {
        protoWriter.writeVarint64(ProtoWriter.b(l.longValue()));
    }

    public Long decode(ProtoReader protoReader) throws IOException {
        return Long.valueOf(ProtoWriter.c(protoReader.readVarint64()));
    }
}
