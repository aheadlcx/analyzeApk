package qsbk.app.activity;

import qsbk.app.QsbkApp;
import qsbk.app.adapter.VideoImmersionCircleAdapter.GdtVideoImmersionCell;
import qsbk.app.adapter.VideoImmersionCircleAdapter.VideoImmersionCell;
import qsbk.app.video.CircleVideoPlayerView;

class afu implements Runnable {
    final /* synthetic */ VideoImmersionCircleActivity a;

    afu(VideoImmersionCircleActivity videoImmersionCircleActivity) {
        this.a = videoImmersionCircleActivity;
    }

    public void run() {
        if (this.a.l.getChildCount() <= this.a.l.getHeaderViewsCount()) {
            this.a.l.post(this);
            return;
        }
        Object tag = this.a.l.getChildAt(this.a.l.getHeaderViewsCount() + 0).getTag();
        if (tag != null && (tag instanceof VideoImmersionCell)) {
            VideoImmersionCell videoImmersionCell = (VideoImmersionCell) tag;
            videoImmersionCell.toLight();
            if (videoImmersionCell.playerView instanceof CircleVideoPlayerView) {
                ((CircleVideoPlayerView) videoImmersionCell.playerView).showControlBar(false, true);
            }
            if (QsbkApp.getInstance().isAutoPlayConfiged()) {
                videoImmersionCell.playerView.play();
                this.a.n.setLastPlayer(videoImmersionCell.playerView);
            }
        }
        if (tag != null && (tag instanceof GdtVideoImmersionCell) && QsbkApp.getInstance().isAutoPlayConfiged()) {
            ((GdtVideoImmersionCell) tag).getRef().play();
            this.a.n.setLastGdtVideoRef(((GdtVideoImmersionCell) tag).getRef());
        }
    }
}
