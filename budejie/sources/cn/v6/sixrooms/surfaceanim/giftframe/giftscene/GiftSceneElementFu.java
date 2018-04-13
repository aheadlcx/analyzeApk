package cn.v6.sixrooms.surfaceanim.giftframe.giftscene;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import cn.v6.sixrooms.surfaceanim.AnimScene;

public abstract class GiftSceneElementFu extends GiftSceneElement {
    int a = 255;
    int b = 0;
    int c = 31;
    private int d;
    protected Bitmap mBg;
    protected Bitmap mBitmap;
    protected Bitmap mBitmap2;
    protected GiftScene mGiftScene;
    protected Paint mPaint1;
    protected Paint mPaint2;
    protected Matrix matrix;

    public abstract Bitmap elementBg();

    public abstract Bitmap elementFu();

    public abstract Bitmap elementFu2();

    public GiftSceneElementFu(AnimScene animScene) {
        super(animScene);
        this.mGiftScene = (GiftScene) animScene;
        this.mPaint1 = new Paint();
        this.mPaint2 = new Paint();
        this.matrix = new Matrix();
        this.mBitmap = elementFu();
        this.mBitmap2 = elementFu2();
        this.mBg = elementBg();
        this.d = (this.mBg.getHeight() / 2) - (this.mBitmap.getHeight() / 2);
    }

    public void drawElement(Canvas canvas) {
        if (!frameControl(this.mCurFrame)) {
            this.mPaint1.setAlpha(this.a);
            this.mPaint2.setAlpha(this.b);
            this.matrix.setTranslate(0.0f, (float) (this.mAnimScene.getSceneParameter().getPoint().y + this.d));
            canvas.drawBitmap(this.mBitmap, this.matrix, this.mPaint1);
            canvas.drawBitmap(this.mBitmap2, this.matrix, this.mPaint2);
        }
    }

    public boolean frameControl(int i) {
        if (i < 75) {
            return true;
        }
        int i2 = i % 24;
        if (i2 == 1) {
            this.a = 255;
            this.b = 0;
        } else if (i2 < 8) {
            this.a = 255;
            this.b += this.c;
        } else if (i2 == 8) {
            this.a = 255;
            this.b = 255;
        } else if (i2 < 16) {
            this.a -= this.c;
            this.b = 255;
        } else if (i2 == 16) {
            this.a = 0;
            this.b = 255;
        } else if (i2 < 24) {
            this.a += this.c;
            this.b -= this.c;
        } else if (i2 == 24) {
            this.a = 255;
            this.b = 0;
        }
        if (i >= this.mAnimScene.getSceneParameter().getMaxFrameNum()) {
            this.a = 0;
            this.b = 0;
        }
        return false;
    }
}
