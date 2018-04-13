package org.apache.commons.httpclient.util;

import java.io.InterruptedIOException;
import java.lang.reflect.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExceptionUtil {
    private static final Method INIT_CAUSE_METHOD = getInitCauseMethod();
    private static final Log LOG;
    private static final Class SOCKET_TIMEOUT_CLASS = SocketTimeoutExceptionClass();
    static Class class$java$lang$Throwable;
    static Class class$org$apache$commons$httpclient$util$ExceptionUtil;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$util$ExceptionUtil == null) {
            class$ = class$("org.apache.commons.httpclient.util.ExceptionUtil");
            class$org$apache$commons$httpclient$util$ExceptionUtil = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$util$ExceptionUtil;
        }
        LOG = LogFactory.getLog(class$);
    }

    private static Method getInitCauseMethod() {
        try {
            Class class$;
            Class[] clsArr = new Class[1];
            if (class$java$lang$Throwable == null) {
                class$ = class$("java.lang.Throwable");
                class$java$lang$Throwable = class$;
            } else {
                class$ = class$java$lang$Throwable;
            }
            clsArr[0] = class$;
            if (class$java$lang$Throwable == null) {
                class$ = class$("java.lang.Throwable");
                class$java$lang$Throwable = class$;
            } else {
                class$ = class$java$lang$Throwable;
            }
            return class$.getMethod("initCause", clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private static Class SocketTimeoutExceptionClass() {
        try {
            return Class.forName("java.net.SocketTimeoutException");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static void initCause(Throwable th, Throwable th2) {
        if (INIT_CAUSE_METHOD != null) {
            try {
                INIT_CAUSE_METHOD.invoke(th, new Object[]{th2});
            } catch (Throwable e) {
                LOG.warn("Exception invoking Throwable.initCause", e);
            }
        }
    }

    public static boolean isSocketTimeoutException(InterruptedIOException interruptedIOException) {
        if (SOCKET_TIMEOUT_CLASS != null) {
            return SOCKET_TIMEOUT_CLASS.isInstance(interruptedIOException);
        }
        return true;
    }
}
