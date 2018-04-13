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
import com.budejie.www.R$styleable;
import java.io.File;

public class ChildElement extends SpecialElement {
    private float A;
    private float B;
    private float C;
    private int D = 0;
    private int E = 0;
    private int a;
    private Bitmap b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;
    private Bitmap g;
    private Bitmap h;
    private AnimBitmap i;
    private AnimBitmap j;
    private AnimBitmap k;
    private AnimBitmap l;
    private float m;
    private float n = 90.0f;
    private float o = 1.0f;
    private float p = 1.0f;
    private float q;
    private float r;
    private AnimFloatEvaluator s;
    private AnimFloatEvaluator t;
    private AnimFloatEvaluator u;
    private AnimFloatEvaluator v;
    private AnimFloatEvaluator w;
    private AnimFloatEvaluator x;
    private int y;
    private int z;

    public ChildElement(AnimScene animScene) {
        super(animScene);
        String resPath = ((SpecialScene) animScene).getSceneParameter().getResPath();
        this.b = AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_child.png");
        this.c = AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_eye1.png");
        this.d = AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_eye2.png");
        this.e = AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_eye3.png");
        this.f = AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_nipple1.png");
        this.g = AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_nipple2.png");
        this.h = AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_bulb.png");
        this.i = new AnimBitmap(this.b);
        this.j = new AnimBitmap(this.c);
        this.k = new AnimBitmap(this.f);
        this.l = new AnimBitmap(this.h);
        this.mAnimEntities[0] = this.i;
        this.mAnimEntities[1] = this.j;
        this.mAnimEntities[2] = this.k;
        this.mAnimEntities[3] = this.l;
        int[] screenSize = AnimSceneResManager.getInstance().getScreenSize();
        if (screenSize[0] > screenSize[1]) {
            this.D = getScalePx(-550);
            this.E = getScalePx(-600);
        }
        this.y = (screenSize[0] - this.b.getWidth()) + this.D;
        this.z = getScalePx(800) + this.E;
        this.p = 0.8f;
        this.q = (float) (this.y + getScalePx(100));
        this.r = (float) (this.z + getScalePx(400));
        this.A = (float) (this.y - this.h.getWidth());
        this.B = (float) (this.z - (this.h.getHeight() / 2));
        this.C = 1.0f;
        this.s = new AnimFloatEvaluator(1, 8, 23.7f, 0.0f);
        this.t = new AnimFloatEvaluator(27, 32, this.n, 0.0f);
        this.u = new AnimFloatEvaluator(1, 8, 1.0f, this.p);
        this.v = new AnimFloatEvaluator(27, 32, this.q, this.q - ((float) getScalePx(R$styleable.Theme_Custom_label_name_text_color)));
        this.w = new AnimFloatEvaluator(27, 32, this.r, this.r + ((float) getScalePx(300)));
        this.x = new AnimFloatEvaluator(97, 117, this.C, 0.75f);
    }

    public boolean frameControl(int i) {
        if (i > 65) {
            return true;
        }
        this.m = this.s.evaluate(i);
        this.n = this.t.evaluate(i);
        this.p = this.u.evaluate(i);
        this.q = this.v.evaluate(i);
        this.r = this.w.evaluate(i);
        this.C = this.x.evaluate(i);
        if (i == 5) {
            this.c = this.d;
        } else if (i == 8) {
            this.u.resetEvaluator(8, 10, this.p, 1.0f);
        } else if (i == 10) {
            this.u.resetEvaluator(10, 12, this.p, 1.2f);
        } else if (i == 12) {
            this.u.resetEvaluator(12, 16, this.p, 1.0f);
            this.c = this.d;
        } else if (i == 15) {
            this.c = this.e;
        } else if (i == 27) {
            this.f = this.g;
            this.w.resetEvaluator(27, 31, this.r, this.r + ((float) getScalePx(R$styleable.Theme_Custom_forward_icon)));
        } else if (i == 32) {
            this.v.resetEvaluator(32, 41, this.q, this.q - ((float) getScalePx(100)));
            this.w.resetEvaluator(32, 34, this.r, this.r - ((float) getScalePx(20)));
        } else if (i == 34) {
            this.w.resetEvaluator(34, 36, this.r, this.r + ((float) getScalePx(20)));
        } else if (i == 36) {
            this.w.resetEvaluator(36, 37, this.r, this.r - ((float) getScalePx(10)));
        } else if (i == 37) {
            this.w.resetEvaluator(37, 38, this.r, this.r + ((float) getScalePx(10)));
        } else if (i == 38) {
            this.w.resetEvaluator(38, 39, this.r, this.r - ((float) getScalePx(10)));
        } else if (i == 39) {
            this.w.resetEvaluator(39, 40, this.r, this.r + ((float) getScalePx(10)));
        } else if (i == 40) {
            this.w.resetEvaluator(40, 41, this.r, this.r + ((float) getScalePx(5)));
        } else if (i == 54) {
            this.u.resetEvaluator(i, 57, this.p, 0.75f);
        } else if (i == 57) {
            this.u.resetEvaluator(i, 61, this.p, 1.2f);
        } else if (i == 61) {
            this.u.resetEvaluator(i, 65, this.p, 1.0f);
        }
        if (i >= 43) {
            if (i < 47) {
                this.a = 255;
            } else if (i >= 50 && i < 54) {
                this.a = 255;
            }
            this.i.getMatrix().setTranslate((float) this.y, (float) this.z).preScale(this.o, this.p, (float) (this.b.getWidth() / 2), (float) this.b.getHeight()).postRotate(this.m, (float) ((this.b.getWidth() * 3) + this.y), (float) (this.b.getHeight() + this.z));
            this.j.setBitmap(this.c);
            this.j.getMatrix().setTranslate((float) (this.y + getScalePx(40)), (float) (this.z + getScalePx(R$styleable.Theme_Custom_forward_icon))).preScale(this.o, this.p, (float) (this.b.getWidth() / 2), (float) this.b.getHeight()).postRotate(this.m, (float) ((this.b.getWidth() * 3) + this.y), (float) (this.b.getHeight() + this.z));
            this.k.setBitmap(this.f);
            this.k.getMatrix().setTranslate(this.q, this.r);
            if (this.mCurFrame <= 27) {
                this.k.getMatrix().preScale(this.o, this.p, (float) (this.b.getWidth() / 2), (float) this.b.getHeight()).postRotate(this.m, (float) ((this.b.getWidth() * 3) + this.y), (float) (this.b.getHeight() + this.z));
            } else if (this.mCurFrame <= 31) {
                this.k.getMatrix().postRotate(this.n, this.q + ((float) (this.f.getWidth() / 2)), this.r + ((float) (this.f.getHeight() / 2)));
            }
            this.l.getMatrix().setTranslate(this.A, this.B);
            this.l.getPaint().setAlpha(this.a);
            return false;
        }
        this.a = 0;
        this.i.getMatrix().setTranslate((float) this.y, (float) this.z).preScale(this.o, this.p, (float) (this.b.getWidth() / 2), (float) this.b.getHeight()).postRotate(this.m, (float) ((this.b.getWidth() * 3) + this.y), (float) (this.b.getHeight() + this.z));
        this.j.setBitmap(this.c);
        this.j.getMatrix().setTranslate((float) (this.y + getScalePx(40)), (float) (this.z + getScalePx(R$styleable.Theme_Custom_forward_icon))).preScale(this.o, this.p, (float) (this.b.getWidth() / 2), (float) this.b.getHeight()).postRotate(this.m, (float) ((this.b.getWidth() * 3) + this.y), (float) (this.b.getHeight() + this.z));
        this.k.setBitmap(this.f);
        this.k.getMatrix().setTranslate(this.q, this.r);
        if (this.mCurFrame <= 27) {
            this.k.getMatrix().preScale(this.o, this.p, (float) (this.b.getWidth() / 2), (float) this.b.getHeight()).postRotate(this.m, (float) ((this.b.getWidth() * 3) + this.y), (float) (this.b.getHeight() + this.z));
        } else if (this.mCurFrame <= 31) {
            this.k.getMatrix().postRotate(this.n, this.q + ((float) (this.f.getWidth() / 2)), this.r + ((float) (this.f.getHeight() / 2)));
        }
        this.l.getMatrix().setTranslate(this.A, this.B);
        this.l.getPaint().setAlpha(this.a);
        return false;
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((AnimBitmap) iAnimEntity).draw(canvas);
        }
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[4];
    }
}
