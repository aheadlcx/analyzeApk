package qsbk.app.video;

import qsbk.app.utils.timer.ITimerProcessor;

class h implements ITimerProcessor {
    final /* synthetic */ QiuyouCircleVideoLoopStatistics a;

    h(QiuyouCircleVideoLoopStatistics qiuyouCircleVideoLoopStatistics) {
        this.a = qiuyouCircleVideoLoopStatistics;
    }

    public void process() {
        this.a.a();
    }
}
