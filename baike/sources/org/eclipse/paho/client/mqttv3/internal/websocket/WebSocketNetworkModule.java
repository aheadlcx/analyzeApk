package org.eclipse.paho.client.mqttv3.internal.websocket;

import com.baidu.mobstat.Config;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class WebSocketNetworkModule extends TCPNetworkModule {
    static Class a;
    private static final String d;
    private static final Logger e = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, d);
    private String f;
    private String g;
    private int h;
    private PipedInputStream i;
    private WebSocketReceiver j;
    private ByteArrayOutputStream k = new a(this);

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketNetworkModule");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        d = cls.getName();
    }

    public WebSocketNetworkModule(SocketFactory socketFactory, String str, String str2, int i, String str3) {
        super(socketFactory, str2, i, str3);
        this.f = str;
        this.g = str2;
        this.h = i;
        this.i = new PipedInputStream();
        e.setResourceName(str3);
    }

    public void start() throws IOException, MqttException {
        super.start();
        new WebSocketHandshake(b(), a(), this.f, this.g, this.h).execute();
        this.j = new WebSocketReceiver(b(), this.i);
        this.j.start("webSocketReceiver");
    }

    static OutputStream a(WebSocketNetworkModule webSocketNetworkModule) throws IOException {
        return webSocketNetworkModule.a();
    }

    private OutputStream a() throws IOException {
        return super.getOutputStream();
    }

    private InputStream b() throws IOException {
        return super.getInputStream();
    }

    public InputStream getInputStream() throws IOException {
        return this.i;
    }

    public OutputStream getOutputStream() throws IOException {
        return this.k;
    }

    public void stop() throws IOException {
        a().write(new WebSocketFrame((byte) 8, true, Constants.DEFAULT_UIN.getBytes()).encodeFrame());
        a().flush();
        if (this.j != null) {
            this.j.stop();
        }
        super.stop();
    }

    public String getServerURI() {
        return new StringBuffer("ws://").append(this.g).append(Config.TRACE_TODAY_VISIT_SPLIT).append(this.h).toString();
    }
}
