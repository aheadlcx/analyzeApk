package com.getkeepsafe.relinker.a;

import com.getkeepsafe.relinker.a.c.a;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class b extends a {
    public b(f fVar, com.getkeepsafe.relinker.a.c.b bVar, long j, int i) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = ((long) (i * 16)) + j;
        this.a = fVar.b(allocate, j2);
        this.b = fVar.b(allocate, j2 + 8);
    }
}
