package com.facebook.stetho.server;

import android.net.LocalSocket;
import java.io.IOException;

public interface SocketHandler {
    void onAccepted(LocalSocket localSocket) throws IOException;
}
