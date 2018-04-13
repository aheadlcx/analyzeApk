package qsbk.app.video;

import com.baidu.mobstat.StatService;
import java.io.File;
import qsbk.app.utils.AppContext;
import qsbk.app.ye.videotools.utils.VideoEditer;
import qsbk.app.ye.videotools.utils.VideoEditer.OnErrorListener;

class y implements OnErrorListener {
    final /* synthetic */ String a;
    final /* synthetic */ VideoEditActivity b;

    y(VideoEditActivity videoEditActivity, String str) {
        this.b = videoEditActivity;
        this.a = str;
    }

    public void onError(VideoEditer videoEditer, int i, int i2) {
        this.b.g();
        StatService.onEventDuration(AppContext.getContext(), "video_edit", "trim_fail", (long) ((int) this.b.c.getDelta()));
        this.b.a(this.a, VideoEditActivity.hashFirst8096Byte(new File(this.a)), false);
    }
}
