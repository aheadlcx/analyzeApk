package com.qiniu.utils;

import com.qiniu.utils.InputStreamAt.ByteInput;
import com.qiniu.utils.InputStreamAt.Input;
import com.qiniu.utils.InputStreamAt.UriInput;
import java.io.IOException;

class c implements Input {
    final /* synthetic */ int a;
    final /* synthetic */ UriInput b;
    private byte[] c = this.b.a(this.a);
    private ByteInput d = new ByteInput(this.c);

    c(UriInput uriInput, int i) throws IOException {
        this.b = uriInput;
        this.a = i;
    }

    public byte[] readAll() throws IOException {
        return this.c;
    }

    public byte[] readNext(int i) throws IOException {
        return this.d.readNext(i).readAll();
    }
}
