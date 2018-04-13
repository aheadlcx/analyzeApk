package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.engine.ReportUserEngine.CallBack;
import cn.v6.sixrooms.utils.ToastUtils;

final class t implements CallBack {
    final /* synthetic */ ReportActivity a;

    t(ReportActivity reportActivity) {
        this.a = reportActivity;
    }

    public final void result(String str) {
        ToastUtils.showToast(str);
        this.a.finish();
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.handleErrorResult(str, str2, this.a);
    }

    public final void error(int i) {
        this.a.showErrorToast(i);
    }
}
