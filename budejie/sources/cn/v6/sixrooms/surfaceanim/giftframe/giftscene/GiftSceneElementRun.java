package cn.v6.sixrooms.surfaceanim.giftframe.giftscene;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;

public abstract class GiftSceneElementRun extends GiftSceneElement {
    protected int alpha = 255;
    protected int alphaFrame;
    protected Bitmap mBg;
    protected GiftScene mGiftScene;
    protected Paint mPaint;
    protected Paint mPaintShade;
    protected Bitmap mRunBitmap;
    protected int mRunBitmapLeft;
    protected int mRunBitmapTop;
    protected Bitmap mRunShadeBitmap;
    protected Matrix matrix;
    protected float scaleX = 1.0f;
    protected float scaleXDest;
    protected float scaleXFrame;
    protected int transXFrame;
    protected int transXFrom;

    public abstract Bitmap elementBg();

    public abstract Bitmap elementRun();

    public GiftSceneElementRun(AnimScene animScene) {
        super(animScene);
        this.mGiftScene = (GiftScene) animScene;
        this.mPaint = new Paint();
        this.mPaintShade = new Paint();
        this.matrix = new Matrix();
        this.mRunBitmap = elementRun();
        this.mRunShadeBitmap = getBitmap(R.drawable.run_shade);
        this.mBg = elementBg();
        this.mRunBitmapLeft = AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.gift_run_margin_left);
        this.mRunBitmapTop = (this.mBg.getHeight() / 2) - (this.mRunBitmap.getHeight() / 2);
        this.transXFrom = -this.mRunBitmap.getWidth();
        this.transXFrame = (this.mRunBitmapLeft - this.transXFrom) / 13;
        this.alphaFrame = this.alpha / 8;
        this.scaleXDest = 0.48349056f;
        this.scaleXFrame = (1.0f - this.scaleXDest) / 8.0f;
    }

    public void drawElement(Canvas canvas) {
        if (!frameControl(this.mCurFrame)) {
            int saveLayer = canvas.saveLayer(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), null, 31);
            this.mPaint.setAlpha(this.alpha);
            int i = this.mAnimScene.getSceneParameter().getPoint().y;
            this.matrix.setScale(this.scaleX, 1.0f, (float) this.mRunBitmap.getWidth(), (float) this.mRunBitmap.getHeight());
            this.matrix.postTranslate((float) this.transXFrom, (float) (this.mRunBitmapTop + i));
            canvas.drawBitmap(this.mRunBitmap, this.matrix, this.mPaint);
            this.mPaintShade.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
            canvas.drawBitmap(this.mRunShadeBitmap, 0.0f, (float) (i + this.mRunBitmapTop), this.mPaintShade);
            this.mPaintShade.setXfermode(null);
            canvas.restoreToCount(saveLayer);
        }
    }
}
