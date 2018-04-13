package com.squareup.wire;

public enum FieldEncoding {
    VARINT(0),
    FIXED64(1),
    LENGTH_DELIMITED(2),
    FIXED32(5);
    
    final int a;

    private FieldEncoding(int i) {
        this.a = i;
    }

    public ProtoAdapter<?> rawProtoAdapter() {
        switch (b.a[ordinal()]) {
            case 1:
                return ProtoAdapter.UINT64;
            case 2:
                return ProtoAdapter.FIXED32;
            case 3:
                return ProtoAdapter.FIXED64;
            case 4:
                return ProtoAdapter.BYTES;
            default:
                throw new AssertionError();
        }
    }
}
