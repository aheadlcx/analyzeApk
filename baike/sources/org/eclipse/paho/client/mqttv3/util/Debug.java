package org.eclipse.paho.client.mqttv3.util;

import com.alipay.sdk.packet.d;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.message.TokenParser;
import java.util.Enumeration;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class Debug {
    static Class a;
    private static final String b;
    private static final Logger c = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, b);
    private static final String d = System.getProperty("line.separator", "\n");
    private String e;
    private ClientComms f;

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.ClientComms");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        b = cls.getName();
    }

    public Debug(String str, ClientComms clientComms) {
        this.e = str;
        this.f = clientComms;
        c.setResourceName(str);
    }

    public void dumpClientDebug() {
        dumpClientComms();
        dumpConOptions();
        dumpClientState();
        dumpBaseDebug();
    }

    public void dumpBaseDebug() {
        b();
        dumpSystemProperties();
        a();
    }

    protected void a() {
        c.dumpTrace();
    }

    protected void b() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new StringBuffer(String.valueOf(d)).append("==============").append(" Version Info ").append("==============").append(d).toString());
        stringBuffer.append(new StringBuffer(String.valueOf(left(d.e, 20, TokenParser.SP))).append(":  ").append(ClientComms.VERSION).append(d).toString());
        stringBuffer.append(new StringBuffer(String.valueOf(left("Build Level", 20, TokenParser.SP))).append(":  ").append(ClientComms.BUILD_LEVEL).append(d).toString());
        stringBuffer.append(new StringBuffer("==========================================").append(d).toString());
        c.fine(b, "dumpVersion", stringBuffer.toString());
    }

    public void dumpSystemProperties() {
        c.fine(b, "dumpSystemProperties", dumpProperties(System.getProperties(), "SystemProperties").toString());
    }

    public void dumpClientState() {
        if (this.f != null && this.f.getClientState() != null) {
            c.fine(b, "dumpClientState", dumpProperties(this.f.getClientState().getDebug(), new StringBuffer(String.valueOf(this.e)).append(" : ClientState").toString()).toString());
        }
    }

    public void dumpClientComms() {
        if (this.f != null) {
            c.fine(b, "dumpClientComms", dumpProperties(this.f.getDebug(), new StringBuffer(String.valueOf(this.e)).append(" : ClientComms").toString()).toString());
        }
    }

    public void dumpConOptions() {
        if (this.f != null) {
            c.fine(b, "dumpConOptions", dumpProperties(this.f.getConOptions().getDebug(), new StringBuffer(String.valueOf(this.e)).append(" : Connect Options").toString()).toString());
        }
    }

    public static String dumpProperties(Properties properties, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration propertyNames = properties.propertyNames();
        stringBuffer.append(new StringBuffer(String.valueOf(d)).append("==============").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(str).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("==============").append(d).toString());
        while (propertyNames.hasMoreElements()) {
            String str2 = (String) propertyNames.nextElement();
            stringBuffer.append(new StringBuffer(String.valueOf(left(str2, 28, TokenParser.SP))).append(":  ").append(properties.get(str2)).append(d).toString());
        }
        stringBuffer.append(new StringBuffer("==========================================").append(d).toString());
        return stringBuffer.toString();
    }

    public static String left(String str, int i, char c) {
        if (str.length() >= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(str);
        int length = i - str.length();
        while (true) {
            length--;
            if (length < 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append(c);
        }
    }
}
