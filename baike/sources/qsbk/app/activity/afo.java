package qsbk.app.activity;

import com.qq.e.comm.constants.ErrorCode$OtherError;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.Util;

class afo implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ VideoImmersionCircleActivity b;

    afo(VideoImmersionCircleActivity videoImmersionCircleActivity, int i) {
        this.b = videoImmersionCircleActivity;
        this.a = i;
    }

    public void run() {
        if (this.b.q && QsbkApp.getInstance().isAutoPlayConfiged()) {
            this.b.checkToPlay();
        } else if (this.a < this.b.l.getCount() - 1) {
            this.b.l.smoothScrollToPositionFromTop(this.a + this.b.l.getHeaderViewsCount(), this.b.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height) + Util.statusBarHeight, ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE);
        }
    }
}
