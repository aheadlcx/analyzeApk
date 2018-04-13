package com.sina.weibo.sdk.web;

import android.text.TextUtils;
import com.sina.weibo.sdk.auth.WbAuthListener;
import java.util.HashMap;
import java.util.Map;

public class WeiboCallbackManager {
    private static WeiboCallbackManager a;
    private Map<String, WbAuthListener> b = new HashMap();

    private WeiboCallbackManager() {
    }

    public static synchronized WeiboCallbackManager getInstance() {
        WeiboCallbackManager weiboCallbackManager;
        synchronized (WeiboCallbackManager.class) {
            if (a == null) {
                a = new WeiboCallbackManager();
            }
            weiboCallbackManager = a;
        }
        return weiboCallbackManager;
    }

    public synchronized WbAuthListener getWeiboAuthListener(String str) {
        WbAuthListener wbAuthListener;
        if (TextUtils.isEmpty(str)) {
            wbAuthListener = null;
        } else {
            wbAuthListener = (WbAuthListener) this.b.get(str);
        }
        return wbAuthListener;
    }

    public synchronized void setWeiboAuthListener(String str, WbAuthListener wbAuthListener) {
        if (!(TextUtils.isEmpty(str) || wbAuthListener == null)) {
            this.b.put(str, wbAuthListener);
        }
    }

    public synchronized void removeWeiboAuthListener(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b.remove(str);
        }
    }

    public String genCallbackKey() {
        return String.valueOf(System.currentTimeMillis());
    }
}
