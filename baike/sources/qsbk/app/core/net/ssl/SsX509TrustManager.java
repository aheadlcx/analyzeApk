package qsbk.app.core.net.ssl;

import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class SsX509TrustManager implements X509TrustManager {
    private ArrayList<X509TrustManager> a = new ArrayList();

    protected SsX509TrustManager(InputStream inputStream, String str) throws GeneralSecurityException {
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init((KeyStore) null);
        for (TrustManager trustManager : instance.getTrustManagers()) {
            if (trustManager instanceof X509TrustManager) {
                this.a.add((X509TrustManager) trustManager);
            }
        }
        X509TrustManager a = a(inputStream, str);
        if (a != null) {
            this.a.add(a);
            return;
        }
        throw new IllegalArgumentException("Keystore is valid but cannot find TrustManagerFactory of type X509TrustManager.");
    }

    private X509TrustManager a(InputStream inputStream, String str) throws GeneralSecurityException {
        TrustManager[] trustManagers = b(inputStream, str).getTrustManagers();
        X509TrustManager x509TrustManager = null;
        for (int i = 0; i < trustManagers.length; i++) {
            if (trustManagers[i] instanceof X509TrustManager) {
                x509TrustManager = (X509TrustManager) trustManagers[i];
            }
        }
        return x509TrustManager;
    }

    private TrustManagerFactory b(InputStream inputStream, String str) throws GeneralSecurityException {
        KeyStore instance = KeyStore.getInstance("BKS");
        try {
            instance.load(inputStream, str.toCharArray());
            TrustManagerFactory instance2 = TrustManagerFactory.getInstance("X509");
            instance2.init(instance);
            return instance2;
        } catch (Throwable e) {
            throw new GeneralSecurityException("Problem reading keystore stream", e);
        }
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        Object obj;
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            try {
                ((X509TrustManager) it.next()).checkServerTrusted(x509CertificateArr, str);
                obj = 1;
                break;
            } catch (CertificateException e) {
            }
        }
        obj = null;
        if (obj == null) {
            throw new CertificateException();
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            arrayList.addAll(Arrays.asList(((X509TrustManager) it.next()).getAcceptedIssuers()));
        }
        return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
    }
}
