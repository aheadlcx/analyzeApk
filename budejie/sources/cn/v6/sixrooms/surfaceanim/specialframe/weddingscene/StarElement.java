package cn.v6.sixrooms.surfaceanim.specialframe.weddingscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import java.io.File;

public class StarElement extends SpecialElement<AnimBitmap> {
    private int a;
    private int b;
    private AnimIntEvaluator c = new AnimIntEvaluator(1, 16, 255, 0);
    private AnimIntEvaluator d = new AnimIntEvaluator(1, 100, 0, 360);
    private AnimIntEvaluator e = new AnimIntEvaluator(1, 15, 0, 255);
    private AnimIntEvaluator f = new AnimIntEvaluator(1, 15, 0, 255);
    private AnimIntEvaluator g = new AnimIntEvaluator(1, 16, 0, 255);
    private int h;
    private int i;
    private int j;
    private int k = (this.h + getScalePx(200));
    private int l = (this.i - getScalePx(50));
    private int m;
    private int n = (this.k + getScalePx(200));
    private int o = (this.l - getScalePx(30));
    private int p;
    private int q = (this.n + getScalePx(R$styleable.Theme_Custom_choose_contact_item_arrow_icon));
    private int r = (this.o + getScalePx(180));
    private int s;
    private int t = (this.h + getScalePx(110));
    private int u = (this.i - getScalePx(130));
    private int v;
    private WeddingScene w;

    public StarElement(AnimScene animScene, int i, int i2, int i3, int i4) {
        super(animScene);
        this.w = (WeddingScene) animScene;
        ((AnimBitmap[]) this.mAnimEntities)[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_star_1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[1] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_star_1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[2] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_star_1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[3] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_star_1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[4] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_star_1.png"));
        this.h = getScalePx((this.w.getOffsetX() + 95) + i3);
        this.i = getScalePx((this.w.getOffsetY() + 950) + i4);
        this.a = i;
        this.b = i2;
    }

    public void drawElement(Canvas canvas) {
        for (AnimBitmap animTranslate : (AnimBitmap[]) this.mAnimEntities) {
            animTranslate.animTranslate().animPostScale().animPostRotate().animAlpha().draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i < this.a || i > this.b) {
            return true;
        }
        int i2 = (i - this.a) % 50;
        if (i2 == 35) {
            this.c.resetEvaluator(i2, 50, 0, 255);
            this.f.resetEvaluator(i2, 50, 255, 0);
        }
        if (i2 == 37) {
            this.e.resetEvaluator(i2, 50, 255, 0);
        }
        if (i2 == 36) {
            this.g.resetEvaluator(i2, 50, 255, 0);
        }
        this.j = this.c.evaluate(i2);
        this.m = this.d.evaluate(i2);
        this.p = this.e.evaluate(i2);
        this.s = this.f.evaluate(i2);
        this.v = this.g.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(this.h).setTranslateY(this.i).setAlpha(this.j);
        ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(this.k).setTranslateY(this.l).setScaleX(0.8f).setScaleY(0.8f).setRotate((float) this.m);
        ((AnimBitmap[]) this.mAnimEntities)[2].setTranslateX(this.n).setTranslateY(this.o).setAlpha(this.p);
        ((AnimBitmap[]) this.mAnimEntities)[3].setTranslateX(this.q).setTranslateY(this.r).setScaleX(0.8f).setScaleY(0.8f).setAlpha(this.s);
        ((AnimBitmap[]) this.mAnimEntities)[4].setTranslateX(this.t).setTranslateY(this.u).setScaleX(0.5f).setScaleY(0.5f).setAlpha(this.v);
        return false;
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[5];
    }
}
