package cz.msebera.android.httpclient.io;

public interface BufferInfo {
    int available();

    int capacity();

    int length();
}
