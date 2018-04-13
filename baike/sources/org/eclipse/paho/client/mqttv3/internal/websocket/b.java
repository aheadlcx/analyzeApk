package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

class b extends ByteArrayOutputStream {
    final WebSocketSecureNetworkModule a;

    b(WebSocketSecureNetworkModule webSocketSecureNetworkModule) {
        this.a = webSocketSecureNetworkModule;
    }

    public void flush() throws IOException {
        ByteBuffer wrap;
        synchronized (this) {
            wrap = ByteBuffer.wrap(toByteArray());
            reset();
        }
        WebSocketSecureNetworkModule.a(this.a).write(new WebSocketFrame((byte) 2, true, wrap.array()).encodeFrame());
        WebSocketSecureNetworkModule.a(this.a).flush();
    }
}
