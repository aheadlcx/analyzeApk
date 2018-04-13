package com.facebook.stetho.websocket;

import java.io.IOException;

interface WriteCallback {
    void onFailure(IOException iOException);

    void onSuccess();
}
