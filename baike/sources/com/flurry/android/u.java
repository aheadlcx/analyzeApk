package com.flurry.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.alipay.sdk.sys.a;
import com.baidu.mobstat.Config;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import qsbk.app.core.model.User;

final class u implements OnClickListener {
    static String a = "FlurryAgent";
    static String b = "";
    private static volatile String c = "market://";
    private static volatile String d = "market://details?id=";
    private static volatile String e = "https://market.android.com/details?id=";
    private static String f = "com.flurry.android.ACTION_CATALOG";
    private static int g = 5000;
    private static volatile long z = 0;
    private String h;
    private String i;
    private String j;
    private long k;
    private long l;
    private long m;
    private z n = new z();
    private boolean o = true;
    private volatile boolean p;
    private String q;
    private Map r = new HashMap();
    private Handler s;
    private boolean t;
    private transient Map u = new HashMap();
    private ag v;
    private List w = new ArrayList();
    private Map x = new HashMap();
    private AppCircleCallback y;

    static /* synthetic */ void a(u uVar, Context context, String str) {
        if (str.startsWith(d)) {
            String substring = str.substring(d.length());
            if (uVar.o) {
                try {
                    ah.a(a, "Launching Android Market for app " + substring);
                    context.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(str)));
                    return;
                } catch (Throwable e) {
                    ah.c(a, "Cannot launch Marketplace url " + str, e);
                    return;
                }
            }
            ah.a(a, "Launching Android Market website for app " + substring);
            context.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(e + substring)));
            return;
        }
        ah.d(a, "Unexpected android market url scheme: " + str);
    }

    static {
        Random random = new Random(System.currentTimeMillis());
    }

    final synchronized void a(Context context, a aVar) {
        boolean z = true;
        synchronized (this) {
            if (!this.p) {
                this.h = aVar.e;
                this.i = aVar.f;
                this.j = aVar.a;
                this.k = aVar.b;
                this.l = aVar.c;
                this.m = aVar.d;
                this.s = aVar.g;
                this.v = new ag(this.s, g);
                context.getResources().getDisplayMetrics();
                this.x.clear();
                this.u.clear();
                this.n.a(context, this, aVar);
                this.r.clear();
                PackageManager packageManager = context.getPackageManager();
                String str = d + context.getPackageName();
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                if (packageManager.queryIntentActivities(intent, 65536).size() <= 0) {
                    z = false;
                }
                this.o = z;
                a();
                this.p = true;
            }
        }
    }

    final synchronized void a() {
        this.w.clear();
    }

    final boolean b() {
        return this.p;
    }

    final void a(String str) {
        this.q = str;
    }

    final synchronized void c() {
        if (q()) {
            this.n.d();
        }
    }

    final synchronized void d() {
        if (q()) {
            this.n.e();
        }
    }

    final synchronized void a(Map map, Map map2, Map map3, Map map4, Map map5, Map map6) {
        if (q()) {
            this.n.a(map, map2, map3, map4, map5, map6);
            Log.i("FlurryAgent", this.n.toString());
        }
    }

    final synchronized long e() {
        long c;
        if (q()) {
            c = this.n.c();
        } else {
            c = 0;
        }
        return c;
    }

    final synchronized Set f() {
        Set a;
        if (q()) {
            a = this.n.a();
        } else {
            a = Collections.emptySet();
        }
        return a;
    }

    final synchronized AdImage a(long j) {
        AdImage b;
        if (q()) {
            b = this.n.b(j);
        } else {
            b = null;
        }
        return b;
    }

    private synchronized AdImage o() {
        AdImage a;
        if (q()) {
            a = this.n.a((short) 1);
        } else {
            a = null;
        }
        return a;
    }

    final synchronized List g() {
        return this.w;
    }

    final synchronized p b(long j) {
        return (p) this.u.get(Long.valueOf(j));
    }

    final synchronized void h() {
        this.u.clear();
    }

    final synchronized void a(Context context, String str) {
        if (q()) {
            try {
                List a = a(Arrays.asList(new String[]{str}), null);
                if (a == null || a.isEmpty()) {
                    Intent intent = new Intent(p());
                    intent.addCategory("android.intent.category.DEFAULT");
                    context.startActivity(intent);
                } else {
                    p pVar = new p(str, (byte) 2, SystemClock.elapsedRealtime() - this.m);
                    pVar.b = (v) a.get(0);
                    a(pVar);
                    b(context, pVar, this.h + a(pVar, Long.valueOf(System.currentTimeMillis())));
                }
            } catch (Throwable e) {
                ah.d(a, "Failed to launch promotional canvas for hook: " + str, e);
            }
        }
    }

    final void a(AppCircleCallback appCircleCallback) {
        this.y = appCircleCallback;
    }

    final void a(boolean z) {
        this.t = z;
    }

    final boolean i() {
        return this.t;
    }

    final String j() {
        return this.h;
    }

    final synchronized void a(Context context, p pVar, String str) {
        if (q()) {
            this.s.post(new ak(this, str, context, pVar));
        }
    }

    private String d(String str) {
        try {
            if (str.startsWith(c)) {
                return str;
            }
            HttpResponse execute = new DefaultHttpClient().execute(new HttpGet(str));
            int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                str = EntityUtils.toString(execute.getEntity());
                if (str.startsWith(c)) {
                    return str;
                }
                return d(str);
            }
            ah.c(a, "Cannot process with responseCode " + statusCode);
            e("Error when fetching application's android market ID, responseCode " + statusCode);
            return str;
        } catch (UnknownHostException e) {
            ah.c(a, "Unknown host: " + e.getMessage());
            if (this.y != null) {
                e("Unknown host: " + e.getMessage());
            }
            return null;
        } catch (Throwable e2) {
            ah.c(a, "Failed on url: " + str, e2);
            return null;
        }
    }

    private void e(String str) {
        a(new ae(this, str));
    }

    final synchronized Offer b(String str) {
        Offer offer = null;
        synchronized (this) {
            if (q()) {
                List a = a(Arrays.asList(new String[]{str}), null);
                if (!(a == null || a.isEmpty())) {
                    offer = a(str, (v) a.get(0));
                    ah.a(a, "Impression for offer with ID " + offer.getId());
                }
            }
        }
        return offer;
    }

    final synchronized void a(Context context, long j) {
        if (q()) {
            OfferInSdk offerInSdk = (OfferInSdk) this.x.get(Long.valueOf(j));
            if (offerInSdk == null) {
                ah.b(a, "Cannot find offer " + j);
            } else {
                p pVar = offerInSdk.b;
                pVar.a(new f((byte) 4, k()));
                String str = FlurryAgent.c() + a(pVar, Long.valueOf(pVar.a()));
                ah.a(a, "Offer " + offerInSdk.a + " accepted. Sent with cookies: " + this.r);
                a(context, pVar, str);
            }
        }
    }

    final synchronized List c(String str) {
        List emptyList;
        if (!q()) {
            emptyList = Collections.emptyList();
        } else if (this.n.b()) {
            v[] a = this.n.a(str);
            emptyList = new ArrayList();
            if (a != null && a.length > 0) {
                for (v a2 : a) {
                    emptyList.add(a(str, a2));
                }
            }
            ah.a(a, "Impressions for " + emptyList.size() + " offers.");
        } else {
            emptyList = Collections.emptyList();
        }
        return emptyList;
    }

    final synchronized void a(List list) {
        if (q()) {
            for (Long remove : list) {
                this.x.remove(remove);
            }
        }
    }

    private Offer a(String str, v vVar) {
        p pVar = new p(str, (byte) 3, k());
        a(pVar);
        pVar.a(new f((byte) 2, k()));
        pVar.b = vVar;
        al a = this.n.a(vVar.a);
        String str2 = a == null ? "" : a.a;
        int i = a == null ? 0 : a.c;
        long j = z + 1;
        z = j;
        OfferInSdk offerInSdk = new OfferInSdk(j, pVar, vVar.h, vVar.d, str2, i);
        this.x.put(Long.valueOf(offerInSdk.a), offerInSdk);
        return new Offer(offerInSdk.a, offerInSdk.f, offerInSdk.c, offerInSdk.d, offerInSdk.e);
    }

    final synchronized List a(Context context, List list, Long l, int i, boolean z) {
        List emptyList;
        if (!q()) {
            emptyList = Collections.emptyList();
        } else if (!this.n.b() || list == null) {
            emptyList = Collections.emptyList();
        } else {
            List a = a(list, l);
            int min = Math.min(list.size(), a.size());
            List arrayList = new ArrayList();
            for (int i2 = 0; i2 < min; i2++) {
                String str = (String) list.get(i2);
                e b = this.n.b(str);
                if (b != null) {
                    p pVar = new p((String) list.get(i2), (byte) 1, k());
                    a(pVar);
                    if (i2 < a.size()) {
                        pVar.b = (v) a.get(i2);
                        pVar.a(new f((byte) 2, k()));
                        y yVar = new y(context, this, pVar, b, i, z);
                        yVar.a(a(pVar, null));
                        arrayList.add(yVar);
                    }
                } else {
                    ah.d(a, "Cannot find hook: " + str);
                }
            }
            emptyList = arrayList;
        }
        return emptyList;
    }

    final synchronized View a(Context context, String str, int i) {
        View view;
        if (q()) {
            o oVar = new o(this, context, str, i);
            this.v.a(oVar);
        } else {
            view = null;
        }
        return view;
    }

    private void a(p pVar) {
        if (this.w.size() < 32767) {
            this.w.add(pVar);
            this.u.put(Long.valueOf(pVar.a()), pVar);
        }
    }

    private List a(List list, Long l) {
        if (list == null || list.isEmpty() || !this.n.b()) {
            return Collections.emptyList();
        }
        v[] a = this.n.a((String) list.get(0));
        if (a == null || a.length <= 0) {
            return Collections.emptyList();
        }
        List arrayList = new ArrayList(Arrays.asList(a));
        Collections.shuffle(arrayList);
        if (l != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (((v) it.next()).a == l.longValue()) {
                    it.remove();
                    break;
                }
            }
        }
        return arrayList.subList(0, Math.min(arrayList.size(), list.size()));
    }

    final long k() {
        return SystemClock.elapsedRealtime() - this.m;
    }

    public final synchronized void onClick(View view) {
        y yVar = (y) view;
        p a = yVar.a();
        a.a(new f((byte) 4, k()));
        if (this.t) {
            b(view.getContext(), a, yVar.b(this.h));
        } else {
            a(view.getContext(), a, yVar.b(this.i));
        }
    }

    final synchronized void a(String str, String str2) {
        this.r.put(str, str2);
    }

    final synchronized void l() {
        this.r.clear();
    }

    private void b(Context context, p pVar, String str) {
        Intent intent = new Intent(p());
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra(User.UNDEFINED, str);
        if (pVar != null) {
            intent.putExtra(Config.OS, pVar.a());
        }
        context.startActivity(intent);
    }

    private static String p() {
        return FlurryAgent.a != null ? FlurryAgent.a : f;
    }

    private String a(p pVar, Long l) {
        v vVar = pVar.b;
        StringBuilder append = new StringBuilder().append("?apik=").append(this.j).append("&cid=").append(vVar.e).append("&adid=").append(vVar.a).append("&pid=").append(this.q).append("&iid=").append(this.k).append("&sid=").append(this.l).append("&its=").append(pVar.a()).append("&hid=").append(r.a(pVar.a)).append("&ac=").append(a(vVar.g));
        if (!(this.r == null || this.r.isEmpty())) {
            for (Entry entry : this.r.entrySet()) {
                String str = "c_" + r.a((String) entry.getKey());
                append.append(a.b).append(str).append("=").append(r.a((String) entry.getValue()));
            }
        }
        append.append("&ats=");
        if (l != null) {
            append.append(l);
        }
        return append.toString();
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] >> 4) & 15;
            if (i2 < 10) {
                stringBuilder.append((char) (i2 + 48));
            } else {
                stringBuilder.append((char) ((i2 + 65) - 10));
            }
            i2 = bArr[i] & 15;
            if (i2 < 10) {
                stringBuilder.append((char) (i2 + 48));
            } else {
                stringBuilder.append((char) ((i2 + 65) - 10));
            }
        }
        return stringBuilder.toString();
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[adLogs=").append(this.w).append("]");
        return stringBuilder.toString();
    }

    final synchronized AdImage m() {
        AdImage o;
        if (q()) {
            o = o();
        } else {
            o = null;
        }
        return o;
    }

    private static void a(Runnable runnable) {
        new Handler().post(runnable);
    }

    final synchronized void a(int i) {
        if (this.y != null) {
            a(new ad(this, i));
        }
    }

    final synchronized boolean n() {
        boolean b;
        if (q()) {
            b = this.n.b();
        } else {
            b = false;
        }
        return b;
    }

    private boolean q() {
        if (!this.p) {
            ah.d(a, "AppCircle is not initialized");
        }
        if (this.q == null) {
            ah.d(a, "Cannot identify UDID.");
        }
        return this.p;
    }
}
