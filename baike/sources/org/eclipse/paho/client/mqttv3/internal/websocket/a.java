package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

class a extends ByteArrayOutputStream {
    final WebSocketNetworkModule a;

    a(WebSocketNetworkModule webSocketNetworkModule) {
        this.a = webSocketNetworkModule;
    }

    public void flush() throws IOException {
        ByteBuffer wrap;
        synchronized (this) {
            wrap = ByteBuffer.wrap(toByteArray());
            reset();
        }
        WebSocketNetworkModule.a(this.a).write(new WebSocketFrame((byte) 2, true, wrap.array()).encodeFrame());
        WebSocketNetworkModule.a(this.a).flush();
    }
}
