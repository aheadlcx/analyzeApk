package qsbk.app.activity;

import com.qq.e.comm.constants.ErrorCode$OtherError;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.Util;

class afc implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ VideoImmersionActivity b;

    afc(VideoImmersionActivity videoImmersionActivity, int i) {
        this.b = videoImmersionActivity;
        this.a = i;
    }

    public void run() {
        if (this.b.r && QsbkApp.getInstance().isAutoPlayConfiged()) {
            this.b.checkToPlay();
        } else if (this.a < this.b.n.getCount() - 1) {
            this.b.n.smoothScrollToPositionFromTop(this.a + this.b.n.getHeaderViewsCount(), this.b.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height) + Util.statusBarHeight, ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE);
        }
    }
}
