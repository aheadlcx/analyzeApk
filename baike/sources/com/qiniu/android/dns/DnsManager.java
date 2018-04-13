package com.qiniu.android.dns;

import com.qiniu.android.dns.local.Hosts;
import com.qiniu.android.dns.local.Hosts.Value;
import com.qiniu.android.dns.util.LruCache;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

public final class DnsManager {
    private final IResolver[] a;
    private final LruCache<String, Record[]> b;
    private final Hosts c;
    private final IpSorter d;
    private volatile NetworkInfo e;
    private volatile int f;

    private static class a implements IpSorter {
        private AtomicInteger a;

        private a() {
            this.a = new AtomicInteger();
        }

        public String[] sort(String[] strArr) {
            return strArr;
        }
    }

    public DnsManager(NetworkInfo networkInfo, IResolver[] iResolverArr) {
        this(networkInfo, iResolverArr, null);
    }

    public DnsManager(NetworkInfo networkInfo, IResolver[] iResolverArr, IpSorter ipSorter) {
        this.c = new Hosts();
        this.e = null;
        this.f = 0;
        if (networkInfo == null) {
            networkInfo = NetworkInfo.normal;
        }
        this.e = networkInfo;
        this.a = (IResolver[]) iResolverArr.clone();
        this.b = new LruCache();
        if (ipSorter == null) {
            ipSorter = new a();
        }
        this.d = ipSorter;
    }

    private static Record[] a(Record[] recordArr) {
        ArrayList arrayList = new ArrayList(recordArr.length);
        for (Record record : recordArr) {
            if (record != null && record.type == 1) {
                arrayList.add(record);
            }
        }
        return (Record[]) arrayList.toArray(new Record[arrayList.size()]);
    }

    private static void b(Record[] recordArr) {
        if (recordArr != null && recordArr.length > 1) {
            Record record = recordArr[0];
            System.arraycopy(recordArr, 1, recordArr, 0, recordArr.length - 1);
            recordArr[recordArr.length - 1] = record;
        }
    }

    private static String[] c(Record[] recordArr) {
        if (recordArr == null || recordArr.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(recordArr.length);
        for (Record record : recordArr) {
            arrayList.add(record.value);
        }
        if (arrayList.size() != 0) {
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        return null;
    }

    public static boolean validIP(String str) {
        if (str == null || str.length() < 7 || str.length() > 15 || str.contains(Constants.ACCEPT_TIME_SEPARATOR_SERVER)) {
            return false;
        }
        try {
            int indexOf = str.indexOf(46);
            if (indexOf != -1 && Integer.parseInt(str.substring(0, indexOf)) > 255) {
                return false;
            }
            indexOf++;
            int indexOf2 = str.indexOf(46, indexOf);
            if (indexOf2 != -1 && Integer.parseInt(str.substring(indexOf, indexOf2)) > 255) {
                return false;
            }
            indexOf2++;
            indexOf = str.indexOf(46, indexOf2);
            if (indexOf == -1 || Integer.parseInt(str.substring(indexOf2, indexOf)) <= 255 || Integer.parseInt(str.substring(indexOf + 1, str.length() - 1)) <= 255 || str.charAt(str.length() - 1) == '.') {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean needHttpDns() {
        try {
            String id = TimeZone.getDefault().getID();
            if ("Asia/Shanghai".equals(id) || "Asia/Chongqing".equals(id) || "Asia/Harbin".equals(id) || "Asia/Urumqi".equals(id)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String[] query(String str) throws IOException {
        return query(new Domain(str));
    }

    public String[] query(Domain domain) throws IOException {
        if (domain == null) {
            throw new IOException("null domain");
        } else if (domain.domain == null || domain.domain.trim().length() == 0) {
            throw new IOException("empty domain " + domain.domain);
        } else if (validIP(domain.domain)) {
            return new String[]{domain.domain};
        } else {
            String[] a = a(domain);
            return (a == null || a.length <= 1) ? a : this.d.sort(a);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String[] a(com.qiniu.android.dns.Domain r11) throws java.io.IOException {
        /*
        r10 = this;
        r2 = 0;
        r1 = 0;
        r0 = r11.hostsFirst;
        if (r0 == 0) goto L_0x0014;
    L_0x0006:
        r0 = r10.c;
        r3 = r10.e;
        r0 = r0.query(r11, r3);
        if (r0 == 0) goto L_0x0014;
    L_0x0010:
        r3 = r0.length;
        if (r3 == 0) goto L_0x0014;
    L_0x0013:
        return r0;
    L_0x0014:
        r3 = r10.b;
        monitor-enter(r3);
        r0 = r10.e;	 Catch:{ all -> 0x0087 }
        r4 = com.qiniu.android.dns.NetworkInfo.normal;	 Catch:{ all -> 0x0087 }
        r0 = r0.equals(r4);	 Catch:{ all -> 0x0087 }
        if (r0 == 0) goto L_0x008a;
    L_0x0021:
        r0 = com.qiniu.android.dns.Network.isNetworkChanged();	 Catch:{ all -> 0x0087 }
        if (r0 == 0) goto L_0x008a;
    L_0x0027:
        r0 = r10.b;	 Catch:{ all -> 0x0087 }
        r0.clear();	 Catch:{ all -> 0x0087 }
        r4 = r10.a;	 Catch:{ all -> 0x0087 }
        monitor-enter(r4);	 Catch:{ all -> 0x0087 }
        r0 = 0;
        r10.f = r0;	 Catch:{ all -> 0x0084 }
        monitor-exit(r4);	 Catch:{ all -> 0x0084 }
        r0 = r2;
    L_0x0034:
        monitor-exit(r3);	 Catch:{ all -> 0x0087 }
        r4 = r10.f;
        r9 = r1;
        r1 = r0;
        r0 = r2;
        r2 = r9;
    L_0x003b:
        r3 = r10.a;
        r3 = r3.length;
        if (r2 >= r3) goto L_0x00ca;
    L_0x0040:
        r3 = r4 + r2;
        r5 = r10.a;
        r5 = r5.length;
        r3 = r3 % r5;
        r5 = r10.e;
        r6 = com.qiniu.android.dns.Network.getIp();
        r7 = r10.a;	 Catch:{ DomainNotOwn -> 0x0111, IOException -> 0x00b2, Exception -> 0x00b7 }
        r3 = r7[r3];	 Catch:{ DomainNotOwn -> 0x0111, IOException -> 0x00b2, Exception -> 0x00b7 }
        r7 = r10.e;	 Catch:{ DomainNotOwn -> 0x0111, IOException -> 0x00b2, Exception -> 0x00b7 }
        r1 = r3.resolve(r11, r7);	 Catch:{ DomainNotOwn -> 0x0111, IOException -> 0x00b2, Exception -> 0x00b7 }
    L_0x0056:
        r3 = com.qiniu.android.dns.Network.getIp();
        r7 = r10.e;
        if (r7 != r5) goto L_0x00ca;
    L_0x005e:
        if (r1 == 0) goto L_0x0063;
    L_0x0060:
        r5 = r1.length;
        if (r5 != 0) goto L_0x00ca;
    L_0x0063:
        r3 = r6.equals(r3);
        if (r3 == 0) goto L_0x00ca;
    L_0x0069:
        r3 = r10.a;
        monitor-enter(r3);
        r5 = r10.f;	 Catch:{ all -> 0x00c7 }
        if (r5 != r4) goto L_0x0080;
    L_0x0070:
        r5 = r10.f;	 Catch:{ all -> 0x00c7 }
        r5 = r5 + 1;
        r10.f = r5;	 Catch:{ all -> 0x00c7 }
        r5 = r10.f;	 Catch:{ all -> 0x00c7 }
        r6 = r10.a;	 Catch:{ all -> 0x00c7 }
        r6 = r6.length;	 Catch:{ all -> 0x00c7 }
        if (r5 != r6) goto L_0x0080;
    L_0x007d:
        r5 = 0;
        r10.f = r5;	 Catch:{ all -> 0x00c7 }
    L_0x0080:
        monitor-exit(r3);	 Catch:{ all -> 0x00c7 }
    L_0x0081:
        r2 = r2 + 1;
        goto L_0x003b;
    L_0x0084:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0084 }
        throw r0;	 Catch:{ all -> 0x0087 }
    L_0x0087:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0087 }
        throw r0;
    L_0x008a:
        r0 = r10.b;	 Catch:{ all -> 0x0087 }
        r4 = r11.domain;	 Catch:{ all -> 0x0087 }
        r0 = r0.get(r4);	 Catch:{ all -> 0x0087 }
        r0 = (com.qiniu.android.dns.Record[]) r0;	 Catch:{ all -> 0x0087 }
        if (r0 == 0) goto L_0x0034;
    L_0x0096:
        r4 = r0.length;	 Catch:{ all -> 0x0087 }
        if (r4 == 0) goto L_0x0034;
    L_0x0099:
        r4 = 0;
        r4 = r0[r4];	 Catch:{ all -> 0x0087 }
        r4 = r4.isExpired();	 Catch:{ all -> 0x0087 }
        if (r4 != 0) goto L_0x00b0;
    L_0x00a2:
        r1 = r0.length;	 Catch:{ all -> 0x0087 }
        r2 = 1;
        if (r1 <= r2) goto L_0x00a9;
    L_0x00a6:
        b(r0);	 Catch:{ all -> 0x0087 }
    L_0x00a9:
        r0 = c(r0);	 Catch:{ all -> 0x0087 }
        monitor-exit(r3);	 Catch:{ all -> 0x0087 }
        goto L_0x0013;
    L_0x00b0:
        r0 = r2;
        goto L_0x0034;
    L_0x00b2:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0056;
    L_0x00b7:
        r3 = move-exception;
        r7 = android.os.Build.VERSION.SDK_INT;
        r8 = 9;
        if (r7 < r8) goto L_0x00c3;
    L_0x00be:
        r0 = new java.io.IOException;
        r0.<init>(r3);
    L_0x00c3:
        r3.printStackTrace();
        goto L_0x0056;
    L_0x00c7:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x00c7 }
        throw r0;
    L_0x00ca:
        r9 = r0;
        r0 = r1;
        r1 = r9;
        if (r0 == 0) goto L_0x00d2;
    L_0x00cf:
        r2 = r0.length;
        if (r2 != 0) goto L_0x00ee;
    L_0x00d2:
        r0 = r11.hostsFirst;
        if (r0 != 0) goto L_0x00e3;
    L_0x00d6:
        r0 = r10.c;
        r2 = r10.e;
        r0 = r0.query(r11, r2);
        if (r0 == 0) goto L_0x00e3;
    L_0x00e0:
        r2 = r0.length;
        if (r2 != 0) goto L_0x0013;
    L_0x00e3:
        if (r1 == 0) goto L_0x00e6;
    L_0x00e5:
        throw r1;
    L_0x00e6:
        r0 = new java.net.UnknownHostException;
        r1 = r11.domain;
        r0.<init>(r1);
        throw r0;
    L_0x00ee:
        r0 = a(r0);
        r1 = r0.length;
        if (r1 != 0) goto L_0x00fd;
    L_0x00f5:
        r0 = new java.net.UnknownHostException;
        r1 = "no A records";
        r0.<init>(r1);
        throw r0;
    L_0x00fd:
        r1 = r10.b;
        monitor-enter(r1);
        r2 = r10.b;	 Catch:{ all -> 0x010e }
        r3 = r11.domain;	 Catch:{ all -> 0x010e }
        r2.put(r3, r0);	 Catch:{ all -> 0x010e }
        monitor-exit(r1);	 Catch:{ all -> 0x010e }
        r0 = c(r0);
        goto L_0x0013;
    L_0x010e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x010e }
        throw r0;
    L_0x0111:
        r3 = move-exception;
        goto L_0x0081;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qiniu.android.dns.DnsManager.a(com.qiniu.android.dns.Domain):java.lang.String[]");
    }

    public InetAddress[] queryInetAdress(Domain domain) throws IOException {
        String[] query = query(domain);
        InetAddress[] inetAddressArr = new InetAddress[query.length];
        for (int i = 0; i < query.length; i++) {
            inetAddressArr[i] = InetAddress.getByName(query[i]);
        }
        return inetAddressArr;
    }

    public void onNetworkChange(NetworkInfo networkInfo) {
        a();
        if (networkInfo == null) {
            networkInfo = NetworkInfo.normal;
        }
        this.e = networkInfo;
        synchronized (this.a) {
            this.f = 0;
        }
    }

    private void a() {
        synchronized (this.b) {
            this.b.clear();
        }
    }

    public DnsManager putHosts(String str, String str2, int i) {
        this.c.put(str, new Value(str2, i));
        return this;
    }

    public DnsManager putHosts(String str, String str2) {
        this.c.put(str, str2);
        return this;
    }
}
