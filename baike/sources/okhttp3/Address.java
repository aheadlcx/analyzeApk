package okhttp3;

import com.alipay.sdk.util.h;
import com.baidu.mobstat.Config;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl.Builder;
import okhttp3.internal.Util;

public final class Address {
    final HttpUrl a;
    final Dns b;
    final SocketFactory c;
    final Authenticator d;
    final List<Protocol> e;
    final List<ConnectionSpec> f;
    final ProxySelector g;
    @Nullable
    final Proxy h;
    @Nullable
    final SSLSocketFactory i;
    @Nullable
    final HostnameVerifier j;
    @Nullable
    final CertificatePinner k;

    public Address(String str, int i, Dns dns, SocketFactory socketFactory, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier, @Nullable CertificatePinner certificatePinner, Authenticator authenticator, @Nullable Proxy proxy, List<Protocol> list, List<ConnectionSpec> list2, ProxySelector proxySelector) {
        this.a = new Builder().scheme(sSLSocketFactory != null ? "https" : "http").host(str).port(i).build();
        if (dns == null) {
            throw new NullPointerException("dns == null");
        }
        this.b = dns;
        if (socketFactory == null) {
            throw new NullPointerException("socketFactory == null");
        }
        this.c = socketFactory;
        if (authenticator == null) {
            throw new NullPointerException("proxyAuthenticator == null");
        }
        this.d = authenticator;
        if (list == null) {
            throw new NullPointerException("protocols == null");
        }
        this.e = Util.immutableList((List) list);
        if (list2 == null) {
            throw new NullPointerException("connectionSpecs == null");
        }
        this.f = Util.immutableList((List) list2);
        if (proxySelector == null) {
            throw new NullPointerException("proxySelector == null");
        }
        this.g = proxySelector;
        this.h = proxy;
        this.i = sSLSocketFactory;
        this.j = hostnameVerifier;
        this.k = certificatePinner;
    }

    public HttpUrl url() {
        return this.a;
    }

    public Dns dns() {
        return this.b;
    }

    public SocketFactory socketFactory() {
        return this.c;
    }

    public Authenticator proxyAuthenticator() {
        return this.d;
    }

    public List<Protocol> protocols() {
        return this.e;
    }

    public List<ConnectionSpec> connectionSpecs() {
        return this.f;
    }

    public ProxySelector proxySelector() {
        return this.g;
    }

    @Nullable
    public Proxy proxy() {
        return this.h;
    }

    @Nullable
    public SSLSocketFactory sslSocketFactory() {
        return this.i;
    }

    @Nullable
    public HostnameVerifier hostnameVerifier() {
        return this.j;
    }

    @Nullable
    public CertificatePinner certificatePinner() {
        return this.k;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof Address) && this.a.equals(((Address) obj).a) && a((Address) obj);
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (((((((((((this.a.hashCode() + 527) * 31) + this.b.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31;
        if (this.h != null) {
            hashCode = this.h.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.i != null) {
            hashCode = this.i.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.j != null) {
            hashCode = this.j.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.k != null) {
            i = this.k.hashCode();
        }
        return hashCode + i;
    }

    boolean a(Address address) {
        if (this.b.equals(address.b) && this.d.equals(address.d) && this.e.equals(address.e) && this.f.equals(address.f) && this.g.equals(address.g) && Util.equal(this.h, address.h) && Util.equal(this.i, address.i) && Util.equal(this.j, address.j) && Util.equal(this.k, address.k) && url().port() == address.url().port()) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder append = new StringBuilder().append("Address{").append(this.a.host()).append(Config.TRACE_TODAY_VISIT_SPLIT).append(this.a.port());
        if (this.h != null) {
            append.append(", proxy=").append(this.h);
        } else {
            append.append(", proxySelector=").append(this.g);
        }
        append.append(h.d);
        return append.toString();
    }
}
