package com.xiaomi.push.service;

import android.content.Context;
import android.os.IBinder.DeathRecipient;
import android.os.Messenger;
import android.text.TextUtils;
import com.xiaomi.push.service.XMPushService.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ap {
    private static ap a;
    private ConcurrentHashMap<String, HashMap<String, b>> b = new ConcurrentHashMap();
    private List<a> c = new ArrayList();

    public interface a {
        void a();
    }

    public static class b {
        public String a;
        public String b;
        public String c;
        public String d;
        public boolean e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public e k;
        public Context l;
        c m = c.unbind;
        c n = null;
        DeathRecipient o = null;
        final b p = new b(this);
        private int q = 0;
        private List<a> r = new ArrayList();
        private XMPushService s;
        private Messenger t;
        private boolean u = false;
        private b v = new b(this);

        public interface a {
            void a(c cVar, c cVar2, int i);
        }

        class b extends h {
            int b;
            int c;
            String d;
            String e;
            final /* synthetic */ b f;

            public b(b bVar) {
                this.f = bVar;
                super(0);
            }

            public h a(int i, int i2, String str, String str2) {
                this.b = i;
                this.c = i2;
                this.e = str2;
                this.d = str;
                return this;
            }

            public void a() {
                if (this.f.a(this.b, this.c, this.e)) {
                    this.f.a(this.b, this.c, this.d, this.e);
                } else {
                    com.xiaomi.channel.commonutils.logger.b.b(" ignore notify client :" + this.f.h);
                }
            }

            public String b() {
                return "notify job";
            }
        }

        class c implements DeathRecipient {
            final b a;
            final Messenger b;
            final /* synthetic */ b c;

            c(b bVar, b bVar2, Messenger messenger) {
                this.c = bVar;
                this.a = bVar2;
                this.b = messenger;
            }

            public void binderDied() {
                com.xiaomi.channel.commonutils.logger.b.b("peer died, chid = " + this.a.h);
                this.c.s.a(new ar(this, 0), 0);
            }
        }

        public b(XMPushService xMPushService) {
            this.s = xMPushService;
            a(new aq(this));
        }

        private void a(int i, int i2, String str, String str2) {
            boolean z = true;
            this.n = this.m;
            if (i == 2) {
                this.k.a(this.l, this, i2);
            } else if (i == 3) {
                this.k.a(this.l, this, str2, str);
            } else if (i == 1) {
                if (this.m != c.binded) {
                    z = false;
                }
                if (!z && "wait".equals(str2)) {
                    this.q++;
                } else if (z) {
                    this.q = 0;
                }
                this.k.a(this.l, this, z, i2, str);
            }
        }

        private boolean a(int i, int i2, String str) {
            if (this.n == null || !this.u) {
                return true;
            }
            if (this.n == this.m) {
                com.xiaomi.channel.commonutils.logger.b.b(" status recovered, don't notify client:" + this.h);
                return false;
            } else if (this.t == null || !this.u) {
                com.xiaomi.channel.commonutils.logger.b.b("peer died, ignore notify " + this.h);
                return false;
            } else {
                com.xiaomi.channel.commonutils.logger.b.b("Peer alive notify status to client:" + this.h);
                return true;
            }
        }

        private boolean b(int i, int i2, String str) {
            boolean z = true;
            switch (i) {
                case 1:
                    return (this.m == c.binded || !this.s.f() || i2 == 21) ? false : (i2 == 7 && "wait".equals(str)) ? false : true;
                case 2:
                    return this.s.f();
                case 3:
                    if ("wait".equals(str)) {
                        z = false;
                    }
                    return z;
                default:
                    return false;
            }
        }

        public String a(int i) {
            switch (i) {
                case 1:
                    return "OPEN";
                case 2:
                    return "CLOSE";
                case 3:
                    return "KICK";
                default:
                    return "unknown";
            }
        }

        void a() {
            try {
                Messenger messenger = this.t;
                if (!(messenger == null || this.o == null)) {
                    messenger.getBinder().unlinkToDeath(this.o, 0);
                }
            } catch (Exception e) {
            }
            this.n = null;
        }

        void a(Messenger messenger) {
            a();
            if (messenger != null) {
                try {
                    this.t = messenger;
                    this.u = true;
                    this.o = new c(this, this, messenger);
                    messenger.getBinder().linkToDeath(this.o, 0);
                    return;
                } catch (Exception e) {
                    com.xiaomi.channel.commonutils.logger.b.b("peer linkToDeath err: " + e.getMessage());
                    this.t = null;
                    this.u = false;
                    return;
                }
            }
            com.xiaomi.channel.commonutils.logger.b.b("peer linked with old sdk chid = " + this.h);
        }

        public void a(a aVar) {
            synchronized (this.r) {
                this.r.add(aVar);
            }
        }

        public void a(c cVar, int i, int i2, String str, String str2) {
            synchronized (this.r) {
                for (a a : this.r) {
                    a.a(this.m, cVar, i2);
                }
            }
            if (this.m != cVar) {
                com.xiaomi.channel.commonutils.logger.b.a(String.format("update the client %7$s status. %1$s->%2$s %3$s %4$s %5$s %6$s", new Object[]{this.m, cVar, a(i), as.a(i2), str, str2, this.h}));
                this.m = cVar;
            }
            if (this.k == null) {
                com.xiaomi.channel.commonutils.logger.b.d("status changed while the client dispatcher is missing");
            } else if (cVar != c.binding) {
                int i3 = 10100;
                if (this.n == null || !this.u) {
                    i3 = 0;
                } else if (this.t != null && this.u) {
                    i3 = 1000;
                }
                this.s.b(this.p);
                if (b(i, i2, str2)) {
                    a(i, i2, str, str2);
                } else {
                    this.s.a(this.p.a(i, i2, str, str2), (long) i3);
                }
            }
        }

        public long b() {
            return 1000 * (((long) ((Math.random() * 20.0d) - 10.0d)) + ((long) ((this.q + 1) * 15)));
        }

        public void b(a aVar) {
            synchronized (this.r) {
                this.r.remove(aVar);
            }
        }
    }

    public enum c {
        unbind,
        binding,
        binded
    }

    private ap() {
    }

    public static synchronized ap a() {
        ap apVar;
        synchronized (ap.class) {
            if (a == null) {
                a = new ap();
            }
            apVar = a;
        }
        return apVar;
    }

    private String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf("@");
        return indexOf > 0 ? str.substring(0, indexOf) : str;
    }

    public synchronized void a(Context context) {
        for (HashMap values : this.b.values()) {
            for (b a : values.values()) {
                a.a(c.unbind, 1, 3, null, null);
            }
        }
    }

    public synchronized void a(Context context, int i) {
        for (HashMap values : this.b.values()) {
            for (b a : values.values()) {
                a.a(c.unbind, 2, i, null, null);
            }
        }
    }

    public synchronized void a(a aVar) {
        this.c.add(aVar);
    }

    public synchronized void a(b bVar) {
        HashMap hashMap = (HashMap) this.b.get(bVar.h);
        if (hashMap == null) {
            hashMap = new HashMap();
            this.b.put(bVar.h, hashMap);
        }
        hashMap.put(d(bVar.b), bVar);
        for (a a : this.c) {
            a.a();
        }
    }

    public synchronized void a(String str) {
        HashMap hashMap = (HashMap) this.b.get(str);
        if (hashMap != null) {
            for (b a : hashMap.values()) {
                a.a();
            }
            hashMap.clear();
            this.b.remove(str);
        }
        for (a a2 : this.c) {
            a2.a();
        }
    }

    public synchronized void a(String str, String str2) {
        HashMap hashMap = (HashMap) this.b.get(str);
        if (hashMap != null) {
            b bVar = (b) hashMap.get(d(str2));
            if (bVar != null) {
                bVar.a();
            }
            hashMap.remove(d(str2));
            if (hashMap.isEmpty()) {
                this.b.remove(str);
            }
        }
        for (a a : this.c) {
            a.a();
        }
    }

    public synchronized b b(String str, String str2) {
        HashMap hashMap;
        hashMap = (HashMap) this.b.get(str);
        return hashMap == null ? null : (b) hashMap.get(d(str2));
    }

    public synchronized ArrayList<b> b() {
        ArrayList<b> arrayList;
        arrayList = new ArrayList();
        for (HashMap values : this.b.values()) {
            arrayList.addAll(values.values());
        }
        return arrayList;
    }

    public synchronized List<String> b(String str) {
        List<String> arrayList;
        arrayList = new ArrayList();
        for (HashMap values : this.b.values()) {
            for (b bVar : values.values()) {
                if (str.equals(bVar.a)) {
                    arrayList.add(bVar.h);
                }
            }
        }
        return arrayList;
    }

    public synchronized int c() {
        return this.b.size();
    }

    public synchronized Collection<b> c(String str) {
        return !this.b.containsKey(str) ? new ArrayList() : ((HashMap) ((HashMap) this.b.get(str)).clone()).values();
    }

    public synchronized void d() {
        Iterator it = b().iterator();
        while (it.hasNext()) {
            ((b) it.next()).a();
        }
        this.b.clear();
    }

    public synchronized void e() {
        this.c.clear();
    }
}
