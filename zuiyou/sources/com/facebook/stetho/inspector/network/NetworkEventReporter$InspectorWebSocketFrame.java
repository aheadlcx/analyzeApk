package com.facebook.stetho.inspector.network;

public interface NetworkEventReporter$InspectorWebSocketFrame {
    public static final int OPCODE_BINARY = 2;
    public static final int OPCODE_CONNECTION_CLOSE = 8;
    public static final int OPCODE_CONTINUATION = 0;
    public static final int OPCODE_PING = 9;
    public static final int OPCODE_PONG = 10;
    public static final int OPCODE_TEXT = 1;

    boolean mask();

    int opcode();

    String payloadData();

    String requestId();
}
