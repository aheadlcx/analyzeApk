package com.qiniu.android.storage;

import com.qiniu.android.common.Zone;
import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.local.AndroidDnsServer;
import com.qiniu.android.dns.local.Resolver;
import com.qiniu.android.http.ProxyConfiguration;
import com.qiniu.android.http.UrlConverter;
import java.io.IOException;
import java.net.InetAddress;

public final class Configuration {
    public static final int BLOCK_SIZE = 4194304;
    public final int chunkSize;
    public final int connectTimeout;
    public DnsManager dns;
    public final KeyGenerator keyGen;
    public final ProxyConfiguration proxy;
    public final int putThreshold;
    public final Recorder recorder;
    public final int responseTimeout;
    public final int retryMax;
    public UrlConverter urlConverter;
    public Zone zone;

    public static class Builder {
        private Zone a = null;
        private Recorder b = null;
        private KeyGenerator c = null;
        private ProxyConfiguration d = null;
        private int e = 262144;
        private int f = 524288;
        private int g = 10;
        private int h = 60;
        private int i = 3;
        private UrlConverter j = null;
        private DnsManager k = null;

        public Builder() {
            Resolver resolver;
            IResolver defaultResolver = AndroidDnsServer.defaultResolver();
            try {
                resolver = new Resolver(InetAddress.getByName("119.29.29.29"));
            } catch (IOException e) {
                e.printStackTrace();
                resolver = null;
            }
            this.k = new DnsManager(NetworkInfo.normal, new IResolver[]{defaultResolver, resolver});
        }

        public Builder zone(Zone zone) {
            this.a = zone;
            return this;
        }

        public Builder recorder(Recorder recorder) {
            this.b = recorder;
            return this;
        }

        public Builder recorder(Recorder recorder, KeyGenerator keyGenerator) {
            this.b = recorder;
            this.c = keyGenerator;
            return this;
        }

        public Builder proxy(ProxyConfiguration proxyConfiguration) {
            this.d = proxyConfiguration;
            return this;
        }

        public Builder chunkSize(int i) {
            this.e = i;
            return this;
        }

        public Builder putThreshhold(int i) {
            this.f = i;
            return this;
        }

        public Builder connectTimeout(int i) {
            this.g = i;
            return this;
        }

        public Builder responseTimeout(int i) {
            this.h = i;
            return this;
        }

        public Builder retryMax(int i) {
            this.i = i;
            return this;
        }

        public Builder urlConverter(UrlConverter urlConverter) {
            this.j = urlConverter;
            return this;
        }

        public Builder dns(DnsManager dnsManager) {
            this.k = dnsManager;
            return this;
        }

        public Configuration build() {
            return new Configuration();
        }
    }

    private Configuration(Builder builder) {
        this.chunkSize = builder.e;
        this.putThreshold = builder.f;
        this.connectTimeout = builder.g;
        this.responseTimeout = builder.h;
        this.recorder = builder.b;
        this.keyGen = a(builder.c);
        this.retryMax = builder.i;
        this.proxy = builder.d;
        this.urlConverter = builder.j;
        this.zone = builder.a == null ? Zone.zone0 : builder.a;
        this.dns = a(builder);
    }

    private static DnsManager a(Builder builder) {
        DnsManager k = builder.k;
        if (k != null) {
            Zone.addDnsIp(k);
        }
        return k;
    }

    private KeyGenerator a(KeyGenerator keyGenerator) {
        if (keyGenerator == null) {
            return new a(this);
        }
        return keyGenerator;
    }
}
