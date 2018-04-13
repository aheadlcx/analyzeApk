package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* renamed from: com.crashlytics.android.internal.av */
public class C0011av {
    private final q a;
    private aG b;
    private SSLSocketFactory c;
    private boolean d;

    public static X509Certificate[] a(X509Certificate[] x509CertificateArr, bm bmVar) throws CertificateException {
        int i;
        int i2 = 1;
        LinkedList linkedList = new LinkedList();
        if (bmVar.a(x509CertificateArr[0])) {
            i = 1;
        } else {
            i = 0;
        }
        linkedList.add(x509CertificateArr[0]);
        int i3 = i;
        i = 1;
        while (i < x509CertificateArr.length) {
            if (bmVar.a(x509CertificateArr[i])) {
                i3 = 1;
            }
            if (!C0011av.a(x509CertificateArr[i], x509CertificateArr[i - 1])) {
                break;
            }
            linkedList.add(x509CertificateArr[i]);
            i++;
        }
        X509Certificate b = bmVar.b(x509CertificateArr[i - 1]);
        if (b != null) {
            linkedList.add(b);
        } else {
            i2 = i3;
        }
        if (i2 != 0) {
            return (X509Certificate[]) linkedList.toArray(new X509Certificate[linkedList.size()]);
        }
        throw new CertificateException("Didn't find a trust anchor in chain cleanup!");
    }

    public C0011av() {
        this(new r());
    }

    public C0011av(q qVar) {
        this.a = qVar;
    }

    public void a(aG aGVar) {
        if (this.b != aGVar) {
            this.b = aGVar;
            a();
        }
    }

    private synchronized void a() {
        this.d = false;
        this.c = null;
    }

    public C0013ay a(C0012ax c0012ax, String str) {
        return a(c0012ax, str, Collections.emptyMap());
    }

    private static boolean a(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        if (!x509Certificate.getSubjectX500Principal().equals(x509Certificate2.getIssuerX500Principal())) {
            return false;
        }
        try {
            x509Certificate2.verify(x509Certificate.getPublicKey());
            return true;
        } catch (GeneralSecurityException e) {
            return false;
        }
    }

    public C0013ay a(C0012ax c0012ax, String str, Map<String, String> map) {
        C0013ay a;
        boolean z;
        switch (by.a[c0012ax.ordinal()]) {
            case 1:
                a = C0013ay.a((CharSequence) str, (Map) map, true);
                break;
            case 2:
                a = C0013ay.b((CharSequence) str, (Map) map, true);
                break;
            case 3:
                a = C0013ay.a((CharSequence) str);
                break;
            case 4:
                a = C0013ay.b((CharSequence) str);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method!");
        }
        if (str == null) {
            z = false;
        } else {
            z = str.toLowerCase().startsWith("https");
        }
        if (z && this.b != null) {
            SSLSocketFactory b = b();
            if (b != null) {
                ((HttpsURLConnection) a.a()).setSSLSocketFactory(b);
            }
        }
        return a;
    }

    private synchronized SSLSocketFactory b() {
        if (this.c == null && !this.d) {
            this.c = c();
        }
        return this.c;
    }

    private synchronized SSLSocketFactory c() {
        SSLSocketFactory socketFactory;
        this.d = true;
        try {
            aG aGVar = this.b;
            SSLContext instance = SSLContext.getInstance("TLS");
            bl blVar = new bl(new bm(aGVar.a(), aGVar.b()), aGVar);
            instance.init(null, new TrustManager[]{blVar}, null);
            socketFactory = instance.getSocketFactory();
            this.a.a(Crashlytics.TAG, "Custom SSL pinning enabled");
        } catch (Throwable e) {
            this.a.a(Crashlytics.TAG, "Exception while validating pinned certs", e);
            socketFactory = null;
        }
        return socketFactory;
    }
}
