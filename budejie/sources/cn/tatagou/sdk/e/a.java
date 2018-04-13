package cn.tatagou.sdk.e;

import android.content.Context;
import android.util.Log;
import cn.tatagou.sdk.a.d;
import cn.tatagou.sdk.android.TtgConfigKey;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.android.TtgSource;
import cn.tatagou.sdk.util.p;
import com.alibaba.fastjson.JSON;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import okhttp3.ab;
import retrofit2.l;

public class a {
    private static final String a = a.class.getSimpleName();
    private static a t;
    private cn.tatagou.sdk.c.a b;
    private String c = "EVENTS";
    private String d = TtgSource.QLDS;
    private String e = "app_events";
    private String f = "ttg_events";
    private Context g;
    private String h = "https://cn-hangzhou.log.aliyuncs.com";
    private String i = "LTAIN0cmiaFnGHAX";
    private String j = "vc0pdUC15e9zUuLuNbzx0gRu4hBm06";
    private String k = "ttjx-dev";
    private HashMap<String, String> l = new HashMap();
    private ConcurrentLinkedQueue<cn.tatagou.sdk.e.a.a> m = new ConcurrentLinkedQueue();
    private int n = 2;
    private int o = 200;
    private List<String> p = new ArrayList();
    private ExecutorService q;
    private Future r;
    private String s;

    private class a implements Runnable {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        public void run() {
            while (this.a.m.size() > 0) {
                final cn.tatagou.sdk.e.a.a aVar = (cn.tatagou.sdk.e.a.a) this.a.m.poll();
                aVar.setAllParams(this.a.l);
                Log.d(a.a, "event obj: " + JSON.toJSONString(aVar));
                ((cn.tatagou.sdk.a.a.a) d.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).userActivity(aVar.getParams()).a(new retrofit2.d<ab>(this) {
                    final /* synthetic */ a b;

                    public void onResponse(retrofit2.b<ab> bVar, l<ab> lVar) {
                        Log.d(a.a, "userActivity onResponse succ");
                        if (TtgSDK.isDebug) {
                            Log.d(a.a, "userActivity onResponse succ" + lVar.a() + ",event:" + aVar.getParams());
                        }
                    }

                    public void onFailure(retrofit2.b<ab> bVar, Throwable th) {
                        Log.e(a.a, "userActivity onResponse fail :" + th.getMessage(), th);
                    }
                });
            }
        }
    }

    private class b implements Runnable {
        final /* synthetic */ a a;

        private b(a aVar) {
            this.a = aVar;
        }

        public void run() {
            Log.d(a.a, "begin to run eventQueue size:" + this.a.m.size());
            while (this.a.m.size() >= this.a.n) {
                Log.d(a.a, "begin to run");
                Log.d(a.a, "add logs - " + this.a.m.size());
                cn.tatagou.sdk.c.a.d dVar = new cn.tatagou.sdk.c.a.d();
                dVar.PutSource(this.a.d);
                dVar.PutTopic(this.a.c);
                int i = 0;
                while (i < this.a.o && this.a.m.size() > 0) {
                    int i2;
                    cn.tatagou.sdk.e.a.a aVar = (cn.tatagou.sdk.e.a.a) this.a.m.poll();
                    Log.d(a.a, "event instance" + aVar.toString());
                    if (aVar != null) {
                        cn.tatagou.sdk.c.a.b bVar = new cn.tatagou.sdk.c.a.b();
                        for (String str : this.a.l.keySet()) {
                            bVar.PutContent(str, (String) this.a.l.get(str));
                        }
                        for (String str2 : aVar.getParams().keySet()) {
                            bVar.PutContent(str2, (String) aVar.getParams().get(str2));
                        }
                        Log.d(a.a, "run is event: " + aVar.getEvent());
                        dVar.PutLog(bVar);
                        i2 = i + 1;
                    } else {
                        i2 = i;
                    }
                    i = i2;
                }
                try {
                    this.a.b.PostLog(dVar, this.a.f);
                } catch (Throwable e) {
                    e.printStackTrace();
                    Log.d(a.a, "LogClient post: " + e.getMessage(), e);
                }
            }
            Log.d(a.a, "statrunner ends");
        }
    }

    a(Context context) {
        this.g = context;
        if (this.b == null) {
            this.b = new cn.tatagou.sdk.c.a(this.h, this.i, this.j, this.k);
        }
        this.q = Executors.newSingleThreadExecutor();
    }

    public a setLogClient(String str, String str2, String str3, String str4) {
        this.h = str;
        this.i = str2;
        this.j = str3;
        this.k = str4;
        this.b = new cn.tatagou.sdk.c.a(this.h, this.i, this.j, this.k);
        return this;
    }

    public a setLogStoreName(String str) {
        this.f = str;
        return this;
    }

    private void b() {
        this.q.shutdownNow();
        this.g = null;
        this.l.clear();
        this.m.clear();
    }

    public static a init(Context context) {
        if (t == null) {
            t = new a(context);
        }
        return t;
    }

    public a setAppSource(String str) {
        setParameters("source", str);
        return this;
    }

    public a setAppDeviceId(String str) {
        setParameters(TtgConfigKey.KEY_APPDEVICEID, str);
        return this;
    }

    public a setTraceId(String str) {
        setParameters(com.alipay.sdk.cons.b.c, str);
        return this;
    }

    public a setIp(String str) {
        setParameters("ip", str);
        return this;
    }

    public a setSource(String str) {
        this.d = str;
        return this;
    }

    public a setAppVersion(String str) {
        setParameters(com.alipay.sdk.sys.a.h, str);
        return this;
    }

    public a setAndroidId(String str) {
        setParameters("dt", str);
        return this;
    }

    public a setSdkDeviceId(String str) {
        setParameters("deviceId", str);
        return this;
    }

    public a setSdkVersion(String str) {
        setParameters("appVersion", str);
        return this;
    }

    public a setLogMinBatchSize(int i) {
        this.n = i;
        return this;
    }

    public a setPlatform(String str) {
        setParameters("platform", str);
        return this;
    }

    public a setLogVersion(String str) {
        setParameters(IXAdRequestInfo.V, str);
        return this;
    }

    public a setPid(String str) {
        setParameters("pid", str);
        return this;
    }

    public void setParameters(String str, String str2) {
        if (!p.isEmpty(str2)) {
            this.l.put(str, str2);
        }
    }

    public a setLogSchema(String str) {
        this.s = str;
        return this;
    }

    public String getSchema() {
        return this.s;
    }

    public int getEventQueueSize() {
        return this.m.size();
    }

    public HashMap<String, String> getParameters() {
        return this.l;
    }

    private void a(cn.tatagou.sdk.e.a.a aVar) {
        if (this.p != null && !this.p.contains(aVar.getEvent())) {
            this.m.add(aVar);
            c();
        }
    }

    public void setSkippedEvents(List<String> list) {
        this.p = list;
    }

    public List<String> getIgnoreEvents() {
        return this.p;
    }

    public void addSkippedEvents(String str) {
        this.p.add(str);
    }

    private synchronized void c() {
        Log.d(a, "stat EventQueue size: " + this.m.size());
        if (this.r == null || this.r.isDone()) {
            Log.d(a, "schema : " + this.s);
            if ((this.s == null || "SLS".equals(this.s)) && this.m.size() >= this.n) {
                this.r = this.q.submit(new b());
            } else if ("HTTP".equals(this.s)) {
                this.r = this.q.submit(new a());
            }
        }
    }

    public void statHttpReport() {
        if (this.r == null || this.r.isDone()) {
            this.r = this.q.submit(new a());
        }
    }

    public static void shutdown() {
        if (t != null) {
            t.b();
        }
    }

    public static void reportEvent(cn.tatagou.sdk.e.a.a aVar) {
        if (t != null) {
            t.a(aVar);
        }
    }
}
