package com.sprite.ads.internal.imageCache;

import android.content.Context;
import com.nostra13.universalimageloader.b.d;
import com.nostra13.universalimageloader.core.download.a;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class AuthImageDownloader extends a {
    final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    };
    private SSLSocketFactory mSSLSocketFactory = sslContextForTrustedCertificates().getSocketFactory();

    public AuthImageDownloader(Context context) {
        super(context);
    }

    public AuthImageDownloader(Context context, int i, int i2) {
        super(context, i, i2);
    }

    protected InputStream getStreamFromNetwork(String str, Object obj) {
        HttpURLConnection createConnection = createConnection(str, obj);
        int i = 0;
        while (createConnection.getResponseCode() / 100 == 3 && i < 5) {
            createConnection = createConnection(createConnection.getHeaderField("Location"), obj);
            i++;
        }
        if (createConnection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) createConnection).setSSLSocketFactory(this.mSSLSocketFactory);
            ((HttpsURLConnection) createConnection).setHostnameVerifier(this.DO_NOT_VERIFY);
        }
        try {
            return new com.nostra13.universalimageloader.core.assist.a(new BufferedInputStream(createConnection.getInputStream(), 32768), createConnection.getContentLength());
        } catch (IOException e) {
            d.a(createConnection.getErrorStream());
            throw e;
        }
    }

    public SSLContext sslContextForTrustedCertificates() {
        NoSuchAlgorithmException e;
        KeyManagementException e2;
        TrustManager[] trustManagerArr = new TrustManager[1];
        SSLContext mitm = new miTM();
        trustManagerArr[0] = mitm;
        try {
            mitm = SSLContext.getInstance("SSL");
            try {
                mitm.init(null, trustManagerArr, null);
                return mitm;
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
            } catch (KeyManagementException e4) {
                e2 = e4;
                e2.printStackTrace();
                return mitm;
            }
        } catch (NoSuchAlgorithmException e5) {
            NoSuchAlgorithmException noSuchAlgorithmException = e5;
            mitm = null;
            e = noSuchAlgorithmException;
            e.printStackTrace();
            return mitm;
        } catch (KeyManagementException e6) {
            KeyManagementException keyManagementException = e6;
            mitm = null;
            e2 = keyManagementException;
            e2.printStackTrace();
            return mitm;
        } catch (Throwable th) {
            return mitm;
        }
    }
}
