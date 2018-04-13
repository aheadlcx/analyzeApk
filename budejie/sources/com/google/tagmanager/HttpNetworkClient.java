package com.google.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

class HttpNetworkClient implements NetworkClient {
    private HttpClient mClient;

    HttpNetworkClient() {
    }

    public InputStream getInputStream(String str) throws IOException {
        this.mClient = createNewHttpClient();
        return handleServerResponse(this.mClient, this.mClient.execute(new HttpGet(str)));
    }

    public void sendPostRequest(String str, byte[] bArr) throws IOException {
        HttpClient createNewHttpClient = createNewHttpClient();
        handleServerResponse(createNewHttpClient, createNewHttpClient.execute(createPostRequest(str, bArr)));
        closeWithClient(createNewHttpClient);
    }

    @VisibleForTesting
    HttpPost createPostRequest(String str, byte[] bArr) {
        HttpPost httpPost = new HttpPost(str);
        httpPost.setEntity(new ByteArrayEntity(bArr));
        return httpPost;
    }

    public void close() {
        closeWithClient(this.mClient);
    }

    private void closeWithClient(HttpClient httpClient) {
        if (httpClient != null && httpClient.getConnectionManager() != null) {
            httpClient.getConnectionManager().shutdown();
        }
    }

    private InputStream handleServerResponse(HttpClient httpClient, HttpResponse httpResponse) throws IOException {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            Log.v("Success response");
            return httpResponse.getEntity().getContent();
        }
        String str = "Bad response: " + statusCode;
        if (statusCode == 404) {
            throw new FileNotFoundException(str);
        }
        throw new IOException(str);
    }

    @VisibleForTesting
    HttpClient createNewHttpClient() {
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
        return new DefaultHttpClient(basicHttpParams);
    }
}
