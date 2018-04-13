package com.getkeepsafe.relinker.a;

import com.getkeepsafe.relinker.a.c.a;
import com.getkeepsafe.relinker.a.c.b;
import com.getkeepsafe.relinker.a.c.c;
import com.getkeepsafe.relinker.a.c.d;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class e extends b {
    private final f j;

    public e(boolean z, f fVar) throws IOException {
        this.a = z;
        this.j = fVar;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.b = fVar.d(allocate, 16);
        this.c = fVar.b(allocate, 32);
        this.d = fVar.b(allocate, 40);
        this.e = fVar.d(allocate, 54);
        this.f = fVar.d(allocate, 56);
        this.g = fVar.d(allocate, 58);
        this.h = fVar.d(allocate, 60);
        this.i = fVar.d(allocate, 62);
    }

    public d a(int i) throws IOException {
        return new j(this.j, this, i);
    }

    public c a(long j) throws IOException {
        return new h(this.j, this, j);
    }

    public a a(long j, int i) throws IOException {
        return new b(this.j, this, j, i);
    }
}
