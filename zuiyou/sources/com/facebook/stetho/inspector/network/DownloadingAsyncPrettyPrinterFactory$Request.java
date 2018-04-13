package com.facebook.stetho.inspector.network;

import com.facebook.stetho.common.Util;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

class DownloadingAsyncPrettyPrinterFactory$Request implements Callable<String> {
    private URL url;

    public DownloadingAsyncPrettyPrinterFactory$Request(URL url) {
        this.url = url;
    }

    public String call() throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) this.url.openConnection();
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Got status code: " + responseCode + " while downloading schema with url: " + this.url.toString());
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        try {
            String readAsUTF8 = Util.readAsUTF8(inputStream);
            return readAsUTF8;
        } finally {
            inputStream.close();
        }
    }
}
