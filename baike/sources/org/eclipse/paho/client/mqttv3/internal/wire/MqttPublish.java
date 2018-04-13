package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPublish extends MqttPersistableWireMessage {
    private MqttMessage c;
    private String d;
    private byte[] e;

    public MqttPublish(String str, MqttMessage mqttMessage) {
        super((byte) 3);
        this.e = null;
        this.d = str;
        this.c = mqttMessage;
    }

    public MqttPublish(byte b, byte[] bArr) throws MqttException, IOException {
        super((byte) 3);
        this.e = null;
        this.c = new MqttReceivedMessage();
        this.c.setQos((b >> 1) & 3);
        if ((b & 1) == 1) {
            this.c.setRetained(true);
        }
        if ((b & 8) == 8) {
            ((MqttReceivedMessage) this.c).setDuplicate(true);
        }
        InputStream countingInputStream = new CountingInputStream(new ByteArrayInputStream(bArr));
        DataInputStream dataInputStream = new DataInputStream(countingInputStream);
        this.d = b(dataInputStream);
        if (this.c.getQos() > 0) {
            this.a = dataInputStream.readUnsignedShort();
        }
        byte[] bArr2 = new byte[(bArr.length - countingInputStream.getCounter())];
        dataInputStream.readFully(bArr2);
        dataInputStream.close();
        this.c.setPayload(bArr2);
    }

    public String toString() {
        String toHexString;
        StringBuffer stringBuffer = new StringBuffer();
        byte[] payload = this.c.getPayload();
        int min = Math.min(payload.length, 20);
        for (int i = 0; i < min; i++) {
            toHexString = Integer.toHexString(payload[i]);
            if (toHexString.length() == 1) {
                toHexString = new StringBuffer("0").append(toHexString).toString();
            }
            stringBuffer.append(toHexString);
        }
        try {
            toHexString = new String(payload, 0, min, "UTF-8");
        } catch (Exception e) {
            toHexString = "?";
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(super.toString());
        stringBuffer2.append(" qos:").append(this.c.getQos());
        if (this.c.getQos() > 0) {
            stringBuffer2.append(" msgId:").append(this.a);
        }
        stringBuffer2.append(" retained:").append(this.c.isRetained());
        stringBuffer2.append(" dup:").append(this.b);
        stringBuffer2.append(" topic:\"").append(this.d).append("\"");
        stringBuffer2.append(" payload:[hex:").append(stringBuffer);
        stringBuffer2.append(" utf8:\"").append(toHexString).append("\"");
        stringBuffer2.append(" length:").append(payload.length).append("]");
        return stringBuffer2.toString();
    }

    protected byte a() {
        byte qos = (byte) (this.c.getQos() << 1);
        if (this.c.isRetained()) {
            qos = (byte) (qos | 1);
        }
        if (this.c.isDuplicate() || this.b) {
            return (byte) (qos | 8);
        }
        return qos;
    }

    public String getTopicName() {
        return this.d;
    }

    public MqttMessage getMessage() {
        return this.c;
    }

    protected static byte[] a(MqttMessage mqttMessage) {
        return mqttMessage.getPayload();
    }

    public byte[] getPayload() throws MqttException {
        if (this.e == null) {
            this.e = a(this.c);
        }
        return this.e;
    }

    public int getPayloadLength() {
        int i = 0;
        try {
            return getPayload().length;
        } catch (MqttException e) {
            return i;
        }
    }

    public void setMessageId(int i) {
        super.setMessageId(i);
        if (this.c instanceof MqttReceivedMessage) {
            ((MqttReceivedMessage) this.c).setMessageId(i);
        }
    }

    protected byte[] b() throws MqttException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            a(dataOutputStream, this.d);
            if (this.c.getQos() > 0) {
                dataOutputStream.writeShort(this.a);
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new MqttException(e);
        }
    }

    public boolean isMessageIdRequired() {
        return true;
    }
}
