package com.squareup.wire;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

class j extends ProtoAdapter<List<E>> {
    final /* synthetic */ ProtoAdapter d;

    j(ProtoAdapter protoAdapter, FieldEncoding fieldEncoding, Class cls) {
        this.d = protoAdapter;
        super(fieldEncoding, cls);
    }

    public int encodedSize(List<E> list) {
        throw new UnsupportedOperationException("Repeated values can only be sized with a tag.");
    }

    public int encodedSizeWithTag(int i, List<E> list) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < list.size()) {
            i3 += this.d.encodedSizeWithTag(i, list.get(i2));
            i2++;
        }
        return i3;
    }

    public void encode(ProtoWriter protoWriter, List<E> list) {
        throw new UnsupportedOperationException("Repeated values can only be encoded with a tag.");
    }

    public void encodeWithTag(ProtoWriter protoWriter, int i, List<E> list) throws IOException {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.d.encodeWithTag(protoWriter, i, list.get(i2));
        }
    }

    public List<E> decode(ProtoReader protoReader) throws IOException {
        return Collections.singletonList(this.d.decode(protoReader));
    }

    public List<E> redact(List<E> list) {
        return Collections.emptyList();
    }
}
