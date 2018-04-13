package com.tencent.weibo.sdk.android.component.sso;

public interface OnAuthListener {
    void onAuthFail(int i, String str);

    void onAuthPassed(String str, WeiboToken weiboToken);

    void onWeiBoNotInstalled();

    void onWeiboVersionMisMatch();
}
