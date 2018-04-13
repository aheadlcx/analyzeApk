package qsbk.app.video;

import com.baidu.mobstat.StatService;
import qsbk.app.utils.AppContext;
import qsbk.app.ye.videotools.utils.VideoEditer;
import qsbk.app.ye.videotools.utils.VideoEditer.OnErrorListener;

class bp implements OnErrorListener {
    final /* synthetic */ bn a;

    bp(bn bnVar) {
        this.a = bnVar;
    }

    public void onError(VideoEditer videoEditer, int i, int i2) {
        if (this.a.d.j != null && this.a.d.j.isShowing()) {
            this.a.d.j.dismiss();
        }
        this.a.d.a(this.a.c);
        StatService.onEventDuration(AppContext.getContext(), "video_edit", "crop_fail", (long) ((int) this.a.d.r.getDelta()));
    }
}
