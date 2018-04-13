package com.facebook.stetho.urlconnection;

import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorRequest;
import com.facebook.stetho.inspector.network.RequestBodyHelper;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import javax.annotation.Nullable;

class URLConnectionInspectorRequest extends URLConnectionInspectorHeaders implements InspectorRequest {
    private final String mFriendlyName;
    private final String mMethod;
    private final RequestBodyHelper mRequestBodyHelper;
    @Nullable
    private final SimpleRequestEntity mRequestEntity;
    private final String mRequestId;
    private final String mUrl;

    public URLConnectionInspectorRequest(String str, String str2, HttpURLConnection httpURLConnection, @Nullable SimpleRequestEntity simpleRequestEntity, RequestBodyHelper requestBodyHelper) {
        super(Util.convertHeaders(httpURLConnection.getRequestProperties()));
        this.mRequestId = str;
        this.mFriendlyName = str2;
        this.mRequestEntity = simpleRequestEntity;
        this.mRequestBodyHelper = requestBodyHelper;
        this.mUrl = httpURLConnection.getURL().toString();
        this.mMethod = httpURLConnection.getRequestMethod();
    }

    public String id() {
        return this.mRequestId;
    }

    public String friendlyName() {
        return this.mFriendlyName;
    }

    public Integer friendlyNameExtra() {
        return null;
    }

    public String url() {
        return this.mUrl;
    }

    public String method() {
        return this.mMethod;
    }

    @Nullable
    public byte[] body() throws IOException {
        if (this.mRequestEntity == null) {
            return null;
        }
        OutputStream createBodySink = this.mRequestBodyHelper.createBodySink(firstHeaderValue("Content-Encoding"));
        try {
            this.mRequestEntity.writeTo(createBodySink);
            return this.mRequestBodyHelper.getDisplayBody();
        } finally {
            createBodySink.close();
        }
    }
}
