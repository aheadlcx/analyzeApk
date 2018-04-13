package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.BezierCubeEvaluator;
import com.alibaba.wireless.security.SecExceptionCode;
import com.budejie.www.R$styleable;
import java.io.File;

public class CarRedHeart2Element extends CarRedHeartElement {
    private BezierCubeEvaluator A;
    private int B;
    private int C;
    private int D;
    private PointI E = new PointI(85, 95);
    private AnimBitmap[] a;
    private AnimIntEvaluator b;
    private AnimIntEvaluator c;
    private AnimIntEvaluator d;
    private AnimIntEvaluator e;
    private AnimIntEvaluator f;
    private AnimIntEvaluator g;
    private AnimIntEvaluator h;
    private AnimIntEvaluator i;
    private AnimIntEvaluator j;
    private AnimFloatEvaluator k;
    private AnimFloatEvaluator l;
    private AnimFloatEvaluator m;
    private AnimFloatEvaluator n;
    private AnimFloatEvaluator o;
    private AnimFloatEvaluator p;
    private AnimFloatEvaluator q;
    private AnimFloatEvaluator r;
    private AnimFloatEvaluator s;
    private AnimFloatEvaluator t;
    private AnimFloatEvaluator u;
    private AnimFloatEvaluator v;
    private BezierCubeEvaluator w;
    private BezierCubeEvaluator x;
    private BezierCubeEvaluator y;
    private BezierCubeEvaluator z;

    public CarRedHeart2Element(AnimScene animScene) {
        super(animScene);
        this.a = (AnimBitmap[]) animScene.getSceneElement(RoadsterScene.CAR).getAnimEntities();
        ((AnimBitmap[]) this.mAnimEntities)[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_heart_red1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[1] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[0].getBitmap());
        ((AnimBitmap[]) this.mAnimEntities)[2] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_heart_pink1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[3] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[0].getBitmap());
        ((AnimBitmap[]) this.mAnimEntities)[4] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[0].getBitmap());
        ((AnimBitmap[]) this.mAnimEntities)[5] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[0].getBitmap());
        PointI pointI = new PointI(0, getScalePx(1573));
        PointI pointI2 = new PointI(getScalePx(211), getScalePx(1233));
        PointI pointI3 = new PointI(getScalePx(R$styleable.Theme_Custom_label_name_text_color), getScalePx(SecExceptionCode.SEC_ERROE_OPENSDK_INVALID_LENGTH));
        PointI pointI4 = new PointI(getScalePx(64), getScalePx(947));
        this.b = new AnimIntEvaluator(1, 23, 255, 0);
        this.k = new AnimFloatEvaluator(1, 23, 30.0f, -410.0f);
        this.l = new AnimFloatEvaluator(1, 23, 1.0f, 2.72f);
        this.w = new BezierCubeEvaluator(1, 33, pointI, pointI2, pointI3, pointI4);
        this.c = new AnimIntEvaluator(2, 24, 255, 0);
        this.m = new AnimFloatEvaluator(2, 24, -50.0f, 40.0f);
        this.n = new AnimFloatEvaluator(2, 24, 1.0f, 1.86f);
        this.x = new BezierCubeEvaluator(2, 49, pointI, pointI2, pointI3, pointI4);
        this.d = new AnimIntEvaluator(4, 20, 255, 0);
        this.o = new AnimFloatEvaluator(4, 20, 5.0f, -50.0f);
        this.p = new AnimFloatEvaluator(4, 20, 1.0f, 2.68f);
        this.z = new BezierCubeEvaluator(1, 22, pointI, pointI2, pointI3, pointI4);
        this.e = new AnimIntEvaluator(2, 25, 255, 0);
        this.q = new AnimFloatEvaluator(2, 25, 10.0f, 630.0f);
        this.r = new AnimFloatEvaluator(2, 25, 1.0f, 1.6f);
        this.f = new AnimIntEvaluator(-2147483647);
        this.g = new AnimIntEvaluator(3, 26, 255, 0);
        this.s = new AnimFloatEvaluator(3, 26, 10.0f, -105.0f);
        this.t = new AnimFloatEvaluator(3, 26, 1.0f, 1.91f);
        this.y = new BezierCubeEvaluator(3, 100, pointI, pointI2, pointI3, pointI4);
        this.h = new AnimIntEvaluator(3, 26, 0, ((AnimBitmap[]) this.mAnimEntities)[4].getWidth() / 2);
        this.i = new AnimIntEvaluator(2, 25, 255, 0);
        this.u = new AnimFloatEvaluator(2, 25, 10.0f, 720.0f);
        this.v = new AnimFloatEvaluator(2, 25, 1.0f, 2.38f);
        this.A = new BezierCubeEvaluator(2, 95, pointI, pointI2, pointI3, pointI4);
        this.j = new AnimIntEvaluator(2, 25, 0, getScalePx(95));
    }

    public void setFirstFrame(int i) {
        this.D = i + 3;
    }

    public void setEleRelativeLocation(int i, int i2) {
        PointI pointI = this.E;
        pointI.x += i;
        pointI = this.E;
        pointI.y += i2;
    }

    public void drawElement(Canvas canvas) {
        if (this.mCurFrame >= this.D) {
            ((AnimBitmap[]) this.mAnimEntities)[0].animTranslate().animPostScale().animPostRotate().animAlpha().draw(canvas);
        }
        if (this.mCurFrame >= this.D + 1) {
            ((AnimBitmap[]) this.mAnimEntities)[1].animTranslate().animPostScale().animPostRotate().animAlpha().draw(canvas);
            ((AnimBitmap[]) this.mAnimEntities)[3].animTranslate().animPostScale().animPostRotate().animAlpha().draw(canvas);
            ((AnimBitmap[]) this.mAnimEntities)[5].animTranslate().animPostScale().animPostRotate().animAlpha().draw(canvas);
        }
        if (this.mCurFrame >= this.D + 2) {
            ((AnimBitmap[]) this.mAnimEntities)[4].animTranslate().animPostScale().animPostRotate().animAlpha().draw(canvas);
        }
        if (this.mCurFrame >= this.D + 3) {
            ((AnimBitmap[]) this.mAnimEntities)[2].animTranslate().animPostScale().animPostRotate().animAlpha().draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i < this.D || i > this.D + 37) {
            return true;
        }
        int i2 = (i - this.D) + 1;
        PointI evaluate = this.w.evaluate(i2);
        PointI evaluate2 = this.z.evaluate(i2);
        if (i2 == 1) {
            int scalePx = getScalePx(this.E.x) + this.a[0].getTranslateX();
            int translateY = (this.a[0].getTranslateY() + this.a[0].getHeight()) - getScalePx(this.E.y);
            this.B = evaluate.x - scalePx;
            this.C = evaluate.y - translateY;
            ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(scalePx).setTranslateY(translateY);
            ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(scalePx).setTranslateY(translateY);
            ((AnimBitmap[]) this.mAnimEntities)[2].setTranslateX(scalePx).setTranslateY(translateY);
            ((AnimBitmap[]) this.mAnimEntities)[3].setTranslateX(scalePx).setTranslateY(translateY);
            this.f.resetEvaluator(2, 25, ((AnimBitmap[]) this.mAnimEntities)[3].getTranslateY(), ((AnimBitmap[]) this.mAnimEntities)[3].getTranslateY() - getScalePx(R$styleable.Theme_Custom_history_post_content_text_color));
            ((AnimBitmap[]) this.mAnimEntities)[4].setTranslateX(scalePx).setTranslateY(translateY);
        }
        if (i2 > 0) {
            float evaluate3 = this.l.evaluate(i2);
            ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(evaluate.x - this.B).setTranslateY(evaluate.y - this.C).setRotate(this.k.evaluate(i2)).setScaleX(evaluate3).setScaleY(evaluate3).setAlpha(this.b.evaluate(i2));
            float evaluate4 = this.p.evaluate(i2);
            ((AnimBitmap[]) this.mAnimEntities)[2].setTranslateX(evaluate2.x - this.B).setTranslateY(evaluate2.y - this.C).setRotate(this.o.evaluate(i2)).setScaleX(evaluate4).setScaleY(evaluate4).setAlpha(this.d.evaluate(i2));
        }
        evaluate = this.x.evaluate(i2);
        evaluate2 = this.A.evaluate(i2);
        if (i2 >= 2) {
            evaluate3 = this.n.evaluate(i2);
            ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(evaluate.x - this.B).setTranslateY(evaluate.y - this.C).setRotate(this.m.evaluate(i2)).setScaleX(evaluate3).setScaleY(evaluate3).setAlpha(this.c.evaluate(i2));
            evaluate4 = this.r.evaluate(i2);
            ((AnimBitmap[]) this.mAnimEntities)[3].setTranslateY(this.f.evaluate(i2)).setRotate(this.q.evaluate(i2)).setScaleX(evaluate4).setScaleY(evaluate4).setAlpha(this.e.evaluate(i2));
            evaluate4 = this.v.evaluate(i2);
            ((AnimBitmap[]) this.mAnimEntities)[5].setTranslateX((evaluate2.x - this.B) + this.j.evaluate(i2)).setTranslateY(evaluate2.y - this.C).setRotate(this.u.evaluate(i2)).setScaleX(evaluate4).setScaleY(evaluate4).setAlpha(this.i.evaluate(i2));
        }
        evaluate = this.y.evaluate(i2);
        if (i2 >= 3) {
            float evaluate5 = this.t.evaluate(i2);
            ((AnimBitmap[]) this.mAnimEntities)[4].setTranslateX((evaluate.x - this.B) - this.h.evaluate(i2)).setTranslateY(evaluate.y - this.C).setRotate(this.s.evaluate(i2)).setScaleX(evaluate5).setScaleY(evaluate5).setAlpha(this.g.evaluate(i2));
        }
        return false;
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[6];
    }
}
