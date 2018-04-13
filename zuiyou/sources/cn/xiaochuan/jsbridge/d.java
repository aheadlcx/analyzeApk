package cn.xiaochuan.jsbridge;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import java.util.HashMap;

public class d extends com.a.a.a.d {
    private static HashMap<String, String> a = new HashMap();

    static {
        a.put("Cache-Control", "max-age=86400");
    }

    public d(f fVar) {
        super(fVar);
    }

    protected boolean a(WebView webView, String str) throws Exception {
        if (str.startsWith("http") && !str.contains("download")) {
            return false;
        }
        return true;
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslErrorHandler != null) {
            sslErrorHandler.proceed();
        }
    }
}
