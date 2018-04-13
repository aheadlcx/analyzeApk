package com.sina.weibo.sdk.net.openapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.tencent.connect.common.Constants;
import com.umeng.analytics.b.g;

public class ShareWeiboApi {
    private static final String REPOST_URL = "https://api.weibo.com/2/statuses/repost.json";
    private static final String TAG = ShareWeiboApi.class.getName();
    private static final String UPDATE_URL = "https://api.weibo.com/2/statuses/update.json";
    private static final String UPLOAD_URL = "https://api.weibo.com/2/statuses/upload.json";
    private String mAccessToken;
    private String mAppKey;
    private Context mContext;

    private ShareWeiboApi(Context context, String str, String str2) {
        this.mContext = context.getApplicationContext();
        this.mAppKey = str;
        this.mAccessToken = str2;
    }

    public static ShareWeiboApi create(Context context, String str, String str2) {
        return new ShareWeiboApi(context, str, str2);
    }

    public void update(String str, String str2, String str3, RequestListener requestListener) {
        requestAsync(UPDATE_URL, buildUpdateParams(str, str2, str3), Constants.HTTP_POST, requestListener);
    }

    public void upload(String str, Bitmap bitmap, String str2, String str3, RequestListener requestListener) {
        WeiboParameters buildUpdateParams = buildUpdateParams(str, str2, str3);
        buildUpdateParams.put("pic", bitmap);
        requestAsync(UPLOAD_URL, buildUpdateParams, Constants.HTTP_POST, requestListener);
    }

    public void repost(String str, String str2, int i, RequestListener requestListener) {
        WeiboParameters buildUpdateParams = buildUpdateParams(str2, null, null);
        buildUpdateParams.put("id", str);
        buildUpdateParams.put("is_comment", String.valueOf(i));
        requestAsync(REPOST_URL, buildUpdateParams, Constants.HTTP_POST, requestListener);
    }

    private void requestAsync(String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        if (TextUtils.isEmpty(this.mAccessToken) || TextUtils.isEmpty(str) || weiboParameters == null || TextUtils.isEmpty(str2) || requestListener == null) {
            LogUtil.e(TAG, "Argument error!");
            return;
        }
        weiboParameters.put("access_token", this.mAccessToken);
        new AsyncWeiboRunner(this.mContext).requestAsync(str, weiboParameters, str2, requestListener);
    }

    private WeiboParameters buildUpdateParams(String str, String str2, String str3) {
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppKey);
        weiboParameters.put(NotificationCompat.CATEGORY_STATUS, str);
        if (!TextUtils.isEmpty(str3)) {
            weiboParameters.put("long", str3);
        }
        if (!TextUtils.isEmpty(str2)) {
            weiboParameters.put(g.ae, str2);
        }
        if (!TextUtils.isEmpty(this.mAppKey)) {
            weiboParameters.put("source", this.mAppKey);
        }
        return weiboParameters;
    }
}
