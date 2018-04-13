package cn.v6.sdk.sixrooms.coop;

import cn.v6.sixrooms.engine.GetCoopTypeEngine.CallBack;
import cn.v6.sixrooms.utils.HandleErrorUtils;

class V6Coop$9 implements CallBack {
    final /* synthetic */ V6Coop this$0;
    final /* synthetic */ V6Coop$GetCoopTypeListener val$getCoopTypeListener;

    V6Coop$9(V6Coop v6Coop, V6Coop$GetCoopTypeListener v6Coop$GetCoopTypeListener) {
        this.this$0 = v6Coop;
        this.val$getCoopTypeListener = v6Coop$GetCoopTypeListener;
    }

    public void success(String str, String str2) {
        this.this$0.setCoopType(str, str2);
        if (this.val$getCoopTypeListener != null) {
            this.val$getCoopTypeListener.getCoopTypeSuccess();
        }
    }

    public void handleErrorInfo(String str, String str2) {
        if (this.val$getCoopTypeListener != null) {
            this.val$getCoopTypeListener.getCoopTypeFailed(str2);
        }
    }

    public void error(int i) {
        if (this.val$getCoopTypeListener != null) {
            this.val$getCoopTypeListener.getCoopTypeFailed(HandleErrorUtils.getError(i));
        }
    }
}
