package com.getkeepsafe.relinker.a;

import com.getkeepsafe.relinker.a.c.b;
import com.getkeepsafe.relinker.a.c.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class g extends c {
    public g(f fVar, b bVar, long j) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = bVar.c + (((long) bVar.e) * j);
        this.a = fVar.c(allocate, j2);
        this.b = fVar.c(allocate, 4 + j2);
        this.c = fVar.c(allocate, 8 + j2);
        this.d = fVar.c(allocate, j2 + 20);
    }
}
