package cn.v6.sixrooms.room.presenter;

import cn.v6.sixrooms.utils.LogUtils;

final class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        LogUtils.d(FrameStatisticsPresenter.p, "sendFrameStatistics22222－－－mStrBuilder---" + this.a.a.d.toString());
        FrameStatisticsPresenter.a(this.a.a, this.a.a.d.toString());
        this.a.a.d.delete(0, this.a.a.d.length());
    }
}
