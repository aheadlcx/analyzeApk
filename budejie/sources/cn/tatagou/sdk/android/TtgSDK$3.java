package cn.tatagou.sdk.android;

import android.util.Log;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.util.b;
import cn.tatagou.sdk.util.c;
import com.ali.auth.third.core.MemberSDK;
import com.ali.auth.third.core.config.AuthOption;
import com.ali.auth.third.mtop.rpc.ResultActionType;
import java.util.Map;

class TtgSDK$3 extends c {
    TtgSDK$3() {
    }

    public void setSysCfg(Map<String, String> map) {
        super.setSysCfg(map);
        b.openReportLaunch(map);
        MemberSDK.setAuthOption(ResultActionType.H5.equals(Config.getInstance().getLoginType()) ? AuthOption.H5ONLY : AuthOption.NORMAL);
        Log.d(TtgSDK.access$000(), "loginType::: " + ResultActionType.H5.equals(Config.getInstance().getLoginType()) + ",is true h5");
    }
}
