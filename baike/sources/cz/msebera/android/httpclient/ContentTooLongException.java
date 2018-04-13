package cz.msebera.android.httpclient;

import java.io.IOException;

public class ContentTooLongException extends IOException {
    public ContentTooLongException(String str) {
        super(str);
    }
}
