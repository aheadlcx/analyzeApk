package com.facebook.imagepipeline.d;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class a implements e {
    private final Executor a = Executors.newFixedThreadPool(2);
    private final Executor b;
    private final Executor c;
    private final Executor d;

    public a(int i) {
        ThreadFactory kVar = new k(10);
        this.b = Executors.newFixedThreadPool(i, kVar);
        this.c = Executors.newFixedThreadPool(i, kVar);
        this.d = Executors.newFixedThreadPool(1, kVar);
    }

    public Executor a() {
        return this.a;
    }

    public Executor b() {
        return this.a;
    }

    public Executor c() {
        return this.b;
    }

    public Executor d() {
        return this.c;
    }

    public Executor e() {
        return this.d;
    }
}
