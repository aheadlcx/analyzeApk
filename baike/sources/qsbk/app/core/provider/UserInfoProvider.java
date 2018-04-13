package qsbk.app.core.provider;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.View;
import java.util.Map;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.Constants;
import qsbk.app.core.web.plugin.embed.JumpPlugin;
import qsbk.app.live.LivePullLauncher;

public abstract class UserInfoProvider {
    public abstract long getBalance();

    public abstract long getCertificate();

    public abstract String getToken();

    public abstract User getUser();

    public abstract String getUserSig();

    public abstract boolean isLogin();

    public abstract void onLogout(String str);

    public abstract void setBalance(long j);

    public abstract void setCertificate(long j);

    public abstract void setToken(String str);

    public abstract void setUser(User user);

    public abstract void setUserSig(String str);

    public abstract void shareImage(Activity activity, String str, int i);

    public abstract void toLogin(Activity activity, int i);

    public abstract void toMain(Activity activity);

    public abstract void toShare(Activity activity, CommonVideo commonVideo, String str, int i);

    public abstract void toUserPage(Activity activity, User user);

    public long getUserOrigin() {
        User user = getUser();
        if (user != null) {
            return user.getOrigin();
        }
        return (long) Constants.SOURCE;
    }

    public long getUserPlatformId() {
        User user = getUser();
        if (user != null) {
            return user.getPlatformId();
        }
        return 0;
    }

    public long getUserId() {
        User user = getUser();
        if (user != null) {
            return user.getOriginId();
        }
        return 0;
    }

    public long getPlatformId() {
        User user = getUser();
        if (user != null) {
            return user.getPlatformId();
        }
        return 0;
    }

    public String getUserName() {
        User user = getUser();
        if (user != null) {
            return user.name;
        }
        return "";
    }

    public void toPay(Activity activity, int i) {
        if (activity != null && !activity.isFinishing() && !AppUtils.isFastDoubleClick()) {
            Intent intent = new Intent();
            intent.setClassName(activity, "qsbk.app.pay.ui.PayActivity");
            activity.startActivityForResult(intent, i);
        }
    }

    public void toLiveRank(Activity activity) {
        if (activity != null && !activity.isFinishing() && !AppUtils.isFastDoubleClick()) {
            Intent intent = new Intent();
            intent.setClassName(activity, "qsbk.app.live.ui.LiveRankActivity");
            activity.startActivity(intent);
        }
    }

    public void toUserGiftRank(Activity activity, User user, boolean z, long j) {
        if (activity != null && !activity.isFinishing() && !AppUtils.isFastDoubleClick() && user != null) {
            Intent intent = new Intent();
            intent.setClassName(activity, "qsbk.app.live.ui.GiftRankActivity");
            intent.putExtra("user", user);
            intent.putExtra("is_anchor", z);
            intent.putExtra("total", j);
            activity.startActivity(intent);
        }
    }

    public void toUserLevel(Activity activity) {
        if (activity != null && !activity.isFinishing() && !AppUtils.isFastDoubleClick()) {
            Intent intent = new Intent();
            intent.setClassName(activity, "qsbk.app.live.ui.LiveUserLevelActivity");
            activity.startActivity(intent);
        }
    }

    public void toShare(Activity activity, CommonVideo commonVideo) {
        toShare(activity, commonVideo, 0);
    }

    public void toShare(Activity activity, CommonVideo commonVideo, int i) {
        toShare(activity, commonVideo, "live", i);
    }

    public void toShare(Activity activity, CommonVideo commonVideo, String str) {
        toShare(activity, commonVideo, str, 0);
    }

    public void setLevel(int i) {
        User user = getUser();
        if (user != null) {
            user.level = i;
            setUser(user);
        }
    }

    public int getLevel() {
        User user = getUser();
        if (user != null) {
            return user.level;
        }
        return 0;
    }

    public void networkRequest(String str, Map<String, String> map, Callback callback) {
        if ("recommendLive".equals(str)) {
            NetRequest.getInstance().get(UrlConstants.GET_LIVE_LIST, callback);
        } else if ("liveDetail".equals(str)) {
            NetRequest.getInstance().get(UrlConstants.GET_LIVE, callback);
        }
    }

    public void toLive(Activity activity, String str, long j) {
        toLive(activity, str, null, j);
    }

    public void toLive(Activity activity, String str, String str2, long j) {
        Intent intent = new Intent();
        intent.setClassName(activity, "qsbk.app.live.ui.LivePullActivity");
        intent.putExtra("liveUserId", str);
        intent.putExtra("livePlatformId", str2);
        intent.putExtra("liveUserSource", j);
        activity.startActivity(intent);
    }

    public void toLive(Activity activity, CommonVideo commonVideo) {
        toLive(activity, commonVideo, 0);
    }

    public void toLive(Activity activity, CommonVideo commonVideo, int i) {
        toLive(activity, commonVideo, null, 0, i);
    }

    public void toLive(Activity activity, CommonVideo commonVideo, String str, int i, int i2) {
        toLive(activity, commonVideo, null, str, i, i2);
    }

    public void toLive(Activity activity, CommonVideo commonVideo, View view, String str, int i, int i2) {
        if (!AppUtils.isFastDoubleClick()) {
            Intent intent = new Intent();
            intent.setClassName(activity, "qsbk.app.live.ui.LivePullActivity");
            intent.putExtra("live", commonVideo);
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra(LivePullLauncher.STSOURCE, str);
                intent.putExtra("tapIndex", i);
            }
            if (view == null) {
                activity.startActivityForResult(intent, i2);
            } else {
                ActivityCompat.startActivityForResult(activity, intent, i2, ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0).toBundle());
            }
        }
    }

    public void toPickImage(Activity activity, int i, int i2) {
    }

    public void toConversation(Activity activity, String str, String str2) {
    }

    public void toMobileBind(Activity activity) {
    }

    public void toJump(Activity activity, String str) {
        if (activity != null && !activity.isFinishing() && !AppUtils.isFastDoubleClick() && TextUtils.equals(JumpPlugin.ACTION_TO_WITHDRAW, str)) {
            Intent intent = new Intent();
            intent.setClassName(activity, "qsbk.app.pay.ui.WithdrawActivity");
            activity.startActivity(intent);
        }
    }

    public String getTaburl(int i) {
        return ConfigInfoUtil.instance().getTaburl(i);
    }
}
