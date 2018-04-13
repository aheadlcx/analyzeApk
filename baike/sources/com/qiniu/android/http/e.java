package com.qiniu.android.http;

import com.qiniu.android.http.CancellationHandler.CancellationException;
import com.qiniu.android.storage.UpToken;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;

class e implements Callback {
    final /* synthetic */ a a;
    final /* synthetic */ UpToken b;
    final /* synthetic */ CompletionHandler c;
    final /* synthetic */ Client d;

    e(Client client, a aVar, UpToken upToken, CompletionHandler completionHandler) {
        this.d = client;
        this.a = aVar;
        this.b = upToken;
        this.c = completionHandler;
    }

    public void onFailure(Call call, IOException iOException) {
        iOException.printStackTrace();
        int i = -1;
        String message = iOException.getMessage();
        if (iOException instanceof CancellationException) {
            i = -2;
        } else if (iOException instanceof UnknownHostException) {
            i = ResponseInfo.UnknownHost;
        } else if (message != null && message.indexOf("Broken pipe") == 0) {
            i = ResponseInfo.NetworkConnectionLost;
        } else if (iOException instanceof SocketTimeoutException) {
            i = ResponseInfo.TimedOut;
        } else if (iOException instanceof ConnectException) {
            i = ResponseInfo.CannotConnectToHost;
        }
        HttpUrl url = call.request().url();
        this.c.complete(ResponseInfo.create(null, i, "", "", "", url.host(), url.encodedPath(), "", url.port(), (double) this.a.duration, -1, iOException.getMessage(), this.b), null);
    }

    public void onResponse(Call call, Response response) throws IOException {
        a aVar = (a) response.request().tag();
        Client.b(response, aVar.ip, aVar.duration, this.b, this.c);
    }
}
