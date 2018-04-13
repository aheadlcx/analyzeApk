package com.d;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.CoopBean;
import cn.v6.sdk.sixrooms.coop.NotifyAppLoginCallBack;
import cn.v6.sdk.sixrooms.coop.NotifyAppLogoutCallBack;
import cn.v6.sdk.sixrooms.coop.SyncLoginCallBack;
import cn.v6.sdk.sixrooms.coop.SyncLogoutCallBack;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.hall.HostsFragment;
import cn.v6.sixrooms.utils.MD5Utils;
import com.budejie.www.bean.UserItem;
import com.sprite.ads.third.sixroom.SixRoomConstants;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.net.URLEncoder;
import java.util.HashMap;

public class a {
    private static HashMap<String, String> a = new a$1();
    private static HashMap<String, String> b = new a$2();

    public static void a(Context context) {
        String str;
        String str2;
        boolean z;
        String str3 = (String) a.get("xiaomi");
        String str4 = (String) b.get("xiaomi");
        if (TextUtils.isEmpty(str3)) {
            str = (String) a.get("other");
        } else {
            str = str3;
        }
        if (TextUtils.isEmpty(str4)) {
            str2 = (String) b.get("other");
        } else {
            str2 = str4;
        }
        V6Coop.getInstance().init(context, SixRoomConstants.SR_CoopSrc, str, str2, "4uJKRXVBAISIBUD%&#&@*EJIEB7#$%##5f");
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(context, "sixroom_close_game");
        V6Coop instance = V6Coop.getInstance();
        if (TextUtils.isEmpty(configParams) || configParams.contains("xiaomi")) {
            z = false;
        } else {
            z = true;
        }
        instance.setShowMiniGame(z);
        V6Coop.getInstance().openSdkCrashHandler(false);
    }

    public static void a(SyncLoginCallBack syncLoginCallBack, SyncLogoutCallBack syncLogoutCallBack, NotifyAppLoginCallBack notifyAppLoginCallBack, NotifyAppLogoutCallBack notifyAppLogoutCallBack) {
        V6Coop.getInstance().setSyncLoginCallBack(syncLoginCallBack).setSyncLogoutCallBack(syncLogoutCallBack).setNotifyAppLoginCallBack(notifyAppLoginCallBack).setNotifyAppLogoutCallBack(notifyAppLogoutCallBack);
    }

    public static void a(Activity activity, UserItem userItem) {
        CoopBean a = a(userItem);
        if (a != null) {
            V6Coop.getInstance().syncLoginStatus(activity, a, false);
        }
    }

    public static void a() {
        V6Coop.getInstance().syncLogoutStatus();
    }

    public static void a(Activity activity) {
        V6Coop.getInstance().gotoHall(activity);
    }

    public static void a(Activity activity, String str, String str2) {
        V6Coop.getInstance().goToRoom(activity, str, str2);
    }

    private static CoopBean a(UserItem userItem) {
        if (userItem == null) {
            return null;
        }
        CoopBean coopBean = new CoopBean();
        String id = userItem.getId();
        coopBean.setCoopUid(id);
        coopBean.setCoopNick(userItem.getName());
        String str = System.currentTimeMillis() + "";
        String mD5Str = MD5Utils.getMD5Str(id + str + "4uJKRXVBAISIBUD%&#&@*EJIEB7#$%##5f");
        id = userItem.getToken();
        if (!TextUtils.isEmpty(id)) {
            id = URLEncoder.encode(id);
        }
        coopBean.setTime(str);
        coopBean.setFlag(mD5Str);
        coopBean.setToken(id);
        if (!TextUtils.isEmpty(userItem.getProfile())) {
            coopBean.setUser_pic(URLEncoder.encode(userItem.getProfile()));
        }
        return coopBean;
    }

    public static HostsFragment b() {
        return HostsFragment.newInstance("");
    }

    public static void a(String str, String str2) {
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str)) {
            V6Coop.getInstance().syncUserInfo(str2, str);
        }
    }
}
