package com.iflytek.cloud.thirdparty;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class aa {
    private ConcurrentLinkedQueue<y> a = new ConcurrentLinkedQueue();
    private long b;
    private int c = Integer.MAX_VALUE;

    public aa(int i) {
        this.c = i;
    }

    public void a() {
        this.a.clear();
        this.b = 0;
    }

    public void a(y yVar) {
        if (yVar != null && yVar.a() != -1) {
            if (this.a.size() >= this.c) {
                this.a.poll();
            } else {
                this.b += (long) yVar.a();
            }
            this.a.add(yVar);
        }
    }

    public y b() {
        y yVar = (y) this.a.poll();
        if (yVar != null) {
            this.b -= (long) yVar.a();
        }
        return yVar;
    }

    public long c() {
        return this.b;
    }

    public y d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ce ceVar = null;
        while (true) {
            try {
                y b = b();
                if (b == null) {
                    break;
                }
                ceVar = b.e;
                byteArrayOutputStream.write(b.c);
            } catch (IOException e) {
                ce ceVar2 = ceVar;
                IOException iOException = e;
                y yVar = null;
            }
        }
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e2) {
            IOException iOException2 = e2;
            yVar = toByteArray;
            ceVar2 = ceVar;
            iOException = iOException2;
            iOException.printStackTrace();
            ceVar = ceVar2;
            toByteArray = yVar;
            return toByteArray != null ? new y(toByteArray, ceVar) : null;
        }
        if (toByteArray != null) {
        }
    }
}
