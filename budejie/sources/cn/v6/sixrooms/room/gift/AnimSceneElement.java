package cn.v6.sixrooms.room.gift;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.animinterface.ISceneElement;
import cn.v6.sixrooms.surfaceanim.protocol.ElementBean;

public abstract class AnimSceneElement<T extends IAnimEntity> implements ISceneElement {
    private String elementName;
    protected T[] mAnimEntities = initAnimEntities();
    protected AnimScene mAnimScene;
    protected int mCurFrame;
    protected ElementBean mElementBean;

    public abstract void drawElement(Canvas canvas);

    public abstract boolean frameControl(int i);

    public abstract T[] initAnimEntities();

    public AnimSceneElement(AnimScene animScene) {
        this.mAnimScene = animScene;
    }

    public AnimSceneElement(AnimScene animScene, ElementBean elementBean) {
        this.mAnimScene = animScene;
        this.mElementBean = elementBean;
    }

    public void draw(Canvas canvas) {
        this.mCurFrame = this.mAnimScene.getSceneParameter().getCurFrameNum();
        if (!frameControl(this.mCurFrame)) {
            drawElement(canvas);
        }
    }

    public void resetAnimEntities() {
    }

    public T[] getAnimEntities() {
        return this.mAnimEntities;
    }

    public void setElementName(String str) {
        this.elementName = str;
    }

    public String getElementName() {
        return this.elementName;
    }
}
