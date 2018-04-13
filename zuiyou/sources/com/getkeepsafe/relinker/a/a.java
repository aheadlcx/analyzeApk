package com.getkeepsafe.relinker.a;

import com.getkeepsafe.relinker.a.c.b;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class a extends com.getkeepsafe.relinker.a.c.a {
    public a(f fVar, b bVar, long j, int i) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = ((long) (i * 8)) + j;
        this.a = fVar.c(allocate, j2);
        this.b = fVar.c(allocate, j2 + 4);
    }
}
