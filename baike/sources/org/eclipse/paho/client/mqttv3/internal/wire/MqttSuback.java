package org.eclipse.paho.client.mqttv3.internal.wire;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttSuback extends MqttAck {
    private int[] c;

    public MqttSuback(byte b, byte[] bArr) throws IOException {
        super((byte) 9);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.a = dataInputStream.readUnsignedShort();
        int i = 0;
        this.c = new int[(bArr.length - 2)];
        for (int read = dataInputStream.read(); read != -1; read = dataInputStream.read()) {
            this.c[i] = read;
            i++;
        }
        dataInputStream.close();
    }

    protected byte[] b() throws MqttException {
        return new byte[0];
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString()).append(" granted Qos");
        for (int append : this.c) {
            stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(append);
        }
        return stringBuffer.toString();
    }

    public int[] getGrantedQos() {
        return this.c;
    }
}
