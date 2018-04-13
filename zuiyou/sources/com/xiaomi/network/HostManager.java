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
import android.support.media.ExifInterface;
import android.text.TextUtils;
import com.umeng.analytics.b.g;
import com.xiaomi.channel.commonutils.network.c;
import com.xiaomi.channel.commonutils.network.d;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HostManager {
    private static a factory;
    protected static boolean hostLoaded = false;
    protected static Context sAppContext;
    private static String sAppName;
    private static String sAppVersion;
    private static HostManager sInstance;
    protected static Map<String, Fallback> sReservedHosts = new HashMap();
    private final long MAX_REQUEST_FAILURE_CNT;
    private String currentISP;
    private long lastRemoteRequestTimestamp;
    protected Map<String, Fallbacks> mHostsMapping;
    private long remoteRequestFailureCount;
    private d sHostFilter;
    protected b sHttpGetter;
    private String sUserId;

    public interface a {
        HostManager a(Context context, d dVar, b bVar, String str);
    }

    public interface b {
        String a(String str);
    }

    protected HostManager(Context context, d dVar, b bVar, String str) {
        this(context, dVar, bVar, str, null, null);
    }

    protected HostManager(Context context, d dVar, b bVar, String str, String str2, String str3) {
        this.mHostsMapping = new HashMap();
        this.sUserId = "0";
        this.remoteRequestFailureCount = 0;
        this.MAX_REQUEST_FAILURE_CNT = 15;
        this.lastRemoteRequestTimestamp = 0;
        this.currentISP = "isp_prov_city_country_ip";
        this.sHttpGetter = bVar;
        if (dVar == null) {
            this.sHostFilter = new a(this);
        } else {
            this.sHostFilter = dVar;
        }
        this.sUserId = str;
        if (str2 == null) {
            str2 = context.getPackageName();
        }
        sAppName = str2;
        if (str3 == null) {
            str3 = getVersionName();
        }
        sAppVersion = str3;
    }

    public static void addReservedHost(String str, String str2) {
        Fallback fallback = (Fallback) sReservedHosts.get(str);
        synchronized (sReservedHosts) {
            if (fallback == null) {
                fallback = new Fallback(str);
                fallback.a(604800000);
                fallback.b(str2);
                sReservedHosts.put(str, fallback);
            } else {
                fallback.b(str2);
            }
        }
    }

    static String getActiveNetworkLabel() {
        if (sAppContext == null) {
            return "unknown";
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) sAppContext.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "unknown";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "unknown";
            }
            if (activeNetworkInfo.getType() != 1) {
                return activeNetworkInfo.getTypeName() + "-" + activeNetworkInfo.getSubtypeName();
            }
            WifiManager wifiManager = (WifiManager) sAppContext.getSystemService("wifi");
            if (!(wifiManager == null || wifiManager.getConnectionInfo() == null)) {
                return "WIFI-" + wifiManager.getConnectionInfo().getSSID();
            }
            return "unknown";
        } catch (Throwable th) {
        }
    }

    public static synchronized HostManager getInstance() {
        HostManager hostManager;
        synchronized (HostManager.class) {
            if (sInstance == null) {
                throw new IllegalStateException("the host manager is not initialized yet.");
            }
            hostManager = sInstance;
        }
        return hostManager;
    }

    private String getVersionName() {
        try {
            PackageInfo packageInfo = sAppContext.getPackageManager().getPackageInfo(sAppContext.getPackageName(), 16384);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
        } catch (Exception e) {
        }
        return "0";
    }

    public static synchronized void init(Context context, d dVar, b bVar, String str, String str2, String str3) {
        synchronized (HostManager.class) {
            sAppContext = context.getApplicationContext();
            if (sAppContext == null) {
                sAppContext = context;
            }
            if (sInstance == null) {
                if (factory == null) {
                    sInstance = new HostManager(context, dVar, bVar, str, str2, str3);
                } else {
                    sInstance = factory.a(context, dVar, bVar, str);
                }
            }
        }
    }

    static String obfuscate(String str) {
        try {
            int length = str.length();
            byte[] bytes = str.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                if ((b & 240) != 240) {
                    bytes[i] = (byte) (((b & 15) ^ ((byte) (((b >> 4) + length) & 15))) | (b & 240));
                }
            }
            return new String(bytes);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    private ArrayList<Fallback> requestRemoteFallbacks(ArrayList<String> arrayList) {
        String str;
        int i;
        purge();
        synchronized (this.mHostsMapping) {
            checkHostMapping();
            for (String str2 : this.mHostsMapping.keySet()) {
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        boolean isEmpty = sReservedHosts.isEmpty();
        synchronized (sReservedHosts) {
            Object[] toArray = sReservedHosts.values().toArray();
            int length = toArray.length;
            int i2 = 0;
            while (i2 < length) {
                Fallback fallback = (Fallback) toArray[i2];
                if (!fallback.b()) {
                    isEmpty = true;
                    sReservedHosts.remove(fallback.b);
                }
                i2++;
                isEmpty = isEmpty;
            }
        }
        if (!arrayList.contains(getHost())) {
            arrayList.add(getHost());
        }
        ArrayList<Fallback> arrayList2 = new ArrayList(arrayList.size());
        for (i = 0; i < arrayList.size(); i++) {
            arrayList2.add(null);
        }
        try {
            str2 = d.e(sAppContext) ? "wifi" : "wap";
            String remoteFallbackJSON = getRemoteFallbackJSON(arrayList, str2, this.sUserId, isEmpty);
            if (!TextUtils.isEmpty(remoteFallbackJSON)) {
                JSONObject jSONObject = new JSONObject(remoteFallbackJSON);
                com.xiaomi.channel.commonutils.logger.b.b(remoteFallbackJSON);
                if ("OK".equalsIgnoreCase(jSONObject.getString(ExifInterface.LATITUDE_SOUTH))) {
                    int i3;
                    jSONObject = jSONObject.getJSONObject("R");
                    String string = jSONObject.getString("province");
                    String string2 = jSONObject.getString("city");
                    String string3 = jSONObject.getString("isp");
                    String string4 = jSONObject.getString("ip");
                    String string5 = jSONObject.getString(g.G);
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                    if (str2.equals("wap")) {
                        str2 = getActiveNetworkLabel();
                    }
                    com.xiaomi.channel.commonutils.logger.b.a("get bucket: ip = " + string4 + " net = " + string3 + str2 + " hosts = " + jSONObject2.toString());
                    for (i3 = 0; i3 < arrayList.size(); i3++) {
                        str2 = (String) arrayList.get(i3);
                        JSONArray optJSONArray = jSONObject2.optJSONArray(str2);
                        if (optJSONArray == null) {
                            com.xiaomi.channel.commonutils.logger.b.a("no bucket found for " + str2);
                        } else {
                            Fallback fallback2 = new Fallback(str2);
                            for (i = 0; i < optJSONArray.length(); i++) {
                                Object string6 = optJSONArray.getString(i);
                                if (!TextUtils.isEmpty(string6)) {
                                    fallback2.a(new c(string6, optJSONArray.length() - i));
                                }
                            }
                            arrayList2.set(i3, fallback2);
                            fallback2.g = string5;
                            fallback2.c = string;
                            fallback2.e = string3;
                            fallback2.f = string4;
                            fallback2.d = string2;
                            if (jSONObject.has("stat-percent")) {
                                fallback2.a(jSONObject.getDouble("stat-percent"));
                            }
                            if (jSONObject.has("stat-domain")) {
                                fallback2.c(jSONObject.getString("stat-domain"));
                            }
                            if (jSONObject.has("ttl")) {
                                fallback2.a(((long) jSONObject.getInt("ttl")) * 1000);
                            }
                            setCurrentISP(fallback2.e());
                        }
                    }
                    JSONObject optJSONObject = jSONObject.optJSONObject("reserved");
                    if (optJSONObject != null) {
                        long j = jSONObject.has("reserved-ttl") ? ((long) jSONObject.getInt("reserved-ttl")) * 1000 : 604800000;
                        Iterator keys = optJSONObject.keys();
                        while (keys.hasNext()) {
                            str2 = (String) keys.next();
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray(str2);
                            if (optJSONArray2 == null) {
                                com.xiaomi.channel.commonutils.logger.b.a("no bucket found for " + str2);
                            } else {
                                Fallback fallback3 = new Fallback(str2);
                                fallback3.a(j);
                                for (i3 = 0; i3 < optJSONArray2.length(); i3++) {
                                    Object string7 = optJSONArray2.getString(i3);
                                    if (!TextUtils.isEmpty(string7)) {
                                        fallback3.a(new c(string7, optJSONArray2.length() - i3));
                                    }
                                }
                                synchronized (sReservedHosts) {
                                    if (this.sHostFilter.a(str2)) {
                                        sReservedHosts.put(str2, fallback3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a("failed to get bucket " + e.getMessage());
        }
        for (i2 = 0; i2 < arrayList.size(); i2++) {
            fallback = (Fallback) arrayList2.get(i2);
            if (fallback != null) {
                updateFallbacks((String) arrayList.get(i2), fallback);
            }
        }
        persist();
        return arrayList2;
    }

    public static synchronized void setHostManagerFactory(a aVar) {
        synchronized (HostManager.class) {
            factory = aVar;
            sInstance = null;
        }
    }

    protected boolean checkHostMapping() {
        synchronized (this.mHostsMapping) {
            if (hostLoaded) {
                return true;
            }
            hostLoaded = true;
            this.mHostsMapping.clear();
            try {
                Object loadHosts = loadHosts();
                if (!TextUtils.isEmpty(loadHosts)) {
                    fromJSON(loadHosts);
                    com.xiaomi.channel.commonutils.logger.b.b("loading the new hosts succeed");
                    return true;
                }
            } catch (Throwable th) {
                com.xiaomi.channel.commonutils.logger.b.a("load bucket failure: " + th.getMessage());
            }
        }
        return false;
    }

    public void clear() {
        synchronized (this.mHostsMapping) {
            this.mHostsMapping.clear();
        }
    }

    protected void fromJSON(String str) {
        int i = 0;
        synchronized (this.mHostsMapping) {
            this.mHostsMapping.clear();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("ver") != 2) {
                throw new JSONException("Bad version");
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                Fallbacks fromJSON = new Fallbacks().fromJSON(optJSONArray.getJSONObject(i2));
                this.mHostsMapping.put(fromJSON.getHost(), fromJSON);
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("reserved");
            while (i < optJSONArray2.length()) {
                Fallback a = new Fallback("").a(optJSONArray2.getJSONObject(i));
                sReservedHosts.put(a.b, a);
                i++;
            }
        }
    }

    public Fallback getFallbacksByHost(String str) {
        return getFallbacksByHost(str, true);
    }

    public Fallback getFallbacksByHost(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        } else if (!this.sHostFilter.a(str)) {
            return null;
        } else {
            Fallback localFallback = getLocalFallback(str);
            if (localFallback != null && localFallback.b()) {
                return localFallback;
            }
            if (z && d.c(sAppContext)) {
                Fallback requestRemoteFallback = requestRemoteFallback(str);
                if (requestRemoteFallback != null) {
                    return requestRemoteFallback;
                }
            }
            return new b(this, str, localFallback);
        }
    }

    public Fallback getFallbacksByURL(String str) {
        if (!TextUtils.isEmpty(str)) {
            return getFallbacksByHost(new URL(str).getHost(), true);
        }
        throw new IllegalArgumentException("the url is empty");
    }

    protected String getHost() {
        return "resolver.msg.xiaomi.net";
    }

    protected Fallback getLocalFallback(String str) {
        synchronized (this.mHostsMapping) {
            checkHostMapping();
            Fallbacks fallbacks = (Fallbacks) this.mHostsMapping.get(str);
        }
        if (fallbacks != null) {
            Fallback fallback = fallbacks.getFallback();
            if (fallback != null) {
                return fallback;
            }
        }
        return null;
    }

    protected String getProcessName() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) sAppContext.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return "com.xiaomi";
    }

    protected String getRemoteFallbackJSON(ArrayList<String> arrayList, String str, String str2, boolean z) {
        Iterator it;
        ArrayList arrayList2;
        ArrayList arrayList3 = new ArrayList();
        List<c> arrayList4 = new ArrayList();
        arrayList4.add(new com.xiaomi.channel.commonutils.network.a("type", str));
        if (str.equals("wap")) {
            arrayList4.add(new com.xiaomi.channel.commonutils.network.a("conpt", obfuscate(d.k(sAppContext))));
        }
        if (z) {
            arrayList4.add(new com.xiaomi.channel.commonutils.network.a("reserved", "1"));
        }
        arrayList4.add(new com.xiaomi.channel.commonutils.network.a("uuid", str2));
        arrayList4.add(new com.xiaomi.channel.commonutils.network.a("list", com.xiaomi.channel.commonutils.string.d.a((Collection) arrayList, ",")));
        Fallback localFallback = getLocalFallback("resolver.msg.xiaomi.net");
        String format = String.format(Locale.US, "http://%1$s/gslb/?ver=4.0", new Object[]{"resolver.msg.xiaomi.net"});
        if (localFallback == null) {
            arrayList3.add(format);
            synchronized (sReservedHosts) {
                localFallback = (Fallback) sReservedHosts.get("resolver.msg.xiaomi.net");
                if (localFallback != null) {
                    it = localFallback.a(true).iterator();
                    while (it.hasNext()) {
                        String str3 = (String) it.next();
                        arrayList3.add(String.format(Locale.US, "http://%1$s/gslb/?ver=4.0", new Object[]{str3}));
                    }
                }
            }
            arrayList2 = arrayList3;
        } else {
            arrayList2 = localFallback.a(format);
        }
        Iterator it2 = arrayList2.iterator();
        IOException iOException = null;
        while (it2.hasNext()) {
            Builder buildUpon = Uri.parse((String) it2.next()).buildUpon();
            for (c cVar : arrayList4) {
                buildUpon.appendQueryParameter(cVar.a(), cVar.b());
            }
            try {
                return this.sHttpGetter == null ? d.a(sAppContext, new URL(buildUpon.toString())) : this.sHttpGetter.a(buildUpon.toString());
            } catch (IOException e) {
                iOException = e;
            }
        }
        if (iOException == null) {
            return null;
        }
        com.xiaomi.channel.commonutils.logger.b.a("network exception: " + iOException.getMessage());
        throw iOException;
    }

    protected String loadHosts() {
        Reader bufferedReader;
        String stringBuilder;
        Throwable th;
        Throwable th2;
        Reader reader = null;
        try {
            File file = new File(sAppContext.getFilesDir(), getProcessName());
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
                        com.xiaomi.channel.commonutils.logger.b.a("load host exception " + th.getMessage());
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

    public void persist() {
        synchronized (this.mHostsMapping) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(sAppContext.openFileOutput(getProcessName(), 0)));
                Object jSONObject = toJSON().toString();
                if (!TextUtils.isEmpty(jSONObject)) {
                    bufferedWriter.write(jSONObject);
                }
                bufferedWriter.close();
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a("persist bucket failure: " + e.getMessage());
            }
        }
    }

    public void purge() {
        synchronized (this.mHostsMapping) {
            for (Fallbacks purge : this.mHostsMapping.values()) {
                purge.purge(true);
            }
            Object obj = null;
            while (obj == null) {
                for (String str : this.mHostsMapping.keySet()) {
                    if (((Fallbacks) this.mHostsMapping.get(str)).getFallbacks().isEmpty()) {
                        this.mHostsMapping.remove(str);
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
        synchronized (this.mHostsMapping) {
            checkHostMapping();
            arrayList = new ArrayList(this.mHostsMapping.keySet());
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                Fallbacks fallbacks = (Fallbacks) this.mHostsMapping.get(arrayList.get(size));
                if (!(fallbacks == null || fallbacks.getFallback() == null)) {
                    arrayList.remove(size);
                }
            }
        }
        ArrayList requestRemoteFallbacks = requestRemoteFallbacks(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            if (requestRemoteFallbacks.get(i) != null) {
                updateFallbacks((String) arrayList.get(i), (Fallback) requestRemoteFallbacks.get(i));
            }
        }
    }

    protected Fallback requestRemoteFallback(String str) {
        if (System.currentTimeMillis() - this.lastRemoteRequestTimestamp > (this.remoteRequestFailureCount * 60) * 1000) {
            this.lastRemoteRequestTimestamp = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            Fallback fallback = (Fallback) requestRemoteFallbacks(arrayList).get(0);
            if (fallback != null) {
                this.remoteRequestFailureCount = 0;
                return fallback;
            } else if (this.remoteRequestFailureCount < 15) {
                this.remoteRequestFailureCount++;
            }
        }
        return null;
    }

    public void setCurrentISP(String str) {
        this.currentISP = str;
    }

    protected JSONObject toJSON() {
        JSONObject jSONObject;
        synchronized (this.mHostsMapping) {
            jSONObject = new JSONObject();
            jSONObject.put("ver", 2);
            JSONArray jSONArray = new JSONArray();
            for (Fallbacks toJSON : this.mHostsMapping.values()) {
                jSONArray.put(toJSON.toJSON());
            }
            jSONObject.put("data", jSONArray);
            jSONArray = new JSONArray();
            for (Fallback f : sReservedHosts.values()) {
                jSONArray.put(f.f());
            }
            jSONObject.put("reserved", jSONArray);
        }
        return jSONObject;
    }

    public void updateFallbacks(String str, Fallback fallback) {
        if (TextUtils.isEmpty(str) || fallback == null) {
            throw new IllegalArgumentException("the argument is invalid " + str + ", " + fallback);
        } else if (this.sHostFilter.a(str)) {
            synchronized (this.mHostsMapping) {
                checkHostMapping();
                if (this.mHostsMapping.containsKey(str)) {
                    ((Fallbacks) this.mHostsMapping.get(str)).addFallback(fallback);
                } else {
                    Fallbacks fallbacks = new Fallbacks(str);
                    fallbacks.addFallback(fallback);
                    this.mHostsMapping.put(str, fallbacks);
                }
            }
        }
    }
}
