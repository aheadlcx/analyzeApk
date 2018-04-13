package org.eclipse.paho.client.mqttv3.logging;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LoggerFactory {
    public static final String MQTT_CLIENT_MSG_CAT = "org.eclipse.paho.client.mqttv3.internal.nls.logcat";
    static Class a;
    static Class b;
    static Class c;
    private static final String d;
    private static String e = null;
    private static String f;

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.logging.LoggerFactory");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        d = cls.getName();
        cls = b;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.logging.JSR47Logger");
                b = cls;
            } catch (Throwable e2) {
                throw new NoClassDefFoundError(e2.getMessage());
            }
        }
        f = cls.getName();
    }

    public static Logger getLogger(String str, String str2) {
        String str3 = e;
        if (str3 == null) {
            str3 = f;
        }
        Logger a = a(str3, ResourceBundle.getBundle(str), str2, null);
        if (a != null) {
            return a;
        }
        throw new MissingResourceException("Error locating the logging class", d, str2);
    }

    private static Logger a(String str, ResourceBundle resourceBundle, String str2, String str3) {
        try {
            Logger logger;
            Class cls = Class.forName(str);
            if (cls != null) {
                try {
                    logger = (Logger) cls.newInstance();
                    logger.initialise(resourceBundle, str2, str3);
                } catch (IllegalAccessException e) {
                    return null;
                } catch (InstantiationException e2) {
                    return null;
                } catch (ExceptionInInitializerError e3) {
                    return null;
                } catch (SecurityException e4) {
                    return null;
                }
            }
            logger = null;
            return logger;
        } catch (NoClassDefFoundError e5) {
            return null;
        } catch (ClassNotFoundException e6) {
            return null;
        }
    }

    public static String getLoggingProperty(String str) {
        try {
            Class cls = Class.forName("java.util.logging.LogManager");
            Object invoke = cls.getMethod("getLogManager", new Class[0]).invoke(null, null);
            String str2 = "getProperty";
            Class[] clsArr = new Class[1];
            Class cls2 = c;
            if (cls2 == null) {
                cls2 = Class.forName("java.lang.String");
                c = cls2;
            }
            clsArr[0] = cls2;
            return (String) cls.getMethod(str2, clsArr).invoke(invoke, new Object[]{str});
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (Exception e2) {
            return null;
        }
    }

    public static void setLogger(String str) {
        e = str;
    }
}
