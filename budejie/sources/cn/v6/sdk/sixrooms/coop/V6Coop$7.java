package cn.v6.sdk.sixrooms.coop;

import android.text.TextUtils;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.utils.AppCount;
import cn.v6.sixrooms.utils.GlobleValue;

class V6Coop$7 implements CallBack {
    final /* synthetic */ V6Coop this$0;
    final /* synthetic */ SyncLoginCallBack val$callBack;
    final /* synthetic */ String val$op;

    V6Coop$7(V6Coop v6Coop, SyncLoginCallBack syncLoginCallBack, String str) {
        this.this$0 = v6Coop;
        this.val$callBack = syncLoginCallBack;
        this.val$op = str;
    }

    public void handleInfo(UserBean userBean) {
        if (userBean != null) {
            GlobleValue.setUserBean(userBean);
            this.this$0.dismissLoadingActivity();
            this.this$0.pushLoginMsg();
            if (this.val$callBack != null) {
                this.val$callBack.syncLoginSuccess();
            }
            if (!TextUtils.isEmpty(this.val$op)) {
                AppCount.sendAppCountInfo(this.val$op);
                return;
            }
            return;
        }
        this.this$0.clearLoginData(V6Coop.access$400(this.this$0));
        this.this$0.dismissLoadingActivity();
        if (this.val$callBack != null) {
            this.val$callBack.syncLoginFailed("6666", V6Coop.ERROR);
        }
    }

    public void error(int i) {
        this.this$0.dismissLoadingActivity();
        this.this$0.clearLoginData(V6Coop.access$400(this.this$0));
        if (i == 1006 && this.val$callBack != null) {
            this.val$callBack.syncLoginFailed("1006", V6Coop.NET_ERROR);
        }
        if (i == 1007 && this.val$callBack != null) {
            this.val$callBack.syncLoginFailed("1007", V6Coop.PARSE_JSON_ERROR);
        }
    }

    public void handleErrorInfo(String str, String str2) {
        this.this$0.dismissLoadingActivity();
        this.this$0.clearLoginData(V6Coop.access$400(this.this$0));
        if (this.val$callBack != null) {
            this.val$callBack.syncLoginFailed(str, str2);
        }
    }
}
