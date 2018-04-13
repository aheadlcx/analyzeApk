package qsbk.app.video;

import qsbk.app.utils.timer.ITimerProcessor;

class ay implements ITimerProcessor {
    final /* synthetic */ VideoLoopStatistics a;

    ay(VideoLoopStatistics videoLoopStatistics) {
        this.a = videoLoopStatistics;
    }

    public void process() {
        this.a.a();
    }
}
