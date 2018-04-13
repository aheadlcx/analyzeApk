package cn.v6.sixrooms.surfaceanim.specialframe.weddingscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import java.io.File;

public class FlowerElement extends SpecialElement {
    private AnimBitmap a;
    private AnimBitmap b;
    private AnimBitmap c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j = 0.0f;
    private float k;
    private float l;
    private int m;
    private int n;
    private int o;
    private AnimIntEvaluator p;
    private AnimIntEvaluator q;
    private AnimFloatEvaluator r;
    private AnimFloatEvaluator s;
    private AnimFloatEvaluator t;
    private AnimFloatEvaluator u;
    private AnimFloatEvaluator v;
    private int w = 0;
    private int x = 0;

    public FlowerElement(SpecialScene specialScene) {
        super(specialScene);
        this.a = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(specialScene.getSceneParameter().getResPath() + File.separator + "special_wedding_box.png"));
        this.b = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(specialScene.getSceneParameter().getResPath() + File.separator + "special_wedding_flower.png"));
        this.c = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(specialScene.getSceneParameter().getResPath() + File.separator + "special_wedding_rays.png"));
        this.mAnimEntities[0] = this.a;
        this.mAnimEntities[1] = this.b;
        this.mAnimEntities[2] = this.c;
        int[] screenSize = AnimSceneResManager.getInstance().getScreenSize();
        int i = screenSize[1];
        if (screenSize[0] > screenSize[1]) {
            this.w = getScalePx(specialScene.getOffsetX());
            this.x = getScalePx(specialScene.getOffsetY());
            i = screenSize[0];
        }
        this.d = (float) ((-this.a.getWidth()) + this.w);
        this.e = (float) (((i - this.a.getHeight()) - getScalePx(100)) + this.x);
        this.r = new AnimFloatEvaluator(1, 18, this.d, (float) getScalePx(this.w + 120));
        this.s = new AnimFloatEvaluator(1, 18, this.e, (float) getScalePx(this.x + 980));
        this.t = new AnimFloatEvaluator(33, 35, this.j, -10.0f);
        this.p = new AnimIntEvaluator(48, 53, 0, 255);
        this.u = new AnimFloatEvaluator(52, 70, 0.0f, 20.0f);
        this.v = new AnimFloatEvaluator(R$styleable.Theme_Custom_label_subscribe_bg_gd_color, 200, 1.0f, 0.6f);
        this.q = new AnimIntEvaluator(R$styleable.Theme_Custom_label_subscribe_bg_gd_color, 200, 255, 0);
        this.n = 255;
    }

    public boolean frameControl(int i) {
        if (i > 200) {
            return true;
        }
        this.d = this.r.evaluate(i);
        this.e = this.s.evaluate(i);
        this.j = this.t.evaluate(i);
        this.o = this.p.evaluate(i);
        this.k = this.u.evaluate(i);
        this.l = this.v.evaluate(i);
        this.m = this.q.evaluate(i);
        this.f = this.d - ((float) getScalePx(16));
        this.g = this.e - ((float) getScalePx(20));
        this.h = this.d - ((float) getScalePx(300));
        this.i = this.e - ((float) getScalePx(400));
        if (i == 18) {
            this.r.resetEvaluator(i, 23, this.d, this.d + ((float) getScalePx(8)));
            this.s.resetEvaluator(i, 23, this.e, this.e - ((float) getScalePx(8)));
        } else if (i == 23) {
            this.r.resetEvaluator(i, 28, this.d, this.d - ((float) getScalePx(8)));
            this.s.resetEvaluator(i, 28, this.e, this.e + ((float) getScalePx(8)));
        } else if (i == 35) {
            this.t.resetEvaluator(i, 37, this.j, 8.0f);
        } else if (i == 37) {
            this.t.resetEvaluator(i, 39, this.j, -8.0f);
        } else if (i == 39) {
            this.t.resetEvaluator(i, 41, this.j, 0.0f);
        } else if (i == 44) {
            this.t.resetEvaluator(i, 46, this.j, -8.0f);
        } else if (i == 46) {
            this.t.resetEvaluator(i, 48, this.j, 8.0f);
        } else if (i == 48) {
            this.t.resetEvaluator(i, 50, this.j, -8.0f);
        } else if (i == 50) {
            this.t.resetEvaluator(i, 52, this.j, 0.0f);
        } else if (i == 52) {
            this.r.resetEvaluator(i, 55, this.d, this.d + ((float) getScalePx(8)));
        } else if (i == 53) {
            this.n = 0;
        } else if (i == 55) {
            this.r.resetEvaluator(i, 58, this.d, this.d - ((float) getScalePx(8)));
        } else if (i == 58) {
            this.r.resetEvaluator(i, 61, this.d, this.d + ((float) getScalePx(8)));
        } else if (i == 61) {
            this.r.resetEvaluator(i, 61, this.d, this.d - ((float) getScalePx(8)));
        } else if (i == 65) {
            this.r.resetEvaluator(i, 65, this.d, this.d + ((float) getScalePx(8)));
            this.p.resetEvaluator(i, 71, this.o, 0);
        } else if (i == 68) {
            this.r.resetEvaluator(i, 68, this.d, this.d - ((float) getScalePx(8)));
        } else if (i == 104) {
            this.r.resetEvaluator(i, R$styleable.Theme_Custom_new_item_login_phone_bg, this.d, this.d + ((float) getScalePx(60)));
            this.s.resetEvaluator(i, R$styleable.Theme_Custom_new_item_login_phone_bg, this.e, this.e - ((float) getScalePx(270)));
        }
        this.a.getPaint().setAlpha(this.m);
        this.a.getMatrix().setTranslate(this.d, this.e).postRotate(this.j, this.d + ((float) (this.a.getWidth() / 2)), this.e + ((float) (this.a.getHeight() / 2))).postScale(this.l, this.l, this.d + ((float) this.a.getWidth()), this.e - ((float) this.a.getHeight()));
        this.b.getMatrix().setTranslate(this.f, this.g).postRotate(this.j, this.f + ((float) (this.b.getWidth() / 2)), this.g + ((float) (this.b.getHeight() / 2)));
        this.b.setTranslateX((int) this.f).setTranslateY((int) this.g);
        this.b.getPaint().setAlpha(this.n);
        this.c.getMatrix().setTranslate(this.h, this.i).postRotate(this.k, this.h + ((float) (this.c.getWidth() / 2)), this.i + ((float) ((this.c.getHeight() * 2) / 3)));
        this.c.getPaint().setAlpha(this.o);
        return false;
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((AnimBitmap) iAnimEntity).draw(canvas);
        }
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[3];
    }
}
