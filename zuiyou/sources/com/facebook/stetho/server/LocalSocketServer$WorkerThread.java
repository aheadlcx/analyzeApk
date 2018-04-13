package com.facebook.stetho.server;

import android.net.LocalSocket;
import com.facebook.stetho.common.LogUtil;
import java.io.IOException;

class LocalSocketServer$WorkerThread extends Thread {
    private final LocalSocket mSocket;
    private final SocketHandler mSocketHandler;

    public LocalSocketServer$WorkerThread(LocalSocket localSocket, SocketHandler socketHandler) {
        this.mSocket = localSocket;
        this.mSocketHandler = socketHandler;
    }

    public void run() {
        try {
            this.mSocketHandler.onAccepted(this.mSocket);
            try {
                this.mSocket.close();
            } catch (IOException e) {
            }
        } catch (IOException e2) {
            LogUtil.w("I/O error: %s", e2);
            try {
                this.mSocket.close();
            } catch (IOException e3) {
            }
        } catch (Throwable th) {
            try {
                this.mSocket.close();
            } catch (IOException e4) {
            }
            throw th;
        }
    }
}
