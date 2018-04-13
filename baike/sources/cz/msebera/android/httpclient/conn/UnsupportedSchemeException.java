package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.annotation.Immutable;
import java.io.IOException;

@Immutable
public class UnsupportedSchemeException extends IOException {
    public UnsupportedSchemeException(String str) {
        super(str);
    }
}
