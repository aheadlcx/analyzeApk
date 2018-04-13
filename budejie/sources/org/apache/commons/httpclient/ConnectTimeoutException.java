package org.apache.commons.httpclient;

import java.io.InterruptedIOException;
import org.apache.commons.httpclient.util.ExceptionUtil;

public class ConnectTimeoutException extends InterruptedIOException {
    public ConnectTimeoutException(String str) {
        super(str);
    }

    public ConnectTimeoutException(String str, Throwable th) {
        super(str);
        ExceptionUtil.initCause(this, th);
    }
}
