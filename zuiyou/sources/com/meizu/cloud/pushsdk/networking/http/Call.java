package com.meizu.cloud.pushsdk.networking.http;

import java.io.IOException;

public interface Call {
    void cancel();

    Response execute() throws IOException;

    boolean isCanceled();

    boolean isExecuted();

    Request request();
}
