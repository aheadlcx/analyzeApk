package cn.v6.sixrooms.surfaceanim.specialframe.yachtscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.specialframe.util.ScalePxUtil;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class LeafElement extends SpecialElement {
    private AnimBitmap a = ((AnimBitmap) this.mAnimEntities[0]);
    private AnimFloatEvaluator b;

    public LeafElement(AnimScene animScene) {
        super(animScene);
        this.mAnimEntities[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_leaf.png"));
        this.a.setTranslateX(ScalePxUtil.getScalePx(-115, 0));
        this.a.setTranslateY(ScalePxUtil.getScalePx(-61, 1));
        this.b = new AnimFloatEvaluator(1, 50, 0.0f, -11.2f);
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((AnimBitmap) iAnimEntity).animTranslate().animPostRotate(ScalePxUtil.getScalePx(-50, 0), ScalePxUtil.getScalePx(-23, 1)).draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        int i2 = i % 100;
        if (i2 == 0) {
            i2 = 100;
        }
        if (i2 == 1) {
            this.b.resetEvaluator(i2, 50, 0.0f, -11.2f);
        }
        if (i2 == 50) {
            this.b.resetEvaluator(i2, 100, -11.2f, 0.0f);
        }
        this.a.setRotate(this.b.evaluate(i2));
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[1];
    }
}
