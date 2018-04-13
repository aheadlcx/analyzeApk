package cn.v6.sixrooms.surfaceanim.specialframe.yachtscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.specialframe.util.ScalePxUtil;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.alibaba.wireless.security.SecExceptionCode;
import java.io.File;

public class CloudElement extends SpecialElement {
    private AnimBitmap a = ((AnimBitmap) this.mAnimEntities[0]);
    private AnimBitmap b;
    private AnimBitmap c;
    private AnimIntEvaluator d;
    private AnimIntEvaluator e;
    private AnimIntEvaluator f;

    public CloudElement(AnimScene animScene) {
        super(animScene);
        this.mAnimEntities[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_cloud1.png"));
        this.mAnimEntities[1] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_cloud2.png"));
        this.b = (AnimBitmap) this.mAnimEntities[1];
        this.mAnimEntities[2] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_cloud3.png"));
        this.c = (AnimBitmap) this.mAnimEntities[2];
        this.a.setTranslateX(ScalePxUtil.getScalePx(-480, 0));
        this.a.setTranslateY(ScalePxUtil.getScalePx(650, 1));
        this.b.setTranslateX(ScalePxUtil.getScalePx(-293, 0));
        this.b.setTranslateY(ScalePxUtil.getScalePx(650, 1));
        this.c.setTranslateX(ScalePxUtil.getScalePx(-528, 0));
        this.c.setTranslateY(ScalePxUtil.getScalePx(SocketUtil.TYPEID_810, 1));
        this.d = new AnimIntEvaluator(1, 109, this.a.getTranslateX(), ScalePxUtil.getScalePx(SecExceptionCode.SEC_ERROR_OPENSDK, 0));
        this.e = new AnimIntEvaluator(1, 200, this.b.getTranslateX(), ScalePxUtil.getScalePx(1170, 0));
        this.f = new AnimIntEvaluator(1, 200, this.c.getTranslateX(), ScalePxUtil.getScalePx(1170, 0));
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((AnimBitmap) iAnimEntity).animTranslate().draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        int i2 = i % 200;
        if (i2 == 0) {
            i2 = 200;
        }
        if (i2 == 1) {
            this.d.resetEvaluator(i2, 109, ScalePxUtil.getScalePx(-480, 0), ScalePxUtil.getScalePx(SecExceptionCode.SEC_ERROR_OPENSDK, 0));
        }
        if (i2 == 110) {
            this.d.resetEvaluator(i2, 200, ScalePxUtil.getScalePx(-448, 0), ScalePxUtil.getScalePx(SecExceptionCode.SEC_ERROR_OPENSDK, 0));
        }
        this.a.setTranslateX(this.d.evaluate(i2));
        this.b.setTranslateX(this.e.evaluate(i2));
        this.c.setTranslateX(this.f.evaluate(i2));
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[3];
    }
}
