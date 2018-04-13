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

public class CucumberElement extends SpecialElement {
    int[] a = new int[]{0, 20, 55, 61, 82, 88};
    int b = 0;
    private SimpleAnimBitmap c;
    private SimpleAnimBitmap d;
    private int e;
    private int f;
    private int g;

    public CucumberElement(SpecialScene specialScene) {
        super(specialScene);
        SceneParameter sceneParameter = specialScene.getSceneParameter();
        this.e = specialScene.getSceneType().getIdentification();
        this.f = specialScene.getOffsetX();
        this.g = specialScene.getOffsetY();
        this.c = new SimpleAnimBitmap(specialScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(sceneParameter.getResPath() + File.separator + "shake_cucumber_hand.png"));
        this.c.setOffset((float) this.f, (float) this.g);
        this.mAnimEntities[1] = this.c;
        this.d = new SimpleAnimBitmap(specialScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(sceneParameter.getResPath() + File.separator + "shake_cucumber_body.png"));
        this.d.setOffset((float) this.f, (float) this.g);
        this.mAnimEntities[0] = this.d;
    }

    public boolean frameControl(int i) {
        float f = 191.0f;
        float f2 = 0.0f;
        if (i < 7 || i > R$styleable.Theme_Custom_myinfo_night_model_bg) {
            return true;
        }
        if (i >= 7 && i <= 12) {
            this.d.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator(1080.0f, 520.0f, 7, 6, i), 1050.0f);
        } else if (i >= 13 && i <= 16) {
            f = AnimEvaluatorUtils.getEvaluator(0.0f, -5.7f, 12, 4, i);
            this.d.setMatrixTranslate(520.0f, this.d.getMatrixTranslate()[1]);
            this.d.postMatrixRotate(f, this.d.getMatrixTranslate()[0] + ((float) this.d.getBitmapWidth()), (float) (this.d.getBitmapHeight() + 1000));
        } else if (i < 17 || i > 24) {
            if (i >= 25 && i <= 28) {
                f = AnimEvaluatorUtils.getEvaluator(-5.7f, -17.6f, 25, 4, i);
                this.d.setMatrixTranslate(this.d.getMatrixTranslate()[0], this.d.getMatrixTranslate()[1]);
                this.d.postMatrixRotate(f, this.d.getMatrixTranslate()[0] + ((float) this.d.getBitmapWidth()), this.d.getMatrixTranslate()[1] + ((float) this.d.getBitmapHeight()));
            } else if (i >= 29 && i <= 46) {
                r2 = AnimEvaluatorUtils.getEvaluator(300.0f, 60.0f, 28, 18, i);
                this.d.setMatrixTranslate(AnimEvaluatorUtils.getOval(700.0f, 130, r2), AnimEvaluatorUtils.getOval(500.0f, 650, r2));
                if (i >= 29 && i <= 32) {
                    f = AnimEvaluatorUtils.getEvaluator(-17.6f, 54.3f, 28, 4, i);
                } else if (i >= 33 && i <= 34) {
                    f = AnimEvaluatorUtils.getEvaluator(54.3f, 72.0f, 32, 2, i);
                } else if (i >= 35 && i <= 36) {
                    f = AnimEvaluatorUtils.getEvaluator(72.0f, 84.5f, 34, 2, i);
                } else if (i >= 37 && i <= 38) {
                    f = AnimEvaluatorUtils.getEvaluator(84.5f, 96.7f, 36, 2, i);
                } else if (i >= 39 && i <= 40) {
                    f = AnimEvaluatorUtils.getEvaluator(96.7f, 133.6f, 38, 2, i);
                } else if (i >= 41 && i <= 42) {
                    f = AnimEvaluatorUtils.getEvaluator(133.6f, 147.9f, 40, 2, i);
                } else if (i >= 43 && i <= 44) {
                    f = AnimEvaluatorUtils.getEvaluator(147.9f, 171.7f, 42, 2, i);
                } else if (i < 45 || i > 46) {
                    f = 0.0f;
                } else {
                    f = AnimEvaluatorUtils.getEvaluator(171.7f, 191.0f, 44, 2, i);
                }
                this.d.postRotateByMyself(f);
            } else if (i >= 47 && i <= 49) {
                r2 = AnimEvaluatorUtils.getEvaluator(1400.0f, 1420.0f, 46, 3, i);
                this.d.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator(760.0f, 840.0f, 46, 3, i), r2);
                this.d.postMatrixRotate(191.0f, 880.0f, 1440.0f);
            } else if (i >= 50 && i <= 52) {
                r2 = AnimEvaluatorUtils.getEvaluator(1420.0f, 1410.0f, 49, 3, i);
                this.d.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator(840.0f, 910.0f, 46, 3, i), r2);
                this.d.postMatrixRotate(191.0f, 880.0f, 1440.0f);
            } else if (i < 53 || i > 79) {
                if (i >= 80 && i <= R$styleable.Theme_Custom_account_item_content_layout_bg) {
                    this.d.setMatrixTranslate(910.0f, 1410.0f);
                    if (i >= 80 && i <= 82) {
                        f = AnimEvaluatorUtils.getEvaluator(191.0f, 178.0f, 79, 3, i);
                    } else if (i >= 83 && i <= 85) {
                        f = AnimEvaluatorUtils.getEvaluator(178.0f, 191.0f, 82, 3, i);
                    } else if (i >= 86 && i <= 88) {
                        f = AnimEvaluatorUtils.getEvaluator(191.0f, 178.0f, 85, 3, i);
                    } else if (i >= 89 && i <= 91) {
                        f = AnimEvaluatorUtils.getEvaluator(178.0f, 191.0f, 88, 3, i);
                    } else if (i < 92 || i > 106) {
                        if (i >= 107 && i <= 109) {
                            f = AnimEvaluatorUtils.getEvaluator(191.0f, 178.0f, 106, 3, i);
                        } else if (i >= 110 && i <= 112) {
                            f = AnimEvaluatorUtils.getEvaluator(178.0f, 191.0f, 109, 3, i);
                        } else if (i >= 113 && i <= 115) {
                            f = AnimEvaluatorUtils.getEvaluator(191.0f, 178.0f, 112, 3, i);
                        } else if (i < R$styleable.Theme_Custom_add_music_album_btn || i > R$styleable.Theme_Custom_account_item_content_layout_bg) {
                            f = 0.0f;
                        } else {
                            f = AnimEvaluatorUtils.getEvaluator(178.0f, 191.0f, 115, 3, i);
                        }
                    }
                    Log.d("CucumberElement", "setCucumberRepeat--degrees--" + f + "====frame--" + i);
                    this.d.postMatrixRotate(f, 880.0f, 1440.0f);
                } else if ((i < 119 || i > 129) && i >= 130 && i <= R$styleable.Theme_Custom_new_item_shareFriend_text_color) {
                    this.d.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator(1050.0f, 400.0f, 129, 7, i), 1410.0f);
                    this.d.postMatrixRotate(191.0f, 880.0f, 1440.0f);
                }
            }
        }
        if (i >= 130 && i <= R$styleable.Theme_Custom_myinfo_night_model_bg) {
            this.c.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator(565.0f, 1130.0f, 130, 12, i), 1110.0f);
        } else if (i < 7 || i > 12) {
            if (i >= 25 && i <= 28) {
                f2 = AnimEvaluatorUtils.getEvaluator(0.0f, -20.6f, this.a[this.b] + 24, 4, i);
            } else if (i >= 29 && i <= 32) {
                f2 = AnimEvaluatorUtils.getEvaluator(-20.6f, 7.7f, this.a[this.b] + 28, 4, i);
            } else if (i >= 33 && i <= 44) {
                f2 = 7.7f;
            } else if (i >= 45 && i <= 48) {
                f2 = AnimEvaluatorUtils.getEvaluator(7.7f, -2.5f, this.a[this.b] + 44, 4, i);
            } else if (i >= 49 && i <= 52) {
                f2 = AnimEvaluatorUtils.getEvaluator(-2.5f, 0.0f, this.a[this.b] + 28, 4, i);
            } else if (i >= 80 && i <= 82) {
                f2 = AnimEvaluatorUtils.getEvaluator(0.0f, -2.7f, 79, 3, i);
            } else if (i >= 83 && i <= 85) {
                f2 = AnimEvaluatorUtils.getEvaluator(-2.7f, 0.0f, 82, 3, i);
            } else if (i >= 86 && i <= 88) {
                f2 = AnimEvaluatorUtils.getEvaluator(0.0f, -2.7f, 85, 3, i);
            } else if (i >= 89 && i <= 91) {
                f2 = AnimEvaluatorUtils.getEvaluator(-2.7f, 0.0f, 88, 3, i);
            } else if (i < 92 || i > 106) {
                if (i >= 107 && i <= 109) {
                    f2 = AnimEvaluatorUtils.getEvaluator(0.0f, -2.7f, 106, 3, i);
                } else if (i >= 110 && i <= 112) {
                    f2 = AnimEvaluatorUtils.getEvaluator(-2.7f, 0.0f, 109, 3, i);
                } else if (i >= 113 && i <= 115) {
                    f2 = AnimEvaluatorUtils.getEvaluator(0.0f, -2.7f, 112, 3, i);
                } else if (i >= R$styleable.Theme_Custom_add_music_album_btn && i <= R$styleable.Theme_Custom_account_item_content_layout_bg) {
                    f2 = AnimEvaluatorUtils.getEvaluator(-2.7f, 0.0f, 115, 3, i);
                }
            }
            Log.d("CucumberElement", "setCucumberRepeat--degrees--" + f2 + "====frame--" + i);
            this.c.setMatrixTranslate(565.0f, 1110.0f);
            this.c.postMatrixRotate(f2, 1088.0f, 1110.0f);
        } else {
            this.c.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator(1130.0f, 565.0f, 7, 5, i), 1110.0f);
        }
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        IAnimEntity[] iAnimEntityArr = new IAnimEntity[2];
        this.mAnimEntities = iAnimEntityArr;
        return iAnimEntityArr;
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((SimpleAnimBitmap) iAnimEntity).draw(canvas);
        }
    }
}
