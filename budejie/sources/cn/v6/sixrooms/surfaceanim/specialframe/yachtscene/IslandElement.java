package cn.v6.sixrooms.surfaceanim.specialframe.yachtscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.specialframe.util.ScalePxUtil;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class IslandElement extends SpecialElement {
    private AnimBitmap a = ((AnimBitmap) this.mAnimEntities[0]);
    private AnimIntEvaluator b;

    public IslandElement(AnimScene animScene) {
        super(animScene);
        this.mAnimEntities[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_island.png"));
        this.a.setTranslateX(ScalePxUtil.getScalePx(-368, 0));
        this.a.setTranslateY(ScalePxUtil.getScalePx(853, 1));
        this.b = new AnimIntEvaluator(1, 104, this.a.getTranslateX(), ScalePxUtil.getScalePx(330, 0));
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((AnimBitmap) iAnimEntity).animTranslate().draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i > 214) {
            return true;
        }
        this.a.setTranslateX(this.b.evaluate(i));
        if (i == 104) {
            this.b.resetEvaluator(i, 185, this.a.getTranslateX(), ScalePxUtil.getScalePx(504, 0));
        }
        if (i != 185) {
            return false;
        }
        this.b.resetEvaluator(i, 214, this.a.getTranslateX(), ScalePxUtil.getScalePx(1080, 0));
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[1];
    }
}
