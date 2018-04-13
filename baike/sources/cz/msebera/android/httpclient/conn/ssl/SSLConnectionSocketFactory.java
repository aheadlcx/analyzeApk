package cz.msebera.android.httpclient.conn.ssl;

import android.os.Build.VERSION;
import android.util.Log;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.conn.socket.LayeredConnectionSocketFactory;
import cz.msebera.android.httpclient.conn.util.PublicSuffixMatcherLoader;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.ssl.SSLContexts;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.TextUtils;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

@ThreadSafe
public class SSLConnectionSocketFactory implements LayeredConnectionSocketFactory {
    @Deprecated
    public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = AllowAllHostnameVerifier.INSTANCE;
    @Deprecated
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = BrowserCompatHostnameVerifier.INSTANCE;
    public static final String SSL = "SSL";
    public static final String SSLV2 = "SSLv2";
    @Deprecated
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = StrictHostnameVerifier.INSTANCE;
    public static final String TAG = "SSLConnSockFact";
    public static final String TLS = "TLS";
    private final SSLSocketFactory a;
    private final HostnameVerifier b;
    private final String[] c;
    private final String[] d;
    public HttpClientAndroidLog log;

    public static HostnameVerifier getDefaultHostnameVerifier() {
        return new DefaultHostnameVerifier(PublicSuffixMatcherLoader.getDefault());
    }

    public static SSLConnectionSocketFactory getSocketFactory() throws SSLInitializationException {
        return new SSLConnectionSocketFactory(SSLContexts.createDefault(), getDefaultHostnameVerifier());
    }

    private static String[] a(String str) {
        if (TextUtils.isBlank(str)) {
            return null;
        }
        return str.split(" *, *");
    }

    public static SSLConnectionSocketFactory getSystemSocketFactory() throws SSLInitializationException {
        return new SSLConnectionSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault(), a(System.getProperty("https.protocols")), a(System.getProperty("https.cipherSuites")), getDefaultHostnameVerifier());
    }

    public SSLConnectionSocketFactory(SSLContext sSLContext) {
        this(sSLContext, getDefaultHostnameVerifier());
    }

    @Deprecated
    public SSLConnectionSocketFactory(SSLContext sSLContext, X509HostnameVerifier x509HostnameVerifier) {
        this(((SSLContext) Args.notNull(sSLContext, "SSL context")).getSocketFactory(), null, null, x509HostnameVerifier);
    }

    @Deprecated
    public SSLConnectionSocketFactory(SSLContext sSLContext, String[] strArr, String[] strArr2, X509HostnameVerifier x509HostnameVerifier) {
        this(((SSLContext) Args.notNull(sSLContext, "SSL context")).getSocketFactory(), strArr, strArr2, x509HostnameVerifier);
    }

    @Deprecated
    public SSLConnectionSocketFactory(SSLSocketFactory sSLSocketFactory, X509HostnameVerifier x509HostnameVerifier) {
        this(sSLSocketFactory, null, null, x509HostnameVerifier);
    }

    @Deprecated
    public SSLConnectionSocketFactory(SSLSocketFactory sSLSocketFactory, String[] strArr, String[] strArr2, X509HostnameVerifier x509HostnameVerifier) {
        this(sSLSocketFactory, strArr, strArr2, (HostnameVerifier) x509HostnameVerifier);
    }

    public SSLConnectionSocketFactory(SSLContext sSLContext, HostnameVerifier hostnameVerifier) {
        this(((SSLContext) Args.notNull(sSLContext, "SSL context")).getSocketFactory(), null, null, hostnameVerifier);
    }

    public SSLConnectionSocketFactory(SSLContext sSLContext, String[] strArr, String[] strArr2, HostnameVerifier hostnameVerifier) {
        this(((SSLContext) Args.notNull(sSLContext, "SSL context")).getSocketFactory(), strArr, strArr2, hostnameVerifier);
    }

    public SSLConnectionSocketFactory(SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier) {
        this(sSLSocketFactory, null, null, hostnameVerifier);
    }

    public SSLConnectionSocketFactory(SSLSocketFactory sSLSocketFactory, String[] strArr, String[] strArr2, HostnameVerifier hostnameVerifier) {
        this.log = new HttpClientAndroidLog(getClass());
        this.a = (SSLSocketFactory) Args.notNull(sSLSocketFactory, "SSL socket factory");
        this.c = strArr;
        this.d = strArr2;
        if (hostnameVerifier == null) {
            hostnameVerifier = getDefaultHostnameVerifier();
        }
        this.b = hostnameVerifier;
    }

    protected void a(SSLSocket sSLSocket) throws IOException {
    }

    public Socket createSocket(HttpContext httpContext) throws IOException {
        return SocketFactory.getDefault().createSocket();
    }

    public Socket connectSocket(int i, Socket socket, HttpHost httpHost, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpContext httpContext) throws IOException {
        Args.notNull(httpHost, "HTTP host");
        Args.notNull(inetSocketAddress, "Remote address");
        Socket createSocket = socket != null ? socket : createSocket(httpContext);
        if (inetSocketAddress2 != null) {
            createSocket.bind(inetSocketAddress2);
        }
        if (i > 0) {
            try {
                if (createSocket.getSoTimeout() == 0) {
                    createSocket.setSoTimeout(i);
                }
            } catch (IOException e) {
                try {
                    createSocket.close();
                } catch (IOException e2) {
                }
                throw e;
            }
        }
        if (this.log.isDebugEnabled()) {
            this.log.debug("Connecting socket to " + inetSocketAddress + " with timeout " + i);
        }
        createSocket.connect(inetSocketAddress, i);
        if (!(createSocket instanceof SSLSocket)) {
            return createLayeredSocket(createSocket, httpHost.getHostName(), inetSocketAddress.getPort(), httpContext);
        }
        SSLSocket sSLSocket = (SSLSocket) createSocket;
        this.log.debug("Starting handshake");
        sSLSocket.startHandshake();
        a(sSLSocket, httpHost.getHostName());
        return createSocket;
    }

    public Socket createLayeredSocket(Socket socket, String str, int i, HttpContext httpContext) throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.a.createSocket(socket, str, i, true);
        if (this.c != null) {
            sSLSocket.setEnabledProtocols(this.c);
        } else {
            String[] enabledProtocols = sSLSocket.getEnabledProtocols();
            List arrayList = new ArrayList(enabledProtocols.length);
            for (String str2 : enabledProtocols) {
                if (!str2.startsWith("SSL")) {
                    arrayList.add(str2);
                }
            }
            if (!arrayList.isEmpty()) {
                sSLSocket.setEnabledProtocols((String[]) arrayList.toArray(new String[arrayList.size()]));
            }
        }
        if (this.d != null) {
            sSLSocket.setEnabledCipherSuites(this.d);
        }
        if (this.log.isDebugEnabled()) {
            this.log.debug("Enabled protocols: " + Arrays.asList(sSLSocket.getEnabledProtocols()));
            this.log.debug("Enabled cipher suites:" + Arrays.asList(sSLSocket.getEnabledCipherSuites()));
        }
        a(sSLSocket);
        this.log.debug("Starting handshake");
        if (VERSION.SDK_INT >= 17) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Enabling SNI for " + str);
            }
            try {
                sSLSocket.getClass().getMethod("setHostname", new Class[]{String.class}).invoke(sSLSocket, new Object[]{str});
            } catch (Throwable e) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "SNI configuration failed", e);
                }
            }
        }
        sSLSocket.startHandshake();
        a(sSLSocket, str);
        return sSLSocket;
    }

    private void a(SSLSocket sSLSocket, String str) throws IOException {
        try {
            SSLSession session;
            X509Certificate x509Certificate;
            Collection<List> subjectAlternativeNames;
            List arrayList;
            List arrayList2;
            Collection<List> issuerAlternativeNames;
            SSLSession session2 = sSLSocket.getSession();
            if (session2 == null) {
                sSLSocket.getInputStream().available();
                session2 = sSLSocket.getSession();
                if (session2 == null) {
                    sSLSocket.startHandshake();
                    session = sSLSocket.getSession();
                    if (session != null) {
                        throw new SSLHandshakeException("SSL session not available");
                    }
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Secure session established");
                        this.log.debug(" negotiated protocol: " + session.getProtocol());
                        this.log.debug(" negotiated cipher suite: " + session.getCipherSuite());
                        try {
                            x509Certificate = (X509Certificate) session.getPeerCertificates()[0];
                            this.log.debug(" peer principal: " + x509Certificate.getSubjectX500Principal().toString());
                            subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
                            if (subjectAlternativeNames != null) {
                                arrayList = new ArrayList();
                                for (List arrayList22 : subjectAlternativeNames) {
                                    if (!arrayList22.isEmpty()) {
                                        arrayList.add((String) arrayList22.get(1));
                                    }
                                }
                                this.log.debug(" peer alternative names: " + arrayList);
                            }
                            this.log.debug(" issuer principal: " + x509Certificate.getIssuerX500Principal().toString());
                            issuerAlternativeNames = x509Certificate.getIssuerAlternativeNames();
                            if (issuerAlternativeNames != null) {
                                arrayList22 = new ArrayList();
                                for (List list : issuerAlternativeNames) {
                                    if (!list.isEmpty()) {
                                        arrayList22.add((String) list.get(1));
                                    }
                                }
                                this.log.debug(" issuer alternative names: " + arrayList22);
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (!this.b.verify(str, session)) {
                        throw new SSLPeerUnverifiedException("Host name '" + str + "' does not match " + "the certificate subject provided by the peer (" + ((X509Certificate) session.getPeerCertificates()[0]).getSubjectX500Principal().toString() + ")");
                    }
                    return;
                }
            }
            session = session2;
            if (session != null) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Secure session established");
                    this.log.debug(" negotiated protocol: " + session.getProtocol());
                    this.log.debug(" negotiated cipher suite: " + session.getCipherSuite());
                    x509Certificate = (X509Certificate) session.getPeerCertificates()[0];
                    this.log.debug(" peer principal: " + x509Certificate.getSubjectX500Principal().toString());
                    subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
                    if (subjectAlternativeNames != null) {
                        arrayList = new ArrayList();
                        for (List arrayList222 : subjectAlternativeNames) {
                            if (!arrayList222.isEmpty()) {
                                arrayList.add((String) arrayList222.get(1));
                            }
                        }
                        this.log.debug(" peer alternative names: " + arrayList);
                    }
                    this.log.debug(" issuer principal: " + x509Certificate.getIssuerX500Principal().toString());
                    issuerAlternativeNames = x509Certificate.getIssuerAlternativeNames();
                    if (issuerAlternativeNames != null) {
                        arrayList222 = new ArrayList();
                        for (List list2 : issuerAlternativeNames) {
                            if (!list2.isEmpty()) {
                                arrayList222.add((String) list2.get(1));
                            }
                        }
                        this.log.debug(" issuer alternative names: " + arrayList222);
                    }
                }
                if (!this.b.verify(str, session)) {
                    throw new SSLPeerUnverifiedException("Host name '" + str + "' does not match " + "the certificate subject provided by the peer (" + ((X509Certificate) session.getPeerCertificates()[0]).getSubjectX500Principal().toString() + ")");
                }
                return;
            }
            throw new SSLHandshakeException("SSL session not available");
        } catch (IOException e2) {
            try {
                sSLSocket.close();
            } catch (Exception e3) {
            }
            throw e2;
        }
    }
}
