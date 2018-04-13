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
import com.budejie.www.R$styleable;
import java.io.File;

public class SuperFireWorksElement extends SpecialElement<AnimBitmap> {
    private Paint a = new Paint();
    private Matrix b;
    private int c;
    private AnimSceneResManager d;
    private Bitmap e;
    private Bitmap f;
    private PointI g;

    public SuperFireWorksElement(AnimScene animScene) {
        super(animScene);
        this.a.setAntiAlias(true);
        this.b = new Matrix();
        this.d = AnimSceneResManager.getInstance();
        this.f = a(((FireWorksScene) this.mAnimScene).mImgHeader + 1);
    }

    public void drawElement(Canvas canvas) {
        this.g = this.mAnimScene.getSceneParameter().getPoint();
        this.e = a(((FireWorksScene) this.mAnimScene).mImgHeader + this.c);
        if (this.g.getX() < this.g.getY()) {
            this.b.setTranslate((float) this.d.getScalePx(0), (float) AnimSceneResManager.getInstance().getScalePx(110));
        } else {
            this.b.setTranslate((float) this.d.getScalePx(110), (float) AnimSceneResManager.getInstance().getScalePx(0));
        }
        this.b.postScale(2.5f, 2.5f);
        if (this.e != null && !this.e.isRecycled()) {
            canvas.drawBitmap(this.e, this.b, this.a);
            this.f = this.e;
        }
    }

    private Bitmap a(String str) {
        return AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + str + ".png");
    }

    public boolean frameControl(int i) {
        if (i <= R$styleable.Theme_Custom_personal_sex_women) {
            this.c = i % 55 == 0 ? 55 : i % 55;
        } else {
            this.c = (((i - 276) / 2) % (((FireWorksScene) this.mAnimScene).mRealFrames - 56)) + 56;
        }
        return false;
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[0];
    }
}
