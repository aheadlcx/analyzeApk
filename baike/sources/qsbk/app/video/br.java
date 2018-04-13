package qsbk.app.video;

import com.baidu.mobstat.StatService;
import java.io.File;
import qsbk.app.utils.AppContext;
import qsbk.app.ye.videotools.utils.VideoEditer;
import qsbk.app.ye.videotools.utils.VideoEditer.OnErrorListener;

class br implements OnErrorListener {
    final /* synthetic */ String a;
    final /* synthetic */ VideoRecordActivity b;

    br(VideoRecordActivity videoRecordActivity, String str) {
        this.b = videoRecordActivity;
        this.a = str;
    }

    public void onError(VideoEditer videoEditer, int i, int i2) {
        if (this.b.j != null && this.b.j.isShowing()) {
            this.b.j.dismiss();
        }
        StatService.onEventDuration(AppContext.getContext(), "video_edit", "trim_fail", (long) ((int) this.b.r.getDelta()));
        this.b.a(new File(this.a));
    }
}
