package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class afe implements OnClickListener {
    final /* synthetic */ VideoImmersionActivity a;

    afe(VideoImmersionActivity videoImmersionActivity) {
        this.a = videoImmersionActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
