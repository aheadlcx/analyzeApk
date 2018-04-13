package qsbk.app.image;

import android.support.v4.util.LruCache;

public class OkHttpDnsUtil {
    private final LruCache<String, DnsInfo> a;

    public static class DnsInfo {
        public static final int TYPE_HTTP_DNS = 2;
        public static final int TYPE_LOCAL_DNS = 1;
        public static final int TYPE_UNKNOW_DNS = -1;
        public String host;
        public String ip;
        public int type;

        public DnsInfo(String str, String str2, int i) {
            this.host = str;
            this.ip = str2;
            this.type = i;
        }

        public boolean isLocalDns() {
            return this.type == 1;
        }

        public boolean isHttpDns() {
            return this.type == 2;
        }

        public String toString() {
            return "DnsInfo{host='" + this.host + '\'' + ", ip='" + this.ip + '\'' + ", type=" + this.type + '}';
        }
    }

    private static final class a {
        private static final OkHttpDnsUtil a = new OkHttpDnsUtil();
    }

    private OkHttpDnsUtil() {
        this.a = new LruCache(80);
    }

    public static OkHttpDnsUtil getInstance() {
        return a.a;
    }

    public DnsInfo put(String str, DnsInfo dnsInfo) {
        return (DnsInfo) this.a.put(str, dnsInfo);
    }

    public DnsInfo remove(String str) {
        return (DnsInfo) this.a.remove(str);
    }

    public DnsInfo get(String str) {
        return (DnsInfo) this.a.get(str);
    }

    public void onDestroy() {
        this.a.evictAll();
    }
}
