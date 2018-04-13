package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class WebSocketReceiver implements Runnable {
    static Class a;
    private static final String b;
    private static final Logger c = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, b);
    private boolean d = false;
    private boolean e = false;
    private Object f = new Object();
    private InputStream g;
    private Thread h = null;
    private volatile boolean i;
    private PipedOutputStream j;

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketReceiver");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        b = cls.getName();
    }

    public WebSocketReceiver(InputStream inputStream, PipedInputStream pipedInputStream) throws IOException {
        this.g = inputStream;
        this.j = new PipedOutputStream();
        pipedInputStream.connect(this.j);
    }

    public void start(String str) {
        c.fine(b, "start", "855");
        synchronized (this.f) {
            if (!this.d) {
                this.d = true;
                this.h = new Thread(this, str);
                this.h.start();
            }
        }
    }

    public void stop() {
        this.e = true;
        synchronized (this.f) {
            c.fine(b, "stop", "850");
            if (this.d) {
                this.d = false;
                this.i = false;
                a();
                if (!Thread.currentThread().equals(this.h)) {
                    try {
                        this.h.join();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        this.h = null;
        c.fine(b, "stop", "851");
    }

    public void run() {
        while (this.d && this.g != null) {
            try {
                c.fine(b, "run", "852");
                this.i = this.g.available() > 0;
                WebSocketFrame webSocketFrame = new WebSocketFrame(this.g);
                if (!webSocketFrame.isCloseFlag()) {
                    for (byte write : webSocketFrame.getPayload()) {
                        this.j.write(write);
                    }
                    this.j.flush();
                } else if (!this.e) {
                    throw new IOException("Server sent a WebSocket Frame with the Stop OpCode");
                }
                this.i = false;
            } catch (IOException e) {
                stop();
            }
        }
    }

    private void a() {
        try {
            this.j.close();
        } catch (IOException e) {
        }
    }

    public boolean isRunning() {
        return this.d;
    }

    public boolean isReceiving() {
        return this.i;
    }
}
