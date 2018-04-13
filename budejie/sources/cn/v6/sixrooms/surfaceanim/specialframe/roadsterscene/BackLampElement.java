package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import java.io.File;

public class BackLampElement extends SpecialElement<AnimBitmap> {
    private AnimBitmap[] a;
    private AnimIntEvaluator b = new AnimIntEvaluator(255);
    private AnimIntEvaluator c = new AnimIntEvaluator(R$styleable.Theme_Custom_event_label_text_color, 210, 0, 25);
    private AnimIntEvaluator d = new AnimIntEvaluator(200, 210, 0, 10);
    private int e;
    private int f;

    public BackLampElement(AnimScene animScene) {
        super(animScene);
        this.a = (AnimBitmap[]) animScene.getSceneElement(RoadsterScene.CAR).getAnimEntities();
        ((AnimBitmap[]) this.mAnimEntities)[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_back_lamp1.png"));
        ((AnimBitmap[]) this.mAnimEntities)[1] = new AnimBitmap(((AnimBitmap[]) this.mAnimEntities)[0].getBitmap());
        ((AnimBitmap[]) this.mAnimEntities)[1].setPaint(((AnimBitmap[]) this.mAnimEntities)[0].getPaint());
        ((AnimBitmap[]) this.mAnimEntities)[0].setRotate(2.0f);
        ((AnimBitmap[]) this.mAnimEntities)[1].setRotate(2.0f);
    }

    public void drawElement(Canvas canvas) {
        ((AnimBitmap[]) this.mAnimEntities)[0].animAlpha();
        ((AnimBitmap[]) this.mAnimEntities)[1].animTranslate().animPostRotate().draw(canvas);
        ((AnimBitmap[]) this.mAnimEntities)[0].animTranslate().animPostRotate().draw(canvas);
    }

    public boolean frameControl(int i) {
        if (i < R$styleable.Theme_Custom_add_music_album_btn) {
            return true;
        }
        ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(this.b.evaluate(i));
        if (i == R$styleable.Theme_Custom_add_music_album_btn) {
            this.e = this.a[0].getTranslateX();
            this.f = this.a[0].getTranslateY();
            ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX((this.a[0].getTranslateX() + this.a[0].getWidth()) - AnimSceneResManager.getInstance().getScalePx(225));
            ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateY((this.a[0].getTranslateY() + this.a[0].getHeight()) - AnimSceneResManager.getInstance().getScalePx((int) R$styleable.Theme_Custom_attention_right));
            ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX());
            ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateY(((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY() - AnimSceneResManager.getInstance().getScalePx(20));
            this.b.resetEvaluator(i, 121, 255, 0);
        } else if (i == 125) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(255);
            this.b.resetEvaluator(i, 129, 255, 0);
        } else if (i == R$styleable.Theme_Custom_new_item_login_phone_bg) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(255);
            this.b.resetEvaluator(i, R$styleable.Theme_Custom_divider_horizontal_bg, 255, 0);
        } else if (i == 150) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(255);
            this.b.resetEvaluator(i, 154, 255, 0);
        } else if (i == R$styleable.Theme_Custom_bottom_navigation_text) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(255);
            this.b.resetEvaluator(i, 161, 255, 0);
        } else if (i == R$styleable.Theme_Custom_search_label_item_color_theme) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(255);
            this.b.resetEvaluator(i, 167, 255, 0);
        } else if (i == R$styleable.Theme_Custom_event_label_text_color) {
            ((AnimBitmap[]) this.mAnimEntities)[0].setAlpha(255);
            this.b.resetEvaluator(i, i, 255, 255);
            ((AnimBitmap[]) this.mAnimEntities)[0].setRotate(0.0f);
            ((AnimBitmap[]) this.mAnimEntities)[1].setRotate(0.0f);
        }
        if (i > R$styleable.Theme_Custom_add_music_album_btn) {
            int translateX = this.a[0].getTranslateX() - this.e;
            int translateY = this.a[0].getTranslateY() - this.f;
            this.e = this.a[0].getTranslateX();
            this.f = this.a[0].getTranslateY();
            for (AnimBitmap animBitmap : (AnimBitmap[]) this.mAnimEntities) {
                animBitmap.setTranslateX((animBitmap.getTranslateX() + translateX) - this.c.evaluate(i));
                animBitmap.setTranslateY(animBitmap.getTranslateY() + translateY);
            }
        }
        ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateY(((AnimBitmap[]) this.mAnimEntities)[1].getTranslateY() - this.d.evaluate(i));
        return false;
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[2];
    }
}
