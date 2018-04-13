package cn.v6.sixrooms.surfaceanim.specialframe.brachildscene;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class SwingBraElement extends SpecialElement {
    private float A;
    private float B;
    private float C;
    private float D;
    private float E;
    private float F;
    private float G;
    private int H = 0;
    private int I = 0;
    private Bitmap a;
    private Bitmap b;
    private AnimBitmap c;
    private AnimBitmap d;
    private AnimBitmap e;
    private AnimBitmap f;
    private AnimBitmap g;
    private float h;
    private AnimFloatEvaluator i;
    private AnimFloatEvaluator j;
    private AnimFloatEvaluator k;
    private AnimFloatEvaluator l;
    private AnimFloatEvaluator m;
    private AnimFloatEvaluator n;
    private AnimFloatEvaluator o;
    private AnimFloatEvaluator p;
    private int q;
    private int r;
    private int s = 0;
    private float t;
    private float u;
    private float v;
    private float w;
    private float x;
    private float y;
    private float z;

    public SwingBraElement(AnimScene animScene) {
        super(animScene);
        String resPath = ((SpecialScene) animScene).getSceneParameter().getResPath();
        this.a = AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_child.png");
        this.b = AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_head.png");
        this.g = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_head_bra.png"));
        this.d = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_bra.png"));
        this.c = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_body.png"));
        this.e = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_arm.png"));
        this.f = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_arm.png"));
        this.mAnimEntities[0] = this.d;
        this.mAnimEntities[1] = this.f;
        this.mAnimEntities[2] = this.c;
        this.mAnimEntities[3] = this.g;
        this.mAnimEntities[4] = this.e;
        int[] screenSize = AnimSceneResManager.getInstance().getScreenSize();
        if (screenSize[0] > screenSize[1]) {
            this.H = getScalePx(-550);
            this.I = getScalePx(-600);
        }
        this.q = (screenSize[0] - this.a.getWidth()) + this.H;
        this.r = getScalePx(800) + this.I;
        this.v = (float) this.q;
        this.w = (float) (this.r + getScalePx(20));
        this.t = (this.v + ((float) (this.b.getWidth() / 2))) - ((float) getScalePx(50));
        this.u = (this.w + ((float) this.b.getHeight())) - ((float) getScalePx(60));
        this.y = (this.t - ((float) this.e.getWidth())) + ((float) getScalePx(80));
        this.z = this.u + ((float) getScalePx(50));
        this.A = (this.y + ((float) this.c.getWidth())) - ((float) getScalePx(40));
        this.B = this.z;
        this.D = this.y - ((float) (this.d.getWidth() / 4));
        this.E = this.z - ((float) ((this.d.getHeight() * 3) / 4));
        this.C = -45.0f;
        this.G = 1.0f;
        this.i = new AnimFloatEvaluator(69, 70, 0.0f, 140.0f);
        this.k = new AnimFloatEvaluator(70, 72, this.w, this.w + ((float) getScalePx(6)));
        this.l = new AnimFloatEvaluator(77, 80, 0.0f, -20.0f);
        this.m = new AnimFloatEvaluator(1, 1, this.E, this.E);
        this.o = new AnimFloatEvaluator(this.F);
        this.j = new AnimFloatEvaluator(66, 66, 0.0f, this.C);
        this.n = new AnimFloatEvaluator(1, 1, this.D, this.D);
        this.p = new AnimFloatEvaluator(97, 117, this.G, 0.75f);
    }

    public boolean frameControl(int i) {
        if (i < 66 || i > 117) {
            return true;
        }
        this.h = this.i.evaluate(i);
        this.w = this.k.evaluate(i);
        this.x = this.l.evaluate(i);
        this.C = this.j.evaluate(i);
        this.F = this.o.evaluate(i);
        this.E = this.m.evaluate(i);
        this.G = this.p.evaluate(i);
        if (i > 86) {
            this.D = this.n.evaluate(i);
        }
        if (i == 70) {
            this.y += (float) getScalePx(8);
            this.z += (float) getScalePx(4);
            this.i.resetEvaluator(i, 72, this.h, this.h + 20.0f);
        } else if (i == 72) {
            this.k.resetEvaluator(i, 74, this.w, this.w - ((float) getScalePx(6)));
            this.i.resetEvaluator(i, 74, this.h, this.h - 20.0f);
        } else if (i == 74) {
            this.k.resetEvaluator(i, 76, this.w, this.w + ((float) getScalePx(6)));
            this.i.resetEvaluator(i, 76, this.h, this.h + 20.0f);
        } else if (i == 76) {
            this.z -= 4.0f;
            this.k.resetEvaluator(i, 77, this.w, this.w - ((float) getScalePx(6)));
            this.i.resetEvaluator(i, 77, this.h, this.h - 45.0f);
        } else if (i == 77) {
            this.y -= 4.0f;
            this.j.resetEvaluator(i, 78, this.C, this.C + 90.0f);
            this.k.resetEvaluator(i, 80, this.w, this.w + ((float) getScalePx(6)));
            this.i.resetEvaluator(i, 80, this.h, this.h - 50.0f);
        } else if (i == 82) {
            this.s = 255;
            this.l.resetEvaluator(i, 86, this.x, 0.0f);
            this.k.resetEvaluator(i, 86, this.w, this.w - ((float) getScalePx(6)));
        } else if (i == 84) {
            this.D -= (float) (this.d.getWidth() / 4);
        } else if (i == 85) {
            this.D = (this.D - ((float) (this.d.getWidth() / 4))) - ((float) (this.d.getHeight() / 4));
            this.E += 20.0f;
            this.o.resetEvaluator(i, 86, this.F - 80.0f, this.F);
        } else if (i == 86) {
            this.o.resetEvaluator(i, 89, this.F, this.F + 80.0f);
            this.m.resetEvaluator(i, 89, this.E, this.E - ((float) this.c.getHeight()));
            this.n.resetEvaluator(i, 89, this.D, this.D + ((float) getScalePx(30)));
        } else if (i == 87) {
            this.B += 10.0f;
            this.j.resetEvaluator(i, 89, 120.0f, 200.0f);
        } else if (i == 89) {
            this.j.resetEvaluator(i, 92, this.C, 120.0f);
            this.o.resetEvaluator(i, 92, this.F, this.F - 80.0f);
            this.m.resetEvaluator(i, 92, this.E, this.E + ((float) this.c.getHeight()));
            this.n.resetEvaluator(i, 92, this.D, this.D - 30.0f);
        } else if (i == 92) {
            this.j.resetEvaluator(i, 95, this.C, 200.0f);
            this.o.resetEvaluator(i, 95, this.F, this.F + 80.0f);
            this.m.resetEvaluator(i, 95, this.E, this.E - ((float) this.c.getHeight()));
            this.n.resetEvaluator(i, 95, this.D, this.D + ((float) getScalePx(30)));
        } else if (i == 95) {
            this.j.resetEvaluator(i, 97, this.C, 120.0f);
            this.o.resetEvaluator(i, 97, this.F, this.F - 80.0f);
            this.m.resetEvaluator(i, 97, this.E, this.E + ((float) this.c.getHeight()));
            this.n.resetEvaluator(i, 97, this.D, this.D - ((float) getScalePx(30)));
        }
        return false;
    }

    public void drawElement(Canvas canvas) {
        if (this.mCurFrame > 97) {
            canvas.scale(this.G, this.G);
        }
        this.d.getPaint().setAlpha(this.s);
        if (this.mCurFrame >= 82) {
            this.d.getMatrix().setTranslate(this.D, this.E).postRotate(this.F, ((float) this.d.getWidth()) + this.D, ((float) this.d.getHeight()) + this.E);
        }
        if (this.mCurFrame < 78) {
            this.f.getMatrix().setRotate(this.C, (float) (this.e.getWidth() / 2), (float) (this.e.getHeight() / 2)).postTranslate(this.A, this.B);
        } else if (this.mCurFrame >= 78 && this.mCurFrame < 88) {
            this.f.getMatrix().setRotate(this.C).postTranslate(this.A, this.B);
        } else if (this.mCurFrame > 87) {
            this.f.getMatrix().setTranslate(this.A, this.B).postRotate(this.C, this.A + ((float) (this.e.getWidth() / 2)), this.B);
        }
        this.c.getMatrix().setTranslate(this.t, this.u);
        this.g.getMatrix().setTranslate(this.v, this.w).postRotate(this.x, ((float) (this.b.getWidth() / 2)) + this.v, (((float) this.b.getHeight()) + this.w) - ((float) getScalePx(20)));
        if (this.mCurFrame > 80) {
            this.g.setBitmap(this.b);
        }
        this.e.getMatrix().setTranslate(this.y, this.z).postRotate(this.h, this.y + ((float) (this.e.getWidth() / 2)), this.z);
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((AnimBitmap) iAnimEntity).draw(canvas);
        }
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[5];
    }
}
