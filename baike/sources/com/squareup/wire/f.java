package com.squareup.wire;

import java.io.IOException;

final class f extends ProtoAdapter<Double> {
    f(FieldEncoding fieldEncoding, Class cls) {
        super(fieldEncoding, cls);
    }

    public int encodedSize(Double d) {
        return 8;
    }

    public void encode(ProtoWriter protoWriter, Double d) throws IOException {
        protoWriter.writeFixed64(Double.doubleToLongBits(d.doubleValue()));
    }

    public Double decode(ProtoReader protoReader) throws IOException {
        return Double.valueOf(Double.longBitsToDouble(protoReader.readFixed64()));
    }
}
