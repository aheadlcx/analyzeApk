package qsbk.app.video;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.VideoFullScreenActivity;

class f implements OnClickListener {
    final /* synthetic */ CircleVideoPlayerView a;

    f(CircleVideoPlayerView circleVideoPlayerView) {
        this.a = circleVideoPlayerView;
    }

    public void onClick(View view) {
        if (this.a.getContext() instanceof VideoFullScreenActivity) {
            ((VideoFullScreenActivity) this.a.getContext()).finish();
        } else if (this.a.u != null) {
            this.a.u.onFullScreenClick();
        } else {
            VideoFullScreenActivity.launch(view.getContext(), this.a.e.getVideoUri(), this.a.getWidth(), this.a.getHeight(), this.a.getCurrentTime());
        }
    }
}
