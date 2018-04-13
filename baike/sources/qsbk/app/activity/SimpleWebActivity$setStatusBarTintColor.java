package qsbk.app.activity;

import android.webkit.JavascriptInterface;

public class SimpleWebActivity$setStatusBarTintColor {
    final /* synthetic */ SimpleWebActivity a;

    public SimpleWebActivity$setStatusBarTintColor(SimpleWebActivity simpleWebActivity) {
        this.a = simpleWebActivity;
    }

    @JavascriptInterface
    public void setColor(int i) {
        this.a.statusBarTintColor = i;
        this.a.setStatusColor();
    }
}
