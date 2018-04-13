package qsbk.app.adapter;

import android.view.View;
import qsbk.app.adapter.VideoImmersionCircleAdapter.VideoImmersionCell;
import qsbk.app.video.SimpleVideoPlayer;
import qsbk.app.video.SimpleVideoPlayer.OnVideoStateListener;

class ex implements OnVideoStateListener {
    final /* synthetic */ VideoImmersionCell a;

    ex(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onVideoState(SimpleVideoPlayer simpleVideoPlayer, int i) {
        if (i == 4) {
            View previewView = this.a.playerView.getPreviewView();
            if (previewView != null && previewView.getVisibility() == 0) {
                previewView.setVisibility(4);
            }
        }
    }
}
