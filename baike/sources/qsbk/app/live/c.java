package qsbk.app.live;

import android.app.Activity;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.model.User;
import qsbk.app.core.provider.UserInfoProvider;

class c extends UserInfoProvider {
    final /* synthetic */ QsbkLiveApp a;

    c(QsbkLiveApp qsbkLiveApp) {
        this.a = qsbkLiveApp;
    }

    public User getUser() {
        User user = new User();
        user.id = 9607284307841025L;
        user.origin = 2;
        user.origin_id = 9607284307841025L;
        user.avatar = "http://avatar.app-remix.com/KT7F4R5VTE0FP5ZD.jpg?imageMogr2/format/jpg/thumbnail/300x300/auto-orient";
        user.name = "太阳的一公升眼泪";
        return user;
    }

    public void setToken(String str) {
    }

    public String getToken() {
        return "4e51c3e1cfe4ebc52b52fca01f545b52";
    }

    public void setUserSig(String str) {
    }

    public String getUserSig() {
        return null;
    }

    public void setCertificate(long j) {
    }

    public long getCertificate() {
        return 0;
    }

    public long getBalance() {
        return 0;
    }

    public void setBalance(long j) {
    }

    public void setUser(User user) {
    }

    public boolean isLogin() {
        return true;
    }

    public void toLogin(Activity activity, int i) {
    }

    public void onLogout(String str) {
    }

    public void toUserPage(Activity activity, User user) {
    }

    public void toPay(Activity activity, int i) {
    }

    public void toShare(Activity activity, CommonVideo commonVideo, String str, int i) {
    }

    public void toMain(Activity activity) {
    }

    public void setLevel(int i) {
    }

    public int getLevel() {
        return 0;
    }

    public void toMobileBind(Activity activity) {
    }

    public void shareImage(Activity activity, String str, int i) {
    }
}
