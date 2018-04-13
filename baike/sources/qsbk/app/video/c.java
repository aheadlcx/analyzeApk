package qsbk.app.video;

import android.view.View;
import android.view.View.OnClickListener;

class c implements OnClickListener {
    final /* synthetic */ CircleVideoPlayerView a;

    c(CircleVideoPlayerView circleVideoPlayerView) {
        this.a = circleVideoPlayerView;
    }

    public void onClick(View view) {
        this.a.startOrPause();
    }
}
