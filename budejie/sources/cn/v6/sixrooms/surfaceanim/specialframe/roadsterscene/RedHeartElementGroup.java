package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;

public abstract class RedHeartElementGroup extends SpecialElement implements ICarRedHeartElementControl {
    protected CarRedHeartElement[] mCarRedHeartElements = new CarRedHeartElement[3];

    public abstract void initRedHeartElements(AnimScene animScene);

    public RedHeartElementGroup(AnimScene animScene) {
        super(animScene);
        int elementFirstFrame = elementFirstFrame();
        PointI elementLocation = elementLocation();
        float elementScale = elementScale();
        initRedHeartElements(animScene);
        for (CarRedHeartElement carRedHeartElement : this.mCarRedHeartElements) {
            carRedHeartElement.setFirstFrame(elementFirstFrame);
            carRedHeartElement.setEleRelativeLocation(elementLocation.x, elementLocation.y);
            carRedHeartElement.setElementScale(elementScale);
        }
    }

    public float elementScale() {
        return 1.0f;
    }

    public void draw(Canvas canvas) {
        for (SpecialElement draw : this.mCarRedHeartElements) {
            draw.draw(canvas);
        }
    }

    public void drawElement(Canvas canvas) {
    }

    public boolean frameControl(int i) {
        for (SpecialElement frameControl : this.mCarRedHeartElements) {
            frameControl.frameControl(i);
        }
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[0];
    }
}
