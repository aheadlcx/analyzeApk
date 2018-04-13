package cn.v6.sixrooms.animation;

import android.content.Context;
import android.graphics.Canvas;
import cn.v6.sixrooms.animation.GiftAnimationManager.IAnimation;
import cn.v6.sixrooms.bean.BeanAnimation;
import cn.v6.sixrooms.bitmap.AnimationBitmapManager;
import java.util.ArrayList;
import java.util.Iterator;

public class GiftAnimation implements IAnimation {
    private int a = 0;
    public SurfaceAnimation animationEnter = null;
    public SurfaceAnimation animationOut = null;
    public SurfaceAnimation animationRunning = null;
    private int b;
    private boolean c = false;
    private int d;
    private int e = 0;
    private int f = 0;
    private boolean g = false;

    public GiftAnimation(int i) {
        this.d = i;
        this.b = AnimationBitmapManager.getModelId();
    }

    public void closeAnimation() {
        this.g = true;
    }

    public int getLocation() {
        return this.f;
    }

    public int getModeId() {
        return this.b;
    }

    public void draw(Context context, Canvas canvas, int i) {
        if (this.e > 0) {
            this.e = (int) (((long) this.e) - 30);
            if (this.e < 0) {
                this.e = 0;
            }
        } else if (this.animationEnter != null && !this.animationEnter.isFinishAnimation()) {
            if (this.g) {
                this.animationEnter.addOneTime();
            } else {
                this.animationEnter.draw(context, canvas, i);
            }
            if (this.animationEnter.isFinishAnimation()) {
                this.a = 1;
            }
        } else if (this.animationRunning == null || this.animationRunning.isFinishAnimation()) {
            if (this.animationOut == null && this.animationRunning != null) {
                this.animationOut = new SurfaceAnimation(this.animationRunning.getCallBack(), AnimationGiftValues.getOutAnimations(context, this.animationRunning.getAnimation(), this.d), AnimationGiftValues.outTimeArray[this.d - 1], this.b);
            }
            if (this.animationOut != null && !this.animationOut.isFinishAnimation()) {
                if (this.g) {
                    this.animationOut.addOneTime();
                } else {
                    this.animationOut.draw(context, canvas, i);
                }
                this.c = this.animationOut.isFinishAnimation();
                if (this.c) {
                    clearCache();
                }
            }
        } else {
            if (this.g) {
                this.animationRunning.addOneTime();
            } else {
                this.animationRunning.draw(context, canvas, i);
            }
            if (this.animationRunning.isFinishAnimation()) {
                this.a = 2;
            }
        }
    }

    public void scrollY(int i, int i2) {
        this.f = 1;
        ArrayList animation = this.animationRunning.getAnimation();
        int duration = this.animationRunning.getDuration();
        int time = this.animationRunning.getTime();
        int i3 = (time - duration) - i2;
        if (i3 < 0) {
            this.animationRunning.setTime(time - i3);
        }
        Iterator it = animation.iterator();
        while (it.hasNext()) {
            BeanAnimation beanAnimation = (BeanAnimation) it.next();
            beanAnimation.scrollY = new int[]{i};
            beanAnimation.scrollTime = new int[]{duration, duration + i2};
        }
    }

    public void setEnterDelay(int i) {
        this.e = i;
    }

    public void clearCache() {
        AnimationBitmapManager.recycleModelBitmap(this.b);
    }

    public boolean isFinish() {
        return this.c;
    }

    public int getStatus() {
        return this.a;
    }
}
