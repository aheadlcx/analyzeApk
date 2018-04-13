package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class MqttOutputStream extends OutputStream {
    static Class a;
    private static final String b;
    private static final Logger c = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, b);
    private ClientState d = null;
    private BufferedOutputStream e;

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.wire.MqttOutputStream");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        b = cls.getName();
    }

    public MqttOutputStream(ClientState clientState, OutputStream outputStream) {
        this.d = clientState;
        this.e = new BufferedOutputStream(outputStream);
    }

    public void close() throws IOException {
        this.e.close();
    }

    public void flush() throws IOException {
        this.e.flush();
    }

    public void write(byte[] bArr) throws IOException {
        this.e.write(bArr);
        this.d.notifySentBytes(bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.e.write(bArr, i, i2);
        this.d.notifySentBytes(i2);
    }

    public void write(int i) throws IOException {
        this.e.write(i);
    }

    public void write(MqttWireMessage mqttWireMessage) throws IOException, MqttException {
        byte[] header = mqttWireMessage.getHeader();
        byte[] payload = mqttWireMessage.getPayload();
        this.e.write(header, 0, header.length);
        this.d.notifySentBytes(header.length);
        int i = 0;
        while (i < payload.length) {
            int min = Math.min(1024, payload.length - i);
            this.e.write(payload, i, min);
            i += 1024;
            this.d.notifySentBytes(min);
        }
        c.fine(b, "write", "500", new Object[]{mqttWireMessage});
    }
}
