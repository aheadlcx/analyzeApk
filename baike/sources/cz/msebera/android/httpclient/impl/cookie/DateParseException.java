package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
@Deprecated
public class DateParseException extends Exception {
    public DateParseException(String str) {
        super(str);
    }
}
