package com.getkeepsafe.relinker.a;

import com.getkeepsafe.relinker.a.c.a;
import com.getkeepsafe.relinker.a.c.b;
import com.getkeepsafe.relinker.a.c.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class d extends b {
    private final f j;

    public d(boolean z, f fVar) throws IOException {
        this.a = z;
        this.j = fVar;
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.b = fVar.d(allocate, 16);
        this.c = fVar.c(allocate, 28);
        this.d = fVar.c(allocate, 32);
        this.e = fVar.d(allocate, 42);
        this.f = fVar.d(allocate, 44);
        this.g = fVar.d(allocate, 46);
        this.h = fVar.d(allocate, 48);
        this.i = fVar.d(allocate, 50);
    }

    public com.getkeepsafe.relinker.a.c.d a(int i) throws IOException {
        return new i(this.j, this, i);
    }

    public c a(long j) throws IOException {
        return new g(this.j, this, j);
    }

    public a a(long j, int i) throws IOException {
        return new a(this.j, this, j, i);
    }
}
