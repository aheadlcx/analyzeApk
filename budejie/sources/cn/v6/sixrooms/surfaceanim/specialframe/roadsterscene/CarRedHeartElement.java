package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;

public abstract class CarRedHeartElement extends SpecialElement<AnimBitmap> {
    protected float mElementScale = 1.0f;

    public abstract void setEleRelativeLocation(int i, int i2);

    public abstract void setFirstFrame(int i);

    public CarRedHeartElement(AnimScene animScene) {
        super(animScene);
    }

    public void setElementScale(float f) {
        this.mElementScale = f;
    }

    protected int getScalePx(int i) {
        return super.getScalePx((int) (((float) i) * this.mElementScale));
    }
}
