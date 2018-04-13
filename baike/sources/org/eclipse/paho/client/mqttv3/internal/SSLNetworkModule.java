package org.eclipse.paho.client.mqttv3.internal;

import com.baidu.mobstat.Config;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class SSLNetworkModule extends TCPNetworkModule {
    static Class a;
    private static final String d;
    private static final Logger e = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, d);
    private String[] f;
    private int g;
    private String h;
    private int i;

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        d = cls.getName();
    }

    public SSLNetworkModule(SSLSocketFactory sSLSocketFactory, String str, int i, String str2) {
        super(sSLSocketFactory, str, i, str2);
        this.h = str;
        this.i = i;
        e.setResourceName(str2);
    }

    public String[] getEnabledCiphers() {
        return this.f;
    }

    public void setEnabledCiphers(String[] strArr) {
        this.f = strArr;
        if (this.b != null && strArr != null) {
            if (e.isLoggable(5)) {
                Object obj = "";
                int i = 0;
                while (i < strArr.length) {
                    if (i > 0) {
                        obj = new StringBuffer(String.valueOf(obj)).append(Constants.ACCEPT_TIME_SEPARATOR_SP).toString();
                    }
                    String stringBuffer = new StringBuffer(String.valueOf(obj)).append(strArr[i]).toString();
                    i++;
                    String str = stringBuffer;
                }
                e.fine(d, "setEnabledCiphers", "260", new Object[]{obj});
            }
            ((SSLSocket) this.b).setEnabledCipherSuites(strArr);
        }
    }

    public void setSSLhandshakeTimeout(int i) {
        super.setConnectTimeout(i);
        this.g = i;
    }

    public void start() throws IOException, MqttException {
        super.start();
        setEnabledCiphers(this.f);
        int soTimeout = this.b.getSoTimeout();
        if (soTimeout == 0) {
            this.b.setSoTimeout(this.g * 1000);
        }
        ((SSLSocket) this.b).startHandshake();
        this.b.setSoTimeout(soTimeout);
    }

    public String getServerURI() {
        return new StringBuffer("ssl://").append(this.h).append(Config.TRACE_TODAY_VISIT_SPLIT).append(this.i).toString();
    }
}
