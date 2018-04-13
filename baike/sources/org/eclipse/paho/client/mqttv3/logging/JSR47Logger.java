package org.eclipse.paho.client.mqttv3.logging;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.MemoryHandler;

public class JSR47Logger implements Logger {
    private Logger a = null;
    private ResourceBundle b = null;
    private ResourceBundle c = null;
    private String d = null;
    private String e = null;
    private String f = null;

    public void initialise(ResourceBundle resourceBundle, String str, String str2) {
        this.c = this.b;
        this.e = str2;
        this.f = str;
        this.a = Logger.getLogger(this.f);
        this.b = resourceBundle;
        this.c = resourceBundle;
        this.d = this.b.getString("0");
    }

    public void setResourceName(String str) {
        this.e = str;
    }

    public boolean isLoggable(int i) {
        return this.a.isLoggable(a(i));
    }

    public void severe(String str, String str2, String str3) {
        log(1, str, str2, str3, null, null);
    }

    public void severe(String str, String str2, String str3, Object[] objArr) {
        log(1, str, str2, str3, objArr, null);
    }

    public void severe(String str, String str2, String str3, Object[] objArr, Throwable th) {
        log(1, str, str2, str3, objArr, th);
    }

    public void warning(String str, String str2, String str3) {
        log(2, str, str2, str3, null, null);
    }

    public void warning(String str, String str2, String str3, Object[] objArr) {
        log(2, str, str2, str3, objArr, null);
    }

    public void warning(String str, String str2, String str3, Object[] objArr, Throwable th) {
        log(2, str, str2, str3, objArr, th);
    }

    public void info(String str, String str2, String str3) {
        log(3, str, str2, str3, null, null);
    }

    public void info(String str, String str2, String str3, Object[] objArr) {
        log(3, str, str2, str3, objArr, null);
    }

    public void info(String str, String str2, String str3, Object[] objArr, Throwable th) {
        log(3, str, str2, str3, objArr, th);
    }

    public void config(String str, String str2, String str3) {
        log(4, str, str2, str3, null, null);
    }

    public void config(String str, String str2, String str3, Object[] objArr) {
        log(4, str, str2, str3, objArr, null);
    }

    public void config(String str, String str2, String str3, Object[] objArr, Throwable th) {
        log(4, str, str2, str3, objArr, th);
    }

    public void log(int i, String str, String str2, String str3, Object[] objArr, Throwable th) {
        Level a = a(i);
        if (this.a.isLoggable(a)) {
            a(a, str, str2, this.d, this.b, str3, objArr, th);
        }
    }

    public void fine(String str, String str2, String str3) {
        trace(5, str, str2, str3, null, null);
    }

    public void fine(String str, String str2, String str3, Object[] objArr) {
        trace(5, str, str2, str3, objArr, null);
    }

    public void fine(String str, String str2, String str3, Object[] objArr, Throwable th) {
        trace(5, str, str2, str3, objArr, th);
    }

    public void finer(String str, String str2, String str3) {
        trace(6, str, str2, str3, null, null);
    }

    public void finer(String str, String str2, String str3, Object[] objArr) {
        trace(6, str, str2, str3, objArr, null);
    }

    public void finer(String str, String str2, String str3, Object[] objArr, Throwable th) {
        trace(6, str, str2, str3, objArr, th);
    }

    public void finest(String str, String str2, String str3) {
        trace(7, str, str2, str3, null, null);
    }

    public void finest(String str, String str2, String str3, Object[] objArr) {
        trace(7, str, str2, str3, objArr, null);
    }

    public void finest(String str, String str2, String str3, Object[] objArr, Throwable th) {
        trace(7, str, str2, str3, objArr, th);
    }

    public void trace(int i, String str, String str2, String str3, Object[] objArr, Throwable th) {
        Level a = a(i);
        if (this.a.isLoggable(a)) {
            a(a, str, str2, this.d, this.c, str3, objArr, th);
        }
    }

    private String a(ResourceBundle resourceBundle, String str) {
        try {
            str = resourceBundle.getString(str);
        } catch (MissingResourceException e) {
        }
        return str;
    }

    private void a(Level level, String str, String str2, String str3, ResourceBundle resourceBundle, String str4, Object[] objArr, Throwable th) {
        if (str4.indexOf("=====") == -1) {
            str4 = MessageFormat.format(a(resourceBundle, str4), objArr);
        }
        LogRecord logRecord = new LogRecord(level, new StringBuffer(String.valueOf(this.e)).append(": ").append(str4).toString());
        logRecord.setSourceClassName(str);
        logRecord.setSourceMethodName(str2);
        logRecord.setLoggerName(this.f);
        if (th != null) {
            logRecord.setThrown(th);
        }
        this.a.log(logRecord);
    }

    private Level a(int i) {
        switch (i) {
            case 1:
                return Level.SEVERE;
            case 2:
                return Level.WARNING;
            case 3:
                return Level.INFO;
            case 4:
                return Level.CONFIG;
            case 5:
                return Level.FINE;
            case 6:
                return Level.FINER;
            case 7:
                return Level.FINEST;
            default:
                return null;
        }
    }

    public String formatMessage(String str, Object[] objArr) {
        try {
            str = this.b.getString(str);
        } catch (MissingResourceException e) {
        }
        return str;
    }

    public void dumpTrace() {
        a(this.a);
    }

    protected static void a(Logger logger) {
        if (logger != null) {
            Handler[] handlers = logger.getHandlers();
            int i = 0;
            while (i < handlers.length) {
                if (handlers[i] instanceof MemoryHandler) {
                    synchronized (handlers[i]) {
                        ((MemoryHandler) handlers[i]).push();
                    }
                    return;
                }
                i++;
            }
            a(logger.getParent());
        }
    }
}
