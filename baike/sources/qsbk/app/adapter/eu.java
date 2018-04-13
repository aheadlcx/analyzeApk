package qsbk.app.adapter;

import qsbk.app.activity.VideoFullScreenActivity$FullPlayCallBack;
import qsbk.app.adapter.VideoImmersionCircleAdapter.VideoImmersionCell;

class eu implements VideoFullScreenActivity$FullPlayCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ VideoImmersionCell b;

    eu(VideoImmersionCell videoImmersionCell, int i) {
        this.b = videoImmersionCell;
        this.a = i;
    }

    public void onPlayInfoCallBack(int i, long j) {
        if (i > 0) {
            this.b.a.l.smoothScrollToPositionFromTop((this.a + i) + this.b.a.l.getHeaderViewsCount(), this.b.a.b);
        }
    }
}
