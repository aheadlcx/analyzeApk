package com.budejie.www.e;

import android.content.Context;
import com.nostra13.universalimageloader.b.d;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class a extends com.nostra13.universalimageloader.core.download.a {
    final HostnameVerifier a = new a$1(this);
    private SSLSocketFactory b = a().getSocketFactory();

    public a(Context context) {
        super(context);
    }

    protected InputStream getStreamFromNetwork(String str, Object obj) throws IOException {
        HttpURLConnection createConnection = createConnection(str, obj);
        int i = 0;
        while (createConnection.getResponseCode() / 100 == 3 && i < 5) {
            createConnection = createConnection(createConnection.getHeaderField("Location"), obj);
            i++;
        }
        if (createConnection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) createConnection).setSSLSocketFactory(this.b);
            ((HttpsURLConnection) createConnection).setHostnameVerifier(this.a);
        }
        try {
            return new com.nostra13.universalimageloader.core.assist.a(new BufferedInputStream(createConnection.getInputStream(), 32768), createConnection.getContentLength());
        } catch (IOException e) {
            d.a(createConnection.getErrorStream());
            throw e;
        }
    }

    public SSLContext a() {
        NoSuchAlgorithmException e;
        KeyManagementException e2;
        TrustManager[] trustManagerArr = new TrustManager[1];
        SSLContext gVar = new g();
        trustManagerArr[0] = gVar;
        try {
            gVar = SSLContext.getInstance("SSL");
            try {
                gVar.init(null, trustManagerArr, null);
                return gVar;
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
            } catch (KeyManagementException e4) {
                e2 = e4;
                e2.printStackTrace();
                return gVar;
            }
        } catch (NoSuchAlgorithmException e5) {
            NoSuchAlgorithmException noSuchAlgorithmException = e5;
            gVar = null;
            e = noSuchAlgorithmException;
            e.printStackTrace();
            return gVar;
        } catch (KeyManagementException e6) {
            KeyManagementException keyManagementException = e6;
            gVar = null;
            e2 = keyManagementException;
            e2.printStackTrace();
            return gVar;
        } catch (Throwable th) {
            return gVar;
        }
    }
}
