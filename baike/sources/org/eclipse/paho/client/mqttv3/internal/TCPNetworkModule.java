package org.eclipse.paho.client.mqttv3.internal;

import com.baidu.mobstat.Config;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class TCPNetworkModule implements NetworkModule {
    private static final String a;
    static Class c;
    private static final Logger d = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, a);
    protected Socket b;
    private SocketFactory e;
    private String f;
    private int g;
    private int h;

    static {
        Class cls = c;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule");
                c = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        a = cls.getName();
    }

    public TCPNetworkModule(SocketFactory socketFactory, String str, int i, String str2) {
        d.setResourceName(str2);
        this.e = socketFactory;
        this.f = str;
        this.g = i;
    }

    public void start() throws IOException, MqttException {
        try {
            d.fine(a, "start", "252", new Object[]{this.f, new Integer(this.g), new Long((long) (this.h * 1000))});
            SocketAddress inetSocketAddress = new InetSocketAddress(this.f, this.g);
            this.b = this.e.createSocket();
            this.b.connect(inetSocketAddress, this.h * 1000);
        } catch (Throwable e) {
            d.fine(a, "start", "250", null, e);
            throw new MqttException(32103, e);
        }
    }

    public InputStream getInputStream() throws IOException {
        return this.b.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return this.b.getOutputStream();
    }

    public void stop() throws IOException {
        if (this.b != null) {
            this.b.close();
        }
    }

    public void setConnectTimeout(int i) {
        this.h = i;
    }

    public String getServerURI() {
        return new StringBuffer("tcp://").append(this.f).append(Config.TRACE_TODAY_VISIT_SPLIT).append(this.g).toString();
    }
}
