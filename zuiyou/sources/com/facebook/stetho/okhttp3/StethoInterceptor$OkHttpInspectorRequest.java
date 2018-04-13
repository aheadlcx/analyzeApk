package com.facebook.stetho.okhttp3;

import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorRequest;
import com.facebook.stetho.inspector.network.RequestBodyHelper;
import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.y;
import okhttp3.z;
import okio.BufferedSink;
import okio.Okio;

class StethoInterceptor$OkHttpInspectorRequest implements InspectorRequest {
    private final y mRequest;
    private RequestBodyHelper mRequestBodyHelper;
    private final String mRequestId;

    public StethoInterceptor$OkHttpInspectorRequest(String str, y yVar, RequestBodyHelper requestBodyHelper) {
        this.mRequestId = str;
        this.mRequest = yVar;
        this.mRequestBodyHelper = requestBodyHelper;
    }

    public String id() {
        return this.mRequestId;
    }

    public String friendlyName() {
        return null;
    }

    @Nullable
    public Integer friendlyNameExtra() {
        return null;
    }

    public String url() {
        return this.mRequest.a().toString();
    }

    public String method() {
        return this.mRequest.b();
    }

    @Nullable
    public byte[] body() throws IOException {
        z d = this.mRequest.d();
        if (d == null) {
            return null;
        }
        BufferedSink buffer = Okio.buffer(Okio.sink(this.mRequestBodyHelper.createBodySink(firstHeaderValue("Content-Encoding"))));
        try {
            d.writeTo(buffer);
            return this.mRequestBodyHelper.getDisplayBody();
        } finally {
            buffer.close();
        }
    }

    public int headerCount() {
        return this.mRequest.c().a();
    }

    public String headerName(int i) {
        return this.mRequest.c().a(i);
    }

    public String headerValue(int i) {
        return this.mRequest.c().b(i);
    }

    @Nullable
    public String firstHeaderValue(String str) {
        return this.mRequest.a(str);
    }
}
