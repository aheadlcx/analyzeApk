package cn.v6.sdk.sixrooms.coop;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import cn.v6.sixrooms.encrypt.MyEncrypt;
import cn.v6.sixrooms.engine.CoopEncyptEngine.CallBack;
import cn.v6.sixrooms.ui.phone.UserManagerActivity;

class V6Coop$8 implements CallBack {
    final /* synthetic */ V6Coop this$0;
    final /* synthetic */ Activity val$activity;

    V6Coop$8(V6Coop v6Coop, Activity activity) {
        this.this$0 = v6Coop;
        this.val$activity = activity;
    }

    public void success(String str, String str2) {
        boolean init = MyEncrypt.instance().init(V6Coop.mPackageName, V6Coop.mCoop, V6Coop.mLoginKey, str2, str);
        V6Coop.access$600(this.this$0);
        if (init) {
            V6Coop.access$702(this.this$0, true);
            if (this.val$activity != null) {
                this.val$activity.startActivity(new Intent(this.val$activity, UserManagerActivity.class));
                return;
            }
            return;
        }
        Log.e(V6Coop.TAG, "权限不足");
        if (V6Coop.access$800(this.this$0) != null) {
            V6Coop.access$800(this.this$0).v6LoingFailed("1017", V6Coop.ILLAGE_COOP);
        }
    }

    public void error(int i) {
        Log.e(V6Coop.TAG, "errorCode = " + i);
        V6Coop.access$600(this.this$0);
        if (i == 1006 && V6Coop.access$800(this.this$0) != null) {
            V6Coop.access$800(this.this$0).v6LoingFailed("1006", V6Coop.NET_ERROR);
        }
        if (i == 1007 && V6Coop.access$800(this.this$0) != null) {
            V6Coop.access$800(this.this$0).v6LoingFailed("1007", V6Coop.PARSE_JSON_ERROR);
        }
        if (i == 1017 && V6Coop.access$800(this.this$0) != null) {
            V6Coop.access$800(this.this$0).v6LoingFailed("1017", V6Coop.ILLAGE_COOP);
        }
    }
}
