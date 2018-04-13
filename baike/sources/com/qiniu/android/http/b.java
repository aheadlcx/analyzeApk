package com.qiniu.android.http;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.Request;
import okhttp3.Response;

class b implements Interceptor {
    final /* synthetic */ Client a;

    b(Client client) {
        this.a = client;
    }

    public Response intercept(Interceptor$Chain interceptor$Chain) throws IOException {
        Request request = interceptor$Chain.request();
        long currentTimeMillis = System.currentTimeMillis();
        Response proceed = interceptor$Chain.proceed(request);
        long currentTimeMillis2 = System.currentTimeMillis();
        a aVar = (a) request.tag();
        String str = "";
        try {
            str = interceptor$Chain.connection().socket().getRemoteSocketAddress().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        aVar.ip = str;
        aVar.duration = currentTimeMillis2 - currentTimeMillis;
        return proceed;
    }
}
