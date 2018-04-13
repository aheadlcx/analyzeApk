package qsbk.app.activity.publish;

import android.os.Build.VERSION;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout.LayoutParams;

class ak implements OnGlobalLayoutListener {
    final /* synthetic */ CirclePublishActivity a;

    ak(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onGlobalLayout() {
        if (VERSION.SDK_INT >= 16) {
            this.a.mVideoLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } else {
            this.a.mVideoLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
        int measuredWidth = this.a.mVideoLayout.getMeasuredWidth();
        int aspectRatio = (int) (((float) measuredWidth) / this.a.z.getAspectRatio());
        LayoutParams layoutParams = (LayoutParams) this.a.player.getLayoutParams();
        layoutParams.width = measuredWidth;
        layoutParams.height = aspectRatio;
        this.a.player.setLayoutParams(layoutParams);
        this.a.player.setAspectRatio(measuredWidth, aspectRatio);
        this.a.player.setWidget(null, this.a.w, this.a.x);
        this.a.v.setVisibility(0);
        this.a.player.setVideo(this.a.z.getFilePath(this.a));
        this.a.player.play();
    }
}
