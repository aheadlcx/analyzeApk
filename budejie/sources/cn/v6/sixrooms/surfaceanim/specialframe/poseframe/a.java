package cn.v6.sixrooms.surfaceanim.specialframe.poseframe;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.specialframe.poseframe.PoseScene.PoseSceneParameter;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;

final class a extends SpecialElement {
    private AnimBitmap a;
    private AnimIntEvaluator b;
    private AnimIntEvaluator c;
    private int d;
    private int e;
    private int f = 60;
    private PointI g;
    private PointI h;
    private Bitmap i;
    private PoseSceneParameter j;
    private boolean k;

    a(AnimScene animScene, PointI pointI, PointI pointI2, int i) {
        super(animScene);
        if (pointI != null) {
            this.j = (PoseSceneParameter) animScene.getSceneParameter();
            this.f = i;
            this.g = pointI;
            this.h = pointI2;
            this.i = this.j.getIcon();
            this.a = new AnimBitmap(this.i);
            this.b = new AnimIntEvaluator(0, 60, this.g.x, this.h.x);
            this.c = new AnimIntEvaluator(0, 60, this.g.y, this.h.y);
        }
    }

    public final void drawElement(Canvas canvas) {
        if (!this.k && this.j.isIconLoaded()) {
            this.a.setBitmap(this.j.getIcon());
            this.k = true;
        }
        if (this.a.getBitmap() != null && !this.a.getBitmap().isRecycled()) {
            this.a.draw(canvas);
        }
    }

    public final boolean frameControl(int i) {
        if (i > this.f) {
            return true;
        }
        this.d = this.b.evaluate(this.mCurFrame);
        this.e = this.c.evaluate(this.mCurFrame);
        this.a.getMatrix().setTranslate((float) this.d, (float) this.e);
        return false;
    }

    public final IAnimEntity[] initAnimEntities() {
        return null;
    }
}
