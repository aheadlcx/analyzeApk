package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;

public abstract class MqttWireMessage {
    public static final byte MESSAGE_TYPE_CONNACK = (byte) 2;
    public static final byte MESSAGE_TYPE_CONNECT = (byte) 1;
    public static final byte MESSAGE_TYPE_DISCONNECT = (byte) 14;
    public static final byte MESSAGE_TYPE_PINGREQ = (byte) 12;
    public static final byte MESSAGE_TYPE_PINGRESP = (byte) 13;
    public static final byte MESSAGE_TYPE_PUBACK = (byte) 4;
    public static final byte MESSAGE_TYPE_PUBCOMP = (byte) 7;
    public static final byte MESSAGE_TYPE_PUBLISH = (byte) 3;
    public static final byte MESSAGE_TYPE_PUBREC = (byte) 5;
    public static final byte MESSAGE_TYPE_PUBREL = (byte) 6;
    public static final byte MESSAGE_TYPE_SUBACK = (byte) 9;
    public static final byte MESSAGE_TYPE_SUBSCRIBE = (byte) 8;
    public static final byte MESSAGE_TYPE_UNSUBACK = (byte) 11;
    public static final byte MESSAGE_TYPE_UNSUBSCRIBE = (byte) 10;
    private static final String[] c = new String[]{"reserved", "CONNECT", "CONNACK", "PUBLISH", "PUBACK", "PUBREC", "PUBREL", "PUBCOMP", "SUBSCRIBE", "SUBACK", "UNSUBSCRIBE", "UNSUBACK", "PINGREQ", "PINGRESP", "DISCONNECT"};
    protected int a;
    protected boolean b = false;
    private byte d;

    protected abstract byte a();

    protected abstract byte[] b() throws MqttException;

    public MqttWireMessage(byte b) {
        this.d = b;
        this.a = 0;
    }

    public byte[] getPayload() throws MqttException {
        return new byte[0];
    }

    public byte getType() {
        return this.d;
    }

    public int getMessageId() {
        return this.a;
    }

    public void setMessageId(int i) {
        this.a = i;
    }

    public String getKey() {
        return new Integer(getMessageId()).toString();
    }

    public byte[] getHeader() throws MqttException {
        try {
            int type = ((getType() & 15) << 4) ^ (a() & 15);
            byte[] b = b();
            int length = b.length + getPayload().length;
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(type);
            dataOutputStream.write(a((long) length));
            dataOutputStream.write(b);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new MqttException(e);
        }
    }

    public boolean isMessageIdRequired() {
        return true;
    }

    public static MqttWireMessage createWireMessage(MqttPersistable mqttPersistable) throws MqttException {
        byte[] payloadBytes = mqttPersistable.getPayloadBytes();
        if (payloadBytes == null) {
            payloadBytes = new byte[0];
        }
        return a(new MultiByteArrayInputStream(mqttPersistable.getHeaderBytes(), mqttPersistable.getHeaderOffset(), mqttPersistable.getHeaderLength(), payloadBytes, mqttPersistable.getPayloadOffset(), mqttPersistable.getPayloadLength()));
    }

    public static MqttWireMessage createWireMessage(byte[] bArr) throws MqttException {
        return a(new ByteArrayInputStream(bArr));
    }

    private static MqttWireMessage a(InputStream inputStream) throws MqttException {
        try {
            InputStream countingInputStream = new CountingInputStream(inputStream);
            DataInputStream dataInputStream = new DataInputStream(countingInputStream);
            int readUnsignedByte = dataInputStream.readUnsignedByte();
            byte b = (byte) (readUnsignedByte >> 4);
            byte b2 = (byte) (readUnsignedByte & 15);
            long value = (a(dataInputStream).getValue() + ((long) countingInputStream.getCounter())) - ((long) countingInputStream.getCounter());
            byte[] bArr = new byte[0];
            if (value > 0) {
                bArr = new byte[((int) value)];
                dataInputStream.readFully(bArr, 0, bArr.length);
            }
            byte[] bArr2 = bArr;
            if (b == (byte) 1) {
                return new MqttConnect(b2, bArr2);
            }
            if (b == (byte) 3) {
                return new MqttPublish(b2, bArr2);
            }
            if (b == (byte) 4) {
                return new MqttPubAck(b2, bArr2);
            }
            if (b == (byte) 7) {
                return new MqttPubComp(b2, bArr2);
            }
            if (b == (byte) 2) {
                return new MqttConnack(b2, bArr2);
            }
            if (b == (byte) 12) {
                return new MqttPingReq(b2, bArr2);
            }
            if (b == (byte) 13) {
                return new MqttPingResp(b2, bArr2);
            }
            if (b == (byte) 8) {
                return new MqttSubscribe(b2, bArr2);
            }
            if (b == (byte) 9) {
                return new MqttSuback(b2, bArr2);
            }
            if (b == (byte) 10) {
                return new MqttUnsubscribe(b2, bArr2);
            }
            if (b == (byte) 11) {
                return new MqttUnsubAck(b2, bArr2);
            }
            if (b == (byte) 6) {
                return new MqttPubRel(b2, bArr2);
            }
            if (b == (byte) 5) {
                return new MqttPubRec(b2, bArr2);
            }
            if (b == (byte) 14) {
                return new MqttDisconnect(b2, bArr2);
            }
            throw ExceptionHelper.createMqttException(6);
        } catch (Throwable e) {
            throw new MqttException(e);
        }
    }

    protected static byte[] a(long j) {
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        do {
            int i2 = (byte) ((int) (j % 128));
            j /= 128;
            if (j > 0) {
                i2 = (byte) (i2 | 128);
            }
            byteArrayOutputStream.write(i2);
            i++;
            if (j <= 0) {
                break;
            }
        } while (i < 4);
        return byteArrayOutputStream.toByteArray();
    }

    protected static MultiByteInteger a(DataInputStream dataInputStream) throws IOException {
        long j = 0;
        int i = 1;
        int i2 = 0;
        byte readByte;
        do {
            readByte = dataInputStream.readByte();
            i2++;
            j += (long) ((readByte & 127) * i);
            i *= 128;
        } while ((readByte & 128) != 0);
        return new MultiByteInteger(j, i2);
    }

    protected byte[] c() throws MqttException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(this.a);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new MqttException(e);
        }
    }

    public boolean isRetryable() {
        return false;
    }

    public void setDuplicate(boolean z) {
        this.b = z;
    }

    protected void a(DataOutputStream dataOutputStream, String str) throws MqttException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte length = (byte) ((bytes.length >>> 0) & 255);
            dataOutputStream.write((byte) ((bytes.length >>> 8) & 255));
            dataOutputStream.write(length);
            dataOutputStream.write(bytes);
        } catch (Throwable e) {
            throw new MqttException(e);
        } catch (Throwable e2) {
            throw new MqttException(e2);
        }
    }

    protected String b(DataInputStream dataInputStream) throws MqttException {
        try {
            byte[] bArr = new byte[dataInputStream.readUnsignedShort()];
            dataInputStream.readFully(bArr);
            return new String(bArr, "UTF-8");
        } catch (Throwable e) {
            throw new MqttException(e);
        }
    }

    public String toString() {
        return c[this.d];
    }
}
