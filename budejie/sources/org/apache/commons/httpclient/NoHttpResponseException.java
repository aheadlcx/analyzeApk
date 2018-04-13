package org.apache.commons.httpclient;

import java.io.IOException;
import org.apache.commons.httpclient.util.ExceptionUtil;

public class NoHttpResponseException extends IOException {
    public NoHttpResponseException(String str) {
        super(str);
    }

    public NoHttpResponseException(String str, Throwable th) {
        super(str);
        ExceptionUtil.initCause(this, th);
    }
}
