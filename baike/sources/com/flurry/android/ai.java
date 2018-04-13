package com.flurry.android;

import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

final class ai extends SSLSocketFactory {
    private SSLContext a = SSLContext.getInstance("TLS");

    public ai(FlurryAgent flurryAgent, KeyStore keyStore) {
        super(keyStore);
        n nVar = new n();
        this.a.init(null, new TrustManager[]{nVar}, null);
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return this.a.getSocketFactory().createSocket(socket, str, i, z);
    }

    public final Socket createSocket() {
        return this.a.getSocketFactory().createSocket();
    }
}
