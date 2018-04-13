package cn.v6.sdk.sixrooms.coop;

import android.util.Log;
import cn.v6.sdk.sixrooms.coop.CoopAliasChangeEngine.CallBack;

class V6Coop$10 implements CallBack {
    final /* synthetic */ V6Coop this$0;

    V6Coop$10(V6Coop v6Coop) {
        this.this$0 = v6Coop;
    }

    public void success() {
        Log.d(V6Coop.TAG, "chane Alias success");
        this.this$0.delayGetUserInfo();
    }

    public void failed(String str) {
        Log.d(V6Coop.TAG, "change Alias failed");
    }
}
