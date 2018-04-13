package cn.v6.sixrooms.room.widgets;

import cn.v6.sixrooms.room.engine.FreeVoteNumEngine.CallBack;

final class a implements CallBack {
    final /* synthetic */ CommonEventDialog a;

    a(CommonEventDialog commonEventDialog) {
        this.a = commonEventDialog;
    }

    public final void result(String str) {
        this.a.updateFreeVoteNum(str);
    }

    public final void handleErrorInfo(String str, String str2) {
        CommonEventDialog.a(this.a).handleErrorResult(str, str2, CommonEventDialog.a(this.a));
    }

    public final void error(int i) {
        CommonEventDialog.a(this.a).showErrorToast(i);
    }
}
