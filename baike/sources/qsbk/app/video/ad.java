package qsbk.app.video;

import android.widget.RelativeLayout.LayoutParams;

class ad implements Runnable {
    final /* synthetic */ VideoEditActivity a;

    ad(VideoEditActivity videoEditActivity) {
        this.a = videoEditActivity;
    }

    public void run() {
        LayoutParams layoutParams = (LayoutParams) this.a.k.getLayoutParams();
        layoutParams.topMargin = (this.a.m / 2) - (this.a.k.getWidth() / 2);
        this.a.k.setLayoutParams(layoutParams);
    }
}
