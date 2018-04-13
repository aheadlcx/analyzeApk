package com.flurry.android;

import java.io.DataInput;

public final class AdImage extends aj {
    long a;
    int b;
    int c;
    String d;
    byte[] e;

    AdImage() {
    }

    AdImage(DataInput dataInput) {
        a(dataInput);
    }

    public final long getId() {
        return this.a;
    }

    public final int getWidth() {
        return this.b;
    }

    public final int getHeight() {
        return this.c;
    }

    public final String getMimeType() {
        return this.d;
    }

    public final byte[] getImageData() {
        return this.e;
    }

    final void a(DataInput dataInput) {
        this.a = dataInput.readLong();
        this.b = dataInput.readInt();
        this.c = dataInput.readInt();
        this.d = dataInput.readUTF();
        this.e = new byte[dataInput.readInt()];
        dataInput.readFully(this.e);
    }
}
