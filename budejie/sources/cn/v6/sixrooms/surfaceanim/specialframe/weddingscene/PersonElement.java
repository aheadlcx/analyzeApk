package cn.v6.sixrooms.surfaceanim.specialframe.weddingscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import java.io.File;

public class PersonElement extends SpecialElement<AnimBitmap> {
    private AnimIntEvaluator a = new AnimIntEvaluator(127, 138, 0, 255);
    private AnimIntEvaluator b = new AnimIntEvaluator(R$styleable.Theme_Custom_top_navigation_bar_bg_color, 250, 255, 0);
    private AnimIntEvaluator c = new AnimIntEvaluator(210, R$styleable.Theme_Custom_top_navigation_bar_bg_color, 0, -90);
    private int d;
    private int e;
    private int f;
    private WeddingScene g;
    private AnimBitmap h = ((AnimBitmap) this.g.getSceneElement(WeddingScene.FLOWER_HEART).getAnimEntities()[0]);
    private int i;
    private int j;
    private int k;
    private int l;

    public PersonElement(AnimScene animScene) {
        super(animScene);
        this.g = (WeddingScene) animScene;
        ((AnimBitmap[]) this.mAnimEntities)[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_persion.png"));
        ((AnimBitmap[]) this.mAnimEntities)[1] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "wedding_star_1.png"));
    }

    public void drawElement(Canvas canvas) {
        ((AnimBitmap[]) this.mAnimEntities)[0].animTranslate().animAlpha().draw(canvas);
        if (this.mCurFrame >= 210) {
            ((AnimBitmap[]) this.mAnimEntities)[1].animTranslate().animAlpha().animPostRotate().draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i < 127) {
            return true;
        }
        if (i == 127) {
            this.d = this.h.getTranslateX() + getScalePx(R$styleable.Theme_Custom_recyclerview_load_image_background);
            this.e = this.h.getTranslateY() + getScalePx(R$styleable.Theme_Custom_choose_contact_item_arrow_icon);
            this.i = this.d + getScalePx(100);
            this.j = this.e + getScalePx(R$styleable.Theme_Custom_label_pinner_tabs_bg);
        }
        if (i == R$styleable.Theme_Custom_notice_red_point) {
            this.a.resetEvaluator(R$styleable.Theme_Custom_notice_red_point, R$styleable.Theme_Custom_history_post_describe_text_color, 255, 0);
        }
        this.f = this.a.evaluate(i);
        ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(this.d).setTranslateY(this.e).setAlpha(this.f);
        this.l = this.c.evaluate(i);
        this.k = this.b.evaluate(i);
        ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(this.i).setTranslateY(this.j).setAlpha(this.k).setRotate((float) this.l);
        return false;
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[2];
    }
}
