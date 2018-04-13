package cn.v6.sixrooms.utils;

import android.content.Intent;
import android.os.Bundle;
import cn.v6.sixrooms.engine.BundleInfoEngine.CallBack;
import cn.v6.sixrooms.ui.phone.MsgVerifyFragmentActivity;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;

final class n implements CallBack {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    private void a(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(UserTrackerConstants.FROM, "bundle");
        bundle.putString("phoneNumber", "");
        bundle.putString("isneedpaawd", str);
        Intent intent = new Intent(this.a.a, MsgVerifyFragmentActivity.class);
        intent.putExtras(bundle);
        this.a.a.startActivity(intent);
    }

    public final void handleErrorInfo(String str, String str2) {
        a("0");
    }

    public final void error(int i) {
        a("0");
    }

    public final void bundleInfo(String str, String str2, String str3) {
        a(str3);
    }
}
