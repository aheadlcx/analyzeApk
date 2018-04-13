package qsbk.app.adapter;

import qsbk.app.activity.VideoFullScreenActivity$FullPlayCallBack;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;

class eg implements VideoFullScreenActivity$FullPlayCallBack {
    final /* synthetic */ VideoImmersionCell a;

    eg(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onPlayInfoCallBack(int i, long j) {
        if (i > 0) {
            this.a.a.l.smoothScrollToPositionFromTop((this.a.q + i) + this.a.a.l.getHeaderViewsCount(), this.a.a.e);
        }
    }
}
