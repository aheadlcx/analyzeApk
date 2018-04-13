package cn.v6.sixrooms.surfaceanim.specialframe.shakecucumberscene;

import android.graphics.Canvas;
import android.util.Log;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.SimpleAnimBitmap;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.util.AnimEvaluatorUtils;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import java.io.File;

public class WordsElement extends SpecialElement {
    private static final int[] c = new int[]{R$styleable.Theme_Custom_label_list_search_icon, 460};
    private static final int[] d = new int[]{148, R$styleable.Theme_Custom_share_message_theme};
    private static final int[] e = new int[]{764, 596};
    int[] a = new int[]{0, 19, 23};
    int b = 0;
    private SimpleAnimBitmap f;
    private SimpleAnimBitmap g;
    private SimpleAnimBitmap h;
    private int i;
    private int j;

    public WordsElement(SpecialScene specialScene) {
        super(specialScene);
        SceneParameter sceneParameter = specialScene.getSceneParameter();
        this.i = specialScene.getOffsetX();
        this.j = specialScene.getOffsetY();
        this.f = new SimpleAnimBitmap(specialScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(sceneParameter.getResPath() + File.separator + "shake_cucumber_body_word.png"));
        this.g = new SimpleAnimBitmap(specialScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(sceneParameter.getResPath() + File.separator + "shake_cucumber_one_word.png"), false);
        this.h = new SimpleAnimBitmap(specialScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(sceneParameter.getResPath() + File.separator + "shake_cucumber_question.png"), false);
        this.f.setOffset((float) this.i, (float) this.j);
        this.g.setOffset((float) this.i, (float) this.j);
        this.h.setOffset((float) this.i, (float) this.j);
        this.f.setMatrixTranslate(148.0f, 352.0f);
        this.mAnimEntities[0] = this.f;
        this.mAnimEntities[1] = this.g;
        this.mAnimEntities[2] = this.h;
    }

    public boolean frameControl(int i) {
        Log.d("WordsElement", "drawOneContent1111---START_FRAME--58");
        if (i >= 58 && i <= R$styleable.Theme_Custom_new_item_shareFriend_text_color) {
            float evaluator;
            float evaluator2;
            float evaluator3;
            if (i > 67 && i < 97) {
                if (i == 68) {
                    this.h.initMatrix();
                    this.h.setMatrixTranslate(764.0f, 596.0f);
                } else if (i >= 69 && i <= 72) {
                    this.h.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator(764.0f, 800.0f, 68, 4, i), AnimEvaluatorUtils.getEvaluator(596.0f, 640.0f, 68, 4, i));
                } else if (i < 73 || i > 78) {
                    this.h.setMatrixTranslate(772.0f, 592.0f);
                } else {
                    this.h.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator(800.0f, 772.0f, 72, 6, i), AnimEvaluatorUtils.getEvaluator(640.0f, 592.0f, 72, 6, i));
                }
            }
            if (i >= 97) {
                if (i == 98) {
                    this.h.initMatrix();
                }
                if (this.b >= this.a.length) {
                    this.h.setMatrixTranslate(772.0f, 592.0f);
                } else {
                    if (i >= this.a[this.b] + 98 && i <= this.a[this.b] + 99) {
                        evaluator = AnimEvaluatorUtils.getEvaluator(0.0f, 23.7f, this.a[this.b] + 97, 2, i);
                        this.h.setMatrixTranslate(772.0f, 592.0f);
                        this.h.postMatrixRotate(evaluator, 772.0f, (float) (this.h.getBitmapHeight() + 592));
                        Log.d("WordsElement", "afterDrawQuestion2222---rotate000--" + evaluator + "--index---" + this.b);
                    } else if (i < this.a[this.b] + 100 || i > this.a[this.b] + 101) {
                        this.h.setMatrixTranslate(772.0f, 592.0f);
                    } else {
                        evaluator = AnimEvaluatorUtils.getEvaluator(23.7f, 0.0f, this.a[this.b] + 99, 2, i);
                        this.h.setMatrixTranslate(772.0f, 592.0f);
                        this.h.postMatrixRotate(evaluator, 772.0f, (float) (this.h.getBitmapHeight() + 592));
                        Log.d("WordsElement", "afterDrawQuestion2222---rotate--" + evaluator + "--index---" + this.b);
                    }
                    if (i == this.a[this.b] + 101) {
                        this.b++;
                    }
                }
            }
            if (i > 62) {
                if (i == 63) {
                    this.g.initMatrix();
                    this.g.setMatrixTranslate(340.0f, 460.0f);
                } else if (i >= 64 && i <= 68) {
                    evaluator = AnimEvaluatorUtils.getEvaluator(340.0f, 452.0f, 63, 5, i);
                    evaluator2 = AnimEvaluatorUtils.getEvaluator(460.0f, 628.0f, 63, 5, i);
                    evaluator3 = AnimEvaluatorUtils.getEvaluator(1.3f, 0.86f, 63, 5, i);
                    this.g.setMatrixTranslate(evaluator, evaluator2);
                    this.g.postScaleByMyself(evaluator3, evaluator3);
                } else if (i >= 69 && i <= 72) {
                    evaluator = AnimEvaluatorUtils.getEvaluator(452.0f, 416.0f, 68, 4, i);
                    evaluator2 = AnimEvaluatorUtils.getEvaluator(628.0f, 600.0f, 68, 4, i);
                    evaluator3 = AnimEvaluatorUtils.getEvaluator(0.86f, 1.0f, 63, 4, i);
                    this.g.setMatrixTranslate(evaluator, evaluator2);
                    this.g.postScaleByMyself(evaluator3, evaluator3);
                }
            }
            if (i == 58) {
                this.f.postMatrixScale(1.35f, 1.35f, (float) (d[0] + (this.f.getBitmapWidth() / 2)), (float) (d[1] + (this.f.getBitmapHeight() / 2)));
            } else if (i >= 59 && i <= 62) {
                evaluator = AnimEvaluatorUtils.getEvaluator(148.0f, 292.0f, 58, 5, i);
                evaluator2 = AnimEvaluatorUtils.getEvaluator(352.0f, 460.0f, 58, 5, i);
                evaluator3 = AnimEvaluatorUtils.getEvaluator(1.35f, 0.85f, 58, 5, i);
                this.f.setMatrixTranslate(evaluator, evaluator2);
                this.f.postScaleByMyself(evaluator3, evaluator3);
            } else if (i >= 63 && i <= 67) {
                evaluator = AnimEvaluatorUtils.getEvaluator(292.0f, 252.0f, 62, 5, i);
                evaluator2 = AnimEvaluatorUtils.getEvaluator(460.0f, 452.0f, 62, 5, i);
                evaluator3 = AnimEvaluatorUtils.getEvaluator(0.85f, 1.0f, 62, 5, i);
                this.f.setMatrixTranslate(evaluator, evaluator2);
                this.f.postScaleByMyself(evaluator3, evaluator3);
            }
        } else if (i >= R$styleable.Theme_Custom_divider_horizontal_bg && i <= R$styleable.Theme_Custom_myinfo_night_model_bg) {
            for (IAnimEntity iAnimEntity : this.mAnimEntities) {
                ((SimpleAnimBitmap) iAnimEntity).getPaint().setAlpha((int) AnimEvaluatorUtils.getEvaluator(225.0f, 0.0f, R$styleable.Theme_Custom_new_item_shareFriend_text_color, 6, i));
            }
        } else if (i != R$styleable.Theme_Custom_phone_edittext_bg) {
            return true;
        } else {
            for (IAnimEntity iAnimEntity2 : this.mAnimEntities) {
                ((SimpleAnimBitmap) iAnimEntity2).getMatrix().setScale(0.0f, 0.0f);
            }
        }
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        IAnimEntity[] iAnimEntityArr = new IAnimEntity[3];
        this.mAnimEntities = iAnimEntityArr;
        return iAnimEntityArr;
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((SimpleAnimBitmap) iAnimEntity).draw(canvas);
        }
    }
}
