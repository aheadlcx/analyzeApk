package com.danikula.videocache;

import com.izuiyou.a.a.b;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class j {
    private final ExecutorService a = Executors.newSingleThreadExecutor();
    private final String b;
    private final int c;

    private class a implements Callable<Boolean> {
        final /* synthetic */ j a;

        private a(j jVar) {
            this.a = jVar;
        }

        public /* synthetic */ Object call() throws Exception {
            return a();
        }

        public Boolean a() throws Exception {
            return Boolean.valueOf(this.a.b());
        }
    }

    j(String str, int i) {
        this.b = (String) k.a((Object) str);
        this.c = i;
    }

    boolean a(int i, int i2) {
        boolean z;
        InterruptedException e;
        k.a(i >= 1);
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        k.a(z);
        int i3 = 0;
        while (i3 < i) {
            try {
                if (((Boolean) this.a.submit(new a()).get((long) i2, TimeUnit.MILLISECONDS)).booleanValue()) {
                    return true;
                }
                i2 *= 2;
                i3++;
            } catch (TimeoutException e2) {
                b.d("Error pinging server (attempt: " + i3 + ", timeout: " + i2 + "). ");
            } catch (InterruptedException e3) {
                e = e3;
                b.d("Error pinging server due to unexpected error", new Object[]{e});
            } catch (ExecutionException e4) {
                e = e4;
                b.d("Error pinging server due to unexpected error", new Object[]{e});
            }
        }
        b.d(String.format(Locale.US, "Error pinging server (attempts: %d, max timeout: %d). If you see this message, please, report at https://github.com/danikula/AndroidVideoCache/issues/134. Default proxies are: %s", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2 / 2), a()}), new Object[]{new ProxyCacheException(String.format(Locale.US, "Error pinging server (attempts: %d, max timeout: %d). If you see this message, please, report at https://github.com/danikula/AndroidVideoCache/issues/134. Default proxies are: %s", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2 / 2), a()}))});
        return false;
    }

    private List<Proxy> a() {
        try {
            return ProxySelector.getDefault().select(new URI(c()));
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    boolean a(String str) {
        return "ping".equals(str);
    }

    void a(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\n\n".getBytes());
        outputStream.write("ping ok".getBytes());
    }

    private boolean b() throws ProxyCacheException {
        boolean equals;
        h hVar = new h(c());
        try {
            byte[] bytes = "ping ok".getBytes();
            hVar.a(0);
            byte[] bArr = new byte[bytes.length];
            hVar.a(bArr);
            equals = Arrays.equals(bytes, bArr);
            b.c("Ping response: `" + new String(bArr) + "`, pinged? " + equals);
            return equals;
        } catch (ProxyCacheException e) {
            equals = e;
            b.d("Error reading ping response", new Object[]{equals});
            return false;
        } finally {
            hVar.b();
        }
    }

    private String c() {
        return String.format(Locale.US, "http://%s:%d/%s", new Object[]{this.b, Integer.valueOf(this.c), "ping"});
    }
}
