package qsbk.app.activity;

import qsbk.app.QsbkApp;
import qsbk.app.adapter.VideoImmersionAdapter.GdtVideoImmersionCell;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.video.CircleVideoPlayerView;

class afi implements Runnable {
    final /* synthetic */ VideoImmersionActivity a;

    afi(VideoImmersionActivity videoImmersionActivity) {
        this.a = videoImmersionActivity;
    }

    public void run() {
        if (this.a.n.getChildCount() <= this.a.n.getHeaderViewsCount()) {
            this.a.n.post(this);
            return;
        }
        Object tag = this.a.n.getChildAt(this.a.n.getHeaderViewsCount() + 0).getTag();
        if (tag != null && (tag instanceof VideoImmersionCell)) {
            VideoImmersionCell videoImmersionCell = (VideoImmersionCell) tag;
            videoImmersionCell.toLight();
            if (videoImmersionCell.player instanceof CircleVideoPlayerView) {
                ((CircleVideoPlayerView) videoImmersionCell.player).showControlBar(false, true);
            }
            if (QsbkApp.getInstance().isAutoPlayConfiged()) {
                videoImmersionCell.player.play();
                this.a.p.setLastPlayer(videoImmersionCell.player);
            }
        }
        if (tag != null && (tag instanceof GdtVideoImmersionCell) && QsbkApp.getInstance().isAutoPlayConfiged()) {
            ((GdtVideoImmersionCell) tag).getRef().play();
            this.a.p.setLastGdtVideoRef(((GdtVideoImmersionCell) tag).getRef());
        }
    }
}
