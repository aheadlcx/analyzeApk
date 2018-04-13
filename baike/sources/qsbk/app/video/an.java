package qsbk.app.video;

import com.baidu.mobstat.StatService;
import java.io.File;
import qsbk.app.utils.AppContext;
import qsbk.app.ye.videotools.utils.VideoEditer;
import qsbk.app.ye.videotools.utils.VideoEditer.OnErrorListener;

class an implements OnErrorListener {
    final /* synthetic */ String a;
    final /* synthetic */ al b;

    an(al alVar, String str) {
        this.b = alVar;
        this.a = str;
    }

    public void onError(VideoEditer videoEditer, int i, int i2) {
        this.b.a.g();
        StatService.onEventDuration(AppContext.getContext(), "video_edit", "trim_fail", (long) ((int) this.b.a.c.getDelta()));
        this.b.a.a(this.a, VideoEditActivity.hashFirst8096Byte(new File(this.a)), false);
    }
}
