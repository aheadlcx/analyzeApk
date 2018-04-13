package com.facebook.stetho.inspector.network;

public class SimpleTextInspectorWebSocketFrame implements NetworkEventReporter$InspectorWebSocketFrame {
    private final String mPayload;
    private final String mRequestId;

    public SimpleTextInspectorWebSocketFrame(String str, String str2) {
        this.mRequestId = str;
        this.mPayload = str2;
    }

    public String requestId() {
        return this.mRequestId;
    }

    public int opcode() {
        return 1;
    }

    public boolean mask() {
        return false;
    }

    public String payloadData() {
        return this.mPayload;
    }
}
