package qsbk.app.activity.publish;

import android.os.Build.VERSION;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout.LayoutParams;

class bt implements OnGlobalLayoutListener {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ PublishActivity d;

    bt(PublishActivity publishActivity, int i, int i2, String str) {
        this.d = publishActivity;
        this.a = i;
        this.b = i2;
        this.c = str;
    }

    public void onGlobalLayout() {
        if (VERSION.SDK_INT >= 16) {
            this.d.mVideoLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } else {
            this.d.mVideoLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
        int measuredWidth = this.d.mVideoLayout.getMeasuredWidth();
        int i = (int) (((float) (this.a * measuredWidth)) / (((float) this.b) * 1.0f));
        LayoutParams layoutParams = (LayoutParams) this.d.mVideoLayout.getLayoutParams();
        layoutParams.height = -2;
        layoutParams.width = measuredWidth;
        this.d.mVideoLayout.setLayoutParams(layoutParams);
        this.d.ah.setVisibility(0);
        layoutParams = (LayoutParams) this.d.ah.getLayoutParams();
        layoutParams.width = measuredWidth;
        layoutParams.height = -2;
        this.d.ah.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.d.ag.getLayoutParams();
        layoutParams.width = measuredWidth;
        layoutParams.height = -2;
        this.d.ag.setLayoutParams(layoutParams);
        this.d.player.setAspectRatio(measuredWidth, i);
        this.d.af.setVisibility(0);
        this.d.player.setVideo(this.c);
        this.d.player.play();
    }
}
