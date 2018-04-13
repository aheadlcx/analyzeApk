package cn.v6.sixrooms.hall;

import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.AppUpdateEngine;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.utils.GlobleValue;

final class l implements CallBack {
    final /* synthetic */ String a;
    final /* synthetic */ HallActivity b;

    l(HallActivity hallActivity, String str) {
        this.b = hallActivity;
        this.a = str;
    }

    public final void handleInfo(UserBean userBean) {
        if (userBean != null) {
            GlobleValue.setUserBean(userBean);
            if (V6Coop.getInstance().getNotifyV6LoginCallBack() != null) {
                V6Coop.getInstance().getNotifyV6LoginCallBack().v6LoginSuccess();
            }
            V6Coop.getInstance().pushLoginMsg();
            AppUpdateEngine appUpdateEngine = new AppUpdateEngine(null);
            if (!TextUtils.isEmpty(this.a)) {
                appUpdateEngine.update(this.a, "3");
            }
        }
    }

    public final void error(int i) {
        this.b.showErrorToast(i);
        if (V6Coop.getInstance().getNotifyV6LoginCallBack() != null) {
            if (i == 1006) {
                V6Coop.getInstance().getNotifyV6LoginCallBack().v6LoingFailed(String.valueOf(i), V6Coop.NET_ERROR);
            } else {
                V6Coop.getInstance().getNotifyV6LoginCallBack().v6LoingFailed(String.valueOf(i), V6Coop.PARSE_JSON_ERROR);
            }
        }
        this.b.logout();
        V6Coop.getInstance().pushLogoutMsg();
    }

    public final void handleErrorInfo(String str, String str2) {
        this.b.handleErrorResult(str, str2, this.b.getParent());
        if (V6Coop.getInstance().getNotifyV6LoginCallBack() != null) {
            V6Coop.getInstance().getNotifyV6LoginCallBack().v6LoingFailed(str, str2);
        }
        this.b.logout();
        V6Coop.getInstance().pushLogoutMsg();
    }
}
