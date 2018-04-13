package cn.v6.sixrooms.surfaceanim.protocolframe;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.protocol.ElementBean;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.protocol.RenderBean;
import cn.v6.sixrooms.surfaceanim.protocolframe.ProtocolElementBuilder.AlphaBeanParse;
import cn.v6.sixrooms.surfaceanim.protocolframe.ProtocolElementBuilder.FrameBeanParse;
import cn.v6.sixrooms.surfaceanim.protocolframe.ProtocolElementBuilder.HideBeanParse;
import cn.v6.sixrooms.surfaceanim.protocolframe.ProtocolElementBuilder.IBeanParse;
import cn.v6.sixrooms.surfaceanim.protocolframe.ProtocolElementBuilder.PlanBeanParse;
import cn.v6.sixrooms.surfaceanim.protocolframe.ProtocolElementBuilder.RotateBeanParse;
import cn.v6.sixrooms.surfaceanim.protocolframe.ProtocolElementBuilder.ScaleBeanParse;
import cn.v6.sixrooms.surfaceanim.protocolframe.ProtocolElementBuilder.TextBeanParse;
import cn.v6.sixrooms.surfaceanim.protocolframe.ProtocolElementBuilder.TranslateBeanParse;
import cn.v6.sixrooms.surfaceanim.protocolframe.ProtocolElementBuilder.XfermodeBeanParse;

final class b extends ProtocolElement<AnimBitmap> {
    private int[] a;
    private int[] b;
    private int[] c;
    private int[] d;
    private int[] e;
    private int[] f;
    private int[] g;
    private int[] h;
    private int[] i;
    private HideBeanParse j = new HideBeanParse();
    private FrameBeanParse k = new FrameBeanParse();
    private AlphaBeanParse l = new AlphaBeanParse();
    private TranslateBeanParse m = new TranslateBeanParse();
    private ScaleBeanParse n = new ScaleBeanParse();
    private RotateBeanParse o = new RotateBeanParse();
    private PlanBeanParse p = new PlanBeanParse();
    private XfermodeBeanParse q = new XfermodeBeanParse();
    private TextBeanParse r = new TextBeanParse();

    b(AnimScene animScene, ElementBean elementBean) {
        super(animScene, elementBean);
    }

    public final /* synthetic */ IAnimEntity[] initAnimEntities() {
        return a();
    }

    private AnimBitmap[] a() {
        AnimBitmap[] animBitmapArr = new AnimBitmap[]{new AnimBitmap(((ProtocolScene) this.mAnimScene).getBitmap(this.mElementBean.getImg()))};
        this.a = new int[1];
        this.b = new int[1];
        this.c = new int[1];
        this.d = new int[1];
        this.e = new int[1];
        this.f = new int[1];
        this.g = new int[1];
        this.h = new int[1];
        this.i = new int[1];
        return animBitmapArr;
    }

    public final void resetAnimEntities() {
        this.mAnimEntities = a();
    }

    public final boolean frameControl(int i) {
        if (i < this.mElementBean.getStart() || i > this.mElementBean.getEnd()) {
            return true;
        }
        PointI point = this.mAnimScene.getSceneParameter().getPoint();
        ((AnimBitmap[]) this.mAnimEntities)[0].setTranslate(point.getX(), point.getY());
        a(i, this.mElementBean.getText(), this.i, this.r, (AnimBitmap[]) this.mAnimEntities, (ProtocolScene) this.mAnimScene);
        if (a(i, this.mElementBean.getHide(), this.a, this.j, (AnimBitmap[]) this.mAnimEntities, (ProtocolScene) this.mAnimScene)) {
            return true;
        }
        a(i, this.mElementBean.getFrame(), this.b, this.k, (AnimBitmap[]) this.mAnimEntities, (ProtocolScene) this.mAnimScene);
        a(i, this.mElementBean.getAlpha(), this.c, this.l, (AnimBitmap[]) this.mAnimEntities, (ProtocolScene) this.mAnimScene);
        a(i, this.mElementBean.getTranslate(), this.d, this.m, (AnimBitmap[]) this.mAnimEntities, (ProtocolScene) this.mAnimScene);
        a(i, this.mElementBean.getScale(), this.e, this.n, (AnimBitmap[]) this.mAnimEntities, (ProtocolScene) this.mAnimScene);
        a(i, this.mElementBean.getRotate(), this.f, this.o, (AnimBitmap[]) this.mAnimEntities, (ProtocolScene) this.mAnimScene);
        return false;
    }

    private static boolean a(int i, RenderBean[] renderBeanArr, int[] iArr, IBeanParse iBeanParse, AnimBitmap[] animBitmapArr, ProtocolScene protocolScene) {
        if (renderBeanArr == null || iArr[0] >= renderBeanArr.length) {
            return false;
        }
        int i2 = iArr[0];
        if (renderBeanArr[i2] == null || i >= renderBeanArr[i2].getEndFrame()) {
            if (renderBeanArr[i2] == null || i != renderBeanArr[i2].getEndFrame()) {
                iArr[0] = iArr[0] + 1;
                return false;
            }
            iArr[0] = iArr[0] + 1;
            return iBeanParse.parseBean(i, renderBeanArr[i2], animBitmapArr, protocolScene, i2);
        } else if (i < renderBeanArr[i2].getStartFrame()) {
            return false;
        } else {
            return iBeanParse.parseBean(i, renderBeanArr[i2], animBitmapArr, protocolScene, i2);
        }
    }

    public final void drawElement(Canvas canvas) {
        if (this.mElementBean.getPlan() == null) {
            ((AnimBitmap[]) this.mAnimEntities)[0].animAlpha().animTranslate().animPostRotate().animPostScale();
            a(canvas);
            return;
        }
        this.p.setAnimBitmap(((AnimBitmap[]) this.mAnimEntities)[0].animAlpha());
        a(this.mCurFrame, this.mElementBean.getPlan(), this.g, this.p, (AnimBitmap[]) this.mAnimEntities, (ProtocolScene) this.mAnimScene);
        a(canvas);
    }

    private void a(Canvas canvas) {
        if (this.mElementBean.getXfermode() == null) {
            ((AnimBitmap[]) this.mAnimEntities)[0].draw(canvas);
            return;
        }
        this.q.setCanvas(canvas);
        a(this.mCurFrame, this.mElementBean.getXfermode(), this.h, this.q, (AnimBitmap[]) this.mAnimEntities, (ProtocolScene) this.mAnimScene);
    }
}
