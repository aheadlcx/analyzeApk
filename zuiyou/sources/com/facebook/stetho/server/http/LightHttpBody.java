package com.facebook.stetho.server.http;

import java.io.IOException;
import java.io.OutputStream;

public abstract class LightHttpBody {
    public abstract int contentLength();

    public abstract String contentType();

    public abstract void writeTo(OutputStream outputStream) throws IOException;

    public static LightHttpBody create(String str, String str2) {
        try {
            return create(str.getBytes("UTF-8"), str2);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static LightHttpBody create(final byte[] bArr, final String str) {
        return new LightHttpBody() {
            public String contentType() {
                return str;
            }

            public int contentLength() {
                return bArr.length;
            }

            public void writeTo(OutputStream outputStream) throws IOException {
                outputStream.write(bArr);
            }
        };
    }
}
