package com.izuiyou.auth.sina;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import com.izuiyou.a.a.b;
import com.izuiyou.auth.a;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseRequest;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler.Response;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

public class c {
    private static c a;
    private static String b = "follow_app_official_microblog";
    private IWeiboShareAPI c = WeiboShareSDK.createWeiboAPI(a.a(), "4117400114");
    private SsoHandler d;

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    private c() {
        this.c.registerApp();
    }

    public void a(Intent intent, Response response) {
        this.c.handleWeiboResponse(intent, response);
    }

    public void a(Activity activity, WeiboAuthListener weiboAuthListener) {
        this.d = new SsoHandler(activity, new AuthInfo(activity, "4117400114", "http://sns.whalecloud.com/sina2/callback", b));
        this.d.authorize(weiboAuthListener);
    }

    public void a(int i, int i2, Intent intent) {
        if (this.d != null) {
            this.d.authorizeCallBack(i, i2, intent);
        }
    }

    public void a(Activity activity, String str, String str2) {
        a(activity, str, null, str2);
    }

    private void a(final Activity activity, String str, Bitmap bitmap, String str2) {
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        if (!TextUtils.isEmpty(str)) {
            weiboMultiMessage.textObject = a(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            weiboMultiMessage.imageObject = b(str2);
        } else if (bitmap != null) {
            weiboMultiMessage.imageObject = a(bitmap);
            bitmap.recycle();
        }
        BaseRequest sendMultiMessageToWeiboRequest = new SendMultiMessageToWeiboRequest();
        sendMultiMessageToWeiboRequest.transaction = String.valueOf(System.currentTimeMillis());
        sendMultiMessageToWeiboRequest.multiMessage = weiboMultiMessage;
        AuthInfo authInfo = new AuthInfo(activity, "4117400114", "http://sns.whalecloud.com/sina2/callback", "");
        Oauth2AccessToken a = a.a(activity.getApplicationContext());
        String str3 = "";
        if (a != null) {
            str3 = a.getToken();
        }
        this.c.sendRequest(activity, sendMultiMessageToWeiboRequest, authInfo, str3, new WeiboAuthListener(this) {
            final /* synthetic */ c b;

            public void onWeiboException(WeiboException weiboException) {
                b.b("onWeiboException");
            }

            public void onComplete(Bundle bundle) {
                b.e("微博分享成功onComplete");
                a.a(activity.getApplicationContext(), Oauth2AccessToken.parseAccessToken(bundle));
            }

            public void onCancel() {
                b.b("onCancel");
            }
        });
    }

    private TextObject a(String str) {
        TextObject textObject = new TextObject();
        textObject.text = str;
        return textObject;
    }

    private ImageObject b(String str) {
        ImageObject imageObject = new ImageObject();
        imageObject.imagePath = str;
        return imageObject;
    }

    private ImageObject a(Bitmap bitmap) {
        ImageObject imageObject = new ImageObject();
        imageObject.setImageObject(bitmap);
        return imageObject;
    }

    public void a(BaseResponse baseResponse) {
        switch (baseResponse.errCode) {
            case 0:
                cn.htjyb.ui.widget.a.a(a.a(), "分享成功", 0).show();
                return;
            case 1:
                cn.htjyb.ui.widget.a.a(a.a(), "取消分享", 0).show();
                return;
            case 2:
                cn.htjyb.ui.widget.a.a(a.a(), "分享失败", 0).show();
                return;
            default:
                return;
        }
    }
}
