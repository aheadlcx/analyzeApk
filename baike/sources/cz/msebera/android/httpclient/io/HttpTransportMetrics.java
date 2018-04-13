package cz.msebera.android.httpclient.io;

public interface HttpTransportMetrics {
    long getBytesTransferred();

    void reset();
}
