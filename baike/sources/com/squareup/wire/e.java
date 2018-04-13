package com.squareup.wire;

import java.io.IOException;

final class e extends ProtoAdapter<Float> {
    e(FieldEncoding fieldEncoding, Class cls) {
        super(fieldEncoding, cls);
    }

    public int encodedSize(Float f) {
        return 4;
    }

    public void encode(ProtoWriter protoWriter, Float f) throws IOException {
        protoWriter.writeFixed32(Float.floatToIntBits(f.floatValue()));
    }

    public Float decode(ProtoReader protoReader) throws IOException {
        return Float.valueOf(Float.intBitsToFloat(protoReader.readFixed32()));
    }
}
