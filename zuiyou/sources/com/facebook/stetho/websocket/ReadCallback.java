package com.facebook.stetho.websocket;

interface ReadCallback {
    void onCompleteFrame(byte b, byte[] bArr, int i);
}
