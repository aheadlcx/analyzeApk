package cn.v6.sixrooms.surfaceanim.specialframe.weddingscene;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.protocol.PointF;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.BezierSquareFloatEvaluator;
import com.budejie.www.R$styleable;
import java.io.File;

public class CurveHeartElement extends SpecialElement {
    private Bitmap a;
    private AnimBitmap b;
    private int c;
    private int d;
    private int e;
    private float f;
    private float g;
    private AnimIntEvaluator h;
    private AnimFloatEvaluator i;
    private AnimFloatEvaluator j;
    private PointF k;
    private PointF l;
    private PointF m;
    private BezierSquareFloatEvaluator n;
    private int o = 0;
    private int p = 0;

    public CurveHeartElement(SpecialScene specialScene) {
        super(specialScene);
        this.a = AnimSceneResManager.getInstance().getExternalBitmap(specialScene.getSceneParameter().getResPath() + File.separator + "special_wedding_heart1.png");
        this.b = new AnimBitmap(this.a);
        this.mAnimEntities[0] = this.b;
        int[] screenSize = AnimSceneResManager.getInstance().getScreenSize();
        if (screenSize[0] > screenSize[1]) {
            this.o = getScalePx(specialScene.getOffsetX());
            this.p = getScalePx(specialScene.getOffsetY());
        }
        this.k = a(this.o + 480, this.p + 580);
        this.l = a(this.o + 788, this.p + 184);
        this.m = a(this.o + R$styleable.Theme_Custom_comment_recoder_send_color, this.p + 360);
        this.c = ((int) this.k.x) - (this.a.getWidth() / 2);
        this.d = ((int) this.k.y) - (this.a.getHeight() / 2);
        this.f = 0.5f;
        this.e = 255;
        this.i = new AnimFloatEvaluator(R$styleable.Theme_Custom_hot_comment_prompt_text_color_bg, R$styleable.Theme_Custom_shape_cmt_like3_bg, this.f, 1.2f);
        this.j = new AnimFloatEvaluator(this.g);
        this.h = new AnimIntEvaluator(this.e);
        this.n = new BezierSquareFloatEvaluator(this.k, this.m, this.l, 256, R$styleable.Theme_Custom_comment_video_bg);
    }

    private static PointF a(int i, int i2) {
        return new PointF((float) AnimSceneResManager.getInstance().getScalePx(i), (float) AnimSceneResManager.getInstance().getScalePx(i2));
    }

    public boolean frameControl(int i) {
        if (i < R$styleable.Theme_Custom_hot_comment_prompt_text_color_bg || i > R$styleable.Theme_Custom_comment_video_bg) {
            return true;
        }
        this.f = this.i.evaluate(i);
        this.g = this.j.evaluate(i);
        this.e = this.h.evaluate(i);
        PointF evaluate = this.n.evaluate(this.mCurFrame);
        if (evaluate != null) {
            this.c = ((int) evaluate.x) - (this.a.getWidth() / 2);
            this.d = ((int) evaluate.y) - (this.a.getHeight() / 2);
        }
        if (i == R$styleable.Theme_Custom_shape_cmt_like3_bg) {
            this.i.resetEvaluator(i, R$styleable.Theme_Custom_top_suiji_btn_bg, this.f, 0.7f);
        } else if (i == R$styleable.Theme_Custom_top_suiji_btn_bg) {
            this.i.resetEvaluator(i, R$styleable.Theme_Custom_theme_bg_color, this.f, 1.2f);
        } else if (i == R$styleable.Theme_Custom_theme_bg_color) {
            this.i.resetEvaluator(i, 256, this.f, 0.7f);
        } else if (i == 256) {
            this.j.resetEvaluator(i, R$styleable.Theme_Custom_comment_video_bg, 0.0f, 60.0f);
            this.h.resetEvaluator(i, R$styleable.Theme_Custom_comment_video_bg, 255, 0);
        }
        this.b.getPaint().setAlpha(this.e);
        this.b.getMatrix().setTranslate((float) this.c, (float) this.d).preScale(this.f, this.f, (float) (this.a.getWidth() / 2), (float) (this.a.getHeight() / 2)).postRotate(this.g, (float) this.c, (float) this.d);
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[1];
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((AnimBitmap) iAnimEntity).draw(canvas);
        }
    }
}
