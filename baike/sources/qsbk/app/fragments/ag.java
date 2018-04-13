package qsbk.app.fragments;

import java.io.File;

class ag implements Runnable {
    final /* synthetic */ File a;
    final /* synthetic */ af b;

    ag(af afVar, File file) {
        this.b = afVar;
        this.a = file;
    }

    public void run() {
        this.b.a.a(this.a);
    }
}
