package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class MqttInputStream extends InputStream {
    static Class a;
    private static final String b;
    private static final Logger c = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, b);
    private ClientState d = null;
    private DataInputStream e;

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.wire.MqttInputStream");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        b = cls.getName();
    }

    public MqttInputStream(ClientState clientState, InputStream inputStream) {
        this.d = clientState;
        this.e = new DataInputStream(inputStream);
    }

    public int read() throws IOException {
        return this.e.read();
    }

    public int available() throws IOException {
        return this.e.available();
    }

    public void close() throws IOException {
        this.e.close();
    }

    public MqttWireMessage readMqttWireMessage() throws IOException, MqttException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte readByte = this.e.readByte();
        this.d.notifyReceivedBytes(1);
        byte b = (byte) ((readByte >>> 4) & 15);
        if (b < (byte) 1 || b > (byte) 14) {
            throw ExceptionHelper.createMqttException(32108);
        }
        long value = MqttWireMessage.a(this.e).getValue();
        byteArrayOutputStream.write(readByte);
        byteArrayOutputStream.write(MqttWireMessage.a(value));
        byte[] bArr = new byte[((int) (value + ((long) byteArrayOutputStream.size())))];
        a(bArr, byteArrayOutputStream.size(), bArr.length - byteArrayOutputStream.size());
        Object toByteArray = byteArrayOutputStream.toByteArray();
        System.arraycopy(toByteArray, 0, bArr, 0, toByteArray.length);
        c.fine(b, "readMqttWireMessage", "501", new Object[]{MqttWireMessage.createWireMessage(bArr)});
        return MqttWireMessage.createWireMessage(bArr);
    }

    private void a(byte[] bArr, int i, int i2) throws IOException {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = 0;
        while (i3 < i2) {
            int read = this.e.read(bArr, i + i3, i2 - i3);
            this.d.notifyReceivedBytes(read);
            if (read < 0) {
                throw new EOFException();
            }
            i3 += read;
        }
    }
}
