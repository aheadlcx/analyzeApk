package com.iflytek.cloud.record;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class d {
    private RandomAccessFile a;
    private short b;
    private int c;
    private short d;

    public d(File file, int i) throws IOException {
        a(file, (short) 1, i, (short) 16);
    }

    private boolean a(File file, short s, int i, short s2) throws IOException {
        if (file == null) {
            return false;
        }
        this.a = new RandomAccessFile(file, "rw");
        if (this.a == null) {
            return false;
        }
        this.b = s;
        this.c = i;
        this.d = s2;
        this.a.write(new byte[44]);
        return true;
    }

    public int a() throws IOException {
        return (int) (this.a.length() - 44);
    }

    public void a(int i) throws IOException {
        this.a.write(i >> 0);
        this.a.write(i >> 8);
        this.a.write(i >> 16);
        this.a.write(i >> 24);
    }

    public void a(String str) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            this.a.write(str.charAt(i));
        }
    }

    public void a(short s) throws IOException {
        this.a.write(s >> 0);
        this.a.write(s >> 8);
    }

    public void b() throws IOException {
        this.a.seek(0);
        a("RIFF");
        a(a() + 36);
        a("WAVE");
        a("fmt ");
        a(16);
        a((short) 1);
        a(this.b);
        a(this.c);
        a(((this.b * this.c) * this.d) / 8);
        a((short) ((this.b * this.d) / 8));
        a(this.d);
        a("data");
        a(a());
    }

    public void c() throws IOException {
        if (this.a != null) {
            this.a.close();
            this.a = null;
        }
    }
}
