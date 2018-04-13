package org.eclipse.paho.client.mqttv3;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.util.Debug;

public class MqttConnectOptions {
    public static final boolean CLEAN_SESSION_DEFAULT = true;
    public static final int CONNECTION_TIMEOUT_DEFAULT = 30;
    public static final int KEEP_ALIVE_INTERVAL_DEFAULT = 60;
    public static final int MAX_INFLIGHT_DEFAULT = 10;
    public static final int MQTT_VERSION_3_1 = 3;
    public static final int MQTT_VERSION_3_1_1 = 4;
    public static final int MQTT_VERSION_DEFAULT = 0;
    private int a = 60;
    private int b = 10;
    private String c = null;
    private MqttMessage d = null;
    private String e;
    private char[] f;
    private SocketFactory g;
    private Properties h = null;
    private boolean i = true;
    private int j = 30;
    private String[] k = null;
    private int l = 0;
    private boolean m = false;

    public char[] getPassword() {
        return this.f;
    }

    public void setPassword(char[] cArr) {
        this.f = cArr;
    }

    public String getUserName() {
        return this.e;
    }

    public void setUserName(String str) {
        if (str == null || !str.trim().equals("")) {
            this.e = str;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setWill(MqttTopic mqttTopic, byte[] bArr, int i, boolean z) {
        String name = mqttTopic.getName();
        a(name, bArr);
        a(name, new MqttMessage(bArr), i, z);
    }

    public void setWill(String str, byte[] bArr, int i, boolean z) {
        a(str, bArr);
        a(str, new MqttMessage(bArr), i, z);
    }

    private void a(String str, Object obj) {
        if (str == null || obj == null) {
            throw new IllegalArgumentException();
        }
        MqttTopic.validate(str, false);
    }

    protected void a(String str, MqttMessage mqttMessage, int i, boolean z) {
        this.c = str;
        this.d = mqttMessage;
        this.d.setQos(i);
        this.d.setRetained(z);
        this.d.a(false);
    }

    public int getKeepAliveInterval() {
        return this.a;
    }

    public int getMqttVersion() {
        return this.l;
    }

    public void setKeepAliveInterval(int i) throws IllegalArgumentException {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.a = i;
    }

    public int getMaxInflight() {
        return this.b;
    }

    public void setMaxInflight(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.b = i;
    }

    public int getConnectionTimeout() {
        return this.j;
    }

    public void setConnectionTimeout(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.j = i;
    }

    public SocketFactory getSocketFactory() {
        return this.g;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.g = socketFactory;
    }

    public String getWillDestination() {
        return this.c;
    }

    public MqttMessage getWillMessage() {
        return this.d;
    }

    public Properties getSSLProperties() {
        return this.h;
    }

    public void setSSLProperties(Properties properties) {
        this.h = properties;
    }

    public boolean isCleanSession() {
        return this.i;
    }

    public void setCleanSession(boolean z) {
        this.i = z;
    }

    public String[] getServerURIs() {
        return this.k;
    }

    public void setServerURIs(String[] strArr) {
        for (String a : strArr) {
            a(a);
        }
        this.k = strArr;
    }

    protected static int a(String str) {
        try {
            URI uri = new URI(str);
            if (uri.getScheme().equals("ws")) {
                return 3;
            }
            if (uri.getScheme().equals("wss")) {
                return 4;
            }
            if (!uri.getPath().equals("")) {
                throw new IllegalArgumentException(str);
            } else if (uri.getScheme().equals("tcp")) {
                return 0;
            } else {
                if (uri.getScheme().equals("ssl")) {
                    return 1;
                }
                if (uri.getScheme().equals("local")) {
                    return 2;
                }
                throw new IllegalArgumentException(str);
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(str);
        }
    }

    public void setMqttVersion(int i) throws IllegalArgumentException {
        if (i == 0 || i == 3 || i == 4) {
            this.l = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public boolean isAutomaticReconnect() {
        return this.m;
    }

    public void setAutomaticReconnect(boolean z) {
        this.m = z;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("MqttVersion", new Integer(getMqttVersion()));
        properties.put("CleanSession", Boolean.valueOf(isCleanSession()));
        properties.put("ConTimeout", new Integer(getConnectionTimeout()));
        properties.put("KeepAliveInterval", new Integer(getKeepAliveInterval()));
        properties.put("UserName", getUserName() == null ? "null" : getUserName());
        properties.put("WillDestination", getWillDestination() == null ? "null" : getWillDestination());
        if (getSocketFactory() == null) {
            properties.put("SocketFactory", "null");
        } else {
            properties.put("SocketFactory", getSocketFactory());
        }
        if (getSSLProperties() == null) {
            properties.put("SSLProperties", "null");
        } else {
            properties.put("SSLProperties", getSSLProperties());
        }
        return properties;
    }

    public String toString() {
        return Debug.dumpProperties(getDebug(), "Connection options");
    }
}
