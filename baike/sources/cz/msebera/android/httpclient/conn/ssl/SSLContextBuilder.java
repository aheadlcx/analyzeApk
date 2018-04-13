package cz.msebera.android.httpclient.conn.ssl;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;

@NotThreadSafe
@Deprecated
public class SSLContextBuilder {
    private String a;
    private Set<KeyManager> b = new LinkedHashSet();
    private Set<TrustManager> c = new LinkedHashSet();
    private SecureRandom d;

    public SSLContextBuilder useTLS() {
        this.a = "TLS";
        return this;
    }

    public SSLContextBuilder useSSL() {
        this.a = "SSL";
        return this;
    }

    public SSLContextBuilder useProtocol(String str) {
        this.a = str;
        return this;
    }

    public SSLContextBuilder setSecureRandom(SecureRandom secureRandom) {
        this.d = secureRandom;
        return this;
    }

    public SSLContextBuilder loadTrustMaterial(KeyStore keyStore, TrustStrategy trustStrategy) throws NoSuchAlgorithmException, KeyStoreException {
        int i = 0;
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(keyStore);
        TrustManager[] trustManagers = instance.getTrustManagers();
        if (trustManagers != null) {
            if (trustStrategy != null) {
                for (int i2 = 0; i2 < trustManagers.length; i2++) {
                    TrustManager trustManager = trustManagers[i2];
                    if (trustManager instanceof X509TrustManager) {
                        trustManagers[i2] = new SSLContextBuilder$b((X509TrustManager) trustManager, trustStrategy);
                    }
                }
            }
            int length = trustManagers.length;
            while (i < length) {
                this.c.add(trustManagers[i]);
                i++;
            }
        }
        return this;
    }

    public SSLContextBuilder loadTrustMaterial(KeyStore keyStore) throws NoSuchAlgorithmException, KeyStoreException {
        return loadTrustMaterial(keyStore, null);
    }

    public SSLContextBuilder loadKeyMaterial(KeyStore keyStore, char[] cArr) throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException {
        loadKeyMaterial(keyStore, cArr, null);
        return this;
    }

    public SSLContextBuilder loadKeyMaterial(KeyStore keyStore, char[] cArr, PrivateKeyStrategy privateKeyStrategy) throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException {
        int i = 0;
        KeyManagerFactory instance = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        instance.init(keyStore, cArr);
        KeyManager[] keyManagers = instance.getKeyManagers();
        if (keyManagers != null) {
            if (privateKeyStrategy != null) {
                for (int i2 = 0; i2 < keyManagers.length; i2++) {
                    KeyManager keyManager = keyManagers[i2];
                    if (keyManager instanceof X509KeyManager) {
                        keyManagers[i2] = new SSLContextBuilder$a((X509KeyManager) keyManager, privateKeyStrategy);
                    }
                }
            }
            int length = keyManagers.length;
            while (i < length) {
                this.b.add(keyManagers[i]);
                i++;
            }
        }
        return this;
    }

    public SSLContext build() throws NoSuchAlgorithmException, KeyManagementException {
        KeyManager[] keyManagerArr;
        TrustManager[] trustManagerArr;
        SSLContext instance = SSLContext.getInstance(this.a != null ? this.a : "TLS");
        if (this.b.isEmpty()) {
            keyManagerArr = null;
        } else {
            keyManagerArr = (KeyManager[]) this.b.toArray(new KeyManager[this.b.size()]);
        }
        if (this.c.isEmpty()) {
            trustManagerArr = null;
        } else {
            trustManagerArr = (TrustManager[]) this.c.toArray(new TrustManager[this.c.size()]);
        }
        instance.init(keyManagerArr, trustManagerArr, this.d);
        return instance;
    }
}
