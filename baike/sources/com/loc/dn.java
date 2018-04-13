package com.loc;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

final class dn extends ByteArrayOutputStream {
    final /* synthetic */ bg a;

    dn(bg bgVar, int i) {
        this.a = bgVar;
        super(i);
    }

    public final String toString() {
        int i = (this.count <= 0 || this.buf[this.count - 1] != (byte) 13) ? this.count : this.count - 1;
        try {
            return new String(this.buf, 0, i, this.a.b.name());
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
