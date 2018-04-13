package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class afq implements OnClickListener {
    final /* synthetic */ VideoImmersionCircleActivity a;

    afq(VideoImmersionCircleActivity videoImmersionCircleActivity) {
        this.a = videoImmersionCircleActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
