package cn.tatagou.sdk.android;

import android.util.Log;
import cn.tatagou.sdk.util.i;
import com.ali.auth.third.core.MemberSDK;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;

class TtgSDK$2 implements AlibcTradeInitCallback {
    final /* synthetic */ TtgCallback val$ttgCallback;

    TtgSDK$2(TtgCallback ttgCallback) {
        this.val$ttgCallback = ttgCallback;
    }

    public void onSuccess() {
        TtgSDK.sBcInitFlag = 1;
        if (TtgSDK.isDebug) {
            Log.d(TtgSDK.access$000(), "AlibcTradeSDK: 初始化成功");
            i.getInstance().writeFile("AlibcTradeSDK: 初始化成功 \n");
            MemberSDK.turnOnDebug();
        }
        AlibcTradeSDK.setForceH5(true);
        AlibcTradeSDK.setSyncForTaoke(false);
        if (this.val$ttgCallback != null) {
            this.val$ttgCallback.onBcSuccess();
        }
        TtgSDK.access$100();
    }

    public void onFailure(int i, String str) {
        if (this.val$ttgCallback != null) {
            this.val$ttgCallback.onBcFial(i, str);
        }
        TtgSDK.access$200(i, str);
    }
}
