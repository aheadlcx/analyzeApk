package com.facebook.imagepipeline.producers;

import java.io.IOException;
import java.io.InputStream;

class ae$1 implements af$a {
    final /* synthetic */ s a;
    final /* synthetic */ ae b;

    ae$1(ae aeVar, s sVar) {
        this.b = aeVar;
        this.a = sVar;
    }

    public void a(InputStream inputStream, int i) throws IOException {
        ae.a(this.b, this.a, inputStream, i);
    }

    public void a(Throwable th) {
        ae.a(this.b, this.a, th);
    }

    public void a() {
        ae.a(this.b, this.a);
    }
}
