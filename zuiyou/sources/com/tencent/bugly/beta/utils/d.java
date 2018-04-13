package com.tencent.bugly.beta.utils;

public class d {
    a a = null;
    private String b = null;
    private long c = 0;
    private long d = 0;

    public d(String str, long j, long j2) {
        this.b = str;
        this.c = j;
        this.d = j2;
    }

    private boolean b() {
        if (this.c == 0 || this.d == 0) {
            return false;
        }
        return true;
    }

    public synchronized void a() {
        if (this.a != null) {
            this.a.a();
            this.a = null;
        }
    }

    private synchronized boolean c() {
        boolean z = false;
        synchronized (this) {
            if (b()) {
                if (this.a == null) {
                    try {
                        this.a = new a(this.b);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                z = true;
            }
        }
        return z;
    }

    private synchronized String b(long j) {
        String str = null;
        synchronized (this) {
            if (this.a != null) {
                StringBuffer stringBuffer = new StringBuffer();
                try {
                    this.a.b(this.c);
                    this.a.b(j);
                    long j2 = 0;
                    while (true) {
                        byte b = this.a.b();
                        if (b == (byte) 0) {
                            break;
                        }
                        stringBuffer.append((char) b);
                        j2++;
                    }
                    this.a.a();
                    this.a = new a(this.b);
                } catch (Exception e) {
                    this.a = null;
                    e.printStackTrace();
                }
                str = stringBuffer.toString();
            }
        }
        return str;
    }

    public synchronized String a(long j) {
        String str = null;
        synchronized (this) {
            if (j >= 0) {
                if (j < this.d) {
                    if (this.a != null || c()) {
                        str = b(j);
                    }
                }
            }
        }
        return str;
    }
}
