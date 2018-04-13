package com.a.a;

import android.webkit.JavascriptInterface;

public class d$b {
    final /* synthetic */ d a;

    public d$b(d dVar) {
        this.a = dVar;
    }

    @JavascriptInterface
    public void gtCallBack(String str, String str2, String str3) {
        c.a("gtCallBack");
        c.a("code:" + str);
        c.a("result:" + str2);
        c.a("message:client result" + str3);
        try {
            if (Integer.parseInt(str) == 1) {
                this.a.dismiss();
                if (d.b(this.a) != null) {
                    d.b(this.a).gtResult(true, str2);
                }
            } else if (d.b(this.a) != null) {
                d.b(this.a).gtResult(false, str2);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void gtCloseWindow() {
        c.a("gtCloseWindow");
        this.a.dismiss();
        if (d.b(this.a) != null) {
            d.b(this.a).closeGt();
        }
    }
}
