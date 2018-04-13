package org.eclipse.paho.client.mqttv3.internal.websocket;

import com.baidu.mobstat.Config;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import qsbk.app.core.net.UrlConstants;

public class WebSocketSecureNetworkModule extends SSLNetworkModule {
    static Class d;
    private static final String e;
    private static final Logger f = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, e);
    private PipedInputStream g;
    private WebSocketReceiver h;
    private String i;
    private String j;
    private int k;
    private ByteArrayOutputStream l = new b(this);

    static {
        Class cls = d;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketSecureNetworkModule");
                d = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        e = cls.getName();
    }

    public WebSocketSecureNetworkModule(SSLSocketFactory sSLSocketFactory, String str, String str2, int i, String str3) {
        super(sSLSocketFactory, str2, i, str3);
        this.i = str;
        this.j = str2;
        this.k = i;
        this.g = new PipedInputStream();
        f.setResourceName(str3);
    }

    public void start() throws IOException, MqttException {
        super.start();
        new WebSocketHandshake(super.getInputStream(), super.getOutputStream(), this.i, this.j, this.k).execute();
        this.h = new WebSocketReceiver(b(), this.g);
        this.h.start("WssSocketReceiver");
    }

    static OutputStream a(WebSocketSecureNetworkModule webSocketSecureNetworkModule) throws IOException {
        return webSocketSecureNetworkModule.a();
    }

    private OutputStream a() throws IOException {
        return super.getOutputStream();
    }

    private InputStream b() throws IOException {
        return super.getInputStream();
    }

    public InputStream getInputStream() throws IOException {
        return this.g;
    }

    public OutputStream getOutputStream() throws IOException {
        return this.l;
    }

    public void stop() throws IOException {
        a().write(new WebSocketFrame((byte) 8, true, Constants.DEFAULT_UIN.getBytes()).encodeFrame());
        a().flush();
        if (this.h != null) {
            this.h.stop();
        }
        super.stop();
    }

    public String getServerURI() {
        return new StringBuffer(UrlConstants.WEBSOCKET_WSS).append(this.j).append(Config.TRACE_TODAY_VISIT_SPLIT).append(this.k).toString();
    }
}
