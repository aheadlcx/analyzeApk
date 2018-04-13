package com.getkeepsafe.relinker.a;

import com.getkeepsafe.relinker.a.c.b;
import com.getkeepsafe.relinker.a.c.d;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class i extends d {
    public i(f fVar, b bVar, int i) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.a = fVar.c(allocate, (bVar.d + ((long) (bVar.g * i))) + 28);
    }
}
