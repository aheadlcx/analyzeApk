package qsbk.app.adapter;

import android.view.View;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.video.SimpleVideoPlayer;
import qsbk.app.video.SimpleVideoPlayer.OnVideoStateListener;

class ej implements OnVideoStateListener {
    final /* synthetic */ VideoImmersionCell a;

    ej(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onVideoState(SimpleVideoPlayer simpleVideoPlayer, int i) {
        if (i == 4) {
            View previewView = this.a.player.getPreviewView();
            if (previewView != null && previewView.getVisibility() == 0) {
                previewView.setVisibility(4);
            }
        }
    }
}
