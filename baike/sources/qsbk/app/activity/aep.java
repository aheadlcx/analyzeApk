package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class aep implements OnClickListener {
    final /* synthetic */ VideoFullScreenActivity a;

    aep(VideoFullScreenActivity videoFullScreenActivity) {
        this.a = videoFullScreenActivity;
    }

    public void onClick(View view) {
        this.a.getMainUIHandler().removeCallbacksAndMessages(null);
        VideoFullScreenActivity.d(this.a).setVisibility(8);
        VideoFullScreenActivity.h(this.a);
    }
}
