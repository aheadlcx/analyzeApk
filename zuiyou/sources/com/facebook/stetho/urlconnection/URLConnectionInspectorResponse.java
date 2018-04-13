package com.facebook.stetho.urlconnection;

import com.facebook.stetho.inspector.network.NetworkEventReporter$InspectorResponse;
import java.io.IOException;
import java.net.HttpURLConnection;

class URLConnectionInspectorResponse extends URLConnectionInspectorHeaders implements NetworkEventReporter$InspectorResponse {
    private final String mRequestId;
    private final int mStatusCode;
    private final String mStatusMessage;
    private final String mUrl;

    public URLConnectionInspectorResponse(String str, HttpURLConnection httpURLConnection) throws IOException {
        super(Util.convertHeaders(httpURLConnection.getHeaderFields()));
        this.mRequestId = str;
        this.mUrl = httpURLConnection.getURL().toString();
        this.mStatusCode = httpURLConnection.getResponseCode();
        this.mStatusMessage = httpURLConnection.getResponseMessage();
    }

    public String requestId() {
        return this.mRequestId;
    }

    public String url() {
        return this.mUrl;
    }

    public int statusCode() {
        return this.mStatusCode;
    }

    public String reasonPhrase() {
        return this.mStatusMessage;
    }

    public boolean connectionReused() {
        return false;
    }

    public int connectionId() {
        return this.mRequestId.hashCode();
    }

    public boolean fromDiskCache() {
        return false;
    }
}
