package com.facebook.stetho.server;

import java.io.IOException;

public interface SocketLikeHandler {
    void onAccepted(SocketLike socketLike) throws IOException;
}
