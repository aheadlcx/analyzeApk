package cn.v6.sixrooms.room.presenter;

import android.os.Handler;
import android.os.Message;
import cn.v6.sixrooms.utils.DateUtil;

final class c extends Handler {
    final /* synthetic */ HeadLinePresenter a;

    c(HeadLinePresenter headLinePresenter) {
        this.a = headLinePresenter;
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        this.a.m = this.a.m - 1;
        if (this.a.m >= 0) {
            if (this.a.d != null) {
                this.a.d.updateCountDownTime(DateUtil.getMinuteFromMillisecond(this.a.m * 1000));
            }
            this.a.a();
        }
    }
}
