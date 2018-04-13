package cn.xiaochuankeji.tieba.network;

import android.net.SSLCertificateSocketFactory;
import android.os.Build.VERSION;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.d.j;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class f extends SSLSocketFactory {
    private static final Pattern a = Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
    private final HostnameVerifier b = HttpsURLConnection.getDefaultHostnameVerifier();

    public Socket createSocket() throws IOException {
        return null;
    }

    public Socket createSocket(String str, int i) throws IOException {
        return null;
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return null;
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return null;
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return null;
    }

    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    public String[] getSupportedCipherSuites() {
        return new String[0];
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        boolean a = j.a(str);
        CharSequence a2 = a(str);
        if (a && !TextUtils.isEmpty(a2)) {
            str = a2;
        }
        InetAddress inetAddress = socket.getInetAddress();
        if (z) {
            socket.close();
        }
        SSLCertificateSocketFactory sSLCertificateSocketFactory = (SSLCertificateSocketFactory) SSLCertificateSocketFactory.getDefault(0);
        SSLSocket sSLSocket = (SSLSocket) sSLCertificateSocketFactory.createSocket(inetAddress, i);
        sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
        if (VERSION.SDK_INT >= 17) {
            sSLCertificateSocketFactory.setHostname(sSLSocket, str);
        } else {
            try {
                sSLSocket.getClass().getMethod("setHostname", new Class[]{String.class}).invoke(sSLSocket, new Object[]{str});
            } catch (Exception e) {
            }
        }
        if (this.b.verify(str, sSLSocket.getSession())) {
            return sSLSocket;
        }
        sSLSocket.close();
        throw new SSLPeerUnverifiedException("Cannot verify hostname: " + str);
    }

    public static X509TrustManager a() throws GeneralSecurityException {
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init((KeyStore) null);
        TrustManager[] trustManagers = instance.getTrustManagers();
        if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
            return (X509TrustManager) trustManagers[0];
        }
        throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
    }

    private String a(String str) {
        ConcurrentHashMap e = e.a().e();
        if (e != null) {
            for (Entry entry : e.entrySet()) {
                if (str.equalsIgnoreCase((String) entry.getValue())) {
                    return (String) entry.getKey();
                }
            }
        }
        HashMap b = b.a().b();
        if (b != null) {
            for (Entry entry2 : b.entrySet()) {
                if (str.equalsIgnoreCase((String) entry2.getValue())) {
                    return (String) entry2.getKey();
                }
            }
        }
        return null;
    }
}
