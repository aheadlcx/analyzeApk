package cn.v6.sdk.sixrooms.coop;

import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.utils.GlobleValue;

class V6Coop$11 implements CallBack {
    final /* synthetic */ V6Coop this$0;

    V6Coop$11(V6Coop v6Coop) {
        this.this$0 = v6Coop;
    }

    public void handleInfo(UserBean userBean) {
        V6Coop.repeated++;
        if (userBean != null) {
            GlobleValue.setUserBean(userBean);
        }
    }

    public void error(int i) {
    }

    public void handleErrorInfo(String str, String str2) {
    }
}
