package cn.v6.sdk.sixrooms.coop;

import cn.v6.sixrooms.engine.PartnerLoginEngine.CallBack;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

class V6Coop$6 implements CallBack {
    final /* synthetic */ V6Coop this$0;
    final /* synthetic */ SyncLoginCallBack val$callBack;

    V6Coop$6(V6Coop v6Coop, SyncLoginCallBack syncLoginCallBack) {
        this.this$0 = v6Coop;
        this.val$callBack = syncLoginCallBack;
    }

    public void loginSuccess(String str, String str2) {
        SaveUserInfoUtils.saveEncpass(V6Coop.access$400(this.this$0), str2);
        V6Coop.access$500(this.this$0, str, this.val$callBack);
    }

    public void handleErrorInfo(String str, String str2) {
        this.this$0.clearLoginData(V6Coop.access$400(this.this$0));
        this.this$0.dismissLoadingActivity();
        if (this.val$callBack != null) {
            this.val$callBack.syncLoginFailed(str, str2);
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

    public void bundlePhone(String str, String str2) {
        this.this$0.dismissLoadingActivity();
        this.this$0.clearLoginData(V6Coop.access$400(this.this$0));
        if (this.val$callBack != null) {
            this.val$callBack.syncLoginFailed("1006", "网络连接异常1");
        }
    }
}
