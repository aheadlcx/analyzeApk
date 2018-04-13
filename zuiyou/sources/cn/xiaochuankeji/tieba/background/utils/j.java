package cn.xiaochuankeji.tieba.background.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class j {
    public final char[] a = new char[]{'R', 'I', 'F', 'F'};
    public int b;
    public char[] c = new char[]{'W', 'A', 'V', 'E'};
    public char[] d = new char[]{'f', 'm', 't', ' '};
    public int e;
    public short f;
    public short g;
    public int h;
    public int i;
    public short j;
    public short k;
    public char[] l = new char[]{'d', 'a', 't', 'a'};
    public int m;

    public byte[] a() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(byteArrayOutputStream, this.a);
        b(byteArrayOutputStream, this.b);
        a(byteArrayOutputStream, this.c);
        a(byteArrayOutputStream, this.d);
        b(byteArrayOutputStream, this.e);
        a(byteArrayOutputStream, this.f);
        a(byteArrayOutputStream, this.g);
        b(byteArrayOutputStream, this.h);
        b(byteArrayOutputStream, this.i);
        a(byteArrayOutputStream, this.j);
        a(byteArrayOutputStream, this.k);
        a(byteArrayOutputStream, this.l);
        b(byteArrayOutputStream, this.m);
        byteArrayOutputStream.flush();
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return toByteArray;
    }

    private void a(ByteArrayOutputStream byteArrayOutputStream, int i) throws IOException {
        byteArrayOutputStream.write(new byte[]{(byte) ((i << 16) >> 24), (byte) ((i << 24) >> 24)});
    }

    private void b(ByteArrayOutputStream byteArrayOutputStream, int i) throws IOException {
        byteArrayOutputStream.write(new byte[]{(byte) (i >> 24), (byte) ((i << 8) >> 24), (byte) ((i << 16) >> 24), (byte) ((i << 24) >> 24)});
    }

    private void a(ByteArrayOutputStream byteArrayOutputStream, char[] cArr) {
        for (char write : cArr) {
            byteArrayOutputStream.write(write);
        }
    }
}
