package com.spriteapp.booklibrary.util;

import com.spriteapp.booklibrary.b.c;

public class WebViewUtil {
    private static final String TAG = "WebViewUtil";
    private static WebViewUtil mWebViewUtil;
    private c mWebViewCallback;

    public static WebViewUtil getInstance() {
        if (mWebViewUtil == null) {
            synchronized (WebViewUtil.class) {
                if (mWebViewUtil == null) {
                    mWebViewUtil = new WebViewUtil();
                }
            }
        }
        return mWebViewUtil;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getJumpUrl(android.content.Context r7, java.lang.String r8) {
        /*
        r6 = this;
        r1 = -1;
        r0 = 0;
        r2 = "";
        r2 = com.spriteapp.booklibrary.util.StringUtil.isEmpty(r8);
        if (r2 == 0) goto L_0x000b;
    L_0x000a:
        return r0;
    L_0x000b:
        r3 = android.net.Uri.parse(r8);
        r4 = r3.getScheme();
        r2 = com.spriteapp.booklibrary.util.StringUtil.isEmpty(r4);
        if (r2 != 0) goto L_0x000a;
    L_0x0019:
        r2 = r4.hashCode();
        switch(r2) {
            case 99628773: goto L_0x002b;
            default: goto L_0x0020;
        };
    L_0x0020:
        r2 = r1;
    L_0x0021:
        switch(r2) {
            case 0: goto L_0x0035;
            default: goto L_0x0024;
        };
    L_0x0024:
        r0 = "huaxi";
        r0 = r0.equals(r4);
        goto L_0x000a;
    L_0x002b:
        r2 = "huaxi";
        r2 = r4.equals(r2);
        if (r2 == 0) goto L_0x0020;
    L_0x0033:
        r2 = r0;
        goto L_0x0021;
    L_0x0035:
        r2 = "action";
        r2 = r3.getQueryParameter(r2);
        r5 = com.spriteapp.booklibrary.util.StringUtil.isEmpty(r2);
        if (r5 != 0) goto L_0x000a;
    L_0x0041:
        r5 = r2.hashCode();
        switch(r5) {
            case -2025268031: goto L_0x0074;
            case -503819303: goto L_0x0057;
            case 110760: goto L_0x007e;
            case 103149417: goto L_0x0092;
            case 343178508: goto L_0x006a;
            case 1985941072: goto L_0x0088;
            case 2024751404: goto L_0x0060;
            default: goto L_0x0048;
        };
    L_0x0048:
        r0 = r1;
    L_0x0049:
        switch(r0) {
            case 0: goto L_0x004d;
            case 1: goto L_0x009c;
            case 2: goto L_0x00bb;
            case 3: goto L_0x00d4;
            case 4: goto L_0x00e3;
            case 5: goto L_0x00f4;
            case 6: goto L_0x00f9;
            default: goto L_0x004c;
        };
    L_0x004c:
        goto L_0x0024;
    L_0x004d:
        r0 = "url";
        r0 = r3.getQueryParameter(r0);
        com.spriteapp.booklibrary.util.ActivityUtil.toWebViewActivity(r7, r0);
        goto L_0x0024;
    L_0x0057:
        r5 = "openpage";
        r2 = r2.equals(r5);
        if (r2 == 0) goto L_0x0048;
    L_0x005f:
        goto L_0x0049;
    L_0x0060:
        r0 = "book_read";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x0048;
    L_0x0068:
        r0 = 1;
        goto L_0x0049;
    L_0x006a:
        r0 = "add_shelf";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x0048;
    L_0x0072:
        r0 = 2;
        goto L_0x0049;
    L_0x0074:
        r0 = "add_comment";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x0048;
    L_0x007c:
        r0 = 3;
        goto L_0x0049;
    L_0x007e:
        r0 = "pay";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x0048;
    L_0x0086:
        r0 = 4;
        goto L_0x0049;
    L_0x0088:
        r0 = "setting";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x0048;
    L_0x0090:
        r0 = 5;
        goto L_0x0049;
    L_0x0092:
        r0 = "login";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x0048;
    L_0x009a:
        r0 = 6;
        goto L_0x0049;
    L_0x009c:
        r0 = "book_id";
        r0 = r3.getQueryParameter(r0);
        r1 = "chapter_id";
        r1 = r3.getQueryParameter(r1);
        r2 = r6.mWebViewCallback;
        if (r2 == 0) goto L_0x0024;
    L_0x00ac:
        r2 = r6.mWebViewCallback;
        r0 = java.lang.Integer.parseInt(r0);
        r1 = java.lang.Integer.parseInt(r1);
        r2.freeRead(r0, r1);
        goto L_0x0024;
    L_0x00bb:
        r0 = "book_id";
        r0 = r3.getQueryParameter(r0);
        r1 = r6.mWebViewCallback;
        if (r1 == 0) goto L_0x0024;
    L_0x00c5:
        r1 = r6.mWebViewCallback;
        r0 = java.lang.Integer.valueOf(r0);
        r0 = r0.intValue();
        r1.addBookToShelf(r0);
        goto L_0x0024;
    L_0x00d4:
        r0 = "book_id";
        r0 = r3.getQueryParameter(r0);
        r0 = java.lang.Integer.parseInt(r0);
        com.spriteapp.booklibrary.util.ActivityUtil.toPublishCommentActivity(r7, r0);
        goto L_0x0024;
    L_0x00e3:
        r0 = "product_id";
        r0 = r3.getQueryParameter(r0);
        r1 = r6.mWebViewCallback;
        if (r1 == 0) goto L_0x0024;
    L_0x00ed:
        r1 = r6.mWebViewCallback;
        r1.getAliPay(r0);
        goto L_0x0024;
    L_0x00f4:
        com.spriteapp.booklibrary.util.ActivityUtil.toSettingActivity(r7);
        goto L_0x0024;
    L_0x00f9:
        r0 = com.spriteapp.booklibrary.config.HuaXiSDK.getInstance();
        r0.toLoginPage(r7);
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spriteapp.booklibrary.util.WebViewUtil.getJumpUrl(android.content.Context, java.lang.String):boolean");
    }

    public void setWebViewCallback(c cVar) {
        this.mWebViewCallback = cVar;
    }
}
