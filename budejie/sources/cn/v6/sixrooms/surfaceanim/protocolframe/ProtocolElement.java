package cn.v6.sixrooms.surfaceanim.protocolframe;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimSceneElement;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.protocol.ElementBean;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;

public abstract class ProtocolElement<T extends IAnimEntity> extends AnimSceneElement<T> {
    protected PointI mPoint = new PointI();

    public ProtocolElement(AnimScene animScene) {
        super(animScene);
    }

    public ProtocolElement(AnimScene animScene, ElementBean elementBean) {
        super(animScene, elementBean);
    }

    public void draw(Canvas canvas) {
        PointI point = this.mAnimScene.getSceneParameter().getPoint();
        if (!point.equals(this.mPoint.x, this.mPoint.y)) {
            canvasSizeUpdate(point.x - this.mPoint.x, point.y - this.mPoint.y);
            this.mPoint = point;
        }
        super.draw(canvas);
    }

    protected Bitmap getBitmap(int i) {
        return AnimSceneResManager.getInstance().getBitmap(this.mAnimScene.getSceneType(), i);
    }

    protected void canvasSizeUpdate(int i, int i2) {
    }
}
