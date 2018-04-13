package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorHeaders;

public interface NetworkEventReporter$InspectorRequestCommon extends InspectorHeaders {
    String friendlyName();

    String id();
}
