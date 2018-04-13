package cn.v6.sixrooms.surfaceanim.specialframe.yachtscene;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.SimpleAnimBitmap;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneType;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimEvaluatorUtils;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class YachtElement extends SpecialElement {
    private SimpleAnimBitmap a;
    private Bitmap b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private int f = 0;
    private int g = 0;
    private IAnimSceneType h;

    public YachtElement(AnimScene animScene) {
        super(animScene);
        this.h = animScene.getSceneType();
        this.f = ((YachtScene) animScene).getOffsetX();
        this.g = ((YachtScene) animScene).getOffsetY();
        this.a = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_ship1.png"));
        this.a.setOffset((float) this.f, (float) this.g);
        this.mAnimEntities[0] = this.a;
        this.b = AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_ship1.png");
        this.c = AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_ship2.png");
        this.d = AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_ship3.png");
        this.e = AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_ship4.png");
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((SimpleAnimBitmap) iAnimEntity).draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i < 45 || i > 214) {
            return true;
        }
        int i2 = (i - 45) % 4;
        if (i2 == 0) {
            this.a.setBitmap(this.b);
        } else if (i2 == 1) {
            this.a.setBitmap(this.c);
        } else if (i2 == 2) {
            this.a.setBitmap(this.d);
        } else if (i2 == 3) {
            this.a.setBitmap(this.e);
        }
        float f = 0.0f;
        if (i >= 45 && i <= 76) {
            f = AnimEvaluatorUtils.getEvaluator(1090.0f, 550.0f, 45, 32, i);
        } else if (i >= 77 && i <= 103) {
            f = AnimEvaluatorUtils.getEvaluator(550.0f, 102.0f, 76, 27, i);
        } else if (i >= 104 && i <= 184) {
            f = AnimEvaluatorUtils.getEvaluator(102.0f, 22.0f, 103, 81, i);
        } else if (i >= 185 && i <= 214) {
            f = AnimEvaluatorUtils.getEvaluator(22.0f, -1000.0f, 184, 30, i);
        }
        this.a.setMatrixTranslate(f, 900.0f);
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        IAnimEntity[] iAnimEntityArr = new IAnimEntity[1];
        this.mAnimEntities = iAnimEntityArr;
        return iAnimEntityArr;
    }
}
