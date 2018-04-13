package com.squareup.wire;

import java.io.IOException;

final class d extends ProtoAdapter<Boolean> {
    d(FieldEncoding fieldEncoding, Class cls) {
        super(fieldEncoding, cls);
    }

    public int encodedSize(Boolean bool) {
        return 1;
    }

    public void encode(ProtoWriter protoWriter, Boolean bool) throws IOException {
        protoWriter.writeVarint32(bool.booleanValue() ? 1 : 0);
    }

    public Boolean decode(ProtoReader protoReader) throws IOException {
        int readVarint32 = protoReader.readVarint32();
        if (readVarint32 == 0) {
            return Boolean.FALSE;
        }
        if (readVarint32 == 1) {
            return Boolean.TRUE;
        }
        throw new IOException(String.format("Invalid boolean value 0x%02x", new Object[]{Integer.valueOf(readVarint32)}));
    }
}
