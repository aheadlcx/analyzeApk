package cn.v6.sixrooms.surfaceanim.specialframe.weddingscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import java.io.File;

public class FlowerHeartElement extends SpecialElement<AnimBitmap> {
    int a = this.c.getOffsetX();
    int b = this.c.getOffsetY();
    private WeddingScene c;
    private AnimIntEvaluator d;
    private AnimIntEvaluator e;
    private AnimIntEvaluator f;
    private AnimIntEvaluator g;
    private AnimFloatEvaluator h;
    private int i;
    private int j;
    private int k;
    private int l;
    private float m;
    private int n;
    private int o;
    private AnimBitmap p;
    private int q;
    private int r;

    public FlowerHeartElement(AnimScene animScene, int i, int i2) {
        super(animScene);
        this.q = i;
        this.r = i2;
        this.c = (WeddingScene) animScene;
        ((AnimBitmap[]) this.mAnimEntities)[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_flower_heart.png"));
        this.p = (AnimBitmap) this.c.getSceneElement(WeddingScene.FLOWER).getAnimEntities()[1];
        int scalePx = getScalePx(this.a - 35);
        this.n = scalePx;
        this.i = scalePx;
        scalePx = getScalePx(this.b + 50);
        this.o = scalePx;
        this.j = scalePx;
        this.h = new AnimFloatEvaluator(65, 75, 0.4f, 1.0f);
        this.f = new AnimIntEvaluator(65, 75, 30, 0);
        this.g = new AnimIntEvaluator(R$styleable.Theme_Custom_notice_red_point, 303, 255, 0);
    }

    public void drawElement(Canvas canvas) {
        ((AnimBitmap[]) this.mAnimEntities)[0].animTranslate().animPostRotate().animPostScale().animAlpha().draw(canvas);
    }

    public boolean frameControl(int i) {
        if (i < this.q || i > this.r) {
            return true;
        }
        if (i == 65) {
            this.d = new AnimIntEvaluator(65, 75, this.p.getTranslateX() - getScalePx(300), this.n);
            this.e = new AnimIntEvaluator(65, 75, this.p.getTranslateY() - getScalePx(400), this.o);
        }
        if (i == 85) {
            this.h.resetEvaluator(i, 104, 1.0f, 1.03f);
        }
        if (i == 104) {
            this.h.resetEvaluator(i, R$styleable.Theme_Custom_account_item_content_layout_bg, 1.03f, 1.0f);
        }
        if (i == R$styleable.Theme_Custom_notice_red_point) {
            this.h.resetEvaluator(i, 303, 1.0f, 0.5f);
        }
        this.l = this.g.evaluate(i);
        this.k = this.f.evaluate(i);
        if (this.d != null) {
            this.i = this.d.evaluate(i);
        }
        if (this.e != null) {
            this.j = this.e.evaluate(i);
        }
        this.m = this.h.evaluate(i);
        ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(this.i).setTranslateY(this.j).setRotate((float) this.k).setScale(this.m).setAlpha(this.l);
        return false;
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[1];
    }
}
