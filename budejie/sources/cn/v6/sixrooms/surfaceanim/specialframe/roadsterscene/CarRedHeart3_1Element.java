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

public class CarRedHeart3_1Element extends CarRedHeartElement {
    private AnimBitmap[] a;
    private AnimIntEvaluator b;
    private AnimIntEvaluator c;
    private AnimIntEvaluator d;
    private AnimIntEvaluator e;
    private AnimIntEvaluator f;
    private AnimFloatEvaluator g;
    private AnimFloatEvaluator h;
    private AnimFloatEvaluator i;
    private AnimFloatEvaluator j;
    private AnimFloatEvaluator k;
    private AnimFloatEvaluator l;
    private BezierCubeEvaluator m;
    private BezierCubeEvaluator n;
    private BezierCubeEvaluator o;
    private BezierCubeEvaluator p;
    private int q;
    private int r;
    private int s;
    private PointI t = new PointI(40, 75);

    public CarRedHeart3_1Element(AnimScene animScene) {
        super(animScene);
        this.a = (AnimBitmap[]) animScene.getSceneElement(RoadsterScene.CAR).getAnimEntities();
        ((AnimBitmap[]) this.mAnimEntities)[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_heart_red1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[1] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[0].getBitmap());
        ((AnimBitmap[]) this.mAnimEntities)[2] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_heart_pink1.png"));
        PointI pointI = new PointI(getScalePx(683), getScalePx(1327));
        PointI pointI2 = new PointI(getScalePx(620), getScalePx(890));
        PointI pointI3 = new PointI(getScalePx(101), getScalePx(1139));
        PointI pointI4 = new PointI(getScalePx(R$styleable.Theme_Custom_label_list_search_text), getScalePx(879));
        PointI pointI5 = new PointI(getScalePx(R$styleable.Theme_Custom_label_list_search_text), getScalePx(879));
        PointI pointI6 = new PointI(getScalePx(495), getScalePx(815));
        PointI pointI7 = new PointI(getScalePx(R$styleable.Theme_Custom_share_message_theme), getScalePx(682));
        PointI pointI8 = new PointI(getScalePx(R$styleable.Theme_Custom_notice_red_point), getScalePx(661));
        this.b = new AnimIntEvaluator(1, 26, 255, 0);
        this.g = new AnimFloatEvaluator(1, 26, -5.0f, 50.0f);
        this.h = new AnimFloatEvaluator(1, 26, 0.95f, 2.37f);
        this.m = new BezierCubeEvaluator(1, 35, pointI, pointI2, pointI3, pointI4);
        this.c = new AnimIntEvaluator(-2147483647);
        this.d = new AnimIntEvaluator(-2147483647);
        this.e = new AnimIntEvaluator(2, 27, 255, 0);
        this.i = new AnimFloatEvaluator(2, 27, 45.0f, -35.0f);
        this.j = new AnimFloatEvaluator(2, 27, 1.0f, 1.86f);
        this.n = new BezierCubeEvaluator(2, 45, pointI, pointI2, pointI3, pointI4);
        this.f = new AnimIntEvaluator(4, 23, 255, 0);
        this.k = new AnimFloatEvaluator(4, 23, -5.0f, 50.0f);
        this.l = new AnimFloatEvaluator(4, 23, 1.0f, 1.89f);
        this.o = new BezierCubeEvaluator(1, 20, pointI, pointI2, pointI3, pointI4);
        this.p = new BezierCubeEvaluator(20, 28, pointI5, pointI6, pointI7, pointI8);
    }

    public void setFirstFrame(int i) {
        this.s = i + 8;
    }

    public void setEleRelativeLocation(int i, int i2) {
        PointI pointI = this.t;
        pointI.x += i;
        pointI = this.t;
        pointI.y += i2;
    }

    public void drawElement(Canvas canvas) {
        if (this.mCurFrame >= this.s) {
            ((AnimBitmap[]) this.mAnimEntities)[0].animTranslate().animPostScale().animPostRotate().animAlpha().draw(canvas);
        }
        if (this.mCurFrame >= this.s + 1) {
            ((AnimBitmap[]) this.mAnimEntities)[1].animTranslate().animPostScale().animPostRotate().animAlpha().draw(canvas);
        }
        if (this.mCurFrame >= this.s + 3) {
            ((AnimBitmap[]) this.mAnimEntities)[2].animTranslate().animPostScale().animPostRotate().animAlpha().draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i < this.s || i > this.s + 32) {
            return true;
        }
        int i2 = (i - this.s) + 1;
        PointI evaluate = this.m.evaluate(i2);
        PointI evaluate2 = this.n.evaluate(i2);
        PointI evaluate3 = this.o.evaluate(i2);
        if (i2 == 1) {
            int scalePx = getScalePx(this.t.x) + this.a[0].getTranslateX();
            int translateY = (this.a[0].getTranslateY() + this.a[0].getHeight()) - getScalePx(this.t.y);
            this.q = evaluate.x - scalePx;
            this.r = evaluate.y - translateY;
            ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(scalePx).setTranslateY(translateY);
            ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(scalePx).setTranslateY(translateY);
        } else if (i2 == 20) {
            this.o = this.p;
        }
        if (i2 > 0) {
            int i3;
            float evaluate4 = this.h.evaluate(i2) * this.mElementScale;
            if (i2 < 12) {
                scalePx = evaluate.y - this.r;
                i3 = evaluate.x - this.q;
            } else {
                int evaluate5 = this.c.evaluate(i2);
                scalePx = this.d.evaluate(i2);
                i3 = evaluate5;
            }
            if (i2 == 11) {
                this.c.resetEvaluator(i2, 26, i3, i3 - getScalePx(R$styleable.Theme_Custom_shape_cmt_like4_bg));
                this.d.resetEvaluator(i2, 26, scalePx, scalePx - getScalePx(R$styleable.Theme_Custom_staggered_fragment_background));
            }
            ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(i3).setTranslateY(scalePx).setRotate(this.g.evaluate(i2)).setScaleX(evaluate4).setScaleY(evaluate4).setAlpha(this.b.evaluate(i2));
        }
        if (i2 >= 2) {
            float evaluate6 = this.mElementScale * this.j.evaluate(i2);
            ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(evaluate2.x - this.q).setTranslateY(evaluate2.y - this.r).setRotate(this.i.evaluate(i2)).setScaleX(evaluate6).setScaleY(evaluate6).setAlpha(this.e.evaluate(i2));
        }
        if (i2 >= 4) {
            float evaluate7 = this.mElementScale * this.l.evaluate(i2);
            ((AnimBitmap[]) this.mAnimEntities)[2].setTranslateX(evaluate3.x - this.q).setTranslateY(evaluate3.y - this.r).setRotate(this.k.evaluate(i2)).setScaleX(evaluate7).setScaleY(evaluate7).setAlpha(this.f.evaluate(i2));
        }
        return false;
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[3];
    }
}
