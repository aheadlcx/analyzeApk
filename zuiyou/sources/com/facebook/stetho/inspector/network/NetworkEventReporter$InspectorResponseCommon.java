package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorHeaders;

public interface NetworkEventReporter$InspectorResponseCommon extends InspectorHeaders {
    String reasonPhrase();

    String requestId();

    int statusCode();
}
