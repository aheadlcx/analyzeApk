package cn.v6.sixrooms.room.popwindows;

import cn.v6.sixrooms.room.bean.WeiBoListMsgBean;
import cn.v6.sixrooms.room.engine.RetransmitEngine.CallBack;
import cn.v6.sixrooms.utils.LogUtils;

final class g implements CallBack {
    final /* synthetic */ UpgradeWindow a;

    g(UpgradeWindow upgradeWindow) {
        this.a = upgradeWindow;
    }

    public final void result(WeiBoListMsgBean weiBoListMsgBean) {
        LogUtils.d("upgrade", weiBoListMsgBean.getAlias());
    }

    public final void error(int i) {
        LogUtils.d("upgrade", "error code:" + i);
    }

    public final void handleErrorInfo(String str, String str2) {
        LogUtils.d("upgrade", "error flag:" + str + " ;content:" + str2);
    }
}
