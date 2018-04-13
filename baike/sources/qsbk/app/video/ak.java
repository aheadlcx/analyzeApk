package qsbk.app.video;

import qsbk.app.utils.DebugUtil;
import qsbk.app.video.VideoCropView.IVideoCropListener;

class ak implements IVideoCropListener {
    final /* synthetic */ VideoEditActivity a;

    ak(VideoEditActivity videoEditActivity) {
        this.a = videoEditActivity;
    }

    public void videoCrop(int i, int i2) {
        DebugUtil.debug(VideoEditActivity.e, "startTime:" + i + "  endTime:" + i2);
        this.a.j.seekTo(i);
        this.a.n = i;
        this.a.o = i2;
    }
}
