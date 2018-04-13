package com.xiaomi.network;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.text.TextUtils;
import com.umeng.commonsdk.proguard.g;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.network.a;
import com.xiaomi.channel.commonutils.network.c;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.utils.HttpUtils;

public class HostManager {
    protected static Map<String, ArrayList<String>> b = new HashMap();
    protected static boolean e = false;
    private static HostManager l;
    private static HostManagerFactory m;
    private static String n;
    private static String o;
    protected Map<String, a> a = new HashMap();
    protected Context c;
    protected HttpGet d;
    private HostFilter f;
    private String g = "0";
    private long h = 0;
    private final long i = 15;
    private long j = 0;
    private String k = "isp_prov_city_country_ip";

    public interface HostManagerFactory {
        HostManager a(Context context, HostFilter hostFilter, HttpGet httpGet, String str);
    }

    public interface HttpGet {
        String a(String str);
    }

    protected HostManager(Context context, HostFilter hostFilter, HttpGet httpGet, String str, String str2, String str3) {
        this.c = context.getApplicationContext();
        if (this.c == null) {
            this.c = context;
        }
        this.d = httpGet;
        if (hostFilter == null) {
            this.f = new b(this);
        } else {
            this.f = hostFilter;
        }
        this.g = str;
        if (str2 == null) {
            str2 = context.getPackageName();
        }
        n = str2;
        if (str3 == null) {
            str3 = f();
        }
        o = str3;
    }

    private ArrayList<Fallback> a(ArrayList<String> arrayList) {
        int i;
        purge();
        synchronized (this.a) {
            c();
            for (String str : this.a.keySet()) {
                String str2;
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        synchronized (b) {
            for (String str22 : b.keySet()) {
                if (!arrayList.contains(str22)) {
                    arrayList.add(str22);
                }
            }
        }
        if (!arrayList.contains(a())) {
            arrayList.add(a());
        }
        ArrayList<Fallback> arrayList2 = new ArrayList(arrayList.size());
        for (i = 0; i < arrayList.size(); i++) {
            arrayList2.add(null);
        }
        try {
            str22 = d.f(this.c) ? "wifi" : HttpUtils.WAP;
            Object a = a(arrayList, str22, this.g);
            if (!TextUtils.isEmpty(a)) {
                JSONObject jSONObject = new JSONObject(a);
                if ("OK".equalsIgnoreCase(jSONObject.getString("S"))) {
                    jSONObject = jSONObject.getJSONObject("R");
                    String string = jSONObject.getString("province");
                    String string2 = jSONObject.getString("city");
                    String string3 = jSONObject.getString("isp");
                    String string4 = jSONObject.getString("ip");
                    String string5 = jSONObject.getString(g.N);
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str22);
                    if (str22.equals(HttpUtils.WAP)) {
                        str22 = getActiveNetworkLabel();
                    }
                    b.a("get bucket: ip = " + string4 + " net = " + string3 + str22 + " hosts = " + jSONObject2.toString());
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        str22 = (String) arrayList.get(i2);
                        JSONArray optJSONArray = jSONObject2.optJSONArray(str22);
                        if (optJSONArray == null) {
                            b.a("no bucket found for " + str22);
                        } else {
                            Fallback fallback = new Fallback(str22);
                            for (i = 0; i < optJSONArray.length(); i++) {
                                Object string6 = optJSONArray.getString(i);
                                if (!TextUtils.isEmpty(string6)) {
                                    fallback.a(new d(string6, optJSONArray.length() - i));
                                }
                            }
                            arrayList2.set(i2, fallback);
                            fallback.g = string5;
                            fallback.c = string;
                            fallback.e = string3;
                            fallback.f = string4;
                            fallback.d = string2;
                            if (jSONObject.has("stat-percent")) {
                                fallback.a(jSONObject.getDouble("stat-percent"));
                            }
                            if (jSONObject.has("stat-domain")) {
                                fallback.b(jSONObject.getString("stat-domain"));
                            }
                            if (jSONObject.has("ttl")) {
                                fallback.a(((long) jSONObject.getInt("ttl")) * 1000);
                            }
                            setCurrentISP(fallback.e());
                        }
                    }
                }
            }
        } catch (Exception e) {
            b.a("failed to get bucket " + e.getMessage());
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            Fallback fallback2 = (Fallback) arrayList2.get(i3);
            if (fallback2 != null) {
                updateFallbacks((String) arrayList.get(i3), fallback2);
            }
        }
        persist();
        return arrayList2;
    }

    public static void addReservedHost(String str, String str2) {
        ArrayList arrayList = (ArrayList) b.get(str);
        synchronized (b) {
            if (arrayList == null) {
                arrayList = new ArrayList();
                arrayList.add(str2);
                b.put(str, arrayList);
            } else if (!arrayList.contains(str2)) {
                arrayList.add(str2);
            }
        }
    }

    private String f() {
        try {
            PackageInfo packageInfo = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 16384);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
        } catch (Exception e) {
        }
        return "0";
    }

    public static synchronized HostManager getInstance() {
        HostManager hostManager;
        synchronized (HostManager.class) {
            if (l == null) {
                throw new IllegalStateException("the host manager is not initialized yet.");
            }
            hostManager = l;
        }
        return hostManager;
    }

    public static synchronized void init(Context context, HostFilter hostFilter, HttpGet httpGet, String str, String str2, String str3) {
        synchronized (HostManager.class) {
            if (l == null) {
                if (m == null) {
                    l = new HostManager(context, hostFilter, httpGet, str, str2, str3);
                } else {
                    l = m.a(context, hostFilter, httpGet, str);
                }
            }
        }
    }

    public static <T> String join(Collection<T> collection, String str) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            stringBuilder.append(it.next());
            if (it.hasNext()) {
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }

    public static String join(String[] strArr, String str) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            stringBuilder.append(str);
            stringBuilder.append(strArr[i]);
        }
        return stringBuilder.toString();
    }

    public static synchronized void setHostManagerFactory(HostManagerFactory hostManagerFactory) {
        synchronized (HostManager.class) {
            m = hostManagerFactory;
            l = null;
        }
    }

    protected Fallback a(String str) {
        synchronized (this.a) {
            c();
            a aVar = (a) this.a.get(str);
        }
        if (aVar != null) {
            Fallback fallback = aVar.getFallback();
            if (fallback != null) {
                return fallback;
            }
        }
        return null;
    }

    protected String a() {
        return "resolver.gslb.mi-idc.com";
    }

    protected String a(ArrayList<String> arrayList, String str, String str2) {
        ArrayList arrayList2 = new ArrayList();
        List<c> arrayList3 = new ArrayList();
        arrayList3.add(new a("type", str));
        arrayList3.add(new a("uuid", str2));
        arrayList3.add(new a("list", join((Collection) arrayList, Constants.ACCEPT_TIME_SEPARATOR_SP)));
        Fallback a = a("resolver.gslb.mi-idc.com");
        String format = String.format("http://%1$s/gslb/gslb/getbucket.asp?ver=3.0", new Object[]{"resolver.gslb.mi-idc.com"});
        if (a == null) {
            arrayList2.add(format);
        } else {
            arrayList2 = a.a(format);
        }
        Iterator it = arrayList2.iterator();
        IOException iOException = null;
        while (it.hasNext()) {
            Builder buildUpon = Uri.parse((String) it.next()).buildUpon();
            for (c cVar : arrayList3) {
                buildUpon.appendQueryParameter(cVar.a(), cVar.b());
            }
            try {
                return this.d == null ? d.a(this.c, new URL(buildUpon.toString())) : this.d.a(buildUpon.toString());
            } catch (IOException e) {
                iOException = e;
                b.a("network ioErr: " + iOException.getMessage());
            }
        }
        if (iOException == null) {
            return null;
        }
        throw iOException;
    }

    protected Fallback b(String str) {
        if (System.currentTimeMillis() - this.j > (this.h * 60) * 1000) {
            this.j = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            Fallback fallback = (Fallback) a(arrayList).get(0);
            if (fallback != null) {
                this.h = 0;
                return fallback;
            } else if (this.h < 15) {
                this.h++;
            }
        }
        return null;
    }

    protected String b() {
        Reader bufferedReader;
        String stringBuilder;
        Throwable th;
        Throwable th2;
        Reader reader = null;
        try {
            File file = new File(this.c.getFilesDir(), d());
            if (file.isFile()) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                try {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder2.append(readLine);
                    }
                    stringBuilder = stringBuilder2.toString();
                    com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        b.a("load host exception " + th.getMessage());
                        com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
                        return stringBuilder;
                    } catch (Throwable th4) {
                        th2 = th4;
                        com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
                        throw th2;
                    }
                }
            }
            com.xiaomi.channel.commonutils.file.a.a(reader);
        } catch (Throwable th5) {
            bufferedReader = reader;
            th2 = th5;
            com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
            throw th2;
        }
        return stringBuilder;
    }

    protected void c(String str) {
        synchronized (this.a) {
            this.a.clear();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                a fromJSON = new a().fromJSON(jSONArray.getJSONObject(i));
                this.a.put(fromJSON.getHost(), fromJSON);
            }
        }
    }

    protected boolean c() {
        synchronized (this.a) {
            if (e) {
                return true;
            }
            e = true;
            this.a.clear();
            try {
                Object b = b();
                if (!TextUtils.isEmpty(b)) {
                    c(b);
                    b.a("loading the new hosts succeed");
                    return true;
                }
            } catch (Throwable th) {
                b.a("load host exception " + th.getMessage());
            }
        }
        return false;
    }

    public void clear() {
        synchronized (this.a) {
            this.a.clear();
        }
    }

    protected String d() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.c.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return "com.xiaomi";
    }

    protected JSONArray e() {
        JSONArray jSONArray;
        synchronized (this.a) {
            jSONArray = new JSONArray();
            for (a toJSON : this.a.values()) {
                jSONArray.put(toJSON.toJSON());
            }
        }
        return jSONArray;
    }

    public String getActiveNetworkLabel() {
        if (this.c == null) {
            return "unknown";
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.c.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "unknown";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "unknown";
            }
            if (activeNetworkInfo.getType() != 1) {
                return activeNetworkInfo.getTypeName() + Constants.ACCEPT_TIME_SEPARATOR_SERVER + activeNetworkInfo.getSubtypeName();
            }
            WifiManager wifiManager = (WifiManager) this.c.getSystemService("wifi");
            if (!(wifiManager == null || wifiManager.getConnectionInfo() == null)) {
                return "WIFI-" + wifiManager.getConnectionInfo().getSSID();
            }
            return "unknown";
        } catch (Throwable th) {
        }
    }

    public Fallback getFallbacksByHost(String str) {
        return getFallbacksByHost(str, true);
    }

    public Fallback getFallbacksByHost(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        } else if (!this.f.a(str)) {
            return null;
        } else {
            Fallback a = a(str);
            if (a != null && a.b()) {
                return a;
            }
            if (z && d.d(this.c)) {
                Fallback b = b(str);
                if (b != null) {
                    return b;
                }
            }
            return new c(this, str, a);
        }
    }

    public Fallback getFallbacksByURL(String str) {
        if (!TextUtils.isEmpty(str)) {
            return getFallbacksByHost(new URL(str).getHost(), true);
        }
        throw new IllegalArgumentException("the url is empty");
    }

    public void persist() {
        purge();
        synchronized (this.a) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.c.openFileOutput(d(), 0)));
                Object jSONArray = e().toString();
                if (!TextUtils.isEmpty(jSONArray)) {
                    bufferedWriter.write(jSONArray);
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public void purge() {
        synchronized (this.a) {
            for (a purge : this.a.values()) {
                purge.purge(false);
            }
            Object obj = null;
            while (obj == null) {
                for (String str : this.a.keySet()) {
                    if (((a) this.a.get(str)).getFallbacks().isEmpty()) {
                        this.a.remove(str);
                        obj = null;
                        break;
                    }
                }
                obj = 1;
            }
        }
    }

    public void refreshFallbacks() {
        ArrayList arrayList;
        synchronized (this.a) {
            c();
            arrayList = new ArrayList(this.a.keySet());
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                a aVar = (a) this.a.get(arrayList.get(size));
                if (!(aVar == null || aVar.getFallback() == null)) {
                    arrayList.remove(size);
                }
            }
        }
        ArrayList a = a(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            if (a.get(i) != null) {
                updateFallbacks((String) arrayList.get(i), (Fallback) a.get(i));
            }
        }
    }

    public void setCurrentISP(String str) {
        this.k = str;
    }

    public void updateFallbacks(String str, Fallback fallback) {
        if (TextUtils.isEmpty(str) || fallback == null) {
            throw new IllegalArgumentException("the argument is invalid " + str + ", " + fallback);
        } else if (this.f.a(str)) {
            synchronized (this.a) {
                c();
                if (this.a.containsKey(str)) {
                    ((a) this.a.get(str)).addFallback(fallback);
                } else {
                    a aVar = new a(str);
                    aVar.addFallback(fallback);
                    this.a.put(str, aVar);
                }
            }
        }
    }
}
