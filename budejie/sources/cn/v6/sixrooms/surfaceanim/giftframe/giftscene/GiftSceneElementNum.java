package cn.v6.sixrooms.surfaceanim.giftframe.giftscene;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftScene.GiftSceneParameter;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.NumType;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;

public abstract class GiftSceneElementNum extends GiftSceneElement {
    protected int alpha = 255;
    protected Bitmap mBg;
    protected GiftScene mGiftScene;
    protected Bitmap mNumBitmap;
    protected int mNumBitmapMarginLeft;
    protected int mNumBitmapMarginTop;
    protected int mNumBitmapTrans = 0;
    protected Paint mPaint;
    protected Resources mRes = AnimSceneResManager.getInstance().getContext().getResources();
    protected GiftSceneParameter mSceneParameter;
    protected int mTransDistance;
    protected Matrix matrix;
    protected float scale = 2.0f;

    public abstract Bitmap elementBg();

    public abstract NumType elementNum();

    public GiftSceneElementNum(AnimScene animScene) {
        super(animScene);
        this.mGiftScene = (GiftScene) animScene;
        this.mSceneParameter = (GiftSceneParameter) this.mGiftScene.getSceneParameter();
        this.mBg = elementBg();
        this.mNumBitmapMarginLeft = ((this.mRes.getDimensionPixelOffset(R.dimen.gift_num_margin_left) + this.mRes.getDimensionPixelSize(R.dimen.gift_bg_margin_left)) + (this.mBg.getWidth() / 2)) + (this.mRes.getDimensionPixelSize(R.dimen.gift_icon_width) / 2);
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.matrix = new Matrix();
    }

    public void drawElement(Canvas canvas) {
        if (!frameControl(this.mCurFrame)) {
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
            if (this.mNumBitmap == null || this.mNumBitmap.isRecycled()) {
                this.mNumBitmap = AnimSceneResManager.getInstance().getBitmap(GiftSceneUtil.generateNumBitmapKey(elementNum(), this.mSceneParameter.getGiftNum(), this.mGiftScene.hashCode()));
                this.mNumBitmapTrans = (this.mRes.getDimensionPixelSize(R.dimen.gift_bg_margin_left) + (this.mBg.getWidth() / 2)) - (this.mNumBitmap.getWidth() / 2);
                this.mTransDistance = this.mNumBitmapMarginLeft - this.mNumBitmapTrans;
            }
            Bitmap bitmap;
            if (this.mCurFrame <= this.mSceneParameter.getNumEndFrame()) {
                bitmap = AnimSceneResManager.getInstance().getBitmap(GiftSceneUtil.generateNumBitmapKey(elementNum(), this.mSceneParameter.getGiftNum(), this.mGiftScene.hashCode()) + this.mCurFrame);
                if (bitmap != null) {
                    this.mNumBitmap = bitmap;
                }
            } else {
                bitmap = AnimSceneResManager.getInstance().getBitmap(GiftSceneUtil.generateNumBitmapKey(elementNum(), this.mSceneParameter.getGiftNum(), this.mGiftScene.hashCode()));
                if (bitmap != null) {
                    this.mNumBitmap = bitmap;
                }
            }
            this.mNumBitmapMarginTop = ((this.mBg.getHeight() - this.mNumBitmap.getHeight()) / 2) + this.mSceneParameter.getPoint().y;
            this.mPaint.setAlpha(this.alpha);
            this.matrix.setScale(this.scale, this.scale, (float) (this.mNumBitmap.getWidth() / 2), (float) (this.mNumBitmap.getHeight() / 2));
            this.matrix.postTranslate((float) this.mNumBitmapTrans, (float) this.mNumBitmapMarginTop);
            canvas.drawBitmap(this.mNumBitmap, this.matrix, this.mPaint);
            canvas.setDrawFilter(null);
        }
    }

    public boolean frameControl(int i) {
        if (i < 23) {
            return true;
        }
        if (i < 26) {
            this.scale -= (this.scale - 1.0f) / 3.0f;
            this.mNumBitmapTrans += this.mTransDistance / 3;
            return false;
        } else if (i <= 26) {
            this.scale = 1.0f;
            this.mNumBitmapTrans = this.mNumBitmapMarginLeft;
            return false;
        } else if (i <= 28) {
            this.scale += 0.05f;
            this.mNumBitmapTrans = this.mNumBitmapMarginLeft;
            return false;
        } else if (i < 30) {
            this.scale -= 0.05f;
            this.mNumBitmapTrans = this.mNumBitmapMarginLeft;
            return false;
        } else if (i <= this.mAnimScene.getSceneParameter().getMaxFrameNum() - 4) {
            this.scale = 1.0f;
            this.mNumBitmapTrans = this.mNumBitmapMarginLeft;
            return false;
        } else if (i < this.mAnimScene.getSceneParameter().getMaxFrameNum()) {
            this.alpha /= 4;
            this.scale = 1.0f;
            this.mNumBitmapTrans = this.mNumBitmapMarginLeft;
            return false;
        } else if (i < this.mAnimScene.getSceneParameter().getMaxFrameNum()) {
            return false;
        } else {
            this.alpha = 0;
            this.scale = 1.0f;
            this.mNumBitmapTrans = this.mNumBitmapMarginLeft;
            return false;
        }
    }
}
