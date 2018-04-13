package org.apache.commons.httpclient;

import java.io.IOException;

public interface HttpMethodRetryHandler {
    boolean retryMethod(HttpMethod httpMethod, IOException iOException, int i);
}
