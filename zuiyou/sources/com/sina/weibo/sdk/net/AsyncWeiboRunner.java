package com.sina.weibo.sdk.net;

import android.content.Context;
import com.sina.weibo.sdk.cmd.WbAppActivator;
import com.sina.weibo.sdk.exception.WeiboException;

public class AsyncWeiboRunner {
    private Context mContext;

    public AsyncWeiboRunner(Context context) {
        this.mContext = context;
    }

    @Deprecated
    public void requestByThread(String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        new AsyncWeiboRunner$1(this, str, str2, weiboParameters, requestListener).start();
    }

    public String request(String str, WeiboParameters weiboParameters, String str2) throws WeiboException {
        WbAppActivator.getInstance(this.mContext, weiboParameters.getAppKey()).activateApp();
        return HttpManager.openUrl(this.mContext, str, str2, weiboParameters);
    }

    public void requestAsync(String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        WbAppActivator.getInstance(this.mContext, weiboParameters.getAppKey()).activateApp();
        new AsyncWeiboRunner$RequestRunner(this.mContext, str, weiboParameters, str2, requestListener).execute(new Void[1]);
    }
}
