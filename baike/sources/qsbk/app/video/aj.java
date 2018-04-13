package qsbk.app.video;

import qsbk.app.utils.DebugUtil;
import qsbk.app.video.VideoEditPlayView.OnScrollStopListener;

class aj implements OnScrollStopListener {
    final /* synthetic */ VideoEditActivity a;

    aj(VideoEditActivity videoEditActivity) {
        this.a = videoEditActivity;
    }

    public void onScrollStop(int i, int i2) {
        DebugUtil.debug(VideoEditActivity.e, "oritation:" + i + "   scrollDistance:" + i2);
        switch (i) {
            case 0:
                if (this.a.u.endsWith("90") || this.a.u.endsWith("270")) {
                    this.a.q = 0;
                    this.a.r = i2;
                    return;
                }
                this.a.q = i2;
                this.a.r = 0;
                return;
            case 1:
                if (this.a.u.endsWith("90") || this.a.u.endsWith("270")) {
                    this.a.q = i2;
                    this.a.r = 0;
                    return;
                }
                this.a.q = 0;
                this.a.r = i2;
                return;
            default:
                return;
        }
    }
}
