package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ael implements OnClickListener {
    final /* synthetic */ VideoFullScreenActivity a;

    ael(VideoFullScreenActivity videoFullScreenActivity) {
        this.a = videoFullScreenActivity;
    }

    public void onClick(View view) {
        this.a.onBackPressed();
    }
}
