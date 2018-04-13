package cn.v6.sixrooms.surfaceanim.specialframe.brachildscene;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.protocol.PointF;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.BezierSquareFloatEvaluator;
import com.ali.auth.third.core.model.SystemMessageConstants;
import com.budejie.www.R$styleable;
import com.umeng.update.util.a;
import java.io.File;

public class HeartElement extends SpecialElement {
    private float A;
    private float B;
    private float C;
    private float D;
    private float E;
    private AnimIntEvaluator F;
    private AnimIntEvaluator G;
    private AnimIntEvaluator H;
    private AnimFloatEvaluator I;
    private AnimFloatEvaluator J;
    private AnimFloatEvaluator K;
    private AnimFloatEvaluator L;
    private AnimFloatEvaluator M;
    private PointF N;
    private PointF O;
    private PointF P;
    private PointF Q;
    private PointF R;
    private PointF S;
    private PointF T;
    private PointF U;
    private PointF V;
    private PointF W;
    private PointF X;
    private PointF Y;
    private PointF Z;
    private Bitmap a;
    private BezierSquareFloatEvaluator aa;
    private BezierSquareFloatEvaluator ab;
    private BezierSquareFloatEvaluator ac;
    private BezierSquareFloatEvaluator ad;
    private BezierSquareFloatEvaluator ae;
    private BezierSquareFloatEvaluator af;
    private BezierSquareFloatEvaluator ag;
    private int ah = 0;
    private int ai = 0;
    private Bitmap b;
    private AnimBitmap c;
    private AnimBitmap d;
    private AnimBitmap e;
    private AnimBitmap f;
    private AnimBitmap g;
    private AnimBitmap h;
    private AnimBitmap i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    private static PointF a(int i, int i2) {
        return new PointF((float) AnimSceneResManager.getInstance().getScalePx(i), (float) AnimSceneResManager.getInstance().getScalePx(i2));
    }

    public HeartElement(AnimScene animScene) {
        super(animScene);
        String resPath = ((SpecialScene) this.mAnimScene).getSceneParameter().getResPath();
        this.a = AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_heart1.png");
        this.b = AnimSceneResManager.getInstance().getExternalBitmap(resPath + File.separator + "special_swing_heart2.png");
        this.c = new AnimBitmap(this.a);
        this.d = new AnimBitmap(this.b);
        this.e = new AnimBitmap(this.b);
        this.f = new AnimBitmap(this.b);
        this.g = new AnimBitmap(this.b);
        this.h = new AnimBitmap(this.b);
        this.i = new AnimBitmap(this.b);
        this.mAnimEntities[0] = this.c;
        this.mAnimEntities[1] = this.d;
        this.mAnimEntities[2] = this.e;
        this.mAnimEntities[3] = this.f;
        this.mAnimEntities[4] = this.g;
        this.mAnimEntities[5] = this.h;
        this.mAnimEntities[6] = this.i;
        this.N = a(484, 932);
        this.O = a(SystemMessageConstants.JS_BRIDGE_ANNOTATION_NOT_PRESENT, R$styleable.Theme_Custom_bt2_color_state);
        this.P = a(R$styleable.Theme_Custom_xlistview_arrow_piclib, 424);
        this.Q = a(524, 1200);
        this.R = a(800, 912);
        this.S = a(1048, 708);
        this.T = a(572, 912);
        this.U = a(1000, 912);
        this.V = a(a.a, 1076);
        this.W = a(444, 700);
        this.X = a(432, R$styleable.Theme_Custom_label_add_icon);
        this.Y = a(208, 864);
        this.Z = a(608, 584);
        int[] screenSize = AnimSceneResManager.getInstance().getScreenSize();
        if (screenSize[0] > screenSize[1]) {
            this.ah = getScalePx(200);
            this.ai = getScalePx(-450);
        }
        this.j = (((int) this.N.x) - (this.a.getWidth() / 2)) + this.ah;
        this.k = (((int) this.N.y) - (this.a.getHeight() / 2)) + this.ai;
        this.m = (((int) this.Q.x) - (this.b.getWidth() / 2)) + this.ah;
        this.n = (((int) this.Q.y) - (this.b.getHeight() / 2)) + this.ai;
        this.p = this.m;
        this.q = this.n;
        this.r = this.m;
        this.s = this.n;
        this.t = (((int) this.V.x) - (this.b.getWidth() / 2)) + this.ah;
        this.u = (((int) this.V.y) - (this.b.getHeight() / 2)) + this.ai;
        this.w = this.t;
        this.y = this.t;
        this.x = this.u;
        this.z = this.u;
        this.A = 1.0f;
        this.l = 255;
        this.I = new AnimFloatEvaluator(R$styleable.Theme_Custom_account_item_content_layout_bg, 123, this.A, this.A * 2.0f);
        this.J = new AnimFloatEvaluator(R$styleable.Theme_Custom_myinfo_setting_bg, 159, this.B / 8.0f, this.B);
        this.K = new AnimFloatEvaluator(this.C);
        this.L = new AnimFloatEvaluator(this.D);
        this.M = new AnimFloatEvaluator(this.E);
        this.F = new AnimIntEvaluator(this.l);
        this.G = new AnimIntEvaluator(this.o);
        this.H = new AnimIntEvaluator(this.v);
        this.aa = new BezierSquareFloatEvaluator(this.N, this.P, this.O, R$styleable.Theme_Custom_myinfo_night_model_bg, R$styleable.Theme_Custom_post_loading_icon);
        this.ab = new BezierSquareFloatEvaluator(this.Q, this.T, this.R, R$styleable.Theme_Custom_myinfo_night_model_bg, 159);
        this.ac = new BezierSquareFloatEvaluator(this.Q, this.T, this.R, R$styleable.Theme_Custom_label_pinner_tabs_bg, 162);
        this.ad = new BezierSquareFloatEvaluator(this.Q, this.T, this.R, R$styleable.Theme_Custom_more_text_color, R$styleable.Theme_Custom_search_label_item_color_theme);
        this.ae = new BezierSquareFloatEvaluator(this.V, this.Y, this.W, R$styleable.Theme_Custom_myinfo_night_model_bg, 159);
        this.af = new BezierSquareFloatEvaluator(this.V, this.Y, this.W, R$styleable.Theme_Custom_label_pinner_tabs_bg, 162);
        this.ag = new BezierSquareFloatEvaluator(this.V, this.Y, this.W, R$styleable.Theme_Custom_more_text_color, R$styleable.Theme_Custom_search_label_item_color_theme);
    }

    public boolean frameControl(int i) {
        if (i < R$styleable.Theme_Custom_account_item_content_layout_bg) {
            return true;
        }
        this.A = this.I.evaluate(i);
        this.B = this.J.evaluate(i);
        this.C = this.K.evaluate(i);
        this.l = this.F.evaluate(i);
        this.o = this.G.evaluate(i);
        this.v = this.H.evaluate(i);
        this.D = this.L.evaluate(i);
        this.E = this.M.evaluate(i);
        PointF evaluate = this.aa.evaluate(this.mCurFrame);
        if (evaluate != null) {
            this.j = (((int) evaluate.x) - (this.a.getWidth() / 2)) + this.ah;
            this.k = (((int) evaluate.y) - (this.a.getHeight() / 2)) + this.ai;
        }
        evaluate = this.ab.evaluate(i);
        if (evaluate != null) {
            this.m = (((int) evaluate.x) - (this.b.getWidth() / 2)) + this.ah;
            this.n = (((int) evaluate.y) - (this.b.getHeight() / 2)) + this.ai;
        }
        evaluate = this.ac.evaluate(i);
        if (evaluate != null) {
            this.p = (((int) evaluate.x) - (this.b.getWidth() / 2)) + this.ah;
            this.q = (((int) evaluate.y) - (this.b.getHeight() / 2)) + this.ai;
        }
        evaluate = this.ad.evaluate(i);
        if (evaluate != null) {
            this.r = (((int) evaluate.x) - (this.b.getWidth() / 2)) + this.ah;
            this.s = (((int) evaluate.y) - (this.b.getHeight() / 2)) + this.ai;
        }
        evaluate = this.ae.evaluate(i);
        if (evaluate != null) {
            this.t = (((int) evaluate.x) - (this.b.getWidth() / 2)) + this.ah;
            this.u = (((int) evaluate.y) - (this.b.getHeight() / 2)) + this.ai;
        }
        evaluate = this.af.evaluate(i);
        if (evaluate != null) {
            this.w = (((int) evaluate.x) - (this.b.getWidth() / 2)) + this.ah;
            this.x = (((int) evaluate.y) - (this.b.getHeight() / 2)) + this.ai;
        }
        evaluate = this.ag.evaluate(i);
        if (evaluate != null) {
            this.y = (((int) evaluate.x) - (this.b.getWidth() / 2)) + this.ah;
            this.z = (((int) evaluate.y) - (this.b.getHeight() / 2)) + this.ai;
        }
        if (i == R$styleable.Theme_Custom_account_item_content_layout_bg) {
            this.I.resetEvaluator(i, 123, this.A, 2.0f);
        } else if (i == 123) {
            this.I.resetEvaluator(i, R$styleable.Theme_Custom_new_item_login_qq_bg, this.A, 1.0f);
        } else if (i == R$styleable.Theme_Custom_new_item_login_qq_bg) {
            this.I.resetEvaluator(i, R$styleable.Theme_Custom_new_item_shareFriend_text_color, this.A, 2.0f);
        } else if (i == R$styleable.Theme_Custom_new_item_shareFriend_text_color) {
            this.I.resetEvaluator(i, R$styleable.Theme_Custom_send_btn_text_color, this.A, 1.0f);
        } else if (i == R$styleable.Theme_Custom_myinfo_setting_bg) {
            this.K.resetEvaluator(i, R$styleable.Theme_Custom_label_details_ic_selector_edit_btn, 0.0f, 60.0f);
            this.F.resetEvaluator(i, R$styleable.Theme_Custom_post_loading_icon, 255, 0);
            this.G.resetEvaluator(i, 159, 180, 180);
            this.H.resetEvaluator(i, 159, 180, 180);
            this.L.resetEvaluator(i, R$styleable.Theme_Custom_post_loading_icon, -30.0f, -10.0f);
            this.M.resetEvaluator(i, 159, 0.0f, 30.0f);
        } else if (i == 159) {
            this.ab.resetEvaluator(this.R, this.U, this.S, i, R$styleable.Theme_Custom_post_loading_icon);
            this.ae.resetEvaluator(this.W, this.Z, this.X, i, R$styleable.Theme_Custom_post_loading_icon);
            this.G.resetEvaluator(i, R$styleable.Theme_Custom_post_loading_icon, 180, 0);
            this.M.resetEvaluator(i, R$styleable.Theme_Custom_post_loading_icon, 30.0f, 10.0f);
            this.H.resetEvaluator(i, R$styleable.Theme_Custom_post_loading_icon, 100, 0);
        } else if (i == 162) {
            this.ac = new BezierSquareFloatEvaluator(this.R, this.U, this.S, i, 192);
            this.af = new BezierSquareFloatEvaluator(this.W, this.Z, this.X, i, 192);
        } else if (i == R$styleable.Theme_Custom_search_label_item_color_theme) {
            this.ad = new BezierSquareFloatEvaluator(this.R, this.U, this.S, i, R$styleable.Theme_Custom_label_details_ic_selector_edit_btn);
            this.ag = new BezierSquareFloatEvaluator(this.W, this.Z, this.X, i, R$styleable.Theme_Custom_label_details_ic_selector_edit_btn);
        }
        this.c.getPaint().setAlpha(this.l);
        this.c.getMatrix().setTranslate((float) this.j, (float) this.k).preScale(this.A, this.A, (float) (this.a.getWidth() / 2), (float) (this.a.getHeight() / 2)).postRotate(this.C, (float) this.j, (float) this.k);
        this.d.getPaint().setAlpha(this.o);
        this.d.getMatrix().setTranslate((float) this.m, (float) this.n).postRotate(this.E, (float) this.m, (float) this.n).postScale(this.B, this.B, (float) this.m, (float) this.n);
        this.e.getMatrix().setTranslate((float) this.p, (float) this.q).postRotate(this.E, (float) this.p, (float) this.q);
        this.e.getPaint().setAlpha(this.o);
        this.f.getMatrix().setTranslate((float) this.r, (float) this.s).postRotate(this.E, (float) this.r, (float) this.s);
        this.f.getPaint().setAlpha(this.o);
        this.g.getMatrix().setTranslate((float) this.t, (float) this.u).postRotate(this.D, (float) this.t, (float) this.u);
        this.g.getPaint().setAlpha(this.v);
        this.h.getMatrix().setTranslate((float) this.w, (float) this.x).postRotate(this.D, (float) this.w, (float) this.x);
        this.h.getPaint().setAlpha(this.v);
        this.i.getMatrix().setTranslate((float) this.y, (float) this.z).postRotate(this.D, (float) this.y, (float) this.z);
        this.i.getPaint().setAlpha(this.v);
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[7];
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((AnimBitmap) iAnimEntity).draw(canvas);
        }
    }
}
