package cn.v6.sixrooms.surfaceanim.specialframe.weddingscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.BezierCubeEvaluator;
import com.ali.auth.third.core.model.SystemMessageConstants;
import com.alibaba.wireless.security.SecExceptionCode;
import com.budejie.www.R$styleable;
import java.io.File;

public class PetalElement extends SpecialElement<AnimBitmap> {
    private int A;
    private int B;
    private int C;
    private int D;
    private BezierCubeEvaluator E;
    private BezierCubeEvaluator F;
    private BezierCubeEvaluator G;
    private BezierCubeEvaluator H;
    private BezierCubeEvaluator I;
    private BezierCubeEvaluator J;
    private BezierCubeEvaluator K;
    private BezierCubeEvaluator L;
    private BezierCubeEvaluator M;
    private BezierCubeEvaluator N;
    private BezierCubeEvaluator O;
    private BezierCubeEvaluator P;
    private BezierCubeEvaluator Q;
    private BezierCubeEvaluator R;
    private float S;
    private float T;
    private float U;
    private float V;
    private float W;
    private float X;
    private float Y;
    private float Z;
    private int a;
    private float aa;
    private float ab;
    private float ac;
    private float ad;
    private float ae;
    private float af;
    private AnimFloatEvaluator ag = new AnimFloatEvaluator(1, 200, 0.0f, 1440.0f);
    private AnimFloatEvaluator ah;
    private AnimFloatEvaluator ai;
    private AnimFloatEvaluator aj;
    private AnimFloatEvaluator ak;
    private AnimFloatEvaluator al;
    private AnimFloatEvaluator am;
    private AnimFloatEvaluator an;
    private AnimFloatEvaluator ao;
    private AnimFloatEvaluator ap;
    private AnimFloatEvaluator aq;
    private AnimFloatEvaluator ar;
    private AnimFloatEvaluator as;
    private AnimFloatEvaluator at;
    private WeddingScene au;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
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

    public PetalElement(AnimScene animScene, int i, int i2) {
        super(animScene);
        this.au = (WeddingScene) animScene;
        this.a = i;
        this.b = i2;
        ((AnimBitmap[]) this.mAnimEntities)[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_3.png"));
        ((AnimBitmap[]) this.mAnimEntities)[1] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_3.png"));
        ((AnimBitmap[]) this.mAnimEntities)[2] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_4.png"));
        ((AnimBitmap[]) this.mAnimEntities)[3] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_4.png"));
        ((AnimBitmap[]) this.mAnimEntities)[4] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_2.png"));
        ((AnimBitmap[]) this.mAnimEntities)[5] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_2.png"));
        ((AnimBitmap[]) this.mAnimEntities)[6] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_2.png"));
        ((AnimBitmap[]) this.mAnimEntities)[7] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[8] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[9] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[10] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[11] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_2.png"));
        ((AnimBitmap[]) this.mAnimEntities)[12] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_2.png"));
        ((AnimBitmap[]) this.mAnimEntities)[13] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_petal_2.png"));
        PointI pointI = new PointI(a(672), b(-156));
        PointI pointI2 = new PointI(a(R$styleable.Theme_Custom_last_refresh_item_text_theme), b(630));
        PointI pointI3 = new PointI(a(1001), b(1039));
        PointI pointI4 = new PointI(a(328), b(2052));
        this.E = new BezierCubeEvaluator(1, 200, pointI, pointI2, pointI3, pointI4);
        this.F = new BezierCubeEvaluator(56, 130, pointI, pointI2, pointI3, pointI4);
        this.ah = new AnimFloatEvaluator(56, 130, 0.0f, 1440.0f);
        pointI = new PointI(a(752), b(-136));
        pointI2 = new PointI(a(1120), b(R$styleable.Theme_Custom_select_grid_view_selector));
        pointI3 = new PointI(a(-199), b(919));
        pointI4 = new PointI(a(472), b(2052));
        this.G = new BezierCubeEvaluator(1, 95, pointI, pointI2, pointI3, pointI4);
        this.ai = new AnimFloatEvaluator(1, 95, 0.0f, -1440.0f);
        this.H = new BezierCubeEvaluator(70, 180, pointI, pointI2, pointI3, pointI4);
        this.aj = new AnimFloatEvaluator(70, 180, 0.0f, -1440.0f);
        pointI = new PointI(a(SystemMessageConstants.JS_BRIDGE_ANNOTATION_NOT_PRESENT), b(-156));
        pointI2 = new PointI(a(1060), b(931));
        pointI3 = new PointI(a(-639), b(539));
        pointI4 = new PointI(a(724), b(2052));
        this.I = new BezierCubeEvaluator(1, 101, pointI, pointI2, pointI3, pointI4);
        this.ak = new AnimFloatEvaluator(1, 101, 0.0f, 1080.0f);
        this.J = new BezierCubeEvaluator(68, 184, pointI, pointI2, pointI3, pointI4);
        this.al = new AnimFloatEvaluator(68, 184, 0.0f, 1080.0f);
        this.K = new BezierCubeEvaluator(97, 200, pointI, pointI2, pointI3, pointI4);
        this.am = new AnimFloatEvaluator(97, 200, 0.0f, 1080.0f);
        pointI = new PointI(a(R$styleable.Theme_Custom_forward_icon), b(-156));
        pointI2 = new PointI(a(R$styleable.Theme_Custom_last_refresh_item_text_theme), b(930));
        pointI3 = new PointI(a(1321), b(1120));
        pointI4 = new PointI(a(548), b(2052));
        this.L = new BezierCubeEvaluator(1, 108, pointI, pointI2, pointI3, pointI4);
        this.an = new AnimFloatEvaluator(1, 108, 0.0f, -2160.0f);
        this.M = new BezierCubeEvaluator(78, 200, pointI, pointI2, pointI3, pointI4);
        this.ao = new AnimFloatEvaluator(78, 200, 0.0f, -2160.0f);
        pointI = new PointI(a(1352), b(R$styleable.Theme_Custom_top_label_btn_bg));
        pointI2 = new PointI(a(SecExceptionCode.SEC_ERROR_SECURITYBODY), b(931));
        pointI3 = new PointI(a(-1079), b(1239));
        pointI4 = new PointI(a(428), b(2272));
        this.N = new BezierCubeEvaluator(1, 125, pointI, pointI2, pointI3, pointI4);
        this.ap = new AnimFloatEvaluator(1, 125, 0.0f, -2160.0f);
        this.O = new BezierCubeEvaluator(61, 200, pointI, pointI2, pointI3, pointI4);
        this.aq = new AnimFloatEvaluator(61, 200, 0.0f, -2160.0f);
        pointI = new PointI(a(-112), b(R$styleable.Theme_Custom_hot_comment_text_color));
        pointI2 = new PointI(a(160), b(1410));
        pointI3 = new PointI(a(SecExceptionCode.SEC_ERROR_ATLAS_UNSUPPORTED), b(460));
        pointI4 = new PointI(a(1292), b(1988));
        this.P = new BezierCubeEvaluator(1, 79, pointI, pointI2, pointI3, pointI4);
        this.ar = new AnimFloatEvaluator(1, 79, 0.0f, 1080.0f);
        this.Q = new BezierCubeEvaluator(62, R$styleable.Theme_Custom_add_music_album_btn, pointI, pointI2, pointI3, pointI4);
        this.as = new AnimFloatEvaluator(62, R$styleable.Theme_Custom_add_music_album_btn, 0.0f, 1080.0f);
        this.R = new BezierCubeEvaluator(107, 200, pointI, pointI2, pointI3, pointI4);
        this.at = new AnimFloatEvaluator(107, 200, 0.0f, 1080.0f);
    }

    private int a(int i) {
        return getScalePx(this.au.getOffsetX() + i);
    }

    private int b(int i) {
        return getScalePx(this.au.getOffsetY() + i);
    }

    public void drawElement(Canvas canvas) {
        for (AnimBitmap animTranslate : (AnimBitmap[]) this.mAnimEntities) {
            animTranslate.animTranslate().animPostRotate().draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i < this.a || i > this.b) {
            return true;
        }
        int i2 = ((i + 122) - this.a) % 200;
        PointI evaluate = this.E.evaluate(i2);
        this.c = evaluate.x;
        this.d = evaluate.y;
        this.S = this.ag.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[0].setTranslate(this.c, this.d).setRotate(this.S);
        evaluate = this.F.evaluate(i2);
        this.e = evaluate.x;
        this.f = evaluate.y;
        this.T = this.ah.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[1].setTranslate(this.e, this.f).setRotate(this.T);
        evaluate = this.G.evaluate(i2);
        this.g = evaluate.x;
        this.h = evaluate.y;
        this.U = this.ai.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[2].setTranslate(this.g, this.h).setRotate(this.U);
        evaluate = this.H.evaluate(i2);
        this.i = evaluate.x;
        this.j = evaluate.y;
        this.V = this.aj.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[3].setTranslate(this.i, this.j).setRotate(this.V);
        evaluate = this.I.evaluate(i2);
        this.k = evaluate.x;
        this.l = evaluate.y;
        this.W = this.ak.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[4].setTranslate(this.k, this.l).setRotate(this.W);
        evaluate = this.J.evaluate(i2);
        this.m = evaluate.x;
        this.n = evaluate.y;
        this.Y = this.al.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[5].setTranslate(this.m, this.n).setRotate(this.Y);
        evaluate = this.K.evaluate(i2);
        this.o = evaluate.x;
        this.p = evaluate.y;
        this.X = this.am.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[6].setTranslate(this.o, this.p).setRotate(this.X);
        evaluate = this.L.evaluate(i2);
        this.s = evaluate.x;
        this.t = evaluate.y;
        this.Z = this.an.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[7].setTranslate(this.s, this.t).setRotate(this.Z);
        evaluate = this.M.evaluate(i2);
        this.q = evaluate.x;
        this.r = evaluate.y;
        this.aa = this.ao.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[8].setTranslate(this.q, this.r).setRotate(this.aa);
        evaluate = this.N.evaluate(i2);
        this.u = evaluate.x;
        this.v = evaluate.y;
        this.ab = this.ap.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[9].setTranslate(this.u, this.v).setRotate(this.ab);
        evaluate = this.O.evaluate(i2);
        if (i2 < 61) {
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
        } else {
            this.w = evaluate.x;
            this.x = evaluate.y;
        }
        this.ac = this.aq.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[10].setTranslate(this.w, this.x).setRotate(this.ac);
        evaluate = this.P.evaluate(i2);
        this.y = evaluate.x;
        this.z = evaluate.y;
        this.ad = this.ar.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[11].setTranslate(this.y, this.z).setRotate(this.ad);
        evaluate = this.Q.evaluate(i2);
        if (i2 < 62) {
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
        } else {
            this.A = evaluate.x;
            this.B = evaluate.y;
        }
        this.ae = this.as.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[12].setTranslate(this.A, this.B).setRotate(this.ae);
        evaluate = this.R.evaluate(i2);
        if (i2 < 107) {
            this.C = Integer.MIN_VALUE;
            this.D = Integer.MIN_VALUE;
        } else {
            this.C = evaluate.x;
            this.D = evaluate.y;
        }
        this.af = this.at.evaluate(i2);
        ((AnimBitmap[]) this.mAnimEntities)[13].setTranslate(this.C, this.D).setRotate(this.af);
        return false;
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[14];
    }
}
