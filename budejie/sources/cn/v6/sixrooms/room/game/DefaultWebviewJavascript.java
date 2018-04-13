package cn.v6.sixrooms.room.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.fragment.RoomBaseFragment;
import cn.v6.sixrooms.utils.HandleErrorUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

public class DefaultWebviewJavascript {
    private Activity mActivity;

    public DefaultWebviewJavascript(Context context) {
        this.mActivity = (Activity) context;
    }

    @JavascriptInterface
    public void appLogin() {
        LogUtils.e("DefaultWebviewJavascript", "需要登录 " + (Looper.getMainLooper() == Looper.myLooper()));
        HandleErrorUtils.showLoginDialog(this.mActivity);
    }

    @JavascriptInterface
    public void appEnterRoom(String str, String str2) {
        Intent intent = new Intent(this.mActivity, RoomActivity.class);
        intent.putExtra("rid", str2);
        intent.putExtra(RoomBaseFragment.RUID_KEY, str);
        this.mActivity.startActivity(intent);
    }

    @JavascriptInterface
    public void appEnterUserInfo(String str) {
    }

    @JavascriptInterface
    public String getLoginUid() {
        return LoginUtils.getLoginUID();
    }

    @JavascriptInterface
    public String getEncpass() {
        return SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext());
    }

    @JavascriptInterface
    public void gotoHall() {
        this.mActivity.finish();
    }

    @JavascriptInterface
    public void finish() {
        this.mActivity.finish();
    }
}
