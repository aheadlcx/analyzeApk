package com.squareup.wire;

import java.io.IOException;

final class r extends ProtoAdapter<Long> {
    r(FieldEncoding fieldEncoding, Class cls) {
        super(fieldEncoding, cls);
    }

    public int encodedSize(Long l) {
        return 8;
    }

    public void encode(ProtoWriter protoWriter, Long l) throws IOException {
        protoWriter.writeFixed64(l.longValue());
    }

    public Long decode(ProtoReader protoReader) throws IOException {
        return Long.valueOf(protoReader.readFixed64());
    }
}
