package com.facebook.stetho.urlconnection;

import com.facebook.stetho.inspector.network.DefaultResponseHandler;
import com.facebook.stetho.inspector.network.NetworkEventReporter;
import com.facebook.stetho.inspector.network.NetworkEventReporterImpl;
import com.facebook.stetho.inspector.network.RequestBodyHelper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

class StethoURLConnectionManagerImpl {
    private HttpURLConnection mConnection;
    @Nullable
    private final String mFriendlyName;
    @Nullable
    private URLConnectionInspectorRequest mInspectorRequest;
    @Nullable
    private RequestBodyHelper mRequestBodyHelper;
    private final String mRequestId = this.mStethoHook.nextRequestId();
    private final NetworkEventReporter mStethoHook = NetworkEventReporterImpl.get();

    public StethoURLConnectionManagerImpl(@Nullable String str) {
        this.mFriendlyName = str;
    }

    public boolean isStethoActive() {
        return this.mStethoHook.isEnabled();
    }

    public void preConnect(HttpURLConnection httpURLConnection, @Nullable SimpleRequestEntity simpleRequestEntity) {
        throwIfConnection();
        this.mConnection = httpURLConnection;
        if (isStethoActive()) {
            this.mRequestBodyHelper = new RequestBodyHelper(this.mStethoHook, getStethoRequestId());
            this.mInspectorRequest = new URLConnectionInspectorRequest(getStethoRequestId(), this.mFriendlyName, httpURLConnection, simpleRequestEntity, this.mRequestBodyHelper);
            this.mStethoHook.requestWillBeSent(this.mInspectorRequest);
        }
    }

    public void postConnect() throws IOException {
        throwIfNoConnection();
        if (isStethoActive()) {
            if (this.mRequestBodyHelper != null && this.mRequestBodyHelper.hasBody()) {
                this.mRequestBodyHelper.reportDataSent();
            }
            this.mStethoHook.responseHeadersReceived(new URLConnectionInspectorResponse(getStethoRequestId(), this.mConnection));
        }
    }

    public void httpExchangeFailed(IOException iOException) {
        throwIfNoConnection();
        if (isStethoActive()) {
            this.mStethoHook.httpExchangeFailed(getStethoRequestId(), iOException.toString());
        }
    }

    public InputStream interpretResponseStream(@Nullable InputStream inputStream) {
        throwIfNoConnection();
        if (!isStethoActive()) {
            return inputStream;
        }
        return this.mStethoHook.interpretResponseStream(getStethoRequestId(), this.mConnection.getHeaderField("Content-Type"), this.mConnection.getHeaderField("Content-Encoding"), inputStream, new DefaultResponseHandler(this.mStethoHook, getStethoRequestId()));
    }

    private void throwIfNoConnection() {
        if (this.mConnection == null) {
            throw new IllegalStateException("Must call preConnect");
        }
    }

    private void throwIfConnection() {
        if (this.mConnection != null) {
            throw new IllegalStateException("Must not call preConnect twice");
        }
    }

    public NetworkEventReporter getStethoHook() {
        return this.mStethoHook;
    }

    @Nonnull
    public String getStethoRequestId() {
        return this.mRequestId;
    }
}
