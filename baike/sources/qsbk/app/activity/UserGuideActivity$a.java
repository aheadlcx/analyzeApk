package qsbk.app.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

abstract class UserGuideActivity$a {
    final /* synthetic */ UserGuideActivity a;
    private boolean b = false;
    public int mScreenWidth;
    public View mView;

    public abstract void invisibleViews();

    public abstract void moveBackIn();

    public abstract void moveBackOut();

    public abstract void moveForwardIn();

    public abstract void moveForwardOut();

    public abstract void visibleViews();

    public UserGuideActivity$a(UserGuideActivity userGuideActivity, int i) {
        this.a = userGuideActivity;
        this.mView = LayoutInflater.from(userGuideActivity).inflate(i, null);
        this.mScreenWidth = userGuideActivity.getResources().getDisplayMetrics().widthPixels;
    }

    public boolean getMoveOutFlag() {
        return this.b;
    }

    public void setMoveOutFlag(boolean z) {
        this.b = z;
    }

    protected void a(View view, int[] iArr, int[] iArr2, int i) {
        Animation translateAnimation = new TranslateAnimation((float) iArr[0], (float) iArr2[0], (float) iArr[1], (float) iArr2[1]);
        translateAnimation.setDuration((long) i);
        view.startAnimation(translateAnimation);
    }
}
