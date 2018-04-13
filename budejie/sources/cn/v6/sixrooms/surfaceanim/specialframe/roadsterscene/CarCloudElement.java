package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import java.io.File;

public class CarCloudElement extends SpecialElement<AnimBitmap> {
    private AnimBitmap[] a;
    private AnimIntEvaluator b = new AnimIntEvaluator(7, 20, 0, 255);
    private AnimIntEvaluator c = new AnimIntEvaluator(-2147483647);
    private AnimIntEvaluator d = new AnimIntEvaluator(3, 14, 0, 255);
    private AnimIntEvaluator e = new AnimIntEvaluator(-2147483647);
    private AnimIntEvaluator f = new AnimIntEvaluator(8, 18, 0, 255);
    private AnimIntEvaluator g = new AnimIntEvaluator(-2147483647);
    private AnimIntEvaluator h = new AnimIntEvaluator(1, 12, 0, 255);
    private AnimIntEvaluator i = new AnimIntEvaluator(2, 14, 0, 255);
    private AnimIntEvaluator j = new AnimIntEvaluator(-2147483647);
    private AnimIntEvaluator k = new AnimIntEvaluator(-2147483647);
    private AnimIntEvaluator l = new AnimIntEvaluator(5, 18, 0, 255);
    private AnimIntEvaluator m = new AnimIntEvaluator(-2147483647);
    private AnimIntEvaluator n = new AnimIntEvaluator(-2147483647);
    private AnimFloatEvaluator o = new AnimFloatEvaluator(7, 33, 0.78f, 1.22f);
    private AnimFloatEvaluator p = new AnimFloatEvaluator(3, 25, 0.7f, 1.0f);
    private AnimFloatEvaluator q = new AnimFloatEvaluator(8, 29, 0.8f, 1.3f);
    private AnimFloatEvaluator r = new AnimFloatEvaluator(1, 12, 0.65f, 0.8f);
    private AnimFloatEvaluator s = new AnimFloatEvaluator(2, 14, 0.85f, 1.0f);
    private AnimFloatEvaluator t = new AnimFloatEvaluator(5, 18, 0.69f, 0.86f);

    public CarCloudElement(AnimScene animScene) {
        super(animScene);
        this.a = (AnimBitmap[]) animScene.getSceneElement(RoadsterScene.CAR).getAnimEntities();
        ((AnimBitmap[]) this.mAnimEntities)[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_cloud2.png"));
        ((AnimBitmap[]) this.mAnimEntities)[1] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_cloud3.png"));
        ((AnimBitmap[]) this.mAnimEntities)[2] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[0].getBitmap());
        ((AnimBitmap[]) this.mAnimEntities)[3] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[1].getBitmap());
        ((AnimBitmap[]) this.mAnimEntities)[4] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[1].getBitmap());
        ((AnimBitmap[]) this.mAnimEntities)[5] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_cloud1.png"));
    }

    public void drawElement(Canvas canvas) {
        if (this.mCurFrame >= 17) {
            ((AnimBitmap[]) this.mAnimEntities)[0].getPaint().setAlpha(((AnimBitmap[]) this.mAnimEntities)[0].getAlpha());
            ((AnimBitmap[]) this.mAnimEntities)[0].getMatrix().setTranslate((float) ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX(), (float) ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY()).postScale(((AnimBitmap[]) this.mAnimEntities)[0].getScaleX(), ((AnimBitmap[]) this.mAnimEntities)[0].getScaleY(), (float) (((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX() + (((AnimBitmap[]) this.mAnimEntities)[0].getWidth() / 2)), (float) (((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY() + (((AnimBitmap[]) this.mAnimEntities)[0].getHeight() / 2)));
            ((AnimBitmap[]) this.mAnimEntities)[0].draw(canvas);
        }
        if (this.mCurFrame >= 13) {
            ((AnimBitmap[]) this.mAnimEntities)[1].getPaint().setAlpha(((AnimBitmap[]) this.mAnimEntities)[1].getAlpha());
            ((AnimBitmap[]) this.mAnimEntities)[1].getMatrix().setTranslate((float) ((AnimBitmap[]) this.mAnimEntities)[1].getTranslateX(), (float) ((AnimBitmap[]) this.mAnimEntities)[1].getTranslateY()).postScale(((AnimBitmap[]) this.mAnimEntities)[1].getScaleX(), ((AnimBitmap[]) this.mAnimEntities)[1].getScaleY(), (float) (((AnimBitmap[]) this.mAnimEntities)[1].getTranslateX() + (((AnimBitmap[]) this.mAnimEntities)[1].getWidth() / 2)), (float) (((AnimBitmap[]) this.mAnimEntities)[1].getTranslateY() + (((AnimBitmap[]) this.mAnimEntities)[1].getHeight() / 2)));
            ((AnimBitmap[]) this.mAnimEntities)[1].draw(canvas);
        }
        if (this.mCurFrame >= 18) {
            ((AnimBitmap[]) this.mAnimEntities)[2].getPaint().setAlpha(((AnimBitmap[]) this.mAnimEntities)[2].getAlpha());
            ((AnimBitmap[]) this.mAnimEntities)[2].getMatrix().setTranslate((float) ((AnimBitmap[]) this.mAnimEntities)[2].getTranslateX(), (float) ((AnimBitmap[]) this.mAnimEntities)[2].getTranslateY()).postScale(((AnimBitmap[]) this.mAnimEntities)[2].getScaleX(), ((AnimBitmap[]) this.mAnimEntities)[2].getScaleY(), (float) (((AnimBitmap[]) this.mAnimEntities)[2].getTranslateX() + (((AnimBitmap[]) this.mAnimEntities)[2].getWidth() / 2)), (float) (((AnimBitmap[]) this.mAnimEntities)[2].getTranslateY() + (((AnimBitmap[]) this.mAnimEntities)[2].getHeight() / 2)));
            ((AnimBitmap[]) this.mAnimEntities)[2].draw(canvas);
        }
        if (this.mCurFrame >= 11) {
            ((AnimBitmap[]) this.mAnimEntities)[3].getPaint().setAlpha(((AnimBitmap[]) this.mAnimEntities)[3].getAlpha());
            ((AnimBitmap[]) this.mAnimEntities)[3].getMatrix().setTranslate((float) ((AnimBitmap[]) this.mAnimEntities)[3].getTranslateX(), (float) ((AnimBitmap[]) this.mAnimEntities)[3].getTranslateY()).postScale(((AnimBitmap[]) this.mAnimEntities)[3].getScaleX(), ((AnimBitmap[]) this.mAnimEntities)[3].getScaleY(), (float) (((AnimBitmap[]) this.mAnimEntities)[3].getTranslateX() + (((AnimBitmap[]) this.mAnimEntities)[3].getWidth() / 2)), (float) (((AnimBitmap[]) this.mAnimEntities)[3].getTranslateY() + (((AnimBitmap[]) this.mAnimEntities)[3].getHeight() / 2)));
            ((AnimBitmap[]) this.mAnimEntities)[3].draw(canvas);
        }
        if (this.mCurFrame >= 12) {
            ((AnimBitmap[]) this.mAnimEntities)[4].getPaint().setAlpha(((AnimBitmap[]) this.mAnimEntities)[4].getAlpha());
            ((AnimBitmap[]) this.mAnimEntities)[4].getMatrix().setTranslate((float) ((AnimBitmap[]) this.mAnimEntities)[4].getTranslateX(), (float) ((AnimBitmap[]) this.mAnimEntities)[4].getTranslateY()).postScale(((AnimBitmap[]) this.mAnimEntities)[4].getScaleX(), ((AnimBitmap[]) this.mAnimEntities)[4].getScaleY(), (float) (((AnimBitmap[]) this.mAnimEntities)[4].getTranslateX() + (((AnimBitmap[]) this.mAnimEntities)[4].getWidth() / 2)), (float) (((AnimBitmap[]) this.mAnimEntities)[4].getTranslateY() + (((AnimBitmap[]) this.mAnimEntities)[4].getHeight() / 2))).postRotate(175.0f, (float) (((AnimBitmap[]) this.mAnimEntities)[4].getTranslateX() + (((AnimBitmap[]) this.mAnimEntities)[4].getWidth() / 2)), (float) (((AnimBitmap[]) this.mAnimEntities)[4].getTranslateY() + (((AnimBitmap[]) this.mAnimEntities)[4].getHeight() / 2)));
            ((AnimBitmap[]) this.mAnimEntities)[4].draw(canvas);
        }
        if (this.mCurFrame >= 15) {
            ((AnimBitmap[]) this.mAnimEntities)[5].getPaint().setAlpha(((AnimBitmap[]) this.mAnimEntities)[5].getAlpha());
            ((AnimBitmap[]) this.mAnimEntities)[5].getMatrix().setTranslate((float) ((AnimBitmap[]) this.mAnimEntities)[5].getTranslateX(), (float) ((AnimBitmap[]) this.mAnimEntities)[5].getTranslateY()).postScale(((AnimBitmap[]) this.mAnimEntities)[5].getScaleX(), ((AnimBitmap[]) this.mAnimEntities)[5].getScaleY(), (float) (((AnimBitmap[]) this.mAnimEntities)[5].getTranslateX() + (((AnimBitmap[]) this.mAnimEntities)[5].getWidth() / 2)), (float) (((AnimBitmap[]) this.mAnimEntities)[5].getTranslateY() + (((AnimBitmap[]) this.mAnimEntities)[5].getHeight() / 2)));
            ((AnimBitmap[]) this.mAnimEntities)[5].draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i < 11 || i > 43) {
            return true;
        }
        int i2 = i - 10;
        Float valueOf = Float.valueOf(this.o.evaluate(i2));
        ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(this.b.evaluate(i2)).setTranslateY(this.c.evaluate(i2)).setScaleX(valueOf.floatValue()).setScaleY(valueOf.floatValue());
        if (i2 == 7) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(this.a[0].getTranslateX() - AnimSceneResManager.getInstance().getScalePx(55)).setTranslateY((this.a[0].getTranslateY() + this.a[0].getHeight()) - AnimSceneResManager.getInstance().getScalePx((int) R$styleable.Theme_Custom_send_btn_text_color));
            this.c.resetEvaluator(7, 33, ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY(), ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY() - AnimSceneResManager.getInstance().getScalePx(64));
        } else if (i2 == 20) {
            this.b.resetEvaluator(i2, 33, 255, 0);
        }
        float evaluate = this.p.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[1].setScaleX(evaluate).setScaleY(evaluate).setAlpha(this.d.evaluate(i2));
        if (i2 == 3) {
            ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(this.a[0].getTranslateX() + AnimSceneResManager.getInstance().getScalePx(60)).setTranslateY((this.a[0].getTranslateY() + this.a[0].getHeight()) - AnimSceneResManager.getInstance().getScalePx((int) R$styleable.Theme_Custom_send_btn_text_color));
        } else if (i2 == 14) {
            this.d.resetEvaluator(i2, 25, 255, 0);
        }
        valueOf = Float.valueOf(this.q.evaluate(i2));
        ((AnimBitmap[]) this.mAnimEntities)[2].setAlpha(this.f.evaluate(i2)).setTranslateX(this.e.evaluate(i2)).setScaleX(valueOf.floatValue()).setScaleY(valueOf.floatValue());
        if (i2 == 8) {
            ((AnimBitmap[]) this.mAnimEntities)[2].setTranslateX(this.a[0].getTranslateX() + AnimSceneResManager.getInstance().getScalePx(250)).setTranslateY((this.a[0].getTranslateY() + this.a[0].getHeight()) - AnimSceneResManager.getInstance().getScalePx((int) R$styleable.Theme_Custom_label_pinner_tabs_bg));
            this.e.resetEvaluator(8, 33, ((AnimBitmap[]) this.mAnimEntities)[2].getTranslateX(), ((AnimBitmap[]) this.mAnimEntities)[2].getTranslateX() + AnimSceneResManager.getInstance().getScalePx(50));
        } else if (i2 == 18) {
            this.f.resetEvaluator(i2, 29, 255, 0);
        }
        valueOf = Float.valueOf(this.r.evaluate(i2));
        ((AnimBitmap[]) this.mAnimEntities)[3].setAlpha(this.h.evaluate(i2)).setTranslateY(this.g.evaluate(i2)).setScaleX(valueOf.floatValue()).setScaleY(valueOf.floatValue());
        if (i2 == 1) {
            ((AnimBitmap[]) this.mAnimEntities)[3].setTranslateX(this.a[0].getTranslateX() + AnimSceneResManager.getInstance().getScalePx(445)).setTranslateY((this.a[0].getTranslateY() + this.a[0].getHeight()) - AnimSceneResManager.getInstance().getScalePx((int) R$styleable.Theme_Custom_attention_right));
            this.g.resetEvaluator(1, 24, ((AnimBitmap[]) this.mAnimEntities)[3].getTranslateY(), ((AnimBitmap[]) this.mAnimEntities)[3].getTranslateY() - AnimSceneResManager.getInstance().getScalePx(95));
        } else if (i2 == 12) {
            this.h.resetEvaluator(i2, 24, 255, 0);
            this.r.resetEvaluator(i2, 24, 0.8f, 1.35f);
        }
        valueOf = Float.valueOf(this.s.evaluate(i2));
        ((AnimBitmap[]) this.mAnimEntities)[4].setAlpha(this.i.evaluate(i2)).setTranslateY(this.k.evaluate(i2)).setTranslateX(this.j.evaluate(i2)).setScaleX(valueOf.floatValue()).setScaleY(valueOf.floatValue());
        if (i2 == 2) {
            ((AnimBitmap[]) this.mAnimEntities)[4].setTranslateX(this.a[0].getTranslateX() + AnimSceneResManager.getInstance().getScalePx(600)).setTranslateY((this.a[0].getTranslateY() + this.a[0].getHeight()) - AnimSceneResManager.getInstance().getScalePx(200));
            this.k.resetEvaluator(2, 14, ((AnimBitmap[]) this.mAnimEntities)[4].getTranslateY(), ((AnimBitmap[]) this.mAnimEntities)[4].getTranslateY() - AnimSceneResManager.getInstance().getScalePx(26));
            this.j.resetEvaluator(14, 26, ((AnimBitmap[]) this.mAnimEntities)[4].getTranslateX(), ((AnimBitmap[]) this.mAnimEntities)[4].getTranslateX() + AnimSceneResManager.getInstance().getScalePx(16));
        } else if (i2 == 14) {
            this.i.resetEvaluator(i2, 26, 255, 0);
            this.s.resetEvaluator(i2, 26, 1.0f, 1.3f);
            this.k.resetEvaluator(i2, 26, ((AnimBitmap[]) this.mAnimEntities)[4].getTranslateY(), ((AnimBitmap[]) this.mAnimEntities)[4].getTranslateY() - AnimSceneResManager.getInstance().getScalePx(10));
        }
        valueOf = Float.valueOf(this.t.evaluate(i2));
        ((AnimBitmap[]) this.mAnimEntities)[5].setAlpha(this.l.evaluate(i2)).setTranslateY(this.n.evaluate(i2)).setTranslateX(this.m.evaluate(i2)).setScaleX(valueOf.floatValue()).setScaleY(valueOf.floatValue());
        if (i2 == 5) {
            ((AnimBitmap[]) this.mAnimEntities)[5].setTranslateX(this.a[0].getTranslateX() + AnimSceneResManager.getInstance().getScalePx(820)).setTranslateY((this.a[0].getTranslateY() + this.a[0].getHeight()) - AnimSceneResManager.getInstance().getScalePx((int) R$styleable.Theme_Custom_send_btn_text_color));
            this.n.resetEvaluator(5, 18, ((AnimBitmap[]) this.mAnimEntities)[5].getTranslateY(), ((AnimBitmap[]) this.mAnimEntities)[5].getTranslateY() - AnimSceneResManager.getInstance().getScalePx(25));
            this.m.resetEvaluator(5, 18, ((AnimBitmap[]) this.mAnimEntities)[5].getTranslateX(), ((AnimBitmap[]) this.mAnimEntities)[5].getTranslateX() + AnimSceneResManager.getInstance().getScalePx(18));
        } else if (i2 == 18) {
            this.l.resetEvaluator(i2, 31, 255, 0);
            this.t.resetEvaluator(i2, 31, ((AnimBitmap[]) this.mAnimEntities)[5].getScaleX(), 1.0f);
            this.n.resetEvaluator(i2, 31, ((AnimBitmap[]) this.mAnimEntities)[5].getTranslateY(), ((AnimBitmap[]) this.mAnimEntities)[5].getTranslateY() - AnimSceneResManager.getInstance().getScalePx(30));
            this.m.resetEvaluator(i2, 31, ((AnimBitmap[]) this.mAnimEntities)[5].getTranslateX(), ((AnimBitmap[]) this.mAnimEntities)[5].getTranslateX() + AnimSceneResManager.getInstance().getScalePx(28));
        }
        return false;
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[6];
    }
}
