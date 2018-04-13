package cn.v6.sdk.sixrooms.coop;

import cn.v6.sixrooms.encrypt.MyEncrypt;
import cn.v6.sixrooms.engine.CoopEncyptEngine.CallBack;

class V6Coop$2 implements CallBack {
    final /* synthetic */ V6Coop this$0;
    final /* synthetic */ CoopBean val$coopbean;

    V6Coop$2(V6Coop v6Coop, CoopBean coopBean) {
        this.this$0 = v6Coop;
        this.val$coopbean = coopBean;
    }

    public void success(String str, String str2) {
        if (MyEncrypt.instance().init(V6Coop.mPackageName, V6Coop.mCoop, V6Coop.mLoginKey, str2, str)) {
            V6Coop.access$300(this.this$0, V6Coop.mCoop, this.val$coopbean, V6Coop.access$200(this.this$0));
            return;
        }
        this.this$0.dismissLoadingActivity();
        this.this$0.clearLoginData(V6Coop.access$400(this.this$0));
        if (V6Coop.access$200(this.this$0) != null) {
            V6Coop.access$200(this.this$0).syncLoginFailed("1017", V6Coop.ILLAGE_COOP);
        }
    }

    public void error(int i) {
        this.this$0.clearLoginData(V6Coop.access$400(this.this$0));
        this.this$0.dismissLoadingActivity();
        if (i == 1006 && V6Coop.access$200(this.this$0) != null) {
            V6Coop.access$200(this.this$0).syncLoginFailed("1006", V6Coop.NET_ERROR);
        }
        if (i == 1007 && V6Coop.access$200(this.this$0) != null) {
            V6Coop.access$200(this.this$0).syncLoginFailed("1007", V6Coop.PARSE_JSON_ERROR);
        }
    }
}
