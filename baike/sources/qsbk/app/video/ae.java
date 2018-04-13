package qsbk.app.video;

import qsbk.app.utils.DebugUtil;
import qsbk.app.video.VideoEditPlayView.OnPauseListener;

class ae implements OnPauseListener {
    final /* synthetic */ VideoEditActivity a;

    ae(VideoEditActivity videoEditActivity) {
        this.a = videoEditActivity;
    }

    public void onPause() {
        DebugUtil.debug(VideoEditActivity.e, "onPause");
        this.a.k.post(new af(this));
    }
}
