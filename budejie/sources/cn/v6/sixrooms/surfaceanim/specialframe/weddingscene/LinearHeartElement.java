package cn.v6.sixrooms.surfaceanim.specialframe.weddingscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import java.io.File;

public class LinearHeartElement extends SpecialElement {
    private int a;
    private int b;
    private PointI c;
    private PointI d;
    private int e = 255;
    private int f;
    private float g;
    private float h;
    private float i = 0.5f;
    private AnimBitmap j;
    private float k;
    private float l;
    private float m;
    private int n;
    private AnimFloatEvaluator o;
    private AnimFloatEvaluator p;
    private AnimFloatEvaluator q;
    private AnimFloatEvaluator r;
    private AnimIntEvaluator s;
    private boolean t = false;
    private int u = 0;
    private int v = 0;

    public LinearHeartElement(SpecialScene specialScene, int i, int i2) {
        super(specialScene);
        this.a = i;
        this.b = i2;
        this.j = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(specialScene.getSceneParameter().getResPath() + File.separator + "special_wedding_heart.png"));
        this.mAnimEntities[0] = this.j;
        int[] screenSize = AnimSceneResManager.getInstance().getScreenSize();
        if (screenSize[0] > screenSize[1]) {
            this.u = getScalePx(specialScene.getOffsetX());
            this.v = getScalePx(specialScene.getOffsetY());
        }
    }

    public void initParameter(PointI pointI, PointI pointI2, int i, int i2, float f, float f2) {
        if (pointI == null || pointI2 == null || this.u == 0) {
            this.c = pointI;
            this.d = pointI2;
        } else {
            this.c = new PointI(pointI.x + this.u, pointI.y + this.v);
            this.d = new PointI(pointI2.x + this.u, pointI2.y + this.v);
        }
        this.e = i;
        this.f = i2;
        this.g = f;
        this.h = f2;
    }

    public boolean frameControl(int i) {
        if (i < this.a || i > this.b) {
            return true;
        }
        if (!(this.t || this.c == null || this.d == null)) {
            this.t = true;
            this.o = new AnimFloatEvaluator(this.a, this.b, (float) (this.c.x - (this.j.getWidth() / 2)), (float) (this.d.x - (this.j.getWidth() / 2)));
            this.p = new AnimFloatEvaluator(this.a, this.b, (float) (this.c.y - (this.j.getHeight() / 2)), (float) (this.d.y - (this.j.getHeight() / 2)));
            this.q = new AnimFloatEvaluator(this.a, this.b, this.g, this.h);
            this.s = new AnimIntEvaluator(this.a, this.b, this.e, this.f);
            this.r = new AnimFloatEvaluator(this.a, this.b, 0.6f, 1.0f);
        }
        this.k = this.o.evaluate(i);
        this.l = this.p.evaluate(i);
        this.m = this.q.evaluate(i);
        this.n = this.s.evaluate(i);
        this.i = this.r.evaluate(i);
        this.j.getMatrix().setTranslate(this.k, this.l).postScale(this.i, this.i, this.k + ((float) (this.j.getWidth() / 2)), this.l + ((float) (this.j.getHeight() / 2))).postRotate(this.m, this.k + ((float) (this.j.getWidth() / 2)), this.l + ((float) (this.j.getHeight() / 2)));
        this.j.getPaint().setAlpha(this.n);
        return false;
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((AnimBitmap) iAnimEntity).draw(canvas);
        }
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[1];
    }
}
