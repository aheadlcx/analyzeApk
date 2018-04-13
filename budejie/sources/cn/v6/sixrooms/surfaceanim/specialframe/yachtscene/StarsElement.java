package cn.v6.sixrooms.surfaceanim.specialframe.yachtscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.specialframe.util.ScalePxUtil;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;

public class StarsElement extends SpecialElement {
    private AnimBitmap a = ((AnimBitmap) this.mAnimEntities[0]);
    private AnimBitmap b;
    private AnimBitmap c;
    private AnimIntEvaluator d;
    private AnimIntEvaluator e;
    private AnimIntEvaluator f;

    public StarsElement(AnimScene animScene) {
        super(animScene);
        this.mAnimEntities[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_star1.png"));
        this.mAnimEntities[1] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_star2.png"));
        this.b = (AnimBitmap) this.mAnimEntities[1];
        this.mAnimEntities[2] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_star3.png"));
        this.c = (AnimBitmap) this.mAnimEntities[2];
        this.a.setTranslateX(ScalePxUtil.getScalePx(R$styleable.Theme_Custom_label_list_item_name, 0));
        this.a.setTranslateY(ScalePxUtil.getScalePx(TbsLog.TBSLOG_CODE_SDK_SELF_MODE, 1));
        this.b.setTranslateX(ScalePxUtil.getScalePx(508, 0));
        this.b.setTranslateY(ScalePxUtil.getScalePx(1162, 1));
        this.c.setTranslateX(ScalePxUtil.getScalePx(782, 0));
        this.c.setTranslateY(ScalePxUtil.getScalePx(1086, 1));
        this.d = new AnimIntEvaluator(1, 13, 0, 255);
        this.e = new AnimIntEvaluator(1, 13, 0, 255);
        this.f = new AnimIntEvaluator(1, 13, 0, 255);
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((AnimBitmap) iAnimEntity).animTranslate().animAlpha().draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i < 104 || i > 185) {
            return true;
        }
        a(((i - 104) % 25) + 1, this.d, this.a);
        if (i < 113) {
            this.b.setAlpha(0);
        } else {
            a(((i - 113) % 25) + 1, this.e, this.b);
        }
        if (i < R$styleable.Theme_Custom_add_music_album_btn) {
            this.c.setAlpha(0);
            return false;
        }
        a(((i - 116) % 25) + 1, this.f, this.c);
        return false;
    }

    private static void a(int i, AnimIntEvaluator animIntEvaluator, AnimBitmap animBitmap) {
        if (i == 1) {
            animIntEvaluator.resetEvaluator(i, 13, 0, 255);
        }
        if (i == 13) {
            animIntEvaluator.resetEvaluator(i, 25, 255, 0);
        }
        animBitmap.setAlpha(animIntEvaluator.evaluate(i));
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[3];
    }
}
