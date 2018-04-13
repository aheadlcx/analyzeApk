package com.facebook.stetho.server;

import android.net.LocalSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SocketLike {
    private final LeakyBufferedInputStream mLeakyInput;
    private final LocalSocket mSocket;

    public SocketLike(SocketLike socketLike, LeakyBufferedInputStream leakyBufferedInputStream) {
        this(socketLike.mSocket, leakyBufferedInputStream);
    }

    public SocketLike(LocalSocket localSocket, LeakyBufferedInputStream leakyBufferedInputStream) {
        this.mSocket = localSocket;
        this.mLeakyInput = leakyBufferedInputStream;
    }

    public InputStream getInput() throws IOException {
        return this.mLeakyInput.leakBufferAndStream();
    }

    public OutputStream getOutput() throws IOException {
        return this.mSocket.getOutputStream();
    }
}
