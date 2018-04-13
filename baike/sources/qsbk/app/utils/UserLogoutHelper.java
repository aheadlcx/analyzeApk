package qsbk.app.utils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.NotificationManager;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.publish.PublishActivity;
import qsbk.app.fragments.MyProfileFragment;
import qsbk.app.fragments.QiuyouCircleFragment;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.im.MessageCountManager;
import qsbk.app.im.QiushiNotificationCountManager;
import qsbk.app.im.QiuyouquanNotificationCountManager;
import qsbk.app.im.UserChatManager;

public class UserLogoutHelper {
    private Activity a;
    private OnClickListener b;

    public UserLogoutHelper(Activity activity) {
        this.a = activity;
    }

    public void setOnLogoutFinish(OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    public void logoutAlert() {
        if (this.a == null) {
            LogUtil.e("activity is null.");
        } else {
            new Builder(this.a).setTitle("温馨提示").setMessage("确定要退出登录吗？").setPositiveButton("确定", new bm(this)).setNegativeButton("取消", new bl(this)).show();
        }
    }

    public void doLogout() {
        SharePreferenceUtils.remove("loginUser");
        SharePreferenceUtils.remove("loginName");
        SharePreferenceUtils.remove("password");
        SharePreferenceUtils.remove(MessageCountManager.NEWFANS_COUNT);
        SharePreferenceUtils.remove("has_click_my_profile_fragmentn");
        SharePreferenceUtils.remove(PublishActivity.KEY_PUBLISH_ARTICLE);
        MyProfileFragment.newFans = 0;
        if (QsbkApp.currentUser != null) {
            QiushiNotificationCountManager.getInstance(QsbkApp.currentUser.userId).destroy();
            QiuyouquanNotificationCountManager.getInstance(QsbkApp.currentUser.userId).destroy();
        }
        ((NotificationManager) QsbkApp.getInstance().getSystemService("notification")).cancelAll();
        SharePreferenceUtils.remove(QiuyouCircleFragment.SIGN_TIME + (QsbkApp.currentUser != null ? QsbkApp.currentUser.userId : null));
        RemarkManager.REMARKS.clear();
        RemarkManager.deleteFile();
        QsbkApp.currentUser = null;
        SharePreferenceUtils.setSharePreferencesValue("token", "nologin");
        a();
        IMNotifyManager.instance().onUserLogout();
        if (UserChatManager.currentChatManager != null) {
            UserChatManager.currentChatManager.destroy(false);
        }
        Intent intent = new Intent();
        intent.setAction(MainActivity.ACTION_QB_LOGOUT);
        LocalBroadcastManager.getInstance(this.a).sendBroadcast(intent);
    }

    private void a() {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.PUSH_DOMAINS, null);
        Map hashMap = new HashMap();
        hashMap.put("action", "logout");
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.executeOnExecutor(SimpleHttpTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
