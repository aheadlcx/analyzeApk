package com.tencent.connect.avatar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.SocialConstants;
import com.tencent.open.b.d;
import com.tencent.open.utils.Global;
import com.tencent.tauth.IUiListener;

public class QQAvatar extends BaseApi {
    private IUiListener a;

    public QQAvatar(QQToken qQToken) {
        super(qQToken);
    }

    private Intent a(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ImageActivity.class);
        return intent;
    }

    public void setAvatar(Activity activity, Uri uri, IUiListener iUiListener, int i) {
        if (this.a != null) {
            this.a.onCancel();
        }
        this.a = iUiListener;
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_AVATAR_URI, uri.toString());
        bundle.putInt("exitAnim", i);
        bundle.putString("appid", this.mToken.getAppId());
        bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.mToken.getAccessToken());
        bundle.putLong(Constants.PARAM_EXPIRES_IN, this.mToken.getExpireTimeInSecond());
        bundle.putString("openid", this.mToken.getOpenId());
        Intent a = a(activity);
        if (hasActivityForIntent(a)) {
            a(activity, bundle, a);
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SET_AVATAR, "12", "18", "0");
            return;
        }
        d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SET_AVATAR, "12", "18", "1");
    }

    private void a(Activity activity, Bundle bundle, Intent intent) {
        a(bundle);
        intent.putExtra(Constants.KEY_ACTION, "action_avatar");
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_AVATER, this.a);
        startAssitActivity(activity, intent, (int) Constants.REQUEST_AVATER);
    }

    private void a(Bundle bundle) {
        if (this.mToken != null) {
            bundle.putString("appid", this.mToken.getAppId());
            if (this.mToken.isSessionValid()) {
                bundle.putString(Constants.PARAM_KEY_STR, this.mToken.getAccessToken());
                bundle.putString(Constants.PARAM_KEY_TYPE, "0x80");
            }
            String openId = this.mToken.getOpenId();
            if (openId != null) {
                bundle.putString("hopenid", openId);
            }
            bundle.putString("platform", "androidqz");
            try {
                bundle.putString(Constants.PARAM_PLATFORM_ID, Global.getContext().getSharedPreferences(Constants.PREFERENCE_PF, 0).getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
            } catch (Exception e) {
                e.printStackTrace();
                bundle.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
            }
        }
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", "a");
    }
}
