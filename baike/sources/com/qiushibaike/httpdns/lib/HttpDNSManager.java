package com.qiushibaike.httpdns.lib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class HttpDNSManager {
    public static final int NETWORK_TYPE_DISCONNECT = -1;
    private static HttpDNSManager a;
    private HashMap<String, DomainRecord> b = new HashMap();
    private HashSet<String> c = new HashSet();
    private a d = new a();
    private Fetch e;
    private int f = -1;
    public Random r = new Random();

    public static class LogResultListener implements FetchResultListener {
        public void onSuccess(DomainRecord domainRecord) {
            if (AppContext.isDebug()) {
                Log.e("HttpDNSManager", String.format("Fetch success %s", new Object[]{domainRecord.toString()}));
            }
        }

        public void onFailure(String str, Exception exception) {
            if (AppContext.isDebug()) {
                Log.e("HttpDNSManager", String.format("Fetch failure %s, %s", new Object[]{str, exception}));
            }
        }
    }

    private static class a extends BroadcastReceiver {
        private a() {
        }

        public void onReceive(Context context, Intent intent) {
            if (AppContext.isDebug()) {
                Log.d("HttpDNSManager", "recv network change status");
            }
            HttpDNSManager.instance().onNetworkChange(context);
        }
    }

    public static synchronized HttpDNSManager instance() {
        HttpDNSManager httpDNSManager;
        synchronized (HttpDNSManager.class) {
            if (a == null) {
                a = new HttpDNSManager();
            }
            httpDNSManager = a;
        }
        return httpDNSManager;
    }

    public static boolean isNetworkConnected(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.isAvailable() : false;
    }

    public Fetch getFetcher() {
        return this.e;
    }

    public void setFetcher(Fetch fetch) {
        this.e = fetch;
    }

    public Fetch getFetcherInner() {
        if (this.e == null) {
            Log.e("HttpDNSManager", "Current fetcher is null, Do you forget to setFetcher? Automatically set.");
            this.e = new Random().nextInt(2) % 2 == 0 ? new QCloudFetcher() : new AliFetch();
            this.e.setResultListener(new LogResultListener());
        }
        return this.e;
    }

    public void reportError(String str, String str2) {
        synchronized (this.b) {
            DomainRecord domainRecord = (DomainRecord) this.b.get(str);
            if (domainRecord != null) {
                if (isNetworkConnected(AppContext.getContext())) {
                    if (AppContext.isDebug()) {
                        Log.e("HttpDNSManager", String.format("domain with ip is wrong %s, ip: %s", new Object[]{str, str2}));
                    }
                    domainRecord.errorCount++;
                }
                if (domainRecord.needInvalid() && str2.equals(domainRecord.ip)) {
                    this.b.remove(str);
                }
            }
        }
    }

    public void reportOK(String str, String str2) {
        DomainRecord domainRecord = (DomainRecord) this.b.get(str);
        if (domainRecord != null && str2.equals(domainRecord.ip)) {
            if (AppContext.isDebug()) {
                Log.e("HttpDNSManager", String.format("domain with ip is OK %s, ip: %s", new Object[]{str, str2}));
            }
            domainRecord.errorCount = 0;
        }
    }

    public String getHttpDnsIp(String str) {
        if (!AppContext.isHttpDnsEnable()) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i;
        for (String contains : new ArrayList(AppContext.getDomainWhiteList())) {
            String contains2;
            if (str.contains(contains2)) {
                i = 1;
                break;
            }
        }
        i = 0;
        if (i == 0) {
            Log.w("HttpDNSManager", String.format("Disable httpdns for %s", new Object[]{str}));
            return null;
        }
        synchronized (this.b) {
            DomainRecord domainRecord = (DomainRecord) this.b.get(str);
            if (domainRecord == null) {
                b(str);
                return null;
            }
            if (domainRecord.needRefresh()) {
                if (AppContext.isDebug()) {
                    Log.w("HttpDNSManager", String.format("refresh domain %s, %s", new Object[]{domainRecord.domain, domainRecord.ip}));
                }
                b(str);
            }
            if (domainRecord.isExpire()) {
                if (AppContext.isDebug()) {
                    Log.w("HttpDNSManager", String.format("refresh domain for Expiration %s, %s", new Object[]{domainRecord.domain, domainRecord.ip}));
                }
                this.b.remove(str);
                return null;
            }
            contains2 = domainRecord.ip;
            return contains2;
        }
    }

    public void onCreate(Context context) {
        AppContext.a(context);
        instance().onNetworkChange(context);
        context.registerReceiver(this.d, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void onDestroy(Context context) {
        context.unregisterReceiver(this.d);
    }

    public void onNetworkChange(Context context) {
        int i;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            i = -1;
        } else {
            i = activeNetworkInfo.getType();
        }
        if (i != this.f && i != -1) {
            if (AppContext.isDebug()) {
                Log.w("HttpDNSManager", String.format("network type is different with lastnetwork type:%s, last:%s", new Object[]{Integer.valueOf(i), Integer.valueOf(this.f)}));
            }
            synchronized (this.b) {
                this.b.clear();
            }
            this.f = i;
        }
    }

    private String a(String str) throws Exception {
        return getFetcherInner().getIpByDomain(str);
    }

    private void b(String str) {
        if (!this.c.contains(str)) {
            this.c.add(str);
            new a(this, str).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }
}
