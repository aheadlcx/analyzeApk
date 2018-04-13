package qsbk.app.utils;

import android.util.Pair;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class S2sHttpClient extends HttpClient {
    private static S2sHttpClient a = null;

    public static S2sHttpClient getIntentce() {
        if (a == null) {
            a = new S2sHttpClient();
        }
        return a;
    }

    protected HttpURLConnection getConnection(String str, Pair<String, DomainRecord> pair) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        return httpURLConnection;
    }
}
