package com.sina.weibo.sdk.net;

import android.content.Context;
import com.sina.weibo.sdk.exception.WeiboException;

public class AsyncWeiboRunner {
    private Context a;

    public AsyncWeiboRunner(Context context) {
        this.a = context;
    }

    @Deprecated
    public void requestByThread(String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        new a(this, str, str2, weiboParameters, requestListener).start();
    }

    public void request4RdirectURL(String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        new b(this, str, str2, weiboParameters, requestListener).start();
    }

    public String request(String str, WeiboParameters weiboParameters, String str2) throws WeiboException {
        a(this.a, weiboParameters.getAppKey());
        return HttpManager.openUrl(this.a, str, str2, weiboParameters);
    }

    public String request(String str, boolean z, String str2, String str3, WeiboParameters weiboParameters, String str4) throws WeiboException {
        if (!z) {
            return request(str3, weiboParameters, str4);
        }
        weiboParameters.put("source", str);
        weiboParameters.setAnonymousCookie(str2);
        return request(str3, weiboParameters, str4);
    }

    public void requestAsync(String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        a(this.a, weiboParameters.getAppKey());
        new AsyncWeiboRunner$RequestRunner(this.a, str, weiboParameters, str2, requestListener).execute(new Void[]{null});
    }

    public void requestAsync(String str, boolean z, String str2, String str3, WeiboParameters weiboParameters, String str4, RequestListener requestListener) {
        if (z) {
            weiboParameters.put("source", str);
            weiboParameters.setAnonymousCookie(str2);
            requestAsync(str3, weiboParameters, str4, requestListener);
            return;
        }
        requestAsync(str3, weiboParameters, str4, requestListener);
    }

    private void a(Context context, String str) {
        try {
            Class.forName("com.sina.weibo.sdk.cmd.WbAppActivator").getMethod("getInstance", new Class[]{Context.class, String.class}).invoke(null, new Object[]{context, str}).getClass().getMethod("activateApp", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
        }
    }
}
