package com.flurry.android;

import android.os.SystemClock;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

final class i {
    private String a;
    private Map b;
    private long c;
    private boolean d;
    private long e;
    private byte[] f;

    public i(String str, Map map, long j, boolean z) {
        this.a = str;
        this.b = map;
        this.c = j;
        this.d = z;
    }

    public final boolean a(String str) {
        return this.d && this.e == 0 && this.a.equals(str);
    }

    public final void a() {
        this.e = SystemClock.elapsedRealtime() - this.c;
    }

    public final byte[] b() {
        Throwable th;
        if (this.f == null) {
            Closeable closeable = null;
            Closeable dataOutputStream;
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    dataOutputStream.writeUTF(this.a);
                    if (this.b == null) {
                        dataOutputStream.writeShort(0);
                    } else {
                        dataOutputStream.writeShort(this.b.size());
                        for (Entry entry : this.b.entrySet()) {
                            dataOutputStream.writeUTF(r.a((String) entry.getKey(), 255));
                            dataOutputStream.writeUTF(r.a((String) entry.getValue(), 255));
                        }
                    }
                    dataOutputStream.writeLong(this.c);
                    dataOutputStream.writeLong(this.e);
                    dataOutputStream.flush();
                    this.f = byteArrayOutputStream.toByteArray();
                    r.a(dataOutputStream);
                } catch (IOException e) {
                    closeable = dataOutputStream;
                    try {
                        this.f = new byte[0];
                        r.a(closeable);
                        return this.f;
                    } catch (Throwable th2) {
                        dataOutputStream = closeable;
                        th = th2;
                        r.a(dataOutputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    r.a(dataOutputStream);
                    throw th;
                }
            } catch (IOException e2) {
                this.f = new byte[0];
                r.a(closeable);
                return this.f;
            } catch (Throwable th22) {
                dataOutputStream = null;
                th = th22;
                r.a(dataOutputStream);
                throw th;
            }
        }
        return this.f;
    }
}
