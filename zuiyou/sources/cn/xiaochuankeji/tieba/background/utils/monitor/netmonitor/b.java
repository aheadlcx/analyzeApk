package cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor.c.a;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class b extends LDNetAsyncTaskEx<String, String, String> implements a, d.a {
    private static final BlockingQueue<Runnable> m = new LinkedBlockingQueue(2);
    private static final ThreadFactory n = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "Trace #" + this.a.getAndIncrement());
            thread.setPriority(1);
            return thread;
        }
    };
    private static ThreadPoolExecutor o = null;
    private String a;
    private boolean b;
    private Context c;
    private List<String> d;
    private final StringBuilder e = new StringBuilder(256);
    private c f;
    private d g;
    private boolean h;
    private a i;
    private TelephonyManager j = null;
    private boolean k = false;
    private boolean l = false;

    public b(Context context, String str, a aVar) {
        this.c = context;
        this.a = str;
        this.i = aVar;
        this.h = false;
        this.d = new ArrayList();
        this.j = (TelephonyManager) context.getSystemService("phone");
        o = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, m, n);
    }

    protected String a(String... strArr) {
        if (c()) {
            return null;
        }
        return f();
    }

    protected void a(String str) {
        if (!c()) {
            super.a((Object) str);
            g();
            boolean z = !str.equals("false");
            if (this.i != null) {
                this.i.a(z, this.e.toString());
            }
        }
    }

    protected void b(String... strArr) {
        if (!c() && this.i != null) {
            if (strArr.length == 2) {
                this.i.a(strArr[0], strArr[1]);
            } else if (strArr.length == 1) {
                this.i.a(strArr[0], "false");
            }
        }
    }

    protected void b() {
        g();
    }

    public String f() {
        if (TextUtils.isEmpty(this.a)) {
            return "false";
        }
        this.h = true;
        this.e.setLength(0);
        this.b = e.a(this.c).booleanValue();
        this.f = new c(this, 1);
        this.f.a(this.a, false);
        if (!this.k) {
            return "false";
        }
        if (!h()) {
            return "false";
        }
        this.g = new d();
        this.g.a((d.a) this);
        this.g.a(this.a);
        if (this.l) {
            return this.e.toString();
        }
        return "false";
    }

    private boolean h() {
        Socket socket = new Socket();
        SocketAddress inetSocketAddress = new InetSocketAddress(this.a, 80);
        try {
            long currentTimeMillis = System.currentTimeMillis();
            socket.connect(inetSocketAddress);
            c("connect:" + this.a + "   port:80   " + (System.currentTimeMillis() - currentTimeMillis) + Parameters.MESSAGE_SEQ);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return false;
        }
    }

    public void g() {
        if (this.h) {
            if (this.f != null) {
                this.f = null;
            }
            if (this.g != null) {
                this.g = null;
            }
            a(true);
            if (!(o == null || o.isShutdown())) {
                o.shutdown();
                o = null;
            }
            this.h = false;
        }
    }

    private void c(String str) {
        this.e.append(str + "\n");
        d(str + "\n");
    }

    public void b(boolean z) {
        this.l = z;
    }

    public void b(String str) {
        if (str != null && this.g != null) {
            if (str.contains(Parameters.MESSAGE_SEQ) || str.contains("***")) {
                str = str + "\n";
            }
            this.e.append(str);
            d(str, "true");
        }
    }

    protected ThreadPoolExecutor d() {
        return o;
    }

    public void a(boolean z, String str) {
        this.k = z;
        if (z) {
            c(str);
        }
    }
}
