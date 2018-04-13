package com.squareup.wire;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

class i extends ProtoAdapter<List<E>> {
    final /* synthetic */ ProtoAdapter d;

    i(ProtoAdapter protoAdapter, FieldEncoding fieldEncoding, Class cls) {
        this.d = protoAdapter;
        super(fieldEncoding, cls);
    }

    public void encodeWithTag(ProtoWriter protoWriter, int i, List<E> list) throws IOException {
        if (!list.isEmpty()) {
            super.encodeWithTag(protoWriter, i, list);
        }
    }

    public int encodedSize(List<E> list) {
        int i = 0;
        int i2 = 0;
        while (i < list.size()) {
            i2 += this.d.encodedSize(list.get(i));
            i++;
        }
        return i2;
    }

    public int encodedSizeWithTag(int i, List<E> list) {
        return list.isEmpty() ? 0 : super.encodedSizeWithTag(i, list);
    }

    public void encode(ProtoWriter protoWriter, List<E> list) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            this.d.encode(protoWriter, list.get(i));
        }
    }

    public List<E> decode(ProtoReader protoReader) throws IOException {
        return Collections.singletonList(this.d.decode(protoReader));
    }

    public List<E> redact(List<E> list) {
        return Collections.emptyList();
    }
}
