package cn.xiaochuankeji.tieba.network;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.d.j;
import com.danikula.videocache.q;
import com.iflytek.cloud.SpeechConstant;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e implements cn.htjyb.netlib.a.a {
    private static String B = (cn.xiaochuankeji.tieba.background.a.e().r() + "smartdns.dat");
    private static volatile e c;
    private int A;
    public boolean a = false;
    public int b = 0;
    private Thread d;
    private List<a> e = Collections.synchronizedList(new ArrayList());
    private List<a> f = Collections.synchronizedList(new ArrayList());
    private List<a> g = Collections.synchronizedList(new ArrayList());
    private final ConcurrentHashMap<String, String> h = new ConcurrentHashMap();
    private long i;
    private boolean j;
    private int k = 32767;
    private int l = 10;
    private ArrayList<String> m = new ArrayList();
    private int n;
    private boolean o;
    private int p = 32767;
    private int q = 0;
    private ArrayList<String> r = new ArrayList();
    private ArrayList<String> s = new ArrayList();
    private Thread t;
    private ArrayList<String> u = new ArrayList();
    private ArrayList<String> v = new ArrayList();
    private LinkedList<String> w = new LinkedList();
    private boolean x = false;
    private Map<String, List<String>> y = new HashMap();
    private int z;

    public static class a {
        private final String a;
        private final ArrayList<String> b;

        public a(String str, ArrayList<String> arrayList) {
            this.a = str;
            this.b = arrayList;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            if (this.b.isEmpty()) {
                return null;
            }
            return (String) this.b.get(0);
        }

        public void a(String str) {
            this.b.remove(str);
            this.b.add(str);
        }

        public static a a(JSONObject jSONObject) {
            String str = "";
            ArrayList arrayList = new ArrayList();
            if (jSONObject != null) {
                Iterator keys = jSONObject.keys();
                if (keys.hasNext()) {
                    str = (String) keys.next();
                    JSONArray optJSONArray = jSONObject.optJSONArray(str);
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            CharSequence optString = optJSONArray.optString(i);
                            if (!TextUtils.isEmpty(optString)) {
                                arrayList.add(optString);
                            }
                        }
                    }
                }
            }
            return new a(str, arrayList);
        }

        public static ArrayList<a> a(JSONArray jSONArray) {
            ArrayList<a> arrayList = new ArrayList();
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(a(jSONArray.optJSONObject(i)));
                }
            }
            return arrayList;
        }
    }

    private static class b extends cn.htjyb.netlib.d {
        public b() {
            super(null, null, null);
            a(true);
        }

        protected void a(AsyncTask asyncTask) {
        }
    }

    private class c extends Thread {
        final /* synthetic */ e a;

        public c(e eVar) {
            this.a = eVar;
            super("Report Sample Thread");
        }

        public void run() {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            synchronized (this.a) {
                arrayList.addAll(this.a.u);
                this.a.u.clear();
                arrayList2.addAll(this.a.v);
                this.a.v.clear();
            }
            if (!arrayList.isEmpty()) {
                a("sample", arrayList);
            }
            if (!arrayList2.isEmpty()) {
                a("video_sample", arrayList2);
            }
            synchronized (this.a) {
                this.a.t = null;
            }
        }

        private void a(String str, ArrayList<String> arrayList) {
            cn.htjyb.netlib.d bVar = new b();
            JSONObject jSONObject = new JSONObject();
            cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
            try {
                jSONObject.put(str, new JSONArray(arrayList));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            cn.xiaochuankeji.tieba.background.a.d().a(bVar, cn.xiaochuankeji.tieba.background.utils.d.a.c, jSONObject.toString());
        }
    }

    private class d extends Thread {
        final /* synthetic */ e a;

        public d(e eVar) {
            this.a = eVar;
            super("Update DNS Thread");
        }

        public void run() {
            try {
                if (cn.htjyb.c.a.b.c(e.B)) {
                    a(cn.htjyb.c.a.b.a(new File(e.B), AppController.kDataCacheCharsetUTF8.name()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cn.htjyb.netlib.d bVar = new b();
            JSONObject jSONObject = new JSONObject();
            cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
            cn.htjyb.netlib.b.a a = cn.xiaochuankeji.tieba.background.a.d().a(bVar, cn.xiaochuankeji.tieba.background.utils.d.a.b(cn.xiaochuankeji.tieba.background.utils.d.a.b), jSONObject.toString());
            synchronized (this.a) {
                if (a.a) {
                    try {
                        a(a.c);
                        cn.htjyb.c.a.b.a(a.c, new File(e.B), AppController.kDataCacheCharsetUTF8.name());
                    } catch (Exception e2) {
                    }
                } else {
                    this.a.i = System.currentTimeMillis() + 600000;
                }
                this.a.d = null;
            }
        }

        private void a(JSONObject jSONObject) {
            int optInt;
            int i = 0;
            Collection a = a.a(jSONObject.optJSONArray("api"));
            this.a.e.clear();
            this.a.e.addAll(a);
            a = a.a(jSONObject.optJSONArray("img"));
            this.a.f.clear();
            this.a.f.addAll(a);
            a = a.a(jSONObject.optJSONArray("video"));
            this.a.g.clear();
            this.a.g.addAll(a);
            this.a.x = jSONObject.optBoolean("diagnosis-video", false);
            this.a.A = jSONObject.optInt("read_timeout", 0);
            this.a.z = jSONObject.optInt("connect_timeout", 0);
            q.a().a(this.a.z, this.a.A);
            this.a.i = System.currentTimeMillis() + ((long) (jSONObject.optInt("ttl", 600) * 1000));
            JSONObject optJSONObject = jSONObject.optJSONObject("sampling");
            if (optJSONObject != null) {
                optInt = optJSONObject.optInt("step", -1);
                if (optInt < 0) {
                    this.a.j = false;
                } else {
                    this.a.j = true;
                    this.a.k = optInt;
                }
                this.a.l = optJSONObject.optInt("batch", 10);
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("video-sampling");
            if (optJSONObject2 != null) {
                optInt = optJSONObject2.optInt("step", -1);
                if (optInt < 0) {
                    this.a.o = false;
                } else {
                    this.a.o = true;
                    this.a.p = optInt;
                }
                this.a.q = optJSONObject.optInt("batch", 0);
            }
            optJSONObject = jSONObject.optJSONObject("img-net-info");
            if (optJSONObject != null) {
                if (optJSONObject.optInt("step", 0) <= 0) {
                    this.a.a = false;
                } else {
                    this.a.a = true;
                }
                this.a.b = optJSONObject.optInt("batch", 0);
            }
            optJSONObject = jSONObject.optJSONObject("video-cdn-info");
            if (!(optJSONObject == null || optJSONObject.optJSONObject(SpeechConstant.DOMAIN) == null)) {
                JSONObject optJSONObject3 = optJSONObject.optJSONObject(SpeechConstant.DOMAIN);
                Iterator keys = optJSONObject3.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    ArrayList arrayList = new ArrayList();
                    if (optJSONObject3.opt(str) instanceof JSONArray) {
                        JSONArray optJSONArray = optJSONObject3.optJSONArray(str);
                        for (optInt = 0; optInt < optJSONArray.length(); optInt++) {
                            arrayList.add(optJSONArray.optString(optInt));
                        }
                        this.a.y.put(str, arrayList);
                    }
                }
            }
            if (this.a.y.size() > 0) {
                q.a().a(this.a.y);
            }
            this.a.s.clear();
            JSONArray optJSONArray2 = jSONObject.optJSONArray("blacklist");
            if (optJSONArray2 != null) {
                while (i < optJSONArray2.length()) {
                    CharSequence optString = optJSONArray2.optString(i);
                    if (!TextUtils.isEmpty(optString)) {
                        this.a.s.add(optString);
                    }
                    i++;
                }
            }
        }
    }

    private e() {
        i();
    }

    public static e a() {
        if (c == null) {
            synchronized (e.class) {
                if (c == null) {
                    c = new e();
                }
            }
        }
        return c;
    }

    public List<a> b() {
        return this.e;
    }

    public List<a> c() {
        return this.f;
    }

    public List<a> d() {
        return this.g;
    }

    public synchronized String a(String str) {
        String b;
        if (System.currentTimeMillis() > this.i && this.i > 0) {
            i();
        }
        for (a aVar : this.e) {
            if (aVar.a().equals(str)) {
                b = aVar.b();
                if (!TextUtils.isEmpty(b)) {
                    this.h.put(str, b);
                }
            }
        }
        for (a aVar2 : this.f) {
            if (aVar2.a().equals(str)) {
                b = aVar2.b();
                break;
            }
        }
        for (a aVar22 : this.g) {
            if (aVar22.a().equals(str)) {
                b = aVar22.b();
                break;
            }
        }
        b = null;
        return b;
    }

    public synchronized void a(String str, String str2) {
        for (a aVar : this.e) {
            if (aVar.a().equals(str)) {
                aVar.a(str2);
                break;
            }
        }
        for (a aVar2 : this.f) {
            if (aVar2.a().equals(str)) {
                aVar2.a(str2);
                break;
            }
        }
        for (a aVar22 : this.g) {
            if (aVar22.a().equals(str)) {
                aVar22.a(str2);
                break;
            }
        }
    }

    public void b(String str) {
        if (!j.a(str)) {
            if (str.contains("api")) {
                for (a aVar : this.e) {
                    if (aVar.a().equals(str)) {
                        aVar.a(aVar.b());
                        return;
                    }
                }
            }
            if (str.contains("file")) {
                for (a aVar2 : this.f) {
                    if (aVar2.a().equals(str)) {
                        aVar2.a(aVar2.b());
                        return;
                    }
                }
            }
        }
    }

    public ConcurrentHashMap<String, String> e() {
        return this.h;
    }

    public synchronized void a(String str, String str2, String str3, long j, String str4) {
        if (this.j) {
            this.n++;
            if (this.n > this.k) {
                this.n = 0;
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(System.currentTimeMillis());
                jSONArray.put(str);
                jSONArray.put(str2);
                jSONArray.put(str3);
                jSONArray.put(j);
                jSONArray.put(str4);
                this.m.add(jSONArray.toString());
                if (this.m.size() >= this.l) {
                    a(this.m);
                    this.m.clear();
                }
            }
        }
    }

    public synchronized boolean c(String str) {
        boolean z;
        Iterator it = this.s.iterator();
        while (it.hasNext()) {
            if (str.contains((String) it.next())) {
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    public synchronized boolean d(String str) {
        boolean z;
        for (a aVar : this.e) {
            if (aVar != null && aVar.a() != null && aVar.a().equals(str)) {
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    public void a(String str, String str2, String str3, String str4) {
        if (!str.contains("/generalreport/event") && !str.contains("/stat/action")) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(System.currentTimeMillis());
            jSONArray.put(str);
            jSONArray.put(str2);
            jSONArray.put(str3);
            jSONArray.put(str4);
            synchronized (this.w) {
                if (this.w.size() > 100) {
                    this.w.removeFirst();
                }
                this.w.push(jSONArray.toString());
            }
        }
    }

    public ArrayList<String> f() {
        ArrayList<String> arrayList;
        synchronized (this.w) {
            arrayList = new ArrayList();
            Iterator it = this.w.iterator();
            while (it.hasNext()) {
                arrayList.add((String) it.next());
            }
        }
        return arrayList;
    }

    private synchronized void i() {
        Log.d("smartdns-thread", Thread.currentThread().getName());
        if (this.d == null) {
            this.d = new d(this);
            this.d.start();
        }
    }

    private synchronized void a(ArrayList<String> arrayList) {
        if (this.u.size() + arrayList.size() <= 100) {
            this.u.addAll(arrayList);
        }
        if (this.t == null) {
            this.t = new c(this);
            this.t.start();
        }
    }

    public boolean g() {
        return this.x;
    }
}
