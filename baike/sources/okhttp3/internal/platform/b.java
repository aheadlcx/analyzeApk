package okhttp3.internal.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.Util;

final class b extends Platform {
    final Method a;
    final Method b;

    b(Method method, Method method2) {
        this.a = method;
        this.b = method2;
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
        Exception e;
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List alpnProtocolNames = Platform.alpnProtocolNames(list);
            this.a.invoke(sSLParameters, new Object[]{alpnProtocolNames.toArray(new String[alpnProtocolNames.size()])});
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException e2) {
            e = e2;
            throw Util.assertionError("unable to set ssl parameters", e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw Util.assertionError("unable to set ssl parameters", e);
        }
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        Exception e;
        try {
            String str = (String) this.b.invoke(sSLSocket, new Object[0]);
            if (str == null || str.equals("")) {
                return null;
            }
            return str;
        } catch (IllegalAccessException e2) {
            e = e2;
            throw Util.assertionError("unable to get selected protocols", e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw Util.assertionError("unable to get selected protocols", e);
        }
    }

    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
    }

    public static b buildIfSupported() {
        try {
            return new b(SSLParameters.class.getMethod("setApplicationProtocols", new Class[]{String[].class}), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
