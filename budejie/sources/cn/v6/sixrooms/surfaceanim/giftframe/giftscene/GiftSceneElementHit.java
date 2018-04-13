package cn.v6.sixrooms.surfaceanim.giftframe.giftscene;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;

public abstract class GiftSceneElementHit extends GiftSceneElement {
    protected int alpha = 255;
    protected int alphaFrame;
    protected Bitmap mBg;
    protected GiftScene mGiftScene;
    protected Bitmap mHitBitmap;
    protected int mHitBitmapLeft;
    protected int mHitBitmapTop;
    protected Paint mPaint;
    protected Matrix matrix;
    protected float scale = 1.0f;
    protected float scaleFrame;

    public abstract Bitmap elementBg();

    public abstract Bitmap elementHit();

    public GiftSceneElementHit(AnimScene animScene) {
        super(animScene);
        this.mGiftScene = (GiftScene) animScene;
        this.mPaint = new Paint();
        this.matrix = new Matrix();
        this.mHitBitmap = elementHit();
        this.mBg = elementBg();
        this.mHitBitmapLeft = AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.gift_hit_margin_left);
        this.mHitBitmapTop = (this.mBg.getHeight() / 2) - (this.mHitBitmap.getHeight() / 2);
        this.scaleFrame = 0.05f;
        this.alphaFrame = 51;
    }

    public void drawElement(Canvas canvas) {
        if (!frameControl(this.mCurFrame)) {
            this.mPaint.setAlpha(this.alpha);
            int i = this.mAnimScene.getSceneParameter().getPoint().y;
            this.matrix.setScale(this.scale, this.scale, (float) (this.mHitBitmap.getWidth() / 2), (float) (this.mHitBitmap.getHeight() / 2));
            this.matrix.postTranslate((float) this.mHitBitmapLeft, (float) (i + this.mHitBitmapTop));
            canvas.drawBitmap(this.mHitBitmap, this.matrix, this.mPaint);
        }
    }
}
