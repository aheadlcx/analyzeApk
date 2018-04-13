package qsbk.app.cafe;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.webkit.WebView;
import java.net.URISyntaxException;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

public class UrlHandler {
    private final Context a;

    public UrlHandler(Context context) {
        this.a = context;
    }

    public static void downloadAPK(Context context, String str) {
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.startsWith("weixin://") || toLowerCase.startsWith("alipays://")) {
            try {
                this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            String substring;
            int indexOf = str.indexOf(63);
            if (indexOf != -1) {
                substring = str.substring(0, indexOf);
            } else {
                substring = str;
            }
            if (substring.endsWith(".apk")) {
                downloadAPK(webView.getContext(), str);
            } else if (toLowerCase.startsWith("http://") || toLowerCase.startsWith("https://")) {
                webView.loadUrl(str);
            } else {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
                    webView.getContext().startActivity(intent);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    try {
                        Intent parseUri = Intent.parseUri(str, 1);
                        parseUri.setComponent(null);
                        parseUri.addCategory("android.intent.category.BROWSABLE");
                        if (VERSION.SDK_INT >= 15) {
                            parseUri.setSelector(null);
                        }
                        if (this.a.getPackageManager().resolveActivity(parseUri, 0) != null) {
                            this.a.startActivity(parseUri);
                        }
                    } catch (URISyntaxException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
        return true;
    }
}
