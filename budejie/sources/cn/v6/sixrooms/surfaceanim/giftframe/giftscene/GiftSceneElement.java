package cn.v6.sixrooms.surfaceanim.giftframe.giftscene;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimSceneElement;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;

public abstract class GiftSceneElement extends AnimSceneElement {
    protected int mCurFrame;

    public abstract void drawElement(Canvas canvas);

    public GiftSceneElement(AnimScene animScene) {
        super(animScene);
    }

    public IAnimEntity[] initAnimEntities() {
        return null;
    }

    public void draw(Canvas canvas) {
        this.mCurFrame = this.mAnimScene.getSceneParameter().getCurFrameNum();
        drawElement(canvas);
    }

    protected Bitmap getBitmap(int i) {
        return AnimSceneResManager.getInstance().getBitmap(this.mAnimScene.getSceneType(), i, true);
    }
}
