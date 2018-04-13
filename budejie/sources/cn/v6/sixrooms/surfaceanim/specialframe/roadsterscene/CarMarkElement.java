package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import java.io.File;

public class CarMarkElement extends SpecialElement<AnimBitmap> {
    private RoadsterScene a;
    private AnimBitmap[] b;
    private Rect c;
    private Rect d;
    private Rect e;
    private Paint f;
    private AnimIntEvaluator g;
    private PointI h = new PointI();

    public CarMarkElement(AnimScene animScene) {
        super(animScene);
        this.a = (RoadsterScene) animScene;
        this.f = new Paint();
        this.f.setColor(Color.parseColor("#ffff00ff"));
        this.b = (AnimBitmap[]) this.a.getSceneElement(RoadsterScene.CAR).getAnimEntities();
        ((AnimBitmap[]) this.mAnimEntities)[0] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_car_mark.png"));
        ((AnimBitmap[]) this.mAnimEntities)[1] = new AnimBitmap(AnimSceneResManager.getInstance().getExternalBitmap(animScene.getSceneParameter().getResPath() + File.separator + "roadster_car_light.png"));
        ((AnimBitmap[]) this.mAnimEntities)[1].getPaint().setAlpha(153);
        this.g = new AnimIntEvaluator(-2147483647);
    }

    public void drawElement(Canvas canvas) {
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), null, 31);
        ((AnimBitmap[]) this.mAnimEntities)[1].getMatrix().setTranslate((float) ((AnimBitmap[]) this.mAnimEntities)[1].getTranslateX(), (float) ((AnimBitmap[]) this.mAnimEntities)[1].getTranslateY());
        ((AnimBitmap[]) this.mAnimEntities)[1].draw(canvas);
        this.f.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        canvas.drawRect(this.c, this.f);
        canvas.drawRect(this.d, this.f);
        canvas.drawRect(this.e, this.f);
        this.f.setXfermode(null);
        ((AnimBitmap[]) this.mAnimEntities)[0].getMatrix().setTranslate((float) ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateX(), (float) ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY());
        ((AnimBitmap[]) this.mAnimEntities)[0].getPaint().setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        ((AnimBitmap[]) this.mAnimEntities)[0].draw(canvas);
        ((AnimBitmap[]) this.mAnimEntities)[0].getPaint().setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }

    public boolean frameControl(int i) {
        if (i < 58 || i >= 96) {
            return true;
        }
        PointI point = this.mAnimScene.getSceneParameter().getPoint();
        if (i == 58) {
            a(point);
            this.g.resetEvaluator(58, 69, ((AnimBitmap[]) this.mAnimEntities)[1].getTranslateX(), ((AnimBitmap[]) this.mAnimEntities)[1].getTranslateX() - getScalePx(R$styleable.Theme_Custom_label_list_item_num));
        } else if (i == 69) {
            this.g.resetEvaluator(i, i + 14, ((AnimBitmap[]) this.mAnimEntities)[1].getTranslateX(), ((AnimBitmap[]) this.mAnimEntities)[1].getTranslateX() - AnimSceneResManager.getInstance().getScalePx(435));
        } else if (i == 83) {
            this.g.resetEvaluator(i, i + 12, ((AnimBitmap[]) this.mAnimEntities)[1].getTranslateX(), ((AnimBitmap[]) this.mAnimEntities)[1].getTranslateX() - AnimSceneResManager.getInstance().getScalePx((int) R$styleable.Theme_Custom_tougao_select_label_text_color));
        }
        if (!(point.x == this.h.x && point.y == this.h.y)) {
            a(point);
            this.g.skewingValue((point.x - this.h.x) / 2);
        }
        ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(this.g.evaluate(i));
        return false;
    }

    private void a(PointI pointI) {
        ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateX(this.b[0].getTranslateX());
        ((AnimBitmap[]) this.mAnimEntities)[0].setTranslateY(this.b[0].getTranslateY());
        ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateX(this.b[0].getTranslateX() + getScalePx(725));
        ((AnimBitmap[]) this.mAnimEntities)[1].setTranslateY(this.b[0].getTranslateY());
        this.c = new Rect(0, this.b[0].getTranslateY(), this.b[0].getTranslateX(), ((AnimBitmap[]) this.mAnimEntities)[0].getBitmap().getHeight() + this.b[0].getTranslateY());
        this.d = new Rect(this.b[0].getBitmap().getWidth() + this.b[0].getTranslateX(), this.b[0].getTranslateY(), pointI.x, ((AnimBitmap[]) this.mAnimEntities)[0].getBitmap().getHeight() + this.b[0].getTranslateY());
        int height = ((AnimBitmap[]) this.mAnimEntities)[0].getBitmap().getHeight() + ((AnimBitmap[]) this.mAnimEntities)[0].getTranslateY();
        this.e = new Rect(0, height, pointI.x, height + 100);
        this.h.x = pointI.x;
        this.h.y = pointI.y;
    }

    public AnimBitmap[] initAnimEntities() {
        return new AnimBitmap[2];
    }
}
