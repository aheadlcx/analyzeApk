package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.MessageCatalog;

public class MqttException extends Exception {
    public static final short REASON_CODE_BROKER_UNAVAILABLE = (short) 3;
    public static final short REASON_CODE_CLIENT_ALREADY_DISCONNECTED = (short) 32101;
    public static final short REASON_CODE_CLIENT_CLOSED = (short) 32111;
    public static final short REASON_CODE_CLIENT_CONNECTED = (short) 32100;
    public static final short REASON_CODE_CLIENT_DISCONNECTING = (short) 32102;
    public static final short REASON_CODE_CLIENT_DISCONNECT_PROHIBITED = (short) 32107;
    public static final short REASON_CODE_CLIENT_EXCEPTION = (short) 0;
    public static final short REASON_CODE_CLIENT_NOT_CONNECTED = (short) 32104;
    public static final short REASON_CODE_CLIENT_TIMEOUT = (short) 32000;
    public static final short REASON_CODE_CONNECTION_LOST = (short) 32109;
    public static final short REASON_CODE_CONNECT_IN_PROGRESS = (short) 32110;
    public static final short REASON_CODE_DISCONNECTED_BUFFER_FULL = (short) 32203;
    public static final short REASON_CODE_FAILED_AUTHENTICATION = (short) 4;
    public static final short REASON_CODE_INVALID_CLIENT_ID = (short) 2;
    public static final short REASON_CODE_INVALID_MESSAGE = (short) 32108;
    public static final short REASON_CODE_INVALID_PROTOCOL_VERSION = (short) 1;
    public static final short REASON_CODE_MAX_INFLIGHT = (short) 32202;
    public static final short REASON_CODE_NOT_AUTHORIZED = (short) 5;
    public static final short REASON_CODE_NO_MESSAGE_IDS_AVAILABLE = (short) 32001;
    public static final short REASON_CODE_SERVER_CONNECT_ERROR = (short) 32103;
    public static final short REASON_CODE_SOCKET_FACTORY_MISMATCH = (short) 32105;
    public static final short REASON_CODE_SSL_CONFIG_ERROR = (short) 32106;
    public static final short REASON_CODE_SUBSCRIBE_FAILED = (short) 128;
    public static final short REASON_CODE_TOKEN_INUSE = (short) 32201;
    public static final short REASON_CODE_UNEXPECTED_ERROR = (short) 6;
    public static final short REASON_CODE_WRITE_TIMEOUT = (short) 32002;
    private int a;
    private Throwable b;

    public MqttException(int i) {
        this.a = i;
    }

    public MqttException(Throwable th) {
        this.a = 0;
        this.b = th;
    }

    public MqttException(int i, Throwable th) {
        this.a = i;
        this.b = th;
    }

    public int getReasonCode() {
        return this.a;
    }

    public Throwable getCause() {
        return this.b;
    }

    public String getMessage() {
        return MessageCatalog.getMessage(this.a);
    }

    public String toString() {
        String stringBuffer = new StringBuffer(String.valueOf(getMessage())).append(" (").append(this.a).append(")").toString();
        if (this.b != null) {
            return new StringBuffer(String.valueOf(stringBuffer)).append(" - ").append(this.b.toString()).toString();
        }
        return stringBuffer;
    }
}
