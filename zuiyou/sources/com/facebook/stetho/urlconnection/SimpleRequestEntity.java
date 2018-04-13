package com.facebook.stetho.urlconnection;

import java.io.IOException;
import java.io.OutputStream;

public interface SimpleRequestEntity {
    void writeTo(OutputStream outputStream) throws IOException;
}
