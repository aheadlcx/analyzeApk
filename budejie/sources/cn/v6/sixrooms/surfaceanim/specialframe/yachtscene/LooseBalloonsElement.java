package cn.v6.sixrooms.surfaceanim.specialframe.yachtscene;

import android.graphics.Canvas;
import android.util.Log;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.SimpleAnimBitmap;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimEvaluatorUtils;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.alibaba.wireless.security.SecExceptionCode;
import com.budejie.www.R$styleable;
import java.io.File;

public class LooseBalloonsElement extends SpecialElement {
    private SimpleAnimBitmap a;
    private SimpleAnimBitmap b;
    private SimpleAnimBitmap c;
    private SimpleAnimBitmap d;
    private SimpleAnimBitmap e;
    private SimpleAnimBitmap f;
    private SimpleAnimBitmap g;
    private SimpleAnimBitmap h;
    private SimpleAnimBitmap i;
    private SimpleAnimBitmap j;
    private int k;
    private int l;

    public LooseBalloonsElement(AnimScene animScene) {
        super(animScene);
        this.k = ((YachtScene) animScene).getOffsetX();
        this.l = ((YachtScene) animScene).getOffsetY();
        this.j = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_pink_ball.png"));
        this.mAnimEntities[0] = this.j;
        this.i = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_blue_ball.png"), false);
        this.mAnimEntities[1] = this.i;
        this.h = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_orange_ball.png"));
        this.mAnimEntities[2] = this.h;
        this.g = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_yellow_ball.png"), false);
        this.mAnimEntities[3] = this.g;
        this.f = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_green_ball.png"), false);
        this.mAnimEntities[4] = this.f;
        this.e = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_pink_ball.png"), false);
        this.mAnimEntities[5] = this.e;
        this.d = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_blue_ball.png"), false);
        this.mAnimEntities[6] = this.d;
        this.c = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_orange_ball.png"), false);
        this.mAnimEntities[7] = this.c;
        this.b = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_yellow_ball.png"), false);
        this.mAnimEntities[8] = this.b;
        this.a = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(this.mAnimScene.getSceneParameter().getResPath() + File.separator + "special_yacht_green_ball.png"), false);
        this.mAnimEntities[9] = this.a;
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((SimpleAnimBitmap) iAnimEntity).draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i < 185 || i > R$styleable.Theme_Custom_follows_shape_item_bg) {
            return true;
        }
        float evaluator;
        float evaluator2;
        float evaluator3;
        float evaluatorForAlpha;
        if (i >= 185 && i <= R$styleable.Theme_Custom_follows_shape_item_bg) {
            this.j.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator((float) (this.k + 400), (float) (this.k + SecExceptionCode.SEC_ERROR_SECURITYBODY), 185, 81, i), AnimEvaluatorUtils.getEvaluator((float) (this.l + 1362), (float) (this.l + 714), 185, 81, i));
            this.j.postRotateByMyself(16.4f);
        }
        if (i == 192) {
            this.i.initMatrix();
        }
        if (i >= 192 && i <= R$styleable.Theme_Custom_select_grid_view_selector) {
            this.i.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator((float) (this.k + R$styleable.Theme_Custom_history_post_content_text_color), (float) (this.k + 1060), 192, 60, i), AnimEvaluatorUtils.getEvaluator((float) (this.l + 1082), (float) (this.l + SecExceptionCode.SEC_ERROR_STA_KEY_ENC_NO_MEMORY), 192, 60, i));
            this.i.postRotateByMyself(AnimEvaluatorUtils.getEvaluator(49.6f, 46.1f, 192, 60, i));
        } else if (i == R$styleable.Theme_Custom_theme_bg_color) {
            this.i.getPaint().setAlpha(0);
        }
        if (i >= 185 && i <= R$styleable.Theme_Custom_follows_shape_item_bg) {
            this.h.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator((float) (this.k + R$styleable.Theme_Custom_new_detail_content_bg), (float) (this.k + 1155), 185, 81, i), AnimEvaluatorUtils.getEvaluator((float) (this.l + 1082), (float) (this.l + 432), 185, 81, i));
            this.h.postRotateByMyself(AnimEvaluatorUtils.getEvaluator(18.1f, 22.5f, 185, 81, i));
        }
        if (i == R$styleable.Theme_Custom_bt2_color_state) {
            this.g.initMatrix();
        }
        if (i >= R$styleable.Theme_Custom_bt2_color_state && i <= R$styleable.Theme_Custom_follows_shape_item_bg) {
            this.g.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator((float) (this.k + 490), (float) (this.k + 1360), R$styleable.Theme_Custom_bt2_color_state, 70, i), AnimEvaluatorUtils.getEvaluator((float) (this.l + SecExceptionCode.SEC_ERROE_OPENSDK_DECODE_FAILED), (float) (this.l + 452), R$styleable.Theme_Custom_bt2_color_state, 70, i));
            this.g.postRotateByMyself(AnimEvaluatorUtils.getEvaluator(25.1f, 33.2f, R$styleable.Theme_Custom_bt2_color_state, 70, i));
        }
        if (i == R$styleable.Theme_Custom_progressbar_bg_state) {
            this.f.initMatrix();
        }
        if (i >= R$styleable.Theme_Custom_progressbar_bg_state && i <= R$styleable.Theme_Custom_more_single_text_color) {
            this.f.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator((float) (this.k + 400), (float) (this.k + 1350), R$styleable.Theme_Custom_progressbar_bg_state, 64, i), AnimEvaluatorUtils.getEvaluator((float) (this.l + 1192), (float) (this.l + R$styleable.Theme_Custom_add_vote_item_bg), R$styleable.Theme_Custom_progressbar_bg_state, 63, i));
            this.f.postRotateByMyself(AnimEvaluatorUtils.getEvaluator(28.7f, 36.8f, R$styleable.Theme_Custom_progressbar_bg_state, 63, i));
        }
        if (i > 201 && i < R$styleable.Theme_Custom_follows_shape_item_bg) {
            if (i == 202) {
                this.e.initMatrix();
            }
            if (i >= 202 && i <= 210) {
                evaluator = AnimEvaluatorUtils.getEvaluator((float) (this.k + R$styleable.Theme_Custom_label_subscribe_bg_gd_color), (float) (this.k + 270), 202, 9, i);
                evaluator2 = AnimEvaluatorUtils.getEvaluator((float) (this.l + 972), (float) (this.l + 922), 202, 9, i);
                evaluator3 = AnimEvaluatorUtils.getEvaluator(28.2f, 27.4f, 202, 9, i);
                evaluatorForAlpha = AnimEvaluatorUtils.getEvaluatorForAlpha(0.0f, 59.9f, 202, 9, i);
                this.e.getPaint().setAlpha((int) evaluatorForAlpha);
            } else if (i < 211 || i > R$styleable.Theme_Custom_follows_shape_bg) {
                evaluatorForAlpha = 0.0f;
                evaluator3 = 0.0f;
                evaluator2 = 0.0f;
                evaluator = 0.0f;
            } else {
                evaluator = AnimEvaluatorUtils.getEvaluator((float) (this.k + 270), (float) (this.k + 1080), 210, 54, i);
                evaluator2 = AnimEvaluatorUtils.getEvaluator((float) (this.l + 922), (float) (this.l + 542), 210, 54, i);
                this.e.setMatrixTranslate(evaluator, evaluator2);
                evaluator3 = AnimEvaluatorUtils.getEvaluator(27.4f, 23.1f, 210, 54, i);
                this.e.postRotateByMyself(evaluator3);
                evaluatorForAlpha = AnimEvaluatorUtils.getEvaluatorForAlpha(59.5f, 78.5f, 210, 63, i);
            }
            this.e.setMatrixTranslate(evaluator, evaluator2);
            this.e.postRotateByMyself(evaluator3);
            Log.d("LooseBalloonsElement", "setBallMartrix7--" + evaluatorForAlpha);
            this.e.getPaint().setAlpha((int) evaluatorForAlpha);
        }
        if (i == 202) {
            this.d.initMatrix();
        }
        if (i >= 202 && i <= R$styleable.Theme_Custom_follows_shape_bg) {
            if (i >= 202 && i <= 210) {
                evaluator = AnimEvaluatorUtils.getEvaluator((float) (this.k + R$styleable.Theme_Custom_label_subscribe_bg_gd_color), (float) (this.k + 270), 202, 9, i);
                evaluator2 = AnimEvaluatorUtils.getEvaluator((float) (this.l + 972), (float) (this.l + 922), 202, 9, i);
                evaluator3 = AnimEvaluatorUtils.getEvaluator(61.3f, 59.4f, 202, 9, i);
                evaluatorForAlpha = AnimEvaluatorUtils.getEvaluatorForAlpha(0.0f, 68.5f, 202, 9, i);
            } else if (i < 211 || i > R$styleable.Theme_Custom_follows_shape_bg) {
                evaluatorForAlpha = 0.0f;
                evaluator3 = 0.0f;
                evaluator2 = 0.0f;
                evaluator = 0.0f;
            } else {
                evaluator = AnimEvaluatorUtils.getEvaluator((float) (this.k + 270), (float) (this.k + 1380), 210, 54, i);
                evaluator2 = AnimEvaluatorUtils.getEvaluator((float) (this.l + 922), (float) (this.l + 422), 210, 54, i);
                evaluator3 = AnimEvaluatorUtils.getEvaluator(59.4f, 46.1f, 210, 54, i);
                evaluatorForAlpha = AnimEvaluatorUtils.getEvaluatorForAlpha(68.5f, 89.9f, 210, 63, i);
            }
            this.d.setMatrixTranslate(evaluator, evaluator2);
            this.d.postRotateByMyself(evaluator3);
            this.d.getPaint().setAlpha((int) evaluatorForAlpha);
        }
        if (i == 210) {
            this.c.initMatrix();
        }
        if (i >= 210 && i <= 218) {
            this.c.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator((float) (this.k + R$styleable.Theme_Custom_attention_right), (float) (this.k + R$styleable.Theme_Custom_forward_icon), 210, 9, i), AnimEvaluatorUtils.getEvaluator((float) (this.l + 1122), (float) (this.l + 1042), 210, 9, i));
            this.c.postRotateByMyself(AnimEvaluatorUtils.getEvaluator(29.9f, 28.8f, 210, 9, i));
            this.c.getPaint().setAlpha((int) AnimEvaluatorUtils.getEvaluatorForAlpha(0.0f, 76.2f, 210, 9, i));
        } else if (i >= 219 && i <= R$styleable.Theme_Custom_xlistview_text_color) {
            this.c.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator((float) (this.k + R$styleable.Theme_Custom_forward_icon), (float) (this.k + 1130), 218, 55, i), AnimEvaluatorUtils.getEvaluator((float) (this.l + 1042), (float) (this.l + R$styleable.Theme_Custom_post_standard_look_background), 218, 55, i));
            this.c.postRotateByMyself(AnimEvaluatorUtils.getEvaluator(28.8f, 22.5f, 218, 55, i));
            this.c.getPaint().setAlpha((int) AnimEvaluatorUtils.getEvaluatorForAlpha(68.5f, 99.9f, 218, 55, i));
        }
        if (i == 210) {
            this.b.initMatrix();
        }
        if (i >= 210 && i <= 218) {
            this.b.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator((float) (this.k + 90), (float) (this.k + R$styleable.Theme_Custom_shape_cmt_like4_bg), 210, 9, i), AnimEvaluatorUtils.getEvaluator((float) (this.l + 1042), (float) (this.l + 962), 210, 9, i));
            this.b.postRotateByMyself(AnimEvaluatorUtils.getEvaluator(36.8f, 36.3f, 210, 9, i));
            this.b.getPaint().setAlpha((int) AnimEvaluatorUtils.getEvaluatorForAlpha(0.0f, 59.7f, 210, 9, i));
        } else if (i >= 219 && i <= R$styleable.Theme_Custom_xlistview_text_color) {
            this.b.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator((float) (this.k + R$styleable.Theme_Custom_shape_cmt_like4_bg), (float) (this.k + SecExceptionCode.SEC_ERROR_SECURITYBODY), 218, 55, i), AnimEvaluatorUtils.getEvaluator((float) (this.l + 962), (float) (this.l + R$styleable.Theme_Custom_pp_btn_cancle_attention), 218, 55, i));
            this.b.postRotateByMyself(AnimEvaluatorUtils.getEvaluator(36.3f, 33.2f, 218, 55, i));
            this.b.getPaint().setAlpha((int) AnimEvaluatorUtils.getEvaluatorForAlpha(59.7f, 78.2f, 218, 55, i));
        }
        if (i == 210) {
            this.a.initMatrix();
        }
        if (i >= 210 && i <= 218) {
            this.a.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator((float) (this.k + R$styleable.Theme_Custom_history_post_content_text_color), (float) (this.k + 400), 210, 9, i), AnimEvaluatorUtils.getEvaluator((float) (this.l + 922), (float) (this.l + 882), 210, 9, i));
            this.a.postRotateByMyself(40.0f);
            this.a.getPaint().setAlpha((int) AnimEvaluatorUtils.getEvaluatorForAlpha(0.0f, 79.6f, 210, 9, i));
        } else if (i >= 219 && i <= R$styleable.Theme_Custom_xlistview_text_color) {
            this.a.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator((float) (this.k + 400), (float) (this.k + 1260), 218, 55, i), AnimEvaluatorUtils.getEvaluator((float) (this.l + 882), (float) (this.l + 492), 218, 55, i));
            this.a.postRotateByMyself(AnimEvaluatorUtils.getEvaluator(40.0f, 36.8f, 218, 55, i));
            this.a.getPaint().setAlpha((int) AnimEvaluatorUtils.getEvaluatorForAlpha(79.6f, 103.0f, 218, 55, i));
        }
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        IAnimEntity[] iAnimEntityArr = new IAnimEntity[10];
        this.mAnimEntities = iAnimEntityArr;
        return iAnimEntityArr;
    }
}
