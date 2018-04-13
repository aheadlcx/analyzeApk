package com.danikula.videocache;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.danikula.videocache.a.b;
import com.meizu.cloud.pushsdk.handler.impl.model.Statics;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

final class g {
    private final AtomicInteger a = new AtomicInteger(0);
    private final String b;
    private volatile e c;
    private final List<b> d = new CopyOnWriteArrayList();
    private final b e;
    private final c f;

    private static final class a extends Handler implements b {
        private final String a;
        private final List<b> b;

        public a(String str, List<b> list) {
            super(Looper.getMainLooper());
            this.a = str;
            this.b = list;
        }

        public void a(String str, String str2, long j, int i, String str3, boolean z) {
            Message obtainMessage = obtainMessage(101);
            Bundle bundle = new Bundle();
            bundle.putString("originUrl", str);
            bundle.putString("url", str2);
            bundle.putLong(Statics.TIME, j);
            bundle.putInt("code", i);
            bundle.putString("exception", str3);
            bundle.putBoolean("cache-header", z);
            obtainMessage.setData(bundle);
            sendMessage(obtainMessage);
        }

        public void a(String str, String str2, String str3, int i, int i2, String str4) {
            Message obtainMessage = obtainMessage(103);
            Bundle bundle = new Bundle();
            bundle.putString("originUrl", str);
            bundle.putString("host", str2);
            bundle.putString("ip", str3);
            bundle.putInt("state", i);
            bundle.putInt("code", i2);
            bundle.putString("exception", str4);
            obtainMessage.setData(bundle);
            sendMessage(obtainMessage);
        }

        public void a(File file, String str, int i) {
            Message obtainMessage = obtainMessage(102);
            obtainMessage.arg1 = i;
            obtainMessage.obj = file;
            sendMessage(obtainMessage);
        }

        public void handleMessage(Message message) {
            Bundle data;
            String string;
            String string2;
            switch (message.what) {
                case 101:
                    data = message.getData();
                    string = data.getString("url");
                    string2 = data.getString("originUrl");
                    long j = data.getLong(Statics.TIME, 0);
                    int i = data.getInt("code");
                    String string3 = data.getString("exception");
                    boolean z = data.getBoolean("cache-header");
                    for (b a : this.b) {
                        a.a(string2, string, j, i, string3, z);
                    }
                    return;
                case 102:
                    for (b a2 : this.b) {
                        a2.a((File) message.obj, this.a, message.arg1);
                    }
                    return;
                case 103:
                    data = message.getData();
                    string2 = data.getString("host");
                    String string4 = data.getString("originUrl");
                    string = data.getString("ip");
                    int i2 = data.getInt("state");
                    int i3 = data.getInt("code");
                    String string5 = data.getString("exception");
                    for (b a22 : this.b) {
                        a22.a(string4, string2, string, i2, i3, string5);
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public g(String str, c cVar) {
        this.b = (String) k.a((Object) str);
        this.f = (c) k.a((Object) cVar);
        this.e = new a(str, this.d);
    }

    public void a(d dVar, Socket socket) throws ProxyCacheException, IOException {
        d();
        try {
            this.a.incrementAndGet();
            this.c.a(dVar, socket);
        } finally {
            e();
        }
    }

    public void a() throws ProxyCacheException, IOException {
        d();
        try {
            this.a.incrementAndGet();
            this.c.a();
        } finally {
            e();
        }
    }

    private synchronized void d() throws ProxyCacheException {
        this.c = this.c == null ? f() : this.c;
    }

    private synchronized void e() {
        if (this.a.decrementAndGet() <= 0) {
            this.c.b();
            this.c = null;
        }
    }

    public void a(b bVar) {
        this.d.add(bVar);
    }

    public void b(b bVar) {
        this.d.remove(bVar);
    }

    public void b() {
        this.d.clear();
        if (this.c != null) {
            this.c.a(null);
            this.c.b();
            this.c = null;
        }
        this.a.set(0);
    }

    public int c() {
        return this.a.get();
    }

    private e f() throws ProxyCacheException {
        h hVar = new h(this.b, this.f.d, this.f.e);
        e eVar = new e(hVar, new b(this.f.a(this.b), this.f.c));
        eVar.a(this.e);
        hVar.a(this.e);
        return eVar;
    }
}
