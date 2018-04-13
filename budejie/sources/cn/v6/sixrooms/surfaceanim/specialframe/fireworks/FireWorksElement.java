package cn.v6.sixrooms.surfaceanim.specialframe.fireworks;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class FireWorksElement extends SpecialElement<AnimBitmap> {
    private Paint a = new Paint();
    private Matrix b;
    private AnimSceneResManager c;
    private Bitmap d;
    private Bitmap e;
    private PointI f;
    private int g;

    public FireWorksElement(AnimScene animScene) {
        super(animScene);
        this.a.setAntiAlias(true);
        this.b = new Matrix();
        this.c = AnimSceneResManager.getInstance();
        this.e = a(((FireWorksScene) this.mAnimScene).mImgHeader + 1);
    }

    public void drawElement(Canvas canvas) {
        this.g = ((this.mCurFrame / 2) % ((FireWorksScene) this.mAnimScene).mRealFrames) + 1;
        this.f = this.mAnimScene.getSceneParameter().getPoint();
        this.d = a(((FireWorksScene) this.mAnimScene).mImgHeader + this.g);
        if (this.f.getX() < this.f.getY()) {
            this.b.setTranslate((float) AnimSceneResManager.getInstance().getScalePx(0), (float) AnimSceneResManager.getInstance().getScalePx(100));
        } else {
            this.b.setTranslate((float) AnimSceneResManager.getInstance().getScalePx(100), (float) AnimSceneResManager.getInstance().getScalePx(0));
        }
        this.b.postScale(2.5f, 2.5f);
        if (this.d != null && !this.d.isRecycled()) {
            canvas.drawBitmap(this.d, this.b, this.a);
            this.e = this.d;
        }
    }

    private Bitmap a(String str) {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + str + ".png");
    }

    public boolean frameControl(int i) {
        return false;
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[0];
    }
}
