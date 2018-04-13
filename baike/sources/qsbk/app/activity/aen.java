package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class aen implements OnClickListener {
    final /* synthetic */ VideoFullScreenActivity a;

    aen(VideoFullScreenActivity videoFullScreenActivity) {
        this.a = videoFullScreenActivity;
    }

    public void onClick(View view) {
        if (VideoFullScreenActivity.g(this.a) != null) {
            VideoFullScreenActivity.g(this.a).showControlBar(false, true);
        }
    }
}
