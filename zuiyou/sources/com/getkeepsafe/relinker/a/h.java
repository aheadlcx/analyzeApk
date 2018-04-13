package com.getkeepsafe.relinker.a;

import com.getkeepsafe.relinker.a.c.b;
import com.getkeepsafe.relinker.a.c.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class h extends c {
    public h(f fVar, b bVar, long j) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = bVar.c + (((long) bVar.e) * j);
        this.a = fVar.c(allocate, j2);
        this.b = fVar.b(allocate, 8 + j2);
        this.c = fVar.b(allocate, 16 + j2);
        this.d = fVar.b(allocate, j2 + 40);
    }
}
