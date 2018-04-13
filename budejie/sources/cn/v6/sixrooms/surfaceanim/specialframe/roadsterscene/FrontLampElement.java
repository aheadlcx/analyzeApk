package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import java.io.File;

public class FrontLampElement extends SpecialElement<AnimBitmap> {
    private AnimBitmap[] a;
    private AnimIntEvaluator b;
    private AnimIntEvaluator c;
    private AnimFloatEvaluator d;
    private int e;
    private int f;
    private PointI g = new PointI();

    public FrontLampElement(AnimScene animScene) {
        super(animScene);
        this.a = (AnimBitmap[]) animScene.getSceneElement(RoadsterScene.CAR).getAnimEntities();
        ((AnimBitmap[]) this.mAnimEntities)[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_front_lamp.png"));
        ((AnimBitmap[]) this.mAnimEntities)[1] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[0].getBitmap());
        ((AnimBitmap[]) this.mAnimEntities)[1].setPaint(((AnimBitmap[]) this.mAnimEntities)[0].getPaint());
        ((AnimBitmap[]) this.mAnimEntities)[2] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_front_lamp2.png"));
        ((AnimBitmap[]) this.mAnimEntities)[3] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[2].getBitmap());
        ((AnimBitmap[]) this.mAnimEntities)[3].setPaint(((AnimBitmap[]) this.mAnimEntities)[2].getPaint());
        this.b = new AnimIntEvaluator(25, 32, 0, 255);
        this.c = new AnimIntEvaluator(255);
        this.d = new AnimFloatEvaluator(44, 87, 0.0f, 360.0f);
    }

    public void drawElement(Canvas canvas) {
        ((AnimBitmap[]) this.mAnimEntities)[0].getPaint().setAlpha(((AnimBitmap[]) this.mAnimEntities)[0].getAlpha());
        ((AnimBitmap[]) this.mAnimEntities)[2].getPaint().setAlpha(((AnimBitmap[]) this.mAnimEntities)[2].getAlpha());
        for (int length = ((AnimBitmap[]) this.mAnimEntities).length - 1; length >= 0; length--) {
            ((AnimBitmap[]) this.mAnimEntities)[length].getMatrix().setTranslate((float) ((AnimBitmap[]) this.mAnimEntities)[length].getTranslateX(), (float) ((AnimBitmap[]) this.mAnimEntities)[length].getTranslateY()).postRotate(((AnimBitmap[]) this.mAnimEntities)[length].getRotate(), (float) (((AnimBitmap[]) this.mAnimEntities)[length].getTranslateX() + (((AnimBitmap[]) this.mAnimEntities)[length].getBitmap().getWidth() / 2)), (float) (((AnimBitmap[]) this.mAnimEntities)[length].getTranslateY() + (((AnimBitmap[]) this.mAnimEntities)[length].getBitmap().getHeight() / 2)));
            ((AnimBitmap[]) this.mAnimEntities)[length].draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i < 29) {
            return true;
        }
        PointI point = this.mAnimScene.getSceneParameter().getPoint();
        if (i == 29) {
            this.g.x = point.x;
            this.g.y = point.y;
            this.e = this.a[0].getTranslateX();
            this.f = this.a[0].getTranslateY();
            ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(this.e - getScalePx(R$styleable.Theme_Custom_label_details_ic_selector_edit_btn));
            ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateY(this.f + getScalePx(106));
            ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX() + getScalePx(182));
            ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateY(((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY() + AnimSceneResManager.getInstance().getScalePx(7));
            ((AnimBitmap[]) this.mAnimEntities)[2].setTranslateX(((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX() + getScalePx(98));
            ((AnimBitmap[]) this.mAnimEntities)[2].setTranslateY((getScalePx(9) + ((((AnimBitmap[]) this.mAnimEntities)[0].getBitmap().getHeight() / 2) + ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY())) - (((AnimBitmap[]) this.mAnimEntities)[2].getBitmap().getHeight() / 2));
            ((AnimBitmap[]) this.mAnimEntities)[3].setTranslateX(((AnimBitmap[]) this.mAnimEntities)[2].getTranslateX() + getScalePx(192));
            ((AnimBitmap[]) this.mAnimEntities)[3].setTranslateY(((AnimBitmap[]) this.mAnimEntities)[2].getTranslateY() + getScalePx(7));
        }
        if (!(point.x == this.g.x && point.y == this.g.y)) {
            a();
            this.g.x = point.x;
            this.g.y = point.y;
        }
        if (i > 106) {
            a();
        }
        ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(this.b.evaluate(i));
        ((AnimBitmap[]) this.mAnimEntities)[2].setAlpha(this.c.evaluate(i));
        ((AnimBitmap[]) this.mAnimEntities)[2].setRotate(this.d.evaluate(i));
        ((AnimBitmap[]) this.mAnimEntities)[3].setRotate(this.d.evaluate(i));
        if (i == 38) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(0);
            ((AnimBitmap[]) this.mAnimEntities)[2].setAlpha(0);
        } else if (i == 42) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(0);
            ((AnimBitmap[]) this.mAnimEntities)[2].setAlpha(0);
        } else if (i == 44) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(0);
            this.b.resetEvaluator(i, 47, 0, 255);
        } else if (i == 91) {
            this.b.resetEvaluator(i, 94, 255, 0);
        } else if (i == 95 || i == 96) {
            ((AnimBitmap[]) this.mAnimEntities)[2].setAlpha(0);
        } else if (i == 97) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(255);
        } else if (i == 106) {
            this.b.resetEvaluator(i, 110, 255, 0);
            this.c.resetEvaluator(i, 110, 255, 0);
        } else if (i == 117) {
            this.b.resetEvaluator(i, 122, 0, 255);
            this.c.resetEvaluator(i, 122, 0, 255);
        } else if (i == 138) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(0);
            this.b.resetEvaluator(i, R$styleable.Theme_Custom_phone_edittext_bg, 0, 255);
            ((AnimBitmap[]) this.mAnimEntities)[2].setAlpha(0);
            this.c.resetEvaluator(i, R$styleable.Theme_Custom_phone_edittext_bg, 0, 255);
        }
        return false;
    }

    private void a() {
        int i = 0;
        int translateX = this.a[0].getTranslateX() - this.e;
        int translateY = this.a[0].getTranslateY() - this.f;
        this.e = this.a[0].getTranslateX();
        this.f = this.a[0].getTranslateY();
        AnimBitmap[] animBitmapArr = (AnimBitmap[]) this.mAnimEntities;
        int length = animBitmapArr.length;
        while (i < length) {
            AnimBitmap animBitmap = animBitmapArr[i];
            animBitmap.setTranslateX(animBitmap.getTranslateX() + translateX);
            animBitmap.setTranslateY(animBitmap.getTranslateY() + translateY);
            i++;
        }
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[4];
    }
}
