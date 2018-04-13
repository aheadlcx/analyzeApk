package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import java.io.InputStream;

public class CountingInputStream extends InputStream {
    private InputStream a;
    private int b = 0;

    public CountingInputStream(InputStream inputStream) {
        this.a = inputStream;
    }

    public int read() throws IOException {
        int read = this.a.read();
        if (read != -1) {
            this.b++;
        }
        return read;
    }

    public int getCounter() {
        return this.b;
    }

    public void resetCounter() {
        this.b = 0;
    }
}
