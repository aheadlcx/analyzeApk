package com.iflytek.cloud.record;

import android.content.Context;
import android.media.AudioTrack;
import android.os.MemoryFile;
import com.iflytek.cloud.thirdparty.bv;
import com.iflytek.cloud.thirdparty.cb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class b {
    private final int a = 2;
    private final int b = 1;
    private final int c = 16000;
    private final int d = 60;
    private final int e = 500;
    private final int f = 1920000;
    private int g = 1920000;
    private ArrayList<a> h = null;
    private Context i = null;
    private int j = 16000;
    private volatile long k = 0;
    private MemoryFile l = null;
    private volatile long m = 0;
    private volatile int n = 0;
    private a o = null;
    private String p = "";
    private String q = null;
    private byte[] r = null;
    private int s = 0;
    private int t = 0;
    private int u = 100;
    private final float v = 0.95f;
    private boolean w = true;

    public class a {
        long a;
        long b;
        int c;
        int d;
        final /* synthetic */ b e;

        public a(b bVar, long j, long j2, int i, int i2) {
            this.e = bVar;
            this.a = j;
            this.b = j2;
            this.c = i;
            this.d = i2;
        }
    }

    public b(Context context, int i, int i2, String str, int i3) {
        this.i = context;
        this.k = 0;
        this.h = new ArrayList();
        this.m = 0;
        this.j = i;
        this.q = str;
        this.u = i3;
        this.g = (((this.j * 2) * 1) * i2) + 1920000;
        cb.a("min audio seconds: " + i2 + ", max audio buf size: " + this.g);
    }

    private void a(byte[] bArr) throws IOException {
        if (bArr != null && bArr.length != 0) {
            if (this.l == null) {
                this.p = k();
                this.l = new MemoryFile(this.p, this.g);
                this.l.allowPurging(false);
            }
            this.l.writeBytes(bArr, 0, (int) this.m, bArr.length);
            this.m += (long) bArr.length;
        }
    }

    private void c(int i) throws IOException {
        int i2;
        if (this.r == null) {
            this.r = new byte[(i * 10)];
        }
        int length = this.r.length;
        int i3 = (int) (this.m - ((long) this.n));
        if (i3 < length) {
            length = i3;
            i2 = i3;
        } else {
            i2 = length;
        }
        this.l.readBytes(this.r, this.n, 0, length);
        this.n = length + this.n;
        this.s = 0;
        this.t = i2;
        cb.a("readAudio leave, dataSize=" + i2 + ", bufLen=" + i3);
    }

    private String k() {
        return bv.a(this.i) + System.currentTimeMillis() + "tts.pcm";
    }

    public int a() {
        return this.j;
    }

    public void a(AudioTrack audioTrack, int i) throws IOException {
        if (this.s >= this.t) {
            c(i);
        }
        int i2 = i * 2 > this.t - this.s ? this.t - this.s : i;
        audioTrack.write(this.r, this.s, i2);
        this.s = i2 + this.s;
        if (f() && i()) {
            b(audioTrack, i);
        }
    }

    public void a(ArrayList<byte[]> arrayList, int i, int i2, int i3) throws IOException {
        cb.b("buffer percent = " + i + ", beg=" + i2 + ", end=" + i3);
        a aVar = new a(this, this.m, this.m, i2, i3);
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            a((byte[]) arrayList.get(i4));
        }
        aVar.b = this.m;
        this.k = (long) i;
        synchronized (this.h) {
            this.h.add(aVar);
        }
        cb.b("allSize = " + this.m + " maxSize=" + this.g);
    }

    public void a(boolean z) {
        this.w = z;
    }

    public boolean a(int i) {
        return ((float) this.k) > 0.95f * ((float) this.u) ? true : this.m / 32 >= ((long) i) && 0 < this.m;
    }

    public boolean a(String str) {
        cb.a("save to local: format = " + str + " totalSize = " + this.m + " maxSize=" + this.g);
        return bv.a(this.l, this.m, this.q) ? bv.a(str, this.q, a()) : false;
    }

    public int b() {
        return this.l != null ? this.l.length() : 0;
    }

    public void b(AudioTrack audioTrack, int i) {
        int i2 = (((this.j * 500) * 2) * 1) / 1000;
        cb.a("mBuffer.writeTrack writeTrackBlankBlock size: " + i2);
        audioTrack.write(new byte[i2], 0, i2);
    }

    public boolean b(int i) {
        return ((long) i) <= ((this.m - ((long) this.n)) + ((long) this.t)) - ((long) this.s);
    }

    public void c() throws IOException {
        this.n = 0;
        this.o = null;
        if (this.h.size() > 0) {
            this.o = (a) this.h.get(0);
        }
    }

    public int d() {
        return this.m <= 0 ? 0 : (int) ((((long) (this.n - (this.t - this.s))) * this.k) / this.m);
    }

    public a e() {
        if (this.o != null) {
            long j = (long) (this.n - (this.t - this.s));
            if (j >= this.o.a && j <= this.o.b) {
                return this.o;
            }
            synchronized (this.h) {
                Iterator it = this.h.iterator();
                while (it.hasNext()) {
                    this.o = (a) it.next();
                    if (j >= this.o.a && j <= this.o.b) {
                        a aVar = this.o;
                        return aVar;
                    }
                }
            }
        }
        return null;
    }

    public boolean f() {
        return ((long) this.u) == this.k && ((long) this.n) >= this.m && this.s >= this.t;
    }

    protected void finalize() throws Throwable {
        j();
        super.finalize();
    }

    public boolean g() {
        return ((long) this.n) < this.m || this.s < this.t;
    }

    public boolean h() {
        return ((long) this.u) == this.k;
    }

    public boolean i() {
        return this.w;
    }

    public void j() {
        cb.a("deleteFile");
        try {
            if (this.l != null) {
                this.l.close();
                this.l = null;
            }
        } catch (Throwable e) {
            cb.a(e);
        }
    }
}
