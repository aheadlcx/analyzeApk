package com.facebook.stetho.okhttp3;

import com.facebook.stetho.inspector.network.NetworkEventReporter$InspectorResponse;
import javax.annotation.Nullable;
import okhttp3.aa;
import okhttp3.i;
import okhttp3.y;

class StethoInterceptor$OkHttpInspectorResponse implements NetworkEventReporter$InspectorResponse {
    private final i mConnection;
    private final y mRequest;
    private final String mRequestId;
    private final aa mResponse;

    public StethoInterceptor$OkHttpInspectorResponse(String str, y yVar, aa aaVar, i iVar) {
        this.mRequestId = str;
        this.mRequest = yVar;
        this.mResponse = aaVar;
        this.mConnection = iVar;
    }

    public String requestId() {
        return this.mRequestId;
    }

    public String url() {
        return this.mRequest.a().toString();
    }

    public int statusCode() {
        return this.mResponse.b();
    }

    public String reasonPhrase() {
        return this.mResponse.d();
    }

    public boolean connectionReused() {
        return false;
    }

    public int connectionId() {
        return this.mConnection.hashCode();
    }

    public boolean fromDiskCache() {
        return this.mResponse.j() != null;
    }

    public int headerCount() {
        return this.mResponse.f().a();
    }

    public String headerName(int i) {
        return this.mResponse.f().a(i);
    }

    public String headerValue(int i) {
        return this.mResponse.f().b(i);
    }

    @Nullable
    public String firstHeaderValue(String str) {
        return this.mResponse.a(str);
    }
}
