package org.apache.commons.httpclient;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class WireLogOutputStream extends FilterOutputStream {
    private OutputStream out;
    private Wire wire;

    public WireLogOutputStream(OutputStream outputStream, Wire wire) {
        super(outputStream);
        this.out = outputStream;
        this.wire = wire;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        this.wire.output(bArr, i, i2);
    }

    public void write(int i) throws IOException {
        this.out.write(i);
        this.wire.output(i);
    }

    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        this.wire.output(bArr);
    }
}
