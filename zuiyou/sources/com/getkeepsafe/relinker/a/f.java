package com.getkeepsafe.relinker.a;

import com.getkeepsafe.relinker.a.c.a;
import com.getkeepsafe.relinker.a.c.b;
import com.getkeepsafe.relinker.a.c.c;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class f implements c, Closeable {
    private final int a = 1179403647;
    private final FileChannel b;

    public f(File file) throws FileNotFoundException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.b = new FileInputStream(file).getChannel();
    }

    public b a() throws IOException {
        this.b.position(0);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (c(allocate, 0) != 1179403647) {
            throw new IllegalArgumentException("Invalid ELF Magic!");
        }
        short e = e(allocate, 4);
        boolean z = e(allocate, 5) == (short) 2;
        if (e == (short) 1) {
            return new d(z, this);
        }
        if (e == (short) 2) {
            return new e(z, this);
        }
        throw new IllegalStateException("Invalid class type!");
    }

    public List<String> b() throws IOException {
        long j;
        this.b.position(0);
        List<String> arrayList = new ArrayList();
        b a = a();
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(a.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = (long) a.f;
        if (j2 == 65535) {
            j2 = a.a(0).a;
        }
        for (j = 0; j < j2; j++) {
            c a2 = a.a(j);
            if (a2.a == 2) {
                j = a2.b;
                break;
            }
        }
        j = 0;
        if (j == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        int i = 0;
        List<Long> arrayList2 = new ArrayList();
        long j3 = 0;
        a a3;
        do {
            a3 = a.a(j, i);
            if (a3.a == 1) {
                arrayList2.add(Long.valueOf(a3.b));
            } else if (a3.a == 5) {
                j3 = a3.b;
            }
            i++;
        } while (a3.a != 0);
        if (j3 == 0) {
            throw new IllegalStateException("String table offset not found!");
        }
        j2 = a(a, j2, j3);
        for (Long longValue : arrayList2) {
            arrayList.add(a(allocate, longValue.longValue() + j2));
        }
        return arrayList;
    }

    private long a(b bVar, long j, long j2) throws IOException {
        for (long j3 = 0; j3 < j; j3++) {
            c a = bVar.a(j3);
            if (a.a == 1 && a.c <= j2 && j2 <= a.c + a.d) {
                return (j2 - a.c) + a.b;
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    public void close() throws IOException {
        this.b.close();
    }

    protected String a(ByteBuffer byteBuffer, long j) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short e = e(byteBuffer, j);
            if (e == (short) 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append((char) e);
            j = j2;
        }
    }

    protected long b(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 8);
        return byteBuffer.getLong();
    }

    protected long c(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 4);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    protected int d(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 2);
        return byteBuffer.getShort() & 65535;
    }

    protected short e(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 1);
        return (short) (byteBuffer.get() & 255);
    }

    protected void a(ByteBuffer byteBuffer, long j, int i) throws IOException {
        byteBuffer.position(0);
        byteBuffer.limit(i);
        long j2 = 0;
        while (j2 < ((long) i)) {
            int read = this.b.read(byteBuffer, j + j2);
            if (read == -1) {
                throw new EOFException();
            }
            j2 += (long) read;
        }
        byteBuffer.position(0);
    }
}
