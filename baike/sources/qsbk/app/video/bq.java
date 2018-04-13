package qsbk.app.video;

import com.baidu.mobstat.StatService;
import java.io.File;
import qsbk.app.utils.AppContext;
import qsbk.app.ye.videotools.utils.VideoEditer;
import qsbk.app.ye.videotools.utils.VideoEditer.OnCompletionListener;

class bq implements OnCompletionListener {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ VideoRecordActivity c;

    bq(VideoRecordActivity videoRecordActivity, String str, String str2) {
        this.c = videoRecordActivity;
        this.a = str;
        this.b = str2;
    }

    public void onCompletion(VideoEditer videoEditer) {
        this.c.j.dismiss();
        StatService.onEventDuration(AppContext.getContext(), "video_edit", "trim_suc", (long) ((int) this.c.r.getDelta()));
        this.c.a(new File(this.a));
        File file = new File(this.b);
        if (file.exists()) {
            file.delete();
        }
    }
}
