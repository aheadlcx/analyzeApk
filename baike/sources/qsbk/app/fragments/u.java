package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class u implements OnClickListener {
    final /* synthetic */ BrowseGIFVideoFragment a;

    u(BrowseGIFVideoFragment browseGIFVideoFragment) {
        this.a = browseGIFVideoFragment;
    }

    public void onClick(View view) {
        if (this.a.videoPlayer.isPlaying()) {
            this.a.videoPlayer.pause();
        } else {
            this.a.videoPlayer.play();
        }
    }
}
