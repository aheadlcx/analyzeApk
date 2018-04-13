package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.BezierCubeEvaluator;
import com.budejie.www.R$styleable;
import java.io.File;

public class CarRedHeart1Element extends CarRedHeartElement {
    private AnimBitmap[] a;
    private AnimIntEvaluator b;
    private AnimIntEvaluator c;
    private AnimIntEvaluator d;
    private AnimIntEvaluator e;
    private AnimIntEvaluator f;
    private AnimIntEvaluator g;
    private AnimIntEvaluator h;
    private AnimIntEvaluator i;
    private AnimFloatEvaluator j;
    private AnimFloatEvaluator k;
    private AnimFloatEvaluator l;
    private AnimFloatEvaluator m;
    private AnimFloatEvaluator n;
    private AnimFloatEvaluator o;
    private AnimFloatEvaluator p;
    private AnimFloatEvaluator q;
    private BezierCubeEvaluator r;
    private BezierCubeEvaluator s;
    private int t;
    private int u;
    private int v;
    private PointI w = new PointI(45, 80);

    public CarRedHeart1Element(AnimScene animScene) {
        super(animScene);
        this.a = (AnimBitmap[]) animScene.getSceneElement(RoadsterScene.CAR).getAnimEntities();
        ((AnimBitmap[]) this.mAnimEntities)[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_heart_red1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[1] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[0].getBitmap());
        ((AnimBitmap[]) this.mAnimEntities)[2] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_heart_pink1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[3] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[0].getBitmap());
        this.b = new AnimIntEvaluator(-2147483647);
        this.c = new AnimIntEvaluator(-2147483647);
        this.j = new AnimFloatEvaluator(1, 24, -5.0f, 640.0f);
        this.k = new AnimFloatEvaluator(1, 24, 1.0f, 1.85f);
        this.d = new AnimIntEvaluator(1, 24, 255, 0);
        PointI pointI = new PointI(0, getScalePx(521));
        PointI pointI2 = new PointI(getScalePx(R$styleable.Theme_Custom_comment_bottom_more_icom), getScalePx(670));
        PointI pointI3 = new PointI(getScalePx(126), getScalePx(750));
        PointI pointI4 = new PointI(getScalePx(99), getScalePx(1097));
        this.r = new BezierCubeEvaluator(1, 34, pointI, pointI2, pointI3, pointI4);
        this.e = new AnimIntEvaluator(2, 25, 255, 0);
        this.l = new AnimFloatEvaluator(2, 25, -50.0f, 0.0f);
        this.m = new AnimFloatEvaluator(2, 25, 1.0f, 1.85f);
        this.g = new AnimIntEvaluator(-2147483647);
        this.h = new AnimIntEvaluator(-2147483647);
        this.n = new AnimFloatEvaluator(4, 21, -5.0f, -85.0f);
        this.o = new AnimFloatEvaluator(4, 21, 1.0f, 1.85f);
        this.f = new AnimIntEvaluator(4, 21, 255, 0);
        this.s = new BezierCubeEvaluator(1, 25, pointI, pointI2, pointI3, pointI4);
        this.i = new AnimIntEvaluator(2, 15, 255, 0);
        this.p = new AnimFloatEvaluator(2, 15, -5.0f, -100.0f);
        this.q = new AnimFloatEvaluator(2, 25, 1.25f, 2.0f);
    }

    public void setFirstFrame(int i) {
        this.v = i;
    }

    public void setEleRelativeLocation(int i, int i2) {
        PointI pointI = this.w;
        pointI.x += i;
        pointI = this.w;
        pointI.y += i2;
    }

    public void drawElement(Canvas canvas) {
        if (this.mCurFrame >= this.v) {
            ((AnimBitmap[]) this.mAnimEntities)[0].animAlpha().animTranslate().animPostRotate().animPostScale().draw(canvas);
        }
        if (this.mCurFrame >= this.v + 1) {
            ((AnimBitmap[]) this.mAnimEntities)[1].animAlpha().animTranslate().animPostRotate().animPostScale().draw(canvas);
            ((AnimBitmap[]) this.mAnimEntities)[3].animAlpha().animTranslate().animPostRotate().animPostScale().draw(canvas);
        }
        if (this.mCurFrame >= this.v + 3) {
            ((AnimBitmap[]) this.mAnimEntities)[2].animAlpha().animTranslate().animPostRotate().animPostScale().draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i < this.v || i > this.v + 40) {
            return true;
        }
        int i2 = (i - this.v) + 1;
        float evaluate = this.k.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(this.b.evaluate(i2)).setTranslateY(this.c.evaluate(i2)).setRotate(this.j.evaluate(i2)).setScaleX(evaluate).setScaleY(evaluate).setAlpha(this.d.evaluate(i2));
        PointI evaluate2 = this.r.evaluate(i2, true);
        PointI evaluate3 = this.s.evaluate(i2, true);
        if (i2 > 2) {
            float evaluate4 = this.l.evaluate(i2);
            float evaluate5 = this.m.evaluate(i2);
            ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(evaluate2.x - this.t).setTranslateY(evaluate2.y - this.u).setAlpha(this.e.evaluate(i2)).setRotate(evaluate4).setScaleX(evaluate5).setScaleY(evaluate5);
            evaluate4 = this.p.evaluate(i2);
            evaluate5 = this.q.evaluate(i2);
            ((AnimBitmap[]) this.mAnimEntities)[3].setTranslateX(evaluate3.x - this.t).setTranslateY(evaluate3.y - this.u).setAlpha(this.i.evaluate(i2)).setRotate(evaluate4).setScaleX(evaluate5).setScaleY(evaluate5);
        }
        if (i2 >= 4) {
            float evaluate6 = this.n.evaluate(i2);
            evaluate4 = this.o.evaluate(i2);
            ((AnimBitmap[]) this.mAnimEntities)[2].setTranslateX(this.g.evaluate(i2)).setTranslateY(this.h.evaluate(i2)).setAlpha(this.f.evaluate(i2)).setRotate(evaluate6).setScaleX(evaluate4).setScaleY(evaluate4);
        }
        if (i2 == 1) {
            i2 = getScalePx(this.w.x) + this.a[0].getTranslateX();
            int translateY = (this.a[0].getTranslateY() + this.a[0].getHeight()) - getScalePx(this.w.y);
            ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(i2).setTranslateY(translateY);
            this.b.resetEvaluator(1, 24, ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX(), ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX() - getScalePx(75));
            this.c.resetEvaluator(1, 24, ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY(), ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY() - getScalePx(475));
            ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(i2).setTranslateY(translateY);
            ((AnimBitmap[]) this.mAnimEntities)[2].setTranslateX(getScalePx(50) + i2).setTranslateY(translateY - getScalePx(150));
            this.g.resetEvaluator(4, 21, ((AnimBitmap[]) this.mAnimEntities)[2].getTranslateX(), ((AnimBitmap[]) this.mAnimEntities)[2].getTranslateX() - getScalePx(200));
            this.h.resetEvaluator(4, 21, ((AnimBitmap[]) this.mAnimEntities)[2].getTranslateY(), ((AnimBitmap[]) this.mAnimEntities)[2].getTranslateY() - getScalePx(R$styleable.Theme_Custom_top_games_btn_bg));
            ((AnimBitmap[]) this.mAnimEntities)[3].setTranslateX(i2).setTranslateY(translateY);
        } else if (i2 == 2) {
            this.t = evaluate2.x - ((AnimBitmap[]) this.mAnimEntities)[1].getTranslateX();
            this.u = evaluate2.y - ((AnimBitmap[]) this.mAnimEntities)[1].getTranslateY();
            ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(((AnimBitmap[]) this.mAnimEntities)[1].getTranslateX()).setTranslateY(((AnimBitmap[]) this.mAnimEntities)[1].getTranslateY()).setRotate(-50.0f);
            ((AnimBitmap[]) this.mAnimEntities)[3].setTranslateX(((AnimBitmap[]) this.mAnimEntities)[3].getTranslateX()).setTranslateY(((AnimBitmap[]) this.mAnimEntities)[1].getTranslateY()).setRotate(-5.0f);
        }
        return false;
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[4];
    }
}
