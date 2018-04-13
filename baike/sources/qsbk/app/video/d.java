package qsbk.app.video;

import android.view.View;
import android.view.View.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ CircleVideoPlayerView a;

    d(CircleVideoPlayerView circleVideoPlayerView) {
        this.a = circleVideoPlayerView;
    }

    public void onClick(View view) {
        this.a.startOrPause();
    }
}
