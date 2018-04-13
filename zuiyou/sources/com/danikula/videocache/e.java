package com.danikula.videocache;

import android.text.TextUtils;
import android.util.Log;
import com.danikula.videocache.a.b;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Locale;

class e extends l {
    private final h a;
    private final b b;
    private b c;

    public e(h hVar, b bVar) {
        super(hVar, bVar);
        this.b = bVar;
        this.a = hVar;
    }

    public void a(b bVar) {
        this.c = bVar;
    }

    public void a(d dVar, Socket socket) throws IOException, ProxyCacheException {
        OutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        String b = b(dVar);
        bufferedOutputStream.write(b.getBytes("UTF-8"));
        Log.d("HttpUrlSource", "proxy header:" + b);
        long j = dVar.b;
        if (a(dVar)) {
            a(bufferedOutputStream, j);
        } else {
            b(bufferedOutputStream, j);
        }
    }

    public void a() throws IOException, ProxyCacheException {
        long a = this.b.a();
        if (a < 512000) {
            byte[] bArr = new byte[5120];
            do {
                int a2 = a(bArr, a, bArr.length);
                if (a2 == -1) {
                    break;
                }
                a += (long) a2;
            } while (a <= 512000);
        }
        b();
    }

    private boolean a(d dVar) throws ProxyCacheException {
        boolean z;
        long a = this.a.a();
        if (a > 0) {
            z = true;
        } else {
            z = false;
        }
        long a2 = this.b.a();
        if (z && dVar.c && ((float) dVar.b) > ((float) a2) + (((float) a) * 0.2f)) {
            return false;
        }
        return true;
    }

    private String b(d dVar) throws IOException, ProxyCacheException {
        int i;
        long j;
        int i2;
        int i3 = !TextUtils.isEmpty(this.a.c()) ? 1 : 0;
        long a = this.b.d() ? this.b.a() : this.a.a();
        Log.d("mingliang", "content length:" + a);
        if (a >= 0) {
            i = 1;
        } else {
            i = 0;
        }
        if (dVar.c) {
            j = a - dVar.b;
        } else {
            j = a;
        }
        if (i == 0 || !dVar.c) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        return (dVar.c ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n") + "Accept-Ranges: bytes\n" + (i != 0 ? a("Content-Length: %d\n", Long.valueOf(j)) : "") + (i2 != 0 ? a("Content-Range: bytes %d-%d/%d\n", Long.valueOf(dVar.b), Long.valueOf(a - 1), Long.valueOf(a)) : "") + (i3 != 0 ? a("Content-Type: %s\n", r10) : "") + "\n";
    }

    private void a(OutputStream outputStream, long j) throws ProxyCacheException, IOException {
        byte[] bArr = new byte[5120];
        while (true) {
            int a = a(bArr, j, bArr.length);
            if (a != -1) {
                outputStream.write(bArr, 0, a);
                j += (long) a;
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    private void b(OutputStream outputStream, long j) throws ProxyCacheException, IOException {
        h hVar = new h(this.a);
        try {
            hVar.a((long) ((int) j));
            byte[] bArr = new byte[5120];
            while (true) {
                int a = hVar.a(bArr);
                if (a == -1) {
                    break;
                }
                outputStream.write(bArr, 0, a);
                j += (long) a;
            }
            outputStream.flush();
        } finally {
            hVar.b();
        }
    }

    private String a(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    protected void a(int i) {
        if (this.c != null) {
            this.c.a(this.b.a, this.a.d(), i);
        }
    }
}
