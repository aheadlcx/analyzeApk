package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class aeq implements OnClickListener {
    final /* synthetic */ VideoFullScreenActivity a;

    aeq(VideoFullScreenActivity videoFullScreenActivity) {
        this.a = videoFullScreenActivity;
    }

    public void onClick(View view) {
        this.a.getMainUIHandler().removeCallbacksAndMessages(null);
        VideoFullScreenActivity.d(this.a).setVisibility(8);
        VideoFullScreenActivity.a(this.a, 3);
        VideoFullScreenActivity.i(this.a).setVisibility(8);
        VideoFullScreenActivity.g(this.a).setStartMs(0);
        VideoFullScreenActivity.g(this.a).play();
    }
}
