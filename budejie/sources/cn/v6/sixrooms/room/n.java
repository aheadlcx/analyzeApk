package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.BaseRoomActivity.GtAppDlgTask;
import cn.v6.sixrooms.room.verify.CaptchaBean;
import cn.v6.sixrooms.room.verify.CaptchaEngine.CallBack;

final class n implements CallBack {
    final /* synthetic */ BaseRoomActivity a;

    n(BaseRoomActivity baseRoomActivity) {
        this.a = baseRoomActivity;
    }

    public final void result(CaptchaBean captchaBean) {
        new GtAppDlgTask(captchaBean.getGt(), captchaBean.getChallenge()).execute(new Void[0]);
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.handleErrorResult(str, str2, this.a);
    }

    public final void error(int i) {
        this.a.showErrorToast(i);
    }
}
