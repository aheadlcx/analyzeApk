package cn.v6.sixrooms.surfaceanim.specialframe.yachtscene;

import android.graphics.Canvas;
import android.util.Log;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.SimpleAnimBitmap;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimEvaluatorUtils;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import java.io.File;

public class BindingBalloonsElement extends SpecialElement {
    private SimpleAnimBitmap a;
    private SimpleAnimBitmap b;
    private SimpleAnimBitmap c;
    private SimpleAnimBitmap d;
    private SimpleAnimBitmap e;
    private SimpleAnimBitmap f;
    private int g;
    private int h;

    public BindingBalloonsElement(AnimScene animScene) {
        super(animScene);
        this.g = ((YachtScene) animScene).getOffsetX();
        this.h = ((YachtScene) animScene).getOffsetY();
        this.a = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_green_ball.png"));
        this.mAnimEntities[0] = this.a;
        this.b = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_yellow_ball.png"));
        this.mAnimEntities[1] = this.b;
        this.c = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_orange_ball.png"));
        this.mAnimEntities[2] = this.c;
        this.d = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_blue_ball.png"));
        this.mAnimEntities[3] = this.d;
        this.e = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_pink_ball.png"));
        this.mAnimEntities[4] = this.e;
        this.f = new SimpleAnimBitmap(animScene.getSceneType(), AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "special_yacht_rope.png"));
        this.mAnimEntities[5] = this.f;
    }

    public void drawElement(Canvas canvas) {
        for (IAnimEntity iAnimEntity : this.mAnimEntities) {
            ((SimpleAnimBitmap) iAnimEntity).draw(canvas);
        }
    }

    public boolean frameControl(int i) {
        if (i < 45 || i > 214) {
            return true;
        }
        if (i >= 45 && i <= 104) {
            a((float) (this.g + 1852), (float) (this.g + 862), (float) (this.h + 852), this.a, i);
            this.a.postRotateByMyself(28.7f);
            a((float) ((this.g + 950) + 990), (float) (this.g + 950), (float) (this.h + 793), this.b, i);
            this.b.postRotateByMyself(25.1f);
            a((float) ((this.g + 849) + 990), (float) (this.g + 849), (float) (this.h + 763), this.c, i);
            this.c.postRotateByMyself(18.1f);
            a((float) ((this.g + 768) + 990), (float) (this.g + 768), (float) (this.h + 890), this.e, i);
            this.e.postRotateByMyself(16.4f);
            a((float) ((this.g + 919) + 990), (float) (this.g + 919), (float) (this.h + 920), this.d, i);
            this.d.postRotateByMyself(49.6f);
            a((float) ((this.g + 750) + 990), (float) (this.g + 750), (float) (this.h + 882), this.f, i);
        } else if (i >= 185 && i <= 214) {
            Log.d("BindingBalloonsElement", "mGreenBalloonBmp--" + this.a + "====" + i);
            b(762.0f, -260.0f, (float) (this.h + 852), this.a, i);
            this.a.postRotateByMyself(28.7f);
            b((float) (this.g + 850), -172.0f, (float) (this.h + 793), this.b, i);
            this.b.postRotateByMyself(25.1f);
            b((float) (this.g + 749), (float) ((this.g + 749) - 1022), (float) (this.h + 763), this.c, i);
            this.c.postRotateByMyself(18.1f);
            b((float) (this.g + 668), (float) ((this.g + 668) - 1022), (float) (this.h + 890), this.e, i);
            this.e.postRotateByMyself(16.4f);
            b((float) (this.g + 819), (float) ((this.g + 819) - 1022), (float) (this.h + 920), this.d, i);
            this.d.postRotateByMyself(49.6f);
            b((float) (this.g + 650), (float) ((this.g + 650) - 1022), (float) (this.h + 882), this.f, i);
        }
        if (i >= 105 && i <= 184) {
            int i2 = (i - 105) / 12;
            a(this.a, this.g + 862, this.g + 762, (float) (this.h + 852), i);
            a(this.a, 28.7f, 36.8f, i, i2);
            a(this.b, this.g + 950, this.g + 850, (float) (this.h + 793), i);
            a(this.b, 25.1f, 33.2f, i, i2);
            a(this.c, this.g + 849, this.g + 749, (float) (this.h + 763), i);
            a(this.c, 18.1f, 22.5f, i, i2);
            a(this.e, this.g + 768, this.g + 668, (float) (this.h + 890), i);
            a(this.e, 16.4f, 23.1f, i, i2);
            a(this.d, this.g + 919, this.g + 819, (float) (this.h + 920), i);
            a(this.d, 49.6f, 46.1f, i, i2);
            a(this.f, this.g + 750, this.g + 650, (float) (this.h + 882), i);
        }
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        IAnimEntity[] iAnimEntityArr = new IAnimEntity[6];
        this.mAnimEntities = iAnimEntityArr;
        return iAnimEntityArr;
    }

    private static void a(float f, float f2, float f3, SimpleAnimBitmap simpleAnimBitmap, int i) {
        simpleAnimBitmap.setMatrixTranslate(AnimEvaluatorUtils.getEvaluator(f, f2, 44, 60, i), f3);
    }

    private static void b(float f, float f2, float f3, SimpleAnimBitmap simpleAnimBitmap, int i) {
        float evaluator = AnimEvaluatorUtils.getEvaluator(f, f2, 185, 30, i);
        if (i == 185) {
            Log.d("BindingBalloonsElement", "setBalloonsRotate001x" + evaluator);
        }
        simpleAnimBitmap.setMatrixTranslate(evaluator, f3);
    }

    private static void a(SimpleAnimBitmap simpleAnimBitmap, int i, int i2, float f, int i3) {
        float evaluator = AnimEvaluatorUtils.getEvaluator((float) i, (float) i2, 104, 80, i3);
        if (i3 == 184) {
            Log.d("BindingBalloonsElement", "setBalloonsRotate000x" + evaluator);
        }
        simpleAnimBitmap.setMatrixTranslate(evaluator, f);
    }

    private static void a(SimpleAnimBitmap simpleAnimBitmap, float f, float f2, int i, int i2) {
        float f3 = 0.0f;
        if (i >= (i2 * 12) + 105 && i <= (i2 * 12) + 110) {
            f3 = AnimEvaluatorUtils.getEvaluator(f, f2, (i2 * 12) + 104, 6, i);
        } else if (i >= (i2 * 12) + 111 && i <= (i2 * 12) + R$styleable.Theme_Custom_add_music_album_btn) {
            f3 = AnimEvaluatorUtils.getEvaluator(f2, f, (i2 * 12) + 110, 6, i);
            Log.d("BindingBalloonsElement", "setBalloonsRotate100" + f3 + "====" + i + "===index---" + i2);
        }
        simpleAnimBitmap.postRotateByMyself(f3);
    }
}
