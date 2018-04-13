package cn.v6.sixrooms.surfaceanim.specialframe.yachtscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.specialframe.util.ScalePxUtil;
import cn.v6.sixrooms.surfaceanim.util.AnimFloatEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import java.io.File;

public class TreeElement extends SpecialElement {
    private AnimBitmap a = ((AnimBitmap) this.mAnimEntities[0]);
    private AnimBitmap b;
    private AnimBitmap c;
    private AnimBitmap d;
    private AnimFloatEvaluator e;
    private AnimFloatEvaluator f;
    private AnimFloatEvaluator g;
    private AnimFloatEvaluator h;
    private AnimIntEvaluator i;
    private AnimIntEvaluator j;
    private AnimIntEvaluator k;
    private AnimIntEvaluator l;

    public TreeElement(AnimScene animScene) {
        super(animScene);
        this.mAnimEntities[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_trunk_right.png"));
        this.mAnimEntities[1] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_leaf_right.png"));
        this.b = (AnimBitmap) this.mAnimEntities[1];
        this.mAnimEntities[2] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_trunk_left.png"));
        this.c = (AnimBitmap) this.mAnimEntities[2];
        this.mAnimEntities[3] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_leaf_left.png"));
        this.d = (AnimBitmap) this.mAnimEntities[3];
        this.a.setTranslateX(ScalePxUtil.getScalePx(890, 0));
        this.a.setTranslateY(ScalePxUtil.getScalePx(470, 1));
        this.b.setTranslateX(ScalePxUtil.getScalePx(728, 0));
        this.b.setTranslateY(ScalePxUtil.getScalePx(R$styleable.Theme_Custom_add_vote_item_edit_color, 1));
        this.c.setTranslateX(ScalePxUtil.getScalePx(-190, 0));
        this.c.setTranslateY(ScalePxUtil.getScalePx(440, 1));
        this.d.setTranslateX(ScalePxUtil.getScalePx(-153, 0));
        this.d.setTranslateY(ScalePxUtil.getScalePx(R$styleable.Theme_Custom_history_post_indicator_icon, 1));
        this.e = new AnimFloatEvaluator(1, 24, 0.0f, -4.0f);
        this.f = new AnimFloatEvaluator(1, 24, 0.0f, -23.2f);
        this.g = new AnimFloatEvaluator(1, 24, 0.0f, 4.0f);
        this.h = new AnimFloatEvaluator(1, 24, 0.0f, 23.2f);
        this.i = new AnimIntEvaluator(1, 24, ScalePxUtil.getScalePx(-153, 0), ScalePxUtil.getScalePx(-110, 0));
        this.j = new AnimIntEvaluator(1, 24, ScalePxUtil.getScalePx(R$styleable.Theme_Custom_history_post_indicator_icon, 1), ScalePxUtil.getScalePx(301, 1));
        this.k = new AnimIntEvaluator(1, 24, ScalePxUtil.getScalePx(728, 0), ScalePxUtil.getScalePx(685, 0));
        this.l = new AnimIntEvaluator(1, 24, ScalePxUtil.getScalePx(R$styleable.Theme_Custom_add_vote_item_edit_color, 1), ScalePxUtil.getScalePx(331, 1));
    }

    public void drawElement(Canvas canvas) {
        for (int i = 0; i < this.mAnimEntities.length; i++) {
            AnimBitmap animBitmap = (AnimBitmap) this.mAnimEntities[i];
            switch (i) {
                case 0:
                    animBitmap.animTranslate().animPostRotate(ScalePxUtil.getScalePx(1076, 0), ScalePxUtil.getScalePx(1140, 1)).draw(canvas);
                    break;
                case 1:
                    animBitmap.animTranslate().animPostRotate().draw(canvas);
                    break;
                case 2:
                    animBitmap.animTranslate().animPostRotate(ScalePxUtil.getScalePx(-13, 0), ScalePxUtil.getScalePx(1110, 1)).draw(canvas);
                    break;
                case 3:
                    animBitmap.animTranslate().animPostRotate().draw(canvas);
                    break;
                default:
                    break;
            }
        }
    }

    public boolean frameControl(int i) {
        int i2 = i % 50;
        if (i2 == 0) {
            i2 = 50;
        }
        if (i2 == 1) {
            this.e.resetEvaluator(i2, 24, 0.0f, -4.0f);
            this.f.resetEvaluator(i2, 24, 0.0f, -23.2f);
            this.g.resetEvaluator(i2, 24, 0.0f, 4.0f);
            this.h.resetEvaluator(i2, 24, 0.0f, 23.2f);
            this.i.resetEvaluator(i2, 24, ScalePxUtil.getScalePx(-153, 0), ScalePxUtil.getScalePx(-110, 0));
            this.j.resetEvaluator(i2, 24, ScalePxUtil.getScalePx(R$styleable.Theme_Custom_history_post_indicator_icon, 1), ScalePxUtil.getScalePx(301, 1));
            this.k.resetEvaluator(i2, 24, ScalePxUtil.getScalePx(728, 0), ScalePxUtil.getScalePx(685, 0));
            this.l.resetEvaluator(i2, 24, ScalePxUtil.getScalePx(R$styleable.Theme_Custom_add_vote_item_edit_color, 1), ScalePxUtil.getScalePx(331, 1));
        }
        if (i2 == 24) {
            this.e.resetEvaluator(i2, 50, -4.0f, 0.0f);
            this.f.resetEvaluator(i2, 50, -23.2f, 0.0f);
            this.g.resetEvaluator(i2, 50, 4.0f, 0.0f);
            this.h.resetEvaluator(i2, 50, 23.2f, 0.0f);
            this.i.resetEvaluator(i2, 50, ScalePxUtil.getScalePx(-110, 0), ScalePxUtil.getScalePx(-153, 0));
            this.j.resetEvaluator(i2, 50, ScalePxUtil.getScalePx(301, 1), ScalePxUtil.getScalePx(R$styleable.Theme_Custom_history_post_indicator_icon, 1));
            this.k.resetEvaluator(i2, 50, ScalePxUtil.getScalePx(685, 0), ScalePxUtil.getScalePx(728, 0));
            this.l.resetEvaluator(i2, 50, ScalePxUtil.getScalePx(331, 1), ScalePxUtil.getScalePx(R$styleable.Theme_Custom_add_vote_item_edit_color, 1));
        }
        this.a.setRotate(this.e.evaluate(i2));
        this.b.setRotate(this.f.evaluate(i2));
        this.c.setRotate(this.g.evaluate(i2));
        this.d.setRotate(this.h.evaluate(i2));
        this.d.setTranslateX(this.i.evaluate(i2));
        this.d.setTranslateY(this.j.evaluate(i2));
        this.b.setTranslateX(this.k.evaluate(i2));
        this.b.setTranslateY(this.l.evaluate(i2));
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[4];
    }
}
