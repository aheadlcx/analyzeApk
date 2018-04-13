package com.opensource.svgaplayer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 6})
final class g implements Runnable {
    final /* synthetic */ URL a;
    final /* synthetic */ Function1 b;
    final /* synthetic */ Function1 c;

    g(URL url, Function1 function1, Function1 function12) {
        this.a = url;
        this.b = function1;
        this.c = function12;
    }

    public final void run() {
        try {
            URLConnection openConnection = this.a.openConnection();
            if (!(openConnection instanceof HttpURLConnection)) {
                openConnection = null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            if (httpURLConnection != null) {
                httpURLConnection.setConnectTimeout(20000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr, 0, 4096);
                    if (read == -1) {
                        this.b.invoke(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                        return;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.c.invoke(e);
        }
    }
}
