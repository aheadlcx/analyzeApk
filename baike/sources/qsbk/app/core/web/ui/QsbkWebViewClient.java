package qsbk.app.core.web.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.core.utils.LogUtils;

public class QsbkWebViewClient extends WebViewClient {
    private void a(Context context, String str) {
        LogUtils.d("download url:" + str);
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        LogUtils.d("url:" + str);
        String toLowerCase = str.toLowerCase();
        int indexOf = toLowerCase.indexOf(63);
        if ((indexOf != -1 ? toLowerCase.substring(0, indexOf) : toLowerCase).endsWith(".apk")) {
            a(webView.getContext(), str);
            return true;
        } else if (toLowerCase.startsWith("http://") || toLowerCase.startsWith("https://")) {
            webView.loadUrl(str);
            return true;
        } else {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
                webView.getContext().startActivity(intent);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslErrorHandler.proceed();
    }

    public void onLoadResource(WebView webView, String str) {
        super.onLoadResource(webView, str);
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        return super.shouldOverrideKeyEvent(webView, keyEvent);
    }

    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return super.shouldInterceptRequest(webView, str);
    }
}
