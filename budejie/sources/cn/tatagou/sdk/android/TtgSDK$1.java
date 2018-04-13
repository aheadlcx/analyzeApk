package cn.tatagou.sdk.android;

import android.util.Log;
import com.ali.auth.third.core.callback.InitResultCallback;

class TtgSDK$1 implements InitResultCallback {
    TtgSDK$1() {
    }

    public void onSuccess() {
        Log.d(TtgSDK.access$000(), "MemberSDK onSuccess:");
    }

    public void onFailure(int i, String str) {
        Log.d(TtgSDK.access$000(), "MemberSDK onFailure:" + i + "," + str);
    }
}
