package qsbk.app.video;

import com.baidu.mobstat.StatService;
import java.io.File;
import qsbk.app.utils.AppContext;
import qsbk.app.ye.videotools.utils.VideoEditer;
import qsbk.app.ye.videotools.utils.VideoEditer.OnCompletionListener;

class x implements OnCompletionListener {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ VideoEditActivity c;

    x(VideoEditActivity videoEditActivity, String str, String str2) {
        this.c = videoEditActivity;
        this.a = str;
        this.b = str2;
    }

    public void onCompletion(VideoEditer videoEditer) {
        this.c.g();
        StatService.onEventDuration(AppContext.getContext(), "video_edit", "trim_suc", (long) ((int) this.c.c.getDelta()));
        this.c.a(this.a, VideoEditActivity.hashFirst8096Byte(new File(this.a)), true);
        File file = new File(this.b);
    }
}
