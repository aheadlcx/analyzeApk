package cz.msebera.android.httpclient.conn.ssl;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.X509KeyManager;

class SSLContextBuilder$a implements X509KeyManager {
    private final X509KeyManager a;
    private final PrivateKeyStrategy b;

    SSLContextBuilder$a(X509KeyManager x509KeyManager, PrivateKeyStrategy privateKeyStrategy) {
        this.a = x509KeyManager;
        this.b = privateKeyStrategy;
    }

    public String[] getClientAliases(String str, Principal[] principalArr) {
        return this.a.getClientAliases(str, principalArr);
    }

    public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
        Map hashMap = new HashMap();
        for (String str : strArr) {
            String[] clientAliases = this.a.getClientAliases(str, principalArr);
            if (clientAliases != null) {
                for (String str2 : clientAliases) {
                    hashMap.put(str2, new PrivateKeyDetails(str, this.a.getCertificateChain(str2)));
                }
            }
        }
        return this.b.chooseAlias(hashMap, socket);
    }

    public String[] getServerAliases(String str, Principal[] principalArr) {
        return this.a.getServerAliases(str, principalArr);
    }

    public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
        Map hashMap = new HashMap();
        String[] serverAliases = this.a.getServerAliases(str, principalArr);
        if (serverAliases != null) {
            for (String str2 : serverAliases) {
                hashMap.put(str2, new PrivateKeyDetails(str, this.a.getCertificateChain(str2)));
            }
        }
        return this.b.chooseAlias(hashMap, socket);
    }

    public X509Certificate[] getCertificateChain(String str) {
        return this.a.getCertificateChain(str);
    }

    public PrivateKey getPrivateKey(String str) {
        return this.a.getPrivateKey(str);
    }
}
