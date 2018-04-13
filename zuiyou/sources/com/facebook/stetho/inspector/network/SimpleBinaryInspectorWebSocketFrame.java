package com.facebook.stetho.inspector.network;

public class SimpleBinaryInspectorWebSocketFrame implements NetworkEventReporter$InspectorWebSocketFrame {
    private final byte[] mPayload;
    private final String mRequestId;

    public SimpleBinaryInspectorWebSocketFrame(String str, byte[] bArr) {
        this.mRequestId = str;
        this.mPayload = bArr;
    }

    public String requestId() {
        return this.mRequestId;
    }

    public int opcode() {
        return 2;
    }

    public boolean mask() {
        return false;
    }

    public String payloadData() {
        try {
            return new String(this.mPayload, "UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
