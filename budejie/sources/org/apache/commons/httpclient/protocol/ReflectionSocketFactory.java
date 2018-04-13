package org.apache.commons.httpclient.protocol;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.commons.httpclient.ConnectTimeoutException;

public final class ReflectionSocketFactory {
    private static Constructor INETSOCKETADDRESS_CONSTRUCTOR = null;
    private static boolean REFLECTION_FAILED = false;
    private static Method SOCKETBIND_METHOD = null;
    private static Method SOCKETCONNECT_METHOD = null;
    private static Class SOCKETTIMEOUTEXCEPTION_CLASS = null;
    static Class class$java$net$InetAddress;
    static Class class$java$net$Socket;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private ReflectionSocketFactory() {
    }

    public static Socket createSocket(String str, String str2, int i, InetAddress inetAddress, int i2, int i3) throws IOException, UnknownHostException, ConnectTimeoutException {
        if (REFLECTION_FAILED) {
            return null;
        }
        try {
            Class class$;
            Class cls = Class.forName(str);
            Socket socket = (Socket) cls.getMethod("createSocket", new Class[0]).invoke(cls.getMethod("getDefault", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            if (INETSOCKETADDRESS_CONSTRUCTOR == null) {
                Class cls2 = Class.forName("java.net.InetSocketAddress");
                Class[] clsArr = new Class[2];
                if (class$java$net$InetAddress == null) {
                    class$ = class$("java.net.InetAddress");
                    class$java$net$InetAddress = class$;
                } else {
                    class$ = class$java$net$InetAddress;
                }
                clsArr[0] = class$;
                clsArr[1] = Integer.TYPE;
                INETSOCKETADDRESS_CONSTRUCTOR = cls2.getConstructor(clsArr);
            }
            Object newInstance = INETSOCKETADDRESS_CONSTRUCTOR.newInstance(new Object[]{InetAddress.getByName(str2), new Integer(i)});
            Object newInstance2 = INETSOCKETADDRESS_CONSTRUCTOR.newInstance(new Object[]{inetAddress, new Integer(i2)});
            if (SOCKETCONNECT_METHOD == null) {
                if (class$java$net$Socket == null) {
                    class$ = class$("java.net.Socket");
                    class$java$net$Socket = class$;
                } else {
                    class$ = class$java$net$Socket;
                }
                SOCKETCONNECT_METHOD = class$.getMethod("connect", new Class[]{Class.forName("java.net.SocketAddress"), Integer.TYPE});
            }
            if (SOCKETBIND_METHOD == null) {
                if (class$java$net$Socket == null) {
                    class$ = class$("java.net.Socket");
                    class$java$net$Socket = class$;
                } else {
                    class$ = class$java$net$Socket;
                }
                SOCKETBIND_METHOD = class$.getMethod("bind", new Class[]{Class.forName("java.net.SocketAddress")});
            }
            SOCKETBIND_METHOD.invoke(socket, new Object[]{newInstance2});
            SOCKETCONNECT_METHOD.invoke(socket, new Object[]{newInstance, new Integer(i3)});
            return socket;
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (SOCKETTIMEOUTEXCEPTION_CLASS == null) {
                try {
                    SOCKETTIMEOUTEXCEPTION_CLASS = Class.forName("java.net.SocketTimeoutException");
                } catch (ClassNotFoundException e2) {
                    REFLECTION_FAILED = true;
                    return null;
                }
            }
            if (SOCKETTIMEOUTEXCEPTION_CLASS.isInstance(targetException)) {
                throw new ConnectTimeoutException(new StringBuffer().append("The host did not accept the connection within timeout of ").append(i3).append(" ms").toString(), targetException);
            } else if (!(targetException instanceof IOException)) {
                return null;
            } else {
                throw ((IOException) targetException);
            }
        } catch (Exception e3) {
            REFLECTION_FAILED = true;
            return null;
        }
    }
}
