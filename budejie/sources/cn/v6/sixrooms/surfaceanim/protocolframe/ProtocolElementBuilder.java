package cn.v6.sixrooms.surfaceanim.protocolframe;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.protocol.AlphaRenderBean;
import cn.v6.sixrooms.surfaceanim.protocol.ElementBean;
import cn.v6.sixrooms.surfaceanim.protocol.FrameRenderBean;
import cn.v6.sixrooms.surfaceanim.protocol.PlanBean;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.protocol.RenderBean;
import cn.v6.sixrooms.surfaceanim.protocol.RotateRenderBean;
import cn.v6.sixrooms.surfaceanim.protocol.ScaleRenderBean;
import cn.v6.sixrooms.surfaceanim.protocol.TextRenderBean;
import cn.v6.sixrooms.surfaceanim.protocol.TranslateRenderBean;
import cn.v6.sixrooms.surfaceanim.protocol.XfermodeBean;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.BezierCubeEvaluator;
import cn.v6.sixrooms.surfaceanim.util.BezierSquareEvaluator;

public class ProtocolElementBuilder {

    public interface IBeanParse<T extends RenderBean> {
        boolean parseBean(int i, T t, AnimBitmap[] animBitmapArr, ProtocolScene protocolScene, int i2);
    }

    public static class AlphaBeanParse implements IBeanParse<AlphaRenderBean> {
        private int a = -1;
        private AnimIntEvaluator b;
        private BezierSquareEvaluator c;
        private BezierCubeEvaluator d;

        public boolean parseBean(int i, AlphaRenderBean alphaRenderBean, AnimBitmap[] animBitmapArr, ProtocolScene protocolScene, int i2) {
            if (i2 != this.a) {
                switch (alphaRenderBean.getConversion()) {
                    case 0:
                        if (this.b != null) {
                            this.b.resetEvaluator(alphaRenderBean.getStartFrame(), alphaRenderBean.getEndFrame(), alphaRenderBean.getStartValue(), alphaRenderBean.getEndValue());
                            break;
                        }
                        this.b = new AnimIntEvaluator(alphaRenderBean.getStartFrame(), alphaRenderBean.getEndFrame(), alphaRenderBean.getStartValue(), alphaRenderBean.getEndValue());
                        break;
                    case 1:
                        if (this.c != null) {
                            this.c.resetEvaluator(alphaRenderBean.getPoints()[0], alphaRenderBean.getPoints()[1], alphaRenderBean.getPoints()[2], alphaRenderBean.getStartFrame(), alphaRenderBean.getEndFrame());
                            break;
                        }
                        this.c = new BezierSquareEvaluator(alphaRenderBean.getPoints()[0], alphaRenderBean.getPoints()[1], alphaRenderBean.getPoints()[2], alphaRenderBean.getStartFrame(), alphaRenderBean.getEndFrame());
                        break;
                    case 2:
                        if (this.d != null) {
                            this.d.resetEvaluator(alphaRenderBean.getStartFrame(), alphaRenderBean.getEndFrame(), alphaRenderBean.getPoints()[0], alphaRenderBean.getPoints()[1], alphaRenderBean.getPoints()[2], alphaRenderBean.getPoints()[3]);
                            break;
                        }
                        this.d = new BezierCubeEvaluator(alphaRenderBean.getStartFrame(), alphaRenderBean.getEndFrame(), alphaRenderBean.getPoints()[0], alphaRenderBean.getPoints()[1], alphaRenderBean.getPoints()[2], alphaRenderBean.getPoints()[3]);
                        break;
                }
                this.a = i2;
            }
            switch (alphaRenderBean.getConversion()) {
                case 0:
                    animBitmapArr[0].setAlpha(this.b.evaluate(i));
                    break;
                case 1:
                    animBitmapArr[0].setAlpha(this.c.evaluate(i).getY());
                    break;
                case 2:
                    animBitmapArr[0].setAlpha(this.d.evaluate(i).getY());
                    break;
            }
            return false;
        }
    }

    public static class FrameBeanParse implements IBeanParse<FrameRenderBean> {
        public boolean parseBean(int i, FrameRenderBean frameRenderBean, AnimBitmap[] animBitmapArr, ProtocolScene protocolScene, int i2) {
            int startFrame = i - frameRenderBean.getStartFrame();
            if (startFrame % frameRenderBean.getStep() == 0) {
                Bitmap bitmap = protocolScene.getBitmap(frameRenderBean.getImgHeader() + ((startFrame / frameRenderBean.getStep()) + frameRenderBean.getImgExtend()) + frameRenderBean.getImgType());
                if (bitmap != null) {
                    animBitmapArr[0].setBitmap(bitmap);
                }
            }
            return false;
        }
    }

    public static class HideBeanParse implements IBeanParse<RenderBean> {
        public boolean parseBean(int i, RenderBean renderBean, AnimBitmap[] animBitmapArr, ProtocolScene protocolScene, int i2) {
            return true;
        }
    }

    public static class PlanBeanParse implements IBeanParse<PlanBean> {
        private AnimBitmap a;

        public void setAnimBitmap(AnimBitmap animBitmap) {
            this.a = animBitmap;
        }

        public AnimBitmap getAnimBitmap() {
            return this.a;
        }

        public boolean parseBean(int i, PlanBean planBean, AnimBitmap[] animBitmapArr, ProtocolScene protocolScene, int i2) {
            this.a.animTranslate();
            for (int i3 = 1; i3 < planBean.getRule().length; i3++) {
                if (planBean.getRule()[i3] > 30) {
                    this.a.animPostScale();
                } else if (planBean.getRule()[i3] > 20) {
                    this.a.animPostRotate();
                }
            }
            return false;
        }
    }

    public static class RotateBeanParse implements IBeanParse<RotateRenderBean> {
        private int a = -1;
        private AnimFloatEvaluator b;
        private BezierSquareEvaluator c;
        private BezierCubeEvaluator d;

        public boolean parseBean(int i, RotateRenderBean rotateRenderBean, AnimBitmap[] animBitmapArr, ProtocolScene protocolScene, int i2) {
            if (i2 != this.a) {
                switch (rotateRenderBean.getConversion()) {
                    case 0:
                        if (this.b != null) {
                            this.b.resetEvaluator(rotateRenderBean.getStartFrame(), rotateRenderBean.getEndFrame(), rotateRenderBean.getStartValue(), rotateRenderBean.getEndValue());
                            break;
                        }
                        this.b = new AnimFloatEvaluator(rotateRenderBean.getStartFrame(), rotateRenderBean.getEndFrame(), rotateRenderBean.getStartValue(), rotateRenderBean.getEndValue());
                        break;
                    case 1:
                        if (this.c != null) {
                            this.c.resetEvaluator(rotateRenderBean.getPoints()[0], rotateRenderBean.getPoints()[1], rotateRenderBean.getPoints()[2], rotateRenderBean.getStartFrame(), rotateRenderBean.getEndFrame());
                            break;
                        }
                        this.c = new BezierSquareEvaluator(rotateRenderBean.getPoints()[0], rotateRenderBean.getPoints()[1], rotateRenderBean.getPoints()[2], rotateRenderBean.getStartFrame(), rotateRenderBean.getEndFrame());
                        break;
                    case 2:
                        if (this.d != null) {
                            this.d.resetEvaluator(rotateRenderBean.getStartFrame(), rotateRenderBean.getEndFrame(), rotateRenderBean.getPoints()[0], rotateRenderBean.getPoints()[1], rotateRenderBean.getPoints()[2], rotateRenderBean.getPoints()[3]);
                            break;
                        }
                        this.d = new BezierCubeEvaluator(rotateRenderBean.getStartFrame(), rotateRenderBean.getEndFrame(), rotateRenderBean.getPoints()[0], rotateRenderBean.getPoints()[1], rotateRenderBean.getPoints()[2], rotateRenderBean.getPoints()[3]);
                        break;
                }
                this.a = i2;
            }
            animBitmapArr[0].setRotateAxisPoint(rotateRenderBean.getAxisPoint());
            switch (rotateRenderBean.getConversion()) {
                case 0:
                    animBitmapArr[0].setRotate(this.b.evaluate(i));
                    break;
                case 1:
                    animBitmapArr[0].setRotate((float) this.c.evaluate(i).getY());
                    break;
                case 2:
                    animBitmapArr[0].setRotate((float) this.d.evaluate(i).getY());
                    break;
            }
            return false;
        }
    }

    public static class ScaleBeanParse implements IBeanParse<ScaleRenderBean> {
        private int a = -1;
        private AnimFloatEvaluator b;
        private AnimFloatEvaluator c;
        private BezierSquareEvaluator d;
        private BezierSquareEvaluator e;
        private BezierSquareEvaluator f;
        private BezierCubeEvaluator g;
        private BezierCubeEvaluator h;
        private BezierCubeEvaluator i;

        public boolean parseBean(int i, ScaleRenderBean scaleRenderBean, AnimBitmap[] animBitmapArr, ProtocolScene protocolScene, int i2) {
            if (i2 != this.a) {
                switch (scaleRenderBean.getConversion()) {
                    case 0:
                        if (this.b == null) {
                            this.b = new AnimFloatEvaluator(scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame(), scaleRenderBean.getStartValue().getX(), scaleRenderBean.getEndValue().getX());
                        } else {
                            this.b.resetEvaluator(scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame(), scaleRenderBean.getStartValue().getX(), scaleRenderBean.getEndValue().getX());
                        }
                        if (this.c != null) {
                            this.c.resetEvaluator(scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame(), scaleRenderBean.getStartValue().getY(), scaleRenderBean.getEndValue().getY());
                            break;
                        }
                        this.c = new AnimFloatEvaluator(scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame(), scaleRenderBean.getStartValue().getY(), scaleRenderBean.getEndValue().getY());
                        break;
                    case 1:
                        if (scaleRenderBean.getPoints() != null) {
                            if (this.f != null) {
                                this.f.resetEvaluator(scaleRenderBean.getPoints()[0], scaleRenderBean.getPoints()[1], scaleRenderBean.getPoints()[2], scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame());
                                break;
                            }
                            this.f = new BezierSquareEvaluator(scaleRenderBean.getPoints()[0], scaleRenderBean.getPoints()[1], scaleRenderBean.getPoints()[2], scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame());
                            break;
                        }
                        if (this.d == null) {
                            this.d = new BezierSquareEvaluator(scaleRenderBean.getPointsX()[0], scaleRenderBean.getPointsX()[1], scaleRenderBean.getPointsX()[2], scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame());
                        } else {
                            this.d.resetEvaluator(scaleRenderBean.getPointsX()[0], scaleRenderBean.getPointsX()[1], scaleRenderBean.getPointsX()[2], scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame());
                        }
                        if (this.e != null) {
                            this.e.resetEvaluator(scaleRenderBean.getPointsY()[0], scaleRenderBean.getPointsY()[1], scaleRenderBean.getPointsY()[2], scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame());
                            break;
                        }
                        this.e = new BezierSquareEvaluator(scaleRenderBean.getPointsY()[0], scaleRenderBean.getPointsY()[1], scaleRenderBean.getPointsY()[2], scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame());
                        break;
                    case 2:
                        if (scaleRenderBean.getPoints() != null) {
                            if (this.i != null) {
                                this.i.resetEvaluator(scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame(), scaleRenderBean.getPoints()[0], scaleRenderBean.getPoints()[1], scaleRenderBean.getPoints()[2], scaleRenderBean.getPoints()[3]);
                                break;
                            }
                            this.i = new BezierCubeEvaluator(scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame(), scaleRenderBean.getPoints()[0], scaleRenderBean.getPoints()[1], scaleRenderBean.getPoints()[2], scaleRenderBean.getPoints()[3]);
                            break;
                        }
                        if (this.g == null) {
                            this.g = new BezierCubeEvaluator(scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame(), scaleRenderBean.getPointsX()[0], scaleRenderBean.getPointsX()[1], scaleRenderBean.getPointsX()[2], scaleRenderBean.getPointsX()[3]);
                        } else {
                            this.g.resetEvaluator(scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame(), scaleRenderBean.getPointsX()[0], scaleRenderBean.getPointsX()[1], scaleRenderBean.getPointsX()[2], scaleRenderBean.getPointsX()[3]);
                        }
                        if (this.h != null) {
                            this.h.resetEvaluator(scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame(), scaleRenderBean.getPointsY()[0], scaleRenderBean.getPointsY()[1], scaleRenderBean.getPointsY()[2], scaleRenderBean.getPointsY()[3]);
                            break;
                        }
                        this.h = new BezierCubeEvaluator(scaleRenderBean.getStartFrame(), scaleRenderBean.getEndFrame(), scaleRenderBean.getPointsY()[0], scaleRenderBean.getPointsY()[1], scaleRenderBean.getPointsY()[2], scaleRenderBean.getPointsY()[3]);
                        break;
                }
                this.a = i2;
            }
            animBitmapArr[0].setScaleAxisPoint(scaleRenderBean.getAxisPoint());
            switch (scaleRenderBean.getConversion()) {
                case 0:
                    animBitmapArr[0].setScaleX(this.b.evaluate(i));
                    animBitmapArr[0].setScaleY(this.c.evaluate(i));
                    break;
                case 1:
                    if (scaleRenderBean.getPoints() == null) {
                        animBitmapArr[0].setScaleX(((float) this.d.evaluate(i).getY()) / 1000.0f);
                        animBitmapArr[0].setScaleY(((float) this.e.evaluate(i).getY()) / 1000.0f);
                        break;
                    }
                    animBitmapArr[0].setScale(((float) this.f.evaluate(i).getY()) / 1000.0f);
                    break;
                case 2:
                    if (scaleRenderBean.getPoints() == null) {
                        animBitmapArr[0].setScaleX(((float) this.g.evaluate(i).getY()) / 1000.0f);
                        animBitmapArr[0].setScaleY(((float) this.h.evaluate(i).getY()) / 1000.0f);
                        break;
                    }
                    animBitmapArr[0].setScale(((float) this.i.evaluate(i).getY()) / 1000.0f);
                    break;
            }
            return false;
        }
    }

    public static class TextBeanParse implements IBeanParse<TextRenderBean> {
        private int a;
        private int b;

        public boolean parseBean(int i, TextRenderBean textRenderBean, AnimBitmap[] animBitmapArr, ProtocolScene protocolScene, int i2) {
            animBitmapArr[0].getPaint().setColor(textRenderBean.getColor());
            animBitmapArr[0].getPaint().setTextSize((float) AnimSceneResManager.getInstance().getScalePx(textRenderBean.getSize()));
            animBitmapArr[0].getPaint().setShadowLayer((float) textRenderBean.getShadowRadius(), (float) textRenderBean.getShadowDx(), (float) textRenderBean.getShadowDy(), textRenderBean.getShadowColor());
            if (textRenderBean.getBold() == 1) {
                animBitmapArr[0].getPaint().setTypeface(Typeface.DEFAULT_BOLD);
            } else if (textRenderBean.getBold() == 0) {
                animBitmapArr[0].getPaint().setTypeface(Typeface.DEFAULT);
            }
            textRenderBean.getLocation().transformScalePoint();
            if (this.a == 0) {
                this.a = animBitmapArr[0].getTranslateX() + textRenderBean.getLocation().getX();
                this.b = animBitmapArr[0].getTranslateY() + textRenderBean.getLocation().getY();
            }
            animBitmapArr[0].setTranslate(this.a, this.b);
            animBitmapArr[0].setText(textRenderBean.getContent());
            return false;
        }
    }

    public static class TranslateBeanParse implements IBeanParse<TranslateRenderBean> {
        private int a = -1;
        private AnimIntEvaluator b;
        private AnimIntEvaluator c;
        private BezierSquareEvaluator d;
        private BezierCubeEvaluator e;

        public boolean parseBean(int i, TranslateRenderBean translateRenderBean, AnimBitmap[] animBitmapArr, ProtocolScene protocolScene, int i2) {
            if (i2 != this.a) {
                switch (translateRenderBean.getConversion()) {
                    case 0:
                        if (this.b == null) {
                            this.b = new AnimIntEvaluator(translateRenderBean.getStartFrame(), translateRenderBean.getEndFrame(), translateRenderBean.getStartValue().transformScalePoint().getX(), translateRenderBean.getEndValue().transformScalePoint().getX());
                        } else {
                            this.b.resetEvaluator(translateRenderBean.getStartFrame(), translateRenderBean.getEndFrame(), translateRenderBean.getStartValue().transformScalePoint().getX(), translateRenderBean.getEndValue().transformScalePoint().getX());
                        }
                        if (this.c != null) {
                            this.c.resetEvaluator(translateRenderBean.getStartFrame(), translateRenderBean.getEndFrame(), translateRenderBean.getStartValue().transformScalePoint().getY(), translateRenderBean.getEndValue().transformScalePoint().getY());
                            break;
                        }
                        this.c = new AnimIntEvaluator(translateRenderBean.getStartFrame(), translateRenderBean.getEndFrame(), translateRenderBean.getStartValue().transformScalePoint().getY(), translateRenderBean.getEndValue().transformScalePoint().getY());
                        break;
                    case 1:
                        if (this.d != null) {
                            this.d.resetEvaluator(translateRenderBean.getPoints()[0].transformScalePoint(), translateRenderBean.getPoints()[1].transformScalePoint(), translateRenderBean.getPoints()[2].transformScalePoint(), translateRenderBean.getStartFrame(), translateRenderBean.getEndFrame());
                            break;
                        }
                        this.d = new BezierSquareEvaluator(translateRenderBean.getPoints()[0].transformScalePoint(), translateRenderBean.getPoints()[1].transformScalePoint(), translateRenderBean.getPoints()[2].transformScalePoint(), translateRenderBean.getStartFrame(), translateRenderBean.getEndFrame());
                        break;
                    case 2:
                        if (this.e != null) {
                            this.e.resetEvaluator(translateRenderBean.getStartFrame(), translateRenderBean.getEndFrame(), translateRenderBean.getPoints()[0].transformScalePoint(), translateRenderBean.getPoints()[1].transformScalePoint(), translateRenderBean.getPoints()[2].transformScalePoint(), translateRenderBean.getPoints()[3].transformScalePoint());
                            break;
                        }
                        this.e = new BezierCubeEvaluator(translateRenderBean.getStartFrame(), translateRenderBean.getEndFrame(), translateRenderBean.getPoints()[0].transformScalePoint(), translateRenderBean.getPoints()[1].transformScalePoint(), translateRenderBean.getPoints()[2].transformScalePoint(), translateRenderBean.getPoints()[3].transformScalePoint());
                        break;
                }
                this.a = i2;
            }
            PointI evaluate;
            switch (translateRenderBean.getConversion()) {
                case 0:
                    animBitmapArr[0].setTranslateX(animBitmapArr[0].getTranslateX() + this.b.evaluate(i));
                    animBitmapArr[0].setTranslateY(animBitmapArr[0].getTranslateY() + this.c.evaluate(i));
                    break;
                case 1:
                    evaluate = this.d.evaluate(i);
                    animBitmapArr[0].setTranslate(animBitmapArr[0].getTranslateX() + evaluate.getX(), evaluate.getY() + animBitmapArr[0].getTranslateY());
                    break;
                case 2:
                    evaluate = this.e.evaluate(i);
                    animBitmapArr[0].setTranslate(animBitmapArr[0].getTranslateX() + evaluate.getX(), evaluate.getY() + animBitmapArr[0].getTranslateY());
                    break;
            }
            return false;
        }
    }

    public static class XfermodeBeanParse implements IBeanParse<XfermodeBean> {
        private Canvas a;

        public void setCanvas(Canvas canvas) {
            this.a = canvas;
        }

        @TargetApi(11)
        public final Mode intToMode(int i) {
            switch (i) {
                case 1:
                    return Mode.SRC;
                case 2:
                    return Mode.DST;
                case 3:
                    return Mode.SRC_OVER;
                case 4:
                    return Mode.DST_OVER;
                case 5:
                    return Mode.SRC_IN;
                case 6:
                    return Mode.DST_IN;
                case 7:
                    return Mode.SRC_OUT;
                case 8:
                    return Mode.DST_OUT;
                case 9:
                    return Mode.SRC_ATOP;
                case 10:
                    return Mode.DST_ATOP;
                case 11:
                    return Mode.XOR;
                case 12:
                    return Mode.ADD;
                case 13:
                    return Mode.MULTIPLY;
                case 14:
                    return Mode.SCREEN;
                case 15:
                    return Mode.OVERLAY;
                case 16:
                    return Mode.DARKEN;
                case 17:
                    return Mode.LIGHTEN;
                default:
                    return Mode.CLEAR;
            }
        }

        public boolean parseBean(int i, XfermodeBean xfermodeBean, AnimBitmap[] animBitmapArr, ProtocolScene protocolScene, int i2) {
            if (xfermodeBean.getLayer() == 1) {
                protocolScene.setLayerId(this.a.saveLayer(0.0f, 0.0f, (float) this.a.getWidth(), (float) this.a.getHeight(), null, 31));
            }
            if (xfermodeBean.getMode() != -1) {
                animBitmapArr[0].getPaint().setXfermode(new PorterDuffXfermode(intToMode(xfermodeBean.getMode())));
                animBitmapArr[0].draw(this.a);
                animBitmapArr[0].getPaint().setXfermode(null);
            } else {
                animBitmapArr[0].draw(this.a);
            }
            if (xfermodeBean.getLayer() == 2) {
                this.a.restoreToCount(protocolScene.getLayerId());
            }
            return false;
        }
    }

    public static ProtocolElement createElement(ProtocolScene protocolScene, ElementBean elementBean) {
        return new b(protocolScene, elementBean);
    }
}
