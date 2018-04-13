package com.squareup.wire;

import java.io.IOException;

final class o extends ProtoAdapter<Long> {
    o(FieldEncoding fieldEncoding, Class cls) {
        super(fieldEncoding, cls);
    }

    public int encodedSize(Long l) {
        return ProtoWriter.a(l.longValue());
    }

    public void encode(ProtoWriter protoWriter, Long l) throws IOException {
        protoWriter.writeVarint64(l.longValue());
    }

    public Long decode(ProtoReader protoReader) throws IOException {
        return Long.valueOf(protoReader.readVarint64());
    }
}
