package com.getkeepsafe.relinker.a;

import com.getkeepsafe.relinker.a.c.b;
import com.getkeepsafe.relinker.a.c.d;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class j extends d {
    public j(f fVar, b bVar, int i) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.a = fVar.c(allocate, (bVar.d + ((long) (bVar.g * i))) + 44);
    }
}
