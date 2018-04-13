package com.facebook.stetho.okhttp3;

import com.facebook.stetho.inspector.network.DefaultResponseHandler;
import com.facebook.stetho.inspector.network.NetworkEventReporter;
import com.facebook.stetho.inspector.network.NetworkEventReporterImpl;
import com.facebook.stetho.inspector.network.RequestBodyHelper;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.t;
import okhttp3.t.a;
import okhttp3.u;
import okhttp3.y;
import okio.BufferedSource;
import okio.Okio;

public class StethoInterceptor implements t {
    private final NetworkEventReporter mEventReporter = NetworkEventReporterImpl.get();

    private static class ForwardingResponseBody extends ab {
        private final ab mBody;
        private final BufferedSource mInterceptedSource;

        public ForwardingResponseBody(ab abVar, InputStream inputStream) {
            this.mBody = abVar;
            this.mInterceptedSource = Okio.buffer(Okio.source(inputStream));
        }

        public u contentType() {
            return this.mBody.contentType();
        }

        public long contentLength() {
            return this.mBody.contentLength();
        }

        public BufferedSource source() {
            return this.mInterceptedSource;
        }
    }

    public aa intercept(a aVar) throws IOException {
        RequestBodyHelper requestBodyHelper;
        String str = null;
        String nextRequestId = this.mEventReporter.nextRequestId();
        y a = aVar.a();
        if (this.mEventReporter.isEnabled()) {
            requestBodyHelper = new RequestBodyHelper(this.mEventReporter, nextRequestId);
            this.mEventReporter.requestWillBeSent(new StethoInterceptor$OkHttpInspectorRequest(nextRequestId, a, requestBodyHelper));
        } else {
            requestBodyHelper = null;
        }
        try {
            aa a2 = aVar.a(a);
            if (this.mEventReporter.isEnabled()) {
                InputStream byteStream;
                u uVar;
                if (requestBodyHelper != null && requestBodyHelper.hasBody()) {
                    requestBodyHelper.reportDataSent();
                }
                this.mEventReporter.responseHeadersReceived(new StethoInterceptor$OkHttpInspectorResponse(nextRequestId, a, a2, aVar.b()));
                ab g = a2.g();
                if (g != null) {
                    u contentType = g.contentType();
                    byteStream = g.byteStream();
                    uVar = contentType;
                } else {
                    byteStream = null;
                    uVar = null;
                }
                NetworkEventReporter networkEventReporter = this.mEventReporter;
                if (uVar != null) {
                    str = uVar.toString();
                }
                InputStream interpretResponseStream = networkEventReporter.interpretResponseStream(nextRequestId, str, a2.a("Content-Encoding"), byteStream, new DefaultResponseHandler(this.mEventReporter, nextRequestId));
                if (interpretResponseStream != null) {
                    return a2.h().a(new ForwardingResponseBody(g, interpretResponseStream)).a();
                }
            }
            return a2;
        } catch (IOException e) {
            if (this.mEventReporter.isEnabled()) {
                this.mEventReporter.httpExchangeFailed(nextRequestId, e.toString());
            }
            throw e;
        }
    }
}
