package cz.msebera.android.httpclient.entity;

import java.io.IOException;
import java.io.OutputStream;

public interface ContentProducer {
    void writeTo(OutputStream outputStream) throws IOException;
}
