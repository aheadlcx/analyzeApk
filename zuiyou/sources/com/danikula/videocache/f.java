package com.danikula.videocache;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import com.danikula.videocache.a.g;
import com.danikula.videocache.a.h;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class f {
    private final Object a;
    private final ExecutorService b;
    private final Map<String, g> c;
    private final ServerSocket d;
    private final int e;
    private final Thread f;
    private final c g;
    private final j h;
    private boolean i;

    public static final class a {
        private File a;
        private com.danikula.videocache.a.c b = new com.danikula.videocache.a.f();
        private com.danikula.videocache.a.a c = new h(IjkMediaMeta.AV_CH_STEREO_LEFT);
        private com.danikula.videocache.d.c d;
        private com.danikula.videocache.b.b e = new com.danikula.videocache.b.a();

        public a(Context context) {
            this.d = com.danikula.videocache.d.d.a(context);
            this.a = p.a(context);
        }

        public a a(File file) {
            this.a = (File) k.a((Object) file);
            return this;
        }

        public a a(com.danikula.videocache.a.c cVar) {
            this.b = (com.danikula.videocache.a.c) k.a((Object) cVar);
            return this;
        }

        public a a(int i) {
            this.c = new g(i);
            return this;
        }

        public a a(com.danikula.videocache.a.a aVar) {
            this.c = (com.danikula.videocache.a.a) k.a((Object) aVar);
            return this;
        }

        public a a(com.danikula.videocache.b.b bVar) {
            this.e = (com.danikula.videocache.b.b) k.a((Object) bVar);
            return this;
        }

        public f a() {
            return new f(b());
        }

        private c b() {
            return new c(this.a, this.b, this.c, this.d, this.e);
        }
    }

    private final class b implements Runnable {
        final /* synthetic */ f a;
        private final String b;

        public b(f fVar, String str) {
            this.a = fVar;
            this.b = str;
        }

        public void run() {
            this.a.h(this.b);
        }
    }

    private final class c implements Runnable {
        final /* synthetic */ f a;
        private final Socket b;

        public c(f fVar, Socket socket) {
            this.a = fVar;
            this.b = socket;
        }

        public void run() {
            this.a.a(this.b);
        }
    }

    private final class d implements Runnable {
        final /* synthetic */ f a;
        private final CountDownLatch b;

        public d(f fVar, CountDownLatch countDownLatch) {
            this.a = fVar;
            this.b = countDownLatch;
        }

        public void run() {
            this.b.countDown();
            this.a.c();
        }
    }

    public f(Context context) {
        this(new a(context).b());
    }

    private f(c cVar) {
        Throwable e;
        this.a = new Object();
        this.b = Executors.newFixedThreadPool(8);
        this.c = new ConcurrentHashMap();
        this.g = (c) k.a((Object) cVar);
        try {
            this.d = new ServerSocket(0, 8, InetAddress.getByName("127.0.0.1"));
            this.e = this.d.getLocalPort();
            i.a("127.0.0.1", this.e);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.f = new Thread(new d(this, countDownLatch), "HttpProxyCacheServer");
            this.f.start();
            countDownLatch.await();
            this.h = new j("127.0.0.1", this.e);
            com.izuiyou.a.a.b.c("Proxy cache server started. Is it alive? " + b());
        } catch (IOException e2) {
            e = e2;
            this.b.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        } catch (InterruptedException e3) {
            e = e3;
            this.b.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        }
    }

    public String a(String str, boolean z) {
        if (!z || !a(str)) {
            return b() ? g(str) : str;
        } else {
            File c = c(str);
            a(c);
            return Uri.fromFile(c).toString();
        }
    }

    private boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getState() == State.CONNECTED && activeNetworkInfo.getType() == 0) {
                return true;
            }
        }
        return false;
    }

    public void a(Context context, String str) {
        if ((this.i || !a(context)) && !a(str)) {
            this.b.submit(new b(this, str));
        }
    }

    public void a(b bVar, String str) {
        k.a(bVar, str);
        synchronized (this.a) {
            try {
                d(str).a(bVar);
            } catch (ProxyCacheException e) {
                com.izuiyou.a.a.b.c("Error registering cache listener", new Object[]{e});
            }
        }
    }

    public void a(b bVar) {
        k.a((Object) bVar);
        synchronized (this.a) {
            for (g b : this.c.values()) {
                b.b(bVar);
            }
        }
    }

    public boolean a(String str) {
        k.a((Object) str, "Url can't be null!");
        return c(str).exists();
    }

    public boolean b(String str) {
        k.a((Object) str, "Url can't be null!");
        try {
            com.danikula.videocache.a.b bVar = new com.danikula.videocache.a.b(this.g.a(str), this.g.c);
            File e = bVar.e();
            long j = 0;
            if (e != null) {
                j = e.length();
            }
            bVar.b();
            if (j >= 512000) {
                return true;
            }
            return false;
        } catch (ProxyCacheException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private boolean b() {
        return this.h.a(3, 15);
    }

    private String g(String str) {
        return String.format(Locale.US, "http://%s:%d/%s", new Object[]{"127.0.0.1", Integer.valueOf(this.e), m.b(str)});
    }

    public File c(String str) {
        return new File(this.g.a, this.g.b.a(str));
    }

    private void a(File file) {
        try {
            this.g.c.a(file);
        } catch (IOException e) {
            com.izuiyou.a.a.b.d("Error touching file " + file, new Object[]{e});
        }
    }

    public void a() {
        if (this.c != null && !this.c.isEmpty()) {
            synchronized (this.a) {
                for (g b : this.c.values()) {
                    b.b();
                }
                this.c.clear();
            }
        }
    }

    private void c() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket accept = this.d.accept();
                com.izuiyou.a.a.b.b("Accept new socket " + accept);
                this.b.submit(new c(this, accept));
            } catch (Throwable e) {
                a(new ProxyCacheException("Error during waiting connection", e));
                return;
            }
        }
    }

    private void a(Socket socket) {
        Throwable e;
        try {
            d a = d.a(socket.getInputStream());
            com.izuiyou.a.a.b.b("Request to cache proxy:" + a);
            String c = m.c(a.a);
            if (this.h.a(c)) {
                this.h.a(socket);
            } else {
                d(c).a(a, socket);
            }
            b(socket);
            com.izuiyou.a.a.b.b("Opened connections: " + d());
        } catch (SocketException e2) {
            com.izuiyou.a.a.b.b("Closing socket… Socket is closed by client.");
            b(socket);
            com.izuiyou.a.a.b.b("Opened connections: " + d());
        } catch (ProxyCacheException e3) {
            e = e3;
            a(new ProxyCacheException("Error processing request", e));
            b(socket);
            com.izuiyou.a.a.b.b("Opened connections: " + d());
        } catch (IOException e4) {
            e = e4;
            a(new ProxyCacheException("Error processing request", e));
            b(socket);
            com.izuiyou.a.a.b.b("Opened connections: " + d());
        } catch (Throwable th) {
            b(socket);
            com.izuiyou.a.a.b.b("Opened connections: " + d());
        }
    }

    private void h(String str) {
        Throwable e;
        try {
            d(str).a();
            return;
        } catch (ProxyCacheException e2) {
            e = e2;
        } catch (IOException e3) {
            e = e3;
        }
        a(new ProxyCacheException("Error processing preload", e));
    }

    public g d(String str) throws ProxyCacheException {
        g gVar;
        synchronized (this.a) {
            gVar = (g) this.c.get(str);
            if (gVar == null) {
                gVar = new g(str, this.g);
                this.c.put(str, gVar);
            }
        }
        return gVar;
    }

    public boolean e(String str) {
        boolean z;
        synchronized (this.a) {
            z = ((g) this.c.get(str)) != null;
        }
        return z;
    }

    private int d() {
        int i;
        synchronized (this.a) {
            i = 0;
            for (g c : this.c.values()) {
                i = c.c() + i;
            }
        }
        return i;
    }

    private void b(Socket socket) {
        c(socket);
        d(socket);
        e(socket);
    }

    private void c(Socket socket) {
        try {
            if (!socket.isInputShutdown()) {
                socket.shutdownInput();
            }
        } catch (SocketException e) {
            com.izuiyou.a.a.b.b("Releasing input stream… Socket is closed by client.");
        } catch (Throwable e2) {
            a(new ProxyCacheException("Error closing socket input stream", e2));
        }
    }

    private void d(Socket socket) {
        try {
            if (!socket.isOutputShutdown()) {
                socket.shutdownOutput();
            }
        } catch (IOException e) {
            com.izuiyou.a.a.b.d("Failed to close socket on proxy side. It seems client have already closed connection.", new Object[]{e});
        }
    }

    private void e(Socket socket) {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (Throwable e) {
            a(new ProxyCacheException("Error closing socket", e));
        }
    }

    private void a(Throwable th) {
        com.izuiyou.a.a.b.d("HttpProxyCacheServer error", new Object[]{th});
    }

    public void f(String str) {
        try {
            g d = d(str);
            if (d != null && d.c() > 0) {
                d.b();
            }
            this.c.remove(str);
        } catch (Exception e) {
            com.izuiyou.a.a.b.e("shutdown client error: " + e);
        }
    }
}
