package com.microquation.linkedme.android.a;

import android.annotation.TargetApi;
import java.util.concurrent.TimeUnit;

@TargetApi(9)
public class b {
    private long a = TimeUnit.SECONDS.toMillis(10);
    private float b = 0.0f;
    private long c = TimeUnit.SECONDS.toMillis(60);
    private long d = TimeUnit.SECONDS.toMillis(30);
    private long e = TimeUnit.MINUTES.toMillis(0);

    public long a() {
        return this.c;
    }

    public void a(float f) {
        this.b = f;
    }

    public void a(long j) {
        this.e = j;
    }

    public long b() {
        return this.d;
    }

    public void b(long j) {
        this.c = j;
    }

    public long c() {
        return this.a;
    }

    public void c(long j) {
        this.d = j;
    }

    public float d() {
        return this.b;
    }

    public void d(long j) {
        this.a = j;
    }
}
