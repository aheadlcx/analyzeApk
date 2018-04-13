package com.loc;

import java.util.concurrent.Callable;

final class dl implements Callable<Void> {
    final /* synthetic */ be a;

    dl(be beVar) {
        this.a = beVar;
    }

    private Void a() throws Exception {
        synchronized (this.a) {
            if (this.a.k == null) {
            } else {
                this.a.m();
                if (this.a.k()) {
                    this.a.j();
                    this.a.n = 0;
                }
            }
        }
        return null;
    }

    public final /* synthetic */ Object call() throws Exception {
        return a();
    }
}
