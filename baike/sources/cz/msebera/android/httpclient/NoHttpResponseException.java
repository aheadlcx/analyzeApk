package cz.msebera.android.httpclient;

import java.io.IOException;

public class NoHttpResponseException extends IOException {
    public NoHttpResponseException(String str) {
        super(str);
    }
}
