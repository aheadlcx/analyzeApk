package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import java.io.File;

public class CarElement extends SpecialElement<AnimBitmap> {
    private AnimIntEvaluator a = new AnimIntEvaluator(-2147483647);
    private AnimIntEvaluator b = new AnimIntEvaluator(-2147483647);
    private AnimIntEvaluator c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;

    public CarElement(AnimScene animScene) {
        super(animScene);
        ((AnimBitmap[]) this.mAnimEntities)[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_car_fall.png"));
        ((AnimBitmap[]) this.mAnimEntities)[1] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_car_run1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[2] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_car_run2.png"));
        ((AnimBitmap[]) this.mAnimEntities)[3] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_car.png"));
        this.c = new AnimIntEvaluator(203, animScene.getSceneParameter().getMaxFrameNum(), 255, 0);
    }

    public void drawElement(Canvas canvas) {
        ((AnimBitmap[]) this.mAnimEntities)[0].animTranslate().animAlpha().draw(canvas);
    }

    public boolean frameControl(int i) {
        PointI point = this.mAnimScene.getSceneParameter().getPoint();
        if (i == 1) {
            this.e = point.y;
            this.f = point.x;
            this.h = (int) (0.425f * ((float) point.y));
            if (point.x < point.y) {
                this.h = getScalePx(784);
            } else {
                this.h = getScalePx(459);
            }
            ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX((point.x - ((AnimBitmap[]) this.mAnimEntities)[0].getBitmap().getWidth()) / 2);
            ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateY((this.h - getScalePx(784)) - ((AnimBitmap[]) this.mAnimEntities)[0].getBitmap().getHeight());
            this.d = ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX();
            this.g = getScalePx(20);
            this.b.resetEvaluator(1, 1, this.d, this.d);
            this.a.resetEvaluator(6, 11, ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY(), this.h);
        }
        if (this.f != point.x) {
            this.b.skewingValue((point.x - this.f) / 2);
            this.f = point.x;
        }
        int evaluate = this.a.evaluate(i);
        int evaluate2 = this.b.evaluate(i);
        ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateY(evaluate);
        ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(evaluate2);
        if (i > 18 && i < 108) {
            if (i == 19 || i == 20) {
                ((AnimBitmap[]) this.mAnimEntities)[0].setBitmap(((AnimBitmap[]) this.mAnimEntities)[1].getBitmap());
            }
            ((AnimBitmap[]) this.mAnimEntities)[0].setBitmap(((AnimBitmap[]) this.mAnimEntities)[3].getBitmap());
        }
        if (i > 107 && i < 210) {
            if (i % 2 == 0) {
                ((AnimBitmap[]) this.mAnimEntities)[0].setBitmap(((AnimBitmap[]) this.mAnimEntities)[1].getBitmap());
            } else {
                ((AnimBitmap[]) this.mAnimEntities)[0].setBitmap(((AnimBitmap[]) this.mAnimEntities)[2].getBitmap());
            }
            if (i < 158) {
                process9Frame((i - 108) % 10);
            }
            if (i == R$styleable.Theme_Custom_bottom_navigation_text) {
                this.b.resetEvaluator(i, R$styleable.Theme_Custom_label_name_text_color, ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX(), ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX() + AnimSceneResManager.getInstance().getScalePx(40));
            } else if (i == R$styleable.Theme_Custom_label_name_text_color) {
                this.b.resetEvaluator(i, R$styleable.Theme_Custom_event_label_text_color, ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX(), this.d);
            } else if (i == R$styleable.Theme_Custom_event_label_text_color) {
                this.b.resetEvaluator(i, 210, ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX(), this.d - AnimSceneResManager.getInstance().getScalePx(1339));
                this.a.resetEvaluator(i, 210, ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY(), ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY() + AnimSceneResManager.getInstance().getScalePx(139));
            }
        }
        if (i == 11) {
            this.a.resetEvaluator(i, 15, ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY(), ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY() - getScalePx(61));
        } else if (i == 15) {
            this.a.resetEvaluator(i, 18, ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY(), this.h);
        }
        if (i >= 203) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(this.c.evaluate(i));
        }
        return false;
    }

    public void process9Frame(int i) {
        if (i == 0) {
            this.b.resetEvaluator(this.mCurFrame, this.mCurFrame + 5, ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX(), ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX() - this.g);
        } else if (i == 5) {
            this.b.resetEvaluator(this.mCurFrame, this.mCurFrame + 4, ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX(), this.d);
        }
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[4];
    }
}
