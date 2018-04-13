package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class aeo implements OnClickListener {
    final /* synthetic */ VideoFullScreenActivity a;

    aeo(VideoFullScreenActivity videoFullScreenActivity) {
        this.a = videoFullScreenActivity;
    }

    public void onClick(View view) {
        VideoFullScreenActivity.f(this.a).setVisibility(VideoFullScreenActivity.f(this.a).getVisibility() != 0 ? 0 : 4);
    }
}
