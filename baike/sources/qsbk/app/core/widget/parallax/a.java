package qsbk.app.core.widget.parallax;

import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.widget.parallax.ParallaxRecyclerView.BackAnimimation;
import qsbk.app.core.widget.parallax.ParallaxRecyclerView.OnTouchEventListener;

class a implements OnTouchEventListener {
    final /* synthetic */ ParallaxRecyclerView a;
    public int mInitTouchY = -1;
    public int mLastTouchY;
    public int mScrollPointerId;

    a(ParallaxRecyclerView parallaxRecyclerView) {
        this.a = parallaxRecyclerView;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        int y;
        switch (actionMasked) {
            case 0:
                this.mScrollPointerId = motionEvent.getPointerId(0);
                y = (int) (motionEvent.getY() + 0.5f);
                this.mLastTouchY = y;
                this.mInitTouchY = y;
                LogUtils.d(ParallaxRecyclerView.I, "mLastTouchY:" + this.mLastTouchY + ",mScrollPointerId:" + this.mScrollPointerId);
                break;
            case 1:
                this.mInitTouchY = -1;
                if (this.a.M < this.a.K.getHeight()) {
                    actionMasked = this.a.K.getHeight() - this.a.M;
                    Animation backAnimimation = new BackAnimimation(this.a, this.a.K, this.a.M, false);
                    backAnimimation.setDuration(300);
                    backAnimimation.setAnimationListener(new b(this, actionMasked));
                    this.a.K.startAnimation(backAnimimation);
                    break;
                }
                break;
            case 2:
                actionMasked = motionEvent.findPointerIndex(this.mScrollPointerId);
                if (actionMasked >= 0) {
                    actionMasked = (int) (motionEvent.getY(actionMasked) + 0.5f);
                    if (this.mInitTouchY < 0) {
                        this.mLastTouchY = actionMasked;
                        this.mInitTouchY = actionMasked;
                    }
                    actionIndex = this.mLastTouchY - actionMasked;
                    int height = this.a.K.getHeight();
                    if (height <= this.a.L) {
                        if (actionIndex < 0) {
                            View findViewByPosition = this.a.getLayoutManager().findViewByPosition(0);
                            if (height - (actionIndex / 2) >= this.a.M && findViewByPosition != null && findViewByPosition.getTop() >= 0) {
                                this.a.K.getLayoutParams().height = height - (actionIndex / 2) < this.a.L ? height - (actionIndex / 2) : this.a.L;
                                this.a.K.requestLayout();
                            }
                        } else if (this.a.K.getHeight() > this.a.M) {
                            this.a.K.getLayoutParams().height = height - actionIndex > this.a.M ? height - actionIndex : this.a.M;
                            this.a.K.requestLayout();
                        }
                    }
                    this.mLastTouchY = actionMasked;
                    break;
                }
                Log.e(ParallaxRecyclerView.I, "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                return false;
            case 5:
                this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
                y = (int) (motionEvent.getY(actionIndex) + 0.5f);
                this.mLastTouchY = y;
                this.mInitTouchY = y;
                LogUtils.d(ParallaxRecyclerView.I, "mLastTouchY:" + this.mLastTouchY + ",mScrollPointerId:" + this.mScrollPointerId + ", actionIndex:" + actionIndex);
                break;
        }
        return true;
    }
}
