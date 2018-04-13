package cz.msebera.android.httpclient.ssl;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509TrustManager;

@NotThreadSafe
public class SSLContextBuilder {
    private String a;
    private final Set<KeyManager> b = new LinkedHashSet();
    private final Set<TrustManager> c = new LinkedHashSet();
    private SecureRandom d;

    static class a extends X509ExtendedKeyManager {
        private final X509ExtendedKeyManager a;
        private final PrivateKeyStrategy b;

        a(X509ExtendedKeyManager x509ExtendedKeyManager, PrivateKeyStrategy privateKeyStrategy) {
            this.a = x509ExtendedKeyManager;
            this.b = privateKeyStrategy;
        }

        public String[] getClientAliases(String str, Principal[] principalArr) {
            return this.a.getClientAliases(str, principalArr);
        }

        public Map<String, PrivateKeyDetails> getClientAliasMap(String[] strArr, Principal[] principalArr) {
            Map<String, PrivateKeyDetails> hashMap = new HashMap();
            for (String str : strArr) {
                String[] clientAliases = this.a.getClientAliases(str, principalArr);
                if (clientAliases != null) {
                    for (String str2 : clientAliases) {
                        hashMap.put(str2, new PrivateKeyDetails(str, this.a.getCertificateChain(str2)));
                    }
                }
            }
            return hashMap;
        }

        public Map<String, PrivateKeyDetails> getServerAliasMap(String str, Principal[] principalArr) {
            Map<String, PrivateKeyDetails> hashMap = new HashMap();
            String[] serverAliases = this.a.getServerAliases(str, principalArr);
            if (serverAliases != null) {
                for (String str2 : serverAliases) {
                    hashMap.put(str2, new PrivateKeyDetails(str, this.a.getCertificateChain(str2)));
                }
            }
            return hashMap;
        }

        public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
            return this.b.chooseAlias(getClientAliasMap(strArr, principalArr), socket);
        }

        public String[] getServerAliases(String str, Principal[] principalArr) {
            return this.a.getServerAliases(str, principalArr);
        }

        public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
            return this.b.chooseAlias(getServerAliasMap(str, principalArr), socket);
        }

        public X509Certificate[] getCertificateChain(String str) {
            return this.a.getCertificateChain(str);
        }

        public PrivateKey getPrivateKey(String str) {
            return this.a.getPrivateKey(str);
        }

        public String chooseEngineClientAlias(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
            return this.b.chooseAlias(getClientAliasMap(strArr, principalArr), null);
        }

        public String chooseEngineServerAlias(String str, Principal[] principalArr, SSLEngine sSLEngine) {
            return this.b.chooseAlias(getServerAliasMap(str, principalArr), null);
        }
    }

    static class b implements X509TrustManager {
        private final X509TrustManager a;
        private final TrustStrategy b;

        b(X509TrustManager x509TrustManager, TrustStrategy trustStrategy) {
            this.a = x509TrustManager;
            this.b = trustStrategy;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            this.a.checkClientTrusted(x509CertificateArr, str);
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            if (!this.b.isTrusted(x509CertificateArr, str)) {
                this.a.checkServerTrusted(x509CertificateArr, str);
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return this.a.getAcceptedIssuers();
        }
    }

    public static SSLContextBuilder create() {
        return new SSLContextBuilder();
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
                        trustManagers[i2] = new b((X509TrustManager) trustManager, trustStrategy);
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

    public SSLContextBuilder loadTrustMaterial(TrustStrategy trustStrategy) throws NoSuchAlgorithmException, KeyStoreException {
        return loadTrustMaterial(null, trustStrategy);
    }

    public SSLContextBuilder loadTrustMaterial(File file, char[] cArr, TrustStrategy trustStrategy) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        Args.notNull(file, "Truststore file");
        KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream fileInputStream = new FileInputStream(file);
        try {
            instance.load(fileInputStream, cArr);
            return loadTrustMaterial(instance, trustStrategy);
        } finally {
            fileInputStream.close();
        }
    }

    public SSLContextBuilder loadTrustMaterial(File file, char[] cArr) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        return loadTrustMaterial(file, cArr, null);
    }

    public SSLContextBuilder loadTrustMaterial(File file) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        return loadTrustMaterial(file, null);
    }

    public SSLContextBuilder loadTrustMaterial(URL url, char[] cArr, TrustStrategy trustStrategy) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        Args.notNull(url, "Truststore URL");
        KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream openStream = url.openStream();
        try {
            instance.load(openStream, cArr);
            return loadTrustMaterial(instance, trustStrategy);
        } finally {
            openStream.close();
        }
    }

    public SSLContextBuilder loadTrustMaterial(URL url, char[] cArr) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        return loadTrustMaterial(url, cArr, null);
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
                    if (keyManager instanceof X509ExtendedKeyManager) {
                        keyManagers[i2] = new a((X509ExtendedKeyManager) keyManager, privateKeyStrategy);
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

    public SSLContextBuilder loadKeyMaterial(KeyStore keyStore, char[] cArr) throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException {
        return loadKeyMaterial(keyStore, cArr, null);
    }

    public SSLContextBuilder loadKeyMaterial(File file, char[] cArr, char[] cArr2, PrivateKeyStrategy privateKeyStrategy) throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException {
        Args.notNull(file, "Keystore file");
        KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream fileInputStream = new FileInputStream(file);
        try {
            instance.load(fileInputStream, cArr);
            return loadKeyMaterial(instance, cArr2, privateKeyStrategy);
        } finally {
            fileInputStream.close();
        }
    }

    public SSLContextBuilder loadKeyMaterial(File file, char[] cArr, char[] cArr2) throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException {
        return loadKeyMaterial(file, cArr, cArr2, null);
    }

    public SSLContextBuilder loadKeyMaterial(URL url, char[] cArr, char[] cArr2, PrivateKeyStrategy privateKeyStrategy) throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException {
        Args.notNull(url, "Keystore URL");
        KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream openStream = url.openStream();
        try {
            instance.load(openStream, cArr);
            return loadKeyMaterial(instance, cArr2, privateKeyStrategy);
        } finally {
            openStream.close();
        }
    }

    public SSLContextBuilder loadKeyMaterial(URL url, char[] cArr, char[] cArr2) throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException {
        return loadKeyMaterial(url, cArr, cArr2, null);
    }

    protected void a(SSLContext sSLContext, Collection<KeyManager> collection, Collection<TrustManager> collection2, SecureRandom secureRandom) throws KeyManagementException {
        KeyManager[] keyManagerArr;
        TrustManager[] trustManagerArr;
        if (collection.isEmpty()) {
            keyManagerArr = null;
        } else {
            keyManagerArr = (KeyManager[]) collection.toArray(new KeyManager[collection.size()]);
        }
        if (collection2.isEmpty()) {
            trustManagerArr = null;
        } else {
            trustManagerArr = (TrustManager[]) collection2.toArray(new TrustManager[collection2.size()]);
        }
        sSLContext.init(keyManagerArr, trustManagerArr, secureRandom);
    }

    public SSLContext build() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext instance = SSLContext.getInstance(this.a != null ? this.a : "TLS");
        a(instance, this.b, this.c, this.d);
        return instance;
    }
}
