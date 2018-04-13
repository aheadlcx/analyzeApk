package org.apache.commons.httpclient.protocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.SSLSocketFactory;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;

public class SSLProtocolSocketFactory implements SecureProtocolSocketFactory {
    static Class class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory;
    private static final SSLProtocolSocketFactory factory = new SSLProtocolSocketFactory();

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static SSLProtocolSocketFactory getSocketFactory() {
        return factory;
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        return SSLSocketFactory.getDefault().createSocket(str, i, inetAddress, i2);
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2, HttpConnectionParams httpConnectionParams) throws IOException, UnknownHostException, ConnectTimeoutException {
        if (httpConnectionParams == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        int connectionTimeout = httpConnectionParams.getConnectionTimeout();
        if (connectionTimeout == 0) {
            return createSocket(str, i, inetAddress, i2);
        }
        Socket createSocket = ReflectionSocketFactory.createSocket("javax.net.ssl.SSLSocketFactory", str, i, inetAddress, i2, connectionTimeout);
        if (createSocket == null) {
            return ControllerThreadSocketFactory.createSocket(this, str, i, inetAddress, i2, connectionTimeout);
        }
        return createSocket;
    }

    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        return SSLSocketFactory.getDefault().createSocket(str, i);
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
        return ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(socket, str, i, z);
    }

    public boolean equals(Object obj) {
        if (obj != null) {
            Object class$;
            Class cls = obj.getClass();
            if (class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory == null) {
                class$ = class$("org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory");
                class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory = class$;
            } else {
                class$ = class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory;
            }
            if (cls.equals(class$)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        Object class$;
        if (class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory == null) {
            class$ = class$("org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory");
            class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory;
        }
        return class$.hashCode();
    }
}
