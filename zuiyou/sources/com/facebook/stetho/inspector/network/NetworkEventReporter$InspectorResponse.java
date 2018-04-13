package com.facebook.stetho.inspector.network;

public interface NetworkEventReporter$InspectorResponse extends NetworkEventReporter$InspectorResponseCommon {
    int connectionId();

    boolean connectionReused();

    boolean fromDiskCache();

    String url();
}
