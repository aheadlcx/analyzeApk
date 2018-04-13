package com.qiniu.utils;

import com.qiniu.utils.InputStreamAt.Input;
import java.io.IOException;

final class b implements Input {
    final /* synthetic */ InputStreamAt a;
    final /* synthetic */ int b;
    private final long c = this.a.a();
    private int d = 0;

    b(InputStreamAt inputStreamAt, int i) {
        this.a = inputStreamAt;
        this.b = i;
    }

    public byte[] readAll() throws IOException {
        return this.a.a(this.c, this.b);
    }

    public byte[] readNext(int i) throws IOException {
        if (this.d + i > this.b) {
            i = this.b - this.d;
        }
        if (i <= 0) {
            return new byte[0];
        }
        byte[] a = this.a.a(this.c + ((long) this.d), i);
        this.d += i;
        return a;
    }
}
