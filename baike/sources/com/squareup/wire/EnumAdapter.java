package com.squareup.wire;

import com.squareup.wire.ProtoAdapter.EnumConstantNotFoundException;
import java.io.IOException;

public abstract class EnumAdapter<E extends WireEnum> extends ProtoAdapter<E> {
    protected abstract E b(int i);

    protected EnumAdapter(Class<E> cls) {
        super(FieldEncoding.VARINT, cls);
    }

    public final int encodedSize(E e) {
        return ProtoWriter.c(e.getValue());
    }

    public final void encode(ProtoWriter protoWriter, E e) throws IOException {
        protoWriter.writeVarint32(e.getValue());
    }

    public final E decode(ProtoReader protoReader) throws IOException {
        int readVarint32 = protoReader.readVarint32();
        E b = b(readVarint32);
        if (b != null) {
            return b;
        }
        throw new EnumConstantNotFoundException(readVarint32, this.a);
    }
}
